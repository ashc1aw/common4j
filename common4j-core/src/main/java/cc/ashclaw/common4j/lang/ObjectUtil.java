// SPDX-License-Identifier: Apache-2.0
// Copyright (c) 2026 AshClaw.

package cc.ashclaw.common4j.lang;

/**
 * Utility class for common object operations.
 * <p>
 * 对象工具类，提供常用的对象操作方法。
 * <p>
 * This class provides null-safe object handling methods including checking for nullity,
 * equality comparison, default value provision, and other common operations.
 * <p>
 * 此类提供空安全的对象处理方法，包括检查空值、相等性比较、默认值提供和其他常见操作。
 *
 * @author b1itz7
 * @since 1.0.0
 */
public class ObjectUtil {
    /**
     * Checks if an object is null.
     * <p>
     * 检查对象是否为null。
     *
     * @param obj the object to check
     *            <p>
     *            要检查的对象
     * @return true if the object is null, false otherwise
     *         <p>
     *         如果对象为null返回true，否则返回false
     */
    public static boolean isNull(Object obj) {
        return obj == null;
    }

    /**
     * Checks if an object is not null.
     * <p>
     * 检查对象是否不为null。
     *
     * @param obj the object to check
     *            <p>
     *            要检查的对象
     * @return true if the object is not null, false otherwise
     *         <p>
     *         如果对象不为null返回true，否则返回false
     */
    public static boolean nonNull(Object obj) {
        return obj != null;
    }

    /**
     * Checks if two objects are equal, handling nulls gracefully.
     * <p>
     * 安全地比较两个对象是否相等，支持null值。
     *
     * @param obj1 the first object to compare
     *             <p>
     *             要比较的第一个对象
     * @param obj2 the second object to compare
     *             <p>
     *             要比较的第二个对象
     * @return true if the objects are equal or both null, false otherwise
     *         <p>
     *         如果两个对象相等或都为null返回true，否则返回false
     */
    public static boolean equals(Object obj1, Object obj2) {
        if (obj1 == obj2) {
            return true;
        }
        return obj1 != null && obj1.equals(obj2);
    }

    /**
     * Returns the first non-null object from the given arguments.
     * <p>
     * 返回第一个非null的对象。
     *
     * @param objects the objects to check
     *                <p>
     *                要检查的对象列表
     * @return the first non-null object, or null if all are null
     *         <p>
     *         第一个非null的对象，如果都为null则返回null
     */
    @SafeVarargs
    public static <T> T firstNonNull(T... objects) {
        if (objects == null) {
            return null;
        }
        for (T obj : objects) {
            if (obj != null) {
                return obj;
            }
        }
        return null;
    }

    /**
     * Returns the default value if the object is null.
     * <p>
     * 如果对象为null则返回默认值。
     *
     * @param obj the object to check
     *            <p>
     *            要检查的对象
     * @param defaultValue the default value to return if the object is null
     *                     <p>
     *                     如果对象为null时返回的默认值
     * @return the object if not null, otherwise the default value
     *         <p>
     *         如果对象不为null则返回对象本身，否则返回默认值
     */
    public static <T> T defaultIfNull(T obj, T defaultValue) {
        return obj != null ? obj : defaultValue;
    }

    /**
     * Returns the hash code of an object, or 0 if the object is null.
     * <p>
     * 返回对象的哈希码，如果对象为null则返回0。
     *
     * @param obj the object to get the hash code for
     *            <p>
     *            要获取哈希码的对象
     * @return the hash code of the object, or 0 if null
     *         <p>
     *         对象的哈希码，如果为null则返回0
     */
    public static int hashCode(Object obj) {
        return obj != null ? obj.hashCode() : 0;
    }

    /**
     * Returns the string representation of an object, or "null" if the object is null.
     * <p>
     * 返回对象的字符串表示，如果对象为null则返回"null"。
     *
     * @param obj the object to get the string representation for
     *            <p>
     *            要获取字符串表示的对象
     * @return the string representation of the object, or "null" if null
     *         <p>
     *         对象的字符串表示，如果为null则返回"null"
     */
    public static String toString(Object obj) {
        return String.valueOf(obj);
    }

    /**
     * Returns the string representation of an object, or the default value if the object is null.
     * <p>
     * 返回对象的字符串表示，如果对象为null则返回默认值。
     *
     * @param obj the object to get the string representation for
     *            <p>
     *            要获取字符串表示的对象
     * @param defaultValue the default value to return if the object is null
     *                     <p>
     *                     如果对象为null时返回的默认值
     * @return the string representation of the object, or the default value if null
     *         <p>
     *         对象的字符串表示，如果为null则返回默认值
     */
    public static String toString(Object obj, String defaultValue) {
        return obj != null ? obj.toString() : defaultValue;
    }

    /**
     * Returns the class of an object, or null if the object is null.
     * <p>
     * 返回对象的类，如果对象为null则返回null。
     *
     * @param obj the object to get the class for
     *            <p>
     *            要获取类的对象
     * @return the class of the object, or null if null
     *         <p>
     *         对象的类，如果为null则返回null
     */
    public static Class<?> getClass(Object obj) {
        return obj != null ? obj.getClass() : null;
    }

    /**
     * Casts an object to the specified type if possible, otherwise returns null.
     * <p>
     * 将对象转换为指定类型，如果转换失败则返回null。
     *
     * @param obj the object to cast
     *            <p>
     *            要转换的对象
     * @param clazz the class to cast to
     *              <p>
     *              要转换的目标类
     * @return the cast object if successful, null otherwise
     *         <p>
     *         如果转换成功则返回转换后的对象，否则返回null
     */
    @SuppressWarnings("unchecked")
    public static <T> T cast(Object obj, Class<T> clazz) {
        if (obj != null && clazz.isInstance(obj)) {
            return (T) obj;
        }
        return null;
    }
}