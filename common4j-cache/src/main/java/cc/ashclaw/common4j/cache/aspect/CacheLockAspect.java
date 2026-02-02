package cc.ashclaw.common4j.cache.aspect;

import cc.ashclaw.common4j.cache.annotation.CacheLock;
import cc.ashclaw.common4j.cache.exception.CacheLockException;
import cc.ashclaw.common4j.cache.impl.redisson.RedissonLock;
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

/**
 * Aspect for handling distributed locking using the {@code @CacheLock} annotation.
 * <p>
 * 处理使用 {@code @CacheLock} 注解的分布式锁定的切面类。
 * <p>
 * This aspect provides AOP support for distributed locking operations
 * using Redisson as the underlying distributed lock implementation.
 * <p>
 * 此切面为分布式锁定操作提供AOP支持，使用Redisson作为底层分布式锁实现。
 *
 * @author b1itz7
 * @since 1.1.0
 */
@Aspect
@Component
public class CacheLockAspect {
    
    /**
     * The Redisson client for distributed locking operations.
     * <p>
     * 用于分布式锁定操作的Redisson客户端。
     */
    @Autowired
    private RedissonClient redissonClient;
    
    /**
     * The cache key generator for generating lock keys.
     * <p>
     * 用于生成锁键的缓存键生成器。
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
     * Handles the {@code @CacheLock} annotation by intercepting method calls
     * and implementing distributed locking logic.
     * <p>
     * 通过拦截方法调用并实现分布式锁定逻辑来处理 {@code @CacheLock} 注解。
     *
     * @param joinPoint the proceeding join point representing the intercepted method
     *                  <p>
     *                  表示被拦截方法的连接点
     * @param cacheLock the {@code @CacheLock} annotation instance
     *                  <p>
     *                  {@code @CacheLock} 注解实例
     * @return the result of the method invocation
     *         <p>
     *         方法调用的结果
     * @throws Throwable if an error occurs during method execution or lock acquisition
     *                   <p>
     *                   如果在方法执行或锁获取期间发生错误
     */
    @Around("@annotation(cacheLock)")
    public Object aroundCacheLock(ProceedingJoinPoint joinPoint, CacheLock cacheLock) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Object[] args = joinPoint.getArgs();
        Object target = joinPoint.getTarget();
        
        // 生成锁键
        String lockKey = generateLockKey(cacheLock, method, args, target);
        
        // 创建分布式锁
        RedissonLock lock = new RedissonLock(redissonClient, lockKey, cacheLock.type());
        
        Object result;
        
        try {
            // 获取锁
            boolean locked = acquireLock(lock, cacheLock);
            if (!locked) {
                throw new CacheLockException(cacheLock.errorMessage());
            }
            
            try {
                // 执行原方法
                result = joinPoint.proceed();
            } finally {
                // 释放锁
                lock.unlock();
            }
        } catch (Exception e) {
            if (e instanceof CacheLockException) {
                throw e;
            }
            throw new CacheLockException("执行分布式锁操作失败", e);
        }
        
        return result;
    }
    
    /**
     * Generates a lock key for the {@code @CacheLock} annotation.
     * <p>
     * 为 {@code @CacheLock} 注解生成锁键。
     *
     * @param cacheLock the {@code @CacheLock} annotation instance
     *                  <p>
     *                  {@code @CacheLock} 注解实例
     * @param method the intercepted method
     *               <p>
     *               被拦截的方法
     * @param args the method arguments
     *             <p>
     *             方法参数
     * @param target the target object
     *               <p>
     *               目标对象
     * @return the generated lock key
     *         <p>
     *         生成的锁键
     */
    private String generateLockKey(CacheLock cacheLock, Method method, Object[] args, Object target) {
        String baseKey = cacheLock.value().isEmpty() ? method.getName() : cacheLock.value();
        
        if (cacheLock.key().isEmpty()) {
            Object keySuffix = cacheKeyGenerator.generate(target, method, args);
            return baseKey + ":" + keySuffix;
        } else {
            Object keySuffix = cacheKeyGenerator.generateKeyBySpEL(cacheLock.key(), target, method, args);
            return baseKey + ":" + keySuffix;
        }
    }
    
    /**
     * Acquires a lock based on the {@code @CacheLock} annotation configuration.
     * <p>
     * 根据 {@code @CacheLock} 注解配置获取锁。
     *
     * @param lock the Redisson lock instance
     *             <p>
     *             Redisson锁实例
     * @param cacheLock the {@code @CacheLock} annotation instance
     *                  <p>
     *                  {@code @CacheLock} 注解实例
     * @return {@code true} if the lock was acquired successfully, {@code false} otherwise
     *         <p>
     *         如果成功获取锁，则返回 {@code true}；否则返回 {@code false}
     */
    private boolean acquireLock(RedissonLock lock, CacheLock cacheLock) {
        if (cacheLock.tryLock()) {
            // 尝试获取锁
            if (cacheLock.waitTime() > 0) {
                return lock.tryLock(cacheLock.waitTime(), cacheLock.leaseTime(), cacheLock.waitTimeUnit());
            } else {
                return lock.tryLock();
            }
        } else {
            // 阻塞获取锁
            if (cacheLock.waitTime() > 0) {
                lock.lock(cacheLock.leaseTime(), cacheLock.leaseTimeUnit());
            } else {
                lock.lock();
            }
            return true;
        }
    }
}