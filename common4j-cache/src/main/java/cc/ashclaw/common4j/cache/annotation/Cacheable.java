package cc.ashclaw.common4j.cache.annotation;

import cc.ashclaw.common4j.cache.enums.CacheType;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * Annotation indicating that a method (or all methods on a class) is cacheable.
 * <p>
 * 指示方法（或类上的所有方法）可缓存的注解。
 * <p>
 * This annotation is compatible with Spring Cache and provides additional
 * features such as multi-level caching and cache protection mechanisms.
 * <p>
 * 此注解与Spring Cache兼容，并提供多级缓存和缓存保护机制等附加功能。
 *
 * @author b1itz7
 * @since 1.1.0
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Cacheable {

    /**
     * The names of the caches to use for the cacheable operation.
     * <p>
     * 用于可缓存操作的缓存名称。
     * <p>
     * This is an alias for {@link #cacheNames()}.
     * <p>
     * 这是 {@link #cacheNames()} 的别名。
     *
     * @return the names of the caches
     * <p>
     * 缓存名称
     */
    @AliasFor("cacheNames")
    String[] value() default {};

    /**
     * The names of the caches to use for the cacheable operation.
     * <p>
     * 用于可缓存操作的缓存名称。
     * <p>
     * This is an alias for {@link #value()}.
     * <p>
     * 这是 {@link #value()} 的别名。
     *
     * @return the names of the caches
     * <p>
     * 缓存名称
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
     * <p>
     * 键的SpEL表达式
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
     * <p>
     * 键生成器的bean名称
     */
    String keyGenerator() default "";

    /**
     * The bean name of the custom cache manager to use.
     * <p>
     * 要使用的自定义缓存管理器的bean名称。
     *
     * @return the bean name of the cache manager
     * <p>
     * 缓存管理器的bean名称
     */
    String cacheManager() default "";

    /**
     * The bean name of the custom cache resolver to use.
     * <p>
     * 要使用的自定义缓存解析器的bean名称。
     *
     * @return the bean name of the cache resolver
     * <p>
     * 缓存解析器的bean名称
     */
    String cacheResolver() default "";

    /**
     * The SpEL expression that should be evaluated to determine whether
     * the caching should be applied.
     * <p>
     * 应评估以确定是否应应用缓存的SpEL表达式。
     * <p>
     * Default is "", meaning caching is always applied.
     * <p>
     * 默认为""，表示始终应用缓存。
     *
     * @return the SpEL expression for the condition
     * <p>
     * 条件的SpEL表达式
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
     * <p>
     * unless条件的SpEL表达式
     */
    String unless() default "";

    /**
     * Whether to synchronize the invocation of the underlying method.
     * <p>
     * 是否同步调用底层方法。
     * <p>
     * This forces concurrent threads to wait while the value is being computed.
     * <p>
     * 这强制并发线程在计算值时等待。
     *
     * @return {@code true} if synchronization is enabled, {@code false} otherwise
     * <p>
     * 如果启用了同步，则返回 {@code true}；否则返回 {@code false}
     */
    boolean sync() default false;

    /**
     * The type of cache to use for this operation.
     * <p>
     * 用于此操作的缓存类型。
     * <p>
     * Default is multi-level caching (local + distributed).
     * <p>
     * 默认为多级缓存（本地 + 分布式）。
     *
     * @return the cache type
     * <p>
     * 缓存类型
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
     * <p>
     * 过期时间
     */
    long expire() default -1;

    /**
     * The time unit for the expiration time.
     * <p>
     * 过期时间的时间单位。
     *
     * @return the time unit
     * <p>
     * 时间单位
     */
    TimeUnit timeUnit() default TimeUnit.SECONDS;

    /**
     * Whether to enable cache penetration protection.
     * <p>
     * 是否启用缓存穿透保护。
     * <p>
     * Cache penetration protection prevents queries for non-existent data
     * from overwhelming the underlying data source.
     * <p>
     * 缓存穿透保护可防止对不存在数据的查询压垮底层数据源。
     *
     * @return {@code true} if penetration protection is enabled, {@code false} otherwise
     * <p>
     * 如果启用了穿透保护，则返回 {@code true}；否则返回 {@code false}
     */
    boolean penetrationProtection() default true;

    /**
     * Whether to enable cache breakdown protection.
     * <p>
     * 是否启用缓存击穿保护。
     * <p>
     * Cache breakdown protection prevents multiple concurrent requests for
     * the same key from overwhelming the underlying data source when the cache expires.
     * <p>
     * 缓存击穿保护可防止在缓存过期时对同一键的多个并发请求压垮底层数据源。
     *
     * @return {@code true} if breakdown protection is enabled, {@code false} otherwise
     * <p>
     * 如果启用了击穿保护，则返回 {@code true}；否则返回 {@code false}
     */
    boolean breakdownProtection() default true;

    /**
     * Whether to enable cache avalanche protection.
     * <p>
     * 是否启用缓存雪崩保护。
     * <p>
     * Cache avalanche protection prevents a large number of cache entries
     * from expiring at the same time, which could overwhelm the underlying data source.
     * <p>
     * 缓存雪崩保护可防止大量缓存条目同时过期，这可能会压垮底层数据源。
     *
     * @return {@code true} if avalanche protection is enabled, {@code false} otherwise
     * <p>
     * 如果启用了雪崩保护，则返回 {@code true}；否则返回 {@code false}
     */
    boolean avalancheProtection() default true;
}