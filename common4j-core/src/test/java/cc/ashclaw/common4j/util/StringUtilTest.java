// SPDX-License-Identifier: Apache-2.0
// Copyright (c) 2026 AshClaw.

package cc.ashclaw.common4j.util;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for StringUtil result verification using JUnit 5.
 * <p>
 * StringUtil结果验证测试类，使用JUnit 5。
 *
 * @author b1itz7
 * @since 1.0.0
 */
public class StringUtilTest {

    /**
     * Test the blank and empty checking methods of StringUtil.
     * <p>
     * 测试StringUtil的空白和空值检查方法。
     */
    @Test
    void testBlankEmptyChecks() {
        // Test isBlank
        assertTrue(StringUtil.isBlank(null), "isBlank should return true for null");
        assertTrue(StringUtil.isBlank(""), "isBlank should return true for empty string");
        assertTrue(StringUtil.isBlank("   "), "isBlank should return true for whitespace only");
        assertFalse(StringUtil.isBlank("test"), "isBlank should return false for non-blank string");
        assertFalse(StringUtil.isBlank("  test  "), "isBlank should return false for string with whitespace");
        
        // Test isEmpty
        assertTrue(StringUtil.isEmpty(null), "isEmpty should return true for null");
        assertTrue(StringUtil.isEmpty(""), "isEmpty should return true for empty string");
        assertFalse(StringUtil.isEmpty("   "), "isEmpty should return false for whitespace only");
        assertFalse(StringUtil.isEmpty("test"), "isEmpty should return false for non-empty string");
        
        // Test isNotBlank
        assertFalse(StringUtil.isNotBlank(null), "isNotBlank should return false for null");
        assertTrue(StringUtil.isNotBlank("test"), "isNotBlank should return true for non-blank string");
        
        // Test isNotEmpty
        assertFalse(StringUtil.isNotEmpty(null), "isNotEmpty should return false for null");
        assertTrue(StringUtil.isNotEmpty("   "), "isNotEmpty should return true for non-empty string");
    }

    /**
     * Test the trim methods of StringUtil.
     * <p>
     * 测试StringUtil的修剪方法。
     */
    @Test
    void testTrimMethods() {
        // Test trim
        assertNull(StringUtil.trim(null), "trim should return null for null");
        assertEquals("test", StringUtil.trim("  test  "), "trim should remove whitespace from both ends");
        
        // Test trimToNull
        assertNull(StringUtil.trimToNull(null), "trimToNull should return null for null");
        assertNull(StringUtil.trimToNull("   "), "trimToNull should return null for whitespace only");
        assertEquals("test", StringUtil.trimToNull("  test  "), "trimToNull should trim and return non-empty string");
        
        // Test trimToEmpty
        assertEquals("", StringUtil.trimToEmpty(null), "trimToEmpty should return empty string for null");
        assertEquals("", StringUtil.trimToEmpty("   "), "trimToEmpty should return empty string for whitespace only");
        assertEquals("test", StringUtil.trimToEmpty("  test  "), "trimToEmpty should trim string");
    }

    /**
     * Test the default value methods of StringUtil.
     * <p>
     * 测试StringUtil的默认值方法。
     */
    @Test
    void testDefaultValueMethods() {
        // Test defaultIfBlank
        assertEquals("default", StringUtil.defaultIfBlank(null, "default"), "defaultIfBlank should return default for null");
        assertEquals("default", StringUtil.defaultIfBlank("", "default"), "defaultIfBlank should return default for empty string");
        assertEquals("default", StringUtil.defaultIfBlank("   ", "default"), "defaultIfBlank should return default for whitespace only");
        assertEquals("value", StringUtil.defaultIfBlank("value", "default"), "defaultIfBlank should return original for non-blank");
        
        // Test defaultIfEmpty
        assertEquals("default", StringUtil.defaultIfEmpty(null, "default"), "defaultIfEmpty should return default for null");
        assertEquals("default", StringUtil.defaultIfEmpty("", "default"), "defaultIfEmpty should return default for empty string");
        assertEquals("   ", StringUtil.defaultIfEmpty("   ", "default"), "defaultIfEmpty should return original for non-empty");
        assertEquals("value", StringUtil.defaultIfEmpty("value", "default"), "defaultIfEmpty should return original for non-empty");
    }

    /**
     * Test the whitespace methods of StringUtil.
     * <p>
     * 测试StringUtil的空白字符方法。
     */
    @Test
    void testWhitespaceMethods() {
        // Test containsWhitespace
        assertFalse(StringUtil.containsWhitespace(null), "containsWhitespace should return false for null");
        assertFalse(StringUtil.containsWhitespace(""), "containsWhitespace should return false for empty string");
        assertFalse(StringUtil.containsWhitespace("no_whitespace"), "containsWhitespace should return false for no whitespace");
        assertTrue(StringUtil.containsWhitespace("with whitespace"), "containsWhitespace should return true for whitespace");
        assertTrue(StringUtil.containsWhitespace("with\ttab"), "containsWhitespace should return true for tab");
        
        // Test removeWhitespace
        assertNull(StringUtil.removeWhitespace(null), "removeWhitespace should return null for null");
        assertEquals("", StringUtil.removeWhitespace(""), "removeWhitespace should return empty string for empty");
        assertEquals("no_whitespace", StringUtil.removeWhitespace("no_whitespace"), "removeWhitespace should return same string for no whitespace");
        assertEquals("withwhitespace", StringUtil.removeWhitespace("with whitespace"), "removeWhitespace should remove whitespace");
        assertEquals("leadingandtrailing", StringUtil.removeWhitespace("  leading and trailing  "), "removeWhitespace should remove all whitespace");
    }

    /**
     * Test the equality methods of StringUtil.
     * <p>
     * 测试StringUtil的相等性方法。
     */
    @Test
    void testEqualityMethods() {
        // Test equals
        assertTrue(StringUtil.equals(null, null), "equals should return true for both null");
        assertFalse(StringUtil.equals(null, "test"), "equals should return false for null and non-null");
        assertTrue(StringUtil.equals("test", "test"), "equals should return true for equal strings");
        assertFalse(StringUtil.equals("test1", "test2"), "equals should return false for different strings");
        
        // Test equalsIgnoreCase
        assertTrue(StringUtil.equalsIgnoreCase("Test", "test"), "equalsIgnoreCase should return true for case-insensitive equal");
        assertTrue(StringUtil.equalsIgnoreCase("TEST", "test"), "equalsIgnoreCase should return true for case-insensitive equal");
        assertFalse(StringUtil.equalsIgnoreCase("Test1", "test2"), "equalsIgnoreCase should return false for different strings");
    }

    /**
     * Test the startsWith and endsWith methods of StringUtil.
     * <p>
     * 测试StringUtil的startsWith和endsWith方法。
     */
    @Test
    void testStartEndMethods() {
        // Test startsWith
        assertTrue(StringUtil.startsWith(null, null), "startsWith should return true for both null");
        assertFalse(StringUtil.startsWith(null, "prefix"), "startsWith should return false for null string");
        assertFalse(StringUtil.startsWith("test", null), "startsWith should return false for null prefix");
        assertTrue(StringUtil.startsWith("test", "te"), "startsWith should return true for matching prefix");
        assertFalse(StringUtil.startsWith("test", "es"), "startsWith should return false for non-matching prefix");
        
        // Test endsWith
        assertTrue(StringUtil.endsWith(null, null), "endsWith should return true for both null");
        assertTrue(StringUtil.endsWith("test", "st"), "endsWith should return true for matching suffix");
        assertFalse(StringUtil.endsWith("test", "es"), "endsWith should return false for non-matching suffix");
    }

    /**
     * Test the substring methods of StringUtil.
     * <p>
     * 测试StringUtil的substring方法。
     */
    @Test
    void testSubstringMethods() {
        // Test substring(String, int)
        assertNull(StringUtil.substring(null, 0), "substring should return null for null string");
        assertEquals("test", StringUtil.substring("test", 0), "substring should return full string for start 0");
        assertEquals("st", StringUtil.substring("test", 2), "substring should return from index 2");
        assertEquals("st", StringUtil.substring("test", -2), "substring should handle negative index");
        assertEquals("", StringUtil.substring("test", 10), "substring should return empty for index too large");
        
        // Test substring(String, int, int)
        assertNull(StringUtil.substring(null, 0, 0), "substring should return null for null string");
        assertEquals("te", StringUtil.substring("test", 0, 2), "substring should return from 0 to 2");
        assertEquals("es", StringUtil.substring("test", 1, 3), "substring should return from 1 to 3");
        assertEquals("es", StringUtil.substring("test", -3, -1), "substring should handle negative indices");
        assertEquals("", StringUtil.substring("test", 2, 1), "substring should return empty for start > end");
    }
}