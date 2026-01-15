// SPDX-License-Identifier: Apache-2.0
// Copyright (c) 2026 AshClaw.

package cc.ashclaw.common4j.core.date;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

/**
 * Utility class for calculating time intervals between dates and times.
 * <p>
 * 时间间隔计算工具类，用于计算日期和时间之间的时间间隔。
 * <p>
 * Provides methods for calculating days, seconds, minutes, and hours between dates/times.
 * <p>
 * 提供计算日期/时间之间的天数、秒数、分钟和小时的方法。
 *
 * @author b1itz7
 * @since 1.0.0
 */
public final class DateBetween {

    /**
     * Prevent instantiation.
     * <p>
     * 防止实例化。
     */
    private DateBetween() {
        throw new UnsupportedOperationException("DateBetween cannot be instantiated");
    }

    /**
     * Calculates the number of days between two dates.
     * <p>
     * 计算两个日期之间的天数差
     */
    public static long daysBetween(LocalDate start, LocalDate end) {
        if (start == null || end == null) return 0;
        return Math.abs(java.time.temporal.ChronoUnit.DAYS.between(start, end));
    }

    /**
     * Calculates the number of seconds between two times.
     * <p>
     * 计算两个时间之间的秒数差。
     */
    public static long secondsBetween(LocalTime start, LocalTime end) {
        if (start == null || end == null) return 0;
        return Math.abs(ChronoUnit.SECONDS.between(start, end));
    }

    /**
     * Calculates the number of minutes between two times.
     * <p>
     * 计算两个时间之间的分钟数差。
     */
    public static long minutesBetween(LocalTime start, LocalTime end) {
        if (start == null || end == null) return 0;
        return Math.abs(ChronoUnit.MINUTES.between(start, end));
    }

    /**
     * Calculates the number of hours between two times.
     * <p>
     * 计算两个时间之间的小时数差。
     */
    public static long hoursBetween(LocalTime start, LocalTime end) {
        if (start == null || end == null) return 0;
        long hours = Math.abs(ChronoUnit.HOURS.between(start, end));
        // Handle cross-day cases
        if (hours > 12) {
            hours = 24 - hours;
        }
        return hours;
    }
}
