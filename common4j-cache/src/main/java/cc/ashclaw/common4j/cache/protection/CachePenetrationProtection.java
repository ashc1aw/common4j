package cc.ashclaw.common4j.cache.protection;

import java.util.concurrent.TimeUnit;

import cc.ashclaw.common4j.cache.config.CacheProperties;
import cc.ashclaw.common4j.cache.core.Cache;

/**
 * Cache penetration protection implementation.
 * <p>
 * 缓存穿透保护实现。
 * <p>
 * This class provides protection against cache penetration by caching
 * null values for non-existent keys to prevent repeated database queries
 * for invalid data.
 * <p>
 * 此类通过为不存在的键缓存空值来提供缓存穿透保护，防止对无效数据的重复数据库查询。
 *
 * @author b1itz7
 * @since 1.1.0
 */
public class CachePenetrationProtection {
    
    private final Cache cache;
    private final CacheProperties cacheProperties;
    
    public CachePenetrationProtection(Cache cache, CacheProperties cacheProperties) {
        this.cache = cache;
        this.cacheProperties = cacheProperties;
    }
    
    /**
     * Gets a cache value with penetration protection by caching null values.
     * <p>
     * 通过缓存空值来获取缓存值，提供穿透保护。
     *
     * @param key the cache key
     *            <p>
     *            缓存键
     * @param type the type of the cached value
     *             <p>
     *             缓存值的类型
     * @param <T> the type of the value
     *            <p>
     *            值的类型
     * @return the cached value, or null if the key doesn't exist
     *         <p>
     *         缓存的值，如果键不存在则返回null
     */
    public <T> T getWithProtection(Object key, Class<T> type) {
        T value = cache.get(key, type);
        
        if (value == null) {
            // 设置空值缓存，防止缓存穿透
            cache.put(key, createNullValue(), 
                cacheProperties.getPenetrationExpireTime().toSeconds(), 
                TimeUnit.SECONDS);
        }
        
        return value;
    }
    
    /**
     * Checks if the value is a null value marker.
     * <p>
     * 检查值是否为空值标记。
     *
     * @param value the value to check
     *              <p>
     *              要检查的值
     * @return {@code true} if the value is a null value marker, {@code false} otherwise
     *         <p>
     *         如果值是空值标记，则返回 {@code true}；否则返回 {@code false}
     */
    public boolean isNullValue(Object value) {
        return value instanceof NullValue;
    }
    
    /**
     * Creates a null value marker object.
     * <p>
     * 创建空值标记对象。
     *
     * @return the null value marker object
     *         <p>
     *         空值标记对象
     */
    private Object createNullValue() {
        return new NullValue();
    }
    
    /**
     * Null value marker class for cache penetration protection.
     * <p>
     * 用于缓存穿透保护的空值标记类。
     */
    private static class NullValue {
        @Override
        public String toString() {
            return "NullValue{}";
        }
    }
}