package cc.ashclaw.common4j.cache.util;

import cc.ashclaw.common4j.cache.enums.LockType;
import cc.ashclaw.common4j.cache.impl.redisson.RedissonLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/**
 * Distributed lock utility class providing static methods for lock operations.
 * <p>
 * 分布式锁工具类，提供用于锁操作的静态方法。
 * <p>
 * This class provides convenient static methods for distributed lock operations
 * using Redisson with support for various lock types and timeout configurations.
 * <p>
 * 此类使用Redisson为分布式锁操作提供方便的静态方法，支持各种锁类型和超时配置。
 *
 * @author b1itz7
 * @since 1.1.0
 */
@Component
public class LockUtil {
    
    private static RedissonClient redissonClient;
    
    @Autowired
    public void setRedissonClient(RedissonClient redissonClient) {
        LockUtil.redissonClient = redissonClient;
    }
    
    /**
     * Executes the specified operation with a distributed lock.
     * <p>
     * 使用分布式锁执行指定的操作。
     *
     * @param lockKey the key for the distributed lock
     *                <p>
     *                分布式锁的键
     * @param supplier the operation to be executed
     *                 <p>
     *                 要执行的操作
     * @param <T> the type of the result
     *            <p>
     *            结果的类型
     * @return the result of the operation
     *         <p>
     *         操作的结果
     */
    public static <T> T executeWithLock(String lockKey, Supplier<T> supplier) {
        return executeWithLock(lockKey, LockType.REENTRANT, 30, 30, TimeUnit.SECONDS, supplier);
    }
    
    /**
     * Executes the specified operation with a distributed lock of the specified type.
     * <p>
     * 使用指定类型的分布式锁执行指定的操作。
     *
     * @param lockKey the key for the distributed lock
     *                <p>
     *                分布式锁的键
     * @param lockType the type of lock to use
     *                 <p>
     *                 要使用的锁类型
     * @param supplier the operation to be executed
     *                 <p>
     *                 要执行的操作
     * @param <T> the type of the result
     *            <p>
     *            结果的类型
     * @return the result of the operation
     *         <p>
     *         操作的结果
     */
    public static <T> T executeWithLock(String lockKey, LockType lockType, Supplier<T> supplier) {
        return executeWithLock(lockKey, lockType, 30, 30, TimeUnit.SECONDS, supplier);
    }
    
    /**
     * Executes the specified operation with a distributed lock with custom timeout configuration.
     * <p>
     * 使用自定义超时配置的分布式锁执行指定的操作。
     *
     * @param lockKey the key for the distributed lock
     *                <p>
     *                分布式锁的键
     * @param lockType the type of lock to use
     *                 <p>
     *                 要使用的锁类型
     * @param waitTime the maximum time to wait for the lock
     *                 <p>
     *                 等待锁的最大时间
     * @param leaseTime the time to hold the lock after acquiring it
     *                  <p>
     *                  获取锁后持有锁的时间
     * @param unit the time unit for waitTime and leaseTime
     *             <p>
     *             waitTime和leaseTime的时间单位
     * @param supplier the operation to be executed
     *                 <p>
     *                 要执行的操作
     * @param <T> the type of the result
     *            <p>
     *            结果的类型
     * @return the result of the operation
     *         <p>
     *         操作的结果
     */
    public static <T> T executeWithLock(String lockKey, LockType lockType, 
                                      long waitTime, long leaseTime, TimeUnit unit, 
                                      Supplier<T> supplier) {
        RedissonLock lock = new RedissonLock(redissonClient, lockKey, lockType);
        
        try {
            if (lock.tryLock(waitTime, leaseTime, unit)) {
                try {
                    return supplier.get();
                } finally {
                    lock.unlock();
                }
            } else {
                throw new RuntimeException("获取分布式锁失败");
            }
        } catch (Exception e) {
            throw new RuntimeException("执行带锁操作失败", e);
        }
    }
    
    /**
     * Executes the specified operation with a distributed lock (no return value).
     * <p>
     * 使用分布式锁执行指定的操作（无返回值）。
     *
     * @param lockKey the key for the distributed lock
     *                <p>
     *                分布式锁的键
     * @param runnable the operation to be executed
     *                 <p>
     *                 要执行的操作
     */
    public static void executeWithLock(String lockKey, Runnable runnable) {
        executeWithLock(lockKey, LockType.REENTRANT, 30, 30, TimeUnit.SECONDS, runnable);
    }
    
    /**
     * Executes the specified operation with a distributed lock of the specified type (no return value).
     * <p>
     * 使用指定类型的分布式锁执行指定的操作（无返回值）。
     *
     * @param lockKey the key for the distributed lock
     *                <p>
     *                分布式锁的键
     * @param lockType the type of lock to use
     *                 <p>
     *                 要使用的锁类型
     * @param runnable the operation to be executed
     *                 <p>
     *                 要执行的操作
     */
    public static void executeWithLock(String lockKey, LockType lockType, Runnable runnable) {
        executeWithLock(lockKey, lockType, 30, 30, TimeUnit.SECONDS, runnable);
    }
    
    /**
     * Executes the specified operation with a distributed lock with custom timeout configuration (no return value).
     * <p>
     * 使用自定义超时配置的分布式锁执行指定的操作（无返回值）。
     *
     * @param lockKey the key for the distributed lock
     *                <p>
     *                分布式锁的键
     * @param lockType the type of lock to use
     *                 <p>
     *                 要使用的锁类型
     * @param waitTime the maximum time to wait for the lock
     *                 <p>
     *                 等待锁的最大时间
     * @param leaseTime the time to hold the lock after acquiring it
     *                  <p>
     *                  获取锁后持有锁的时间
     * @param unit the time unit for waitTime and leaseTime
     *             <p>
     *             waitTime和leaseTime的时间单位
     * @param runnable the operation to be executed
     *                 <p>
     *                 要执行的操作
     */
    public static void executeWithLock(String lockKey, LockType lockType, 
                                      long waitTime, long leaseTime, TimeUnit unit, 
                                      Runnable runnable) {
        RedissonLock lock = new RedissonLock(redissonClient, lockKey, lockType);
        
        try {
            if (lock.tryLock(waitTime, leaseTime, unit)) {
                try {
                    runnable.run();
                } finally {
                    lock.unlock();
                }
            } else {
                throw new RuntimeException("获取分布式锁失败");
            }
        } catch (Exception e) {
            throw new RuntimeException("执行带锁操作失败", e);
        }
    }
    
    /**
     * Attempts to acquire the lock immediately.
     * <p>
     * 立即尝试获取锁。
     *
     * @param lockKey the key for the distributed lock
     *                <p>
     *                分布式锁的键
     * @return {@code true} if the lock was acquired, {@code false} otherwise
     *         <p>
     *         如果获取了锁，则返回 {@code true}；否则返回 {@code false}
     */
    public static boolean tryLock(String lockKey) {
        RedissonLock lock = new RedissonLock(redissonClient, lockKey);
        return lock.tryLock();
    }
    
    /**
     * Attempts to acquire the lock within the specified wait time.
     * <p>
     * 在指定的等待时间内尝试获取锁。
     *
     * @param lockKey the key for the distributed lock
     *                <p>
     *                分布式锁的键
     * @param waitTime the maximum time to wait for the lock
     *                 <p>
     *                 等待锁的最大时间
     * @param unit the time unit of the waitTime
     *             <p>
     *             waitTime的时间单位
     * @return {@code true} if the lock was acquired, {@code false} otherwise
     *         <p>
     *         如果获取了锁，则返回 {@code true}；否则返回 {@code false}
     */
    public static boolean tryLock(String lockKey, long waitTime, TimeUnit unit) {
        RedissonLock lock = new RedissonLock(redissonClient, lockKey);
        return lock.tryLock(waitTime, unit);
    }
    
    /**
     * Releases the lock.
     * <p>
     * 释放锁。
     *
     * @param lockKey the key for the distributed lock
     *                <p>
     *                分布式锁的键
     */
    public static void unlock(String lockKey) {
        RedissonLock lock = new RedissonLock(redissonClient, lockKey);
        lock.unlock();
    }
    
    /**
     * Checks if the lock is currently held by any thread.
     * <p>
     * 检查锁是否当前被任何线程持有。
     *
     * @param lockKey the key for the distributed lock
     *                <p>
     *                分布式锁的键
     * @return {@code true} if the lock is held by any thread, {@code false} otherwise
     *         <p>
     *         如果锁被任何线程持有，则返回 {@code true}；否则返回 {@code false}
     */
    public static boolean isLocked(String lockKey) {
        RedissonLock lock = new RedissonLock(redissonClient, lockKey);
        return lock.isLocked();
    }
}