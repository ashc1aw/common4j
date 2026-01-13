// SPDX-License-Identifier: Apache-2.0
// Copyright (c) 2026 AshClaw.

package cc.ashclaw.common4j.date;

import java.time.LocalDate;

/**
 * Test class for DateUtils result verification.
 * <p>
 * DateUtils结果验证测试类。
 *
 * @author b1itz7
 * @since 1.0.0
 */
public class DateUtilsTest {

    public static void main(String[] args) {
        System.out.println("===== DateUtils Result Verification Test Start =====");
        
        // Test basic date methods
        testBasicDateMethods();
        
        // Test date formatting methods
        testDateFormattingMethods();
        
        // Test date parsing methods
        testDateParsingMethods();
        
        // Test date manipulation methods
        testDateManipulationMethods();
        
        // Test date comparison methods
        testDateComparisonMethods();
        
        // Test date information methods
        testDateInformationMethods();
        
        System.out.println("===== DateUtils Result Verification Test End =====");
    }

    /**
     * Test the basic date methods of DateUtils.
     * <p>
     * 测试DateUtils的基本日期方法。
     */
    private static void testBasicDateMethods() {
        System.out.println("\n1. Testing basic date methods...");
        
        // Test today
        LocalDate result = DateUtils.today();
        System.out.println("today() = " + result);
        if (result == null) {
            System.out.println("ERROR: today should return current date!");
            return;
        }
        
        // Test yesterday
        LocalDate yesterday = DateUtils.yesterday();
        System.out.println("yesterday() = " + yesterday);
        if (yesterday == null || !yesterday.isBefore(result)) {
            System.out.println("ERROR: yesterday should return previous day!");
            return;
        }
        
        // Test tomorrow
        LocalDate tomorrow = DateUtils.tomorrow();
        System.out.println("tomorrow() = " + tomorrow);
        if (tomorrow == null || !tomorrow.isAfter(result)) {
            System.out.println("ERROR: tomorrow should return next day!");
            return;
        }
        
        System.out.println("basic date methods test passed.");
    }

    /**
     * Test the date formatting methods of DateUtils.
     * <p>
     * 测试DateUtils的日期格式化方法。
     */
    private static void testDateFormattingMethods() {
        System.out.println("\n2. Testing date formatting methods...");
        
        LocalDate date = LocalDate.of(2023, 12, 25);
        
        // Test format with default pattern
        String result = DateUtils.format(date);
        System.out.println("format(LocalDate.of(2023, 12, 25)) = \"" + result + "\"");
        if (!"2023-12-25".equals(result)) {
            System.out.println("ERROR: format with default pattern should return yyyy-MM-dd format!");
            return;
        }
        
        // Test format with custom pattern
        result = DateUtils.format(date, "yyyy/MM/dd");
        System.out.println("format(LocalDate.of(2023, 12, 25), \"yyyy/MM/dd\") = \"" + result + "\"");
        if (!"2023/12/25".equals(result)) {
            System.out.println("ERROR: format with custom pattern should use specified pattern!");
            return;
        }
        
        // Test format with null date
        result = DateUtils.format(null);
        System.out.println("format(null) = " + result);
        if (result != null) {
            System.out.println("ERROR: format should return null for null date!");
            return;
        }
        
        // Test todayStr
        result = DateUtils.todayStr();
        System.out.println("todayStr() = \"" + result + "\"");
        if (result == null || !result.matches("\\d{4}-\\d{2}-\\d{2}")) {
            System.out.println("ERROR: todayStr should return current date in yyyy-MM-dd format!");
            return;
        }
        
        System.out.println("date formatting methods test passed.");
    }

    /**
     * Test the date parsing methods of DateUtils.
     * <p>
     * 测试DateUtils的日期解析方法。
     */
    private static void testDateParsingMethods() {
        System.out.println("\n3. Testing date parsing methods...");
        
        // Test parse with default pattern
        LocalDate result = DateUtils.parse("2023-12-25");
        System.out.println("parse(\"2023-12-25\") = " + result);
        if (result == null || !result.equals(LocalDate.of(2023, 12, 25))) {
            System.out.println("ERROR: parse with default pattern should parse yyyy-MM-dd format!");
            return;
        }
        
        // Test parse with custom pattern
        result = DateUtils.parse("2023/12/25", "yyyy/MM/dd");
        System.out.println("parse(\"2023/12/25\", \"yyyy/MM/dd\") = " + result);
        if (result == null || !result.equals(LocalDate.of(2023, 12, 25))) {
            System.out.println("ERROR: parse with custom pattern should parse specified pattern!");
            return;
        }
        
        // Test parse with null string
        result = DateUtils.parse(null);
        System.out.println("parse(null) = " + result);
        if (result != null) {
            System.out.println("ERROR: parse should return null for null string!");
            return;
        }
        
        System.out.println("date parsing methods test passed.");
    }

    /**
     * Test the date manipulation methods of DateUtils.
     * <p>
     * 测试DateUtils的日期操作方法。
     */
    private static void testDateManipulationMethods() {
        System.out.println("\n4. Testing date manipulation methods...");
        
        LocalDate date = LocalDate.of(2023, 12, 25);
        
        // Test addDays
        LocalDate result = DateUtils.addDays(date, 5);
        System.out.println("addDays(2023-12-25, 5) = " + result);
        if (result == null || !result.equals(LocalDate.of(2023, 12, 30))) {
            System.out.println("ERROR: addDays should add specified days!");
            return;
        }
        
        // Test addMonths
        result = DateUtils.addMonths(date, 1);
        System.out.println("addMonths(2023-12-25, 1) = " + result);
        if (result == null || !result.equals(LocalDate.of(2024, 1, 25))) {
            System.out.println("ERROR: addMonths should add specified months!");
            return;
        }
        
        // Test addYears
        result = DateUtils.addYears(date, 1);
        System.out.println("addYears(2023-12-25, 1) = " + result);
        if (result == null || !result.equals(LocalDate.of(2024, 12, 25))) {
            System.out.println("ERROR: addYears should add specified years!");
            return;
        }
        
        // Test of(int, int, int)
        result = DateUtils.of(2023, 12, 25);
        System.out.println("of(2023, 12, 25) = " + result);
        if (result == null || !result.equals(LocalDate.of(2023, 12, 25))) {
            System.out.println("ERROR: of should create LocalDate from year, month, day!");
            return;
        }
        
        System.out.println("date manipulation methods test passed.");
    }

    /**
     * Test the date comparison methods of DateUtils.
     * <p>
     * 测试DateUtils的日期比较方法。
     */
    private static void testDateComparisonMethods() {
        System.out.println("\n5. Testing date comparison methods...");
        
        LocalDate date1 = LocalDate.of(2023, 12, 25);
        LocalDate date2 = LocalDate.of(2023, 12, 25);
        LocalDate date3 = LocalDate.of(2024, 1, 1);
        LocalDate date4 = LocalDate.of(2023, 12, 20);
        
        // Test isSameDay
        boolean result = DateUtils.isSameDay(date1, date2);
        System.out.println("isSameDay(2023-12-25, 2023-12-25) = " + result);
        if (!result) {
            System.out.println("ERROR: isSameDay should return true for same dates!");
            return;
        }
        
        result = DateUtils.isSameDay(date1, date3);
        System.out.println("isSameDay(2023-12-25, 2024-01-01) = " + result);
        if (result) {
            System.out.println("ERROR: isSameDay should return false for different dates!");
            return;
        }
        
        // Test isBetween
        result = DateUtils.isBetween(date1, date4, date3);
        System.out.println("isBetween(2023-12-25, 2023-12-20, 2024-01-01) = " + result);
        if (!result) {
            System.out.println("ERROR: isBetween should return true for date in range!");
            return;
        }
        
        result = DateUtils.isBetween(date3, date4, date1);
        System.out.println("isBetween(2024-01-01, 2023-12-20, 2023-12-25) = " + result);
        if (result) {
            System.out.println("ERROR: isBetween should return false for date out of range!");
            return;
        }
        
        System.out.println("date comparison methods test passed.");
    }

    /**
     * Test the date information methods of DateUtils.
     * <p>
     * 测试DateUtils的日期信息方法。
     */
    private static void testDateInformationMethods() {
        System.out.println("\n6. Testing date information methods...");
        
        LocalDate date = LocalDate.of(2023, 12, 25); // Monday
        LocalDate weekend = LocalDate.of(2023, 12, 23); // Saturday
        LocalDate leapYearDate = LocalDate.of(2024, 2, 29);
        LocalDate nonLeapYearDate = LocalDate.of(2023, 2, 28);
        
        // Test isWeekend
        boolean result = DateUtils.isWeekend(weekend);
        System.out.println("isWeekend(2023-12-23) = " + result);
        if (!result) {
            System.out.println("ERROR: isWeekend should return true for Saturday!");
            return;
        }
        
        result = DateUtils.isWeekend(date);
        System.out.println("isWeekend(2023-12-25) = " + result);
        if (result) {
            System.out.println("ERROR: isWeekend should return false for Monday!");
            return;
        }
        
        // Test isWeekday
        result = DateUtils.isWeekday(date);
        System.out.println("isWeekday(2023-12-25) = " + result);
        if (!result) {
            System.out.println("ERROR: isWeekday should return true for Monday!");
            return;
        }
        
        // Test getQuarter
        int quarter = DateUtils.getQuarter(date);
        System.out.println("getQuarter(2023-12-25) = " + quarter);
        if (quarter != 4) {
            System.out.println("ERROR: getQuarter should return 4 for December!");
            return;
        }
        
        // Test getDayOfWeekNumber
        int dayOfWeek = DateUtils.getDayOfWeekNumber(date);
        System.out.println("getDayOfWeekNumber(2023-12-25) = " + dayOfWeek);
        if (dayOfWeek != 1) {
            System.out.println("ERROR: getDayOfWeekNumber should return 1 for Monday!");
            return;
        }
        
        // Test getDayOfWeekChinese
        String dayOfWeekChinese = DateUtils.getDayOfWeekChinese(date);
        System.out.println("getDayOfWeekChinese(2023-12-25) = " + dayOfWeekChinese);
        if (!"周一".equals(dayOfWeekChinese)) {
            System.out.println("ERROR: getDayOfWeekChinese should return '周一' for Monday!");
            return;
        }
        
        // Test isLeapYear
        result = DateUtils.isLeapYear(leapYearDate);
        System.out.println("isLeapYear(2024-02-29) = " + result);
        if (!result) {
            System.out.println("ERROR: isLeapYear should return true for 2024!");
            return;
        }
        
        result = DateUtils.isLeapYear(nonLeapYearDate);
        System.out.println("isLeapYear(2023-02-28) = " + result);
        if (result) {
            System.out.println("ERROR: isLeapYear should return false for 2023!");
            return;
        }
        
        // Test getDaysInMonth
        int daysInMonth = DateUtils.getDaysInMonth(date);
        System.out.println("getDaysInMonth(2023-12-25) = " + daysInMonth);
        if (daysInMonth != 31) {
            System.out.println("ERROR: getDaysInMonth should return 31 for December!");
            return;
        }
        
        // Test calculateAge
        int age = DateUtils.calculateAge(LocalDate.of(2000, 1, 1), LocalDate.of(2023, 12, 31));
        System.out.println("calculateAge(2000-01-01, 2023-12-31) = " + age);
        if (age != 23) {
            System.out.println("ERROR: calculateAge should return 23 for 2000-2023!");
            return;
        }
        
        System.out.println("date information methods test passed.");
    }
}