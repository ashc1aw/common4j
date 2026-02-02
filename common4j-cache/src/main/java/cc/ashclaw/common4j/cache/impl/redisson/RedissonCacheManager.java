package cc.ashclaw.common4j.cache.impl.redisson;

import cc.ashclaw.common4j.cache.config.CacheProperties;
import cc.ashclaw.common4j.cache.core.Cache;
import cc.ashclaw.common4j.cache.core.CacheManager;
import cc.ashclaw.common4j.cache.enums.CacheType;
import cc.ashclaw.common4j.cache.support.CacheSerializer;
import org.redisson.api.RedissonClient;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Implementation of CacheManager interface for Redisson caches.
 * <p>
 * 用于Redisson缓存的CacheManager接口实现。
 * <p>
 * This class manages Redisson cache instances and provides
 * methods for creating, retrieving, and managing distributed cache instances
 * using Redis backend.
 * <p>
 * 此类管理Redisson缓存实例，并提供使用Redis后端创建、检索和管理分布式缓存实例的方法。
 *
 * @author b1itz7
 * @since 1.1.0
 */
public class RedissonCacheManager implements CacheManager {
    
    private final RedissonClient redissonClient;
    private final CacheSerializer serializer;
    private final CacheProperties cacheProperties;
    private final Map<String, Cache> cacheMap = new ConcurrentHashMap<>();
    
    /**
     * Constructs a new RedissonCacheManager with the specified Redisson client and cache properties.
     * <p>
     * 使用指定的Redisson客户端和缓存属性构造一个新的RedissonCacheManager。
     *
     * @param redissonClient the Redisson client instance
     *                       <p>
     *                       Redisson客户端实例
     * @param cacheProperties the cache configuration properties
     *                        <p>
     *                        缓存配置属性
     */
    public RedissonCacheManager(RedissonClient redissonClient, CacheProperties cacheProperties) {
        this.redissonClient = redissonClient;
        this.serializer = new CacheSerializer();
        this.cacheProperties = cacheProperties;
    }
    
    /**
     * Constructs a new RedissonCacheManager with the specified Redisson client, serializer, and cache properties.
     * <p>
     * 使用指定的Redisson客户端、序列化器和缓存属性构造一个新的RedissonCacheManager。
     *
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
    public RedissonCacheManager(RedissonClient redissonClient, CacheSerializer serializer, CacheProperties cacheProperties) {
        this.redissonClient = redissonClient;
        this.serializer = serializer;
        this.cacheProperties = cacheProperties;
    }
    
    @Override
    public Cache getCache(String name) {
        return cacheMap.computeIfAbsent(name, 
            key -> new RedissonCache(name, redissonClient, serializer, cacheProperties));
    }
    
    @Override
    public CacheType getCacheType() {
        return CacheType.DISTRIBUTED;
    }
    
    @Override
    public Collection<String> getCacheNames() {
        return cacheMap.keySet();
    }
    
    @Override
    public Cache createCache(String name) {
        RedissonCache cache = new RedissonCache(name, redissonClient, serializer, cacheProperties);
        cacheMap.put(name, cache);
        return cache;
    }
    
    @Override
    public void destroyCache(String name) {
        Cache cache = cacheMap.remove(name);
        if (cache != null) {
            cache.clear();
        }
    }
}