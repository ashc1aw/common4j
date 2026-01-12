// SPDX-License-Identifier: Apache-2.0
// Copyright (c) 2026 AshClaw.

package cc.ashclaw.common4j.enums;

/**
 * Provides common business result codes and their descriptions.
 * <p>
 * 提供常用的业务结果码及其描述。
 * <p>
 * Includes result codes for success, client errors, server errors, and business-specific errors.
 * <p>
 * 包含成功、客户端错误、服务器错误和业务特定错误的结果码。
 *
 * @author b1itz7
 * @since 1.0.0
 */
public enum ResultCode {

    
    /**
     * 200 OK
     * <p>
     * 200 成功
     */
    OK(200, "OK"),

    /**
     * 201 Created
     * <p>
     * 201 创建成功
     */
    CREATED(201, "Created"),

    /**
     * 200 Updated
     * <p>
     * 200 更新成功
     */
    UPDATED(200, "Updated"),

    /**
     * 200 Deleted
     * <p>
     * 200 删除成功
     */
    DELETED(200, "Deleted"),

    /**
     * 204 No Content
     * <p>
     * 204 无内容
     */
    NO_CONTENT(204, "No Content"),

    /**
     * 207 Batch Processed
     * <p>
     * 207 批量处理完成
     */
    BATCH_PROCESSED(207, "Batch Processed"),

    
    /**
     * 400 Bad Request
     * <p>
     * 400 请求错误
     */
    BAD_REQUEST(400, "Bad Request"),

    /**
     * 400 Invalid Parameters
     * <p>
     * 400 参数无效
     */
    INVALID_PARAMETERS(400, "Invalid Parameters"),

    /**
     * 400 Missing Required Parameters
     * <p>
     * 400 缺少必填参数
     */
    MISSING_REQUIRED_PARAMETERS(400, "Missing Required Parameters"),

    /**
     * 400 Invalid Format
     * <p>
     * 400 格式无效
     */
    INVALID_FORMAT(400, "Invalid Format"),

    /**
     * 400 Out of Range
     * <p>
     * 400 超出范围
     */
    OUT_OF_RANGE(400, "Out of Range"),

    /**
     * 409 Duplicate Data
     * <p>
     * 409 数据重复
     */
    DUPLICATE_DATA(409, "Duplicate Data"),

    /**
     * 404 Resource Not Found
     * <p>
     * 404 资源不存在
     */
    RESOURCE_NOT_FOUND(404, "Resource Not Found"),

    /**
     * 405 Operation Not Allowed
     * <p>
     * 405 操作不允许
     */
    OPERATION_NOT_ALLOWED(405, "Operation Not Allowed"),

    
    /**
     * 401 Unauthorized
     * <p>
     * 401 未授权
     */
    UNAUTHORIZED(401, "Unauthorized"),

    /**
     * 401 Invalid Token
     * <p>
     * 401 令牌无效
     */
    INVALID_TOKEN(401, "Invalid Token"),

    /**
     * 401 Expired Token
     * <p>
     * 401 令牌过期
     */
    EXPIRED_TOKEN(401, "Expired Token"),

    /**
     * 401 Token Required
     * <p>
     * 401 需要令牌
     */
    TOKEN_REQUIRED(401, "Token Required"),

    /**
     * 401 Invalid Credentials
     * <p>
     * 401 凭证无效
     */
    INVALID_CREDENTIALS(401, "Invalid Credentials"),

    /**
     * 401 Account Locked
     * <p>
     * 401 账户锁定
     */
    ACCOUNT_LOCKED(401, "Account Locked"),

    /**
     * 401 Account Disabled
     * <p>
     * 401 账户禁用
     */
    ACCOUNT_DISABLED(401, "Account Disabled"),

    /**
     * 403 Forbidden
     * <p>
     * 403 禁止访问
     */
    FORBIDDEN(403, "Forbidden"),

    /**
     * 403 Insufficient Permissions
     * <p>
     * 403 权限不足
     */
    INSUFFICIENT_PERMISSIONS(403, "Insufficient Permissions"),

    /**
     * 403 Access Denied
     * <p>
     * 403 访问拒绝
     */
    ACCESS_DENIED(403, "Access Denied"),

    
    /**
     * 500 Internal Server Error
     * <p>
     * 500 内部服务器错误
     */
    INTERNAL_SERVER_ERROR(500, "Internal Server Error"),

    /**
     * 500 Database Error
     * <p>
     * 500 数据库错误
     */
    DATABASE_ERROR(500, "Database Error"),

    /**
     * 503 Service Unavailable
     * <p>
     * 503 服务不可用
     */
    SERVICE_UNAVAILABLE(503, "Service Unavailable"),

    /**
     * 500 Network Error
     * <p>
     * 500 网络错误
     */
    NETWORK_ERROR(500, "Network Error"),

    /**
     * 504 Timeout
     * <p>
     * 504 超时
     */
    TIMEOUT(504, "Timeout"),

    /**
     * 502 Third Party Service Error
     * <p>
     * 502 第三方服务错误
     */
    THIRD_PARTY_SERVICE_ERROR(502, "Third Party Service Error"),

    /**
     * 503 System Busy
     * <p>
     * 503 系统繁忙
     */
    SYSTEM_BUSY(503, "System Busy"),

    /**
     * 500 Unknown Error
     * <p>
     * 500 未知错误
     */
    UNKNOWN_ERROR(500, "Unknown Error"),

    
    /**
     * 422 Business Error
     * <p>
     * 422 业务错误
     */
    BUSINESS_ERROR(422, "Business Error"),

    /**
     * 400 Validation Error
     * <p>
     * 400 验证错误
     */
    VALIDATION_ERROR(400, "Validation Error"),

    /**
     * 422 Data Processing Error
     * <p>
     * 422 数据处理错误
     */
    DATA_PROCESSING_ERROR(422, "Data Processing Error"),

    /**
     * 422 Business Rule Violation
     * <p>
     * 422 违反业务规则
     */
    BUSINESS_RULE_VIOLATION(422, "Business Rule Violation"),

    /**
     * 429 Resource Exhausted
     * <p>
     * 429 资源耗尽
     */
    RESOURCE_EXHAUSTED(429, "Resource Exhausted"),

    /**
     * 429 Quota Exceeded
     * <p>
     * 429 超出配额
     */
    QUOTA_EXCEEDED(429, "Quota Exceeded"),

    /**
     * 422 Operation Failed
     * <p>
     * 422 操作失败
     */
    OPERATION_FAILED(422, "Operation Failed"),

    /**
     * 501 Not Supported
     * <p>
     * 501 不支持
     */
    NOT_SUPPORTED(501, "Not Supported");

    private final int code;
    private final String message;

    /**
     * Creates a new result code.
     * <p>
     * 创建一个新的结果码。
     *
     * @param code    the result code
     * @param message the result message
     */
    ResultCode(int code, String message) {
        this.code = code;
        this.message = message;
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
     * Finds a result code by its code.
     * <p>
     * 根据结果码查找结果。
     *
     * @param code the result code
     * @return the corresponding result code, or null if not found
     */
    public static ResultCode valueOf(int code) {
        for (ResultCode resultCode : values()) {
            if (resultCode.code == code) {
                return resultCode;
            }
        }
        return null;
    }
}
