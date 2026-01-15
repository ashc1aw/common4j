// SPDX-License-Identifier: Apache-2.0
// Copyright (c) 2026 AshClaw.

package cc.ashclaw.core.lang;

import cc.ashclaw.common4j.core.lang.Pair;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for Pair result verification using JUnit 5.
 * <p>
 * Pair结果验证测试类，使用JUnit 5。
 *
 * @author b1itz7
 * @since 1.0.0
 */
public class PairTest {

    /**
     * Test the creation methods of Pair.
     * <p>
     * 测试Pair的创建方法。
     */
    @Test
    void testCreationMethods() {
        // Test constructor
        Pair<String, Integer> pair1 = new Pair<>("key", 123);
        assertNotNull(pair1, "Pair constructor should not return null");
        
        // Test of method
        Pair<String, Integer> pair2 = Pair.of("key2", 456);
        assertNotNull(pair2, "Pair.of should not return null");
        
        // Test with null values
        Pair<String, Integer> pair3 = Pair.of(null, null);
        assertNotNull(pair3, "Pair.of should handle null values");
    }

    /**
     * Test the access methods of Pair.
     * <p>
     * 测试Pair的访问方法。
     */
    @Test
    void testAccessMethods() {
        Pair<String, Integer> pair = Pair.of("testKey", 789);
        
        // Test getKey
        String key = pair.getKey();
        assertEquals("testKey", key, "getKey should return correct key");
        
        // Test getValue
        Integer value = pair.getValue();
        assertEquals(789, value, "getValue should return correct value");
        
        // Test with null values
        Pair<String, Integer> nullPair = Pair.of(null, null);
        assertNull(nullPair.getKey(), "getKey should return null for null key");
        assertNull(nullPair.getValue(), "getValue should return null for null value");
    }

    /**
     * Test the equality methods of Pair.
     * <p>
     * 测试Pair的相等性方法。
     */
    @Test
    void testEqualityMethods() {
        Pair<String, Integer> pair1 = Pair.of("key", 123);
        Pair<String, Integer> pair2 = Pair.of("key", 123);
        Pair<String, Integer> pair3 = Pair.of("differentKey", 123);
        Pair<String, Integer> pair4 = Pair.of("key", 456);
        Pair<String, Integer> pair5 = Pair.of(null, null);
        Pair<String, Integer> pair6 = Pair.of(null, null);
        
        // Test equality with same values
        assertEquals(pair1, pair2, "Pairs with same key and value should be equal");
        
        // Test inequality with different keys
        assertNotEquals(pair1, pair3, "Pairs with different keys should not be equal");
        
        // Test inequality with different values
        assertNotEquals(pair1, pair4, "Pairs with different values should not be equal");
        
        // Test equality with null values
        assertEquals(pair5, pair6, "Pairs with null values should be equal");
        
        // Test inequality with different null combinations
        assertNotEquals(pair5, Pair.of("key", null), "Pairs with different null combinations should not be equal");
        assertNotEquals(pair5, Pair.of(null, 123), "Pairs with different null combinations should not be equal");
        
        // Test equality with different types
        assertNotEquals(pair1, "Not a Pair", "Pair should not be equal to a non-Pair object");
        
        // Test equality with null
        assertNotEquals(pair1, null, "Pair should not be equal to null");
    }

    /**
     * Test the hash code methods of Pair.
     * <p>
     * 测试Pair的哈希码方法。
     */
    @Test
    void testHashCodeMethods() {
        Pair<String, Integer> pair1 = Pair.of("key", 123);
        Pair<String, Integer> pair2 = Pair.of("key", 123);
        Pair<String, Integer> pair3 = Pair.of("differentKey", 123);
        
        // Test that equal pairs have equal hash codes
        assertEquals(pair1.hashCode(), pair2.hashCode(), "Equal pairs should have equal hash codes");
        
        // Test that different pairs may have different hash codes
        assertNotEquals(pair1.hashCode(), pair3.hashCode(), "Different pairs may have different hash codes");
        
        // Test hashCode with null values
        Pair<String, Integer> nullPair = Pair.of(null, null);
        assertNotNull(nullPair, "Pair with null values should have a valid hash code");
    }

    /**
     * Test the toString methods of Pair.
     * <p>
     * 测试Pair的toString方法。
     */
    @Test
    void testToStringMethods() {
        Pair<String, Integer> pair = Pair.of("key", 123);
        String toString = pair.toString();
        
        // Test that toString contains key and value
        assertNotNull(toString, "toString should not return null");
        assertTrue(toString.contains("key"), "toString should contain the key");
        assertTrue(toString.contains("123"), "toString should contain the value");
        
        // Test toString with null values
        Pair<String, Integer> nullPair = Pair.of(null, null);
        String nullToString = nullPair.toString();
        assertNotNull(nullToString, "toString should not return null for null values");
    }
}