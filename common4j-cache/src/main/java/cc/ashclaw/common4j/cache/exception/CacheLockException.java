package cc.ashclaw.common4j.cache.exception;

/**
 * Exception class for distributed lock operations.
 * <p>
 * 分布式锁操作异常类。
 * <p>
 * This exception is thrown when distributed lock operations fail,
 * such as timeout or acquisition failure.
 * <p>
 * 当分布式锁操作失败时抛出此异常，例如超时或获取失败。
 *
 * @author b1itz7
 * @since 1.1.0
 */
public class CacheLockException extends CacheException {
    
    /**
     * Constructs a new CacheLockException with the specified detail message.
     * <p>
     * 使用指定的详细消息构造一个新的 CacheLockException。
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     *                <p>
     *                详细消息。该消息将被保存，以便稍后通过 {@link #getMessage()} 方法检索。
     */
    public CacheLockException(String message) {
        super(message);
    }
    
    /**
     * Constructs a new CacheLockException with the specified detail message and cause.
     * <p>
     * 使用指定的详细消息和原因构造一个新的 CacheLockException。
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     *                <p>
     *                详细消息。该消息将被保存，以便稍后通过 {@link #getMessage()} 方法检索。
     * @param cause the cause (which is saved for later retrieval by the
     *              {@link #getCause()} method). (A {@code null} value is
     *              permitted, and indicates that the cause is nonexistent or
     *              unknown.)
     *              <p>
     *              原因（将被保存，以便稍后通过 {@link #getCause()} 方法检索）。
     *              （允许 {@code null} 值，表示原因不存在或未知。）
     */
    public CacheLockException(String message, Throwable cause) {
        super(message, cause);
    }
    
    /**
     * Constructs a new CacheLockException with the specified cause.
     * <p>
     * 使用指定的原因构造一个新的 CacheLockException。
     *
     * @param cause the cause (which is saved for later retrieval by the
     *              {@link #getCause()} method). (A {@code null} value is
     *              permitted, and indicates that the cause is nonexistent or
     *              unknown.)
     *              <p>
     *              原因（将被保存，以便稍后通过 {@link #getCause()} 方法检索）。
     *              （允许 {@code null} 值，表示原因不存在或未知。）
     */
    public CacheLockException(Throwable cause) {
        super(cause);
    }
}