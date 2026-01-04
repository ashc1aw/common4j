package cc.ashclaw.common4j.enums;

public enum ResultCode {
    SUCCESS(200, "成功"),
    CREATED(200, "创建成功"),
    UPDATED(200, "更新成功"),
    DELETED(200, "删除成功"),
    BAD_REQUEST(400, "请求参数错误"),
    UNAUTHORIZED(401, "未授权"),
    FORBIDDEN(403, "禁止访问"),
    NOT_FOUND(404, "资源不存在"),
    INTERNAL_SERVER_ERROR(500, "服务器内部错误"),
    CREATE_ERROR(500, "创建失败"),
    UPDATE_ERROR(500, "更新失败"),
    DELETE_ERROR(500, "删除失败");

    private final Integer code;
    private final String desc;

    ResultCode(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
