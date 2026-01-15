// SPDX-License-Identifier: Apache-2.0
// Copyright (c) 2026 AshClaw.

package cc.ashclaw.common4j.core.constant;

/**
 * Provides special date constants and time markers.
 * <p>
 * 特殊日期和标志常量
 * <p>
 * Includes epoch dates, boundaries, infinity markers, and other special time values.
 * <p>
 * 包含纪元日期、边界、无穷大标记和其他特殊时间值。
 *
 * @author b1itz7
 * @since 1.0.0
 */
public final class SpecialDates {

    /**
     * Prevent instantiation.
     * <p>
     * 防止实例化
     */
    private SpecialDates() {
        throw new UnsupportedOperationException("SpecialDates cannot be instantiated");
    }

    /**
     * Unix epoch timestamp start time: 1970-01-01T00:00:00Z
     * <p>
     * Unix 时间戳起始时间：1970-01-01T00:00:00Z
     */
    public static final String UNIX_EPOCH = "1970-01-01T00:00:00Z";

    /**
     * Unix epoch timestamp start time (readable format): 1970-01-01 00:00:00
     * <p>
     * Unix 时间戳起始时间（可读格式）：1970-01-01 00:00:00
     */
    public static final String UNIX_EPOCH_READABLE = "1970-01-01 00:00:00";

    /**
     * Minimum supported datetime: 0001-01-01T00:00:00Z
     * <p>
     * 最小支持的日期时间：0001-01-01T00:00:00Z
     */
    public static final String MIN_SUPPORTED = "0001-01-01T00:00:00Z";

    /**
     * Maximum supported datetime: 9999-12-31T23:59:59.999999999Z
     * <p>
     * 最大支持的日期时间：9999-12-31T23:59:59.999999999Z
     */
    public static final String MAX_SUPPORTED = "9999-12-31T23:59:59.999999999Z";

    /**
     * Common start date: 1900-01-01
     * <p>
     * 常用起始日期：1900-01-01
     */
    public static final String START_OF_CENTURY = "1900-01-01";

    /**
     * 21st century start date: 2000-01-01
     * <p>
     * 21世纪起始日期：2000-01-01
     */
    public static final String START_OF_21ST_CENTURY = "2000-01-01";

    /**
     * Common end date: 2099-12-31
     * <p>
     * 常用截止日期：2099-12-31
     */
    public static final String END_OF_CENTURY = "2099-12-31";

    /**
     * Infinity time marker (used to indicate never expires)
     * <p>
     * 无穷大时间标志（用于表示永不过期）
     */
    public static final String INFINITY = "+292278994-08-17T07:12:55.807Z";

    /**
     * Negative infinity time marker (used to indicate invalid time)
     * <p>
     * 无穷小时间标志（用于表示无效时间）
     */
    public static final String NEGATIVE_INFINITY = "-292275055-05-16T16:47:04.192Z";

    /**
     * Leap second adjustment epoch: 1972-01-01
     * <p>
     * 闰秒调整日期：1972-01-01
     */
    public static final String LEAP_SECOND_EPOCH = "1972-01-01";

    /**
     * GPS time start date: 1980-01-06T00:00:00Z
     * <p>
     * GPS 时间起始日期：1980-01-06T00:00:00Z
     */
    public static final String GPS_EPOCH = "1980-01-06T00:00:00Z";

}
