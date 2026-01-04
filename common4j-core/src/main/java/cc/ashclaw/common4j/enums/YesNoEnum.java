package cc.ashclaw.common4j.enums;

public enum YesNoEnum {

    YES("1", "是"),
    NO("0", "否");

    private final String code;
    private final String desc;

    YesNoEnum(String code, String desc) {
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
