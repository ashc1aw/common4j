// SPDX-License-Identifier: Apache-2.0
// Copyright (c) 2026 AshClaw.

package cc.ashclaw.common4j.core.exception;

/**
 * Server-Sent Events (SSE) exception class for the Common4J library.
 * <p>
 * Common4J 库的服务器发送事件（SSE）异常类。
 * <p>
 * This exception is used to represent errors related to SSE operations,
 * such as connection issues, data format errors, or protocol violations.
 * <p>
 * 此类用于表示与SSE操作相关的错误，例如连接问题、数据格式错误或协议违规。
 *
 * @author b1itz7
 * @since 1.1.1
 */
public class SseException extends BaseException {

    /**
     * Error code for the SSE exception.
     * <p>
     * SSE异常的错误代码。
     */
    private Integer code;

    /**
     * Error message for the SSE exception.
     * <p>
     * SSE异常的错误消息。
     */
    private String message;

    /**
     * Detailed error message providing additional context.
     * <p>
     * 提供额外上下文的详细错误消息。
     */
    private String detailMessage;

    /**
     * Constructs a new SseException with {@code null} as its detail message.
     * <p>
     * 使用 {@code null} 作为详细消息构造一个新的 SseException。
     */
    public SseException() {
        super();
    }

    /**
     * Constructs a new SseException with the specified detail message.
     * <p>
     * 使用指定的详细消息构造一个新的 SseException。
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     *                <p>
     *                详细消息。该消息将被保存，以便稍后通过 {@link #getMessage()} 方法检索。
     */
    public SseException(String message) {
        super(message);
        this.message = message;
    }

    /**
     * Constructs a new SseException with the specified error code and message.
     * <p>
     * 使用指定的错误代码和消息构造一个新的 SseException。
     *
     * @param code    the error code for the SSE exception.
     *                <p>
     *                SSE异常的错误代码。
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     *                <p>
     *                详细消息。该消息将被保存，以便稍后通过 {@link #getMessage()} 方法检索。
     */
    public SseException(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    /**
     * Constructs a new SseException with the specified error code, message, and detail message.
     * <p>
     * 使用指定的错误代码、消息和详细消息构造一个新的 SseException。
     *
     * @param code          the error code for the SSE exception.
     *                      <p>
     *                      SSE异常的错误代码。
     * @param message       the detail message. The detail message is saved for
     *                      later retrieval by the {@link #getMessage()} method.
     *                      <p>
     *                      详细消息。该消息将被保存，以便稍后通过 {@link #getMessage()} 方法检索。
     * @param detailMessage the detailed error message providing additional context.
     *                      <p>
     *                      提供额外上下文的详细错误消息。
     */
    public SseException(Integer code, String message, String detailMessage) {
        super(message);
        this.code = code;
        this.message = message;
        this.detailMessage = detailMessage;
    }

    /**
     * Constructs a new SseException with the specified detail message and cause.
     * <p>
     * 使用指定的详细消息和原因构造一个新的 SseException。
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     *                <p>
     *                详细消息。该消息将被保存，以便稍后通过 {@link #getMessage()} 方法检索。
     * @param cause   the cause (which is saved for later retrieval by the
     *                {@link #getCause()} method). (A {@code null} value is
     *                permitted, and indicates that the cause is nonexistent or unknown.)
     *                <p>
     *                原因（将被保存，以便稍后通过 {@link #getCause()} 方法检索）。
     *                允许 {@code null} 值，表示原因不存在或未知。
     */
    public SseException(String message, Throwable cause) {
        super(message, cause);
        this.message = message;
    }

    /**
     * Constructs a new SseException with the specified error code, message, and cause.
     * <p>
     * 使用指定的错误代码、消息和原因构造一个新的 SseException。
     *
     * @param code    the error code for the SSE exception.
     *                <p>
     *                SSE异常的错误代码。
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     *                <p>
     *                详细消息。该消息将被保存，以便稍后通过 {@link #getMessage()} 方法检索。
     * @param cause   the cause (which is saved for later retrieval by the
     *                {@link #getCause()} method). (A {@code null} value is
     *                permitted, and indicates that the cause is nonexistent or unknown.)
     *                <p>
     *                原因（将被保存，以便稍后通过 {@link #getCause()} 方法检索）。
     *                允许 {@code null} 值，表示原因不存在或未知。
     */
    public SseException(Integer code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
        this.message = message;
    }

    /**
     * Constructs a new SseException with the specified error code, message, detail message, and cause.
     * <p>
     * 使用指定的错误代码、消息、详细消息和原因构造一个新的 SseException。
     *
     * @param code          the error code for the SSE exception.
     *                      <p>
     *                      SSE异常的错误代码。
     * @param message       the detail message. The detail message is saved for
     *                      later retrieval by the {@link #getMessage()} method.
     *                      <p>
     *                      详细消息。该消息将被保存，以便稍后通过 {@link #getMessage()} 方法检索。
     * @param detailMessage the detailed error message providing additional context.
     *                      <p>
     *                      提供额外上下文的详细错误消息。
     * @param cause         the cause (which is saved for later retrieval by the
     *                      {@link #getCause()} method). (A {@code null} value is
     *                      permitted, and indicates that the cause is nonexistent or unknown.)
     *                      <p>
     *                      原因（将被保存，以便稍后通过 {@link #getCause()} 方法检索）。
     *                      允许 {@code null} 值，表示原因不存在或未知。
     */
    public SseException(Integer code, String message, String detailMessage, Throwable cause) {
        super(message, cause);
        this.code = code;
        this.message = message;
        this.detailMessage = detailMessage;
    }

    /**
     * Constructs a new SseException with the specified cause and a
     * detail message of {@code (cause==null ? null : cause.toString())}.
     * <p>
     * 使用指定的原因和详细消息 {@code (cause==null ? null : cause.toString())} 构造一个新的 SseException。
     *
     * @param cause the cause (which is saved for later retrieval by the
     *              {@link #getCause()} method). (A {@code null} value is
     *              permitted, and indicates that the cause is nonexistent or unknown.)
     *              <p>
     *              原因（将被保存，以便稍后通过 {@link #getCause()} 方法检索）。
     *              允许 {@code null} 值，表示原因不存在或未知。
     */
    public SseException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructs a new SseException with the specified detail message,
     * cause, suppression enabled or disabled, and writable stack trace enabled or disabled.
     * <p>
     * 使用指定的详细消息、原因、是否启用抑制以及是否启用可写堆栈跟踪构造一个新的 SseException。
     *
     * @param message            the detail message.
     *                           <p>
     *                           详细消息。
     * @param cause              the cause.
     *                           <p>
     *                           原因。
     * @param enableSuppression  whether or not suppression is enabled
     *                           or disabled.
     *                           <p>
     *                           是否启用抑制。
     * @param writableStackTrace whether or not the stack trace should
     *                           be writable.
     *                           <p>
     *                           堆栈跟踪是否应该可写。
     */
    protected SseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.message = message;
    }

    /**
     * Returns the error code for this SSE exception.
     * <p>
     * 返回此SSE异常的错误代码。
     *
     * @return the error code, or {@code null} if no code was set
     *         <p>
     *         错误代码，如果未设置代码则返回 {@code null}
     */
    public Integer getCode() {
        return code;
    }

    /**
     * Sets the error code for this SSE exception.
     * <p>
     * 设置此SSE异常的错误代码。
     *
     * @param code the error code to set
     *             <p>
     *             要设置的错误代码
     */
    public void setCode(Integer code) {
        this.code = code;
    }

    /**
     * Returns the error message for this SSE exception.
     * <p>
     * 返回此SSE异常的错误消息。
     *
     * @return the error message, or {@code null} if no message was set
     *         <p>
     *         错误消息，如果未设置消息则返回 {@code null}
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the error message for this SSE exception.
     * <p>
     * 设置此SSE异常的错误消息。
     *
     * @param message the error message to set
     *                <p>
     *                要设置的错误消息
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Returns the detailed error message for this SSE exception.
     * <p>
     * 返回此SSE异常的详细错误消息。
     *
     * @return the detailed error message, or {@code null} if no detail message was set
     *         <p>
     *         详细错误消息，如果未设置详细消息则返回 {@code null}
     */
    public String getDetailMessage() {
        return detailMessage;
    }

    /**
     * Sets the detailed error message for this SSE exception.
     * <p>
     * 设置此SSE异常的详细错误消息。
     *
     * @param detailMessage the detailed error message to set
     *                      <p>
     *                      要设置的详细错误消息
     */
    public void setDetailMessage(String detailMessage) {
        this.detailMessage = detailMessage;
    }
}