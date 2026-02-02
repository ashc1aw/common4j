package cc.ashclaw.common4j.cache.enums;

/**
 * Enumeration of lock types.
 * <p>
 * 锁类型枚举。
 * <p>
 * This enum defines the different types of distributed locks supported
 * by the cache module, including reentrant, fair, read-write, and red locks.
 * <p>
 * 此枚举定义了缓存模块支持的不同分布式锁类型，包括可重入锁、公平锁、读写锁和红锁。
 *
 * @author b1itz7
 * @since 1.1.0
 */
public enum LockType {
    
    /**
     * Reentrant lock that allows the same thread to acquire the lock multiple times.
     * <p>
     * 可重入锁，允许同一线程多次获取锁。
     */
    REENTRANT,
    
    /**
     * Fair lock that grants access in the order threads requested it.
     * <p>
     * 公平锁，按照线程请求的顺序授予访问权限。
     */
    FAIR,
    
    /**
     * Read lock that allows multiple readers to access simultaneously.
     * <p>
     * 读锁，允许多个读取者同时访问。
     */
    READ,
    
    /**
     * Write lock that allows exclusive access for a single writer.
     * <p>
     * 写锁，允许单个写入者独占访问。
     */
    WRITE,
    
    /**
     * Red lock (multi-node lock) for distributed locking across multiple Redis nodes.
     * <p>
     * 红锁（多节点锁），用于跨多个Redis节点的分布式锁定。
     */
    RED_LOCK
}