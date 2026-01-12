// SPDX-License-Identifier: Apache-2.0
// Copyright (c) 2026 AshClaw.

package cc.ashclaw.common4j.enums;

/**
 * Provides standard HTTP status codes and their descriptions.
 * <p>
 * 提供标准的HTTP状态码及其描述。
 * <p>
 * Includes status codes from RFC 7231 and other relevant specifications.
 * <p>
 * 包含来自RFC 7231和其他相关规范的状态码。
 *
 * @author b1itz7
 * @since 1.0.0
 */
public enum HttpStatus {

    

    /**
     * 100 Continue
     * <p>
     * 100 继续
     */
    CONTINUE(100, "Continue"),

    /**
     * 101 Switching Protocols
     * <p>
     * 101 切换协议
     */
    SWITCHING_PROTOCOLS(101, "Switching Protocols"),

    /**
     * 102 Processing
     * <p>
     * 102 处理中
     */
    PROCESSING(102, "Processing"),

    /**
     * 103 Early Hints
     * <p>
     * 103 提前提示
     */
    EARLY_HINTS(103, "Early Hints"),

    

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
     * 202 Accepted
     * <p>
     * 202 已接受
     */
    ACCEPTED(202, "Accepted"),

    /**
     * 203 Non-Authoritative Information
     * <p>
     * 203 非权威信息
     */
    NON_AUTHORITATIVE_INFORMATION(203, "Non-Authoritative Information"),

    /**
     * 204 No Content
     * <p>
     * 204 无内容
     */
    NO_CONTENT(204, "No Content"),

    /**
     * 205 Reset Content
     * <p>
     * 205 重置内容
     */
    RESET_CONTENT(205, "Reset Content"),

    /**
     * 206 Partial Content
     * <p>
     * 206 部分内容
     */
    PARTIAL_CONTENT(206, "Partial Content"),

    /**
     * 207 Multi-Status
     * <p>
     * 207 多状态
     */
    MULTI_STATUS(207, "Multi-Status"),

    /**
     * 208 Already Reported
     * <p>
     * 208 已报告
     */
    ALREADY_REPORTED(208, "Already Reported"),

    /**
     * 226 IM Used
     * <p>
     * 226 IM已使用
     */
    IM_USED(226, "IM Used"),

    

    /**
     * 300 Multiple Choices
     * <p>
     * 300 多种选择
     */
    MULTIPLE_CHOICES(300, "Multiple Choices"),

    /**
     * 301 Moved Permanently
     * <p>
     * 301 永久移动
     */
    MOVED_PERMANENTLY(301, "Moved Permanently"),

    /**
     * 302 Found
     * <p>
     * 302 临时移动
     */
    FOUND(302, "Found"),

    /**
     * 303 See Other
     * <p>
     * 303 查看其他
     */
    SEE_OTHER(303, "See Other"),

    /**
     * 304 Not Modified
     * <p>
     * 304 未修改
     */
    NOT_MODIFIED(304, "Not Modified"),

    /**
     * 305 Use Proxy
     * <p>
     * 305 使用代理
     */
    USE_PROXY(305, "Use Proxy"),

    /**
     * 307 Temporary Redirect
     * <p>
     * 307 临时重定向
     */
    TEMPORARY_REDIRECT(307, "Temporary Redirect"),

    /**
     * 308 Permanent Redirect
     * <p>
     * 308 永久重定向
     */
    PERMANENT_REDIRECT(308, "Permanent Redirect"),

    

    /**
     * 400 Bad Request
     * <p>
     * 400 错误请求
     */
    BAD_REQUEST(400, "Bad Request"),

    /**
     * 401 Unauthorized
     * <p>
     * 401 未授权
     */
    UNAUTHORIZED(401, "Unauthorized"),

    /**
     * 402 Payment Required
     * <p>
     * 402 需要付款
     */
    PAYMENT_REQUIRED(402, "Payment Required"),

    /**
     * 403 Forbidden
     * <p>
     * 403 禁止访问
     */
    FORBIDDEN(403, "Forbidden"),

    /**
     * 404 Not Found
     * <p>
     * 404 未找到
     */
    NOT_FOUND(404, "Not Found"),

    /**
     * 405 Method Not Allowed
     * <p>
     * 405 方法不允许
     */
    METHOD_NOT_ALLOWED(405, "Method Not Allowed"),

    /**
     * 406 Not Acceptable
     * <p>
     * 406 不可接受
     */
    NOT_ACCEPTABLE(406, "Not Acceptable"),

    /**
     * 407 Proxy Authentication Required
     * <p>
     * 407 需要代理认证
     */
    PROXY_AUTHENTICATION_REQUIRED(407, "Proxy Authentication Required"),

    /**
     * 408 Request Timeout
     * <p>
     * 408 请求超时
     */
    REQUEST_TIMEOUT(408, "Request Timeout"),

    /**
     * 409 Conflict
     * <p>
     * 409 冲突
     */
    CONFLICT(409, "Conflict"),

    /**
     * 410 Gone
     * <p>
     * 410 已删除
     */
    GONE(410, "Gone"),

    /**
     * 411 Length Required
     * <p>
     * 411 需要长度
     */
    LENGTH_REQUIRED(411, "Length Required"),

    /**
     * 412 Precondition Failed
     * <p>
     * 412 前置条件失败
     */
    PRECONDITION_FAILED(412, "Precondition Failed"),

    /**
     * 413 Payload Too Large
     * <p>
     * 413 请求体过大
     */
    PAYLOAD_TOO_LARGE(413, "Payload Too Large"),

    /**
     * 414 URI Too Long
     * <p>
     * 414 URI过长
     */
    URI_TOO_LONG(414, "URI Too Long"),

    /**
     * 415 Unsupported Media Type
     * <p>
     * 415 不支持的媒体类型
     */
    UNSUPPORTED_MEDIA_TYPE(415, "Unsupported Media Type"),

    /**
     * 416 Range Not Satisfiable
     * <p>
     * 416 范围不可满足
     */
    RANGE_NOT_SATISFIABLE(416, "Range Not Satisfiable"),

    /**
     * 417 Expectation Failed
     * <p>
     * 417 期望失败
     */
    EXPECTATION_FAILED(417, "Expectation Failed"),

    /**
     * 418 I'm a teapot
     * <p>
     * 418 我是一个茶壶
     */
    IM_A_TEAPOT(418, "I'm a teapot"),

    /**
     * 421 Misdirected Request
     * <p>
     * 421 请求被重定向
     */
    MISDIRECTED_REQUEST(421, "Misdirected Request"),

    /**
     * 422 Unprocessable Entity
     * <p>
     * 422 无法处理的实体
     */
    UNPROCESSABLE_ENTITY(422, "Unprocessable Entity"),

    /**
     * 423 Locked
     * <p>
     * 423 已锁定
     */
    LOCKED(423, "Locked"),

    /**
     * 424 Failed Dependency
     * <p>
     * 424 依赖失败
     */
    FAILED_DEPENDENCY(424, "Failed Dependency"),

    /**
     * 425 Too Early
     * <p>
     * 425 过早
     */
    TOO_EARLY(425, "Too Early"),

    /**
     * 426 Upgrade Required
     * <p>
     * 426 需要升级
     */
    UPGRADE_REQUIRED(426, "Upgrade Required"),

    /**
     * 428 Precondition Required
     * <p>
     * 428 需要前置条件
     */
    PRECONDITION_REQUIRED(428, "Precondition Required"),

    /**
     * 429 Too Many Requests
     * <p>
     * 429 请求过多
     */
    TOO_MANY_REQUESTS(429, "Too Many Requests"),

    /**
     * 431 Request Header Fields Too Large
     * <p>
     * 431 请求头字段过大
     */
    REQUEST_HEADER_FIELDS_TOO_LARGE(431, "Request Header Fields Too Large"),

    /**
     * 451 Unavailable For Legal Reasons
     * <p>
     * 451 因法律原因不可用
     */
    UNAVAILABLE_FOR_LEGAL_REASONS(451, "Unavailable For Legal Reasons"),

    

    /**
     * 500 Internal Server Error
     * <p>
     * 500 内部服务器错误
     */
    INTERNAL_SERVER_ERROR(500, "Internal Server Error"),

    /**
     * 501 Not Implemented
     * <p>
     * 501 未实现
     */
    NOT_IMPLEMENTED(501, "Not Implemented"),

    /**
     * 502 Bad Gateway
     * <p>
     * 502 错误网关
     */
    BAD_GATEWAY(502, "Bad Gateway"),

    /**
     * 503 Service Unavailable
     * <p>
     * 503 服务不可用
     */
    SERVICE_UNAVAILABLE(503, "Service Unavailable"),

    /**
     * 504 Gateway Timeout
     * <p>
     * 504 网关超时
     */
    GATEWAY_TIMEOUT(504, "Gateway Timeout"),

    /**
     * 505 HTTP Version Not Supported
     * <p>
     * 505 HTTP版本不支持
     */
    HTTP_VERSION_NOT_SUPPORTED(505, "HTTP Version Not Supported"),

    /**
     * 506 Variant Also Negotiates
     * <p>
     * 506 变体也进行协商
     */
    VARIANT_ALSO_NEGOTIATES(506, "Variant Also Negotiates"),

    /**
     * 507 Insufficient Storage
     * <p>
     * 507 存储空间不足
     */
    INSUFFICIENT_STORAGE(507, "Insufficient Storage"),

    /**
     * 508 Loop Detected
     * <p>
     * 508 检测到循环
     */
    LOOP_DETECTED(508, "Loop Detected"),

    /**
     * 510 Not Extended
     * <p>
     * 510 未扩展
     */
    NOT_EXTENDED(510, "Not Extended"),

    /**
     * 511 Network Authentication Required
     * <p>
     * 511 需要网络认证
     */
    NETWORK_AUTHENTICATION_REQUIRED(511, "Network Authentication Required");

    private final int code;
    private final String message;

    /**
     * Creates a new HTTP status.
     * <p>
     * 创建一个新的HTTP状态。
     *
     * @param code    the status code
     * @param message the status message
     */
    HttpStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * Gets the status code.
     * <p>
     * 获取状态码。
     *
     * @return the status code
     */
    public int getCode() {
        return code;
    }

    /**
     * Gets the status message.
     * <p>
     * 获取状态消息。
     *
     * @return the status message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Finds an HTTP status by its code.
     * <p>
     * 根据状态码查找HTTP状态。
     *
     * @param code the status code
     * @return the corresponding HTTP status, or null if not found
     */
    public static HttpStatus valueOf(int code) {
        for (HttpStatus status : values()) {
            if (status.code == code) {
                return status;
            }
        }
        return null;
    }
}
