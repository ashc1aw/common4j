// SPDX-License-Identifier: Apache-2.0
// Copyright (c) 2026 AshClaw.

package cc.ashclaw.common4j.lang;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

/**
 * Test class for ObjectUtil result verification using JUnit 5.
 * <p>
 * ObjectUtil结果验证测试类，使用JUnit 5。
 *
 * @author b1itz7
 * @since 1.0.0
 */
public class ObjectUtilTest {

    /**
     * Test the null checking methods of ObjectUtil.
     * <p>
     * 测试ObjectUtil的null检查方法。
     */
    @Test
    void testNullCheckingMethods() {
        // Test isNull
        boolean result = ObjectUtil.isNull(null);
        assertTrue(result, "isNull should return true for null");
        
        result = ObjectUtil.isNull("test");
        assertFalse(result, "isNull should return false for non-null");
        
        // Test nonNull
        result = ObjectUtil.nonNull(null);
        assertFalse(result, "nonNull should return false for null");
        
        result = ObjectUtil.nonNull("test");
        assertTrue(result, "nonNull should return true for non-null");
    }

    /**
     * Test the equality methods of ObjectUtil.
     * <p>
     * 测试ObjectUtil的相等性方法。
     */
    @Test
    void testEqualityMethods() {
        // Test equals
        boolean result = ObjectUtil.equals(null, null);
        assertTrue(result, "equals should return true for both null");
        
        result = ObjectUtil.equals(null, "test");
        assertFalse(result, "equals should return false for null and non-null");
        
        result = ObjectUtil.equals("test", "test");
        assertTrue(result, "equals should return true for equal objects");
        
        result = ObjectUtil.equals("test1", "test2");
        assertFalse(result, "equals should return false for different objects");
    }

    /**
     * Test the default value methods of ObjectUtil.
     * <p>
     * 测试ObjectUtil的默认值方法。
     */
    @Test
    void testDefaultValueMethods() {
        // Test defaultIfNull
        String result = ObjectUtil.defaultIfNull(null, "default");
        assertEquals("default", result, "defaultIfNull should return default for null");
        
        result = ObjectUtil.defaultIfNull("value", "default");
        assertEquals("value", result, "defaultIfNull should return original for non-null");
        
        // Test firstNonNull
        String firstNonNull = ObjectUtil.firstNonNull(null, "second", "third");
        assertEquals("second", firstNonNull, "firstNonNull should return first non-null value");
    }

    /**
     * Test the hash code methods of ObjectUtil.
     * <p>
     * 测试ObjectUtil的哈希码方法。
     */
    @Test
    void testHashCodeMethods() {
        // Test hashCode
        int result = ObjectUtil.hashCode(null);
        assertEquals(0, result, "hashCode should return 0 for null");
        
        result = ObjectUtil.hashCode("test");
        assertNotEquals(0, result, "hashCode should not return 0 for non-null object");
    }

    /**
     * Test the object conversion methods of ObjectUtil.
     * <p>
     * 测试ObjectUtil的对象转换方法。
     */
    @Test
    void testObjectConversionMethods() {
        // Test toString
        String result = ObjectUtil.toString(null);
        assertEquals("null", result, "toString should return \"null\" for null");
        
        result = ObjectUtil.toString("test");
        assertEquals("test", result, "toString should return object's toString() result");
        
        // Test toString with default value
        result = ObjectUtil.toString(null, "default");
        assertEquals("default", result, "toString should return default for null");
    }
}