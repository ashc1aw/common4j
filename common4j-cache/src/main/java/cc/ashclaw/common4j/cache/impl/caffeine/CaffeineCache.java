package cc.ashclaw.common4j.cache.impl.caffeine;

import cc.ashclaw.common4j.cache.config.CacheProperties;
import cc.ashclaw.common4j.cache.core.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

/**
 * Implementation of Cache interface using Caffeine library.
 * <p>
 * 使用Caffeine库实现的Cache接口。
 * <p>
 * This class provides a local in-memory cache implementation
 * using the Caffeine caching library with support for various
 * cache operations and configurations.
 * <p>
 * 此类使用Caffeine缓存库提供本地内存缓存实现，支持各种缓存操作和配置。
 *
 * @author b1itz7
 * @since 1.1.0
 */
public class CaffeineCache implements Cache {
    
    private final String name;
    private final com.github.benmanes.caffeine.cache.Cache<Object, Object> cache;
    private final CacheProperties cacheProperties;
    
    /**
     * Constructs a new CaffeineCache with the specified name, Caffeine builder, and cache properties.
     * <p>
     * 使用指定的名称、Caffeine构建器和缓存属性构造一个新的CaffeineCache。
     *
     * @param name the name of the cache
     *             <p>
     *             缓存的名称
     * @param caffeineBuilder the Caffeine builder instance
     *                        <p>
     *                        Caffeine构建器实例
     * @param cacheProperties the cache configuration properties
     *                        <p>
     *                        缓存配置属性
     */
    public CaffeineCache(String name, Caffeine<Object, Object> caffeineBuilder, CacheProperties cacheProperties) {
        this.name = name;
        this.cache = caffeineBuilder.build();
        this.cacheProperties = cacheProperties;
    }
    
    @Override
    public String getName() {
        return name;
    }
    
    @Override
    public Object getNativeCache() {
        return cache;
    }
    
    @Override
    public <T> T get(Object key, Class<T> type) {
        Object value = cache.getIfPresent(key);
        return type.cast(value);
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public <T> T get(Object key, Callable<T> valueLoader) {
        try {
            return (T) cache.get(key, k -> {
                try {
                    return valueLoader.call();
                } catch (Exception e) {
                    throw new RuntimeException("加载缓存值失败", e);
                }
            });
        } catch (Exception e) {
            throw new RuntimeException("获取缓存值失败", e);
        }
    }
    
    @Override
    public void put(Object key, Object value) {
        cache.put(key, value);
    }
    
    @Override
    public void put(Object key, Object value, long timeout, TimeUnit unit) {
        // Caffeine缓存构建时已经设置了过期策略，这里直接使用put
        cache.put(key, value);
    }
    
    @Override
    public boolean putIfAbsent(Object key, Object value) {
        Object existing = cache.getIfPresent(key);
        if (existing == null) {
            cache.put(key, value);
            return true;
        }
        return false;
    }
    
    @Override
    public boolean putIfAbsent(Object key, Object value, long timeout, TimeUnit unit) {
        return putIfAbsent(key, value);
    }
    
    @Override
    public void evict(Object key) {
        cache.invalidate(key);
    }
    
    @Override
    public void evict(Collection<?> keys) {
        cache.invalidateAll(keys);
    }
    
    @Override
    public void clear() {
        cache.invalidateAll();
    }
    
    @Override
    public <T> Map<Object, T> multiGet(Collection<?> keys, Class<T> type) {
        Map<Object, Object> values = cache.getAllPresent(keys);
        Map<Object, T> result = new java.util.HashMap<>();
        
        for (Map.Entry<Object, Object> entry : values.entrySet()) {
            result.put(entry.getKey(), type.cast(entry.getValue()));
        }
        
        return result;
    }
    
    @Override
    public void multiPut(Map<?, ?> map) {
        cache.putAll(map);
    }
    
    @Override
    public void multiPut(Map<?, ?> map, long timeout, TimeUnit unit) {
        multiPut(map);
    }
    
    @Override
    public Long getExpire(Object key) {
        return null;
    }
    
    @Override
    public Long getExpire(Object key, TimeUnit timeUnit) {
        return null;
    }
    
    @Override
    public boolean expire(Object key, long timeout, TimeUnit unit) {
        return false;
    }
    
    @Override
    public boolean hasKey(Object key) {
        return cache.getIfPresent(key) != null;
    }
    
    @Override
    public long size() {
        return cache.estimatedSize();
    }
}