// SPDX-License-Identifier: Apache-2.0
// Copyright (c) 2026 AshClaw.

package cc.ashclaw.common4j.lang;

/**
 * Base class for tuple implementations.
 * <p>
 * 元组实现的基类。
 * <p>
 * This class provides common functionality for tuple implementations.
 * <p>
 * 此类为元组实现提供通用功能。
 *
 * @author b1itz7
 * @since 1.0.0
 */
public abstract class Tuple {
    /**
     * Gets the number of elements in the tuple.
     * <p>
     * 获取元组中的元素数量。
     *
     * @return the number of elements
     *         <p>
     *         元素数量
     */
    public abstract int size();

    /**
     * Gets the element at the specified index.
     * <p>
     * 获取指定索引处的元素。
     *
     * @param index the index of the element to get
     *              <p>
     *              要获取的元素索引
     * @return the element at the specified index
     *         <p>
     *         指定索引处的元素
     * @throws IndexOutOfBoundsException if the index is out of bounds
     *                                  <p>
     *                                  如果索引越界则抛出异常
     */
    public abstract Object get(int index);

    /**
     * Returns a string representation of the tuple.
     * <p>
     * 返回元组的字符串表示。
     *
     * @return a string representation of the tuple
     *         <p>
     *         元组的字符串表示
     */
    @Override
    public abstract String toString();

    /**
     * Checks if this tuple is equal to another object.
     * <p>
     * 检查此元组是否与另一个对象相等。
     *
     * @param obj the object to compare with
     *            <p>
     *            要比较的对象
     * @return true if the objects are equal, false otherwise
     *         <p>
     *         如果对象相等返回true，否则返回false
     */
    @Override
    public abstract boolean equals(Object obj);

    /**
     * Computes the hash code for this tuple.
     * <p>
     * 计算此元组的哈希码。
     *
     * @return the hash code of this tuple
     *         <p>
     *         元组的哈希码
     */
    @Override
    public abstract int hashCode();
}


/**
 * A tuple with 2 elements (Pair alternative).
 * <p>
 * 包含2个元素的元组（Pair的替代方案）。
 *
 * @param <T1> the type of the first element
 *            <p>
 *            第一个元素的类型
 * @param <T2> the type of the second element
 *            <p>
 *            第二个元素的类型
 */
class Tuple2<T1, T2> extends Tuple {
    private final T1 t1;
    private final T2 t2;

    /**
     * Creates a new Tuple2 with the specified elements.
     * <p>
     * 使用指定的元素创建新的Tuple2。
     *
     * @param t1 the first element
     *           <p>
     *           第一个元素
     * @param t2 the second element
     *           <p>
     *           第二个元素
     */
    public Tuple2(T1 t1, T2 t2) {
        this.t1 = t1;
        this.t2 = t2;
    }

    /**
     * Creates a new Tuple2 with the specified elements.
     * <p>
     * 使用指定的元素创建新的Tuple2。
     *
     * @param t1 the first element
     *           <p>
     *           第一个元素
     * @param t2 the second element
     *           <p>
     *           第二个元素
     * @return a new Tuple2 containing the specified elements
     *         <p>
     *         包含指定元素的新Tuple2
     */
    public static <T1, T2> Tuple2<T1, T2> of(T1 t1, T2 t2) {
        return new Tuple2<>(t1, t2);
    }

    /**
     * Gets the first element.
     * <p>
     * 获取第一个元素。
     *
     * @return the first element
     *         <p>
     *         第一个元素
     */
    public T1 getT1() {
        return t1;
    }

    /**
     * Gets the second element.
     * <p>
     * 获取第二个元素。
     *
     * @return the second element
     *         <p>
     *         第二个元素
     */
    public T2 getT2() {
        return t2;
    }

    @Override
    public int size() {
        return 2;
    }

    @Override
    public Object get(int index) {
        switch (index) {
            case 0: return t1;
            case 1: return t2;
            default: throw new IndexOutOfBoundsException("Index: " + index + ", Size: 2");
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Tuple2)) {
            return false;
        }
        Tuple2<?, ?> other = (Tuple2<?, ?>) obj;
        return ObjectUtil.equals(t1, other.t1) && ObjectUtil.equals(t2, other.t2);
    }

    @Override
    public int hashCode() {
        int result = ObjectUtil.hashCode(t1);
        result = 31 * result + ObjectUtil.hashCode(t2);
        return result;
    }

    @Override
    public String toString() {
        return "Tuple2{" +
                "t1=" + t1 +
                ", t2=" + t2 +
                '}';
    }
}


/**
 * A tuple with 3 elements.
 * <p>
 * 包含3个元素的元组。
 *
 * @param <T1> the type of the first element
 *            <p>
 *            第一个元素的类型
 * @param <T2> the type of the second element
 *            <p>
 *            第二个元素的类型
 * @param <T3> the type of the third element
 *            <p>
 *            第三个元素的类型
 */
class Tuple3<T1, T2, T3> extends Tuple {
    private final T1 t1;
    private final T2 t2;
    private final T3 t3;

    /**
     * Creates a new Tuple3 with the specified elements.
     * <p>
     * 使用指定的元素创建新的Tuple3。
     *
     * @param t1 the first element
     *           <p>
     *           第一个元素
     * @param t2 the second element
     *           <p>
     *           第二个元素
     * @param t3 the third element
     *           <p>
     *           第三个元素
     */
    public Tuple3(T1 t1, T2 t2, T3 t3) {
        this.t1 = t1;
        this.t2 = t2;
        this.t3 = t3;
    }

    /**
     * Creates a new Tuple3 with the specified elements.
     * <p>
     * 使用指定的元素创建新的Tuple3。
     *
     * @param t1 the first element
     *           <p>
     *           第一个元素
     * @param t2 the second element
     *           <p>
     *           第二个元素
     * @param t3 the third element
     *           <p>
     *           第三个元素
     * @return a new Tuple3 containing the specified elements
     *         <p>
     *         包含指定元素的新Tuple3
     */
    public static <T1, T2, T3> Tuple3<T1, T2, T3> of(T1 t1, T2 t2, T3 t3) {
        return new Tuple3<>(t1, t2, t3);
    }

    /**
     * Gets the first element.
     * <p>
     * 获取第一个元素。
     *
     * @return the first element
     *         <p>
     *         第一个元素
     */
    public T1 getT1() {
        return t1;
    }

    /**
     * Gets the second element.
     * <p>
     * 获取第二个元素。
     *
     * @return the second element
     *         <p>
     *         第二个元素
     */
    public T2 getT2() {
        return t2;
    }

    /**
     * Gets the third element.
     * <p>
     * 获取第三个元素。
     *
     * @return the third element
     *         <p>
     *         第三个元素
     */
    public T3 getT3() {
        return t3;
    }

    @Override
    public int size() {
        return 3;
    }

    @Override
    public Object get(int index) {
        switch (index) {
            case 0: return t1;
            case 1: return t2;
            case 2: return t3;
            default: throw new IndexOutOfBoundsException("Index: " + index + ", Size: 3");
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Tuple3)) {
            return false;
        }
        Tuple3<?, ?, ?> other = (Tuple3<?, ?, ?>) obj;
        return ObjectUtil.equals(t1, other.t1) && 
               ObjectUtil.equals(t2, other.t2) && 
               ObjectUtil.equals(t3, other.t3);
    }

    @Override
    public int hashCode() {
        int result = ObjectUtil.hashCode(t1);
        result = 31 * result + ObjectUtil.hashCode(t2);
        result = 31 * result + ObjectUtil.hashCode(t3);
        return result;
    }

    @Override
    public String toString() {
        return "Tuple3{" +
                "t1=" + t1 +
                ", t2=" + t2 +
                ", t3=" + t3 +
                '}';
    }
}