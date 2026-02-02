package cc.ashclaw.common4j.cache.annotation;

import cc.ashclaw.common4j.cache.enums.CacheType;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * Annotation indicating that a method (or all methods on a class) triggers
 * a cache evict operation.
 * <p>
 * 指示方法（或类上的所有方法）触发缓存清除操作的注解。
 * <p>
 * This annotation is compatible with Spring Cache and provides additional
 * features such as multi-level cache eviction.
 * <p>
 * 此注解与Spring Cache兼容，并提供多级缓存清除等附加功能。
 *
 * @author b1itz7
 * @since 1.1.0
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface CacheEvict {
    
    /**
     * The names of the caches to use for the cache evict operation.
     * <p>
     * 用于缓存清除操作的缓存名称。
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
     * The names of the caches to use for the cache evict operation.
     * <p>
     * 用于缓存清除操作的缓存名称。
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
     * the cache eviction should be applied.
     * <p>
     * 应评估以确定是否应应用缓存清除的SpEL表达式。
     * <p>
     * Default is "", meaning cache eviction is always applied.
     * <p>
     * 默认为""，表示始终应用缓存清除。
     *
     * @return the SpEL expression for the condition
     *         <p>
     *         条件的SpEL表达式
     */
    String condition() default "";
    
    /**
     * Whether to evict all entries within the cache(s).
     * <p>
     * 是否清除缓存中的所有条目。
     * <p>
     * If set to true, the key parameter is ignored.
     * <p>
     * 如果设置为true，则忽略key参数。
     *
     * @return {@code true} if all entries should be evicted, {@code false} otherwise
     *         <p>
     *         如果应清除所有条目，则返回 {@code true}；否则返回 {@code false}
     */
    boolean allEntries() default false;
    
    /**
     * Whether the eviction should occur before the method is invoked.
     * <p>
     * 是否应在方法调用前进行清除。
     * <p>
     * Setting this to true causes the eviction to occur regardless of method outcome.
     * <p>
     * 将其设置为true会导致无论方法结果如何都进行清除。
     *
     * @return {@code true} if eviction should occur before invocation, {@code false} otherwise
     *         <p>
     *         如果应在调用前进行清除，则返回 {@code true}；否则返回 {@code false}
     */
    boolean beforeInvocation() default false;
    
    /**
     * The type of cache to use for this eviction operation.
     * <p>
     * 用于此清除操作的缓存类型。
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
}