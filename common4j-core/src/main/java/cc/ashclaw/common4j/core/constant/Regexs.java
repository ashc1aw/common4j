// SPDX-License-Identifier: Apache-2.0
// Copyright (c) 2026 AshClaw.

package cc.ashclaw.common4j.core.constant;

/**
 * Provides commonly used regular expression patterns.
 * <p>
 * 提供常用的正则表达式模式。
 * <p>
 * This class contains various regex patterns for validation and matching purposes,
 * including patterns for emails, phone numbers, IDs, dates, and other common formats.
 * <p>
 * 此类包含各种用于验证和匹配的正则表达式模式，包括电子邮件、电话号码、ID、日期等常见格式。
 *
 * @author b1itz7
 * @since 1.0.0
 */
public final class Regexs {

    /**
     * Simple email validation pattern.
     * <p>
     * 简单的电子邮件验证模式。
     */
    public static final String EMAIL_SIMPLE = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}$";

    /**
     * RFC 5322 compliant email validation pattern.
     * <p>
     * 符合 RFC 5322 标准的电子邮件验证模式。
     */
    public static final String EMAIL_RFC5322 = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";

    /**
     * Chinese mainland phone number pattern (11 digits).
     * <p>
     * 中国大陆手机号码模式（11位）。
     */
    public static final String PHONE_CHINESE_MAINLAND = "^1[3-9]\\d{9}$";

    /**
     * Chinese phone number with area code pattern.
     * <p>
     * 带区号的中国电话号码模式。
     */
    public static final String PHONE_CHINESE_WITH_AREA_CODE = "^\\d{3,4}-\\d{7,8}$";

    /**
     * Chinese ID card number pattern (18 digits).
     * <p>
     * 中国身份证号码模式（18位）。
     */
    public static final String ID_CARD_CHINESE = "^[1-9]\\d{5}(18|19|20)\\d{2}((0[1-9])|(1[0-2]))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$";

    /**
     * Chinese ID card number pattern (15 digits).
     * <p>
     * 中国身份证号码模式（15位）。
     */
    public static final String ID_CARD_CHINESE_15 = "^[1-9]\\d{7}((0[1-9])|(1[0-2]))(([0-2][1-9])|10|20|30|31)\\d{3}$";

    /**
     * Date pattern: yyyy-MM-dd.
     * <p>
     * 日期模式：yyyy-MM-dd。
     */
    public static final String DATE_YYYY_MM_DD = "^\\d{4}-\\d{2}-\\d{2}$";

    /**
     * Date pattern: yyyy/MM/dd.
     * <p>
     * 日期模式：yyyy/MM/dd。
     */
    public static final String DATE_YYYY_MM_DD_SLASH = "^\\d{4}/\\d{2}/\\d{2}$";

    /**
     * DateTime pattern: yyyy-MM-dd HH:mm:ss.
     * <p>
     * 日期时间模式：yyyy-MM-dd HH:mm:ss。
     */
    public static final String DATETIME_YYYY_MM_DD_HH_MM_SS = "^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}$";

    /**
     * Positive integer pattern.
     * <p>
     * 正整数模式。
     */
    public static final String NUMBER_POSITIVE_INTEGER = "^[1-9]\\d*$";

    /**
     * Negative integer pattern.
     * <p>
     * 负整数模式。
     */
    public static final String NUMBER_NEGATIVE_INTEGER = "^-[1-9]\\d*$";

    /**
     * Integer pattern (positive, negative, or zero).
     * <p>
     * 整数模式（正、负或零）。
     */
    public static final String NUMBER_INTEGER = "^-?\\d+$";

    /**
     * Decimal pattern.
     * <p>
     * 小数模式。
     */
    public static final String NUMBER_DECIMAL = "^-?\\d+(\\.\\d+)?$";

    /**
     * Non-negative decimal pattern.
     * <p>
     * 非负小数模式。
     */
    public static final String NUMBER_NON_NEGATIVE_DECIMAL = "^\\d+(\\.\\d+)?$";

    /**
     * HTTP/HTTPS URL pattern.
     * <p>
     * HTTP/HTTPS URL模式。
     */
    public static final String URL_HTTP_HTTPS = "^https?:\\/\\/(www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b([-a-zA-Z0-9()@:%_\\+.~#?&//=]*)$";

    /**
     * IP address pattern (IPv4).
     * <p>
     * IP地址模式（IPv4）。
     */
    public static final String IP_V4 = "^((25[0-5]|(2[0-4]|1\\d|[1-9]|)\\d)\\.?\\b){4}$";

    /**
     * Chinese character pattern (simplified and traditional).
     * <p>
     * 中文字符模式（简体和繁体）。
     */
    public static final String CHINESE_CHARACTERS = "^[\\u4e00-\\u9fa5]+$";

    /**
     * Chinese name pattern (2-4 characters).
     * <p>
     * 中文姓名模式（2-4个字符）。
     */
    public static final String CHINESE_NAME = "^[\\u4e00-\\u9fa5]{2,4}$";

    /**
     * Username pattern: 4-20 characters, letters, numbers, underscores.
     * <p>
     * 用户名模式：4-20个字符，字母、数字、下划线。
     */
    public static final String USERNAME = "^[a-zA-Z0-9_]{4,20}$";

    /**
     * Strong password pattern: at least 8 characters, including uppercase, lowercase, number, and special character.
     * <p>
     * 强密码模式：至少8个字符，包含大小写字母、数字和特殊字符。
     */
    public static final String PASSWORD_STRONG = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";

    /**
     * File name pattern (basic).
     * <p>
     * 文件名模式（基本）。
     */
    public static final String FILE_NAME = "^[^\\/:*?\"<>|]+$";

    /**
     * Image file extension pattern.
     * <p>
     * 图片文件扩展名模式。
     */
    public static final String IMAGE_EXTENSIONS = "^.*\\.(jpg|jpeg|png|gif|bmp|webp)$";

    /**
     * HTML tag pattern.
     * <p>
     * HTML标签模式。
     */
    public static final String HTML_TAG = "<[^>]+>";

    /**
     * HTML comment pattern.
     * <p>
     * HTML注释模式。
     */
    public static final String HTML_COMMENT = "<!--.*?-->";

    /**
     * Chinese postal code pattern (6 digits).
     * <p>
     * 中国邮政编码模式（6位）。
     */
    public static final String POSTAL_CODE_CHINESE = "^[1-9]\\d{5}$";

    /**
     * Credit card number pattern (basic validation).
     * <p>
     * 信用卡号码模式（基本验证）。
     */
    public static final String CREDIT_CARD = "^\\d{13,19}$";

    /**
     * Visa card number pattern.
     * <p>
     * Visa信用卡号码模式。
     */
    public static final String CREDIT_CARD_VISA = "^4[0-9]{12}(?:[0-9]{3})?$";

    /**
     * MasterCard number pattern.
     * <p>
     * MasterCard信用卡号码模式。
     */
    public static final String CREDIT_CARD_MASTERCARD = "^5[1-5][0-2][1-9][0-9]\\d{10}$";

    /**
     * US Social Security Number (SSN) pattern.
     * <p>
     * 美国社会保障号码（SSN）模式。
     */
    public static final String SSN_US = "^\\d{3}-\\d{2}-\\d{4}$";

    /**
     * Private constructor to prevent instantiation.
     * <p>
     * 私有构造函数，防止实例化。
     */
    private Regexs() {
        throw new AssertionError("Utility class cannot be instantiated");
    }
}