// SPDX-License-Identifier: Apache-2.0
// Copyright (c) 2026 AshClaw.

package cc.ashclaw.common4j.date;

import cc.ashclaw.common4j.constant.DateFormats;
import cc.ashclaw.common4j.constant.TimeZones;
import cc.ashclaw.common4j.util.StringUtil;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * Time utility class for handling time-related operations.
 * <p>
 * 时间工具类，用于处理时间相关操作。
 * <p>
 * Provides methods for time formatting, parsing, comparison, and manipulation.
 * <p>
 * 提供时间格式化、解析、比较和操作方法。
 *
 * @author b1itz7
 * @since 1.0.0
 */
public final class TimeUtil {

    private static final ConcurrentHashMap<String, DateTimeFormatter> FORMATTER_CACHE =
            new ConcurrentHashMap<>(16);

    private static final DateTimeFormatter DEFAULT_FORMATTER;

    static {
        DEFAULT_FORMATTER = DateTimeFormatters.FMT_TIME;
        FORMATTER_CACHE.put(DateFormats.STANDARD_TIME, DEFAULT_FORMATTER);
    }

    /**
     * Prevent instantiation.
     * <p>
     * 防止实例化。
     */
    private TimeUtil() {
        throw new UnsupportedOperationException("TimeUtils cannot be instantiated");
    }

    /**
     * Gets the current time.
     * <p>
     * 获取当前时间。
     */
    public static LocalTime now() {
        return LocalTime.now();
    }

    /**
     * Formats a LocalTime object to a string using the specified pattern.
     * <p>
     * 使用指定的模式将LocalTime对象格式化为字符串。
     */
    public static String format(LocalTime time, String pattern) {
        return time == null ? null : getFormatter(pattern).format(time);
    }

    /**
     * Formats a LocalTime object to a string using the default pattern.
     * <p>
     * 使用默认模式将LocalTime对象格式化为字符串。
     */
    public static String format(LocalTime time) {
        return time == null ? null : DEFAULT_FORMATTER.format(time);
    }

    /**
     * Formats a long timestamp (milliseconds since epoch) to a string using the specified pattern.
     * <p>
     * 使用指定的模式将长整型时间戳（自纪元以来的毫秒数）格式化为字符串。
     *
     * @param timestamp Long timestamp in milliseconds
     * @param pattern Format pattern string
     * @return Formatted time string
     * <p>
     * @param timestamp 长整型时间戳（毫秒）
     * @param pattern 格式模式字符串
     * @return 格式化后的时间字符串
     */
    public static String format(long timestamp, String pattern) {
        return DateTimeUtil.format(Instant.ofEpochMilli(timestamp)
                .atZone(TimeZones.SYSTEM_ZONE)
                .toLocalDateTime(), pattern);
    }

    /**
     * Formats a long timestamp (milliseconds since epoch) to a string using the default pattern.
     * <p>
     * 使用默认模式将长整型时间戳（自纪元以来的毫秒数）格式化为字符串。
     *
     * @param timestamp Long timestamp in milliseconds
     * @return Formatted time string
     * <p>
     * @param timestamp 长整型时间戳（毫秒）
     * @return 格式化后的时间字符串
     */
    public static String format(long timestamp) {
        return DateTimeUtil.format(Instant.ofEpochMilli(timestamp)
                .atZone(TimeZones.SYSTEM_ZONE)
                .toLocalDateTime());
    }

    /**
     * Parses a string to a LocalTime object using the specified pattern.
     * <p>
     * 使用指定的模式将字符串解析为LocalTime对象。
     */
    public static LocalTime parse(String timeStr, String pattern) {
        try {
            if (StringUtil.isBlank(timeStr)) {
                return null;
            }

            return LocalTime.parse(timeStr, getFormatter(pattern));
        } catch (DateTimeParseException e) {
            String errorMsg = String.format(
                    "Failed to parse time string '%s' with pattern '%s'. Expected format: %s",
                    timeStr, pattern, getFormatter(pattern).toString()
            );
            throw new DateTimeParseException(
                    errorMsg,
                    timeStr,
                    e.getErrorIndex(),
                    e
            );
        }
    }

    /**
     * Parses a string to a LocalTime object using the default pattern.
     * <p>
     * 使用默认模式将字符串解析为LocalTime对象。
     */
    public static LocalTime parse(String timeStr) {
        try {
            if (StringUtil.isBlank(timeStr)) {
                return null;
            }

            return LocalTime.parse(timeStr, DEFAULT_FORMATTER);
        } catch (DateTimeParseException e) {
            String errorMsg = String.format(
                    "Failed to parse time string '%s' with pattern '%s'. Expected format: %s",
                    timeStr, DateFormats.STANDARD_TIME, DEFAULT_FORMATTER.toString()
            );
            throw new DateTimeParseException(
                    errorMsg,
                    timeStr,
                    e.getErrorIndex(),
                    e
            );
        }
    }

    /**
     * Adds the specified number of hours to the given time.
     * <p>
     * 向给定时间添加指定小时数。
     */
    public static LocalTime addHours(LocalTime time, long hours) {
        return time == null ? null : time.plusHours(hours);
    }

    /**
     * Adds the specified number of minutes to the given time.
     * <p>
     * 向给定时间添加指定分钟数。
     */
    public static LocalTime addMinutes(LocalTime time, long minutes) {
        return time == null ? null : time.plusMinutes(minutes);
    }

    /**
     * Adds the specified number of seconds to the given time.
     * <p>
     * 向给定时间添加指定秒数。
     */
    public static LocalTime addSeconds(LocalTime time, long seconds) {
        return time == null ? null : time.plusSeconds(seconds);
    }


    /**
     * Checks if a time is between two other times (inclusive).
     * <p>
     * 判断时间是否在两个时间之间（包含边界）。
     */
    public static boolean isBetween(LocalTime time, LocalTime start, LocalTime end) {
        if (time == null || start == null || end == null) return false;
        return !time.isBefore(start) && !time.isAfter(end);
    }

    /**
     * Checks if a time is in the specified period, handling cross-day cases.
     * <p>
     * 判断时间是否在时间段内 (跨天处理)。
     */
    public static boolean isInPeriod(LocalTime time, LocalTime periodStart, LocalTime periodEnd) {
        if (time == null || periodStart == null || periodEnd == null) return false;

        if (!periodEnd.isBefore(periodStart)) {
            // 不跨天
            return !time.isBefore(periodStart) && !time.isAfter(periodEnd);
        } else {
            // 跨天 (如 22:00 - 06:00)
            return !time.isBefore(periodStart) || !time.isAfter(periodEnd);
        }
    }

    /**
     * Gets a human-readable description of the duration.
     * <p>
     * 获取时间间隔的人类可读描述。
     */
    public static String getDurationDescription(Duration duration) {
        if (duration == null) return "";

        long hours = duration.toHours();
        long minutes = duration.toMinutes() % 60;
        long seconds = duration.getSeconds() % 60;

        if (hours > 0) {
            return String.format("%d小时%d分钟%d秒", hours, minutes, seconds);
        } else if (minutes > 0) {
            return String.format("%d分钟%d秒", minutes, seconds);
        } else {
            return String.format("%d秒", seconds);
        }
    }

    /**
     * Converts milliseconds to a human-readable time string.
     * <p>
     * 将毫秒转换为人类可读的时间字符串。
     */
    public static String millisToReadable(long millis) {
        if (millis < 1000) {
            return millis + "ms";
        }

        long days = TimeUnit.MILLISECONDS.toDays(millis);
        long hours = TimeUnit.MILLISECONDS.toHours(millis) % 24;
        long minutes = TimeUnit.MILLISECONDS.toMinutes(millis) % 60;
        long seconds = TimeUnit.MILLISECONDS.toSeconds(millis) % 60;

        StringBuilder sb = new StringBuilder();
        if (days > 0) sb.append(days).append("天");
        if (hours > 0) sb.append(hours).append("小时");
        if (minutes > 0) sb.append(minutes).append("分钟");
        if (seconds > 0) sb.append(seconds).append("秒");

        return sb.toString();
    }


    /**
     * Executes a task and measures its execution time in milliseconds.
     * <p>
     * 执行任务并测量其执行时间（毫秒）。
     */
    public static long timed(Runnable task) {
        long start = System.currentTimeMillis();
        task.run();
        return System.currentTimeMillis() - start;
    }

    /**
     * Gets the start time of the day (00:00:00).
     * <p>
     * 获取今天的开始时间 (00:00:00)。
     */
    public static LocalTime startOfDay() {
        return LocalTime.MIN;
    }

    /**
     * Gets the end time of the day (23:59:59.999).
     * <p>
     * 获取今天的结束时间 (23:59:59.999)。
     */
    public static LocalTime endOfDay() {
        return LocalTime.MAX;
    }

    /**
     * Checks if the given time is in the morning (before noon).
     * <p>
     * 判断给定时间是否是上午（中午之前）。
     */
    public static boolean isAM(LocalTime time) {
        return time != null && time.isBefore(LocalTime.NOON);
    }

    /**
     * Checks if the given time is in the afternoon (after noon).
     * <p>
     * 判断给定时间是否是下午（中午之后）。
     */
    public static boolean isPM(LocalTime time) {
        return time != null && !time.isBefore(LocalTime.NOON);
    }

    /**
     * Rounds a time to the nearest specified number of minutes.
     * <p>
     * 将时间取整到最近的指定分钟数。
     */
    public static LocalTime roundToNearestMinutes(LocalTime time, int minutes) {
        if (time == null || minutes <= 0) return null;

        int minute = time.getMinute();
        int remainder = minute % minutes;
        int adjustment = remainder < minutes / 2 ? -remainder : minutes - remainder;

        return time.plusMinutes(adjustment).truncatedTo(ChronoUnit.MINUTES);
    }

    /**
     * Gets or creates a DateTimeFormatter for the specified pattern.
     * <p>
     * 获取或创建指定模式的DateTimeFormatter。
     * <p>
     * Uses a thread-safe cache to store and reuse formatters for better performance.
     * <p>
     * 使用线程安全的缓存来存储和重用格式化器，以获得更好的性能。
     *
     * @param pattern Format pattern string
     * @return DateTimeFormatter instance
     * <p>
     * @param pattern 格式模式字符串
     * @return DateTimeFormatter实例
     */
    private static DateTimeFormatter getFormatter(final String pattern) {
        return FORMATTER_CACHE.computeIfAbsent(pattern, DateTimeFormatter::ofPattern);
    }
}