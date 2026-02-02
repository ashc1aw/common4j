package cc.ashclaw.common4j.cache.config;

import cc.ashclaw.common4j.cache.enums.CacheType;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

/**
 * Configuration properties for cache operations.
 * <p>
 * 缓存操作的配置属性。
 * <p>
 * This class provides configuration options for cache behavior including
 * cache types, expiration times, protection mechanisms, and specific cache
 * implementation configurations.
 * <p>
 * 此类提供缓存行为的配置选项，包括缓存类型、过期时间、保护机制和特定缓存实现配置。
 *
 * @author b1itz7
 * @since 1.1.0
 */
@ConfigurationProperties(prefix = "common4j.cache")
@Data
public class CacheProperties {
    
    /**
     * Whether caching is enabled.
     * <p>
     * 是否启用缓存。
     */
    private boolean enabled = true;
    
    /**
     * The default cache type to use.
     * <p>
     * 要使用的默认缓存类型。
     */
    private CacheType type = CacheType.MULTI_LEVEL;
    
    /**
     * The default expiration time for cache entries.
     * <p>
     * 缓存条目的默认过期时间。
     */
    private Duration expireTime = Duration.ofHours(1);
    
    /**
     * Whether cache protection mechanisms are enabled.
     * <p>
     * 是否启用缓存保护机制。
     */
    private boolean protectionEnabled = true;
    
    /**
     * The expiration time for null values in cache penetration protection.
     * <p>
     * 缓存穿透保护中空值的过期时间。
     */
    private Duration penetrationExpireTime = Duration.ofMinutes(5);
    
    /**
     * The wait time for mutex locks in cache breakdown protection.
     * <p>
     * 缓存击穿保护中互斥锁的等待时间。
     */
    private Duration breakdownWaitTime = Duration.ofSeconds(30);
    
    /**
     * The random range (percentage) for expiration time in cache avalanche protection.
     * <p>
     * 缓存雪崩保护中过期时间的随机范围（百分比）。
     */
    private int avalancheRandomRange = 10;
    
    /**
     * Configuration properties for Redisson distributed caching.
     * <p>
     * Redisson分布式缓存的配置属性。
     */
    private RedissonProperties redisson = new RedissonProperties();
    
    /**
     * Configuration properties for Caffeine local caching.
     * <p>
     * Caffeine本地缓存的配置属性。
     */
    private CaffeineProperties caffeine = new CaffeineProperties();
    
    /**
     * Configuration properties for multi-level caching.
     * <p>
     * 多级缓存的配置属性。
     */
    private MultiLevelProperties multiLevel = new MultiLevelProperties();
    
    /**
     * Cache-specific configuration items.
     * <p>
     * 特定缓存的配置项。
     */
    private Map<String, CacheConfig> configs = new HashMap<>();
    
    /**
     * Configuration properties for Redisson distributed caching.
     * <p>
     * Redisson分布式缓存的配置属性。
     */
    @Data
    public static class RedissonProperties {
        
        /**
         * The Redis server address.
         * <p>
         * Redis服务器地址。
         */
        private String address = "redis://localhost:6379";
        
        /**
         * The password for Redis authentication.
         * <p>
         * Redis身份验证的密码。
         */
        private String password;
        
        /**
         * The Redis database index.
         * <p>
         * Redis数据库索引。
         */
        private int database = 0;
        
        /**
         * The connection timeout for Redis connections.
         * <p>
         * Redis连接的连接超时时间。
         */
        private Duration connectionTimeout = Duration.ofSeconds(10);
        
        /**
         * The command timeout for Redis operations.
         * <p>
         * Redis操作的命令超时时间。
         */
        private Duration commandTimeout = Duration.ofSeconds(5);

    }
    
    /**
     * Configuration properties for Caffeine local caching.
     * <p>
     * Caffeine本地缓存的配置属性。
     */
    @Data
    public static class CaffeineProperties {
        
        /**
         * The initial capacity of the cache.
         * <p>
         * 缓存的初始容量。
         */
        private int initialCapacity = 100;
        
        /**
         * The maximum size of the cache.
         * <p>
         * 缓存的最大容量。
         */
        private long maximumSize = 1000;
        
        /**
         * The expiration time after write.
         * <p>
         * 写入后的过期时间。
         */
        private Duration expireAfterWrite = Duration.ofHours(1);
        
        /**
         * The expiration time after access.
         * <p>
         * 访问后的过期时间。
         */
        private Duration expireAfterAccess = Duration.ofMinutes(30);
        
        /**
         * Whether statistics collection is enabled.
         * <p>
         * 是否启用统计收集。
         */
        private boolean statsEnabled = false;
        
    }
    
    /**
     * Configuration properties for multi-level caching.
     * <p>
     * 多级缓存的配置属性。
     */
    @Data
    public static class MultiLevelProperties {
        
        /**
         * Whether multi-level caching is enabled.
         * <p>
         * 是否启用多级缓存。
         */
        private boolean enabled = true;
        
        /**
         * The expiration time for local cache entries.
         * <p>
         * 本地缓存条目的过期时间。
         */
        private Duration localExpireTime = Duration.ofMinutes(10);
        
        /**
         * Whether cache synchronization is enabled.
         * <p>
         * 是否启用缓存同步。
         */
        private boolean syncEnabled = true;
        
        /**
         * The delay time for cache synchronization.
         * <p>
         * 缓存同步的延迟时间。
         */
        private Duration syncDelay = Duration.ofSeconds(1);
    }
    
    /**
     * Configuration for specific cache instances.
     * <p>
     * 特定缓存实例的配置。
     */
    @Data
    public static class CacheConfig {
        
        /**
         * The cache type for this specific cache.
         * <p>
         * 此特定缓存的缓存类型。
         */
        private CacheType type;
        
        /**
         * The expiration time for this specific cache.
         * <p>
         * 此特定缓存的过期时间。
         */
        private Duration expireTime;
        
        /**
         * Whether protection is enabled for this specific cache.
         * <p>
         * 是否为此特定缓存启用保护。
         */
        private Boolean protectionEnabled;
    }

}