// SPDX-License-Identifier: Apache-2.0
// Copyright (c) 2026 AshClaw.

package cc.ashclaw.core.util;

import cc.ashclaw.common4j.core.util.HexUtil;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for HexUtil result verification using JUnit 5.
 * <p>
 * HexUtil结果验证测试类，使用JUnit 5。
 *
 * @author b1itz7
 * @since 1.0.0
 */
public class HexUtilTest {

    /**
     * Test the bytesToHex method of HexUtil.
     * <p>
     * 测试HexUtil的bytesToHex方法。
     */
    @Test
    void testBytesToHex() {
        // Test normal case
        byte[] bytes = {10, 20, 30, 40, 50};
        String hex = HexUtil.bytesToHex(bytes);
        assertEquals("0a141e2832", hex, "bytesToHex should convert bytes to correct hex string");
        
        // Test empty byte array
        hex = HexUtil.bytesToHex(new byte[0]);
        assertEquals("", hex, "bytesToHex should return empty string for empty byte array");
        
        // Test null input
        hex = HexUtil.bytesToHex(null);
        assertNull(hex, "bytesToHex should return null for null input");
    }

    /**
     * Test the hexToBytes method of HexUtil.
     * <p>
     * 测试HexUtil的hexToBytes方法。
     */
    @Test
    void testHexToBytes() {
        // Test normal case
        String hex = "0a141e2832";
        byte[] bytes = HexUtil.hexToBytes(hex);
        
        byte[] expected = {10, 20, 30, 40, 50};
        assertArrayEquals(expected, bytes, "hexToBytes should convert hex string to correct bytes");
        
        // Test empty string
        bytes = HexUtil.hexToBytes("");
        assertNotNull(bytes, "hexToBytes should not return null for empty string");
        assertEquals(0, bytes.length, "hexToBytes should return empty array for empty string");
        
        // Test null input
        bytes = HexUtil.hexToBytes(null);
        assertNull(bytes, "hexToBytes should return null for null input");
        
        // Test invalid hex string (odd length)
        assertThrows(IllegalArgumentException.class, () -> HexUtil.hexToBytes("0a1"), 
                     "hexToBytes should throw IllegalArgumentException for odd length");
    }

    /**
     * Test the isHexString method of HexUtil.
     * <p>
     * 测试HexUtil的isHexString方法。
     */
    @Test
    void testIsHexString() {
        // Test valid hex strings
        String[] validHex = {"0a141e2832", "0000", "FFff", "1234567890abcdef"};
        for (String hex : validHex) {
            assertTrue(HexUtil.isHexString(hex), "isHexString should return true for valid hex string: " + hex);
        }
        
        // Test invalid hex strings
        String[] invalidHex = {"0a141", "0g14", "", "1234567890abcdefg", "0a-14"};
        for (String hex : invalidHex) {
            assertFalse(HexUtil.isHexString(hex), "isHexString should return false for invalid hex string: " + hex);
        }
        
        // Test null input
        boolean result = HexUtil.isHexString(null);
        assertFalse(result, "isHexString should return false for null input");
    }

    /**
     * Test the intToHex method of HexUtil.
     * <p>
     * 测试HexUtil的intToHex方法。
     */
    @Test
    void testIntToHex() {
        // Test positive numbers
        int[] positiveNumbers = {0, 10, 255, 1024, 65535, 2147483647};
        String[] expectedPositive = {"00000000", "0000000a", "000000ff", "00000400", "0000ffff", "7fffffff"};
        
        for (int i = 0; i < positiveNumbers.length; i++) {
            String hex = HexUtil.intToHex(positiveNumbers[i]);
            assertEquals(expectedPositive[i], hex, "intToHex should convert int " + positiveNumbers[i] + " to correct hex string");
        }
        
        // Test negative numbers
        int[] negativeNumbers = {-1, -10, -255, -1024, -2147483648};
        String[] expectedNegative = {"ffffffff", "fffffff6", "ffffff01", "fffffc00", "80000000"};
        
        for (int i = 0; i < negativeNumbers.length; i++) {
            String hex = HexUtil.intToHex(negativeNumbers[i]);
            assertEquals(expectedNegative[i], hex, "intToHex should convert int " + negativeNumbers[i] + " to correct hex string");
        }
    }

    /**
     * Test the hexToInt method of HexUtil.
     * <p>
     * 测试HexUtil的hexToInt方法。
     */
    @Test
    void testHexToInt() {
        // Test normal case
        String[] hexStrings = {"00000000", "0000000a", "000000ff", "00000400", "0000ffff", "7fffffff", "ffffffff", "fffffffa", "ffffff01", "fffffc00", "80000000"};
        int[] expected = {0, 10, 255, 1024, 65535, 2147483647, -1, -6, -255, -1024, -2147483648};
        
        for (int i = 0; i < hexStrings.length; i++) {
            int result = HexUtil.hexToInt(hexStrings[i]);
            assertEquals(expected[i], result, "hexToInt should convert hex string \"" + hexStrings[i] + "\" to correct int");
        }
        
        // Test case insensitive
        int result = HexUtil.hexToInt("FFff");
        assertEquals(65535, result, "hexToInt should be case insensitive");
        
        // Test null input
        assertThrows(NumberFormatException.class, () -> HexUtil.hexToInt(null), 
                     "hexToInt should throw NumberFormatException for null input");
        
        // Test invalid hex string
        assertThrows(NumberFormatException.class, () -> HexUtil.hexToInt("0g"), 
                     "hexToInt should throw NumberFormatException for invalid hex string");
    }
}