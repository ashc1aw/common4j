// SPDX-License-Identifier: Apache-2.0
// Copyright (c) 2026 AshClaw.

package cc.ashclaw.common4j.date;

import cc.ashclaw.common4j.annotation.ThreadSafe;
import cc.ashclaw.common4j.constant.DateFormats;
import cc.ashclaw.common4j.constant.TimeZones;
import cc.ashclaw.common4j.util.StringUtil;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Date and time utility class for handling date-time related operations.
 * <p>
 * 日期时间工具类，用于处理日期时间相关操作。
 * <p>
 * Provides methods for date-time formatting, parsing, conversion, and manipulation.
 * <p>
 * 提供日期时间格式化、解析、转换和操作方法。
 *
 * @author b1itz7
 * @since 1.0.0
 */
@ThreadSafe
public final class DateTimeUtil {

    private static final ConcurrentHashMap<String, DateTimeFormatter> FORMATTER_CACHE
            = new ConcurrentHashMap<>(16);

    private static final DateTimeFormatter DEFAULT_FORMATTER;

    static {
        DEFAULT_FORMATTER = DateTimeFormatters.FMT_DATETIME;
        FORMATTER_CACHE.put(DateFormats.STANDARD_DATETIME, DEFAULT_FORMATTER);
    }

    /**
     * Prevent instantiation.
     * <p>
     * 防止实例化。
     */
    private DateTimeUtil() {
        throw new UnsupportedOperationException("DateTimeUtils cannot be instantiated");
    }

    /**
     * Gets the current date and time.
     * <p>
     * 获取当前日期时间。
     */
    public static LocalDateTime now() {
        return LocalDateTime.now();
    }

    /**
     * Formats a LocalDateTime object to a string using the specified pattern.
     * <p>
     * 使用指定的模式将LocalDateTime对象格式化为字符串。
     */
    public static String format(LocalDateTime dateTime, String pattern) {
        return dateTime == null ? null : getFormatter(pattern).format(dateTime);
    }

    /**
     * Formats a LocalDateTime object to a string using the default pattern.
     * <p>
     * 使用默认模式将LocalDateTime对象格式化为字符串。
     */
    public static String format(LocalDateTime dateTime) {
        return dateTime == null ? null : DEFAULT_FORMATTER.format(dateTime);
    }

    /**
     * Parses a string to a LocalDateTime object using the specified pattern.
     * <p>
     * 使用指定的模式将字符串解析为LocalDateTime对象。
     */
    public static LocalDateTime parse(String dateTimeStr, String pattern) {
        try {
            if (StringUtil.isBlank(dateTimeStr)) {
                return null;
            }
            if (dateTimeStr.contains("Z") || dateTimeStr.contains("+")) {
                return Instant.parse(dateTimeStr)
                        .atZone(TimeZones.SYSTEM_ZONE)
                        .toLocalDateTime();
            }
            return LocalDateTime.parse(dateTimeStr, getFormatter(pattern));
        } catch (DateTimeParseException e) {
            String errorMsg = String.format(
                    "Failed to parse date time string '%s' with pattern '%s'. Expected format: %s",
                    dateTimeStr, pattern, getFormatter(pattern).toString()
            );
            throw new DateTimeParseException(
                    errorMsg,
                    dateTimeStr,
                    e.getErrorIndex(),
                    e
            );
        }
    }

    /**
     * Parses a string to a LocalDateTime object using the default pattern.
     * <p>
     * 使用默认模式将字符串解析为LocalDateTime对象。
     */
    public static LocalDateTime parse(String dateTimeStr) {
        try {
            if (StringUtil.isBlank(dateTimeStr)) {
                return null;
            }
            if (dateTimeStr.contains("Z") || dateTimeStr.contains("+")) {
                return Instant.parse(dateTimeStr)
                        .atZone(TimeZones.SYSTEM_ZONE)
                        .toLocalDateTime();
            }
            return LocalDateTime.parse(dateTimeStr, DEFAULT_FORMATTER);
        } catch (DateTimeParseException e) {
            String errorMsg = String.format(
                    "Failed to parse date time string '%s'. Expected format: %s",
                    dateTimeStr, DEFAULT_FORMATTER.toString()
            );
            throw new DateTimeParseException(
                    errorMsg,
                    dateTimeStr,
                    e.getErrorIndex(),
                    e
            );
        }
    }


    /**
     * Safely parses a string to a LocalDateTime object, returns null if parsing fails.
     * <p>
     * 安全地解析日期时间，解析失败时返回null而不是抛出异常。
     *
     * @param dateTimeStr Date-time string to parse
     * @param pattern     Format pattern to use
     * @return Parsed LocalDateTime object, or null if parsing fails
     * <p>
     * @param dateTimeStr 日期时间字符串
     * @param pattern     格式模式
     * @return 解析后的日期时间，如果解析失败则返回null
     */
    public static LocalDateTime parseQuietly(String dateTimeStr, String pattern) {
        try {
            return parse(dateTimeStr, pattern);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    /**
     * Safely parses a string to a LocalDateTime object using default format, returns null if parsing fails.
     * <p>
     * 安全地解析日期时间（使用默认格式），解析失败时返回null而不是抛出异常。
     *
     * @param dateTimeStr Date-time string to parse
     * @return Parsed LocalDateTime object, or null if parsing fails
     * <p>
     * @param dateTimeStr 日期时间字符串
     * @return 解析后的日期时间，如果解析失败则返回null
     */
    public static LocalDateTime parseQuietly(String dateTimeStr) {
        try {
            return parse(dateTimeStr);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    /**
     * Converts LocalDateTime to epoch seconds.
     * <p>
     * 将LocalDateTime转换为纪元秒（秒级时间戳）。
     */
    public static long toEpochSecond(LocalDateTime dateTime) {
        return dateTime == null ? 0 :
                dateTime.atZone(TimeZones.SYSTEM_ZONE).toInstant().getEpochSecond();
    }

    /**
     * Converts LocalDateTime to epoch milliseconds.
     * <p>
     * 将LocalDateTime转换为纪元毫秒（毫秒级时间戳）。
     */
    public static long toEpochMilli(LocalDateTime dateTime) {
        return dateTime == null ? 0 :
                dateTime.atZone(TimeZones.SYSTEM_ZONE).toInstant().toEpochMilli();
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