package cc.ashclaw.common4j.cache.impl.redisson;

import cc.ashclaw.common4j.cache.config.CacheProperties;
import cc.ashclaw.common4j.cache.core.Cache;
import cc.ashclaw.common4j.cache.support.CacheSerializer;
import org.redisson.api.RMapCache;
import org.redisson.api.RedissonClient;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * Implementation of Cache interface using Redisson library.
 * <p>
 * 使用Redisson库实现的Cache接口。
 * <p>
 * This class provides a distributed cache implementation
 * using the Redisson library with Redis backend for distributed
 * caching operations and data consistency.
 * <p>
 * 此类使用Redisson库和Redis后端提供分布式缓存实现，用于分布式缓存操作和数据一致性。
 *
 * @author b1itz7
 * @since 1.1.0
 */
public class RedissonCache implements Cache {
    
    private final String name;
    private final RMapCache<Object, Object> cache;
    private final CacheSerializer serializer;
    private final CacheProperties cacheProperties;
    
    /**
     * Constructs a new RedissonCache with the specified name, Redisson client, serializer, and cache properties.
     * <p>
     * 使用指定的名称、Redisson客户端、序列化器和缓存属性构造一个新的RedissonCache。
     *
     * @param name the name of the cache
     *             <p>
     *             缓存的名称
     * @param redissonClient the Redisson client instance
     *                       <p>
     *                       Redisson客户端实例
     * @param serializer the cache serializer for object serialization
     *                   <p>
     *                   用于对象序列化的缓存序列化器
     * @param cacheProperties the cache configuration properties
     *                        <p>
     *                        缓存配置属性
     */
    public RedissonCache(String name, RedissonClient redissonClient, CacheSerializer serializer, CacheProperties cacheProperties) {
        this.name = name;
        this.cache = redissonClient.getMapCache(name);
        this.serializer = serializer;
        this.cacheProperties = cacheProperties;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return name;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Object getNativeCache() {
        return cache;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public <T> T get(Object key, Class<T> type) {
        Object value = cache.get(key);
        if (value == null) {
            return null;
        }
        
        if (value instanceof String) {
            return serializer.deserialize((String) value, type);
        }
        
        return type.cast(value);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public <T> T get(Object key, Callable<T> valueLoader) {
        T value = get(key, (Class<T>) Object.class);
        if (value != null) {
            return value;
        }
        
        try {
            value = valueLoader.call();
            if (value != null) {
                put(key, value);
            }
            return value;
        } catch (Exception e) {
            throw new RuntimeException("加载缓存值失败", e);
        }
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void put(Object key, Object value) {
        put(key, value, cacheProperties.getExpireTime().toSeconds(), TimeUnit.SECONDS);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void put(Object key, Object value, long timeout, TimeUnit unit) {
        Object cacheValue = value;
        if (!(value instanceof String)) {
            cacheValue = serializer.serialize(value);
        }
        
        if (timeout > 0) {
            cache.fastPut(key, cacheValue, timeout, unit);
        } else {
            cache.fastPut(key, cacheValue);
        }
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean putIfAbsent(Object key, Object value) {
        return putIfAbsent(key, value, cacheProperties.getExpireTime().toSeconds(), TimeUnit.SECONDS);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean putIfAbsent(Object key, Object value, long timeout, TimeUnit unit) {
        Object cacheValue = value;
        if (!(value instanceof String)) {
            cacheValue = serializer.serialize(value);
        }
        
        if (timeout > 0) {
            return cache.fastPutIfAbsent(key, cacheValue, timeout, unit);
        } else {
            return cache.fastPutIfAbsent(key, cacheValue);
        }
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void evict(Object key) {
        cache.fastRemove(key);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void evict(Collection<?> keys) {
        cache.fastRemove(keys.toArray());
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void clear() {
        cache.clear();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public <T> Map<Object, T> multiGet(Collection<?> keys, Class<T> type) {
        Set<Object> keySet = new java.util.HashSet<>(keys);
        Map<Object, Object> values = cache.getAll(keySet);
        Map<Object, T> result = new java.util.HashMap<>();
        
        for (Map.Entry<Object, Object> entry : values.entrySet()) {
            Object value = entry.getValue();
            if (value instanceof String) {
                result.put(entry.getKey(), serializer.deserialize((String) value, type));
            } else {
                result.put(entry.getKey(), type.cast(value));
            }
        }
        
        return result;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void multiPut(Map<?, ?> map) {
        multiPut(map, cacheProperties.getExpireTime().toSeconds(), TimeUnit.SECONDS);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void multiPut(Map<?, ?> map, long timeout, TimeUnit unit) {
        Map<Object, Object> cacheMap = new java.util.HashMap<>();
        
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            Object value = entry.getValue();
            if (!(value instanceof String)) {
                cacheMap.put(entry.getKey(), serializer.serialize(value));
            } else {
                cacheMap.put(entry.getKey(), value);
            }
        }
        
        if (timeout > 0) {
            cache.putAll(cacheMap, timeout, unit);
        } else {
            cache.putAll(cacheMap);
        }
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Long getExpire(Object key) {
        return getExpire(key, TimeUnit.SECONDS);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Long getExpire(Object key, TimeUnit timeUnit) {
        long ttl = cache.remainTimeToLive(key);
        if (ttl == -1 || ttl == -2) {
            return null;
        }
        return timeUnit.convert(ttl, TimeUnit.MILLISECONDS);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean expire(Object key, long timeout, TimeUnit unit) {
        return cache.expire(java.time.Duration.of(timeout, unit.toChronoUnit()));
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasKey(Object key) {
        return cache.containsKey(key);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public long size() {
        return cache.size();
    }
}