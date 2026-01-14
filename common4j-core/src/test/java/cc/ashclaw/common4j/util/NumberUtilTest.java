// SPDX-License-Identifier: Apache-2.0
// Copyright (c) 2026 AshClaw.

package cc.ashclaw.common4j.util;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for NumberUtil result verification using JUnit 5.
 * <p>
 * NumberUtil结果验证测试类，使用JUnit 5。
 *
 * @author b1itz7
 * @since 1.0.0
 */
public class NumberUtilTest {

    /**
     * Test the isInteger method of NumberUtil.
     * <p>
     * 测试NumberUtil的isInteger方法。
     */
    @Test
    void testIsInteger() {
        // Test valid integers
        String[] validInts = {"0", "123", "2147483647"};
        for (String s : validInts) {
            assertTrue(NumberUtil.isInteger(s), "String \"" + s + "\" should be considered an integer");
        }
        
        // Test invalid integers (including negative numbers, since isInteger returns false for them)
        String[] invalidInts = {"abc", "123.45", "-456", "2147483648", "-2147483648", "", "   ", "123abc", null};
        for (String s : invalidInts) {
            assertFalse(NumberUtil.isInteger(s), "String \"" + s + "\" should not be considered an integer");
        }
    }

    /**
     * Test the isLong method of NumberUtil.
     * <p>
     * 测试NumberUtil的isLong方法。
     */
    @Test
    void testIsLong() {
        // Test valid longs
        String[] validLongs = {"0", "123", "9223372036854775807"};
        for (String s : validLongs) {
            assertTrue(NumberUtil.isLong(s), "String \"" + s + "\" should be considered a long");
        }
        
        // Test invalid longs (including negative numbers, since isLong returns false for them)
        String[] invalidLongs = {"abc", "123.45", "-456", "9223372036854775808", "-9223372036854775809", "", "   ", "123abc", null};
        for (String s : invalidLongs) {
            assertFalse(NumberUtil.isLong(s), "String \"" + s + "\" should not be considered a long");
        }
    }

    /**
     * Test the isDouble method of NumberUtil.
     * <p>
     * 测试NumberUtil的isDouble方法。
     */
    @Test
    void testIsDouble() {
        // Test valid doubles
        String[] validDoubles = {"0", "123", "123.45", "1.23e4", "Infinity"};
        for (String s : validDoubles) {
            assertTrue(NumberUtil.isDouble(s), "String \"" + s + "\" should be considered a double");
        }
        
        // Test invalid doubles (including negative numbers, since isDouble returns false for them)
        String[] invalidDoubles = {"abc", "-456", "-678.90", "-5.67e-8", "-Infinity", "", "   ", "123abc", "123..45", null};
        for (String s : invalidDoubles) {
            assertFalse(NumberUtil.isDouble(s), "String \"" + s + "\" should not be considered a double");
        }
    }

    /**
     * Test the parseInt method of NumberUtil.
     * <p>
     * 测试NumberUtil的parseInt方法。
     */
    @Test
    void testParseInt() {
        // Test valid integers
        assertEquals(123, NumberUtil.parseInt("123", -1), "parseInt should return 123 for valid input");
        assertEquals(-456, NumberUtil.parseInt("-456", -1), "parseInt should return -456 for valid input");
        assertEquals(0, NumberUtil.parseInt("0", -1), "parseInt should return 0 for valid input");
        
        // Test invalid integers
        assertEquals(-1, NumberUtil.parseInt("abc", -1), "parseInt should return default value for invalid input");
        assertEquals(-1, NumberUtil.parseInt("123.45", -1), "parseInt should return default value for invalid input");
        
        // Test null input
        assertEquals(-1, NumberUtil.parseInt(null, -1), "parseInt should return default value for null input");
        
        // Test empty string
        assertEquals(-1, NumberUtil.parseInt("", -1), "parseInt should return default value for empty string");
    }

    /**
     * Test the parseLong method of NumberUtil.
     * <p>
     * 测试NumberUtil的parseLong方法。
     */
    @Test
    void testParseLong() {
        // Test valid longs
        assertEquals(1234567890L, NumberUtil.parseLong("1234567890", -1L), "parseLong should return 1234567890L for valid input");
        assertEquals(-9876543210L, NumberUtil.parseLong("-9876543210", -1L), "parseLong should return -9876543210L for valid input");
        
        // Test invalid longs
        assertEquals(-1L, NumberUtil.parseLong("abc", -1L), "parseLong should return default value for invalid input");
        assertEquals(-1L, NumberUtil.parseLong("123.45", -1L), "parseLong should return default value for invalid input");
        
        // Test null input
        assertEquals(-1L, NumberUtil.parseLong(null, -1L), "parseLong should return default value for null input");
    }

    /**
     * Test the parseDouble method of NumberUtil.
     * <p>
     * 测试NumberUtil的parseDouble方法。
     */
    @Test
    void testParseDouble() {
        // Test valid doubles
        assertEquals(123.45, NumberUtil.parseDouble("123.45", -1.0), "parseDouble should return 123.45 for valid input");
        assertEquals(-678.90, NumberUtil.parseDouble("-678.90", -1.0), "parseDouble should return -678.90 for valid input");
        assertEquals(12300.0, NumberUtil.parseDouble("1.23e4", -1.0), "parseDouble should return 12300.0 for valid input");
        
        // Test invalid doubles
        assertEquals(-1.0, NumberUtil.parseDouble("abc", -1.0), "parseDouble should return default value for invalid input");
        
        // Test null input
        assertEquals(-1.0, NumberUtil.parseDouble(null, -1.0), "parseDouble should return default value for null input");
    }

    /**
     * Test the isBetween methods of NumberUtil.
     * <p>
     * 测试NumberUtil的isBetween方法。
     */
    @Test
    void testIsBetween() {
        // Test isBetween(int)
        assertTrue(NumberUtil.isBetween(5, 1, 10), "isBetween(int) should return true for value within range");
        assertFalse(NumberUtil.isBetween(0, 1, 10), "isBetween(int) should return false for value below range");
        assertFalse(NumberUtil.isBetween(11, 1, 10), "isBetween(int) should return false for value above range");
        assertTrue(NumberUtil.isBetween(1, 1, 10), "isBetween(int) should return true for value equal to min");
        assertTrue(NumberUtil.isBetween(10, 1, 10), "isBetween(int) should return true for value equal to max");
        
        // Test isBetween(long)
        assertTrue(NumberUtil.isBetween(1234567890L, 1000000000L, 2000000000L), "isBetween(long) should return true for value within range");
        
        // Test isBetween(double)
        assertTrue(NumberUtil.isBetween(5.5, 1.0, 10.0), "isBetween(double) should return true for value within range");
        assertFalse(NumberUtil.isBetween(0.5, 1.0, 10.0), "isBetween(double) should return false for value below range");
        assertFalse(NumberUtil.isBetween(10.5, 1.0, 10.0), "isBetween(double) should return false for value above range");
    }

    /**
     * Test the round method of NumberUtil.
     * <p>
     * 测试NumberUtil的round方法。
     */
    @Test
    void testRound() {
        // Test normal rounding
        assertEquals(123.46, NumberUtil.round(123.456, 2), "round should return 123.46 for 123.456 rounded to 2 decimals");
        assertEquals(123.45, NumberUtil.round(123.454, 2), "round should return 123.45 for 123.454 rounded to 2 decimals");
        assertEquals(123.46, NumberUtil.round(123.455, 2), "round should return 123.46 for 123.455 rounded to 2 decimals");
        
        // Test rounding to zero decimals
        assertEquals(124.0, NumberUtil.round(123.5, 0), "round should return 124.0 for 123.5 rounded to 0 decimals");
        
        // Test rounding to integer
        assertEquals(124.0, NumberUtil.round(123.999, 0), "round should return 124.0 for 123.999 rounded to 0 decimals");
        
        // Test rounding with negative number
        assertEquals(-123.46, NumberUtil.round(-123.456, 2), "round should return -123.46 for -123.456 rounded to 2 decimals");
        
        // Test rounding with negative places should throw exception
        assertThrows(IllegalArgumentException.class, () -> NumberUtil.round(123.456, -1),
                "round should throw IllegalArgumentException for negative places");
    }
}