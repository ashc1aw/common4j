package cc.ashclaw.common4j.cache.aspect;

import cc.ashclaw.common4j.cache.annotation.Cacheable;
import cc.ashclaw.common4j.cache.annotation.CacheEvict;
import cc.ashclaw.common4j.cache.annotation.CachePut;
import cc.ashclaw.common4j.cache.config.CacheProperties;
import cc.ashclaw.common4j.cache.core.Cache;
import cc.ashclaw.common4j.cache.core.CacheManager;
import cc.ashclaw.common4j.cache.enums.CacheType;
import cc.ashclaw.common4j.cache.protection.CacheAvalancheProtection;
import cc.ashclaw.common4j.cache.support.CacheKeyGenerator;
import cc.ashclaw.common4j.cache.support.ExpressionEvaluator;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * Aspect for handling cache-related annotations.
 * <p>
 * 处理缓存相关注解的切面类。
 * <p>
 * This aspect provides AOP support for cache operations including
 * {@code @Cacheable}, {@code @CacheEvict}, and {@code @CachePut} annotations.
 * It handles cache retrieval, eviction, and updates with support for
 * multi-level caching and protection mechanisms.
 * <p>
 * 此切面为缓存操作提供AOP支持，包括 {@code @Cacheable}、{@code @CacheEvict} 和 {@code @CachePut} 注解。
 * 它处理缓存检索、清除和更新，支持多级缓存和保护机制。
 *
 * @author b1itz7
 * @since 1.1.0
 */
@Aspect
@Component
public class CacheAspect {
    
    /**
     * The cache manager for managing cache instances.
     * <p>
     * 用于管理缓存实例的缓存管理器。
     */
    @Autowired
    private CacheManager cacheManager;
    
    /**
     * The cache key generator for generating cache keys.
     * <p>
     * 用于生成缓存键的缓存键生成器。
     */
    @Autowired
    private CacheKeyGenerator cacheKeyGenerator;
    
    /**
     * The expression evaluator for evaluating SpEL expressions.
     * <p>
     * 用于评估SpEL表达式的表达式评估器。
     */
    @Autowired
    private ExpressionEvaluator expressionEvaluator;
    
    /**
     * The cache properties configuration.
     * <p>
     * 缓存属性配置。
     */
    @Autowired
    private CacheProperties cacheProperties;
    
    /**
     * The Redisson client for distributed caching operations.
     * <p>
     * 用于分布式缓存操作的Redisson客户端。
     */
    @Autowired(required = false)
    private RedissonClient redissonClient;
    
    /**
     * Handles the {@code @Cacheable} annotation by intercepting method calls
     * and implementing cache retrieval logic.
     * <p>
     * 通过拦截方法调用并实现缓存检索逻辑来处理 {@code @Cacheable} 注解。
     *
     * @param joinPoint the proceeding join point representing the intercepted method
     *                  <p>
     *                  表示被拦截方法的连接点
     * @param cacheable the {@code @Cacheable} annotation instance
     *                  <p>
     *                  {@code @Cacheable} 注解实例
     * @return the cached result if available, otherwise the result of the method invocation
     *         <p>
     *         如果缓存可用则返回缓存结果，否则返回方法调用的结果
     * @throws Throwable if an error occurs during method execution
     *                   <p>
     *                   如果在方法执行期间发生错误
     */
    @Around("@annotation(cacheable)")
    public Object aroundCacheable(ProceedingJoinPoint joinPoint, Cacheable cacheable) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Object[] args = joinPoint.getArgs();
        Object target = joinPoint.getTarget();
        
        // 检查条件
        if (!expressionEvaluator.evaluateCondition(cacheable.condition(), target, method, args, null)) {
            return joinPoint.proceed();
        }
        
        // 生成缓存键
        Object key = generateCacheKey(cacheable, method, args, target);
        
        // 获取缓存
        Cache cache = getCache(cacheable.cacheNames()[0], cacheable.cacheType());
        
        // 尝试从缓存获取
        Object result = cache.get(key, Object.class);
        if (result != null) {
            return result;
        }
        
        // 缓存未命中，执行原方法
        result = joinPoint.proceed();
        
        // 检查unless条件
        if (expressionEvaluator.evaluateUnless(cacheable.unless(), target, method, args, result)) {
            return result;
        }
        
        // 写入缓存
        if (result != null) {
            long expireTime = cacheable.expire() > 0 ? cacheable.expire() : 
                cacheProperties.getExpireTime().toSeconds();
            TimeUnit timeUnit = cacheable.timeUnit();
            
            if (cacheable.avalancheProtection() && cacheProperties.isProtectionEnabled()) {
                CacheAvalancheProtection protection = new CacheAvalancheProtection(cache, cacheProperties);
                protection.putWithProtection(key, result, expireTime, timeUnit);
            } else {
                cache.put(key, result, expireTime, timeUnit);
            }
        }
        
        return result;
    }
    
    /**
     * Handles the {@code @CacheEvict} annotation by intercepting method calls
     * and implementing cache eviction logic.
     * <p>
     * 通过拦截方法调用并实现缓存清除逻辑来处理 {@code @CacheEvict} 注解。
     *
     * @param joinPoint the proceeding join point representing the intercepted method
     *                  <p>
     *                  表示被拦截方法的连接点
     * @param cacheEvict the {@code @CacheEvict} annotation instance
     *                   <p>
     *                   {@code @CacheEvict} 注解实例
     * @return the result of the method invocation
     *         <p>
     *         方法调用的结果
     * @throws Throwable if an error occurs during method execution
     *                   <p>
     *                   如果在方法执行期间发生错误
     */
    @Around("@annotation(cacheEvict)")
    public Object aroundCacheEvict(ProceedingJoinPoint joinPoint, CacheEvict cacheEvict) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Object[] args = joinPoint.getArgs();
        Object target = joinPoint.getTarget();
        
        // 检查条件
        if (!expressionEvaluator.evaluateCondition(cacheEvict.condition(), target, method, args, null)) {
            return joinPoint.proceed();
        }
        
        Object result;
        
        // 是否在方法调用前清除
        if (cacheEvict.beforeInvocation()) {
            evictCache(cacheEvict, method, args, target);
            result = joinPoint.proceed();
        } else {
            result = joinPoint.proceed();
            evictCache(cacheEvict, method, args, target);
        }
        
        return result;
    }
    
    /**
     * Handles the {@code @CachePut} annotation by intercepting method calls
     * and implementing cache update logic.
     * <p>
     * 通过拦截方法调用并实现缓存更新逻辑来处理 {@code @CachePut} 注解。
     *
     * @param joinPoint the proceeding join point representing the intercepted method
     *                  <p>
     *                  表示被拦截方法的连接点
     * @param cachePut the {@code @CachePut} annotation instance
     *                 <p>
     *                 {@code @CachePut} 注解实例
     * @return the result of the method invocation
     *         <p>
     *         方法调用的结果
     * @throws Throwable if an error occurs during method execution
     *                   <p>
     *                   如果在方法执行期间发生错误
     */
    @Around("@annotation(cachePut)")
    public Object aroundCachePut(ProceedingJoinPoint joinPoint, CachePut cachePut) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Object[] args = joinPoint.getArgs();
        Object target = joinPoint.getTarget();
        
        // 检查条件
        if (!expressionEvaluator.evaluateCondition(cachePut.condition(), target, method, args, null)) {
            return joinPoint.proceed();
        }
        
        // 执行原方法
        Object result = joinPoint.proceed();
        
        // 检查unless条件
        if (expressionEvaluator.evaluateUnless(cachePut.unless(), target, method, args, result)) {
            return result;
        }
        
        // 写入缓存
        if (result != null) {
            Object key = generateCacheKey(cachePut, method, args, target);
            Cache cache = getCache(cachePut.cacheNames()[0], cachePut.cacheType());
            
            long expireTime = cachePut.expire() > 0 ? cachePut.expire() : 
                cacheProperties.getExpireTime().toSeconds();
            TimeUnit timeUnit = cachePut.timeUnit();
            
            cache.put(key, result, expireTime, timeUnit);
        }
        
        return result;
    }
    
    /**
     * Generates a cache key for the {@code @Cacheable} annotation.
     * <p>
     * 为 {@code @Cacheable} 注解生成缓存键。
     *
     * @param cacheable the {@code @Cacheable} annotation instance
     *                  <p>
     *                  {@code @Cacheable} 注解实例
     * @param method the intercepted method
     *               <p>
     *               被拦截的方法
     * @param args the method arguments
     *             <p>
     *             方法参数
     * @param target the target object
     *               <p>
     *               目标对象
     * @return the generated cache key
     *         <p>
     *         生成的缓存键
     */
    private Object generateCacheKey(cc.ashclaw.common4j.cache.annotation.Cacheable cacheable, 
                                   Method method, Object[] args, Object target) {
        if (cacheable.key().isEmpty()) {
            return cacheKeyGenerator.generate(target, method, args);
        } else {
            return cacheKeyGenerator.generateKeyBySpEL(cacheable.key(), target, method, args);
        }
    }
    
    /**
     * Generates a cache key for the {@code @CacheEvict} annotation.
     * <p>
     * 为 {@code @CacheEvict} 注解生成缓存键。
     *
     * @param cacheEvict the {@code @CacheEvict} annotation instance
     *                   <p>
     *                   {@code @CacheEvict} 注解实例
     * @param method the intercepted method
     *               <p>
     *               被拦截的方法
     * @param args the method arguments
     *             <p>
     *             方法参数
     * @param target the target object
     *               <p>
     *               目标对象
     * @return the generated cache key
     *         <p>
     *         生成的缓存键
     */
    private Object generateCacheKey(CacheEvict cacheEvict, Method method, Object[] args, Object target) {
        if (cacheEvict.key().isEmpty()) {
            return cacheKeyGenerator.generate(target, method, args);
        } else {
            return cacheKeyGenerator.generateKeyBySpEL(cacheEvict.key(), target, method, args);
        }
    }
    
    /**
     * Generates a cache key for the {@code @CachePut} annotation.
     * <p>
     * 为 {@code @CachePut} 注解生成缓存键。
     *
     * @param cachePut the {@code @CachePut} annotation instance
     *                 <p>
     *                 {@code @CachePut} 注解实例
     * @param method the intercepted method
     *               <p>
     *               被拦截的方法
     * @param args the method arguments
     *             <p>
     *             方法参数
     * @param target the target object
     *               <p>
     *               目标对象
     * @return the generated cache key
     *         <p>
     *         生成的缓存键
     */
    private Object generateCacheKey(CachePut cachePut, Method method, Object[] args, Object target) {
        if (cachePut.key().isEmpty()) {
            return cacheKeyGenerator.generate(target, method, args);
        } else {
            return cacheKeyGenerator.generateKeyBySpEL(cachePut.key(), target, method, args);
        }
    }
    
    /**
     * Gets a cache instance by name and type.
     * <p>
     * 按名称和类型获取缓存实例。
     *
     * @param cacheName the name of the cache
     *                  <p>
     *                  缓存名称
     * @param cacheType the type of the cache
     *                  <p>
     *                  缓存类型
     * @return the cache instance
     *         <p>
     *         缓存实例
     */
    private Cache getCache(String cacheName, CacheType cacheType) {
        return cacheManager.getCache(cacheName);
    }
    
    /**
     * Evicts cache entries based on the {@code @CacheEvict} annotation configuration.
     * <p>
     * 根据 {@code @CacheEvict} 注解配置清除缓存条目。
     *
     * @param cacheEvict the {@code @CacheEvict} annotation instance
     *                   <p>
     *                   {@code @CacheEvict} 注解实例
     * @param method the intercepted method
     *               <p>
     *               被拦截的方法
     * @param args the method arguments
     *             <p>
     *             方法参数
     * @param target the target object
     *               <p>
     *               目标对象
     */
    private void evictCache(CacheEvict cacheEvict, Method method, Object[] args, Object target) {
        for (String cacheName : cacheEvict.cacheNames()) {
            Cache cache = getCache(cacheName, cacheEvict.cacheType());
            
            if (cacheEvict.allEntries()) {
                cache.clear();
            } else {
                Object key = generateCacheKey(cacheEvict, method, args, target);
                cache.evict(key);
            }
        }
    }
}