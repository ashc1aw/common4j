// SPDX-License-Identifier: Apache-2.0
// Copyright (c) 2026 AshClaw.

package cc.ashclaw.common4j.util;

/**
 * Utility class for common string operations.
 * <p>
 * 字符串工具类，提供常用的字符串操作方法。
 * <p>
 * This class provides null-safe string handling methods including checking for emptiness,
 * trimming, comparison, and other common operations.
 * <p>
 * 此类提供空安全的字符串处理方法，包括检查空值、修剪、比较和其他常见操作。
 *
 * @author b1itz7
 * @since 1.0.0
 */
public class StringUtils {
    /**
     * Checks if a CharSequence is null, empty, or whitespace-only.
     * <p>
     * 检查字符串是否为null、空或仅包含空白字符。
     */
    public static boolean isBlank(CharSequence cs) {
        if (cs == null) {
            return true;
        }

        int strLen = cs.length();
        if (strLen == 0) {
            return true;
        }

        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(cs.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    /**
     * Checks if a CharSequence is null or empty (length is 0).
     * <p>
     * 检查字符串是否为null或空字符串（长度为0）。
     */
    public static boolean isEmpty(CharSequence cs) {
        return cs == null || cs.length() == 0;
    }

    /**
     * Checks if a CharSequence is not null, not empty, and not whitespace-only.
     * <p>
     * 检查字符串是否不为null、不为空且不全是空白字符。
     */
    public static boolean isNotBlank(CharSequence cs) {
        return !isBlank(cs);
    }

    /**
     * Checks if a CharSequence is not null and not empty (length is greater than 0).
     * <p>
     * 检查字符串是否不为null且不为空字符串（长度大于0）。
     */
    public static boolean isNotEmpty(CharSequence cs) {
        return !isEmpty(cs);
    }

    /**
     * Safely trims whitespace from both ends of a String.
     * <p>
     * 安全地去除字符串两端的空白字符。
     */
    public static String trim(String str) {
        return str == null ? null : str.trim();
    }

    /**
     * Trims whitespace from both ends of a String and returns null if the result is empty.
     * <p>
     * 去除字符串两端空白后如果为空则返回null。
     */
    public static String trimToNull(String str) {
        String result = trim(str);
        return isEmpty(result) ? null : result;
    }

    /**
     * Trims whitespace from both ends of a String and returns empty String if null.
     * <p>
     * 去除字符串两端空白后如果为null则返回空字符串。
     */
    public static String trimToEmpty(String str) {
        return str == null ? "" : str.trim();
    }

    /**
     * Returns a default String if the input is blank.
     * <p>
     * 如果字符串为blank则返回默认值。
     */
    public static String defaultIfBlank(String str, String defaultStr) {
        return isBlank(str) ? defaultStr : str;
    }

    /**
     * Returns a default String if the input is empty.
     * <p>
     * 如果字符串为empty则返回默认值。
     */
    public static String defaultIfEmpty(String str, String defaultStr) {
        return isEmpty(str) ? defaultStr : str;
    }

    /**
     * Checks if a CharSequence contains any whitespace characters.
     * <p>
     * 检查字符串是否包含空白字符。
     */
    public static boolean containsWhitespace(CharSequence cs) {
        if (isEmpty(cs)) {
            return false;
        }

        int strLen = cs.length();
        for (int i = 0; i < strLen; i++) {
            if (Character.isWhitespace(cs.charAt(i))) {
                return true;
            }
        }

        return false;
    }

    /**
     * Removes all whitespace characters from a String.
     * <p>
     * 移除字符串中的所有空白字符。
     */
    public static String removeWhitespace(String str) {
        if (isEmpty(str)) {
            return str;
        }

        int strLen = str.length();
        char[] chars = new char[strLen];
        int count = 0;

        for (int i = 0; i < strLen; i++) {
            char ch = str.charAt(i);
            if (!Character.isWhitespace(ch)) {
                chars[count++] = ch;
            }
        }

        if (count == strLen) {
            return str;
        }

        return new String(chars, 0, count);
    }

    /**
     * Safely compares two CharSequence instances for equality.
     * <p>
     * 安全地比较两个字符串是否相等。
     */
    public static boolean equals(CharSequence cs1, CharSequence cs2) {
        if (cs1 == cs2) {
            return true;
        }
        if (cs1 == null || cs2 == null) {
            return false;
        }
        if (cs1.length() != cs2.length()) {
            return false;
        }
        if (cs1 instanceof String && cs2 instanceof String) {
            return cs1.equals(cs2);
        }

        int length = cs1.length();
        for (int i = 0; i < length; i++) {
            if (cs1.charAt(i) != cs2.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Compares two CharSequence instances for equality, ignoring case.
     * <p>
     * 忽略大小写地比较两个字符串是否相等。
     */
    public static boolean equalsIgnoreCase(CharSequence cs1, CharSequence cs2) {
        if (cs1 == cs2) {
            return true;
        }
        if (cs1 == null || cs2 == null) {
            return false;
        }
        if (cs1.length() != cs2.length()) {
            return false;
        }

        int length = cs1.length();
        for (int i = 0; i < length; i++) {
            char ch1 = Character.toLowerCase(cs1.charAt(i));
            char ch2 = Character.toLowerCase(cs2.charAt(i));
            if (ch1 != ch2) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if a CharSequence starts with the specified prefix.
     * <p>
     * 检查字符串是否以指定前缀开始。
     */
    public static boolean startsWith(CharSequence str, CharSequence prefix) {
        if (str == null || prefix == null) {
            return str == prefix;
        }

        int strLen = str.length();
        int prefixLen = prefix.length();

        if (prefixLen > strLen) {
            return false;
        }

        for (int i = 0; i < prefixLen; i++) {
            if (str.charAt(i) != prefix.charAt(i)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Checks if a CharSequence ends with the specified suffix.
     * <p>
     * 检查字符串是否以指定后缀结束。
     */
    public static boolean endsWith(CharSequence str, CharSequence suffix) {
        if (str == null || suffix == null) {
            return str == suffix;
        }

        int strLen = str.length();
        int suffixLen = suffix.length();

        if (suffixLen > strLen) {
            return false;
        }

        for (int i = 0; i < suffixLen; i++) {
            if (str.charAt(strLen - suffixLen + i) != suffix.charAt(i)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Safely extracts a substring from a String.
     * <p>
     * 安全地截取字符串，支持负索引。
     */
    public static String substring(String str, int start) {
        if (str == null) {
            return null;
        }

        int length = str.length();
        if (start < 0) {
            start = length + start;
        }

        if (start < 0) {
            start = 0;
        }
        if (start > length) {
            return "";
        }

        return str.substring(start);
    }

    /**
     * Safely extracts a substring from a String.
     * <p>
     * 安全地截取字符串，支持负索引。
     */
    public static String substring(String str, int start, int end) {
        if (str == null) {
            return null;
        }

        int length = str.length();
        if (start < 0) {
            start = length + start;
        }
        if (end < 0) {
            end = length + end;
        }

        if (start < 0) {
            start = 0;
        }
        if (end > length) {
            end = length;
        }

        if (start > end) {
            return "";
        }

        return str.substring(start, end);
    }
}
