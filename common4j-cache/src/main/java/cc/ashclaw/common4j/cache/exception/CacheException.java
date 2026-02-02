package cc.ashclaw.common4j.cache.exception;

/**
 * Base exception class for cache operations.
 * <p>
 * 缓存操作的基础异常类。
 * <p>
 * All cache-related exceptions should extend this class
 * to maintain a consistent exception hierarchy.
 * <p>
 * 所有与缓存相关的异常都应继承此类，以保持一致的异常层次结构。
 *
 * @author b1itz7
 * @since 1.1.0
 */
public class CacheException extends RuntimeException {
    
    /**
     * Constructs a new CacheException with the specified detail message.
     * <p>
     * 使用指定的详细消息构造一个新的 CacheException。
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     *                <p>
     *                详细消息。该消息将被保存，以便稍后通过 {@link #getMessage()} 方法检索。
     */
    public CacheException(String message) {
        super(message);
    }
    
    /**
     * Constructs a new CacheException with the specified detail message and cause.
     * <p>
     * 使用指定的详细消息和原因构造一个新的 CacheException。
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
    public CacheException(String message, Throwable cause) {
        super(message, cause);
    }
    
    /**
     * Constructs a new CacheException with the specified cause.
     * <p>
     * 使用指定的原因构造一个新的 CacheException。
     *
     * @param cause the cause (which is saved for later retrieval by the
     *              {@link #getCause()} method). (A {@code null} value is
     *              permitted, and indicates that the cause is nonexistent or
     *              unknown.)
     *              <p>
     *              原因（将被保存，以便稍后通过 {@link #getCause()} 方法检索）。
     *              （允许 {@code null} 值，表示原因不存在或未知。）
     */
    public CacheException(Throwable cause) {
        super(cause);
    }
}