package cc.ashclaw.common4j.cache.impl.multilevel;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import cc.ashclaw.common4j.cache.config.CacheProperties;
import cc.ashclaw.common4j.cache.core.Cache;
import cc.ashclaw.common4j.cache.core.MultiLevelCache;

/**
 * Implementation of MultiLevelCache interface combining local and distributed caching.
 * <p>
 * 结合本地缓存和分布式缓存的多级缓存接口实现。
 * <p>
 * This class provides a multi-level caching strategy that combines
 * local in-memory cache with distributed cache for improved performance
 * and data consistency.
 * <p>
 * 此类提供多级缓存策略，将本地内存缓存与分布式缓存结合，以提高性能和数据一致性。
 *
 * @author b1itz7
 * @since 1.1.0
 */
public class MultiLevelCacheImpl implements MultiLevelCache {
    
    private final Cache localCache;
    private final Cache distributedCache;
    private final CacheProperties cacheProperties;
    private SyncStrategy syncStrategy;
    
    /**
     * Constructs a new MultiLevelCacheImpl with the specified local cache, distributed cache, and cache properties.
     * <p>
     * 使用指定的本地缓存、分布式缓存和缓存属性构造一个新的MultiLevelCacheImpl。
     *
     * @param localCache the local cache instance
     *                   <p>
     *                   本地缓存实例
     * @param distributedCache the distributed cache instance
     *                         <p>
     *                         分布式缓存实例
     * @param cacheProperties the cache configuration properties
     *                        <p>
     *                        缓存配置属性
     */
    public MultiLevelCacheImpl(Cache localCache, Cache distributedCache, CacheProperties cacheProperties) {
        this.localCache = localCache;
        this.distributedCache = distributedCache;
        this.cacheProperties = cacheProperties;
        this.syncStrategy = new DefaultSyncStrategy();
    }
    
    @Override
    public String getName() {
        return localCache.getName();
    }
    
    @Override
    public Object getNativeCache() {
        return this;
    }
    
    @Override
    public <T> T get(Object key, Class<T> type) {
        // 先查本地缓存
        T value = localCache.get(key, type);
        if (value != null) {
            return value;
        }
        
        // 本地缓存未命中，查分布式缓存
        value = distributedCache.get(key, type);
        if (value != null) {
            // 写入本地缓存
            localCache.put(key, value, 
                cacheProperties.getMultiLevel().getLocalExpireTime().toSeconds(), 
                TimeUnit.SECONDS);
        }
        
        return value;
    }
    
    @Override
    public <T> T get(Object key, Callable<T> valueLoader) {
        // 先查本地缓存
        T value = localCache.get(key, (Class<T>) Object.class);
        if (value != null) {
            return value;
        }
        
        // 本地缓存未命中，查分布式缓存
        value = distributedCache.get(key, (Class<T>) Object.class);
        if (value != null) {
            // 写入本地缓存
            localCache.put(key, value, 
                cacheProperties.getMultiLevel().getLocalExpireTime().toSeconds(), 
                TimeUnit.SECONDS);
            return value;
        }
        
        // 两级缓存都未命中，执行valueLoader
        try {
            value = valueLoader.call();
            if (value != null) {
                // 写入两级缓存
                put(key, value);
            }
            return value;
        } catch (Exception e) {
            throw new RuntimeException("加载缓存值失败", e);
        }
    }
    
    @Override
    public void put(Object key, Object value) {
        // 写入本地缓存
        localCache.put(key, value, 
            cacheProperties.getMultiLevel().getLocalExpireTime().toSeconds(), 
            TimeUnit.SECONDS);
        
        // 写入分布式缓存
        distributedCache.put(key, value);
    }
    
    @Override
    public void put(Object key, Object value, long timeout, TimeUnit unit) {
        // 写入本地缓存（使用较短的过期时间）
        localCache.put(key, value, 
            cacheProperties.getMultiLevel().getLocalExpireTime().toSeconds(), 
            TimeUnit.SECONDS);
        
        // 写入分布式缓存
        distributedCache.put(key, value, timeout, unit);
    }
    
    @Override
    public boolean putIfAbsent(Object key, Object value) {
        boolean localResult = localCache.putIfAbsent(key, value);
        boolean distributedResult = distributedCache.putIfAbsent(key, value);
        return localResult && distributedResult;
    }
    
    @Override
    public boolean putIfAbsent(Object key, Object value, long timeout, TimeUnit unit) {
        boolean localResult = localCache.putIfAbsent(key, value, 
            cacheProperties.getMultiLevel().getLocalExpireTime().toSeconds(), 
            TimeUnit.SECONDS);
        boolean distributedResult = distributedCache.putIfAbsent(key, value, timeout, unit);
        return localResult && distributedResult;
    }
    
    @Override
    public void evict(Object key) {
        localCache.evict(key);
        distributedCache.evict(key);
    }
    
    @Override
    public void evict(Collection<?> keys) {
        localCache.evict(keys);
        distributedCache.evict(keys);
    }
    
    @Override
    public void clear() {
        localCache.clear();
        distributedCache.clear();
    }
    
    @Override
    public <T> Map<Object, T> multiGet(Collection<?> keys, Class<T> type) {
        // 先查本地缓存
        Map<Object, T> result = localCache.multiGet(keys, type);
        
        // 找出本地缓存未命中的键
        Collection<Object> missingKeys = new java.util.ArrayList<>();
        for (Object key : keys) {
            if (!result.containsKey(key)) {
                missingKeys.add(key);
            }
        }
        
        if (!missingKeys.isEmpty()) {
            // 查分布式缓存
            Map<Object, T> distributedResult = distributedCache.multiGet(missingKeys, type);
            result.putAll(distributedResult);
            
            // 将分布式缓存结果写入本地缓存
            for (Map.Entry<Object, T> entry : distributedResult.entrySet()) {
                localCache.put(entry.getKey(), entry.getValue(), 
                    cacheProperties.getMultiLevel().getLocalExpireTime().toSeconds(), 
                    TimeUnit.SECONDS);
            }
        }
        
        return result;
    }
    
    @Override
    public void multiPut(Map<?, ?> map) {
        localCache.multiPut(map);
        distributedCache.multiPut(map);
    }
    
    @Override
    public void multiPut(Map<?, ?> map, long timeout, TimeUnit unit) {
        localCache.multiPut(map);
        distributedCache.multiPut(map, timeout, unit);
    }
    
    @Override
    public Long getExpire(Object key) {
        return distributedCache.getExpire(key);
    }
    
    @Override
    public Long getExpire(Object key, TimeUnit timeUnit) {
        return distributedCache.getExpire(key, timeUnit);
    }
    
    @Override
    public boolean expire(Object key, long timeout, TimeUnit unit) {
        boolean localResult = localCache.expire(key, 
            cacheProperties.getMultiLevel().getLocalExpireTime().toSeconds(), 
            TimeUnit.SECONDS);
        boolean distributedResult = distributedCache.expire(key, timeout, unit);
        return localResult && distributedResult;
    }
    
    @Override
    public boolean hasKey(Object key) {
        return localCache.hasKey(key) || distributedCache.hasKey(key);
    }
    
    @Override
    public long size() {
        return distributedCache.size();
    }
    
    @Override
    public Cache getLocalCache() {
        return localCache;
    }
    
    @Override
    public Cache getDistributedCache() {
        return distributedCache;
    }
    
    @Override
    public void setSyncStrategy(SyncStrategy strategy) {
        this.syncStrategy = strategy;
    }
    
    @Override
    public void syncToDistributed() {
        // 同步本地缓存到分布式缓存
        // 这里需要根据具体业务需求实现
    }
    
    @Override
    public void syncFromDistributed() {
        // 从分布式缓存同步到本地缓存
        // 这里需要根据具体业务需求实现
    }
    
    /**
     * Default synchronization strategy implementation.
     * <p>
     * 默认同步策略实现。
     */
    private static class DefaultSyncStrategy implements SyncStrategy {
        /**
         * {@inheritDoc}
         */
        @Override
        public boolean shouldSyncOnWrite() {
            return true;
        }
        
        /**
         * {@inheritDoc}
         */
        @Override
        public boolean shouldSyncOnRead() {
            return false;
        }
        
        /**
         * {@inheritDoc}
         */
        @Override
        public long getSyncDelay() {
            return 0;
        }
    }
}