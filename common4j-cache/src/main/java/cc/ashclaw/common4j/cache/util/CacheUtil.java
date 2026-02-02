package cc.ashclaw.common4j.cache.util;

import cc.ashclaw.common4j.cache.core.Cache;
import cc.ashclaw.common4j.cache.core.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * Cache utility class providing static methods for cache operations.
 * <p>
 * 缓存工具类，提供用于缓存操作的静态方法。
 * <p>
 * This class provides convenient static methods for common cache operations
 * including get, put, evict, and clear operations with simplified API.
 * <p>
 * 此类为常见的缓存操作提供方便的静态方法，包括get、put、evict和clear操作，具有简化的API。
 *
 * @author b1itz7
 * @since 1.1.0
 */
@Component
public class CacheUtil {
    
    private static CacheManager cacheManager;
    
    @Autowired
    public void setCacheManager(CacheManager cacheManager) {
        CacheUtil.cacheManager = cacheManager;
    }
    
    /**
     * Gets the cache instance associated with the specified name.
     * <p>
     * 获取与指定名称关联的缓存实例。
     *
     * @param name the name of the cache to retrieve
     *             <p>
     *             要检索的缓存名称
     * @return the cache instance
     *         <p>
     *         缓存实例
     */
    public static Cache getCache(String name) {
        return cacheManager.getCache(name);
    }
    
    /**
     * Gets the value associated with the specified key from the cache.
     * <p>
     * 从缓存中获取与指定键关联的值。
     *
     * @param cacheName the name of the cache
     *                  <p>
     *                  缓存名称
     * @param key the key whose associated value is to be returned
     *            <p>
     *            要返回其关联值的键
     * @param type the type of the value to be returned
     *             <p>
     *            要返回的值的类型
     * @param <T> the type of the value
     *            <p>
     *            值的类型
     * @return the value to which the specified key is mapped, or {@code null} if no value is found
     *         <p>
     *         指定键映射的值，如果未找到值，则返回 {@code null}
     */
    public static <T> T get(String cacheName, Object key, Class<T> type) {
        Cache cache = getCache(cacheName);
        return cache.get(key, type);
    }
    
    /**
     * Gets the value associated with the specified key from the cache,
     * loading it via the specified valueLoader if no value is found.
     * <p>
     * 从缓存中获取与指定键关联的值，如果未找到值，则通过指定的valueLoader加载。
     *
     * @param cacheName the name of the cache
     *                  <p>
     *                  缓存名称
     * @param key the key whose associated value is to be returned
     *            <p>
     *            要返回其关联值的键
     * @param valueLoader the value loader to use if no value is found
     *                    <p>
     *                    如果未找到值，则使用的值加载器
     * @param <T> the type of the value
     *            <p>
     *            值的类型
     * @return the value to which the specified key is mapped
     *         <p>
     *         指定键映射的值
     */
    public static <T> T get(String cacheName, Object key, Callable<T> valueLoader) {
        Cache cache = getCache(cacheName);
        return cache.get(key, valueLoader);
    }
    
    /**
     * Associates the specified value with the specified key in the cache.
     * <p>
     * 将指定的值与指定的键关联在缓存中。
     *
     * @param cacheName the name of the cache
     *                  <p>
     *                  缓存名称
     * @param key the key with which the specified value is to be associated
     *            <p>
     *            要与指定值关联的键
     * @param value the value to be associated with the specified key
     *              <p>
     *              要与指定键关联的值
     */
    public static void put(String cacheName, Object key, Object value) {
        Cache cache = getCache(cacheName);
        cache.put(key, value);
    }
    
    /**
     * Associates the specified value with the specified key in the cache with a timeout.
     * <p>
     * 将指定的值与指定的键关联在缓存中，并指定过期时间。
     *
     * @param cacheName the name of the cache
     *                  <p>
     *                  缓存名称
     * @param key the key with which the specified value is to be associated
     *            <p>
     *            要与指定值关联的键
     * @param value the value to be associated with the specified key
     *              <p>
     *              要与指定键关联的值
     * @param timeout the timeout duration
     *                <p>
     *                超时持续时间
     * @param unit the time unit of the timeout
     *             <p>
     *             超时的时间单位
     */
    public static void put(String cacheName, Object key, Object value, long timeout, TimeUnit unit) {
        Cache cache = getCache(cacheName);
        cache.put(key, value, timeout, unit);
    }
    
    /**
     * Removes the mapping for the specified key from the cache.
     * <p>
     * 从缓存中移除指定键的映射。
     *
     * @param cacheName the name of the cache
     *                  <p>
     *                  缓存名称
     * @param key the key whose mapping is to be removed
     *            <p>
     *            要移除其映射的键
     */
    public static void evict(String cacheName, Object key) {
        Cache cache = getCache(cacheName);
        cache.evict(key);
    }
    
    /**
     * Removes all mappings from the cache.
     * <p>
     * 从缓存中移除所有映射。
     *
     * @param cacheName the name of the cache
     *                  <p>
     *                  缓存名称
     */
    public static void clear(String cacheName) {
        Cache cache = getCache(cacheName);
        cache.clear();
    }
    
    /**
     * Returns {@code true} if the cache contains a mapping for the specified key.
     * <p>
     * 如果缓存包含指定键的映射，则返回 {@code true}。
     *
     * @param cacheName the name of the cache
     *                  <p>
     *                  缓存名称
     * @param key the key whose presence in the cache is to be tested
     *            <p>
     *            要测试其在缓存中是否存在的键
     * @return {@code true} if the cache contains a mapping for the specified key
     *         <p>
     *         如果缓存包含指定键的映射，则返回 {@code true}
     */
    public static boolean hasKey(String cacheName, Object key) {
        Cache cache = getCache(cacheName);
        return cache.hasKey(key);
    }
    
    /**
     * Returns the number of key-value mappings in the cache.
     * <p>
     * 返回缓存中键值映射的数量。
     *
     * @param cacheName the name of the cache
     *                  <p>
     *                  缓存名称
     * @return the number of key-value mappings in the cache
     *         <p>
     *         缓存中键值映射的数量
     */
    public static long size(String cacheName) {
        Cache cache = getCache(cacheName);
        return cache.size();
    }
}