package cc.ashclaw.common4j.cache.impl.caffeine;

import cc.ashclaw.common4j.cache.config.CacheProperties;
import cc.ashclaw.common4j.cache.core.Cache;
import cc.ashclaw.common4j.cache.core.CacheManager;
import cc.ashclaw.common4j.cache.enums.CacheType;
import com.github.benmanes.caffeine.cache.Caffeine;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Implementation of CacheManager interface for Caffeine caches.
 * <p>
 * 用于Caffeine缓存的CacheManager接口实现。
 * <p>
 * This class manages Caffeine cache instances and provides
 * methods for creating, retrieving, and managing local cache instances.
 * <p>
 * 此类管理Caffeine缓存实例，并提供用于创建、检索和管理本地缓存实例的方法。
 *
 * @author b1itz7
 * @since 1.1.0
 */
public class CaffeineCacheManager implements CacheManager {
    
    private final Caffeine<Object, Object> caffeineBuilder;
    private final CacheProperties cacheProperties;
    private final Map<String, Cache> cacheMap = new ConcurrentHashMap<>();
    
    /**
     * Constructs a new CaffeineCacheManager with the specified cache properties.
     * <p>
     * 使用指定的缓存属性构造一个新的CaffeineCacheManager。
     *
     * @param cacheProperties the cache configuration properties
     *                        <p>
     *                        缓存配置属性
     */
    public CaffeineCacheManager(CacheProperties cacheProperties) {
        this(Caffeine.newBuilder(), cacheProperties);
    }
    
    /**
     * Constructs a new CaffeineCacheManager with the specified Caffeine builder and cache properties.
     * <p>
     * 使用指定的Caffeine构建器和缓存属性构造一个新的CaffeineCacheManager。
     *
     * @param caffeineBuilder the Caffeine builder instance
     *                        <p>
     *                        Caffeine构建器实例
     * @param cacheProperties the cache configuration properties
     *                        <p>
     *                        缓存配置属性
     */
    public CaffeineCacheManager(Caffeine<Object, Object> caffeineBuilder, CacheProperties cacheProperties) {
        this.caffeineBuilder = caffeineBuilder;
        this.cacheProperties = cacheProperties;
    }
    
    @Override
    public Cache getCache(String name) {
        return cacheMap.computeIfAbsent(name, 
            key -> new CaffeineCache(name, caffeineBuilder, cacheProperties));
    }
    
    @Override
    public CacheType getCacheType() {
        return CacheType.LOCAL;
    }
    
    @Override
    public Collection<String> getCacheNames() {
        return cacheMap.keySet();
    }
    
    @Override
    public Cache createCache(String name) {
        CaffeineCache cache = new CaffeineCache(name, caffeineBuilder, cacheProperties);
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