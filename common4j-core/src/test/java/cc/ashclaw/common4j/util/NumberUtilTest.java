// SPDX-License-Identifier: Apache-2.0
// Copyright (c) 2026 AshClaw.

package cc.ashclaw.common4j.util;

/**
 * Test class for NumberUtil result verification.
 * <p>
 * NumberUtil结果验证测试类。
 *
 * @author b1itz7
 * @since 1.0.0
 */
public class NumberUtilTest {

    public static void main(String[] args) {
        System.out.println("===== NumberUtil Result Verification Test Start =====");
        
        // Test isInteger method
        testIsInteger();
        
        // Test isLong method
        testIsLong();
        
        // Test isDouble method
        testIsDouble();
        
        // Test parseInt method
        testParseInt();
        
        // Test parseLong method
        testParseLong();
        
        // Test parseDouble method
        testParseDouble();
        
        // Test isBetween methods
        testIsBetween();
        
        // Test round method
        testRound();
        
        System.out.println("===== NumberUtil Result Verification Test End =====");
    }

    /**
     * Test the isInteger method of NumberUtil.
     * <p>
     * 测试NumberUtil的isInteger方法。
     */
    private static void testIsInteger() {
        System.out.println("\n1. Testing isInteger method...");
        
        // Test valid integers
        String[] validInts = {"0", "123", "-456", "2147483647", "-2147483648"};
        for (String s : validInts) {
            boolean result = NumberUtil.isInteger(s);
            System.out.println("String \"" + s + "\" is integer: " + result);
            if (!result) {
                System.out.println("ERROR: isInteger should return true for valid integer!\n");
                return;
            }
        }
        
        // Test invalid integers
        String[] invalidInts = {"abc", "123.45", "2147483648", "-2147483649", "", "   ", "123abc"};
        for (String s : invalidInts) {
            boolean result = NumberUtil.isInteger(s);
            System.out.println("String \"" + s + "\" is integer: " + result);
            if (result) {
                System.out.println("ERROR: isInteger should return false for invalid integer!\n");
                return;
            }
        }
        
        // Test null input
        boolean result = NumberUtil.isInteger(null);
        System.out.println("Null input is integer: " + result);
        if (result) {
            System.out.println("ERROR: isInteger should return false for null input!\n");
            return;
        }
        
        System.out.println("isInteger method test passed.");
    }

    /**
     * Test the isLong method of NumberUtil.
     * <p>
     * 测试NumberUtil的isLong方法。
     */
    private static void testIsLong() {
        System.out.println("\n2. Testing isLong method...");
        
        // Test valid longs
        String[] validLongs = {"0", "123", "-456", "9223372036854775807", "-9223372036854775808"};
        for (String s : validLongs) {
            boolean result = NumberUtil.isLong(s);
            System.out.println("String \"" + s + "\" is long: " + result);
            if (!result) {
                System.out.println("ERROR: isLong should return true for valid long!\n");
                return;
            }
        }
        
        // Test invalid longs
        String[] invalidLongs = {"abc", "123.45", "9223372036854775808", "-9223372036854775809", "", "   ", "123abc"};
        for (String s : invalidLongs) {
            boolean result = NumberUtil.isLong(s);
            System.out.println("String \"" + s + "\" is long: " + result);
            if (result) {
                System.out.println("ERROR: isLong should return false for invalid long!\n");
                return;
            }
        }
        
        // Test null input
        boolean result = NumberUtil.isLong(null);
        System.out.println("Null input is long: " + result);
        if (result) {
            System.out.println("ERROR: isLong should return false for null input!\n");
            return;
        }
        
        System.out.println("isLong method test passed.");
    }

    /**
     * Test the isDouble method of NumberUtil.
     * <p>
     * 测试NumberUtil的isDouble方法。
     */
    private static void testIsDouble() {
        System.out.println("\n3. Testing isDouble method...");
        
        // Test valid doubles
        String[] validDoubles = {"0", "123", "-456", "123.45", "-678.90", "1.23e4", "-5.67e-8", "Infinity", "-Infinity"};
        for (String s : validDoubles) {
            boolean result = NumberUtil.isDouble(s);
            System.out.println("String \"" + s + "\" is double: " + result);
            if (!result) {
                System.out.println("ERROR: isDouble should return true for valid double!\n");
                return;
            }
        }
        
        // Test invalid doubles
        String[] invalidDoubles = {"abc", "", "   ", "123abc", "123..45"};
        for (String s : invalidDoubles) {
            boolean result = NumberUtil.isDouble(s);
            System.out.println("String \"" + s + "\" is double: " + result);
            if (result) {
                System.out.println("ERROR: isDouble should return false for invalid double!\n");
                return;
            }
        }
        
        // Test null input
        boolean result = NumberUtil.isDouble(null);
        System.out.println("Null input is double: " + result);
        if (result) {
            System.out.println("ERROR: isDouble should return false for null input!\n");
            return;
        }
        
        System.out.println("isDouble method test passed.");
    }

    /**
     * Test the parseInt method of NumberUtil.
     * <p>
     * 测试NumberUtil的parseInt方法。
     */
    private static void testParseInt() {
        System.out.println("\n4. Testing parseInt method...");
        
        // Test valid integers
        int result = NumberUtil.parseInt("123", -1);
        System.out.println("parseInt(\"123\", -1) = " + result);
        if (result != 123) {
            System.out.println("ERROR: parseInt should return 123 for valid input!\n");
            return;
        }
        
        result = NumberUtil.parseInt("-456", -1);
        System.out.println("parseInt(\"-456\", -1) = " + result);
        if (result != -456) {
            System.out.println("ERROR: parseInt should return -456 for valid input!\n");
            return;
        }
        
        result = NumberUtil.parseInt("0", -1);
        System.out.println("parseInt(\"0\", -1) = " + result);
        if (result != 0) {
            System.out.println("ERROR: parseInt should return 0 for valid input!\n");
            return;
        }
        
        // Test invalid integers
        result = NumberUtil.parseInt("abc", -1);
        System.out.println("parseInt(\"abc\", -1) = " + result);
        if (result != -1) {
            System.out.println("ERROR: parseInt should return default value for invalid input!\n");
            return;
        }
        
        result = NumberUtil.parseInt("123.45", -1);
        System.out.println("parseInt(\"123.45\", -1) = " + result);
        if (result != -1) {
            System.out.println("ERROR: parseInt should return default value for invalid input!\n");
            return;
        }
        
        // Test null input
        result = NumberUtil.parseInt(null, -1);
        System.out.println("parseInt(null, -1) = " + result);
        if (result != -1) {
            System.out.println("ERROR: parseInt should return default value for null input!\n");
            return;
        }
        
        // Test empty string
        result = NumberUtil.parseInt("", -1);
        System.out.println("parseInt(\"\", -1) = " + result);
        if (result != -1) {
            System.out.println("ERROR: parseInt should return default value for empty string!\n");
            return;
        }
        
        System.out.println("parseInt method test passed.");
    }

    /**
     * Test the parseLong method of NumberUtil.
     * <p>
     * 测试NumberUtil的parseLong方法。
     */
    private static void testParseLong() {
        System.out.println("\n5. Testing parseLong method...");
        
        // Test valid longs
        long result = NumberUtil.parseLong("1234567890", -1L);
        System.out.println("parseLong(\"1234567890\", -1L) = " + result);
        if (result != 1234567890L) {
            System.out.println("ERROR: parseLong should return 1234567890L for valid input!\n");
            return;
        }
        
        result = NumberUtil.parseLong("-9876543210", -1L);
        System.out.println("parseLong(\"-9876543210\", -1L) = " + result);
        if (result != -9876543210L) {
            System.out.println("ERROR: parseLong should return -9876543210L for valid input!\n");
            return;
        }
        
        // Test invalid longs
        result = NumberUtil.parseLong("abc", -1L);
        System.out.println("parseLong(\"abc\", -1L) = " + result);
        if (result != -1L) {
            System.out.println("ERROR: parseLong should return default value for invalid input!\n");
            return;
        }
        
        result = NumberUtil.parseLong("123.45", -1L);
        System.out.println("parseLong(\"123.45\", -1L) = " + result);
        if (result != -1L) {
            System.out.println("ERROR: parseLong should return default value for invalid input!\n");
            return;
        }
        
        // Test null input
        result = NumberUtil.parseLong(null, -1L);
        System.out.println("parseLong(null, -1L) = " + result);
        if (result != -1L) {
            System.out.println("ERROR: parseLong should return default value for null input!\n");
            return;
        }
        
        System.out.println("parseLong method test passed.");
    }

    /**
     * Test the parseDouble method of NumberUtil.
     * <p>
     * 测试NumberUtil的parseDouble方法。
     */
    private static void testParseDouble() {
        System.out.println("\n6. Testing parseDouble method...");
        
        // Test valid doubles
        double result = NumberUtil.parseDouble("123.45", -1.0);
        System.out.println("parseDouble(\"123.45\", -1.0) = " + result);
        if (result != 123.45) {
            System.out.println("ERROR: parseDouble should return 123.45 for valid input!\n");
            return;
        }
        
        result = NumberUtil.parseDouble("-678.90", -1.0);
        System.out.println("parseDouble(\"-678.90\", -1.0) = " + result);
        if (result != -678.90) {
            System.out.println("ERROR: parseDouble should return -678.90 for valid input!\n");
            return;
        }
        
        result = NumberUtil.parseDouble("1.23e4", -1.0);
        System.out.println("parseDouble(\"1.23e4\", -1.0) = " + result);
        if (result != 12300.0) {
            System.out.println("ERROR: parseDouble should return 12300.0 for valid input!\n");
            return;
        }
        
        // Test invalid doubles
        result = NumberUtil.parseDouble("abc", -1.0);
        System.out.println("parseDouble(\"abc\", -1.0) = " + result);
        if (result != -1.0) {
            System.out.println("ERROR: parseDouble should return default value for invalid input!\n");
            return;
        }
        
        // Test null input
        result = NumberUtil.parseDouble(null, -1.0);
        System.out.println("parseDouble(null, -1.0) = " + result);
        if (result != -1.0) {
            System.out.println("ERROR: parseDouble should return default value for null input!\n");
            return;
        }
        
        System.out.println("parseDouble method test passed.");
    }

    /**
     * Test the isBetween methods of NumberUtil.
     * <p>
     * 测试NumberUtil的isBetween方法。
     */
    private static void testIsBetween() {
        System.out.println("\n7. Testing isBetween methods...");
        
        // Test isBetween(int)
        boolean intResult1 = NumberUtil.isBetween(5, 1, 10);
        System.out.println("isBetween(5, 1, 10) = " + intResult1);
        if (!intResult1) {
            System.out.println("ERROR: isBetween(int) should return true for value within range!\n");
            return;
        }
        
        boolean intResult2 = NumberUtil.isBetween(0, 1, 10);
        System.out.println("isBetween(0, 1, 10) = " + intResult2);
        if (intResult2) {
            System.out.println("ERROR: isBetween(int) should return false for value below range!\n");
            return;
        }
        
        boolean intResult3 = NumberUtil.isBetween(11, 1, 10);
        System.out.println("isBetween(11, 1, 10) = " + intResult3);
        if (intResult3) {
            System.out.println("ERROR: isBetween(int) should return false for value above range!\n");
            return;
        }
        
        boolean intResult4 = NumberUtil.isBetween(1, 1, 10);
        System.out.println("isBetween(1, 1, 10) = " + intResult4);
        if (!intResult4) {
            System.out.println("ERROR: isBetween(int) should return true for value equal to min!\n");
            return;
        }
        
        boolean intResult5 = NumberUtil.isBetween(10, 1, 10);
        System.out.println("isBetween(10, 1, 10) = " + intResult5);
        if (!intResult5) {
            System.out.println("ERROR: isBetween(int) should return true for value equal to max!\n");
            return;
        }
        
        // Test isBetween(long)
        boolean longResult = NumberUtil.isBetween(1234567890L, 1000000000L, 2000000000L);
        System.out.println("isBetween(1234567890L, 1000000000L, 2000000000L) = " + longResult);
        if (!longResult) {
            System.out.println("ERROR: isBetween(long) should return true for value within range!\n");
            return;
        }
        
        // Test isBetween(double)
        boolean doubleResult1 = NumberUtil.isBetween(5.5, 1.0, 10.0);
        System.out.println("isBetween(5.5, 1.0, 10.0) = " + doubleResult1);
        if (!doubleResult1) {
            System.out.println("ERROR: isBetween(double) should return true for value within range!\n");
            return;
        }
        
        boolean doubleResult2 = NumberUtil.isBetween(0.5, 1.0, 10.0);
        System.out.println("isBetween(0.5, 1.0, 10.0) = " + doubleResult2);
        if (doubleResult2) {
            System.out.println("ERROR: isBetween(double) should return false for value below range!\n");
            return;
        }
        
        boolean doubleResult3 = NumberUtil.isBetween(10.5, 1.0, 10.0);
        System.out.println("isBetween(10.5, 1.0, 10.0) = " + doubleResult3);
        if (doubleResult3) {
            System.out.println("ERROR: isBetween(double) should return false for value above range!\n");
            return;
        }
        
        System.out.println("isBetween methods test passed.");
    }

    /**
     * Test the round method of NumberUtil.
     * <p>
     * 测试NumberUtil的round方法。
     */
    private static void testRound() {
        System.out.println("\n8. Testing round method...");
        
        // Test normal rounding
        double result1 = NumberUtil.round(123.456, 2);
        System.out.println("round(123.456, 2) = " + result1);
        if (result1 != 123.46) {
            System.out.println("ERROR: round should return 123.46 for 123.456 rounded to 2 decimals!\n");
            return;
        }
        
        double result2 = NumberUtil.round(123.454, 2);
        System.out.println("round(123.454, 2) = " + result2);
        if (result2 != 123.45) {
            System.out.println("ERROR: round should return 123.45 for 123.454 rounded to 2 decimals!\n");
            return;
        }
        
        double result3 = NumberUtil.round(123.455, 2);
        System.out.println("round(123.455, 2) = " + result3);
        if (result3 != 123.46) {
            System.out.println("ERROR: round should return 123.46 for 123.455 rounded to 2 decimals!\n");
            return;
        }
        
        // Test rounding to zero decimals
        double result4 = NumberUtil.round(123.5, 0);
        System.out.println("round(123.5, 0) = " + result4);
        if (result4 != 124.0) {
            System.out.println("ERROR: round should return 124.0 for 123.5 rounded to 0 decimals!\n");
            return;
        }
        
        // Test rounding to integer
        double result5 = NumberUtil.round(123.999, 0);
        System.out.println("round(123.999, 0) = " + result5);
        if (result5 != 124.0) {
            System.out.println("ERROR: round should return 124.0 for 123.999 rounded to 0 decimals!\n");
            return;
        }
        
        // Test rounding with negative number
        double result6 = NumberUtil.round(-123.456, 2);
        System.out.println("round(-123.456, 2) = " + result6);
        if (result6 != -123.46) {
            System.out.println("ERROR: round should return -123.46 for -123.456 rounded to 2 decimals!\n");
            return;
        }
        
        // Test rounding with negative places should throw exception
        try {
            NumberUtil.round(123.456, -1);
            System.out.println("ERROR: round should throw IllegalArgumentException for negative places!\n");
            return;
        } catch (IllegalArgumentException e) {
            System.out.println("round correctly throws IllegalArgumentException for negative places.");
        }
        
        System.out.println("round method test passed.");
    }
}
