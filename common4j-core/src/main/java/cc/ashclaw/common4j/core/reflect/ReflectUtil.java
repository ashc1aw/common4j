// SPDX-License-Identifier: Apache-2.0
// Copyright (c) 2026 AshClaw.

package cc.ashclaw.common4j.core.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Utility class for reflection operations.
 * <p>
 * 反射操作工具类。
 * <p>
 * Provides methods for common reflection operations such as creating instances,
 * accessing fields, invoking methods, and more.
 * <p>
 * 提供常见的反射操作方法，如创建实例、访问字段、调用方法等。
 *
 * @author b1itz7
 * @since 1.0.0
 */
public final class ReflectUtil {

    /**
     * Private constructor to prevent instantiation.
     * <p>
     * 私有构造函数，防止实例化。
     */
    private ReflectUtil() {
        throw new UnsupportedOperationException("This class cannot be instantiated.");
    }

    /**
     * Creates an instance of the specified class using its no-argument constructor.
     * <p>
     * 使用无参构造函数创建指定类的实例。
     *
     * @param <T>   the type of the instance
     *              <p>
     *              实例的类型
     * @param clazz the class to instantiate
     *              <p>
     *              要实例化的类
     * @return the created instance
     *         <p>
     *         创建的实例
     * @throws RuntimeException if instantiation fails
     *                          <p>
     *                          如果实例化失败
     */
    public static <T> T newInstance(Class<T> clazz) {
        Objects.requireNonNull(clazz, "Class cannot be null");
        try {
            Constructor<T> constructor = clazz.getDeclaredConstructor();
            constructor.setAccessible(true);
            return constructor.newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Failed to create instance of " + clazz.getName(), e);
        }
    }

    /**
     * Creates an instance of the specified class using the given parameters.
     * <p>
     * 使用给定的参数创建指定类的实例。
     *
     * @param <T>        the type of the instance
     *                   <p>
     *                   实例的类型
     * @param clazz      the class to instantiate
     *                   <p>
     *                   要实例化的类
     * @param paramTypes the parameter types of the constructor
     *                   <p>
     *                   构造函数的参数类型
     * @param params     the parameters to pass to the constructor
     *                   <p>
     *                   传递给构造函数的参数
     * @return the created instance
     *         <p>
     *         创建的实例
     * @throws RuntimeException if instantiation fails
     *                          <p>
     *                          如果实例化失败
     */
    public static <T> T newInstance(Class<T> clazz, Class<?>[] paramTypes, Object[] params) {
        Objects.requireNonNull(clazz, "Class cannot be null");
        try {
            Constructor<T> constructor = clazz.getDeclaredConstructor(paramTypes);
            constructor.setAccessible(true);
            return constructor.newInstance(params);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create instance of " + clazz.getName(), e);
        }
    }

    /**
     * Gets the value of a field from an object, including private fields.
     * <p>
     * 从对象中获取字段的值，包括私有字段。
     *
     * @param object    the object to get the field from
     *                  <p>
     *                  要获取字段的对象
     * @param fieldName the name of the field
     *                  <p>
     *                  字段名
     * @return the value of the field
     *         <p>
     *         字段的值
     * @throws RuntimeException if field access fails
     *                          <p>
     *                          如果字段访问失败
     */
    public static Object getFieldValue(Object object, String fieldName) {
        Objects.requireNonNull(object, "Object cannot be null");
        Objects.requireNonNull(fieldName, "Field name cannot be null");
        return getFieldValue(object.getClass(), object, fieldName);
    }

    /**
     * Gets the value of a static field from a class, including private fields.
     * <p>
     * 从类中获取静态字段的值，包括私有字段。
     *
     * @param clazz     the class to get the field from
     *                  <p>
     *                  要获取字段的类
     * @param fieldName the name of the field
     *                  <p>
     *                  字段名
     * @return the value of the field
     *         <p>
     *         字段的值
     * @throws RuntimeException if field access fails
     *                          <p>
     *                          如果字段访问失败
     */
    public static Object getStaticFieldValue(Class<?> clazz, String fieldName) {
        Objects.requireNonNull(clazz, "Class cannot be null");
        Objects.requireNonNull(fieldName, "Field name cannot be null");
        return getFieldValue(clazz, null, fieldName);
    }

    /**
     * Sets the value of a field in an object, including private fields.
     * <p>
     * 设置对象中字段的值，包括私有字段。
     *
     * @param object    the object to set the field in
     *                  <p>
     *                  要设置字段的对象
     * @param fieldName the name of the field
     *                  <p>
     *                  字段名
     * @param value     the value to set
     *                  <p>
     *                  要设置的值
     * @throws RuntimeException if field access fails
     *                          <p>
     *                          如果字段访问失败
     */
    public static void setFieldValue(Object object, String fieldName, Object value) {
        Objects.requireNonNull(object, "Object cannot be null");
        Objects.requireNonNull(fieldName, "Field name cannot be null");
        setFieldValue(object.getClass(), object, fieldName, value);
    }

    /**
     * Sets the value of a static field in a class, including private fields.
     * <p>
     * 设置类中静态字段的值，包括私有字段。
     *
     * @param clazz     the class to set the field in
     *                  <p>
     *                  要设置字段的类
     * @param fieldName the name of the field
     *                  <p>
     *                  字段名
     * @param value     the value to set
     *                  <p>
     *                  要设置的值
     * @throws RuntimeException if field access fails
     *                          <p>
     *                          如果字段访问失败
     */
    public static void setStaticFieldValue(Class<?> clazz, String fieldName, Object value) {
        Objects.requireNonNull(clazz, "Class cannot be null");
        Objects.requireNonNull(fieldName, "Field name cannot be null");
        setFieldValue(clazz, null, fieldName, value);
    }

    /**
     * Invokes a method on an object, including private methods.
     * <p>
     * 在对象上调用方法，包括私有方法。
     *
     * @param object     the object to invoke the method on
     *                   <p>
     *                   要调用方法的对象
     * @param methodName the name of the method
     *                   <p>
     *                   方法名
     * @param paramTypes the parameter types of the method
     *                   <p>
     *                   方法的参数类型
     * @param params     the parameters to pass to the method
     *                   <p>
     *                   传递给方法的参数
     * @return the result of the method invocation
     *         <p>
     *         方法调用的结果
     * @throws RuntimeException if method invocation fails
     *                          <p>
     *                          如果方法调用失败
     */
    public static Object invokeMethod(Object object, String methodName, Class<?>[] paramTypes, Object[] params) {
        Objects.requireNonNull(object, "Object cannot be null");
        Objects.requireNonNull(methodName, "Method name cannot be null");
        return invokeMethod(object.getClass(), object, methodName, paramTypes, params);
    }

    /**
     * Invokes a static method on a class, including private methods.
     * <p>
     * 在类上调用静态方法，包括私有方法。
     *
     * @param clazz      the class to invoke the method on
     *                   <p>
     *                   要调用方法的类
     * @param methodName the name of the method
     *                   <p>
     *                   方法名
     * @param paramTypes the parameter types of the method
     *                   <p>
     *                   方法的参数类型
     * @param params     the parameters to pass to the method
     *                   <p>
     *                   传递给方法的参数
     * @return the result of the method invocation
     *         <p>
     *         方法调用的结果
     * @throws RuntimeException if method invocation fails
     *                          <p>
     *                          如果方法调用失败
     */
    public static Object invokeStaticMethod(Class<?> clazz, String methodName, Class<?>[] paramTypes, Object[] params) {
        Objects.requireNonNull(clazz, "Class cannot be null");
        Objects.requireNonNull(methodName, "Method name cannot be null");
        return invokeMethod(clazz, null, methodName, paramTypes, params);
    }

    /**
     * Gets all fields of a class, including private fields and fields from superclasses.
     * <p>
     * 获取类的所有字段，包括私有字段和父类的字段。
     *
     * @param clazz the class to get fields from
     *              <p>
     *              要获取字段的类
     * @return list of all fields
     *         <p>
     *         所有字段的列表
     */
    public static List<Field> getAllFields(Class<?> clazz) {
        Objects.requireNonNull(clazz, "Class cannot be null");
        List<Field> fields = new ArrayList<>();
        Class<?> currentClass = clazz;
        while (currentClass != null && currentClass != Object.class) {
            fields.addAll(Arrays.asList(currentClass.getDeclaredFields()));
            currentClass = currentClass.getSuperclass();
        }
        return fields;
    }

    /**
     * Gets all methods of a class, including private methods and methods from superclasses.
     * <p>
     * 获取类的所有方法，包括私有方法和父类的方法。
     *
     * @param clazz the class to get methods from
     *              <p>
     *              要获取方法的类
     * @return list of all methods
     *         <p>
     *         所有方法的列表
     */
    public static List<Method> getAllMethods(Class<?> clazz) {
        Objects.requireNonNull(clazz, "Class cannot be null");
        List<Method> methods = new ArrayList<>();
        Class<?> currentClass = clazz;
        while (currentClass != null && currentClass != Object.class) {
            methods.addAll(Arrays.asList(currentClass.getDeclaredMethods()));
            currentClass = currentClass.getSuperclass();
        }
        return methods;
    }

    /**
     * Checks if a class has a specific method.
     * <p>
     * 检查类是否有特定的方法。
     *
     * @param clazz      the class to check
     *                   <p>
     *                   要检查的类
     * @param methodName the name of the method
     *                   <p>
     *                   方法名
     * @param paramTypes the parameter types of the method
     *                   <p>
     *                   方法的参数类型
     * @return true if the class has the method, false otherwise
     *         <p>
     *         如果类有该方法则返回true，否则返回false
     */
    public static boolean hasMethod(Class<?> clazz, String methodName, Class<?>... paramTypes) {
        Objects.requireNonNull(clazz, "Class cannot be null");
        Objects.requireNonNull(methodName, "Method name cannot be null");
        try {
            clazz.getDeclaredMethod(methodName, paramTypes);
            return true;
        } catch (NoSuchMethodException e) {
            return false;
        }
    }

    /**
     * Checks if a class has a specific field.
     * <p>
     * 检查类是否有特定的字段。
     *
     * @param clazz     the class to check
     *                  <p>
     *                  要检查的类
     * @param fieldName the name of the field
     *                  <p>
     *                  字段名
     * @return true if the class has the field, false otherwise
     *         <p>
     *         如果类有该字段则返回true，否则返回false
     */
    public static boolean hasField(Class<?> clazz, String fieldName) {
        Objects.requireNonNull(clazz, "Class cannot be null");
        Objects.requireNonNull(fieldName, "Field name cannot be null");
        try {
            clazz.getDeclaredField(fieldName);
            return true;
        } catch (NoSuchFieldException e) {
            return false;
        }
    }

    /**
     * Gets the field with the specified name from a class, including private fields.
     * <p>
     * 从类中获取指定名称的字段，包括私有字段。
     *
     * @param clazz     the class to get the field from
     *                  <p>
     *                  要获取字段的类
     * @param fieldName the name of the field
     *                  <p>
     *                  字段名
     * @return the field
     *         <p>
     *         字段
     * @throws RuntimeException if field not found
     *                          <p>
     *                          如果字段未找到
     */
    public static Field getField(Class<?> clazz, String fieldName) {
        Objects.requireNonNull(clazz, "Class cannot be null");
        Objects.requireNonNull(fieldName, "Field name cannot be null");
        try {
            Field field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
            return field;
        } catch (NoSuchFieldException e) {
            throw new RuntimeException("Field " + fieldName + " not found in class " + clazz.getName(), e);
        }
    }

    /**
     * Gets the method with the specified name and parameter types from a class, including private methods.
     * <p>
     * 从类中获取指定名称和参数类型的方法，包括私有方法。
     *
     * @param clazz      the class to get the method from
     *                   <p>
     *                   要获取方法的类
     * @param methodName the name of the method
     *                   <p>
     *                   方法名
     * @param paramTypes the parameter types of the method
     *                   <p>
     *                   方法的参数类型
     * @return the method
     *         <p>
     *         方法
     * @throws RuntimeException if method not found
     *                          <p>
     *                          如果方法未找到
     */
    public static Method getMethod(Class<?> clazz, String methodName, Class<?>... paramTypes) {
        Objects.requireNonNull(clazz, "Class cannot be null");
        Objects.requireNonNull(methodName, "Method name cannot be null");
        try {
            Method method = clazz.getDeclaredMethod(methodName, paramTypes);
            method.setAccessible(true);
            return method;
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("Method " + methodName + " not found in class " + clazz.getName(), e);
        }
    }

    /**
     * Private helper method to get field value.
     * <p>
     * 获取字段值的私有辅助方法。
     */
    private static Object getFieldValue(Class<?> clazz, Object object, String fieldName) {
        try {
            Field field = getField(clazz, fieldName);
            return field.get(object);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Failed to get value of field " + fieldName, e);
        }
    }

    /**
     * Private helper method to set field value.
     * <p>
     * 设置字段值的私有辅助方法。
     */
    private static void setFieldValue(Class<?> clazz, Object object, String fieldName, Object value) {
        try {
            Field field = getField(clazz, fieldName);
            field.set(object, value);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Failed to set value of field " + fieldName, e);
        }
    }

    /**
     * Private helper method to invoke method.
     * <p>
     * 调用方法的私有辅助方法。
     */
    private static Object invokeMethod(Class<?> clazz, Object object, String methodName, Class<?>[] paramTypes, Object[] params) {
        try {
            Method method = getMethod(clazz, methodName, paramTypes);
            return method.invoke(object, params);
        } catch (Exception e) {
            throw new RuntimeException("Failed to invoke method " + methodName, e);
        }
    }
}
