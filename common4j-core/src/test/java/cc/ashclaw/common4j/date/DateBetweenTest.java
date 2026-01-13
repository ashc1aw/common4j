// SPDX-License-Identifier: Apache-2.0
// Copyright (c) 2026 AshClaw.

package cc.ashclaw.common4j.date;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Test class for DateBetween result verification.
 * <p>
 * DateBetween结果验证测试类。
 *
 * @author b1itz7
 * @since 1.0.0
 */
public class DateBetweenTest {

    public static void main(String[] args) {
        System.out.println("===== DateBetween Result Verification Test Start =====");
        
        // Test days between method
        testDaysBetweenMethod();
        
        // Test seconds between method
        testSecondsBetweenMethod();
        
        // Test minutes between method
        testMinutesBetweenMethod();
        
        // Test hours between method
        testHoursBetweenMethod();
        
        System.out.println("===== DateBetween Result Verification Test End =====");
    }

    /**
     * Test the daysBetween method of DateBetween.
     * <p>
     * 测试DateBetween的daysBetween方法。
     */
    private static void testDaysBetweenMethod() {
        System.out.println("\n1. Testing daysBetween method...");
        
        // Test days between two dates
        LocalDate date1 = LocalDate.of(2023, 12, 20);
        LocalDate date2 = LocalDate.of(2023, 12, 25);
        long result = DateBetween.daysBetween(date1, date2);
        System.out.println("daysBetween(2023-12-20, 2023-12-25) = " + result);
        if (result != 5) {
            System.out.println("ERROR: daysBetween should return 5 days!");
            return;
        }
        
        // Test days between same dates
        result = DateBetween.daysBetween(date1, date1);
        System.out.println("daysBetween(2023-12-20, 2023-12-20) = " + result);
        if (result != 0) {
            System.out.println("ERROR: daysBetween should return 0 for same dates!");
            return;
        }
        
        // Test days between dates in reverse order
        result = DateBetween.daysBetween(date2, date1);
        System.out.println("daysBetween(2023-12-25, 2023-12-20) = " + result);
        if (result != 5) {
            System.out.println("ERROR: daysBetween should return absolute difference!");
            return;
        }
        
        // Test days between with null dates
        result = DateBetween.daysBetween(null, date1);
        System.out.println("daysBetween(null, 2023-12-20) = " + result);
        if (result != 0) {
            System.out.println("ERROR: daysBetween should return 0 for null dates!");
            return;
        }
        
        System.out.println("daysBetween method test passed.");
    }

    /**
     * Test the secondsBetween method of DateBetween.
     * <p>
     * 测试DateBetween的secondsBetween方法。
     */
    private static void testSecondsBetweenMethod() {
        System.out.println("\n2. Testing secondsBetween method...");
        
        // Test seconds between two times
        LocalTime time1 = LocalTime.of(10, 30, 0);
        LocalTime time2 = LocalTime.of(10, 30, 45);
        long result = DateBetween.secondsBetween(time1, time2);
        System.out.println("secondsBetween(10:30:00, 10:30:45) = " + result);
        if (result != 45) {
            System.out.println("ERROR: secondsBetween should return 45 seconds!");
            return;
        }
        
        // Test seconds between times with hours difference
        time1 = LocalTime.of(10, 30, 0);
        time2 = LocalTime.of(11, 30, 0);
        result = DateBetween.secondsBetween(time1, time2);
        System.out.println("secondsBetween(10:30:00, 11:30:00) = " + result);
        if (result != 3600) {
            System.out.println("ERROR: secondsBetween should return 3600 seconds for 1 hour!");
            return;
        }
        
        // Test seconds between same times
        result = DateBetween.secondsBetween(time1, time1);
        System.out.println("secondsBetween(10:30:00, 10:30:00) = " + result);
        if (result != 0) {
            System.out.println("ERROR: secondsBetween should return 0 for same times!");
            return;
        }
        
        // Test seconds between with null times
        result = DateBetween.secondsBetween(null, time1);
        System.out.println("secondsBetween(null, 10:30:00) = " + result);
        if (result != 0) {
            System.out.println("ERROR: secondsBetween should return 0 for null times!");
            return;
        }
        
        System.out.println("secondsBetween method test passed.");
    }

    /**
     * Test the minutesBetween method of DateBetween.
     * <p>
     * 测试DateBetween的minutesBetween方法。
     */
    private static void testMinutesBetweenMethod() {
        System.out.println("\n3. Testing minutesBetween method...");
        
        // Test minutes between two times
        LocalTime time1 = LocalTime.of(10, 30, 0);
        LocalTime time2 = LocalTime.of(10, 45, 0);
        long result = DateBetween.minutesBetween(time1, time2);
        System.out.println("minutesBetween(10:30:00, 10:45:00) = " + result);
        if (result != 15) {
            System.out.println("ERROR: minutesBetween should return 15 minutes!");
            return;
        }
        
        // Test minutes between times with hours difference
        time1 = LocalTime.of(10, 30, 0);
        time2 = LocalTime.of(12, 30, 0);
        result = DateBetween.minutesBetween(time1, time2);
        System.out.println("minutesBetween(10:30:00, 12:30:00) = " + result);
        if (result != 120) {
            System.out.println("ERROR: minutesBetween should return 120 minutes for 2 hours!");
            return;
        }
        
        // Test minutes between same times
        result = DateBetween.minutesBetween(time1, time1);
        System.out.println("minutesBetween(10:30:00, 10:30:00) = " + result);
        if (result != 0) {
            System.out.println("ERROR: minutesBetween should return 0 for same times!");
            return;
        }
        
        // Test minutes between with null times
        result = DateBetween.minutesBetween(null, time1);
        System.out.println("minutesBetween(null, 10:30:00) = " + result);
        if (result != 0) {
            System.out.println("ERROR: minutesBetween should return 0 for null times!");
            return;
        }
        
        System.out.println("minutesBetween method test passed.");
    }

    /**
     * Test the hoursBetween method of DateBetween.
     * <p>
     * 测试DateBetween的hoursBetween方法。
     */
    private static void testHoursBetweenMethod() {
        System.out.println("\n4. Testing hoursBetween method...");
        
        // Test hours between two times
        LocalTime time1 = LocalTime.of(10, 30, 0);
        LocalTime time2 = LocalTime.of(12, 30, 0);
        long result = DateBetween.hoursBetween(time1, time2);
        System.out.println("hoursBetween(10:30:00, 12:30:00) = " + result);
        if (result != 2) {
            System.out.println("ERROR: hoursBetween should return 2 hours!");
            return;
        }
        
        // Test hours between times with day difference
        time1 = LocalTime.of(22, 0, 0);
        time2 = LocalTime.of(2, 0, 0);
        result = DateBetween.hoursBetween(time1, time2);
        System.out.println("hoursBetween(22:00:00, 02:00:00) = " + result);
        if (result != 4) {
            System.out.println("ERROR: hoursBetween should return 4 hours!");
            return;
        }
        
        // Test hours between same times
        result = DateBetween.hoursBetween(time1, time1);
        System.out.println("hoursBetween(22:00:00, 22:00:00) = " + result);
        if (result != 0) {
            System.out.println("ERROR: hoursBetween should return 0 for same times!");
            return;
        }
        
        // Test hours between with null times
        result = DateBetween.hoursBetween(null, time1);
        System.out.println("hoursBetween(null, 22:00:00) = " + result);
        if (result != 0) {
            System.out.println("ERROR: hoursBetween should return 0 for null times!");
            return;
        }
        
        System.out.println("hoursBetween method test passed.");
    }
}