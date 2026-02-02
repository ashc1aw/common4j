package cc.ashclaw.common4j.cache.protection;

import cc.ashclaw.common4j.cache.core.Cache;
import cc.ashclaw.common4j.cache.config.CacheProperties;
import cc.ashclaw.common4j.cache.impl.redisson.RedissonLock;
import org.redisson.api.RedissonClient;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * Cache breakdown protection implementation.
 * <p>
 * 缓存击穿保护实现。
 * <p>
 * This class provides protection against cache breakdown by using
 * distributed locks to prevent multiple threads from simultaneously
 * loading data when cache misses occur.
 * <p>
 * 此类通过使用分布式锁来提供缓存击穿保护，防止在缓存未命中时多个线程同时加载数据。
 *
 * @author b1itz7
 * @since 1.1.0
 */
public class CacheBreakdownProtection {
    
    private final Cache cache;
    private final CacheProperties cacheProperties;
    private final RedissonClient redissonClient;
    
    public CacheBreakdownProtection(Cache cache, CacheProperties cacheProperties, RedissonClient redissonClient) {
        this.cache = cache;
        this.cacheProperties = cacheProperties;
        this.redissonClient = redissonClient;
    }
    
    /**
     * Gets a cache value with breakdown protection using distributed locks.
     * <p>
     * 使用分布式锁获取缓存值，提供击穿保护。
     *
     * @param key the cache key
     *            <p>
     *            缓存键
     * @param type the type of the cached value
     *             <p>
     *             缓存值的类型
     * @param valueLoader the value loader to call when cache misses
     *                    <p>
     *                    缓存未命中时调用的值加载器
     * @param <T> the type of the value
     *            <p>
     *            值的类型
     * @return the cached value, or the loaded value if cache misses
     *         <p>
     *         缓存的值，如果缓存未命中则返回加载的值
     * @throws RuntimeException if lock acquisition fails or value loading fails
     *                          <p>
     *                          如果获取锁失败或值加载失败，则抛出RuntimeException
     */
    public <T> T getWithProtection(Object key, Class<T> type, Callable<T> valueLoader) {
        T value = cache.get(key, type);
        if (value != null) {
            return value;
        }
        
        // 使用分布式锁防止缓存击穿
        String lockKey = "cache:lock:" + key;
        RedissonLock lock = new RedissonLock(redissonClient, lockKey);
        
        try {
            // 尝试获取锁
            if (lock.tryLock(cacheProperties.getBreakdownWaitTime().toSeconds(), TimeUnit.SECONDS)) {
                try {
                    // 再次检查缓存，防止在等待锁期间其他线程已经加载了数据
                    value = cache.get(key, type);
                    if (value != null) {
                        return value;
                    }
                    
                    // 加载数据
                    value = valueLoader.call();
                    if (value != null) {
                        cache.put(key, value);
                    }
                    
                    return value;
                } finally {
                    lock.unlock();
                }
            } else {
                // 获取锁失败，直接返回null或抛出异常
                throw new RuntimeException("获取缓存锁失败，请稍后重试");
            }
        } catch (Exception e) {
            throw new RuntimeException("缓存击穿保护处理失败", e);
        }
    }
}