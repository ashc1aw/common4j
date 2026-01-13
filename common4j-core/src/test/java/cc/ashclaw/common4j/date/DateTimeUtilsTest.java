// SPDX-License-Identifier: Apache-2.0
// Copyright (c) 2026 AshClaw.

package cc.ashclaw.common4j.date;

import java.time.LocalDateTime;

/**
 * Test class for DateTimeUtils result verification.
 * <p>
 * DateTimeUtils结果验证测试类。
 *
 * @author b1itz7
 * @since 1.0.0
 */
public class DateTimeUtilsTest {

    public static void main(String[] args) {
        System.out.println("===== DateTimeUtils Result Verification Test Start =====");
        
        // Test basic date-time methods
        testBasicDateTimeMethods();
        
        // Test date-time formatting methods
        testDateTimeFormattingMethods();
        
        // Test date-time parsing methods
        testDateTimeParsingMethods();
        
        // Test quiet parsing methods
        testQuietParsingMethods();
        
        // Test conversion methods
        testConversionMethods();
        
        System.out.println("===== DateTimeUtils Result Verification Test End =====");
    }

    /**
     * Test the basic date-time methods of DateTimeUtils.
     * <p>
     * 测试DateTimeUtils的基本日期时间方法。
     */
    private static void testBasicDateTimeMethods() {
        System.out.println("\n1. Testing basic date-time methods...");
        
        // Test now
        LocalDateTime result = DateTimeUtils.now();
        System.out.println("now() = " + result);
        if (result == null) {
            System.out.println("ERROR: now should return current date-time!");
            return;
        }
        
        System.out.println("basic date-time methods test passed.");
    }

    /**
     * Test the date-time formatting methods of DateTimeUtils.
     * <p>
     * 测试DateTimeUtils的日期时间格式化方法。
     */
    private static void testDateTimeFormattingMethods() {
        System.out.println("\n2. Testing date-time formatting methods...");
        
        LocalDateTime dateTime = LocalDateTime.of(2023, 12, 25, 10, 30, 45);
        
        // Test format with default pattern
        String result = DateTimeUtils.format(dateTime);
        System.out.println("format(2023-12-25T10:30:45) = \"" + result + "\"");
        if (!"2023-12-25 10:30:45".equals(result)) {
            System.out.println("ERROR: format with default pattern should return yyyy-MM-dd HH:mm:ss format!");
            return;
        }
        
        // Test format with custom pattern
        result = DateTimeUtils.format(dateTime, "yyyy/MM/dd HH:mm:ss");
        System.out.println("format(2023-12-25T10:30:45, \"yyyy/MM/dd HH:mm:ss\") = \"" + result + "\"");
        if (!"2023/12/25 10:30:45".equals(result)) {
            System.out.println("ERROR: format with custom pattern should use specified pattern!");
            return;
        }
        
        // Test format with null date-time
        result = DateTimeUtils.format(null);
        System.out.println("format(null) = " + result);
        if (result != null) {
            System.out.println("ERROR: format should return null for null date-time!");
            return;
        }
        
        System.out.println("date-time formatting methods test passed.");
    }

    /**
     * Test the date-time parsing methods of DateTimeUtils.
     * <p>
     * 测试DateTimeUtils的日期时间解析方法。
     */
    private static void testDateTimeParsingMethods() {
        System.out.println("\n3. Testing date-time parsing methods...");
        
        // Test parse with default pattern
        LocalDateTime result = DateTimeUtils.parse("2023-12-25 10:30:45");
        System.out.println("parse(\"2023-12-25 10:30:45\") = " + result);
        if (result == null || !result.equals(LocalDateTime.of(2023, 12, 25, 10, 30, 45))) {
            System.out.println("ERROR: parse with default pattern should parse yyyy-MM-dd HH:mm:ss format!");
            return;
        }
        
        // Test parse with custom pattern
        result = DateTimeUtils.parse("2023/12/25 10:30:45", "yyyy/MM/dd HH:mm:ss");
        System.out.println("parse(\"2023/12/25 10:30:45\", \"yyyy/MM/dd HH:mm:ss\") = " + result);
        if (result == null || !result.equals(LocalDateTime.of(2023, 12, 25, 10, 30, 45))) {
            System.out.println("ERROR: parse with custom pattern should parse specified pattern!");
            return;
        }
        
        // Test parse with null string
        result = DateTimeUtils.parse(null);
        System.out.println("parse(null) = " + result);
        if (result != null) {
            System.out.println("ERROR: parse should return null for null string!");
            return;
        }
        
        System.out.println("date-time parsing methods test passed.");
    }

    /**
     * Test the quiet parsing methods of DateTimeUtils.
     * <p>
     * 测试DateTimeUtils的安静解析方法。
     */
    private static void testQuietParsingMethods() {
        System.out.println("\n4. Testing quiet parsing methods...");
        
        // Test parseQuietly with valid string
        LocalDateTime result = DateTimeUtils.parseQuietly("2023-12-25 10:30:45");
        System.out.println("parseQuietly(\"2023-12-25 10:30:45\") = " + result);
        if (result == null || !result.equals(LocalDateTime.of(2023, 12, 25, 10, 30, 45))) {
            System.out.println("ERROR: parseQuietly should parse valid date-time string!");
            return;
        }
        
        // Test parseQuietly with invalid string
        result = DateTimeUtils.parseQuietly("invalid-date-time");
        System.out.println("parseQuietly(\"invalid-date-time\") = " + result);
        if (result != null) {
            System.out.println("ERROR: parseQuietly should return null for invalid string!");
            return;
        }
        
        // Test parseQuietly with custom pattern and invalid string
        result = DateTimeUtils.parseQuietly("2023/12/25", "yyyy-MM-dd HH:mm:ss");
        System.out.println("parseQuietly(\"2023/12/25\", \"yyyy-MM-dd HH:mm:ss\") = " + result);
        if (result != null) {
            System.out.println("ERROR: parseQuietly should return null for invalid string with custom pattern!");
            return;
        }
        
        System.out.println("quiet parsing methods test passed.");
    }

    /**
     * Test the conversion methods of DateTimeUtils.
     * <p>
     * 测试DateTimeUtils的转换方法。
     */
    private static void testConversionMethods() {
        System.out.println("\n5. Testing conversion methods...");
        
        LocalDateTime dateTime = LocalDateTime.of(2023, 12, 25, 10, 30, 45);
        
        // Test toEpochSecond
        long epochSecond = DateTimeUtils.toEpochSecond(dateTime);
        System.out.println("toEpochSecond(2023-12-25T10:30:45) = " + epochSecond);
        if (epochSecond == 0) {
            System.out.println("ERROR: toEpochSecond should return valid epoch seconds!");
            return;
        }
        
        // Test toEpochSecond with null
        epochSecond = DateTimeUtils.toEpochSecond(null);
        System.out.println("toEpochSecond(null) = " + epochSecond);
        if (epochSecond != 0) {
            System.out.println("ERROR: toEpochSecond should return 0 for null!");
            return;
        }
        
        // Test toEpochMilli
        long epochMilli = DateTimeUtils.toEpochMilli(dateTime);
        System.out.println("toEpochMilli(2023-12-25T10:30:45) = " + epochMilli);
        if (epochMilli == 0) {
            System.out.println("ERROR: toEpochMilli should return valid epoch milliseconds!");
            return;
        }
        
        // Test toEpochMilli with null
        epochMilli = DateTimeUtils.toEpochMilli(null);
        System.out.println("toEpochMilli(null) = " + epochMilli);
        if (epochMilli != 0) {
            System.out.println("ERROR: toEpochMilli should return 0 for null!");
            return;
        }
        
        System.out.println("conversion methods test passed.");
    }
}