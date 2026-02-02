package cc.ashclaw.common4j.cache.annotation;

import cc.ashclaw.common4j.cache.enums.CacheType;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * Annotation for cache preheating operations.
 * <p>
 * 缓存预热操作的注解。
 * <p>
 * This annotation is used to preload cache data during application startup
 * or at specific times to improve performance.
 * <p>
 * 此注解用于在应用程序启动时或在特定时间预加载缓存数据以提高性能。
 *
 * @author b1itz7
 * @since 1.1.0
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CachePreheat {
    
    /**
     * The names of the caches to preheat.
     * <p>
     * 要预热的缓存名称。
     *
     * @return the names of the caches
     *         <p>
     *         缓存名称
     */
    String[] cacheNames() default {};
    
    /**
     * The SpEL expression for computing the cache key dynamically.
     * <p>
     * 用于动态计算缓存键的SpEL表达式。
     *
     * @return the SpEL expression for the cache key
     *         <p>
     *         缓存键的SpEL表达式
     */
    String key() default "";
    
    /**
     * The type of cache to use for preheating.
     * <p>
     * 用于预热的缓存类型。
     * <p>
     * Default is multi-level caching (local + distributed).
     * <p>
     * 默认为多级缓存（本地 + 分布式）。
     *
     * @return the cache type
     *         <p>
     *         缓存类型
     */
    CacheType cacheType() default CacheType.MULTI_LEVEL;
    
    /**
     * The expiration time for the preheated cache entries.
     * <p>
     * 预热的缓存条目的过期时间。
     *
     * @return the expiration time
     *         <p>
     *         过期时间
     */
    long expire() default 3600;
    
    /**
     * The time unit for the expiration time.
     * <p>
     * 过期时间的时间单位。
     *
     * @return the time unit
     *         <p>
     *         时间单位
     */
    TimeUnit timeUnit() default TimeUnit.SECONDS;
    
    /**
     * Whether to perform preheating asynchronously.
     * <p>
     * 是否异步执行预热。
     * <p>
     * Asynchronous preheating doesn't block application startup.
     * <p>
     * 异步预热不会阻塞应用程序启动。
     *
     * @return {@code true} if preheating should be asynchronous, {@code false} otherwise
     *         <p>
     *         如果应异步预热，则返回 {@code true}；否则返回 {@code false}
     */
    boolean async() default true;
    
    /**
     * The SpEL expression that should be evaluated to determine whether
     * the preheating should be applied.
     * <p>
     * 应评估以确定是否应应用预热的SpEL表达式。
     * <p>
     * Default is "", meaning preheating is always applied.
     * <p>
     * 默认为""，表示始终应用预热。
     *
     * @return the SpEL expression for the condition
     *         <p>
     *         条件的SpEL表达式
     */
    String condition() default "";
    
    /**
     * The order in which preheating operations should be executed.
     * <p>
     * 预热操作应执行的顺序。
     * <p>
     * Lower values have higher priority (executed first).
     * <p>
     * 数值越小优先级越高（先执行）。
     *
     * @return the preheating order
     *         <p>
     *         预热顺序
     */
    int order() default 0;
}