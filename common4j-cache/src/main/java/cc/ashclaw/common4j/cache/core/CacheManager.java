package cc.ashclaw.common4j.cache.core;

import java.util.Collection;

import cc.ashclaw.common4j.cache.enums.CacheType;

/**
 * Interface for managing cache instances.
 * <p>
 * 缓存管理器接口，用于管理缓存实例。
 * <p>
 * This interface provides methods for creating, retrieving, and managing
 * cache instances with different names and configurations.
 * <p>
 * 该接口提供用于创建、检索和管理具有不同名称和配置的缓存实例的方法。
 *
 * @author b1itz7
 * @since 1.1.0
 */
public interface CacheManager {
    
    /**
     * Gets the cache instance associated with the specified name.
     * <p>
     * 获取与指定名称关联的缓存实例。
     *
     * @param name the name of the cache to retrieve
     *             <p>
     *             要检索的缓存名称
     * @return the cache instance, or {@code null} if no cache exists with the specified name
     *         <p>
     *         缓存实例，如果不存在具有指定名称的缓存，则返回 {@code null}
     */
    Cache getCache(String name);
    
    /**
     * Gets the type of cache managed by this cache manager.
     * <p>
     * 获取此缓存管理器管理的缓存类型。
     *
     * @return the cache type
     *         <p>
     *         缓存类型
     */
    CacheType getCacheType();
    
    /**
     * Gets the collection of cache names managed by this cache manager.
     * <p>
     * 获取此缓存管理器管理的缓存名称集合。
     *
     * @return the collection of cache names
     *         <p>
     *         缓存名称集合
     */
    Collection<String> getCacheNames();
    
    /**
     * Creates a new cache instance with the specified name.
     * <p>
     * 使用指定名称创建一个新的缓存实例。
     *
     * @param name the name of the cache to create
     *             <p>
     *             要创建的缓存名称
     * @return the newly created cache instance
     *         <p>
     *         新创建的缓存实例
     */
    Cache createCache(String name);
    
    /**
     * Destroys the cache instance associated with the specified name.
     * <p>
     * 销毁与指定名称关联的缓存实例。
     *
     * @param name the name of the cache to destroy
     *             <p>
     *             要销毁的缓存名称
     */
    void destroyCache(String name);
}