// SPDX-License-Identifier: Apache-2.0
// Copyright (c) 2026 AshClaw.

package cc.ashclaw.common4j.date;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for DateUtils result verification using JUnit 5.
 * <p>
 * DateUtils结果验证测试类，使用JUnit 5。
 *
 * @author b1itz7
 * @since 1.0.0
 */
public class DateUtilTest {

    /**
     * Test the basic date methods of DateUtils.
     * <p>
     * 测试DateUtils的基本日期方法。
     */
    @Test
    void testBasicDateMethods() {
        // Test today
        LocalDate result = DateUtil.today();
        assertNotNull(result, "today should return current date");
        
        // Test yesterday
        LocalDate yesterday = DateUtil.yesterday();
        assertNotNull(yesterday, "yesterday should return previous day");
        assertTrue(yesterday.isBefore(result), "yesterday should be before today");
        
        // Test tomorrow
        LocalDate tomorrow = DateUtil.tomorrow();
        assertNotNull(tomorrow, "tomorrow should return next day");
        assertTrue(tomorrow.isAfter(result), "tomorrow should be after today");
    }

    /**
     * Test the date formatting methods of DateUtils.
     * <p>
     * 测试DateUtils的日期格式化方法。
     */
    @Test
    void testDateFormattingMethods() {
        LocalDate date = LocalDate.of(2023, 12, 25);
        
        // Test format with default pattern
        String result = DateUtil.format(date);
        assertEquals("2023-12-25", result, "format with default pattern should return yyyy-MM-dd format");
        
        // Test format with custom pattern
        result = DateUtil.format(date, "yyyy/MM/dd");
        assertEquals("2023/12/25", result, "format with custom pattern should use specified pattern");
        
        // Test format with null date
        result = DateUtil.format(null);
        assertNull(result, "format should return null for null date");
        
        // Test todayStr
        result = DateUtil.todayStr();
        assertNotNull(result, "todayStr should return current date string");
        assertTrue(result.matches("\\d{4}-\\d{2}-\\d{2}"), "todayStr should return date in yyyy-MM-dd format");
    }

    /**
     * Test the date parsing methods of DateUtils.
     * <p>
     * 测试DateUtils的日期解析方法。
     */
    @Test
    void testDateParsingMethods() {
        // Test parse with default pattern
        LocalDate result = DateUtil.parse("2023-12-25");
        assertNotNull(result, "parse with default pattern should parse yyyy-MM-dd format");
        assertEquals(LocalDate.of(2023, 12, 25), result, "parse with default pattern should parse yyyy-MM-dd format");
        
        // Test parse with custom pattern
        result = DateUtil.parse("2023/12/25", "yyyy/MM/dd");
        assertNotNull(result, "parse with custom pattern should parse specified pattern");
        assertEquals(LocalDate.of(2023, 12, 25), result, "parse with custom pattern should parse specified pattern");
        
        // Test parse with null string
        result = DateUtil.parse(null);
        assertNull(result, "parse should return null for null string");
    }

    /**
     * Test the date manipulation methods of DateUtils.
     * <p>
     * 测试DateUtils的日期操作方法。
     */
    @Test
    void testDateManipulationMethods() {
        LocalDate date = LocalDate.of(2023, 12, 25);
        
        // Test addDays
        LocalDate result = DateUtil.addDays(date, 5);
        assertNotNull(result, "addDays should add specified days");
        assertEquals(LocalDate.of(2023, 12, 30), result, "addDays should add specified days");
        
        // Test addMonths
        result = DateUtil.addMonths(date, 1);
        assertNotNull(result, "addMonths should add specified months");
        assertEquals(LocalDate.of(2024, 1, 25), result, "addMonths should add specified months");
        
        // Test addYears
        result = DateUtil.addYears(date, 1);
        assertNotNull(result, "addYears should add specified years");
        assertEquals(LocalDate.of(2024, 12, 25), result, "addYears should add specified years");
        
        // Test of(int, int, int)
        result = DateUtil.of(2023, 12, 25);
        assertNotNull(result, "of should create LocalDate from year, month, day");
        assertEquals(LocalDate.of(2023, 12, 25), result, "of should create LocalDate from year, month, day");
    }

    /**
     * Test the date comparison methods of DateUtils.
     * <p>
     * 测试DateUtils的日期比较方法。
     */
    @Test
    void testDateComparisonMethods() {
        LocalDate date1 = LocalDate.of(2023, 12, 25);
        LocalDate date2 = LocalDate.of(2023, 12, 25);
        LocalDate date3 = LocalDate.of(2024, 1, 1);
        LocalDate date4 = LocalDate.of(2023, 12, 20);
        
        // Test isSameDay
        boolean result = DateUtil.isSameDay(date1, date2);
        assertTrue(result, "isSameDay should return true for same dates");
        
        result = DateUtil.isSameDay(date1, date3);
        assertFalse(result, "isSameDay should return false for different dates");
        
        // Test isBetween
        result = DateUtil.isBetween(date1, date4, date3);
        assertTrue(result, "isBetween should return true for date in range");
        
        result = DateUtil.isBetween(date3, date4, date1);
        assertFalse(result, "isBetween should return false for date out of range");
    }

    /**
     * Test the date information methods of DateUtils.
     * <p>
     * 测试DateUtils的日期信息方法。
     */
    @Test
    void testDateInformationMethods() {
        LocalDate date = LocalDate.of(2023, 12, 25); // Monday
        LocalDate weekend = LocalDate.of(2023, 12, 23); // Saturday
        LocalDate leapYearDate = LocalDate.of(2024, 2, 29);
        LocalDate nonLeapYearDate = LocalDate.of(2023, 2, 28);
        
        // Test isWeekend
        boolean result = DateUtil.isWeekend(weekend);
        assertTrue(result, "isWeekend should return true for Saturday");
        
        result = DateUtil.isWeekend(date);
        assertFalse(result, "isWeekend should return false for Monday");
        
        // Test isWeekday
        result = DateUtil.isWeekday(date);
        assertTrue(result, "isWeekday should return true for Monday");
        
        // Test getQuarter
        int quarter = DateUtil.getQuarter(date);
        assertEquals(4, quarter, "getQuarter should return 4 for December");
        
        // Test getDayOfWeekNumber
        int dayOfWeek = DateUtil.getDayOfWeekNumber(date);
        assertEquals(1, dayOfWeek, "getDayOfWeekNumber should return 1 for Monday");
        
        // Test getDayOfWeekChinese
        String dayOfWeekChinese = DateUtil.getDayOfWeekChinese(date);
        assertEquals("周一", dayOfWeekChinese, "getDayOfWeekChinese should return '周一' for Monday");
        
        // Test isLeapYear
        result = DateUtil.isLeapYear(leapYearDate);
        assertTrue(result, "isLeapYear should return true for 2024");
        
        result = DateUtil.isLeapYear(nonLeapYearDate);
        assertFalse(result, "isLeapYear should return false for 2023");
        
        // Test getDaysInMonth
        int daysInMonth = DateUtil.getDaysInMonth(date);
        assertEquals(31, daysInMonth, "getDaysInMonth should return 31 for December");
        
        // Test calculateAge
        int age = DateUtil.calculateAge(LocalDate.of(2000, 1, 1), LocalDate.of(2023, 12, 31));
        assertEquals(23, age, "calculateAge should return 23 for 2000-2023");
    }
}