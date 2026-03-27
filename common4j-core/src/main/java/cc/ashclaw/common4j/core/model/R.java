// SPDX-License-Identifier: Apache-2.0
// Copyright (c) 2026 AshClaw.

package cc.ashclaw.common4j.core.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import cc.ashclaw.common4j.core.date.DateTimeUtil;
import cc.ashclaw.common4j.core.enums.ResultCode;

/**
 * Represents a standard API response result.
 * <p>
 * 表示标准的 API 响应结果。
 * <p>
 * Provides a unified response structure including result code, message, data, and timestamp.
 * <p>
 * 提供统一的响应结构，包括结果码、消息、数据和时间戳。
 *
 * @author b1itz7
 * @since 1.0.0
 */
public class R<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Result code.
     * <p>
     * 结果码。
     */
    private final int code;

    /**
     * Result message.
     * <p>
     * 结果消息。
     */
    private final String message;

    /**
     * Response data.
     * <p>
     * 响应数据。
     */
    private final T data;

    /**
     * Response timestamp.
     * <p>
     * 响应时间戳。
     */
    private final String timestamp;

    /**
     * Constructs a new R instance with the specified code, message, and data.
     * <p>
     * 使用指定的码、消息和数据构造一个新的 R 实例。
     *
     * @param code    the result code
     *                <p>
     *                结果码
     * @param message the result message
     *                <p>
     *                结果消息
     * @param data    the response data
     *                <p>
     *                响应数据
     */
    private R(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.timestamp = DateTimeUtil.format(LocalDateTime.now());
    }

    /**
     * Creates a success result with default OK code and no data.
     * <p>
     * 使用默认的 OK 码创建成功结果，无数据。
     *
     * @param <T> the data type
     *            <p>
     *            数据类型
     * @return the success result
     * <p>
     * 成功结果
     */
    public static <T> R<T> ok() {
        return new R<>(ResultCode.OK.getCode(), ResultCode.OK.getMessage(), null);
    }

    /**
     * Creates a success result with data.
     * <p>
     * 创建带数据的成功结果。
     *
     * @param <T>  the data type
     *             <p>
     *             数据类型
     * @param data the response data
     *             <p>
     *             响应数据
     * @return the success result with data
     * <p>
     * 带数据的成功结果
     */
    public static <T> R<T> ok(T data) {
        return new R<>(ResultCode.OK.getCode(), ResultCode.OK.getMessage(), data);
    }

    /**
     * Creates a success result with custom message and no data.
     * <p>
     * 创建带自定义消息的成功结果，无数据。
     *
     * @param <T>     the data type
     *                <p>
     *                数据类型
     * @param message the custom message
     *                <p>
     *                自定义消息
     * @return the success result with message
     * <p>
     * 带消息的成功结果
     */
    public static <T> R<T> ok(String message) {
        return new R<>(ResultCode.OK.getCode(), message, null);
    }

    /**
     * Creates a success result with data and custom message.
     * <p>
     * 创建带数据和自定义消息的成功结果。
     *
     * @param <T>     the data type
     *                <p>
     *                数据类型
     * @param data    the response data
     *                <p>
     *                响应数据
     * @param message the custom message
     *                <p>
     *                自定义消息
     * @return the success result with data and message
     * <p>
     * 带数据和消息的成功结果
     */
    public static <T> R<T> ok(T data, String message) {
        return new R<>(ResultCode.OK.getCode(), message, data);
    }

    /**
     * Creates an error result with default INTERNAL_SERVER_ERROR code and message.
     * <p>
     * 使用默认的 INTERNAL_SERVER_ERROR 码和消息创建错误结果。
     *
     * @param <T> the data type
     *            <p>
     *            数据类型
     * @return the error result
     *         <p>
     *         错误结果
     */
    public static <T> R<T> fail() {
        return new R<>(ResultCode.INTERNAL_SERVER_ERROR.getCode(), ResultCode.INTERNAL_SERVER_ERROR.getMessage(), null);
    }

    /**
     * Creates an error result with default INTERNAL_SERVER_ERROR code.
     * <p>
     * 使用默认的 INTERNAL_SERVER_ERROR 码创建错误结果。
     *
     * @param <T>     the data type
     *                <p>
     *                数据类型
     * @param message the error message
     *                <p>
     *                错误消息
     * @return the error result
     * <p>
     * 错误结果
     */
    public static <T> R<T> fail(String message) {
        return new R<>(ResultCode.INTERNAL_SERVER_ERROR.getCode(), message, null);
    }

    /**
     * Creates an error result with custom code and message.
     * <p>
     * 使用自定义码和消息创建错误结果。
     *
     * @param <T>     the data type
     *                <p>
     *                数据类型
     * @param code    the error code
     *                <p>
     *                错误码
     * @param message the error message
     *                <p>
     *                错误消息
     * @return the error result
     * <p>
     * 错误结果
     */
    public static <T> R<T> fail(int code, String message) {
        return new R<>(code, message, null);
    }

    /**
     * Creates an error result with ResultCode and custom message.
     * <p>
     * 使用 ResultCode 和自定义消息创建错误结果。
     *
     * @param <T>        the data type
     *                   <p>
     *                   数据类型
     * @param resultCode the result code
     *                   <p>
     *                   结果码
     * @param message    the error message
     *                   <p>
     *                   错误消息
     * @return the error result
     * <p>
     * 错误结果
     */
    public static <T> R<T> fail(ResultCode resultCode, String message) {
        return new R<>(resultCode.getCode(), message, null);
    }

    /**
     * Creates an error result with custom code, message and data.
     * <p>
     * 使用自定义码、消息和数据创建错误结果。
     *
     * @param <T>     the data type
     *                <p>
     *                数据类型
     * @param code    the error code
     *                <p>
     *                错误码
     * @param message the error message
     *                <p>
     *                错误消息
     * @param data    the response data
     *                <p>
     *                响应数据
     * @return the error result with data
     * <p>
     * 带数据的错误结果
     */
    public static <T> R<T> fail(int code, String message, T data) {
        return new R<>(code, message, data);
    }

    /**
     * Creates an error result with ResultCode, custom message and data.
     * <p>
     * 使用 ResultCode、自定义消息和数据创建错误结果。
     *
     * @param <T>        the data type
     *                   <p>
     *                   数据类型
     * @param resultCode the result code
     *                   <p>
     *                   结果码
     * @param message    the error message
     *                   <p>
     *                   错误消息
     * @param data       the response data
     *                   <p>
     *                   响应数据
     * @return the error result with data
     * <p>
     * 带数据的错误结果
     */
    public static <T> R<T> fail(ResultCode resultCode, String message, T data) {
        return new R<>(resultCode.getCode(), message, data);
    }

    /**
     * Gets the result code.
     * <p>
     * 获取结果码。
     *
     * @return the result code
     * <p>
     * 结果码
     */
    public int getCode() {
        return code;
    }

    /**
     * Gets the result message.
     * <p>
     * 获取结果消息。
     *
     * @return the result message
     * <p>
     * 结果消息
     */
    public String getMessage() {
        return message;
    }

    /**
     * Gets the response data.
     * <p>
     * 获取响应数据。
     *
     * @return the response data
     * <p>
     * 响应数据
     */
    public T getData() {
        return data;
    }

    /**
     * Gets the response timestamp.
     * <p>
     * 获取响应时间戳。
     *
     * @return the response timestamp
     * <p>
     * 响应时间戳
     */
    public String getTimestamp() {
        return timestamp;
    }

    /**
     * Checks if the result is success.
     * <p>
     * 检查结果是否成功。
     *
     * @return {@code true} if success (2xx code), {@code false} otherwise
     * <p>
     * 如果成功（2xx 码）返回 {@code true}，否则返回 {@code false}
     */
    public boolean success() {
        return code >= 200 && code < 300;
    }

    /**
     * Checks if the result is error.
     * <p>
     * 检查结果是否错误。
     *
     * @return {@code true} if error, {@code false} otherwise
     * <p>
     * 如果错误返回 {@code true}，否则返回 {@code false}
     */
    public boolean error() {
        return !success();
    }

    @Override
    public String toString() {
        return "R{"
                + "code=" + code
                + ", message='" + message + '\''
                + ", data=" + data
                + ", timestamp='" + timestamp + '\''
                + '}';
    }
}