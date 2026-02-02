package cc.ashclaw.common4j.cache.enums;

/**
 * Enumeration of cache operations.
 * <p>
 * 缓存操作枚举。
 * <p>
 * This enum defines the types of operations that can be performed
 * on cache instances, including retrieval, storage, eviction, and clearing.
 * <p>
 * 此枚举定义了可以在缓存实例上执行的操作类型，包括检索、存储、清除和清空。
 *
 * @author b1itz7
 * @since 1.1.0
 */
public enum CacheOperation {
    
    /**
     * Get operation for retrieving cached values.
     * <p>
     * 获取操作，用于检索缓存的值。
     */
    GET,
    
    /**
     * Put operation for storing values in cache.
     * <p>
     * 设置操作，用于将值存储在缓存中。
     */
    PUT,
    
    /**
     * Evict operation for removing specific cache entries.
     * <p>
     * 删除操作，用于移除特定的缓存条目。
     */
    EVICT,
    
    /**
     * Clear operation for removing all cache entries.
     * <p>
     * 清空操作，用于移除所有缓存条目。
     */
    CLEAR
}