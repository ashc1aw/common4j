// SPDX-License-Identifier: Apache-2.0
// Copyright (c) 2026 AshClaw.

package cc.ashclaw.common4j.date;

import cc.ashclaw.common4j.constant.DateFormats;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Test class for DateTimeFormatters result verification.
 * <p>
 * DateTimeFormatters结果验证测试类。
 *
 * @author b1itz7
 * @since 1.0.0
 */
public class DateTimeFormattersTest {

    public static void main(String[] args) {
        System.out.println("===== DateTimeFormatters Result Verification Test Start =====");
        
        // Test all formatter instances
        testFormatterInstances();
        
        // Test formatter functionality
        testFormatterFunctionality();
        
        System.out.println("===== DateTimeFormatters Result Verification Test End =====");
    }

    /**
     * Test the formatter instances of DateTimeFormatters.
     * <p>
     * 测试DateTimeFormatters的格式化器实例。
     */
    private static void testFormatterInstances() {
        System.out.println("\n1. Testing formatter instances...");
        
        // Test FMT_DATETIME
        if (DateTimeFormatters.FMT_DATETIME == null) {
            System.out.println("ERROR: FMT_DATETIME should not be null!");
            return;
        }
        System.out.println("FMT_DATETIME = " + DateTimeFormatters.FMT_DATETIME);
        
        // Test FMT_DATE
        if (DateTimeFormatters.FMT_DATE == null) {
            System.out.println("ERROR: FMT_DATE should not be null!");
            return;
        }
        System.out.println("FMT_DATE = " + DateTimeFormatters.FMT_DATE);
        
        // Test FMT_TIME
        if (DateTimeFormatters.FMT_TIME == null) {
            System.out.println("ERROR: FMT_TIME should not be null!");
            return;
        }
        System.out.println("FMT_TIME = " + DateTimeFormatters.FMT_TIME);
        
        // Test FMT_COMPACT
        if (DateTimeFormatters.FMT_COMPACT == null) {
            System.out.println("ERROR: FMT_COMPACT should not be null!");
            return;
        }
        System.out.println("FMT_COMPACT = " + DateTimeFormatters.FMT_COMPACT);
        
        System.out.println("formatter instances test passed.");
    }

    /**
     * Test the functionality of DateTimeFormatters.
     * <p>
     * 测试DateTimeFormatters的功能。
     */
    private static void testFormatterFunctionality() {
        System.out.println("\n2. Testing formatter functionality...");
        
        LocalDateTime dateTime = LocalDateTime.of(2023, 12, 25, 10, 30, 45);
        LocalDate date = dateTime.toLocalDate();
        LocalTime time = dateTime.toLocalTime();
        
        // Test FMT_DATETIME
        String formatted = DateTimeFormatters.FMT_DATETIME.format(dateTime);
        System.out.println("FMT_DATETIME.format(2023-12-25T10:30:45) = \"" + formatted + "\"");
        if (!"2023-12-25 10:30:45".equals(formatted)) {
            System.out.println("ERROR: FMT_DATETIME should format as yyyy-MM-dd HH:mm:ss!");
            return;
        }
        
        // Test FMT_DATE
        formatted = DateTimeFormatters.FMT_DATE.format(date);
        System.out.println("FMT_DATE.format(2023-12-25) = \"" + formatted + "\"");
        if (!"2023-12-25".equals(formatted)) {
            System.out.println("ERROR: FMT_DATE should format as yyyy-MM-dd!");
            return;
        }
        
        // Test FMT_TIME
        formatted = DateTimeFormatters.FMT_TIME.format(time);
        System.out.println("FMT_TIME.format(10:30:45) = \"" + formatted + "\"");
        if (!"10:30:45".equals(formatted)) {
            System.out.println("ERROR: FMT_TIME should format as HH:mm:ss!");
            return;
        }
        
        // Test FMT_COMPACT
        formatted = DateTimeFormatters.FMT_COMPACT.format(date);
        System.out.println("FMT_COMPACT.format(2023-12-25) = \"" + formatted + "\"");
        if (!"20231225".equals(formatted)) {
            System.out.println("ERROR: FMT_COMPACT should format as yyyyMMdd!");
            return;
        }
        
        System.out.println("formatter functionality test passed.");
    }
}