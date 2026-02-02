package cc.ashclaw.common4j.cache.protection;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import cc.ashclaw.common4j.cache.config.CacheProperties;
import cc.ashclaw.common4j.cache.core.Cache;

/**
 * Cache avalanche protection implementation.
 * <p>
 * 缓存雪崩保护实现。
 * <p>
 * This class provides protection against cache avalanche by adding
 * random expiration times to cache entries to prevent simultaneous
 * cache expiration across multiple cache instances.
 * <p>
 * 此类通过为缓存条目添加随机过期时间来提供缓存雪崩保护，防止多个缓存实例同时过期。
 *
 * @author b1itz7
 * @since 1.1.0
 */
public class CacheAvalancheProtection {
    
    private final Cache cache;
    private final CacheProperties cacheProperties;
    private final Random random = new Random();
    
    public CacheAvalancheProtection(Cache cache, CacheProperties cacheProperties) {
        this.cache = cache;
        this.cacheProperties = cacheProperties;
    }
    
    /**
     * Puts a cache value with avalanche protection by adding random expiration time.
     * <p>
     * 通过添加随机过期时间来设置缓存值，提供雪崩保护。
     *
     * @param key the cache key
     *            <p>
     *            缓存键
     * @param value the cache value
     *              <p>
     *              缓存值
     */
    public void putWithProtection(Object key, Object value) {
        long baseExpireTime = cacheProperties.getExpireTime().toSeconds();
        long randomExpireTime = getRandomExpireTime(baseExpireTime);
        
        cache.put(key, value, randomExpireTime, TimeUnit.SECONDS);
    }
    
    /**
     * Puts a cache value with avalanche protection using specified base expiration time.
     * <p>
     * 使用指定的基础过期时间设置缓存值，提供雪崩保护。
     *
     * @param key the cache key
     *            <p>
     *            缓存键
     * @param value the cache value
     *              <p>
     *              缓存值
     * @param baseExpireTime the base expiration time
     *                       <p>
     *                       基础过期时间
     * @param unit the time unit of the base expiration time
     *             <p>
     *             基础过期时间的时间单位
     */
    public void putWithProtection(Object key, Object value, long baseExpireTime, TimeUnit unit) {
        long baseSeconds = unit.toSeconds(baseExpireTime);
        long randomExpireTime = getRandomExpireTime(baseSeconds);
        
        cache.put(key, value, randomExpireTime, TimeUnit.SECONDS);
    }
    
    /**
     * Puts multiple cache values with avalanche protection by adding random expiration time.
     * <p>
     * 通过添加随机过期时间来批量设置缓存值，提供雪崩保护。
     *
     * @param map the map containing key-value pairs to cache
     *            <p>
     *            包含要缓存的键值对的映射
     */
    public void multiPutWithProtection(java.util.Map<?, ?> map) {
        long baseExpireTime = cacheProperties.getExpireTime().toSeconds();
        long randomExpireTime = getRandomExpireTime(baseExpireTime);
        
        cache.multiPut(map, randomExpireTime, TimeUnit.SECONDS);
    }
    
    /**
     * Generates a random expiration time based on the base expiration time.
     * <p>
     * 基于基础过期时间生成随机过期时间。
     *
     * @param baseExpireTime the base expiration time in seconds
     *                       <p>
     *                       基础过期时间（秒）
     * @return the random expiration time in seconds
     *         <p>
     *         随机过期时间（秒）
     */
    private long getRandomExpireTime(long baseExpireTime) {
        int randomRange = cacheProperties.getAvalancheRandomRange();
        
        if (randomRange <= 0) {
            return baseExpireTime;
        }
        
        // 计算随机范围（正负百分比）
        long range = (long) (baseExpireTime * randomRange / 100.0);
        
        // 生成随机偏移量
        long offset = random.nextLong() % (range * 2 + 1) - range;
        
        // 确保过期时间不小于1秒
        long expireTime = baseExpireTime + offset;
        return Math.max(expireTime, 1);
    }
}