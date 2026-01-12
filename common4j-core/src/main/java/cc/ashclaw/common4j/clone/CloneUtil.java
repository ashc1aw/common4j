// SPDX-License-Identifier: Apache-2.0
// Copyright (c) 2026 AshClaw.

package cc.ashclaw.common4j.clone;

import cc.ashclaw.common4j.exception.CloneException;
import java.io.*;
import java.util.Objects;

/**
 * Utility class for object cloning operations in the Common4J library.
 * <p>
 * Common4J 库中对象克隆操作的工具类。
 * <p>
 * This class provides methods for both shallow cloning and deep cloning of objects,
 * supporting different cloning mechanisms to suit various use cases.
 * <p>
 * 该类提供对象的浅克隆和深克隆方法，支持不同的克隆机制以适应各种使用场景。
 *
 * @author b1itz7
 * @since 1.0.0
 */
public final class CloneUtil {

    /**
     * Private constructor to prevent instantiation.
     * <p>
     * 私有构造函数以防止实例化。
     */
    private CloneUtil() {
        throw new UnsupportedOperationException("CloneUtil cannot be instantiated.");
    }

    /**
     * Performs a deep clone of an object using serialization.
     * <p>
     * 使用序列化执行对象的深克隆。
     *
     * @param <T>   the type of the object to clone
     *              <p>
     *              要克隆的对象类型
     * @param obj   the object to clone, must implement Serializable
     *              <p>
     *              要克隆的对象，必须实现 Serializable 接口
     * @return a deep clone of the object
     *         <p>
     *         对象的深克隆
     * @throws RuntimeException if cloning fails
     *                          <p>
     *                          如果克隆失败
     */
    @SuppressWarnings("unchecked")
    public static <T extends Serializable> T deepClone(T obj) {
        Objects.requireNonNull(obj, "Object to clone cannot be null");
        
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(baos)) {
            
            // Write the object to the output stream
            oos.writeObject(obj);
            
            try (ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
                 ObjectInputStream ois = new ObjectInputStream(bais)) {
                
                // Read the object from the input stream
                return (T) ois.readObject();
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new CloneException("Failed to deep clone object: " + obj.getClass().getName(), e);
        }
    }

    /**
     * Performs a shallow clone of an object using the clone() method.
     * <p>
     * 使用 clone() 方法执行对象的浅克隆。
     *
     * @param <T>   the type of the object to clone
     *              <p>
     *              要克隆的对象类型
     * @param obj   the object to clone, must implement Cloneable
     *              <p>
     *              要克隆的对象，必须实现 Cloneable 接口
     * @return a shallow clone of the object
     *         <p>
     *         对象的浅克隆
     * @throws RuntimeException if cloning fails
     *                          <p>
     *                          如果克隆失败
     */
    @SuppressWarnings("unchecked")
    public static <T extends Cloneable> T shallowClone(T obj) {
        Objects.requireNonNull(obj, "Object to clone cannot be null");
        
        try {
            // Get the clone method from Object class
            java.lang.reflect.Method cloneMethod = obj.getClass().getMethod("clone");
            cloneMethod.setAccessible(true);
            
            // Invoke the clone method
            return (T) cloneMethod.invoke(obj);
        } catch (Exception e) {
            throw new CloneException("Failed to shallow clone object: " + obj.getClass().getName(), e);
        }
    }

    /**
     * Checks if an object can be deep cloned using serialization.
     * <p>
     * 检查对象是否可以使用序列化进行深克隆。
     *
     * @param obj the object to check
     *            <p>
     *            要检查的对象
     * @return true if the object can be deep cloned, false otherwise
     *         <p>
     *         如果对象可以深克隆则返回 true，否则返回 false
     */
    public static boolean isDeepCloneable(Object obj) {
        return obj instanceof Serializable;
    }

    /**
     * Checks if an object can be shallow cloned using the clone() method.
     * <p>
     * 检查对象是否可以使用 clone() 方法进行浅克隆。
     *
     * @param obj the object to check
     *            <p>
     *            要检查的对象
     * @return true if the object can be shallow cloned, false otherwise
     *         <p>
     *         如果对象可以浅克隆则返回 true，否则返回 false
     */
    public static boolean isShallowCloneable(Object obj) {
        return obj instanceof Cloneable;
    }
}
