// SPDX-License-Identifier: Apache-2.0
// Copyright (c) 2026 AshClaw.

package cc.ashclaw.common4j.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

/**
 * Test class for ReflectUtil result verification using JUnit 5.
 * <p>
 * ReflectUtil结果验证测试类，使用JUnit 5。
 *
 * @author b1itz7
 * @since 1.0.0
 */
public class ReflectUtilTest {

    /**
     * Test the instance creation methods of ReflectUtil.
     * <p>
     * 测试ReflectUtil的实例创建方法。
     */
    @Test
    void testInstanceCreationMethods() {
        // Test newInstance with no arguments
        TestClass instance = ReflectUtil.newInstance(TestClass.class);
        assertNotNull(instance, "newInstance should return a non-null instance");
        
        // Test newInstance with arguments
        Class<?>[] paramTypes = {String.class, int.class};
        Object[] params = {"Test", 123};
        instance = ReflectUtil.newInstance(TestClass.class, paramTypes, params);
        assertNotNull(instance, "newInstance should return a non-null instance");
        
        // Verify parameters were set correctly
        String name = (String) ReflectUtil.getFieldValue(instance, "name");
        int value = (int) ReflectUtil.getFieldValue(instance, "value");
        assertEquals("Test", name, "newInstance should set name correctly");
        assertEquals(123, value, "newInstance should set value correctly");
    }

    /**
     * Test the field access methods of ReflectUtil.
     * <p>
     * 测试ReflectUtil的字段访问方法。
     */
    @Test
    void testFieldAccessMethods() {
        TestClass instance = new TestClass("Initial", 456);
        
        // Test getFieldValue
        String name = (String) ReflectUtil.getFieldValue(instance, "name");
        assertEquals("Initial", name, "getFieldValue should return the correct value");
        
        // Test setFieldValue
        ReflectUtil.setFieldValue(instance, "name", "Updated");
        name = (String) ReflectUtil.getFieldValue(instance, "name");
        assertEquals("Updated", name, "setFieldValue should update the value");
        
        // Test getStaticFieldValue
        String staticValue = (String) ReflectUtil.getStaticFieldValue(TestClass.class, "STATIC_FIELD");
        assertEquals("static_value", staticValue, "getStaticFieldValue should return the correct value");
        
        // Test setStaticFieldValue
        ReflectUtil.setStaticFieldValue(TestClass.class, "STATIC_FIELD", "updated_static_value");
        staticValue = (String) ReflectUtil.getStaticFieldValue(TestClass.class, "STATIC_FIELD");
        assertEquals("updated_static_value", staticValue, "setStaticFieldValue should update the value");
        
        // Restore original value
        ReflectUtil.setStaticFieldValue(TestClass.class, "STATIC_FIELD", "static_value");
    }

    /**
     * Test the method invocation methods of ReflectUtil.
     * <p>
     * 测试ReflectUtil的方法调用方法。
     */
    @Test
    void testMethodInvocationMethods() {
        TestClass instance = new TestClass("Test", 123);
        
        // Test invokeMethod
        Class<?>[] paramTypes = {String.class};
        Object[] params = {" world"};
        String result = (String) ReflectUtil.invokeMethod(instance, "append", paramTypes, params);
        assertEquals("Test world", result, "invokeMethod should return the correct result");
        
        // Test invokeStaticMethod
        paramTypes = new Class<?>[]{int.class, int.class};
        params = new Object[]{10, 20};
        int intResult = (int) ReflectUtil.invokeStaticMethod(TestClass.class, "add", paramTypes, params);
        assertEquals(30, intResult, "invokeStaticMethod should return the correct result");
    }

    /**
     * Test the reflection information methods of ReflectUtil.
     * <p>
     * 测试ReflectUtil的反射信息方法。
     */
    @Test
    void testReflectionInformationMethods() {
        // Test getAllFields
        List<Field> fields = ReflectUtil.getAllFields(SubTestClass.class);
        assertTrue(fields.size() >= 4, "getAllFields should find all fields from both class and superclass");
        
        // Test getAllMethods
        List<Method> methods = ReflectUtil.getAllMethods(SubTestClass.class);
        assertTrue(methods.size() >= 5, "getAllMethods should find all methods from both class and superclass");
        
        // Test hasMethod
        boolean hasMethod = ReflectUtil.hasMethod(TestClass.class, "append", String.class);
        assertTrue(hasMethod, "hasMethod should return true for existing method");
        
        hasMethod = ReflectUtil.hasMethod(TestClass.class, "nonExistentMethod");
        assertFalse(hasMethod, "hasMethod should return false for non-existent method");
        
        // Test hasField
        boolean hasField = ReflectUtil.hasField(TestClass.class, "name");
        assertTrue(hasField, "hasField should return true for existing field");
        
        hasField = ReflectUtil.hasField(TestClass.class, "nonExistentField");
        assertFalse(hasField, "hasField should return false for non-existent field");
    }

    /**
     * Test the helper methods of ReflectUtil.
     * <p>
     * 测试ReflectUtil的辅助方法。
     */
    @Test
    void testHelperMethods() {
        // Test getField
        Field field = ReflectUtil.getField(TestClass.class, "name");
        assertEquals("name", field.getName(), "getField should return the correct field");
        
        // Test getMethod
        Method method = ReflectUtil.getMethod(TestClass.class, "append", String.class);
        assertEquals("append", method.getName(), "getMethod should return the correct method");
    }

    /**
     * Test class for reflection operations.
     * <p>
     * 用于反射操作的测试类。
     */
    static class TestClass {
        private String name;
        private int value;
        public static String STATIC_FIELD = "static_value";

        public TestClass() {
            this.name = "default";
            this.value = 0;
        }

        public TestClass(String name, int value) {
            this.name = name;
            this.value = value;
        }

        public String append(String suffix) {
            return this.name + suffix;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public int getValue() {
            return this.value;
        }

        private static int add(int a, int b) {
            return a + b;
        }

        @Override
        public String toString() {
            return "TestClass{name='" + name + "', value=" + value + "}";
        }
    }

    /**
     * Sub test class for inheritance testing.
     * <p>
     * 用于继承测试的子类。
     */
    static class SubTestClass extends TestClass {
        private String subField;

        public SubTestClass() {
            super();
            this.subField = "sub_default";
        }

        public String getSubField() {
            return subField;
        }

        public void setSubField(String subField) {
            this.subField = subField;
        }

        public String subMethod() {
            return "sub_method_result";
        }
    }
}