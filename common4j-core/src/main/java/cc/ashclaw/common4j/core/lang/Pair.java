// SPDX-License-Identifier: Apache-2.0
// Copyright (c) 2026 AshClaw.

package cc.ashclaw.common4j.core.lang;

import cc.ashclaw.common4j.core.annotation.ThreadSafe;

/**
 * A simple immutable key-value pair implementation.
 * <p>
 * 简单的不可变键值对实现。
 * <p>
 * This class represents a pair of objects, a key and a value, where both are immutable once created.
 * It provides equals, hashCode, and toString methods for proper object behavior.
 * <p>
 * 此类表示一对对象，一个键和一个值，创建后两者都不可变。
 * 它提供equals、hashCode和toString方法以实现正确的对象行为。
 *
 * @author b1itz7
 * @since 1.0.0
 */
@ThreadSafe
public class Pair<K, V> {
    /** The key part of the pair. */
    private final K key;
    
    /** The value part of the pair. */
    private final V value;

    /**
     * Creates a new pair with the specified key and value.
     * <p>
     * 使用指定的键和值创建新的键值对。
     *
     * @param key the key of the pair
     *            <p>
     *            键值对的键
     * @param value the value of the pair
     *              <p>
     *              键值对的值
     */
    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    /**
     * Creates a new pair with the specified key and value.
     * <p>
     * 使用指定的键和值创建新的键值对。
     *
     * @param key the key of the pair
     *            <p>
     *            键值对的键
     * @param value the value of the pair
     *              <p>
     *              键值对的值
     * @return a new pair containing the specified key and value
     *         <p>
     *         包含指定键和值的新键值对
     */
    public static <K, V> Pair<K, V> of(K key, V value) {
        return new Pair<>(key, value);
    }

    /**
     * Gets the key of this pair.
     * <p>
     * 获取键值对的键。
     *
     * @return the key of this pair
     *         <p>
     *         键值对的键
     */
    public K getKey() {
        return key;
    }

    /**
     * Gets the value of this pair.
     * <p>
     * 获取键值对的值。
     *
     * @return the value of this pair
     *         <p>
     *         键值对的值
     */
    public V getValue() {
        return value;
    }

    /**
     * Checks if this pair is equal to another object.
     * <p>
     * 检查此键值对是否与另一个对象相等。
     *
     * @param obj the object to compare with
     *            <p>
     *            要比较的对象
     * @return true if the objects are equal, false otherwise
     *         <p>
     *         如果对象相等返回true，否则返回false
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Pair)) {
            return false;
        }
        Pair<?, ?> other = (Pair<?, ?>) obj;
        return ObjectUtil.equals(key, other.key) && ObjectUtil.equals(value, other.value);
    }

    /**
     * Computes the hash code for this pair.
     * <p>
     * 计算此键值对的哈希码。
     *
     * @return the hash code of this pair
     *         <p>
     *         键值对的哈希码
     */
    @Override
    public int hashCode() {
        int result = ObjectUtil.hashCode(key);
        result = 31 * result + ObjectUtil.hashCode(value);
        return result;
    }

    /**
     * Returns a string representation of this pair.
     * <p>
     * 返回此键值对的字符串表示。
     *
     * @return a string representation of this pair
     *         <p>
     *         键值对的字符串表示
     */
    @Override
    public String toString() {
        return "Pair{" +
                "key=" + key +
                ", value=" + value +
                '}';
    }
}