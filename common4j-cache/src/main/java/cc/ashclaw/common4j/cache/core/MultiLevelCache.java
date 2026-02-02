package cc.ashclaw.common4j.cache.core;

/**
 * Interface for multi-level cache implementations.
 * <p>
 * 多级缓存接口，用于多级缓存实现。
 * <p>
 * This interface extends the basic {@code Cache} interface and provides
 * additional methods for managing multi-level caching with local and
 * distributed cache layers.
 * <p>
 * 该接口扩展了基本的 {@code Cache} 接口，并提供用于管理具有本地和分布式缓存层的
 * 多级缓存的附加方法。
 *
 * @author b1itz7
 * @since 1.1.0
 */
public interface MultiLevelCache extends Cache {
    
    /**
     * Gets the local cache component of the multi-level cache.
     * <p>
     * 获取多级缓存的本地缓存组件。
     *
     * @return the local cache instance
     *         <p>
     *         本地缓存实例
     */
    Cache getLocalCache();
    
    /**
     * Gets the distributed cache component of the multi-level cache.
     * <p>
     * 获取多级缓存的分布式缓存组件。
     *
     * @return the distributed cache instance
     *         <p>
     *         分布式缓存实例
     */
    Cache getDistributedCache();
    
    /**
     * Sets the synchronization strategy for cache operations.
     * <p>
     * 设置缓存操作的同步策略。
     *
     * @param strategy the synchronization strategy to use
     *                 <p>
     *                 要使用的同步策略
     */
    void setSyncStrategy(SyncStrategy strategy);
    
    /**
     * Synchronizes data from the local cache to the distributed cache.
     * <p>
     * 将数据从本地缓存同步到分布式缓存。
     */
    void syncToDistributed();
    
    /**
     * Synchronizes data from the distributed cache to the local cache.
     * <p>
     * 将数据从分布式缓存同步到本地缓存。
     */
    void syncFromDistributed();
    
    /**
     * Interface for defining cache synchronization strategies.
     * <p>
     * 缓存同步策略接口，用于定义缓存同步策略。
     */
    interface SyncStrategy {
        
        /**
         * Determines whether synchronization should occur on write operations.
         * <p>
         * 确定是否应在写入操作时进行同步。
         *
         * @return {@code true} if synchronization should occur on write, {@code false} otherwise
         *         <p>
         *         如果应在写入时进行同步，则返回 {@code true}；否则返回 {@code false}
         */
        boolean shouldSyncOnWrite();
        
        /**
         * Determines whether synchronization should occur on read operations.
         * <p>
         * 确定是否应在读取操作时进行同步。
         *
         * @return {@code true} if synchronization should occur on read, {@code false} otherwise
         *         <p>
         *         如果应在读取时进行同步，则返回 {@code true}；否则返回 {@code false}
         */
        boolean shouldSyncOnRead();
        
        /**
         * Gets the synchronization delay time in milliseconds.
         * <p>
         * 获取同步延迟时间（以毫秒为单位）。
         *
         * @return the synchronization delay time in milliseconds
         *         <p>
         *         同步延迟时间（毫秒）
         */
        long getSyncDelay();
    }
}