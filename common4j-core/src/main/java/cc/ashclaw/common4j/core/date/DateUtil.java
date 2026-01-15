// SPDX-License-Identifier: Apache-2.0
// Copyright (c) 2026 AshClaw.

package cc.ashclaw.common4j.core.date;

import cc.ashclaw.common4j.core.constant.DateFormats;
import cc.ashclaw.common4j.core.util.StringUtil;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAdjusters;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Date utility class for handling date-related operations.
 * <p>
 * 日期工具类，用于处理日期相关操作。
 * <p>
 * Provides methods for date formatting, parsing, comparison, and manipulation.
 * <p>
 * 提供日期格式化、解析、比较和操作方法。
 *
 * @author b1itz7
 * @since 1.0.0
 */
public final class DateUtil {

    private static final ConcurrentHashMap<String, DateTimeFormatter> FORMATTER_CACHE =
            new ConcurrentHashMap<>(16);

    private static final DateTimeFormatter DEFAULT_FORMATTER;

    static {
        DEFAULT_FORMATTER = DateTimeFormatters.FMT_DATE;
        FORMATTER_CACHE.put(DateFormats.STANDARD_DATE, DEFAULT_FORMATTER);
    }

    /**
     * Prevent instantiation.
     * <p>
     * 防止实例化。
     */
    private DateUtil() {
        throw new UnsupportedOperationException("DateUtils cannot be instantiated");
    }

    /**
     * Gets the current date.
     * <p>
     * 获取当前日期。
     */
    public static LocalDate today() {
        return LocalDate.now();
    }

    /**
     * Formats a LocalDate object to a string using the specified pattern.
     * <p>
     * 使用指定的模式将LocalDate对象格式化为字符串。
     */
    public static String format(LocalDate date, String pattern) {
        return date == null ? null : getFormatter(pattern).format(date);
    }

    /**
     * Formats a LocalDate object to a string using the default pattern.
     * <p>
     * 使用默认模式将LocalDate对象格式化为字符串。
     */
    public static String format(LocalDate date) {
        return date == null ? null : DEFAULT_FORMATTER.format(date);
    }

    /**
     * Parses a string to a LocalDate object using the specified pattern.
     * <p>
     * 使用指定的模式将字符串解析为LocalDate对象。
     */
    public static LocalDate parse(String dateStr, String pattern) {
        try {
            if (StringUtil.isBlank(dateStr)) {
                return null;
            }

            return LocalDate.parse(dateStr, getFormatter(pattern));
        } catch (DateTimeParseException e) {
            String errorMsg = String.format(
                    "Failed to parse date string '%s' with pattern '%s'. Expected format: %s",
                    dateStr, pattern, getFormatter(pattern).toString()
            );
            throw new DateTimeParseException(
                    errorMsg,
                    dateStr,
                    e.getErrorIndex(),
                    e
            );
        }
    }

    /**
     * Parses a string to a LocalDate object using the default pattern.
     * <p>
     * 使用默认模式将字符串解析为LocalDate对象。
     */
    public static LocalDate parse(String dateStr) {
        try {
            if (StringUtil.isBlank(dateStr)) {
                return null;
            }

            return LocalDate.parse(dateStr, DEFAULT_FORMATTER);
        } catch (DateTimeParseException e) {
            String errorMsg = String.format(
                    "Failed to parse date string '%s' with pattern '%s'. Expected format: %s",
                    dateStr, DateFormats.STANDARD_DATE, DEFAULT_FORMATTER.toString()
            );
            throw new DateTimeParseException(
                    errorMsg,
                    dateStr,
                    e.getErrorIndex(),
                    e
            );
        }
    }

    /**
     * Gets the date of yesterday.
     * <p>
     * 获取昨天的日期。
     */
    public static LocalDate yesterday() {
        return LocalDate.now().minusDays(1);
    }

    /**
     * Gets the date of tomorrow.
     * <p>
     * 获取明天的日期。
     */
    public static LocalDate tomorrow() {
        return LocalDate.now().plusDays(1);
    }

    /**
     * Adds the specified number of days to the given date.
     * <p>
     * 向给定日期添加指定天数。
     */
    public static LocalDate addDays(LocalDate date, int days) {
        return date == null ? null : date.plusDays(days);
    }

    /**
     * Adds the specified number of months to the given date.
     * <p>
     * 向给定日期添加指定月份。
     */
    public static LocalDate addMonths(LocalDate date, int months) {
        return date == null ? null : date.plusMonths(months);
    }

    /**
     * Adds the specified number of years to the given date.
     * <p>
     * 向给定日期添加指定年数。
     */
    public static LocalDate addYears(LocalDate date, int years) {
        return date == null ? null : date.plusYears(years);
    }


    /**
     * Checks if two dates represent the same day.
     * <p>
     * 判断两个日期是否为同一天。
     */
    public static boolean isSameDay(LocalDate date1, LocalDate date2) {
        if (date1 == null || date2 == null) return false;
        return date1.isEqual(date2);
    }

    /**
     * Checks if a date is between two other dates (inclusive).
     * <p>
     * 判断日期是否在两个日期之间（包含边界）。
     */
    public static boolean isBetween(LocalDate date, LocalDate start, LocalDate end) {
        if (date == null || start == null || end == null) return false;
        return !date.isBefore(start) && !date.isAfter(end);
    }

    /**
     * Checks if a date falls on a weekend.
     * <p>
     * 判断日期是否为周末。
     */
    public static boolean isWeekend(LocalDate date) {
        if (date == null) return false;
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY;
    }

    /**
     * Checks if a date falls on a weekday.
     * <p>
     * 判断日期是否为工作日。
     */
    public static boolean isWeekday(LocalDate date) {
        return !isWeekend(date);
    }

    /**
     * Gets the quarter of the year for the given date (1-4).
     * <p>
     * 获取给定日期的季度 (1-4)。
     */
    public static int getQuarter(LocalDate date) {
        if (date == null) return 0;
        int month = date.getMonthValue();
        return (month - 1) / 3 + 1;
    }

    /**
     * Gets the first day of the month for the given date.
     * <p>
     * 获取给定日期所在月份的第一天。
     */
    public static LocalDate firstDayOfMonth(LocalDate date) {
        return date == null ? null : date.with(TemporalAdjusters.firstDayOfMonth());
    }

    /**
     * Gets the last day of the month for the given date.
     * <p>
     * 获取给定日期所在月份的最后一天。
     */
    public static LocalDate lastDayOfMonth(LocalDate date) {
        return date == null ? null : date.with(TemporalAdjusters.lastDayOfMonth());
    }

    /**
     * Gets the first day of the year for the given date.
     * <p>
     * 获取给定日期所在年份的第一天。
     */
    public static LocalDate firstDayOfYear(LocalDate date) {
        return date == null ? null : date.with(TemporalAdjusters.firstDayOfYear());
    }

    /**
     * Gets the last day of the year for the given date.
     * <p>
     * 获取给定日期所在年份的最后一天。
     */
    public static LocalDate lastDayOfYear(LocalDate date) {
        return date == null ? null : date.with(TemporalAdjusters.lastDayOfYear());
    }

    /**
     * Calculates age based on birth date using current date.
     * <p>
     * 基于生日计算年龄（使用当前日期）。
     */
    public static int calculateAge(LocalDate birthDate) {
        return calculateAge(birthDate, LocalDate.now());
    }

    /**
     * Calculates age based on birth date and current date.
     * <p>
     * 基于生日和当前日期计算年龄。
     */
    public static int calculateAge(LocalDate birthDate, LocalDate currentDate) {
        if (birthDate == null || currentDate == null) return 0;
        return (int) java.time.temporal.ChronoUnit.YEARS.between(birthDate, currentDate);
    }

    /**
     * Gets the day of the week as a number (1=Monday, ..., 7=Sunday).
     * <p>
     * 获取日期的星期几 (1=周一, ..., 7=周日)。
     */
    public static int getDayOfWeekNumber(LocalDate date) {
        if (date == null) return 0;
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        // 将DayOfWeek转换为数字 (1=周一, ..., 7=周日)
        return dayOfWeek.getValue();
    }

    /**
     * Gets the Chinese representation of the day of the week.
     * <p>
     * 获取日期的星期几中文表示。
     */
    public static String getDayOfWeekChinese(LocalDate date) {
        if (date == null) return null;
        String[] weekDays = {"周一", "周二", "周三", "周四", "周五", "周六", "周日"};
        int index = getDayOfWeekNumber(date) - 1;
        return weekDays[index];
    }

    /**
     * Checks if the year of the given date is a leap year.
     * <p>
     * 判断给定日期的年份是否为闰年。
     */
    public static boolean isLeapYear(LocalDate date) {
        return date != null && date.isLeapYear();
    }

    /**
     * Gets the number of days in the month for the given date.
     * <p>
     * 获取给定日期所在月份的天数。
     */
    public static int getDaysInMonth(LocalDate date) {
        return date == null ? 0 : date.lengthOfMonth();
    }

    /**
     * Gets the number of days in the year for the given date.
     * <p>
     * 获取给定日期所在年份的天数。
     */
    public static int getDaysInYear(LocalDate date) {
        return date == null ? 0 : date.lengthOfYear();
    }

    /**
     * Checks if the given date is the last day of its month.
     * <p>
     * 判断给定日期是否为当月的最后一天。
     */
    public static boolean isLastDayOfMonth(LocalDate date) {
        return date != null && date.equals(lastDayOfMonth(date));
    }

    /**
     * Creates a LocalDate object from year, month, and day.
     * <p>
     * 根据年月日创建 LocalDate 对象。
     */
    public static LocalDate of(int year, int month, int day) {
        return LocalDate.of(year, month, day);
    }

    /**
     * Creates a LocalDate object from YearMonth (first day of the month).
     * <p>
     * 根据 YearMonth 创建 LocalDate 对象（获取该月的第一天）。
     */
    public static LocalDate of(YearMonth yearMonth) {
        return yearMonth == null ? null : yearMonth.atDay(1);
    }

    /**
     * Gets the string representation of current date in default format.
     * <p>
     * 获取当前日期的字符串表示 (默认格式)。
     */
    public static String todayStr() {
        return format(today(), DateFormats.STANDARD_DATE);
    }

    /**
     * Gets the string representation of current date in specified format.
     * <p>
     * 获取当前日期的字符串表示 (指定格式)。
     */
    public static String todayStr(String pattern) {
        return format(today(), pattern);
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