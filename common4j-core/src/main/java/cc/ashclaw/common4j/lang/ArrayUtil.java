// SPDX-License-Identifier: Apache-2.0
// Copyright (c) 2026 AshClaw.

package cc.ashclaw.common4j.lang;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Utility class for common array operations.
 * <p>
 * 数组工具类，提供常用的数组操作方法。
 * <p>
 * This class provides null-safe array handling methods including checking for emptiness,
 * getting length, cloning, comparing, finding elements, and other common operations.
 * <p>
 * 此类提供空安全的数组处理方法，包括检查空值、获取长度、克隆、比较、查找元素和其他常见操作。
 *
 * @author b1itz7
 * @since 1.0.0
 */
public class ArrayUtil {
    /**
     * Checks if an array is null.
     * <p>
     * 检查数组是否为null。
     *
     * @param array the array to check
     *              <p>
     *              要检查的数组
     * @return true if the array is null, false otherwise
     *         <p>
     *         如果数组为null返回true，否则返回false
     */
    public static boolean isNull(Object[] array) {
        return array == null;
    }

    /**
     * Checks if an array is not null.
     * <p>
     * 检查数组是否不为null。
     *
     * @param array the array to check
     *              <p>
     *              要检查的数组
     * @return true if the array is not null, false otherwise
     *         <p>
     *         如果数组不为null返回true，否则返回false
     */
    public static boolean nonNull(Object[] array) {
        return array != null;
    }

    /**
     * Checks if an array is empty (null or has length 0).
     * <p>
     * 检查数组是否为空（null或长度为0）。
     *
     * @param array the array to check
     *              <p>
     *              要检查的数组
     * @return true if the array is empty, false otherwise
     *         <p>
     *         如果数组为空返回true，否则返回false
     */
    public static boolean isEmpty(Object[] array) {
        return array == null || array.length == 0;
    }

    /**
     * Checks if an array is not empty (not null and has length greater than 0).
     * <p>
     * 检查数组是否不为空（不为null且长度大于0）。
     *
     * @param array the array to check
     *              <p>
     *              要检查的数组
     * @return true if the array is not empty, false otherwise
     *         <p>
     *         如果数组不为空返回true，否则返回false
     */
    public static boolean isNotEmpty(Object[] array) {
        return array != null && array.length > 0;
    }

    /**
     * Gets the length of an array, or 0 if the array is null.
     * <p>
     * 获取数组的长度，如果数组为null则返回0。
     *
     * @param array the array to get length for
     *              <p>
     *              要获取长度的数组
     * @return the length of the array, or 0 if null
     *         <p>
     *         数组的长度，如果为null则返回0
     */
    public static int length(Object[] array) {
        return array != null ? array.length : 0;
    }

    /**
     * Clones an array.
     * <p>
     * 克隆数组。
     *
     * @param array the array to clone
     *              <p>
     *              要克隆的数组
     * @return the cloned array, or null if the original array is null
     *         <p>
     *         克隆的数组，如果原数组为null则返回null
     */
    public static <T> T[] clone(T[] array) {
        if (array == null) {
            return null;
        }
        return array.clone();
    }

    /**
     * Gets the first element of an array.
     * <p>
     * 获取数组的第一个元素。
     *
     * @param array the array to get the first element from
     *              <p>
     *              要获取第一个元素的数组
     * @return the first element of the array, or null if the array is null or empty
     *         <p>
     *         数组的第一个元素，如果数组为null或空则返回null
     */
    public static <T> T getFirst(T[] array) {
        if (isEmpty(array)) {
            return null;
        }
        return array[0];
    }

    /**
     * Gets the last element of an array.
     * <p>
     * 获取数组的最后一个元素。
     *
     * @param array the array to get the last element from
     *              <p>
     *              要获取最后一个元素的数组
     * @return the last element of the array, or null if the array is null or empty
     *         <p>
     *         数组的最后一个元素，如果数组为null或空则返回null
     */
    public static <T> T getLast(T[] array) {
        if (isEmpty(array)) {
            return null;
        }
        return array[array.length - 1];
    }

    /**
     * Gets an element at a specific index, handling out-of-bounds gracefully.
     * <p>
     * 获取指定索引处的元素，优雅地处理越界情况。
     *
     * @param array the array to get the element from
     *              <p>
     *              要获取元素的数组
     * @param index the index of the element to get
     *              <p>
     *              要获取元素的索引
     * @return the element at the specified index, or null if the array is null or index is out of bounds
     *         <p>
     *         指定索引处的元素，如果数组为null或索引越界则返回null
     */
    public static <T> T get(T[] array, int index) {
        if (array == null || index < 0 || index >= array.length) {
            return null;
        }
        return array[index];
    }

    /**
     * Finds the index of an element in an array.
     * <p>
     * 查找元素在数组中的索引。
     *
     * @param array the array to search
     *              <p>
     *              要搜索的数组
     * @param element the element to find
     *                <p>
     *                要查找的元素
     * @return the index of the element, or -1 if not found or array is null
     *         <p>
     *         元素的索引，如果未找到或数组为null则返回-1
     */
    public static <T> int indexOf(T[] array, T element) {
        if (array == null) {
            return -1;
        }
        if (element == null) {
            for (int i = 0; i < array.length; i++) {
                if (array[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < array.length; i++) {
                if (element.equals(array[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * Finds the last index of an element in an array.
     * <p>
     * 查找元素在数组中最后出现的索引。
     *
     * @param array the array to search
     *              <p>
     *              要搜索的数组
     * @param element the element to find
     *                <p>
     *                要查找的元素
     * @return the last index of the element, or -1 if not found or array is null
     *         <p>
     *         元素最后出现的索引，如果未找到或数组为null则返回-1
     */
    public static <T> int lastIndexOf(T[] array, T element) {
        if (array == null) {
            return -1;
        }
        if (element == null) {
            for (int i = array.length - 1; i >= 0; i--) {
                if (array[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = array.length - 1; i >= 0; i--) {
                if (element.equals(array[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * Checks if an array contains a specific element.
     * <p>
     * 检查数组是否包含特定元素。
     *
     * @param array the array to check
     *              <p>
     *              要检查的数组
     * @param element the element to find
     *                <p>
     *                要查找的元素
     * @return true if the array contains the element, false otherwise
     *         <p>
     *         如果数组包含元素返回true，否则返回false
     */
    public static <T> boolean contains(T[] array, T element) {
        return indexOf(array, element) != -1;
    }

    /**
     * Combines two arrays into a single array.
     * <p>
     * 将两个数组合并为一个数组。
     *
     * @param array1 the first array
     *               <p>
     *               第一个数组
     * @param array2 the second array
     *               <p>
     *               第二个数组
     * @return the combined array, or null if both arrays are null
     *         <p>
     *         合并后的数组，如果两个数组都为null则返回null
     */
    @SuppressWarnings("unchecked")
    public static <T> T[] concat(T[] array1, T[] array2) {
        if (array1 == null && array2 == null) {
            return null;
        }
        if (array1 == null) {
            return clone(array2);
        }
        if (array2 == null) {
            return clone(array1);
        }
        T[] result = (T[]) Array.newInstance(array1.getClass().getComponentType(), array1.length + array2.length);
        System.arraycopy(array1, 0, result, 0, array1.length);
        System.arraycopy(array2, 0, result, array1.length, array2.length);
        return result;
    }

    /**
     * Combines multiple arrays into a single array.
     * <p>
     * 将多个数组合并为一个数组。
     *
     * @param arrays the arrays to combine
     *               <p>
     *               要合并的数组列表
     * @return the combined array, or null if all arrays are null
     *         <p>
     *         合并后的数组，如果所有数组都为null则返回null
     */
    @SuppressWarnings("unchecked")
    public static <T> T[] concatAll(T[]... arrays) {
        if (arrays == null) {
            return null;
        }
        int totalLength = 0;
        for (T[] array : arrays) {
            totalLength += length(array);
        }
        if (totalLength == 0) {
            return null;
        }
        T[] result = null;
        int offset = 0;
        for (T[] array : arrays) {
            if (array != null) {
                if (result == null) {
                    result = (T[]) Array.newInstance(array.getClass().getComponentType(), totalLength);
                }
                System.arraycopy(array, 0, result, offset, array.length);
                offset += array.length;
            }
        }
        return result;
    }

    /**
     * Compares two arrays for equality.
     * <p>
     * 比较两个数组是否相等。
     *
     * @param array1 the first array to compare
     *               <p>
     *               要比较的第一个数组
     * @param array2 the second array to compare
     *               <p>
     *               要比较的第二个数组
     * @return true if the arrays are equal, false otherwise
     *         <p>
     *         如果数组相等返回true，否则返回false
     */
    public static <T> boolean equals(T[] array1, T[] array2) {
        return Arrays.equals(array1, array2);
    }

    /**
     * Sorts an array.
     * <p>
     * 对数组进行排序。
     *
     * @param array the array to sort
     *              <p>
     *              要排序的数组
     * @return the sorted array, or null if the input array is null
     *         <p>
     *         排序后的数组，如果输入数组为null则返回null
     */
    public static <T extends Comparable<? super T>> T[] sort(T[] array) {
        if (array == null) {
            return null;
        }
        T[] result = clone(array);
        Arrays.sort(result);
        return result;
    }

    /**
     * Converts an array to a list.
     * <p>
     * 将数组转换为列表。
     *
     * @param array the array to convert
     *              <p>
     *              要转换的数组
     * @return the list representation of the array, or an empty list if the array is null
     *         <p>
     *         数组的列表表示，如果数组为null则返回空列表
     */
    public static <T> List<T> toList(T[] array) {
        if (array == null) {
            return new ArrayList<>();
        }
        return new ArrayList<>(Arrays.asList(array));
    }

    /**
     * Creates a new array with the specified length and fills it with the given element.
     * <p>
     * 创建指定长度的新数组并用给定元素填充。
     *
     * @param clazz the component type of the array
     *              <p>
     *              数组的组件类型
     * @param length the length of the array
     *               <p>
     *               数组的长度
     * @param element the element to fill the array with
     *                <p>
     *                用于填充数组的元素
     * @return the filled array, or null if the class is null
     *         <p>
     *         填充后的数组，如果类为null则返回null
     */
    @SuppressWarnings("unchecked")
    public static <T> T[] fill(Class<T> clazz, int length, T element) {
        if (clazz == null) {
            return null;
        }
        T[] array = (T[]) Array.newInstance(clazz, length);
        Arrays.fill(array, element);
        return array;
    }

    /**
     * Removes null elements from an array.
     * <p>
     * 从数组中移除null元素。
     *
     * @param array the array to process
     *              <p>
     *              要处理的数组
     * @return the array without null elements, or null if the input array is null
     *         <p>
     *         去除null元素后的数组，如果输入数组为null则返回null
     */
    @SuppressWarnings("unchecked")
    public static <T> T[] removeNulls(T[] array) {
        if (array == null) {
            return null;
        }
        List<T> list = new ArrayList<>();
        for (T element : array) {
            if (element != null) {
                list.add(element);
            }
        }
        return list.toArray((T[]) Array.newInstance(array.getClass().getComponentType(), list.size()));
    }
}