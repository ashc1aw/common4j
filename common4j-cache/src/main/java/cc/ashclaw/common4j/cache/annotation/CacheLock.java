package cc.ashclaw.common4j.cache.annotation;

import cc.ashclaw.common4j.cache.enums.LockType;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * Annotation for distributed locking using Redis.
 * <p>
 * 使用Redis进行分布式锁的注解。
 * <p>
 * This annotation provides distributed locking capabilities with various
 * lock types and configuration options.
 * <p>
 * 此注解提供具有各种锁类型和配置选项的分布式锁定功能。
 *
 * @author b1itz7
 * @since 1.1.0
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface CacheLock {
    
    /**
     * The name of the lock.
     * <p>
     * 锁的名称。
     *
     * @return the lock name
     *         <p>
     *         锁名称
     */
    String value() default "";
    
    /**
     * The SpEL expression for computing the lock key dynamically.
     * <p>
     * 用于动态计算锁键的SpEL表达式。
     *
     * @return the SpEL expression for the lock key
     *         <p>
     *         锁键的SpEL表达式
     */
    String key() default "";
    
    /**
     * The type of lock to use.
     * <p>
     * 要使用的锁类型。
     * <p>
     * Default is reentrant lock.
     * <p>
     * 默认可重入锁。
     *
     * @return the lock type
     *         <p>
     *         锁类型
     */
    LockType type() default LockType.REENTRANT;
    
    /**
     * The maximum time to wait for the lock acquisition.
     * <p>
     * 获取锁的最大等待时间。
     *
     * @return the wait time
     *         <p>
     *         等待时间
     */
    long waitTime() default 30;
    
    /**
     * The time unit for the wait time.
     * <p>
     * 等待时间的时间单位。
     *
     * @return the time unit for wait time
     *         <p>
     *         等待时间的时间单位
     */
    TimeUnit waitTimeUnit() default TimeUnit.SECONDS;
    
    /**
     * The lease time for the lock.
     * <p>
     * 锁的持有时间。
     *
     * @return the lease time
     *         <p>
     *         持有时间
     */
    long leaseTime() default 30;
    
    /**
     * The time unit for the lease time.
     * <p>
     * 持有时间的时间单位。
     *
     * @return the time unit for lease time
     *         <p>
     *         持有时间的时间单位
     */
    TimeUnit leaseTimeUnit() default TimeUnit.SECONDS;
    
    /**
     * Whether the lock should be fair.
     * <p>
     * 锁是否应该是公平的。
     * <p>
     * Fair locks guarantee that the longest waiting thread will acquire the lock first.
     * <p>
     * 公平锁保证等待时间最长的线程将首先获取锁。
     *
     * @return {@code true} if the lock should be fair, {@code false} otherwise
     *         <p>
     *         如果锁应该是公平的，则返回 {@code true}；否则返回 {@code false}
     */
    boolean fair() default false;
    
    /**
     * Whether to use tryLock instead of lock.
     * <p>
     * 是否使用tryLock而不是lock。
     * <p>
     * If true, the method will try to acquire the lock but won't block if it's not available.
     * <p>
     * 如果为true，方法将尝试获取锁，但如果锁不可用则不会阻塞。
     *
     * @return {@code true} if tryLock should be used, {@code false} otherwise
     *         <p>
     *         如果应使用tryLock，则返回 {@code true}；否则返回 {@code false}
     */
    boolean tryLock() default false;
    
    /**
     * The error message to use when lock acquisition fails.
     * <p>
     * 获取锁失败时使用的错误消息。
     *
     * @return the error message
     *         <p>
     *         错误消息
     */
    String errorMessage() default "获取分布式锁失败";
    
    /**
     * Whether to acquire the lock before method invocation.
     * <p>
     * 是否在方法调用前获取锁。
     * <p>
     * If true, the lock is acquired before the method is invoked.
     * If false, the lock is acquired during method execution.
     * <p>
     * 如果为true，则在方法调用前获取锁。
     * 如果为false，则在方法执行期间获取锁。
     *
     * @return {@code true} if lock should be acquired before invocation, {@code false} otherwise
     *         <p>
     *         如果应在调用前获取锁，则返回 {@code true}；否则返回 {@code false}
     */
    boolean beforeInvocation() default true;
}