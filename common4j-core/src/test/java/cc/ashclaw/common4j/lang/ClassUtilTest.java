// SPDX-License-Identifier: Apache-2.0
// Copyright (c) 2026 AshClaw.

package cc.ashclaw.common4j.lang;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;

/**
 * Test class for ClassUtil result verification using JUnit 5.
 * <p>
 * ClassUtil结果验证测试类，使用JUnit 5。
 *
 * @author b1itz7
 * @since 1.0.0
 */
public class ClassUtilTest {

    /**
     * Test the class loading methods of ClassUtil.
     * <p>
     * 测试ClassUtil的类加载方法。
     */
    @Test
    void testClassLoadingMethods() {
        try {
            // Test loadClass
            Class<?> clazz = ClassUtil.loadClass("java.lang.String");
            assertNotNull(clazz, "loadClass should not return null for valid class");
            assertEquals("java.lang.String", clazz.getName(), "loadClass should return correct class");
            
            // Test loadClassQuietly
            Class<?> clazzQuiet = ClassUtil.loadClassQuietly("java.lang.Integer");
            assertNotNull(clazzQuiet, "loadClassQuietly should not return null for valid class");
            assertEquals("java.lang.Integer", clazzQuiet.getName(), "loadClassQuietly should return correct class");
            
            // Test loadClassQuietly with invalid class
            Class<?> invalidClazz = ClassUtil.loadClassQuietly("com.invalid.Class");
            assertNull(invalidClazz, "loadClassQuietly should return null for invalid class");
            
        } catch (Exception e) {
            fail("Exception in class loading methods: " + e.getMessage(), e);
        }
    }

    /**
     * Test the class information methods of ClassUtil.
     * <p>
     * 测试ClassUtil的类信息方法。
     */
    @Test
    void testClassInformationMethods() {
        // Test getCanonicalName
        String canonicalName = ClassUtil.getCanonicalName(String.class);
        assertEquals("java.lang.String", canonicalName, "getCanonicalName should return full class name");
        
        // Test getSimpleName
        String simpleName = ClassUtil.getSimpleName(String.class);
        assertEquals("String", simpleName, "getSimpleName should return simple class name");
        
        // Test getPackageName
        String packageName = ClassUtil.getPackageName(String.class);
        assertEquals("java.lang", packageName, "getPackageName should return package name");
    }

    /**
     * Test the class type checking methods of ClassUtil.
     * <p>
     * 测试ClassUtil的类类型检查方法。
     */
    @Test
    void testClassTypeCheckingMethods() {
        // Test isPrimitive
        boolean result = ClassUtil.isPrimitive(int.class);
        assertTrue(result, "isPrimitive should return true for primitive type");
        
        result = ClassUtil.isPrimitive(String.class);
        assertFalse(result, "isPrimitive should return false for non-primitive type");
        
        // Test isArray
        result = ClassUtil.isArray(int[].class);
        assertTrue(result, "isArray should return true for array type");
        
        result = ClassUtil.isArray(String.class);
        assertFalse(result, "isArray should return false for non-array type");
        
        // Test isAssignable
        result = ClassUtil.isAssignable(Number.class, Integer.class);
        assertTrue(result, "isAssignable should return true for assignable types");
    }
}