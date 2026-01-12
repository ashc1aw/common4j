// SPDX-License-Identifier: Apache-2.0
// Copyright (c) 2026 AshClaw.

package cc.ashclaw.common4j.model;

import cc.ashclaw.common4j.enums.ResultCode;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static cc.ashclaw.common4j.constant.DateFormats.ISO_DATETIME_WITH_TIMEZONE;

/**
 * Represents a standard API response result.
 * <p>
 * 表示标准的API响应结果。
 * <p>
 * Provides a unified response structure including result code, message, data, and timestamp.
 * <p>
 * 提供统一的响应结构，包括结果码、消息、数据和时间戳。
 *
 * @author b1itz7
 * @since 1.0.0
 */
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(ISO_DATETIME_WITH_TIMEZONE);

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
     * Creates a new Result instance.
     * <p>
     * 创建一个新的Result实例。
     *
     * @param code    the result code
     * @param message the result message
     * @param data    the response data
     */
    private Result(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.timestamp = LocalDateTime.now().format(FORMATTER);
    }

    /**
     * Creates a success result with default OK code.
     * <p>
     * 使用默认的OK代码创建成功结果。
     *
     * @param <T> the data type
     * @return the success result
     */
    public static <T> Result<T> ok() {
        return new Result<>(ResultCode.OK.getCode(), ResultCode.OK.getMessage(), null);
    }

    /**
     * Creates a success result with data.
     * <p>
     * 创建带数据的成功结果。
     *
     * @param <T> the data type
     * @param data the response data
     * @return the success result with data
     */
    public static <T> Result<T> ok(T data) {
        return new Result<>(ResultCode.OK.getCode(), ResultCode.OK.getMessage(), data);
    }

    /**
     * Creates a success result with custom message.
     * <p>
     * 创建带自定义消息的成功结果。
     *
     * @param <T>     the data type
     * @param message the custom message
     * @return the success result with custom message
     */
    public static <T> Result<T> ok(String message) {
        return new Result<>(ResultCode.OK.getCode(), message, null);
    }

    /**
     * Creates a success result with data and custom message.
     * <p>
     * 创建带数据和自定义消息的成功结果。
     *
     * @param <T>     the data type
     * @param data    the response data
     * @param message the custom message
     * @return the success result with data and custom message
     */
    public static <T> Result<T> ok(T data, String message) {
        return new Result<>(ResultCode.OK.getCode(), message, data);
    }

    /**
     * Creates a created result.
     * <p>
     * 创建创建成功结果。
     *
     * @param <T> the data type
     * @return the created result
     */
    public static <T> Result<T> created() {
        return new Result<>(ResultCode.CREATED.getCode(), ResultCode.CREATED.getMessage(), null);
    }

    /**
     * Creates a created result with data.
     * <p>
     * 创建带数据的创建成功结果。
     *
     * @param <T> the data type
     * @param data the response data
     * @return the created result with data
     */
    public static <T> Result<T> created(T data) {
        return new Result<>(ResultCode.CREATED.getCode(), ResultCode.CREATED.getMessage(), data);
    }

    /**
     * Creates an updated result.
     * <p>
     * 创建更新成功结果。
     *
     * @param <T> the data type
     * @return the updated result
     */
    public static <T> Result<T> updated() {
        return new Result<>(ResultCode.UPDATED.getCode(), ResultCode.UPDATED.getMessage(), null);
    }

    /**
     * Creates an updated result with data.
     * <p>
     * 创建带数据的更新成功结果。
     *
     * @param <T> the data type
     * @param data the response data
     * @return the updated result with data
     */
    public static <T> Result<T> updated(T data) {
        return new Result<>(ResultCode.UPDATED.getCode(), ResultCode.UPDATED.getMessage(), data);
    }

    /**
     * Creates a deleted result.
     * <p>
     * 创建删除成功结果。
     *
     * @param <T> the data type
     * @return the deleted result
     */
    public static <T> Result<T> deleted() {
        return new Result<>(ResultCode.DELETED.getCode(), ResultCode.DELETED.getMessage(), null);
    }

    /**
     * Creates a no content result.
     * <p>
     * 创建无内容结果。
     *
     * @param <T> the data type
     * @return the no content result
     */
    public static <T> Result<T> noContent() {
        return new Result<>(ResultCode.NO_CONTENT.getCode(), ResultCode.NO_CONTENT.getMessage(), null);
    }

    /**
     * Creates an error result with custom code and message.
     * <p>
     * 创建带自定义代码和消息的错误结果。
     *
     * @param <T>     the data type
     * @param code    the error code
     * @param message the error message
     * @return the error result
     */
    public static <T> Result<T> error(int code, String message) {
        return new Result<>(code, message, null);
    }

    /**
     * Creates an error result with result code.
     * <p>
     * 使用结果码创建错误结果。
     *
     * @param <T>        the data type
     * @param resultCode the result code
     * @return the error result
     */
    public static <T> Result<T> error(ResultCode resultCode) {
        return new Result<>(resultCode.getCode(), resultCode.getMessage(), null);
    }

    /**
     * Creates an error result with result code and custom message.
     * <p>
     * 使用结果码和自定义消息创建错误结果。
     *
     * @param <T>        the data type
     * @param resultCode the result code
     * @param message    the custom error message
     * @return the error result
     */
    public static <T> Result<T> error(ResultCode resultCode, String message) {
        return new Result<>(resultCode.getCode(), message, null);
    }

    /**
     * Gets the result code.
     * <p>
     * 获取结果码。
     *
     * @return the result code
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
     */
    public String getTimestamp() {
        return timestamp;
    }

    /**
     * Checks if the result is success.
     * <p>
     * 检查结果是否成功。
     *
     * @return true if success, false otherwise
     */
    public boolean isSuccess() {
        return code >= 200 && code < 300;
    }

    /**
     * Checks if the result is error.
     * <p>
     * 检查结果是否错误。
     *
     * @return true if error, false otherwise
     */
    public boolean isError() {
        return !isSuccess();
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }
}