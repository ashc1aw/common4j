// SPDX-License-Identifier: Apache-2.0
// Copyright (c) 2026 AshClaw.

package cc.ashclaw.core.date;

import java.time.Duration;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cc.ashclaw.common4j.core.date.TimeUtil;
import org.junit.jupiter.api.Test;

/**
 * Test class for TimeUtils result verification using JUnit 5.
 * <p>
 * TimeUtils结果验证测试类，使用JUnit 5。
 *
 * @author b1itz7
 * @since 1.0.0
 */
public class TimeUtilTest {

    /**
     * Test the basic time methods of TimeUtils.
     * <p>
     * 测试TimeUtils的基本时间方法。
     */
    @Test
    void testBasicTimeMethods() {
        // Test now
        LocalTime result = TimeUtil.now();
        assertNotNull(result, "now should return current time");
        
        // Test startOfDay
        LocalTime startOfDay = TimeUtil.startOfDay();
        assertEquals(LocalTime.MIN, startOfDay, "startOfDay should return LocalTime.MIN");
        
        // Test endOfDay
        LocalTime endOfDay = TimeUtil.endOfDay();
        assertEquals(LocalTime.MAX, endOfDay, "endOfDay should return LocalTime.MAX");
    }

    /**
     * Test the time formatting methods of TimeUtils.
     * <p>
     * 测试TimeUtils的时间格式化方法。
     */
    @Test
    void testTimeFormattingMethods() {
        LocalTime time = LocalTime.of(10, 30, 45);
        
        // Test format with default pattern
        String result = TimeUtil.format(time);
        assertEquals("10:30:45", result, "format with default pattern should return HH:mm:ss format");
        
        // Test format with custom pattern
        result = TimeUtil.format(time, "HH:mm");
        assertEquals("10:30", result, "format with custom pattern should use specified pattern");
        
        // Test format with timestamp
        long timestamp = 1703476245000L; // 2023-12-25T10:30:45 in UTC
        result = TimeUtil.format(timestamp, "yyyy-MM-dd HH:mm:ss");
        assertTrue(result.contains("2023-12-25"), "format with timestamp should format correctly");
    }

    /**
     * Test the time parsing methods of TimeUtils.
     * <p>
     * 测试TimeUtils的时间解析方法。
     */
    @Test
    void testTimeParsingMethods() {
        // Test parse with default pattern
        LocalTime result = TimeUtil.parse("10:30:45");
        assertNotNull(result, "parse with default pattern should parse HH:mm:ss format");
        assertEquals(LocalTime.of(10, 30, 45), result, "parse with default pattern should parse HH:mm:ss format");
        
        // Test parse with custom pattern
        result = TimeUtil.parse("10:30", "HH:mm");
        assertNotNull(result, "parse with custom pattern should parse specified pattern");
        assertEquals(LocalTime.of(10, 30), result, "parse with custom pattern should parse specified pattern");
        
        // Test parse with null string
        result = TimeUtil.parse(null, "HH:mm:ss");
        assertNull(result, "parse should return null for null string");
    }

    /**
     * Test the time manipulation methods of TimeUtils.
     * <p>
     * 测试TimeUtils的时间操作方法。
     */
    @Test
    void testTimeManipulationMethods() {
        LocalTime time = LocalTime.of(10, 30, 45);
        
        // Test addHours
        LocalTime result = TimeUtil.addHours(time, 2);
        assertNotNull(result, "addHours should add specified hours");
        assertEquals(LocalTime.of(12, 30, 45), result, "addHours should add specified hours");
        
        // Test addMinutes
        result = TimeUtil.addMinutes(time, 30);
        assertNotNull(result, "addMinutes should add specified minutes");
        assertEquals(LocalTime.of(11, 0, 45), result, "addMinutes should add specified minutes");
        
        // Test addSeconds
        result = TimeUtil.addSeconds(time, 15);
        assertNotNull(result, "addSeconds should add specified seconds");
        assertEquals(LocalTime.of(10, 31, 0), result, "addSeconds should add specified seconds");
    }

    /**
     * Test the time comparison methods of TimeUtils.
     * <p>
     * 测试TimeUtils的时间比较方法。
     */
    @Test
    void testTimeComparisonMethods() {
        LocalTime time1 = LocalTime.of(10, 30, 45);
        LocalTime time2 = LocalTime.of(12, 0, 0);
        LocalTime time3 = LocalTime.of(14, 0, 0);
        LocalTime time4 = LocalTime.of(22, 0, 0);
        LocalTime time5 = LocalTime.of(2, 0, 0);
        
        // Test isBetween
        boolean result = TimeUtil.isBetween(time1, LocalTime.of(9, 0, 0), LocalTime.of(11, 0, 0));
        assertTrue(result, "isBetween should return true for time in range");
        
        result = TimeUtil.isBetween(time1, LocalTime.of(11, 0, 0), LocalTime.of(13, 0, 0));
        assertFalse(result, "isBetween should return false for time out of range");
        
        // Test isInPeriod (non-cross-day)
        result = TimeUtil.isInPeriod(time1, LocalTime.of(9, 0, 0), LocalTime.of(11, 0, 0));
        assertTrue(result, "isInPeriod should return true for time in non-cross-day range");
        
        // Test isInPeriod (cross-day)
        result = TimeUtil.isInPeriod(time4, LocalTime.of(21, 0, 0), LocalTime.of(3, 0, 0));
        assertTrue(result, "isInPeriod should return true for time in cross-day range");
        
        result = TimeUtil.isInPeriod(time5, LocalTime.of(21, 0, 0), LocalTime.of(3, 0, 0));
        assertTrue(result, "isInPeriod should return true for time in cross-day range");
    }

    /**
     * Test the duration methods of TimeUtils.
     * <p>
     * 测试TimeUtils的间隔方法。
     */
    @Test
    void testDurationMethods() {
        // Test getDurationDescription
        Duration duration = Duration.ofHours(2).plusMinutes(30).plusSeconds(45);
        String result = TimeUtil.getDurationDescription(duration);
        assertEquals("2小时30分钟45秒", result, "getDurationDescription should format duration correctly");
        
        duration = Duration.ofMinutes(30).plusSeconds(45);
        result = TimeUtil.getDurationDescription(duration);
        assertEquals("30分钟45秒", result, "getDurationDescription should format short duration correctly");
        
        // Test millisToReadable
        long millis = 86400000; // 1天
        result = TimeUtil.millisToReadable(millis);
        assertEquals("1天", result, "millisToReadable should format 1 day correctly");
        
        millis = 3661000; // 1小时1分钟1秒
        result = TimeUtil.millisToReadable(millis);
        assertEquals("1小时1分钟1秒", result, "millisToReadable should format 1h1m1s correctly");
    }

    /**
     * Test the utility methods of TimeUtils.
     * <p>
     * 测试TimeUtils的工具方法。
     */
    @Test
    void testUtilityMethods() {
        // Test timed
        long time = TimeUtil.timed(() -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        assertTrue(time >= 90, "timed should measure approximately 100ms");
        
        // Test isAM/isPM
        LocalTime morning = LocalTime.of(10, 0);
        LocalTime afternoon = LocalTime.of(14, 0);
        
        boolean isAM = TimeUtil.isAM(morning);
        assertTrue(isAM, "isAM should return true for 10:00");
        
        boolean isPM = TimeUtil.isPM(afternoon);
        assertTrue(isPM, "isPM should return true for 14:00");
        
        // Test roundToNearestMinutes
        LocalTime timeToRound = LocalTime.of(10, 13);
        LocalTime rounded = TimeUtil.roundToNearestMinutes(timeToRound, 15);
        assertEquals(LocalTime.of(10, 15), rounded, "roundToNearestMinutes should round 10:13 to 10:15");
        
        timeToRound = LocalTime.of(10, 8);
        rounded = TimeUtil.roundToNearestMinutes(timeToRound, 15);
        assertEquals(LocalTime.of(10, 15), rounded, "roundToNearestMinutes should round 10:08 to 10:15");
    }
}