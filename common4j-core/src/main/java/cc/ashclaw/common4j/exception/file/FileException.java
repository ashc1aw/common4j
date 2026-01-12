// SPDX-License-Identifier: Apache-2.0
// Copyright (c) 2026 AshClaw.

package cc.ashclaw.common4j.exception.file;

import cc.ashclaw.common4j.exception.BaseException;

/**
 * Base exception class for file operations in the Common4J library.
 * <p>
 * Common4J 库中文件操作的基础异常类。
 * <p>
 * All custom exceptions related to file operations should extend this class
 * to maintain a consistent exception hierarchy.
 * <p>
 * 所有与文件操作相关的自定义异常都应继承此类，以保持一致的异常层次结构。
 *
 * @author b1itz7
 * @since 1.0.0
 */
public class FileException extends BaseException {

    /**
     * Constructs a new FileException with {@code null} as its detail message.
     * <p>
     * 使用 {@code null} 作为详细消息构造一个新的 FileException。
     */
    public FileException() {
        super();
    }

    /**
     * Constructs a new FileException with the specified detail message.
     * <p>
     * 使用指定的详细消息构造一个新的 FileException。
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     *                <p>
     *                详细消息。该消息将被保存，以便稍后通过 {@link #getMessage()} 方法检索。
     */
    public FileException(String message) {
        super(message);
    }

    /**
     * Constructs a new FileException with the specified detail message and cause.
     * <p>
     * 使用指定的详细消息和原因构造一个新的 FileException。
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
    public FileException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new FileException with the specified cause and a
     * detail message of {@code (cause==null ? null : cause.toString())}.
     * <p>
     * 使用指定的原因和详细消息 {@code (cause==null ? null : cause.toString())} 构造一个新的 FileException。
     *
     * @param cause the cause (which is saved for later retrieval by the
     *              {@link #getCause()} method). (A {@code null} value is
     *              permitted, and indicates that the cause is nonexistent or unknown.)
     *              <p>
     *              原因（将被保存，以便稍后通过 {@link #getCause()} 方法检索）。
     *              允许 {@code null} 值，表示原因不存在或未知。
     */
    public FileException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructs a new FileException with the specified detail message,
     * cause, suppression enabled or disabled, and writable stack trace enabled or disabled.
     * <p>
     * 使用指定的详细消息、原因、是否启用抑制以及是否启用可写堆栈跟踪构造一个新的 FileException。
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
    protected FileException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}