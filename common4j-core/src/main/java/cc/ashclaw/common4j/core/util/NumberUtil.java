// SPDX-License-Identifier: Apache-2.0
// Copyright (c) 2026 AshClaw.

package cc.ashclaw.common4j.core.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Utility class for number operations.
 * <p>
 * 数字操作工具类。
 * <p>
 * Provides methods for number conversion, validation, and other number-related operations.
 * <p>
 * 提供数字转换、验证以及其他与数字相关的操作方法。
 *
 * @author b1itz7
 * @since 1.0.0
 */
public final class NumberUtil {

    /**
     * Private constructor to prevent instantiation.
     * <p>
     * 私有构造函数，防止实例化。
     */
    private NumberUtil() {
        throw new UnsupportedOperationException("NumberUtil cannot be instantiated.");
    }

    /**
     * Checks if a string can be parsed to an integer.
     * <p>
     * 检查字符串是否可以解析为整数。
     *
     * @param str the string to check
     *            <p>
     *            要检查的字符串
     * @return true if the string can be parsed to an integer, false otherwise
     *         <p>
     *         如果字符串可以解析为整数则返回true，否则返回false
     */
    public static boolean isInteger(String str) {
        if (str == null) {
            return false;
        }
        try {
            return Integer.parseInt(str) >= 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Checks if a string can be parsed to a long.
     * <p>
     * 检查字符串是否可以解析为长整数。
     *
     * @param str the string to check
     *            <p>
     *            要检查的字符串
     * @return true if the string can be parsed to a long, false otherwise
     *         <p>
     *         如果字符串可以解析为长整数则返回true，否则返回false
     */
    public static boolean isLong(String str) {
        if (str == null) {
            return false;
        }
        try {
            return Long.parseLong(str) >= 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Checks if a string can be parsed to a double.
     * <p>
     * 检查字符串是否可以解析为双精度浮点数。
     *
     * @param str the string to check
     *            <p>
     *            要检查的字符串
     * @return true if the string can be parsed to a double, false otherwise
     *         <p>
     *         如果字符串可以解析为双精度浮点数则返回true，否则返回false
     */
    public static boolean isDouble(String str) {
        if (str == null) {
            return false;
        }
        try {
            return Double.parseDouble(str) >= 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Parses a string to an integer, returning a default value if parsing fails.
     * <p>
     * 将字符串解析为整数，如果解析失败则返回默认值。
     *
     * @param str          the string to parse
     *                     <p>
     *                     要解析的字符串
     * @param defaultValue the default value to return if parsing fails
     *                     <p>
     *                     如果解析失败则返回的默认值
     * @return the parsed integer or the default value
     *         <p>
     *         解析后的整数或默认值
     */
    public static int parseInt(String str, int defaultValue) {
        if (str == null) {
            return defaultValue;
        }
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    /**
     * Parses a string to a long, returning a default value if parsing fails.
     * <p>
     * 将字符串解析为长整数，如果解析失败则返回默认值。
     *
     * @param str          the string to parse
     *                     <p>
     *                     要解析的字符串
     * @param defaultValue the default value to return if parsing fails
     *                     <p>
     *                     如果解析失败则返回的默认值
     * @return the parsed long or the default value
     *         <p>
     *         解析后的长整数或默认值
     */
    public static long parseLong(String str, long defaultValue) {
        if (str == null) {
            return defaultValue;
        }
        try {
            return Long.parseLong(str);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    /**
     * Parses a string to a double, returning a default value if parsing fails.
     * <p>
     * 将字符串解析为双精度浮点数，如果解析失败则返回默认值。
     *
     * @param str          the string to parse
     *                     <p>
     *                     要解析的字符串
     * @param defaultValue the default value to return if parsing fails
     *                     <p>
     *                     如果解析失败则返回的默认值
     * @return the parsed double or the default value
     *         <p>
     *         解析后的双精度浮点数或默认值
     */
    public static double parseDouble(String str, double defaultValue) {
        if (str == null) {
            return defaultValue;
        }
        try {
            return Double.parseDouble(str);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    /**
     * Checks if a number is between two values (inclusive).
     * <p>
     * 检查数字是否在两个值之间（包括边界）。
     *
     * @param value the number to check
     *              <p>
     *              要检查的数字
     * @param min   the minimum value
     *              <p>
     *              最小值
     * @param max   the maximum value
     *              <p>
     *              最大值
     * @return true if the number is between min and max (inclusive), false otherwise
     *         <p>
     *         如果数字在min和max之间（包括边界）则返回true，否则返回false
     */
    public static boolean isBetween(int value, int min, int max) {
        return value >= min && value <= max;
    }

    /**
     * Checks if a number is between two values (inclusive).
     * <p>
     * 检查数字是否在两个值之间（包括边界）。
     *
     * @param value the number to check
     *              <p>
     *              要检查的数字
     * @param min   the minimum value
     *              <p>
     *              最小值
     * @param max   the maximum value
     *              <p>
     *              最大值
     * @return true if the number is between min and max (inclusive), false otherwise
     *         <p>
     *         如果数字在min和max之间（包括边界）则返回true，否则返回false
     */
    public static boolean isBetween(long value, long min, long max) {
        return value >= min && value <= max;
    }

    /**
     * Checks if a number is between two values (inclusive).
     * <p>
     * 检查数字是否在两个值之间（包括边界）。
     *
     * @param value the number to check
     *              <p>
     *              要检查的数字
     * @param min   the minimum value
     *              <p>
     *              最小值
     * @param max   the maximum value
     *              <p>
     *              最大值
     * @return true if the number is between min and max (inclusive), false otherwise
     *         <p>
     *         如果数字在min和max之间（包括边界）则返回true，否则返回false
     */
    public static boolean isBetween(double value, double min, double max) {
        return value >= min && value <= max;
    }

    /**
     * Rounds a double to a specified number of decimal places.
     * <p>
     * 将双精度浮点数四舍五入到指定的小数位数。
     *
     * @param value  the value to round
     *               <p>
     *               要四舍五入的值
     * @param places the number of decimal places
     *               <p>
     *               小数位数
     * @return the rounded value
     *         <p>
     *         四舍五入后的值
     */
    public static double round(double value, int places) {
        if (places < 0) {
            throw new IllegalArgumentException("Number of decimal places must be non-negative");
        }
        long factor = (long) Math.pow(10, places);
        value = value * factor;
        return Math.round(value) / (double) factor;
    }

    /**
     * Checks if a string can be parsed to a BigDecimal.
     * <p>
     * 检查字符串是否可以解析为BigDecimal。
     *
     * @param str the string to check
     *            <p>
     *            要检查的字符串
     * @return true if the string can be parsed to a BigDecimal, false otherwise
     *         <p>
     *         如果字符串可以解析为BigDecimal则返回true，否则返回false
     */
    public static boolean isBigDecimal(String str) {
        if (str == null) {
            return false;
        }
        try {
            new BigDecimal(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Parses a string to a BigDecimal, returning a default value if parsing fails.
     * <p>
     * 将字符串解析为BigDecimal，如果解析失败则返回默认值。
     *
     * @param str          the string to parse
     *                     <p>
     *                     要解析的字符串
     * @param defaultValue the default value to return if parsing fails
     *                     <p>
     *                     如果解析失败则返回的默认值
     * @return the parsed BigDecimal or the default value
     *         <p>
     *         解析后的BigDecimal或默认值
     */
    public static BigDecimal parseBigDecimal(String str, BigDecimal defaultValue) {
        if (str == null) {
            return defaultValue;
        }
        try {
            return new BigDecimal(str);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    /**
     * Checks if a BigDecimal is between two values (inclusive).
     * <p>
     * 检查BigDecimal是否在两个值之间（包括边界）。
     *
     * @param value the number to check
     *              <p>
     *              要检查的数字
     * @param min   the minimum value
     *              <p>
     *              最小值
     * @param max   the maximum value
     *              <p>
     *              最大值
     * @return true if the number is between min and max (inclusive), false otherwise
     *         <p>
     *         如果数字在min和max之间（包括边界）则返回true，否则返回false
     */
    public static boolean isBetween(BigDecimal value, BigDecimal min, BigDecimal max) {
        return value.compareTo(min) >= 0 && value.compareTo(max) <= 0;
    }

    /**
     * Rounds a BigDecimal to a specified number of decimal places using HALF_UP rounding mode.
     * <p>
     * 将BigDecimal四舍五入到指定的小数位数，使用HALF_UP舍入模式。
     *
     * @param value  the value to round
     *               <p>
     *               要四舍五入的值
     * @param places the number of decimal places
     *               <p>
     *               小数位数
     * @return the rounded value
     *         <p>
     *         四舍五入后的值
     */
    public static BigDecimal round(BigDecimal value, int places) {
        if (places < 0) {
            throw new IllegalArgumentException("Number of decimal places must be non-negative");
        }
        return value.setScale(places, RoundingMode.HALF_UP);
    }

    /**
     * Multiplies two BigDecimals and rounds to the specified number of decimal places using HALF_UP rounding mode.
     * <p>
     * 乘以两个BigDecimal并四舍五入到指定的小数位数，使用HALF_UP舍入模式。
     *
     * @param multiplicand the first value to multiply
     *                     <p>
     *                     被乘数
     * @param multiplier   the second value to multiply
     *                     <p>
     *                     乘数
     * @param places       the number of decimal places to round to
     *                     <p>
     *                     要四舍五入到的小数位数
     * @return the product of the two values, rounded to the specified number of decimal places
     *         <p>
     *         两个值的乘积，四舍五入到指定的小数位数
     */
    public static BigDecimal multiply(BigDecimal multiplicand, BigDecimal multiplier, int places) {
        return multiply(multiplicand, multiplier, places, RoundingMode.HALF_UP);
    }

    /**
     * Multiplies two BigDecimals and rounds to the specified number of decimal places using the specified rounding mode.
     * <p>
     * 乘以两个BigDecimal并四舍五入到指定的小数位数，使用指定的舍入模式。
     *
     * @param multiplicand the first value to multiply
     *                     <p>
     *                     被乘数
     * @param multiplier   the second value to multiply
     *                     <p>
     *                     乘数
     * @param places       the number of decimal places to round to
     *                     <p>
     *                     要四舍五入到的小数位数
     * @param roundingMode the rounding mode to use
     *                     <p>
     *                     要使用的舍入模式
     * @return the product of the two values, rounded to the specified number of decimal places
     *         <p>
     *         两个值的乘积，四舍五入到指定的小数位数
     */
    public static BigDecimal multiply(BigDecimal multiplicand, BigDecimal multiplier, int places, RoundingMode roundingMode) {
        if (places < 0) {
            throw new IllegalArgumentException("Number of decimal places must be non-negative");
        }
        if (roundingMode == null) {
            throw new IllegalArgumentException("Rounding mode cannot be null");
        }
        return multiplicand.multiply(multiplier).setScale(places, roundingMode);
    }

    /**
     * Divides two BigDecimals and rounds to the specified number of decimal places using HALF_UP rounding mode.
     * <p>
     * 除以两个BigDecimal并四舍五入到指定的小数位数，使用HALF_UP舍入模式。
     *
     * @param dividend the value to be divided
     *                 <p>
     *                 被除数
     * @param divisor  the value to divide by
     *                 <p>
     *                 除数
     * @param places   the number of decimal places to round to
     *                 <p>
     *                 要四舍五入到的小数位数
     * @return the quotient of the two values, rounded to the specified number of decimal places
     *         <p>
     *         两个值的商，四舍五入到指定的小数位数
     */
    public static BigDecimal divide(BigDecimal dividend, BigDecimal divisor, int places) {
        return divide(dividend, divisor, places, RoundingMode.HALF_UP);
    }

    /**
     * Divides two BigDecimals and rounds to the specified number of decimal places using the specified rounding mode.
     * <p>
     * 除以两个BigDecimal并四舍五入到指定的小数位数，使用指定的舍入模式。
     *
     * @param dividend     the value to be divided
     *                     <p>
     *                     被除数
     * @param divisor      the value to divide by
     *                     <p>
     *                     除数
     * @param places       the number of decimal places to round to
     *                     <p>
     *                     要四舍五入到的小数位数
     * @param roundingMode the rounding mode to use
     *                     <p>
     *                     要使用的舍入模式
     * @return the quotient of the two values, rounded to the specified number of decimal places
     *         <p>
     *         两个值的商，四舍五入到指定的小数位数
     */
    public static BigDecimal divide(BigDecimal dividend, BigDecimal divisor, int places, RoundingMode roundingMode) {
        if (places < 0) {
            throw new IllegalArgumentException("Number of decimal places must be non-negative");
        }
        if (roundingMode == null) {
            throw new IllegalArgumentException("Rounding mode cannot be null");
        }
        if (BigDecimal.ZERO.compareTo(divisor) == 0) {
            throw new ArithmeticException("Cannot divide by zero");
        }
        return dividend.divide(divisor, places, roundingMode);
    }
}
