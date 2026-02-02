package cc.ashclaw.common4j.cache.core;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * Cache interface definition for common cache operations.
 * <p>
 * 缓存接口定义，包含通用的缓存操作。
 * <p>
 * This interface provides a unified API for different cache implementations,
 * including local cache, distributed cache, and multi-level cache.
 * <p>
 * 该接口为不同的缓存实现提供统一的API，包括本地缓存、分布式缓存和多级缓存。
 *
 * @author b1itz7
 * @since 1.1.0
 */
public interface Cache {
    
    /**
     * Gets the name of this cache.
     * <p>
     * 获取此缓存的名称。
     *
     * @return the cache name
     *         <p>
     *         缓存名称
     */
    String getName();
    
    /**
     * Gets the underlying native cache instance.
     * <p>
     * 获取底层的原生缓存实例。
     *
     * @return the native cache instance
     *         <p>
     *         原生缓存实例
     */
    Object getNativeCache();
    
    /**
     * Gets the value from the cache for the specified key.
     * <p>
     * 从缓存中获取指定键的值。
     *
     * @param key the key whose associated value is to be returned
     *            <p>
     *            要返回其关联值的键
     * @param type the type of the value to be returned
     *             <p>
     *             要返回的值的类型
     * @param <T> the type of the value
     *            <p>
     *            值的类型
     * @return the value to which the specified key is mapped, or {@code null}
     *         if the cache contains no mapping for the key
     *         <p>
     *         指定键映射的值，如果缓存不包含该键的映射，则返回 {@code null}
     */
    <T> T get(Object key, Class<T> type);
    
    /**
     * Gets the value from the cache for the specified key, loading it via
     * the {@code valueLoader} if no value is present.
     * <p>
     * 从缓存中获取指定键的值，如果不存在则通过 {@code valueLoader} 加载。
     *
     * @param key the key whose associated value is to be returned
     *            <p>
     *            要返回其关联值的键
     * @param valueLoader the {@link Callable} to use for loading the value
     *                    <p>
     *                    用于加载值的 {@link Callable}
     * @param <T> the type of the value
     *            <p>
     *            值的类型
     * @return the value to which the specified key is mapped
     *         <p>
     *         指定键映射的值
     */
    <T> T get(Object key, Callable<T> valueLoader);
    
    /**
     * Associates the specified value with the specified key in the cache.
     * <p>
     * 将指定的值与指定的键关联到缓存中。
     *
     * @param key the key with which the specified value is to be associated
     *            <p>
     *            要与指定值关联的键
     * @param value the value to be associated with the specified key
     *              <p>
     *              要与指定键关联的值
     */
    void put(Object key, Object value);
    
    /**
     * Associates the specified value with the specified key in the cache,
     * with the specified expiration timeout.
     * <p>
     * 将指定的值与指定的键关联到缓存中，并指定过期超时时间。
     *
     * @param key the key with which the specified value is to be associated
     *            <p>
     *            要与指定值关联的键
     * @param value the value to be associated with the specified key
     *              <p>
     *              要与指定键关联的值
     * @param timeout the expiration timeout
     *                <p>
     *                过期超时时间
     * @param unit the time unit of the timeout
     *             <p>
     *             超时时间单位
     */
    void put(Object key, Object value, long timeout, TimeUnit unit);
    
    /**
     * Associates the specified value with the specified key in the cache
     * if the specified key is not already associated with a value.
     * <p>
     * 如果指定的键尚未与值关联，则将指定的值与指定的键关联到缓存中。
     *
     * @param key the key with which the specified value is to be associated
     *            <p>
     *            要与指定值关联的键
     * @param value the value to be associated with the specified key
     *              <p>
     *              要与指定键关联的值
     * @return {@code true} if the value was set, {@code false} if the key
     *         was already present in the cache
     *         <p>
     *         如果设置了值，则返回 {@code true}；如果键已存在于缓存中，则返回 {@code false}
     */
    boolean putIfAbsent(Object key, Object value);
    
    /**
     * Associates the specified value with the specified key in the cache
     * if the specified key is not already associated with a value,
     * with the specified expiration timeout.
     * <p>
     * 如果指定的键尚未与值关联，则将指定的值与指定的键关联到缓存中，并指定过期超时时间。
     *
     * @param key the key with which the specified value is to be associated
     *            <p>
     *            要与指定值关联的键
     * @param value the value to be associated with the specified key
     *              <p>
     *              要与指定键关联的值
     * @param timeout the expiration timeout
     *                <p>
     *                过期超时时间
     * @param unit the time unit of the timeout
     *             <p>
     *             超时时间单位
     * @return {@code true} if the value was set, {@code false} if the key
     *         was already present in the cache
     *         <p>
     *         如果设置了值，则返回 {@code true}；如果键已存在于缓存中，则返回 {@code false}
     */
    boolean putIfAbsent(Object key, Object value, long timeout, TimeUnit unit);
    
    /**
     * Evicts the mapping for a key from the cache if it is present.
     * <p>
     * 从缓存中移除键的映射（如果存在）。
     *
     * @param key the key whose mapping is to be removed from the cache
     *            <p>
     *            要从缓存中移除其映射的键
     */
    void evict(Object key);
    
    /**
     * Evicts the mappings for the specified keys from the cache.
     * <p>
     * 从缓存中移除指定键的映射。
     *
     * @param keys the keys whose mappings are to be removed from the cache
     *             <p>
     *             要从缓存中移除其映射的键
     */
    void evict(Collection<?> keys);
    
    /**
     * Removes all mappings from the cache.
     * <p>
     * 从缓存中移除所有映射。
     */
    void clear();
    
    /**
     * Gets multiple values from the cache for the specified keys.
     * <p>
     * 从缓存中获取指定键的多个值。
     *
     * @param keys the keys whose associated values are to be returned
     *             <p>
     *             要返回其关联值的键
     * @param type the type of the values to be returned
     *             <p>
     *             要返回的值的类型
     * @param <T> the type of the values
     *            <p>
     *            值的类型
     * @return a map containing the key-value pairs for the specified keys
     *         <p>
     *         包含指定键的键值对的映射
     */
    <T> Map<Object, T> multiGet(Collection<?> keys, Class<T> type);
    
    /**
     * Associates the specified key-value pairs in the cache.
     * <p>
     * 将指定的键值对关联到缓存中。
     *
     * @param map the key-value pairs to be associated in the cache
     *            <p>
     *            要关联到缓存中的键值对
     */
    void multiPut(Map<?, ?> map);
    
    /**
     * Associates the specified key-value pairs in the cache,
     * with the specified expiration timeout.
     * <p>
     * 将指定的键值对关联到缓存中，并指定过期超时时间。
     *
     * @param map the key-value pairs to be associated in the cache
     *            <p>
     *            要关联到缓存中的键值对
     * @param timeout the expiration timeout
     *                <p>
     *                过期超时时间
     * @param unit the time unit of the timeout
     *             <p>
     *             超时时间单位
     */
    void multiPut(Map<?, ?> map, long timeout, TimeUnit unit);
    
    /**
     * Gets the expiration time for the specified key.
     * <p>
     * 获取指定键的过期时间。
     *
     * @param key the key whose expiration time is to be returned
     *            <p>
     *            要返回其过期时间的键
     * @return the expiration time in seconds, or {@code null} if the key
     *         does not exist or has no expiration
     *         <p>
     *         以秒为单位的过期时间，如果键不存在或没有过期时间，则返回 {@code null}
     */
    Long getExpire(Object key);
    
    /**
     * Gets the expiration time for the specified key in the specified time unit.
     * <p>
     * 获取指定键的过期时间（以指定的时间单位）。
     *
     * @param key the key whose expiration time is to be returned
     *            <p>
     *            要返回其过期时间的键
     * @param timeUnit the time unit of the returned expiration time
     *                 <p>
     *                 返回的过期时间的时间单位
     * @return the expiration time in the specified time unit, or {@code null}
     *         if the key does not exist or has no expiration
     *         <p>
     *         以指定时间单位为单位的过期时间，如果键不存在或没有过期时间，则返回 {@code null}
     */
    Long getExpire(Object key, TimeUnit timeUnit);
    
    /**
     * Sets the expiration time for the specified key.
     * <p>
     * 设置指定键的过期时间。
     *
     * @param key the key whose expiration time is to be set
     *            <p>
     *            要设置其过期时间的键
     * @param timeout the expiration timeout
     *                <p>
     *                过期超时时间
     * @param unit the time unit of the timeout
     *             <p>
     *             超时时间单位
     * @return {@code true} if the expiration time was set successfully,
     *         {@code false} otherwise
     *         <p>
     *         如果成功设置了过期时间，则返回 {@code true}；否则返回 {@code false}
     */
    boolean expire(Object key, long timeout, TimeUnit unit);
    
    /**
     * Checks if the cache contains a mapping for the specified key.
     * <p>
     * 检查缓存是否包含指定键的映射。
     *
     * @param key the key whose presence in the cache is to be tested
     *            <p>
     *            要测试其在缓存中是否存在的键
     * @return {@code true} if the cache contains a mapping for the specified key
     *         <p>
     *         如果缓存包含指定键的映射，则返回 {@code true}
     */
    boolean hasKey(Object key);
    
    /**
     * Gets the number of key-value mappings in the cache.
     * <p>
     * 获取缓存中键值映射的数量。
     *
     * @return the number of key-value mappings in the cache
     *         <p>
     *         缓存中键值映射的数量
     */
    long size();
}