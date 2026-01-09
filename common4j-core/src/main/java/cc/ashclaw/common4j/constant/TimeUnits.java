// SPDX-License-Identifier: Apache-2.0
// Copyright (c) 2026 AshClaw.

package cc.ashclaw.common4j.constant;

import java.time.Duration;

/**
 * Provides time unit constants in milliseconds and Duration objects.
 * <p>
 * 时间单位常量（毫秒数）
 * <p>
 * Includes various time units from milliseconds to years, as well as JDK Duration constants.
 * <p>
 * 包含从毫秒到年的各种时间单位，以及JDK Duration常量。
 *
 * @author b1itz7
 * @since 1.0.0
 */
public final class TimeUnits {

    /**
     * Prevent instantiation.
     * <p>
     * 防止实例化
     */
    private TimeUnits() {
        throw new UnsupportedOperationException("TimeUnits cannot be instantiated");
    }

    /**
     * One millisecond in milliseconds.
     * <p>
     * 一毫秒
     */
    public static final long MILLISECOND = 1L;

    /**
     * One second in milliseconds.
     * <p>
     * 一秒（毫秒数）
     */
    public static final long SECOND = 1000L;

    /**
     * One minute in milliseconds.
     * <p>
     * 一分钟（毫秒数）
     */
    public static final long MINUTE = 60 * SECOND;

    /**
     * One hour in milliseconds.
     * <p>
     * 一小时（毫秒数）
     */
    public static final long HOUR = 60 * MINUTE;

    /**
     * One day in milliseconds.
     * <p>
     * 一天（毫秒数）
     */
    public static final long DAY = 24 * HOUR;

    /**
     * One week in milliseconds.
     * <p>
     * 一周（毫秒数）
     */
    public static final long WEEK = 7 * DAY;

    /**
     * Average month in milliseconds (based on 30.44 days).
     * <p>
     * 平均月（毫秒数，按30.44天计算）
     */
    public static final long MONTH_AVERAGE = (long) (30.44 * DAY);

    /**
     * Average year in milliseconds (based on 365.25 days).
     * <p>
     * 平均年（毫秒数，按365.25天计算）
     */
    public static final long YEAR_AVERAGE = (long) (365.25 * DAY);

    /**
     * Duration of one day.
     * <p>
     * 一天的 Duration
     */
    public static final Duration ONE_DAY = Duration.ofDays(1);

    /**
     * Duration of one hour.
     * <p>
     * 一小时的 Duration
     */
    public static final Duration ONE_HOUR = Duration.ofHours(1);

    /**
     * Duration of one minute.
     * <p>
     * 一分钟的 Duration
     */
    public static final Duration ONE_MINUTE = Duration.ofMinutes(1);

    /**
     * Duration of one second.
     * <p>
     * 一秒的 Duration
     */
    public static final Duration ONE_SECOND = Duration.ofSeconds(1);

    /**
     * Duration of one millisecond.
     * <p>
     * 一毫秒的 Duration
     */
    public static final Duration ONE_MILLISECOND = Duration.ofMillis(1);

    /**
     * Duration of one week.
     * <p>
     * 一周的 Duration
     */
    public static final Duration ONE_WEEK = Duration.ofDays(7);


}
