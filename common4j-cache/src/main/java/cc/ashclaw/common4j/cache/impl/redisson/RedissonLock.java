package cc.ashclaw.common4j.cache.impl.redisson;

import cc.ashclaw.common4j.cache.enums.LockType;
import cc.ashclaw.common4j.cache.exception.CacheLockException;
import org.redisson.api.RLock;
import org.redisson.api.RReadWriteLock;
import org.redisson.api.RedissonClient;

import java.util.concurrent.TimeUnit;

/**
 * Distributed lock implementation using Redisson library.
 * <p>
 * 使用Redisson库实现的分布式锁。
 * <p>
 * This class provides distributed locking capabilities using
 * Redisson with Redis backend, supporting various lock types
 * including reentrant, fair, read-write, and red locks.
 * <p>
 * 此类使用Redisson和Redis后端提供分布式锁定功能，支持各种锁类型，包括可重入锁、公平锁、读写锁和红锁。
 *
 * @author b1itz7
 * @since 1.1.0
 */
public class RedissonLock {
    
    private final RedissonClient redissonClient;
    private final String lockKey;
    private RLock lock;
    
    /**
     * Constructs a new RedissonLock with the specified Redisson client and lock key.
     * <p>
     * 使用指定的Redisson客户端和锁键构造一个新的RedissonLock。
     *
     * @param redissonClient the Redisson client instance
     *                       <p>
     *                       Redisson客户端实例
     * @param lockKey the key used for the distributed lock
     *                <p>
     *                用于分布式锁的键
     */
    public RedissonLock(RedissonClient redissonClient, String lockKey) {
        this.redissonClient = redissonClient;
        this.lockKey = lockKey;
        this.lock = redissonClient.getLock(lockKey);
    }
    
    /**
     * Constructs a new RedissonLock with the specified Redisson client, lock key, and lock type.
     * <p>
     * 使用指定的Redisson客户端、锁键和锁类型构造一个新的RedissonLock。
     *
     * @param redissonClient the Redisson client instance
     *                       <p>
     *                       Redisson客户端实例
     * @param lockKey the key used for the distributed lock
     *                <p>
     *                用于分布式锁的键
     * @param lockType the type of lock to create
     *                 <p>
     *                 要创建的锁类型
     */
    public RedissonLock(RedissonClient redissonClient, String lockKey, LockType lockType) {
        this.redissonClient = redissonClient;
        this.lockKey = lockKey;
        
        switch (lockType) {
            case REENTRANT:
                this.lock = redissonClient.getLock(lockKey);
                break;
            case FAIR:
                this.lock = redissonClient.getFairLock(lockKey);
                break;
            case READ:
                RReadWriteLock readWriteLock = redissonClient.getReadWriteLock(lockKey);
                this.lock = readWriteLock.readLock();
                break;
            case WRITE:
                RReadWriteLock rwLock = redissonClient.getReadWriteLock(lockKey);
                this.lock = rwLock.writeLock();
                break;
            case RED_LOCK:
                // 红锁需要多个Redis实例，这里简化处理
                this.lock = redissonClient.getLock(lockKey);
                break;
            default:
                this.lock = redissonClient.getLock(lockKey);
        }
    }
    
    /**
     * Acquires the lock.
     * <p>
     * 获取锁。
     *
     * @throws CacheLockException if lock acquisition fails
     *                            <p>
     *                            如果获取锁失败，则抛出CacheLockException
     */
    public void lock() {
        try {
            lock.lock();
        } catch (Exception e) {
            throw new CacheLockException("获取分布式锁失败", e);
        }
    }
    
    /**
     * Acquires the lock with the specified lease time.
     * <p>
     * 获取锁并设置过期时间。
     *
     * @param leaseTime the lease time for the lock
     *                  <p>
     *                  锁的租约时间
     * @param unit the time unit of the lease time
     *             <p>
     *             租约时间的时间单位
     * @throws CacheLockException if lock acquisition fails
     *                            <p>
     *                            如果获取锁失败，则抛出CacheLockException
     */
    public void lock(long leaseTime, TimeUnit unit) {
        try {
            lock.lock(leaseTime, unit);
        } catch (Exception e) {
            throw new CacheLockException("获取分布式锁失败", e);
        }
    }
    
    /**
     * Attempts to acquire the lock immediately.
     * <p>
     * 立即尝试获取锁。
     *
     * @return {@code true} if the lock was acquired, {@code false} otherwise
     *         <p>
     *         如果获取了锁，则返回 {@code true}；否则返回 {@code false}
     * @throws CacheLockException if lock acquisition fails
     *                            <p>
     *                            如果获取锁失败，则抛出CacheLockException
     */
    public boolean tryLock() {
        try {
            return lock.tryLock();
        } catch (Exception e) {
            throw new CacheLockException("尝试获取分布式锁失败", e);
        }
    }
    
    /**
     * Attempts to acquire the lock within the specified wait time.
     * <p>
     * 在指定的等待时间内尝试获取锁。
     *
     * @param waitTime the maximum time to wait for the lock
     *                 <p>
     *                 等待锁的最大时间
     * @param unit the time unit of the wait time
     *             <p>
     *             等待时间的时间单位
     * @return {@code true} if the lock was acquired, {@code false} otherwise
     *         <p>
     *         如果获取了锁，则返回 {@code true}；否则返回 {@code false}
     * @throws CacheLockException if lock acquisition fails
     *                            <p>
     *                            如果获取锁失败，则抛出CacheLockException
     */
    public boolean tryLock(long waitTime, TimeUnit unit) {
        try {
            return lock.tryLock(waitTime, unit);
        } catch (Exception e) {
            throw new CacheLockException("尝试获取分布式锁失败", e);
        }
    }
    
    /**
     * Attempts to acquire the lock within the specified wait time with lease time.
     * <p>
     * 在指定的等待时间内尝试获取锁，并设置租约时间。
     *
     * @param waitTime the maximum time to wait for the lock
     *                 <p>
     *                 等待锁的最大时间
     * @param leaseTime the lease time for the lock
     *                  <p>
     *                  锁的租约时间
     * @param unit the time unit for both wait time and lease time
     *             <p>
     *             等待时间和租约时间的时间单位
     * @return {@code true} if the lock was acquired, {@code false} otherwise
     *         <p>
     *         如果获取了锁，则返回 {@code true}；否则返回 {@code false}
     * @throws CacheLockException if lock acquisition fails
     *                            <p>
     *                            如果获取锁失败，则抛出CacheLockException
     */
    public boolean tryLock(long waitTime, long leaseTime, TimeUnit unit) {
        try {
            return lock.tryLock(waitTime, leaseTime, unit);
        } catch (Exception e) {
            throw new CacheLockException("尝试获取分布式锁失败", e);
        }
    }
    
    /**
     * Releases the lock if it is held by the current thread.
     * <p>
     * 如果锁被当前线程持有，则释放锁。
     *
     * @throws CacheLockException if lock release fails
     *                            <p>
     *                            如果释放锁失败，则抛出CacheLockException
     */
    public void unlock() {
        try {
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        } catch (Exception e) {
            throw new CacheLockException("释放分布式锁失败", e);
        }
    }
    
    /**
     * Forcefully releases the lock regardless of the holding thread.
     * <p>
     * 强制释放锁，无论哪个线程持有。
     *
     * @throws CacheLockException if force unlock fails
     *                            <p>
     *                            如果强制释放锁失败，则抛出CacheLockException
     */
    public void forceUnlock() {
        try {
            lock.forceUnlock();
        } catch (Exception e) {
            throw new CacheLockException("强制释放分布式锁失败", e);
        }
    }
    
    /**
     * Checks if the lock is held by the current thread.
     * <p>
     * 检查锁是否被当前线程持有。
     *
     * @return {@code true} if the lock is held by the current thread, {@code false} otherwise
     *         <p>
     *         如果锁被当前线程持有，则返回 {@code true}；否则返回 {@code false}
     */
    public boolean isHeldByCurrentThread() {
        return lock.isHeldByCurrentThread();
    }
    
    /**
     * Checks if the lock is currently held by any thread.
     * <p>
     * 检查锁是否当前被任何线程持有。
     *
     * @return {@code true} if the lock is held by any thread, {@code false} otherwise
     *         <p>
     *         如果锁被任何线程持有，则返回 {@code true}；否则返回 {@code false}
     */
    public boolean isLocked() {
        return lock.isLocked();
    }
}