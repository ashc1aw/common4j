// SPDX-License-Identifier: Apache-2.0
// Copyright (c) 2026 AshClaw.

package cc.ashclaw.common4j.core.convert;

/**
 * Type conversion utility class for the Common4J library.
 * <p>
 * Common4J 库的类型转换工具类。
 * <p>
 * This class provides various methods for converting between different data types,
 * including string, number, boolean, date, and collection conversions.
 * <p>
 * 此类提供各种方法用于在不同数据类型之间进行转换，包括字符串、数字、布尔值、日期和集合转换。
 *
 * @author b1itz7
 * @since 1.1.2
 */
public class Convert {

    /**
     * Converts an object to a string. Returns {@code null} if the object is {@code null}.
     * <p>
     * 将对象转换为字符串。如果对象为 {@code null}，则返回 {@code null}。
     *
     * @param value the object to convert
     *              <p>
     *              要转换的对象
     * @return the string representation of the object, or {@code null} if the object is {@code null}
     *         <p>
     *         对象的字符串表示，如果对象为 {@code null} 则返回 {@code null}
     */
    public static String toStr(Object value) {
        return toStr(value, null);
    }

    /**
     * Converts an object to a string. Returns the default value if the object is {@code null}.
     * <p>
     * 将对象转换为字符串。如果对象为 {@code null}，则返回默认值。
     *
     * @param value        the object to convert
     *                     <p>
     *                     要转换的对象
     * @param defaultValue the default value to return if the object is {@code null}
     *                     <p>
     *                     如果对象为 {@code null} 时要返回的默认值
     * @return the string representation of the object, or the default value if the object is {@code null}
     *         <p>
     *         对象的字符串表示，如果对象为 {@code null} 则返回默认值
     */
    public static String toStr(Object value, String defaultValue) {
        if (value == null) {
            return defaultValue;
        }
        if (value instanceof String) {
            return (String) value;
        }
        return value.toString();
    }

    /**
     * Converts an object to a Long. Returns {@code null} if the conversion fails.
     * <p>
     * 将对象转换为 Long。如果转换失败，则返回 {@code null}。
     *
     * @param value the object to convert
     *              <p>
     *              要转换的对象
     * @return the Long value, or {@code null} if the conversion fails
     *         <p>
     *         Long 值，如果转换失败则返回 {@code null}
     */
    public static Long toLong(Object value) {
        return toLong(value, null);
    }

    /**
     * Converts an object to a Long. Returns the default value if the conversion fails.
     * <p>
     * 将对象转换为 Long。如果转换失败，则返回默认值。
     *
     * @param value        the object to convert
     *                     <p>
     *                     要转换的对象
     * @param defaultValue the default value to return if the conversion fails
     *                     <p>
     *                     如果转换失败时要返回的默认值
     * @return the Long value, or the default value if the conversion fails
     *         <p>
     *         Long 值，如果转换失败则返回默认值
     */
    public static Long toLong(Object value, Long defaultValue) {
        switch (value) {
            case null -> {
                return defaultValue;
            }
            case Long l -> {
                return l;
            }
            case Number number -> {
                return number.longValue();
            }
            default -> {
            }
        }
        try {
            return Long.parseLong(value.toString().trim());
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    /**
     * Converts an object to an Integer. Returns {@code null} if the conversion fails.
     * <p>
     * 将对象转换为 Integer。如果转换失败，则返回 {@code null}。
     *
     * @param value the object to convert
     *              <p>
     *              要转换的对象
     * @return the Integer value, or {@code null} if the conversion fails
     *         <p>
     *         Integer 值，如果转换失败则返回 {@code null}
     */
    public static Integer toInt(Object value) {
        return toInt(value, null);
    }

    /**
     * Converts an object to an Integer. Returns the default value if the conversion fails.
     * <p>
     * 将对象转换为 Integer。如果转换失败，则返回默认值。
     *
     * @param value        the object to convert
     *                     <p>
     *                     要转换的对象
     * @param defaultValue the default value to return if the conversion fails
     *                     <p>
     *                     如果转换失败时要返回的默认值
     * @return the Integer value, or the default value if the conversion fails
     *         <p>
     *         Integer 值，如果转换失败则返回默认值
     */
    public static Integer toInt(Object value, Integer defaultValue) {
        switch (value) {
            case null -> {
                return defaultValue;
            }
            case Integer i -> {
                return i;
            }
            case Number number -> {
                return number.intValue();
            }
            default -> {
            }
        }
        try {
            return Integer.parseInt(value.toString().trim());
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    /**
     * Converts an object to a Double. Returns {@code null} if the conversion fails.
     * <p>
     * 将对象转换为 Double。如果转换失败，则返回 {@code null}。
     *
     * @param value the object to convert
     *              <p>
     *              要转换的对象
     * @return the Double value, or {@code null} if the conversion fails
     *         <p>
     *         Double 值，如果转换失败则返回 {@code null}
     */
    public static Double toDouble(Object value) {
        return toDouble(value, null);
    }

    /**
     * Converts an object to a Double. Returns the default value if the conversion fails.
     * <p>
     * 将对象转换为 Double。如果转换失败，则返回默认值。
     *
     * @param value        the object to convert
     *                     <p>
     *                     要转换的对象
     * @param defaultValue the default value to return if the conversion fails
     *                     <p>
     *                     如果转换失败时要返回的默认值
     * @return the Double value, or the default value if the conversion fails
     *         <p>
     *         Double 值，如果转换失败则返回默认值
     */
    public static Double toDouble(Object value, Double defaultValue) {
        switch (value) {
            case null -> {
                return defaultValue;
            }
            case Double v -> {
                return v;
            }
            case Number number -> {
                return number.doubleValue();
            }
            default -> {
            }
        }
        try {
            return Double.parseDouble(value.toString().trim());
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    /**
     * Converts an object to a Boolean. Returns {@code null} if the conversion fails.
     * <p>
     * 将对象转换为 Boolean。如果转换失败，则返回 {@code null}。
     *
     * @param value the object to convert
     *              <p>
     *              要转换的对象
     * @return the Boolean value, or {@code null} if the conversion fails
     *         <p>
     *         Boolean 值，如果转换失败则返回 {@code null}
     */
    public static Boolean toBoolean(Object value) {
        return toBoolean(value, null);
    }

    /**
     * Converts an object to a Boolean. Returns the default value if the conversion fails.
     * <p>
     * 将对象转换为 Boolean。如果转换失败，则返回默认值。
     *
     * @param value        the object to convert
     *                     <p>
     *                     要转换的对象
     * @param defaultValue the default value to return if the conversion fails
     *                     <p>
     *                     如果转换失败时要返回的默认值
     * @return the Boolean value, or the default value if the conversion fails
     *         <p>
     *         Boolean 值，如果转换失败则返回默认值
     */
    public static Boolean toBoolean(Object value, Boolean defaultValue) {
        if (value == null) {
            return defaultValue;
        }
        if (value instanceof Boolean) {
            return (Boolean) value;
        }
        String strValue = value.toString().trim().toLowerCase();
        if ("true".equals(strValue) || "1".equals(strValue) || "yes".equals(strValue) || "y".equals(strValue)) {
            return true;
        }
        if ("false".equals(strValue) || "0".equals(strValue) || "no".equals(strValue) || "n".equals(strValue)) {
            return false;
        }
        return defaultValue;
    }

    /**
     * Converts an object to a Float. Returns {@code null} if the conversion fails.
     * <p>
     * 将对象转换为 Float。如果转换失败，则返回 {@code null}。
     *
     * @param value the object to convert
     *              <p>
     *              要转换的对象
     * @return the Float value, or {@code null} if the conversion fails
     *         <p>
     *         Float 值，如果转换失败则返回 {@code null}
     */
    public static Float toFloat(Object value) {
        return toFloat(value, null);
    }

    /**
     * Converts an object to a Float. Returns the default value if the conversion fails.
     * <p>
     * 将对象转换为 Float。如果转换失败，则返回默认值。
     *
     * @param value        the object to convert
     *                     <p>
     *                     要转换的对象
     * @param defaultValue the default value to return if the conversion fails
     *                     <p>
     *                     如果转换失败时要返回的默认值
     * @return the Float value, or the default value if the conversion fails
     *         <p>
     *         Float 值，如果转换失败则返回默认值
     */
    public static Float toFloat(Object value, Float defaultValue) {
        switch (value) {
            case null -> {
                return defaultValue;
            }
            case Float v -> {
                return v;
            }
            case Number number -> {
                return number.floatValue();
            }
            default -> {
            }
        }
        try {
            return Float.parseFloat(value.toString().trim());
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    /**
     * Converts an object to a Short. Returns {@code null} if the conversion fails.
     * <p>
     * 将对象转换为 Short。如果转换失败，则返回 {@code null}。
     *
     * @param value the object to convert
     *              <p>
     *              要转换的对象
     * @return the Short value, or {@code null} if the conversion fails
     *         <p>
     *         Short 值，如果转换失败则返回 {@code null}
     */
    public static Short toShort(Object value) {
        return toShort(value, null);
    }

    /**
     * Converts an object to a Short. Returns the default value if the conversion fails.
     * <p>
     * 将对象转换为 Short。如果转换失败，则返回默认值。
     *
     * @param value        the object to convert
     *                     <p>
     *                     要转换的对象
     * @param defaultValue the default value to return if the conversion fails
     *                     <p>
     *                     如果转换失败时要返回的默认值
     * @return the Short value, or the default value if the conversion fails
     *         <p>
     *         Short 值，如果转换失败则返回默认值
     */
    public static Short toShort(Object value, Short defaultValue) {
        switch (value) {
            case null -> {
                return defaultValue;
            }
            case Short i -> {
                return i;
            }
            case Number number -> {
                return number.shortValue();
            }
            default -> {
            }
        }
        try {
            return Short.parseShort(value.toString().trim());
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    /**
     * Converts an object to a Byte. Returns {@code null} if the conversion fails.
     * <p>
     * 将对象转换为 Byte。如果转换失败，则返回 {@code null}。
     *
     * @param value the object to convert
     *              <p>
     *              要转换的对象
     * @return the Byte value, or {@code null} if the conversion fails
     *         <p>
     *         Byte 值，如果转换失败则返回 {@code null}
     */
    public static Byte toByte(Object value) {
        return toByte(value, null);
    }

    /**
     * Converts an object to a Byte. Returns the default value if the conversion fails.
     * <p>
     * 将对象转换为 Byte。如果转换失败，则返回默认值。
     *
     * @param value        the object to convert
     *                     <p>
     *                     要转换的对象
     * @param defaultValue the default value to return if the conversion fails
     *                     <p>
     *                     如果转换失败时要返回的默认值
     * @return the Byte value, or the default value if the conversion fails
     *         <p>
     *         Byte 值，如果转换失败则返回默认值
     */
    public static Byte toByte(Object value, Byte defaultValue) {
        switch (value) {
            case null -> {
                return defaultValue;
            }
            case Byte b -> {
                return b;
            }
            case Number number -> {
                return number.byteValue();
            }
            default -> {
            }
        }
        try {
            return Byte.parseByte(value.toString().trim());
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    /**
     * Converts an object to a Character. Returns {@code null} if the conversion fails.
     * <p>
     * 将对象转换为 Character。如果转换失败，则返回 {@code null}。
     *
     * @param value the object to convert
     *              <p>
     *              要转换的对象
     * @return the Character value, or {@code null} if the conversion fails
     *         <p>
     *         Character 值，如果转换失败则返回 {@code null}
     */
    public static Character toChar(Object value) {
        return toChar(value, null);
    }

    /**
     * Converts an object to a Character. Returns the default value if the conversion fails.
     * <p>
     * 将对象转换为 Character。如果转换失败，则返回默认值。
     *
     * @param value        the object to convert
     *                     <p>
     *                     要转换的对象
     * @param defaultValue the default value to return if the conversion fails
     *                     <p>
     *                     如果转换失败时要返回的默认值
     * @return the Character value, or the default value if the conversion fails
     *         <p>
     *         Character 值，如果转换失败则返回默认值
     */
    public static Character toChar(Object value, Character defaultValue) {
        if (value == null) {
            return defaultValue;
        }
        if (value instanceof Character) {
            return (Character) value;
        }
        String str = value.toString();
        if (str.length() == 1) {
            return str.charAt(0);
        }
        return defaultValue;
    }

    /**
     * Checks if the object can be converted to a number.
     * <p>
     * 检查对象是否可以转换为数字。
     *
     * @param value the object to check
     *              <p>
     *              要检查的对象
     * @return {@code true} if the object can be converted to a number, {@code false} otherwise
     *         <p>
     *         如果对象可以转换为数字则返回 {@code true}，否则返回 {@code false}
     */
    public static boolean isNumber(Object value) {
        if (value == null) {
            return false;
        }
        if (value instanceof Number) {
            return true;
        }
        try {
            Double.parseDouble(value.toString().trim());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Checks if the object can be converted to a boolean.
     * <p>
     * 检查对象是否可以转换为布尔值。
     *
     * @param value the object to check
     *              <p>
     *              要检查的对象
     * @return {@code true} if the object can be converted to a boolean, {@code false} otherwise
     *         <p>
     *         如果对象可以转换为布尔值则返回 {@code true}，否则返回 {@code false}
     */
    public static boolean isBoolean(Object value) {
        if (value == null) {
            return false;
        }
        if (value instanceof Boolean) {
            return true;
        }
        String strValue = value.toString().trim().toLowerCase();
        return "true".equals(strValue) || "false".equals(strValue) ||
               "1".equals(strValue) || "0".equals(strValue) ||
               "yes".equals(strValue) || "no".equals(strValue) ||
               "y".equals(strValue) || "n".equals(strValue);
    }
}