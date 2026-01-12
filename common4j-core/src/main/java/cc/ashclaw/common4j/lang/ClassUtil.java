// SPDX-License-Identifier: Apache-2.0
// Copyright (c) 2026 AshClaw.

package cc.ashclaw.common4j.lang;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Utility class for common class operations.
 * <p>
 * 类工具类，提供常用的类操作方法。
 * <p>
 * This class provides methods for loading classes, checking class types, 
 * getting class information, and working with class inheritance relationships.
 * <p>
 * 此类提供类加载、类型检查、类信息获取和类继承关系处理等方法。
 *
 * @author b1itz7
 * @since 1.0.0
 */
public class ClassUtil {
    /**
     * Loads a class by its fully qualified name.
     * <p>
     * 通过全限定名加载类。
     *
     * @param className the fully qualified name of the class
     *                  <p>
     *                  类的全限定名
     * @return the loaded class
     *         <p>
     *         加载的类
     * @throws ClassNotFoundException if the class cannot be found
     *                                <p>
     *                                如果找不到类则抛出异常
     */
    public static Class<?> loadClass(String className) throws ClassNotFoundException {
        return Class.forName(className);
    }

    /**
     * Loads a class by its fully qualified name, suppressing the ClassNotFoundException.
     * <p>
     * 通过全限定名加载类，抑制ClassNotFoundException异常。
     *
     * @param className the fully qualified name of the class
     *                  <p>
     *                  类的全限定名
     * @return the loaded class, or null if the class cannot be found
     *         <p>
     *         加载的类，如果找不到则返回null
     */
    public static Class<?> loadClassQuietly(String className) {
        try {
            return loadClass(className);
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

    /**
     * Checks if a class is a primitive type.
     * <p>
     * 检查类是否为基本数据类型。
     *
     * @param clazz the class to check
     *              <p>
     *              要检查的类
     * @return true if the class is a primitive type, false otherwise
     *         <p>
     *         如果类是基本数据类型返回true，否则返回false
     */
    public static boolean isPrimitive(Class<?> clazz) {
        return clazz != null && clazz.isPrimitive();
    }

    /**
     * Checks if a class is an array type.
     * <p>
     * 检查类是否为数组类型。
     *
     * @param clazz the class to check
     *              <p>
     *              要检查的类
     * @return true if the class is an array type, false otherwise
     *         <p>
     *         如果类是数组类型返回true，否则返回false
     */
    public static boolean isArray(Class<?> clazz) {
        return clazz != null && clazz.isArray();
    }

    /**
     * Checks if a class is an enum type.
     * <p>
     * 检查类是否为枚举类型。
     *
     * @param clazz the class to check
     *              <p>
     *              要检查的类
     * @return true if the class is an enum type, false otherwise
     *         <p>
     *         如果类是枚举类型返回true，否则返回false
     */
    public static boolean isEnum(Class<?> clazz) {
        return clazz != null && clazz.isEnum();
    }

    /**
     * Checks if a class is an interface type.
     * <p>
     * 检查类是否为接口类型。
     *
     * @param clazz the class to check
     *              <p>
     *              要检查的类
     * @return true if the class is an interface type, false otherwise
     *         <p>
     *         如果类是接口类型返回true，否则返回false
     */
    public static boolean isInterface(Class<?> clazz) {
        return clazz != null && clazz.isInterface();
    }

    /**
     * Checks if a class is a member class (inner class).
     * <p>
     * 检查类是否为成员类（内部类）。
     *
     * @param clazz the class to check
     *              <p>
     *              要检查的类
     * @return true if the class is a member class, false otherwise
     *         <p>
     *         如果类是成员类返回true，否则返回false
     */
    public static boolean isMemberClass(Class<?> clazz) {
        return clazz != null && clazz.isMemberClass();
    }

    /**
     * Checks if a class is a static inner class.
     * <p>
     * 检查类是否为静态内部类。
     *
     * @param clazz the class to check
     *              <p>
     *              要检查的类
     * @return true if the class is a static inner class, false otherwise
     *         <p>
     *         如果类是静态内部类返回true，否则返回false
     */
    public static boolean isStaticInnerClass(Class<?> clazz) {
        return isMemberClass(clazz) && !isInnerClass(clazz);
    }

    /**
     * Checks if a class is a non-static inner class.
     * <p>
     * 检查类是否为非静态内部类。
     *
     * @param clazz the class to check
     *              <p>
     *              要检查的类
     * @return true if the class is a non-static inner class, false otherwise
     *         <p>
     *         如果类是非静态内部类返回true，否则返回false
     */
    public static boolean isInnerClass(Class<?> clazz) {
        return isMemberClass(clazz) && !java.lang.reflect.Modifier.isStatic(clazz.getModifiers());
    }

    /**
     * Gets the simple name of a class, handling array types properly.
     * <p>
     * 获取类的简单名称，正确处理数组类型。
     *
     * @param clazz the class to get the name for
     *              <p>
     *              要获取名称的类
     * @return the simple name of the class
     *         <p>
     *         类的简单名称
     */
    public static String getSimpleName(Class<?> clazz) {
        if (clazz == null) {
            return null;
        }
        return clazz.getSimpleName();
    }

    /**
     * Gets the fully qualified name of a class, handling array types properly.
     * <p>
     * 获取类的全限定名，正确处理数组类型。
     *
     * @param clazz the class to get the name for
     *              <p>
     *              要获取名称的类
     * @return the fully qualified name of the class
     *         <p>
     *         类的全限定名
     */
    public static String getCanonicalName(Class<?> clazz) {
        if (clazz == null) {
            return null;
        }
        return clazz.getCanonicalName();
    }

    /**
     * Gets the package name of a class.
     * <p>
     * 获取类的包名。
     *
     * @param clazz the class to get the package name for
     *              <p>
     *              要获取包名的类
     * @return the package name of the class, or null if the class is in the default package
     *         <p>
     *         类的包名，如果类在默认包中则返回null
     */
    public static String getPackageName(Class<?> clazz) {
        if (clazz == null) {
            return null;
        }
        Package pkg = clazz.getPackage();
        return pkg != null ? pkg.getName() : null;
    }

    /**
     * Checks if a class is assignable from another class (inheritance or interface implementation).
     * <p>
     * 检查一个类是否可以从另一个类赋值（继承或接口实现）。
     *
     * @param superClass the super class or interface
     *                   <p>
     *                   父类或接口
     * @param subClass the subclass to check
     *                 <p>
     *                 要检查的子类
     * @return true if the subclass is assignable to the super class, false otherwise
     *         <p>
     *         如果子类可以赋值给父类返回true，否则返回false
     */
    public static boolean isAssignable(Class<?> superClass, Class<?> subClass) {
        if (superClass == null || subClass == null) {
            return false;
        }
        return superClass.isAssignableFrom(subClass);
    }

    /**
     * Gets the superclass chain of a class.
     * <p>
     * 获取类的父类链。
     *
     * @param clazz the class to get the superclass chain for
     *              <p>
     *              要获取父类链的类
     * @return the list of superclasses, from the immediate superclass to Object
     *         <p>
     *         父类列表，从直接父类到Object类
     */
    public static List<Class<?>> getSuperclassChain(Class<?> clazz) {
        List<Class<?>> superclasses = new ArrayList<>();
        if (clazz == null) {
            return superclasses;
        }
        Class<?> current = clazz.getSuperclass();
        while (current != null) {
            superclasses.add(current);
            current = current.getSuperclass();
        }
        return superclasses;
    }

    /**
     * Gets all interfaces implemented by a class and its superclasses.
     * <p>
     * 获取类及其父类实现的所有接口。
     *
     * @param clazz the class to get interfaces for
     *              <p>
     *              要获取接口的类
     * @return the set of all interfaces implemented by the class and its superclasses
     *         <p>
     *         类及其父类实现的所有接口的集合
     */
    public static List<Class<?>> getAllInterfaces(Class<?> clazz) {
        List<Class<?>> interfaces = new ArrayList<>();
        if (clazz == null) {
            return interfaces;
        }
        collectInterfaces(clazz, interfaces);
        Class<?> current = clazz.getSuperclass();
        while (current != null) {
            collectInterfaces(current, interfaces);
            current = current.getSuperclass();
        }
        return interfaces;
    }

    /**
     * Collects all interfaces implemented by a class.
     * <p>
     * 收集类实现的所有接口。
     *
     * @param clazz the class to collect interfaces for
     *              <p>
     *              要收集接口的类
     * @param interfaces the list to add interfaces to
     *                   <p>
     *                   用于添加接口的列表
     */
    private static void collectInterfaces(Class<?> clazz, List<Class<?>> interfaces) {
        Class<?>[] classInterfaces = clazz.getInterfaces();
        for (Class<?> iface : classInterfaces) {
            if (!interfaces.contains(iface)) {
                interfaces.add(iface);
                collectInterfaces(iface, interfaces);
            }
        }
    }

    /**
     * Creates a new instance of a class using the default constructor.
     * <p>
     * 使用默认构造函数创建类的新实例。
     *
     * @param clazz the class to create an instance of
     *              <p>
     *              要创建实例的类
     * @return a new instance of the class
     *         <p>
     *         类的新实例
     * @throws InstantiationException if the class cannot be instantiated
     *                                <p>
     *                                如果类无法实例化则抛出异常
     * @throws IllegalAccessException if the constructor is not accessible
     *                               <p>
     *                               如果构造函数不可访问则抛出异常
     */
    public static <T> T newInstance(Class<T> clazz) throws InstantiationException, IllegalAccessException {
        return clazz.newInstance();
    }

    /**
     * Creates a new instance of a class using the default constructor, suppressing exceptions.
     * <p>
     * 使用默认构造函数创建类的新实例，抑制异常。
     *
     * @param clazz the class to create an instance of
     *              <p>
     *              要创建实例的类
     * @return a new instance of the class, or null if instantiation fails
     *         <p>
     *         类的新实例，如果实例化失败则返回null
     */
    public static <T> T newInstanceQuietly(Class<T> clazz) {
        try {
            return newInstance(clazz);
        } catch (InstantiationException | IllegalAccessException e) {
            return null;
        }
    }

    /**
     * Gets the component type of an array class.
     * <p>
     * 获取数组类的组件类型。
     *
     * @param arrayClass the array class
     *                   <p>
     *                   数组类
     * @return the component type of the array
     *         <p>
     *         数组的组件类型
     */
    public static Class<?> getComponentType(Class<?> arrayClass) {
        if (arrayClass == null || !arrayClass.isArray()) {
            return null;
        }
        return arrayClass.getComponentType();
    }

    /**
     * Creates a new array of the specified component type and length.
     * <p>
     * 创建指定组件类型和长度的新数组。
     *
     * @param componentType the component type of the array
     *                     <p>
     *                     数组的组件类型
     * @param length the length of the array
     *               <p>
     *               数组的长度
     * @return a new array of the specified component type and length
     *         <p>
     *         指定组件类型和长度的新数组
     */
    public static Object newArray(Class<?> componentType, int length) {
        if (componentType == null) {
            return null;
        }
        return Array.newInstance(componentType, length);
    }
}