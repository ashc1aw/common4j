// SPDX-License-Identifier: Apache-2.0
// Copyright (c) 2026 AshClaw.

package cc.ashclaw.core.lang;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cc.ashclaw.common4j.core.lang.ArrayUtil;
import org.junit.jupiter.api.Test;

/**
 * Test class for ArrayUtil result verification using JUnit 5.
 * <p>
 * ArrayUtil结果验证测试类，使用JUnit 5。
 *
 * @author b1itz7
 * @since 1.0.0
 */
public class ArrayUtilTest {

    /**
     * Test the null and empty checking methods of ArrayUtil.
     * <p>
     * 测试ArrayUtil的null和空值检查方法。
     */
    @Test
    void testNullEmptyChecks() {
        // Test isNull
        boolean result = ArrayUtil.isNull(null);
        assertTrue(result, "isNull should return true for null");
        
        result = ArrayUtil.isNull(new String[]{});
        assertFalse(result, "isNull should return false for empty array");
        
        // Test nonNull
        result = ArrayUtil.nonNull(null);
        assertFalse(result, "nonNull should return false for null");
        
        result = ArrayUtil.nonNull(new String[]{});
        assertTrue(result, "nonNull should return true for non-null array");
        
        // Test isEmpty
        result = ArrayUtil.isEmpty(null);
        assertTrue(result, "isEmpty should return true for null");
        
        result = ArrayUtil.isEmpty(new String[]{});
        assertTrue(result, "isEmpty should return true for empty array");
        
        result = ArrayUtil.isEmpty(new String[]{"test"});
        assertFalse(result, "isEmpty should return false for non-empty array");
        
        // Test isNotEmpty
        result = ArrayUtil.isNotEmpty(null);
        assertFalse(result, "isNotEmpty should return false for null");
        
        result = ArrayUtil.isNotEmpty(new String[]{});
        assertFalse(result, "isNotEmpty should return false for empty array");
        
        result = ArrayUtil.isNotEmpty(new String[]{"test"});
        assertTrue(result, "isNotEmpty should return true for non-empty array");
    }

    /**
     * Test the length methods of ArrayUtil.
     * <p>
     * 测试ArrayUtil的长度方法。
     */
    @Test
    void testLengthMethods() {
        // Test length
        int result = ArrayUtil.length(null);
        assertEquals(0, result, "length should return 0 for null");
        
        result = ArrayUtil.length(new String[]{});
        assertEquals(0, result, "length should return 0 for empty array");
        
        result = ArrayUtil.length(new String[]{"a", "b", "c"});
        assertEquals(3, result, "length should return correct length");
    }

    /**
     * Test the element access methods of ArrayUtil.
     * <p>
     * 测试ArrayUtil的元素访问方法。
     */
    @Test
    void testElementAccessMethods() {
        String[] array = {"first", "second", "third"};
        
        // Test getFirst
        String result = ArrayUtil.getFirst(null);
        assertNull(result, "getFirst should return null for null");
        
        result = ArrayUtil.getFirst(new String[]{});
        assertNull(result, "getFirst should return null for empty array");
        
        result = ArrayUtil.getFirst(array);
        assertEquals("first", result, "getFirst should return first element");
        
        // Test getLast
        result = ArrayUtil.getLast(null);
        assertNull(result, "getLast should return null for null");
        
        result = ArrayUtil.getLast(new String[]{});
        assertNull(result, "getLast should return null for empty array");
        
        result = ArrayUtil.getLast(array);
        assertEquals("third", result, "getLast should return last element");
        
        // Test get
        result = ArrayUtil.get(null, 0);
        assertNull(result, "get should return null for null array");
        
        result = ArrayUtil.get(array, 1);
        assertEquals("second", result, "get should return correct element");
        
        result = ArrayUtil.get(array, 10);
        assertNull(result, "get should return null for index out of bounds");
    }

    /**
     * Test the array manipulation methods of ArrayUtil.
     * <p>
     * 测试ArrayUtil的数组操作方法。
     */
    @Test
    void testArrayManipulationMethods() {
        // Test clone
        String[] array = {"a", "b", "c"};
        String[] cloned = ArrayUtil.clone(array);
        assertNotNull(cloned, "clone should not return null for non-null array");
        
        // Test concat
        String[] array1 = {"a", "b"};
        String[] array2 = {"c", "d"};
        String[] concatenated = ArrayUtil.concat(array1, array2);
        assertNotNull(concatenated, "concat should not return null");
        assertEquals(4, concatenated.length, "concat should combine arrays correctly");
        
        // Test contains
        boolean result = ArrayUtil.contains(array, "b");
        assertTrue(result, "contains should find existing element");
        
        result = ArrayUtil.contains(array, "z");
        assertFalse(result, "contains should not find non-existing element");
    }

    /**
     * Test the comparison methods of ArrayUtil.
     * <p>
     * 测试ArrayUtil的比较方法。
     */
    @Test
    void testComparisonMethods() {
        // Test equals
        String[] array1 = {"a", "b", "c"};
        String[] array2 = {"a", "b", "c"};
        String[] array3 = {"x", "y", "z"};
        
        boolean result = ArrayUtil.equals(array1, array2);
        assertTrue(result, "equals should return true for equal arrays");
        
        result = ArrayUtil.equals(array1, array3);
        assertFalse(result, "equals should return false for different arrays");
        
        result = ArrayUtil.equals(null, null);
        assertTrue(result, "equals should return true for both null");
    }
}