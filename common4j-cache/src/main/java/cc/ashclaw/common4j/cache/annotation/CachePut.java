package cc.ashclaw.common4j.cache.annotation;

import cc.ashclaw.common4j.cache.enums.CacheType;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * Annotation indicating that a method (or all methods on a class) triggers
 * a cache put operation.
 * <p>
 * 指示方法（或类上的所有方法）触发缓存更新操作的注解。
 * <p>
 * This annotation is compatible with Spring Cache and provides additional
 * features such as multi-level caching and expiration time configuration.
 * <p>
 * 此注解与Spring Cache兼容，并提供多级缓存和过期时间配置等附加功能。
 *
 * @author b1itz7
 * @since 1.1.0
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface CachePut {
    
    /**
     * The names of the caches to use for the cache put operation.
     * <p>
     * 用于缓存更新操作的缓存名称。
     * <p>
     * This is an alias for {@link #cacheNames()}.
     * <p>
     * 这是 {@link #cacheNames()} 的别名。
     *
     * @return the names of the caches
     *         <p>
     *         缓存名称
     */
    @AliasFor("cacheNames")
    String[] value() default {};
    
    /**
     * The names of the caches to use for the cache put operation.
     * <p>
     * 用于缓存更新操作的缓存名称。
     * <p>
     * This is an alias for {@link #value()}.
     * <p>
     * 这是 {@link #value()} 的别名。
     *
     * @return the names of the caches
     *         <p>
     *         缓存名称
     */
    @AliasFor("value")
    String[] cacheNames() default {};
    
    /**
     * The SpEL expression for computing the key dynamically.
     * <p>
     * 用于动态计算键的SpEL表达式。
     * <p>
     * Default is "", meaning all method parameters are considered as a key.
     * <p>
     * 默认为""，表示所有方法参数都被视为一个键。
     *
     * @return the SpEL expression for the key
     *         <p>
     *         键的SpEL表达式
     */
    String key() default "";
    
    /**
     * The bean name of the custom key generator to use.
     * <p>
     * 要使用的自定义键生成器的bean名称。
     * <p>
     * Mutually exclusive with the key attribute.
     * <p>
     * 与key属性互斥。
     *
     * @return the bean name of the key generator
     *         <p>
     *         键生成器的bean名称
     */
    String keyGenerator() default "";
    
    /**
     * The bean name of the custom cache manager to use.
     * <p>
     * 要使用的自定义缓存管理器的bean名称。
     *
     * @return the bean name of the cache manager
     *         <p>
     *         缓存管理器的bean名称
     */
    String cacheManager() default "";
    
    /**
     * The bean name of the custom cache resolver to use.
     * <p>
     * 要使用的自定义缓存解析器的bean名称。
     *
     * @return the bean name of the cache resolver
     *         <p>
     *         缓存解析器的bean名称
     */
    String cacheResolver() default "";
    
    /**
     * The SpEL expression that should be evaluated to determine whether
     * the cache put operation should be applied.
     * <p>
     * 应评估以确定是否应应用缓存更新操作的SpEL表达式。
     * <p>
     * Default is "", meaning the cache put operation is always applied.
     * <p>
     * 默认为""，表示始终应用缓存更新操作。
     *
     * @return the SpEL expression for the condition
     *         <p>
     *         条件的SpEL表达式
     */
    String condition() default "";
    
    /**
     * The SpEL expression used to veto caching based on the result of the method invocation.
     * <p>
     * 用于根据方法调用的结果否决缓存的SpEL表达式。
     * <p>
     * Unlike {@link #condition()}, this expression is evaluated after the method has been called.
     * <p>
     * 与 {@link #condition()} 不同，此表达式在方法调用后进行评估。
     *
     * @return the SpEL expression for the unless condition
     *         <p>
     *         unless条件的SpEL表达式
     */
    String unless() default "";
    
    /**
     * The type of cache to use for this put operation.
     * <p>
     * 用于此更新操作的缓存类型。
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
     * The expiration time for the cached value.
     * <p>
     * 缓存值的过期时间。
     * <p>
     * Default is -1, meaning no explicit expiration time is set.
     * <p>
     * 默认为-1，表示未设置显式过期时间。
     *
     * @return the expiration time
     *         <p>
     *         过期时间
     */
    long expire() default -1;
    
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
}