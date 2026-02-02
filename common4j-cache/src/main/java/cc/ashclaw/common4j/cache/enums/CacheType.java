package cc.ashclaw.common4j.cache.enums;

/**
 * Enumeration of cache types.
 * <p>
 * 缓存类型枚举。
 * <p>
 * This enum defines the different types of cache implementations
 * supported by the cache module, including local, distributed, and multi-level caching.
 * <p>
 * 此枚举定义了缓存模块支持的不同缓存实现类型，包括本地缓存、分布式缓存和多级缓存。
 *
 * @author b1itz7
 * @since 1.1.0
 */
public enum CacheType {
    
    /**
     * Local cache implementation using in-memory storage.
     * <p>
     * 本地缓存实现，使用内存存储。
     */
    LOCAL,
    
    /**
     * Distributed cache implementation using Redis/Redisson.
     * <p>
     * 分布式缓存实现，使用Redis/Redisson。
     */
    DISTRIBUTED,
    
    /**
     * Multi-level cache combining local and distributed caching.
     * <p>
     * 多级缓存，结合本地缓存和分布式缓存。
     */
    MULTI_LEVEL
}