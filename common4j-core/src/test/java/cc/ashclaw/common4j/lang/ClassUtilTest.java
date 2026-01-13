// SPDX-License-Identifier: Apache-2.0
// Copyright (c) 2026 AshClaw.

package cc.ashclaw.common4j.lang;

/**
 * Test class for ClassUtil result verification.
 * <p>
 * ClassUtil结果验证测试类。
 *
 * @author b1itz7
 * @since 1.0.0
 */
public class ClassUtilTest {

    public static void main(String[] args) {
        System.out.println("===== ClassUtil Result Verification Test Start =====");
        
        // Test class loading methods
        testClassLoadingMethods();
        
        // Test class information methods
        testClassInformationMethods();
        
        // Test class type checking methods
        testClassTypeCheckingMethods();
        
        System.out.println("===== ClassUtil Result Verification Test End =====");
    }

    /**
     * Test the class loading methods of ClassUtil.
     * <p>
     * 测试ClassUtil的类加载方法。
     */
    private static void testClassLoadingMethods() {
        System.out.println("\n1. Testing class loading methods...");
        
        try {
            // Test loadClass
            Class<?> clazz = ClassUtil.loadClass("java.lang.String");
            System.out.println("loadClass(\"java.lang.String\") = " + clazz.getName());
            if (clazz == null) {
                System.out.println("ERROR: loadClass should not return null for valid class!");
                return;
            }
            
            // Test loadClassQuietly
            Class<?> clazzQuiet = ClassUtil.loadClassQuietly("java.lang.Integer");
            System.out.println("loadClassQuietly(\"java.lang.Integer\") = " + (clazzQuiet != null ? clazzQuiet.getName() : "null"));
            if (clazzQuiet == null) {
                System.out.println("ERROR: loadClassQuietly should not return null for valid class!");
                return;
            }
            
            // Test loadClassQuietly with invalid class
            Class<?> invalidClazz = ClassUtil.loadClassQuietly("com.invalid.Class");
            System.out.println("loadClassQuietly(\"com.invalid.Class\") = " + invalidClazz);
            if (invalidClazz != null) {
                System.out.println("ERROR: loadClassQuietly should return null for invalid class!");
                return;
            }
            
            System.out.println("class loading methods test passed.");
        } catch (Exception e) {
            System.out.println("ERROR in class loading methods: " + e.getMessage());
        }
    }

    /**
     * Test the class information methods of ClassUtil.
     * <p>
     * 测试ClassUtil的类信息方法。
     */
    private static void testClassInformationMethods() {
        System.out.println("\n2. Testing class information methods...");
        
        // Test getCanonicalName
        String canonicalName = ClassUtil.getCanonicalName(String.class);
        System.out.println("getCanonicalName(String.class) = \"" + canonicalName + "\"");
        if (!"java.lang.String".equals(canonicalName)) {
            System.out.println("ERROR: getCanonicalName should return full class name!");
            return;
        }
        
        // Test getSimpleName
        String simpleName = ClassUtil.getSimpleName(String.class);
        System.out.println("getSimpleName(String.class) = \"" + simpleName + "\"");
        if (!"String".equals(simpleName)) {
            System.out.println("ERROR: getSimpleName should return simple class name!");
            return;
        }
        
        // Test getPackageName
        String packageName = ClassUtil.getPackageName(String.class);
        System.out.println("getPackageName(String.class) = \"" + packageName + "\"");
        if (!"java.lang".equals(packageName)) {
            System.out.println("ERROR: getPackageName should return package name!");
            return;
        }
        
        System.out.println("class information methods test passed.");
    }

    /**
     * Test the class type checking methods of ClassUtil.
     * <p>
     * 测试ClassUtil的类类型检查方法。
     */
    private static void testClassTypeCheckingMethods() {
        System.out.println("\n3. Testing class type checking methods...");
        
        // Test isPrimitive
        boolean result = ClassUtil.isPrimitive(int.class);
        System.out.println("isPrimitive(int.class) = " + result);
        if (!result) {
            System.out.println("ERROR: isPrimitive should return true for primitive type!");
            return;
        }
        
        result = ClassUtil.isPrimitive(String.class);
        System.out.println("isPrimitive(String.class) = " + result);
        if (result) {
            System.out.println("ERROR: isPrimitive should return false for non-primitive type!");
            return;
        }
        
        // Test isArray
        result = ClassUtil.isArray(int[].class);
        System.out.println("isArray(int[].class) = " + result);
        if (!result) {
            System.out.println("ERROR: isArray should return true for array type!");
            return;
        }
        
        result = ClassUtil.isArray(String.class);
        System.out.println("isArray(String.class) = " + result);
        if (result) {
            System.out.println("ERROR: isArray should return false for non-array type!");
            return;
        }
        
        // Test isAssignable
        result = ClassUtil.isAssignable(Number.class, Integer.class);
        System.out.println("isAssignable(Number.class, Integer.class) = " + result);
        if (!result) {
            System.out.println("ERROR: isAssignable should return true for assignable types!");
            return;
        }
        
        System.out.println("class type checking methods test passed.");
    }
}
