// SPDX-License-Identifier: Apache-2.0
// Copyright (c) 2026 AshClaw.

package cc.ashclaw.core.date;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cc.ashclaw.common4j.core.date.DateBetween;
import org.junit.jupiter.api.Test;

/**
 * Test class for DateBetween result verification using JUnit 5.
 * <p>
 * DateBetween结果验证测试类，使用JUnit 5。
 *
 * @author b1itz7
 * @since 1.0.0
 */
public class DateBetweenTest {

    /**
     * Test the daysBetween method of DateBetween.
     * <p>
     * 测试DateBetween的daysBetween方法。
     */
    @Test
    void testDaysBetweenMethod() {
        // Test days between two dates
        LocalDate date1 = LocalDate.of(2023, 12, 20);
        LocalDate date2 = LocalDate.of(2023, 12, 25);
        long result = DateBetween.daysBetween(date1, date2);
        assertEquals(5, result, "daysBetween should return 5 days");
        
        // Test days between same dates
        result = DateBetween.daysBetween(date1, date1);
        assertEquals(0, result, "daysBetween should return 0 for same dates");
        
        // Test days between dates in reverse order
        result = DateBetween.daysBetween(date2, date1);
        assertEquals(5, result, "daysBetween should return absolute difference");
        
        // Test days between with null dates
        result = DateBetween.daysBetween(null, date1);
        assertEquals(0, result, "daysBetween should return 0 for null dates");
    }

    /**
     * Test the secondsBetween method of DateBetween.
     * <p>
     * 测试DateBetween的secondsBetween方法。
     */
    @Test
    void testSecondsBetweenMethod() {
        // Test seconds between two times
        LocalTime time1 = LocalTime.of(10, 30, 0);
        LocalTime time2 = LocalTime.of(10, 30, 45);
        long result = DateBetween.secondsBetween(time1, time2);
        assertEquals(45, result, "secondsBetween should return 45 seconds");
        
        // Test seconds between times with hours difference
        time1 = LocalTime.of(10, 30, 0);
        time2 = LocalTime.of(11, 30, 0);
        result = DateBetween.secondsBetween(time1, time2);
        assertEquals(3600, result, "secondsBetween should return 3600 seconds for 1 hour");
        
        // Test seconds between same times
        result = DateBetween.secondsBetween(time1, time1);
        assertEquals(0, result, "secondsBetween should return 0 for same times");
        
        // Test seconds between with null times
        result = DateBetween.secondsBetween(null, time1);
        assertEquals(0, result, "secondsBetween should return 0 for null times");
    }

    /**
     * Test the minutesBetween method of DateBetween.
     * <p>
     * 测试DateBetween的minutesBetween方法。
     */
    @Test
    void testMinutesBetweenMethod() {
        // Test minutes between two times
        LocalTime time1 = LocalTime.of(10, 30, 0);
        LocalTime time2 = LocalTime.of(10, 45, 0);
        long result = DateBetween.minutesBetween(time1, time2);
        assertEquals(15, result, "minutesBetween should return 15 minutes");
        
        // Test minutes between times with hours difference
        time1 = LocalTime.of(10, 30, 0);
        time2 = LocalTime.of(12, 30, 0);
        result = DateBetween.minutesBetween(time1, time2);
        assertEquals(120, result, "minutesBetween should return 120 minutes for 2 hours");
        
        // Test minutes between same times
        result = DateBetween.minutesBetween(time1, time1);
        assertEquals(0, result, "minutesBetween should return 0 for same times");
        
        // Test minutes between with null times
        result = DateBetween.minutesBetween(null, time1);
        assertEquals(0, result, "minutesBetween should return 0 for null times");
    }

    /**
     * Test the hoursBetween method of DateBetween.
     * <p>
     * 测试DateBetween的hoursBetween方法。
     */
    @Test
    void testHoursBetweenMethod() {
        // Test hours between two times
        LocalTime time1 = LocalTime.of(10, 30, 0);
        LocalTime time2 = LocalTime.of(12, 30, 0);
        long result = DateBetween.hoursBetween(time1, time2);
        assertEquals(2, result, "hoursBetween should return 2 hours");
        
        // Test hours between times with day difference
        time1 = LocalTime.of(22, 0, 0);
        time2 = LocalTime.of(2, 0, 0);
        result = DateBetween.hoursBetween(time1, time2);
        assertEquals(4, result, "hoursBetween should return 4 hours");
        
        // Test hours between same times
        result = DateBetween.hoursBetween(time1, time1);
        assertEquals(0, result, "hoursBetween should return 0 for same times");
        
        // Test hours between with null times
        result = DateBetween.hoursBetween(null, time1);
        assertEquals(0, result, "hoursBetween should return 0 for null times");
    }
}