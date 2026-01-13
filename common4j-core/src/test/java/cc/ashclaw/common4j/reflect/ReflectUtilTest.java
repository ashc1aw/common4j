// SPDX-License-Identifier: Apache-2.0
// Copyright (c) 2026 AshClaw.

package cc.ashclaw.common4j.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Test class for ReflectUtil result verification.
 * <p>
 * ReflectUtil结果验证测试类。
 *
 * @author b1itz7
 * @since 1.0.0
 */
public class ReflectUtilTest {

    public static void main(String[] args) {
        System.out.println("===== ReflectUtil Result Verification Test Start =====");
        
        // Test instance creation methods
        testInstanceCreationMethods();
        
        // Test field access methods
        testFieldAccessMethods();
        
        // Test method invocation methods
        testMethodInvocationMethods();
        
        // Test reflection information methods
        testReflectionInformationMethods();
        
        // Test helper methods
        testHelperMethods();
        
        System.out.println("===== ReflectUtil Result Verification Test End =====");
    }

    /**
     * Test the instance creation methods of ReflectUtil.
     * <p>
     * 测试ReflectUtil的实例创建方法。
     */
    private static void testInstanceCreationMethods() {
        System.out.println("\n1. Testing instance creation methods...");
        
        // Test newInstance with no arguments
        System.out.println("Testing newInstance(Class)...");
        try {
            TestClass instance = ReflectUtil.newInstance(TestClass.class);
            System.out.println("newInstance(TestClass.class) created instance: " + instance);
            if (instance == null) {
                System.out.println("ERROR: newInstance should return a non-null instance!\n");
                return;
            }
        } catch (Exception e) {
            System.out.println("ERROR: newInstance failed! " + e.getMessage() + "\n");
            return;
        }
        
        // Test newInstance with arguments
        System.out.println("Testing newInstance(Class, Class[], Object[])...");
        try {
            Class<?>[] paramTypes = {String.class, int.class};
            Object[] params = {"Test", 123};
            TestClass instance = ReflectUtil.newInstance(TestClass.class, paramTypes, params);
            System.out.println("newInstance(TestClass.class, paramTypes, params) created instance: " + instance);
            if (instance == null) {
                System.out.println("ERROR: newInstance should return a non-null instance!\n");
                return;
            }
            // Verify parameters were set correctly
            String name = (String) ReflectUtil.getFieldValue(instance, "name");
            int value = (int) ReflectUtil.getFieldValue(instance, "value");
            if (!"Test".equals(name) || value != 123) {
                System.out.println("ERROR: newInstance did not set parameters correctly!\n");
                return;
            }
        } catch (Exception e) {
            System.out.println("ERROR: newInstance failed! " + e.getMessage() + "\n");
            return;
        }
        
        System.out.println("instance creation methods test passed.");
    }

    /**
     * Test the field access methods of ReflectUtil.
     * <p>
     * 测试ReflectUtil的字段访问方法。
     */
    private static void testFieldAccessMethods() {
        System.out.println("\n2. Testing field access methods...");
        
        TestClass instance = new TestClass("Initial", 456);
        
        // Test getFieldValue
        System.out.println("Testing getFieldValue...");
        try {
            String name = (String) ReflectUtil.getFieldValue(instance, "name");
            System.out.println("getFieldValue(instance, \"name\") = " + name);
            if (!"Initial".equals(name)) {
                System.out.println("ERROR: getFieldValue returned incorrect value!\n");
                return;
            }
        } catch (Exception e) {
            System.out.println("ERROR: getFieldValue failed! " + e.getMessage() + "\n");
            return;
        }
        
        // Test setFieldValue
        System.out.println("Testing setFieldValue...");
        try {
            ReflectUtil.setFieldValue(instance, "name", "Updated");
            String name = (String) ReflectUtil.getFieldValue(instance, "name");
            System.out.println("setFieldValue(instance, \"name\", \"Updated\"); getFieldValue = " + name);
            if (!"Updated".equals(name)) {
                System.out.println("ERROR: setFieldValue did not update the value!\n");
                return;
            }
        } catch (Exception e) {
            System.out.println("ERROR: setFieldValue failed! " + e.getMessage() + "\n");
            return;
        }
        
        // Test getStaticFieldValue
        System.out.println("Testing getStaticFieldValue...");
        try {
            String staticValue = (String) ReflectUtil.getStaticFieldValue(TestClass.class, "STATIC_FIELD");
            System.out.println("getStaticFieldValue(TestClass.class, \"STATIC_FIELD\") = " + staticValue);
            if (!"static_value".equals(staticValue)) {
                System.out.println("ERROR: getStaticFieldValue returned incorrect value!\n");
                return;
            }
        } catch (Exception e) {
            System.out.println("ERROR: getStaticFieldValue failed! " + e.getMessage() + "\n");
            return;
        }
        
        // Test setStaticFieldValue
        System.out.println("Testing setStaticFieldValue...");
        try {
            ReflectUtil.setStaticFieldValue(TestClass.class, "STATIC_FIELD", "updated_static_value");
            String staticValue = (String) ReflectUtil.getStaticFieldValue(TestClass.class, "STATIC_FIELD");
            System.out.println("setStaticFieldValue(TestClass.class, \"STATIC_FIELD\", \"updated_static_value\"); getStaticFieldValue = " + staticValue);
            if (!"updated_static_value".equals(staticValue)) {
                System.out.println("ERROR: setStaticFieldValue did not update the value!\n");
                return;
            }
            // Restore original value
            ReflectUtil.setStaticFieldValue(TestClass.class, "STATIC_FIELD", "static_value");
        } catch (Exception e) {
            System.out.println("ERROR: setStaticFieldValue failed! " + e.getMessage() + "\n");
            return;
        }
        
        System.out.println("field access methods test passed.");
    }

    /**
     * Test the method invocation methods of ReflectUtil.
     * <p>
     * 测试ReflectUtil的方法调用方法。
     */
    private static void testMethodInvocationMethods() {
        System.out.println("\n3. Testing method invocation methods...");
        
        TestClass instance = new TestClass("Test", 123);
        
        // Test invokeMethod
        System.out.println("Testing invokeMethod...");
        try {
            Class<?>[] paramTypes = {String.class};
            Object[] params = {" world"};
            String result = (String) ReflectUtil.invokeMethod(instance, "append", paramTypes, params);
            System.out.println("invokeMethod(instance, \"append\", paramTypes, params) = " + result);
            if (!"Test world".equals(result)) {
                System.out.println("ERROR: invokeMethod returned incorrect result!\n");
                return;
            }
        } catch (Exception e) {
            System.out.println("ERROR: invokeMethod failed! " + e.getMessage() + "\n");
            return;
        }
        
        // Test invokeStaticMethod
        System.out.println("Testing invokeStaticMethod...");
        try {
            Class<?>[] paramTypes = {int.class, int.class};
            Object[] params = {10, 20};
            int result = (int) ReflectUtil.invokeStaticMethod(TestClass.class, "add", paramTypes, params);
            System.out.println("invokeStaticMethod(TestClass.class, \"add\", paramTypes, params) = " + result);
            if (result != 30) {
                System.out.println("ERROR: invokeStaticMethod returned incorrect result!\n");
                return;
            }
        } catch (Exception e) {
            System.out.println("ERROR: invokeStaticMethod failed! " + e.getMessage() + "\n");
            return;
        }
        
        System.out.println("method invocation methods test passed.");
    }

    /**
     * Test the reflection information methods of ReflectUtil.
     * <p>
     * 测试ReflectUtil的反射信息方法。
     */
    private static void testReflectionInformationMethods() {
        System.out.println("\n4. Testing reflection information methods...");
        
        // Test getAllFields
        System.out.println("Testing getAllFields...");
        try {
            List<Field> fields = ReflectUtil.getAllFields(SubTestClass.class);
            System.out.println("getAllFields(SubTestClass.class) found " + fields.size() + " fields");
            // SubTestClass should have fields from both itself and TestClass
            if (fields.size() < 4) { // name, value, staticField from TestClass and subField from SubTestClass
                System.out.println("ERROR: getAllFields did not find all fields!\n");
                return;
            }
        } catch (Exception e) {
            System.out.println("ERROR: getAllFields failed! " + e.getMessage() + "\n");
            return;
        }
        
        // Test getAllMethods
        System.out.println("Testing getAllMethods...");
        try {
            List<Method> methods = ReflectUtil.getAllMethods(SubTestClass.class);
            System.out.println("getAllMethods(SubTestClass.class) found " + methods.size() + " methods");
            // Should have methods from both SubTestClass and TestClass
            if (methods.size() < 5) { // append, add, setValue, getValue, and subMethod
                System.out.println("ERROR: getAllMethods did not find all methods!\n");
                return;
            }
        } catch (Exception e) {
            System.out.println("ERROR: getAllMethods failed! " + e.getMessage() + "\n");
            return;
        }
        
        // Test hasMethod
        System.out.println("Testing hasMethod...");
        try {
            boolean hasMethod = ReflectUtil.hasMethod(TestClass.class, "append", String.class);
            System.out.println("hasMethod(TestClass.class, \"append\", String.class) = " + hasMethod);
            if (!hasMethod) {
                System.out.println("ERROR: hasMethod should return true for existing method!\n");
                return;
            }
            
            hasMethod = ReflectUtil.hasMethod(TestClass.class, "nonExistentMethod");
            System.out.println("hasMethod(TestClass.class, \"nonExistentMethod\") = " + hasMethod);
            if (hasMethod) {
                System.out.println("ERROR: hasMethod should return false for non-existent method!\n");
                return;
            }
        } catch (Exception e) {
            System.out.println("ERROR: hasMethod failed! " + e.getMessage() + "\n");
            return;
        }
        
        // Test hasField
        System.out.println("Testing hasField...");
        try {
            boolean hasField = ReflectUtil.hasField(TestClass.class, "name");
            System.out.println("hasField(TestClass.class, \"name\") = " + hasField);
            if (!hasField) {
                System.out.println("ERROR: hasField should return true for existing field!\n");
                return;
            }
            
            hasField = ReflectUtil.hasField(TestClass.class, "nonExistentField");
            System.out.println("hasField(TestClass.class, \"nonExistentField\") = " + hasField);
            if (hasField) {
                System.out.println("ERROR: hasField should return false for non-existent field!\n");
                return;
            }
        } catch (Exception e) {
            System.out.println("ERROR: hasField failed! " + e.getMessage() + "\n");
            return;
        }
        
        System.out.println("reflection information methods test passed.");
    }

    /**
     * Test the helper methods of ReflectUtil.
     * <p>
     * 测试ReflectUtil的辅助方法。
     */
    private static void testHelperMethods() {
        System.out.println("\n5. Testing helper methods...");
        
        // Test getField
        System.out.println("Testing getField...");
        try {
            Field field = ReflectUtil.getField(TestClass.class, "name");
            System.out.println("getField(TestClass.class, \"name\") = " + field.getName());
            if (!"name".equals(field.getName())) {
                System.out.println("ERROR: getField returned incorrect field!\n");
                return;
            }
        } catch (Exception e) {
            System.out.println("ERROR: getField failed! " + e.getMessage() + "\n");
            return;
        }
        
        // Test getMethod
        System.out.println("Testing getMethod...");
        try {
            Method method = ReflectUtil.getMethod(TestClass.class, "append", String.class);
            System.out.println("getMethod(TestClass.class, \"append\", String.class) = " + method.getName());
            if (!"append".equals(method.getName())) {
                System.out.println("ERROR: getMethod returned incorrect method!\n");
                return;
            }
        } catch (Exception e) {
            System.out.println("ERROR: getMethod failed! " + e.getMessage() + "\n");
            return;
        }
        
        System.out.println("helper methods test passed.");
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
