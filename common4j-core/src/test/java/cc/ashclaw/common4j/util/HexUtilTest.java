// SPDX-License-Identifier: Apache-2.0
// Copyright (c) 2026 AshClaw.

package cc.ashclaw.common4j.util;

/**
 * Test class for HexUtil result verification.
 * <p>
 * HexUtil结果验证测试类。
 *
 * @author b1itz7
 * @since 1.0.0
 */
public class HexUtilTest {

    public static void main(String[] args) {
        System.out.println("===== HexUtil Result Verification Test Start =====");
        
        // Test bytesToHex method
        testBytesToHex();
        
        // Test hexToBytes method
        testHexToBytes();
        
        // Test isHexString method
        testIsHexString();
        
        // Test intToHex method
        testIntToHex();
        
        // Test hexToInt method
        testHexToInt();
        
        System.out.println("===== HexUtil Result Verification Test End =====");
    }

    /**
     * Test the bytesToHex method of HexUtil.
     * <p>
     * 测试HexUtil的bytesToHex方法。
     */
    private static void testBytesToHex() {
        System.out.println("\n1. Testing bytesToHex method...");
        
        // Test normal case
        byte[] bytes = {10, 20, 30, 40, 50};
        String hex = HexUtil.bytesToHex(bytes);
        System.out.println("Input bytes: [10, 20, 30, 40, 50]");
        System.out.println("Output hex: " + hex);
        if ("0a141e2832".equals(hex)) {
            System.out.println("bytesToHex normal case test passed.");
        } else {
            System.out.println("ERROR: bytesToHex normal case test failed!");
            return;
        }
        
        // Test empty byte array
        hex = HexUtil.bytesToHex(new byte[0]);
        System.out.println("Empty byte array output: " + hex);
        if ("".equals(hex)) {
            System.out.println("bytesToHex empty array test passed.");
        } else {
            System.out.println("ERROR: bytesToHex empty array test failed!");
            return;
        }
        
        // Test null input
        hex = HexUtil.bytesToHex(null);
        System.out.println("Null input output: " + hex);
        if (null == hex) {
            System.out.println("bytesToHex null input test passed.");
        } else {
            System.out.println("ERROR: bytesToHex null input test failed!");
            return;
        }
        
        System.out.println("bytesToHex method test passed.");
    }

    /**
     * Test the hexToBytes method of HexUtil.
     * <p>
     * 测试HexUtil的hexToBytes方法。
     */
    private static void testHexToBytes() {
        System.out.println("\n2. Testing hexToBytes method...");
        
        // Test normal case
        String hex = "0a141e2832";
        byte[] bytes = HexUtil.hexToBytes(hex);
        System.out.println("Input hex: " + hex);
        System.out.print("Output bytes: [");
        for (int i = 0; i < bytes.length; i++) {
            System.out.print(bytes[i]);
            if (i < bytes.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
        
        boolean passed = true;
        byte[] expected = {10, 20, 30, 40, 50};
        if (bytes.length != expected.length) {
            passed = false;
        } else {
            for (int i = 0; i < bytes.length; i++) {
                if (bytes[i] != expected[i]) {
                    passed = false;
                    break;
                }
            }
        }
        
        if (passed) {
            System.out.println("hexToBytes normal case test passed.");
        } else {
            System.out.println("ERROR: hexToBytes normal case test failed!");
            return;
        }
        
        // Test empty string
        bytes = HexUtil.hexToBytes("");
        System.out.println("Empty string output: " + bytes);
        if (bytes != null && bytes.length == 0) {
            System.out.println("hexToBytes empty string test passed.");
        } else {
            System.out.println("ERROR: hexToBytes empty string test failed!");
            return;
        }
        
        // Test null input
        bytes = HexUtil.hexToBytes(null);
        System.out.println("Null input output: " + bytes);
        if (null == bytes) {
            System.out.println("hexToBytes null input test passed.");
        } else {
            System.out.println("ERROR: hexToBytes null input test failed!");
            return;
        }
        
        // Test invalid hex string (odd length)
        try {
            HexUtil.hexToBytes("0a1");
            System.out.println("ERROR: hexToBytes should throw IllegalArgumentException for odd length!");
            return;
        } catch (IllegalArgumentException e) {
            System.out.println("hexToBytes odd length test passed.");
        }
        
        System.out.println("hexToBytes method test passed.");
    }

    /**
     * Test the isHexString method of HexUtil.
     * <p>
     * 测试HexUtil的isHexString方法。
     */
    private static void testIsHexString() {
        System.out.println("\n3. Testing isHexString method...");
        
        // Test valid hex strings
        String[] validHex = {"0a141e2832", "0000", "FFff", "1234567890abcdef"};
        for (String hex : validHex) {
            boolean result = HexUtil.isHexString(hex);
            System.out.println("Hex \"" + hex + "\" is valid: " + result);
            if (!result) {
                System.out.println("ERROR: isHexString should return true for valid hex string!\n");
                return;
            }
        }
        
        // Test invalid hex strings
        String[] invalidHex = {"0a141", "0g14", "", "1234567890abcdefg", "0a-14"};
        for (String hex : invalidHex) {
            boolean result = HexUtil.isHexString(hex);
            System.out.println("Hex \"" + hex + "\" is valid: " + result);
            if (result) {
                System.out.println("ERROR: isHexString should return false for invalid hex string!\n");
                return;
            }
        }
        
        // Test null input
        boolean result = HexUtil.isHexString(null);
        System.out.println("Null input is valid: " + result);
        if (!result) {
            System.out.println("isHexString null input test passed.");
        } else {
            System.out.println("ERROR: isHexString should return false for null input!\n");
            return;
        }
        
        System.out.println("isHexString method test passed.");
    }

    /**
     * Test the intToHex method of HexUtil.
     * <p>
     * 测试HexUtil的intToHex方法。
     */
    private static void testIntToHex() {
        System.out.println("\n4. Testing intToHex method...");
        
        // Test positive numbers
        int[] positiveNumbers = {0, 10, 255, 1024, 65535, 2147483647};
        String[] expectedPositive = {"00000000", "0000000a", "000000ff", "00000400", "0000ffff", "7fffffff"};
        
        for (int i = 0; i < positiveNumbers.length; i++) {
            String hex = HexUtil.intToHex(positiveNumbers[i]);
            System.out.println("Input int: " + positiveNumbers[i] + ", Output hex: " + hex);
            if (!expectedPositive[i].equals(hex)) {
                System.out.println("ERROR: intToHex test failed for " + positiveNumbers[i] + "!\n");
                return;
            }
        }
        
        // Test negative numbers
        int[] negativeNumbers = {-1, -10, -255, -1024, -2147483648};
        String[] expectedNegative = {"ffffffff", "fffffff6", "ffffff01", "fffffc00", "80000000"};
        
        for (int i = 0; i < negativeNumbers.length; i++) {
            String hex = HexUtil.intToHex(negativeNumbers[i]);
            System.out.println("Input int: " + negativeNumbers[i] + ", Output hex: " + hex);
            if (!expectedNegative[i].equals(hex)) {
                System.out.println("ERROR: intToHex test failed for " + negativeNumbers[i] + "!\n");
                return;
            }
        }
        
        System.out.println("intToHex method test passed.");
    }

    /**
     * Test the hexToInt method of HexUtil.
     * <p>
     * 测试HexUtil的hexToInt方法。
     */
    private static void testHexToInt() {
        System.out.println("\n5. Testing hexToInt method...");
        
        // Test normal case
        String[] hexStrings = {"00000000", "0000000a", "000000ff", "00000400", "0000ffff", "7fffffff", "ffffffff", "fffffffa", "ffffff01", "fffffc00", "80000000"};
        int[] expected = {0, 10, 255, 1024, 65535, 2147483647, -1, -6, -255, -1024, -2147483648};
        
        for (int i = 0; i < hexStrings.length; i++) {
            int result = HexUtil.hexToInt(hexStrings[i]);
            System.out.println("Input hex: \"" + hexStrings[i] + "\", Output int: " + result);
            if (result != expected[i]) {
                System.out.println("ERROR: hexToInt test failed for \"" + hexStrings[i] + "\"!\n");
                return;
            }
        }
        
        // Test case insensitive
        int result = HexUtil.hexToInt("FFff");
        System.out.println("Input hex: \"FFff\", Output int: " + result);
        if (result != 65535) {
            System.out.println("ERROR: hexToInt case insensitive test failed!\n");
            return;
        }
        
        // Test null input
        try {
            HexUtil.hexToInt(null);
            System.out.println("ERROR: hexToInt should throw NumberFormatException for null input!\n");
            return;
        } catch (NumberFormatException e) {
            System.out.println("hexToInt null input test passed.");
        }
        
        // Test invalid hex string
        try {
            HexUtil.hexToInt("0g");
            System.out.println("ERROR: hexToInt should throw NumberFormatException for invalid hex string!\n");
            return;
        } catch (NumberFormatException e) {
            System.out.println("hexToInt invalid hex string test passed.");
        }
        
        System.out.println("hexToInt method test passed.");
    }
}
