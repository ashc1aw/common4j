// SPDX-License-Identifier: Apache-2.0
// Copyright (c) 2026 AshClaw.

package cc.ashclaw.common4j.constant;

/**
 * Provides commonly used date and time format string constants.
 * <p>
 * 提供常用的日期时间格式字符串常量。
 * <p>
 * Includes various standard formats such as ISO 8601, RFC, logging,
 * and Chinese environment formats.
 * <p>
 * 包含 ISO 8601、RFC、日志以及中文环境等多种标准格式。
 *
 * @author b1itz7
 * @since 1.0.0
 */
public final class DateFormats {

    /**
     * Prevent instantiation.
     * <p>
     * 防止实例化。
     */
    private DateFormats() {
        throw new UnsupportedOperationException("DateFormats cannot be instantiated");
    }

    

    /**
     * ISO 8601 Basic date format: yyyyMMdd
     * <p>
     * ISO 8601 基本日期格式：yyyyMMdd
     */
    public static final String ISO_BASIC_DATE = "yyyyMMdd";

    /**
     * ISO 8601 Extended date format: yyyy-MM-dd
     * <p>
     * ISO 8601 扩展日期格式：yyyy-MM-dd
     */
    public static final String ISO_EXTENDED_DATE = "yyyy-MM-dd";

    /**
     * ISO 8601 Basic time format: HHmmss
     * <p>
     * ISO 8601 基本时间格式：HHmmss
     */
    public static final String ISO_BASIC_TIME = "HHmmss";

    /**
     * ISO 8601 Extended time format: HH:mm:ss
     * <p>
     * ISO 8601 扩展时间格式：HH:mm:ss
     */
    public static final String ISO_EXTENDED_TIME = "HH:mm:ss";

    /**
     * ISO 8601 Basic date-time format: yyyyMMdd'T'HHmmss
     * <p>
     * ISO 8601 基本日期时间格式：yyyyMMdd'T'HHmmss
     */
    public static final String ISO_BASIC_DATETIME = "yyyyMMdd'T'HHmmss";

    /**
     * ISO 8601 Extended date-time format: yyyy-MM-dd'T'HH:mm:ss
     * <p>
     * ISO 8601 扩展日期时间格式：yyyy-MM-dd'T'HH:mm:ss
     */
    public static final String ISO_EXTENDED_DATETIME = "yyyy-MM-dd'T'HH:mm:ss";

    /**
     * ISO 8601 Format with timezone: yyyy-MM-dd'T'HH:mm:ssXXX
     * <p>
     * ISO 8601 带时区格式：yyyy-MM-dd'T'HH:mm:ssXXX
     */
    public static final String ISO_DATETIME_WITH_TIMEZONE = "yyyy-MM-dd'T'HH:mm:ssXXX";

    /**
     * ISO 8601 Format with milliseconds: yyyy-MM-dd'T'HH:mm:ss.SSS
     * <p>
     * ISO 8601 带毫秒格式：yyyy-MM-dd'T'HH:mm:ss.SSS
     */
    public static final String ISO_DATETIME_WITH_MILLIS = "yyyy-MM-dd'T'HH:mm:ss.SSS";

    /**
     * ISO 8601 Format with milliseconds and timezone: yyyy-MM-dd'T'HH:mm:ss.SSSXXX
     * <p>
     * ISO 8601 带毫秒和时区格式：yyyy-MM-dd'T'HH:mm:ss.SSSXXX
     */
    public static final String ISO_DATETIME_WITH_MILLIS_TIMEZONE = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";

    

    /**
     * RFC 1123 date-time format: EEE, dd MMM yyyy HH:mm:ss zzz
     * <p>
     * RFC 1123 日期时间格式：EEE, dd MMM yyyy HH:mm:ss zzz
     */
    public static final String RFC_1123 = "EEE, dd MMM yyyy HH:mm:ss zzz";

    /**
     * RFC 3339 date-time format: yyyy-MM-dd'T'HH:mm:ssXXX
     * <p>
     * RFC 3339 日期时间格式：yyyy-MM-dd'T'HH:mm:ssXXX
     */
    public static final String RFC_3339 = "yyyy-MM-dd'T'HH:mm:ssXXX";

    

    /**
     * Chinese date format: yyyy年MM月dd日
     * <p>
     * 中文日期格式：yyyy年MM月dd日
     */
    public static final String CHINESE_DATE = "yyyy年MM月dd日";

    /**
     * Chinese date-time format: yyyy年MM月dd日 HH时mm分ss秒
     * <p>
     * 中文日期时间格式：yyyy年MM月dd日 HH时mm分ss秒
     */
    public static final String CHINESE_DATETIME = "yyyy年MM月dd日 HH时mm分ss秒";

    /**
     * Chinese short date format: yyyy年M月d日
     * <p>
     * 中文简短日期格式：yyyy年M月d日
     */
    public static final String CHINESE_DATE_SHORT = "yyyy年M月d日";

    

    /**
     * US date format: MM/dd/yyyy
     * <p>
     * 美式日期格式：MM/dd/yyyy
     */
    public static final String US_DATE = "MM/dd/yyyy";

    /**
     * US date-time format: MM/dd/yyyy HH:mm:ss
     * <p>
     * 美式日期时间格式：MM/dd/yyyy HH:mm:ss
     */
    public static final String US_DATETIME = "MM/dd/yyyy HH:mm:ss";

    

    /**
     * Standard date-time format: yyyy-MM-dd HH:mm:ss
     * <p>
     * 标准日期时间格式：yyyy-MM-dd HH:mm:ss
     */
    public static final String STANDARD_DATETIME = "yyyy-MM-dd HH:mm:ss";

    /**
     * Standard date format: yyyy-MM-dd
     * <p>
     * 标准日期格式：yyyy-MM-dd
     */
    public static final String STANDARD_DATE = "yyyy-MM-dd";

    /**
     * Standard time format: HH:mm:ss
     * <p>
     * 标准时间格式：HH:mm:ss
     */
    public static final String STANDARD_TIME = "HH:mm:ss";

    /**
     * Compact date format: yyyyMMdd
     * <p>
     * 紧凑日期格式：yyyyMMdd
     */
    public static final String COMPACT_DATE = "yyyyMMdd";

    /**
     * Compact date-time format: yyyyMMddHHmmss
     * <p>
     * 紧凑日期时间格式：yyyyMMddHHmmss
     */
    public static final String COMPACT_DATETIME = "yyyyMMddHHmmss";

    /**
     * Compact date-time format with milliseconds: yyyyMMddHHmmssSSS
     * <p>
     * 紧凑日期时间毫秒格式：yyyyMMddHHmmssSSS
     */
    public static final String COMPACT_DATETIME_MILLIS = "yyyyMMddHHmmssSSS";

    /**
     * Year-month format: yyyy-MM
     * <p>
     * 月份格式：yyyy-MM
     */
    public static final String YEAR_MONTH = "yyyy-MM";

    /**
     * Hour-minute format: HH:mm
     * <p>
     * 小时分钟格式：HH:mm
     */
    public static final String HOUR_MINUTE = "HH:mm";

    /**
     * Month-day format: MM-dd
     * <p>
     * 仅月份日期格式：MM-dd
     */
    public static final String MONTH_DAY = "MM-dd";

    /**
     * English month-date format: MMM dd, yyyy
     * <p>
     * 英文月份日期格式：MMM dd, yyyy
     */
    public static final String ENGLISH_MONTH_DATE = "MMM dd, yyyy";

    /**
     * English full date-time format: EEEE, MMMM dd, yyyy HH:mm:ss
     * <p>
     * 英文完整日期时间格式：EEEE, MMMM dd, yyyy HH:mm:ss
     */
    public static final String ENGLISH_FULL_DATETIME = "EEEE, MMMM dd, yyyy HH:mm:ss";

    /**
     * Standard log content timestamp format (with milliseconds): yyyy-MM-dd HH:mm:ss.SSS
     * <p>
     * 标准日志内容时间戳格式（精确到毫秒）：yyyy-MM-dd HH:mm:ss.SSS
     */
    public static final String LOG_CONTENT_TIMESTAMP = "yyyy-MM-dd HH:mm:ss.SSS";

    /**
     * Safe log file naming format: yyyy-MM-dd_HH-mm-ss
     * <p>
     * 安全的日志文件命名格式：yyyy-MM-dd_HH-mm-ss
     */
    public static final String LOG_FILE_SAFE = "yyyy-MM-dd_HH-mm-ss";

    /**
     * ISO format log timestamp with timezone: yyyy-MM-dd'T'HH:mm:ss.SSSXXX
     * <p>
     * 带时区的ISO格式日志时间戳：yyyy-MM-dd'T'HH:mm:ss.SSSXXX
     */
    public static final String LOG_CONTENT_ISO = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";
}