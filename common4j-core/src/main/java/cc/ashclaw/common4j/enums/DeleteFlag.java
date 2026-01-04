package cc.ashclaw.common4j.enums;

public enum DeleteFlag {

    DELETED("1", "已删除"),
    NOT_DELETE("0", "未删除");

    private final String code;
    private final String desc;

    DeleteFlag(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

}
