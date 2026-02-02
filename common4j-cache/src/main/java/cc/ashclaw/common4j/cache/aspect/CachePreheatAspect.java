package cc.ashclaw.common4j.cache.aspect;

import cc.ashclaw.common4j.cache.annotation.CachePreheat;
import cc.ashclaw.common4j.cache.config.CacheProperties;
import cc.ashclaw.common4j.cache.core.Cache;
import cc.ashclaw.common4j.cache.core.CacheManager;
import cc.ashclaw.common4j.cache.support.CacheKeyGenerator;
import cc.ashclaw.common4j.cache.support.ExpressionEvaluator;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * Aspect for handling cache preheating using the {@code @CachePreheat} annotation.
 * <p>
 * 处理使用 {@code @CachePreheat} 注解的缓存预热的切面类。
 * <p>
 * This aspect provides AOP support for cache preheating operations that
 * automatically execute during application startup to preload cache data.
 * <p>
 * 此切面为缓存预热操作提供AOP支持，在应用程序启动期间自动执行以预加载缓存数据。
 *
 * @author b1itz7
 * @since 1.1.0
 */
@Aspect
@Component
public class CachePreheatAspect implements ApplicationListener<ApplicationReadyEvent> {
    
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
     * The list of preheat tasks to be executed during application startup.
     * <p>
     * 在应用程序启动期间要执行的预热任务列表。
     */
    private final List<PreheatTask> preheatTasks = new ArrayList<>();
    
    /**
     * Handles the {@code @CachePreheat} annotation by intercepting method calls
     * and recording preheat tasks for later execution.
     * <p>
     * 通过拦截方法调用并记录预热任务以供稍后执行来处理 {@code @CachePreheat} 注解。
     *
     * @param joinPoint the proceeding join point representing the intercepted method
     *                  <p>
     *                  表示被拦截方法的连接点
     * @param cachePreheat the {@code @CachePreheat} annotation instance
     *                     <p>
     *                     {@code @CachePreheat} 注解实例
     * @return the result of the method invocation
     *         <p>
     *         方法调用的结果
     * @throws Throwable if an error occurs during method execution
     *                   <p>
     *                   如果在方法执行期间发生错误
     */
    @Around("@annotation(cachePreheat)")
    public Object aroundCachePreheat(ProceedingJoinPoint joinPoint, CachePreheat cachePreheat) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        
        // 记录预热任务
        PreheatTask task = new PreheatTask(joinPoint, cachePreheat, method);
        preheatTasks.add(task);
        
        // 正常执行方法
        return joinPoint.proceed();
    }
    
    /**
     * Handles the application ready event by executing all recorded preheat tasks.
     * <p>
     * 通过执行所有记录的预热任务来处理应用程序就绪事件。
     *
     * @param event the application ready event
     *              <p>
     *              应用程序就绪事件
     */
    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        // 应用启动完成后执行预热任务
        executePreheatTasks();
    }
    
    /**
     * Executes all recorded preheat tasks.
     * <p>
     * 执行所有记录的预热任务。
     * <p>
     * Tasks are executed in order of priority (lower order value has higher priority).
     * Asynchronous tasks are executed in parallel, while synchronous tasks are
     * executed sequentially.
     * <p>
     * 任务按优先级顺序执行（较低的顺序值具有较高的优先级）。
     * 异步任务并行执行，而同步任务顺序执行。
     */
    private void executePreheatTasks() {
        if (preheatTasks.isEmpty()) {
            return;
        }
        
        // 按order排序
        preheatTasks.sort((t1, t2) -> Integer.compare(t1.cachePreheat.order(), t2.cachePreheat.order()));
        
        List<CompletableFuture<Void>> futures = new ArrayList<>();
        
        for (PreheatTask task : preheatTasks) {
            if (task.cachePreheat.async()) {
                // 异步执行
                CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                    executePreheatTask(task);
                });
                futures.add(future);
            } else {
                // 同步执行
                executePreheatTask(task);
            }
        }
        
        // 等待所有异步任务完成
        if (!futures.isEmpty()) {
            CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
        }
    }
    
    /**
     * Executes a single preheat task.
     * <p>
     * 执行单个预热任务。
     *
     * @param task the preheat task to execute
     *             <p>
     *             要执行的预热任务
     */
    private void executePreheatTask(PreheatTask task) {
        try {
            Object target = task.joinPoint.getTarget();
            Method method = task.method;
            Object[] args = task.joinPoint.getArgs();
            
            // 检查预热条件
            if (!task.cachePreheat.condition().isEmpty()) {
                boolean condition = expressionEvaluator.evaluateCondition(
                    task.cachePreheat.condition(), target, method, args, null);
                if (!condition) {
                    return;
                }
            }
            
            // 执行预热方法
            Object result = task.joinPoint.proceed();
            
            if (result != null) {
                // 生成缓存键
                Object key = generateCacheKey(task.cachePreheat, method, args, target);
                
                // 写入缓存
                for (String cacheName : task.cachePreheat.cacheNames()) {
                    Cache cache = cacheManager.getCache(cacheName);
                    
                    long expireTime = task.cachePreheat.expire();
                    TimeUnit timeUnit = task.cachePreheat.timeUnit();
                    
                    cache.put(key, result, expireTime, timeUnit);
                }
            }
        } catch (Throwable e) {
            throw new RuntimeException("执行缓存预热任务失败", e);
        }
    }
    
    /**
     * Generates a cache key for the {@code @CachePreheat} annotation.
     * <p>
     * 为 {@code @CachePreheat} 注解生成缓存键。
     *
     * @param cachePreheat the {@code @CachePreheat} annotation instance
     *                     <p>
     *                     {@code @CachePreheat} 注解实例
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
    private Object generateCacheKey(CachePreheat cachePreheat, Method method, Object[] args, Object target) {
        if (cachePreheat.key().isEmpty()) {
            return cacheKeyGenerator.generate(target, method, args);
        } else {
            return cacheKeyGenerator.generateKeyBySpEL(cachePreheat.key(), target, method, args);
        }
    }
    
    /**
     * Represents a cache preheat task.
     * <p>
     * 表示一个缓存预热任务。
     * <p>
     * This class encapsulates the information needed to execute a preheat operation,
     * including the join point, annotation configuration, and method metadata.
     * <p>
     * 此类封装了执行预热操作所需的信息，包括连接点、注解配置和方法元数据。
     */
    private static class PreheatTask {
        /**
         * The join point representing the intercepted method.
         * <p>
         * 表示被拦截方法的连接点。
         */
        final ProceedingJoinPoint joinPoint;
        
        /**
         * The {@code @CachePreheat} annotation instance.
         * <p>
         * {@code @CachePreheat} 注解实例。
         */
        final CachePreheat cachePreheat;
        
        /**
         * The intercepted method.
         * <p>
         * 被拦截的方法。
         */
        final Method method;
        
        /**
         * Constructs a new PreheatTask with the specified parameters.
         * <p>
         * 使用指定的参数构造一个新的 PreheatTask。
         *
         * @param joinPoint the proceeding join point
         *                  <p>
         *                  连接点
         * @param cachePreheat the cache preheat annotation
         *                     <p>
         *                     缓存预热注解
         * @param method the intercepted method
         *               <p>
         *               被拦截的方法
         */
        PreheatTask(ProceedingJoinPoint joinPoint, CachePreheat cachePreheat, Method method) {
            this.joinPoint = joinPoint;
            this.cachePreheat = cachePreheat;
            this.method = method;
        }
    }
}