// SPDX-License-Identifier: Apache-2.0
// Copyright (c) 2026 AshClaw.

package cc.ashclaw.core.date;

import java.time.LocalDateTime;

import cc.ashclaw.common4j.core.date.DateTimeUtil;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for DateTimeUtils result verification using JUnit 5.
 * <p>
 * DateTimeUtils结果验证测试类，使用JUnit 5。
 *
 * @author b1itz7
 * @since 1.0.0
 */
public class DateTimeUtilTest {

    /**
     * Test the basic date-time methods of DateTimeUtils.
     * <p>
     * 测试DateTimeUtils的基本日期时间方法。
     */
    @Test
    void testBasicDateTimeMethods() {
        // Test now
        LocalDateTime result = DateTimeUtil.now();
        assertNotNull(result, "now should return current date-time");
    }

    /**
     * Test the date-time formatting methods of DateTimeUtils.
     * <p>
     * 测试DateTimeUtils的日期时间格式化方法。
     */
    @Test
    void testDateTimeFormattingMethods() {
        LocalDateTime dateTime = LocalDateTime.of(2023, 12, 25, 10, 30, 45);
        
        // Test format with default pattern
        String result = DateTimeUtil.format(dateTime);
        assertEquals("2023-12-25 10:30:45", result, "format with default pattern should return yyyy-MM-dd HH:mm:ss format");
        
        // Test format with custom pattern
        result = DateTimeUtil.format(dateTime, "yyyy/MM/dd HH:mm:ss");
        assertEquals("2023/12/25 10:30:45", result, "format with custom pattern should use specified pattern");
        
        // Test format with null date-time
        result = DateTimeUtil.format(null);
        assertNull(result, "format should return null for null date-time");
    }

    /**
     * Test the date-time parsing methods of DateTimeUtils.
     * <p>
     * 测试DateTimeUtils的日期时间解析方法。
     */
    @Test
    void testDateTimeParsingMethods() {
        // Test parse with default pattern
        LocalDateTime result = DateTimeUtil.parse("2023-12-25 10:30:45");
        assertNotNull(result, "parse with default pattern should parse yyyy-MM-dd HH:mm:ss format");
        assertEquals(LocalDateTime.of(2023, 12, 25, 10, 30, 45), result, "parse with default pattern should parse yyyy-MM-dd HH:mm:ss format");
        
        // Test parse with custom pattern
        result = DateTimeUtil.parse("2023/12/25 10:30:45", "yyyy/MM/dd HH:mm:ss");
        assertNotNull(result, "parse with custom pattern should parse specified pattern");
        assertEquals(LocalDateTime.of(2023, 12, 25, 10, 30, 45), result, "parse with custom pattern should parse specified pattern");
        
        // Test parse with null string
        result = DateTimeUtil.parse(null);
        assertNull(result, "parse should return null for null string");
    }

    /**
     * Test the quiet parsing methods of DateTimeUtils.
     * <p>
     * 测试DateTimeUtils的安静解析方法。
     */
    @Test
    void testQuietParsingMethods() {
        // Test parseQuietly with valid string
        LocalDateTime result = DateTimeUtil.parseQuietly("2023-12-25 10:30:45");
        assertNotNull(result, "parseQuietly should parse valid date-time string");
        assertEquals(LocalDateTime.of(2023, 12, 25, 10, 30, 45), result, "parseQuietly should parse valid date-time string");
        
        // Test parseQuietly with invalid string
        result = DateTimeUtil.parseQuietly("invalid-date-time");
        assertNull(result, "parseQuietly should return null for invalid string");
        
        // Test parseQuietly with custom pattern and invalid string
        result = DateTimeUtil.parseQuietly("2023/12/25", "yyyy-MM-dd HH:mm:ss");
        assertNull(result, "parseQuietly should return null for invalid string with custom pattern");
    }

    /**
     * Test the conversion methods of DateTimeUtils.
     * <p>
     * 测试DateTimeUtils的转换方法。
     */
    @Test
    void testConversionMethods() {
        LocalDateTime dateTime = LocalDateTime.of(2023, 12, 25, 10, 30, 45);
        
        // Test toEpochSecond
        long epochSecond = DateTimeUtil.toEpochSecond(dateTime);
        assertNotEquals(0, epochSecond, "toEpochSecond should return valid epoch seconds");
        
        // Test toEpochSecond with null
        epochSecond = DateTimeUtil.toEpochSecond(null);
        assertEquals(0, epochSecond, "toEpochSecond should return 0 for null");
        
        // Test toEpochMilli
        long epochMilli = DateTimeUtil.toEpochMilli(dateTime);
        assertNotEquals(0, epochMilli, "toEpochMilli should return valid epoch milliseconds");
        
        // Test toEpochMilli with null
        epochMilli = DateTimeUtil.toEpochMilli(null);
        assertEquals(0, epochMilli, "toEpochMilli should return 0 for null");
    }
}