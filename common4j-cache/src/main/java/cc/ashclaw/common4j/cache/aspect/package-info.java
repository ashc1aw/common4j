/**
 * Package containing cache-related aspect classes.
 * <p>
 * 包含缓存相关切面类的包。
 * <p>
 * This package provides aspect-oriented programming (AOP) support for
 * cache operations including:
 * - {@code CacheAspect}: Handles {@code @Cacheable}, {@code @CacheEvict}, and {@code @CachePut} annotations
 * - {@code CacheLockAspect}: Handles {@code @CacheLock} annotation for distributed locking
 * - {@code CachePreheatAspect}: Handles {@code @CachePreheat} annotation for cache preheating
 * <p>
 * 此包为缓存操作提供面向切面编程（AOP）支持，包括：
 * - {@code CacheAspect}：处理 {@code @Cacheable}、{@code @CacheEvict} 和 {@code @CachePut} 注解
 * - {@code CacheLockAspect}：处理用于分布式锁定的 {@code @CacheLock} 注解
 * - {@code CachePreheatAspect}：处理用于缓存预热的 {@code @CachePreheat} 注解
 *
 * @author b1itz7
 * @since 1.1.0
 */
package cc.ashclaw.common4j.cache.aspect;