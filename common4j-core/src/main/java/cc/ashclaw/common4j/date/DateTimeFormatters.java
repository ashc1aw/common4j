// SPDX-License-Identifier: Apache-2.0
// Copyright (c) 2026 AshClaw.

package cc.ashclaw.common4j.date;

import cc.ashclaw.common4j.constant.DateFormats;

import java.time.format.DateTimeFormatter;

/**
 * Provides commonly used DateTimeFormatter instances.
 * <p>
 * 提供常用的DateTimeFormatter实例。
 * <p>
 * Contains predefined formatters for various date and time formats.
 * <p>
 * 包含用于各种日期和时间格式的预定义格式化程序。
 *
 * @author b1itz7
 * @since 1.0.0
 */
public final class DateTimeFormatters {
    /**
     * Prevent instantiation.
     * <p>
     * 防止实例化。
     */
    private DateTimeFormatters() {
        throw new UnsupportedOperationException("DateTimeFormatters cannot be instantiated");
    }

    /**
     * Standard date-time formatter: yyyy-MM-dd HH:mm:ss.
     * <p>
     * 标准日期时间格式化器：yyyy-MM-dd HH:mm:ss。
     */
    public static final DateTimeFormatter FMT_DATETIME = 
            DateTimeFormatter.ofPattern(DateFormats.STANDARD_DATETIME);
    
    /**
     * Standard date formatter: yyyy-MM-dd.
     * <p>
     * 标准日期格式化器：yyyy-MM-dd。
     */
    public static final DateTimeFormatter FMT_DATE = 
            DateTimeFormatter.ofPattern(DateFormats.STANDARD_DATE);
    
    /**
     * Standard time formatter: HH:mm:ss.
     * <p>
     * 标准时间格式化器：HH:mm:ss。
     */
    public static final DateTimeFormatter FMT_TIME = 
            DateTimeFormatter.ofPattern(DateFormats.STANDARD_TIME);
    
    /**
     * Compact date formatter: yyyyMMdd.
     * <p>
     * 紧凑日期格式化器：yyyyMMdd。
     */
    public static final DateTimeFormatter FMT_COMPACT = 
            DateTimeFormatter.ofPattern(DateFormats.COMPACT_DATE);
    
    /**
     * Hour-minute time formatter: HH:mm.
     * <p>
     * 小时分钟时间格式化器：HH:mm。
     */
    public static final DateTimeFormatter FMT_TIME_HM = 
            DateTimeFormatter.ofPattern(DateFormats.HOUR_MINUTE);

}
