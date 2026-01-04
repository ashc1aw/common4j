package cc.ashclaw.common4j.model;

import cc.ashclaw.common4j.enums.ResultCode;

import java.util.Date;
import java.util.function.Supplier;

/**
 * @author b1itz7
 */
public class Result<T> {
    private Integer code;
    private String message;
    private T data;
    private final Date timestamp;

    // 私有化构造器，强制使用静态工厂方法
    private Result() {
        this.timestamp = new Date();
    }

    private Result(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.timestamp = new Date();
    }

    // ==================== 核心构建方法 ====================

    public static <T> Result<T> of(ResultCode resultCode) {
        return of(resultCode, null, null);
    }

    public static <T> Result<T> of(ResultCode resultCode, String message) {
        return of(resultCode, message, null);
    }

    public static <T> Result<T> of(ResultCode resultCode, T data) {
        return of(resultCode, null, data);
    }

    public static <T> Result<T> of(ResultCode resultCode, String message, T data) {
        return new Result<>(resultCode.getCode(),
                message != null ? message : resultCode.getDesc(),
                data);
    }

    // ==================== 成功快捷方法 ====================

    public static <T> Result<T> success() {
        return of(ResultCode.SUCCESS);
    }

    public static <T> Result<T> success(String message) {
        return of(ResultCode.SUCCESS, message);
    }

    public static <T> Result<T> success(T data) {
        return of(ResultCode.SUCCESS, data);
    }

    public static <T> Result<T> success(String message, T data) {
        return of(ResultCode.SUCCESS, message, data);
    }

    // ==================== 业务操作成功快捷方法 ====================

    public static <T> Result<T> created() {
        return of(ResultCode.CREATED);
    }

    public static <T> Result<T> updated() {
        return of(ResultCode.UPDATED);
    }

    public static <T> Result<T> deleted() {
        return of(ResultCode.DELETED);
    }

    // ==================== 通用错误方法 ====================

    public static <T> Result<T> error(ResultCode resultCode) {
        return of(resultCode);
    }

    public static <T> Result<T> error(ResultCode resultCode, String message) {
        return errorWithDetail(resultCode, message);
    }

    public static <T> Result<T> error(ResultCode resultCode, T data) {
        return of(resultCode, data);
    }

    // ==================== 业务操作失败快捷方法 ====================

    public static <T> Result<T> createError() {
        return error(ResultCode.CREATE_ERROR);
    }

    public static <T> Result<T> createError(String message) {
        return error(ResultCode.CREATE_ERROR, message);
    }

    public static <T> Result<T> updateError() {
        return error(ResultCode.UPDATE_ERROR);
    }

    public static <T> Result<T> updateError(String message) {
        return error(ResultCode.UPDATE_ERROR, message);
    }

    public static <T> Result<T> deleteError() {
        return error(ResultCode.DELETE_ERROR);
    }

    public static <T> Result<T> deleteError(String message) {
        return error(ResultCode.DELETE_ERROR, message);
    }

    // ==================== 辅助方法 ====================

    private static <T> Result<T> errorWithDetail(ResultCode resultCode, String detail) {
        return new Result<>(resultCode.getCode(),
                String.format("%s: %s", resultCode.getDesc(), detail),
                null);
    }

    public boolean isSuccess() {
        return ResultCode.SUCCESS.getCode().equals(this.code);
    }

    public boolean isError() {
        return !isSuccess();
    }

    // ==================== 链式操作方法 ====================

    public Result<T> withMessage(String message) {
        this.message = message;
        return this;
    }

    public Result<T> withData(T data) {
        this.data = data;
        return this;
    }

    // ==================== 安全数据处理方法 ====================

    public T getDataOrDefault(T defaultValue) {
        return data != null ? data : defaultValue;
    }

    public T getDataOrElse(Supplier<T> supplier) {
        return data != null ? data : supplier.get();
    }

    // ==================== Getter方法 ====================

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    // ==================== 重写方法 ====================

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                ", timestamp=" + timestamp +
                '}';
    }
}