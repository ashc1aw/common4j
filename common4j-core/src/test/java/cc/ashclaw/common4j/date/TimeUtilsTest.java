// SPDX-License-Identifier: Apache-2.0
// Copyright (c) 2026 AshClaw.

package cc.ashclaw.common4j.date;

import java.time.Duration;
import java.time.LocalTime;

/**
 * Test class for TimeUtils result verification.
 * <p>
 * TimeUtils结果验证测试类。
 *
 * @author b1itz7
 * @since 1.0.0
 */
public class TimeUtilsTest {

    public static void main(String[] args) {
        System.out.println("===== TimeUtils Result Verification Test Start =====");
        
        // Test basic time methods
        testBasicTimeMethods();
        
        // Test time formatting methods
        testTimeFormattingMethods();
        
        // Test time parsing methods
        testTimeParsingMethods();
        
        // Test time manipulation methods
        testTimeManipulationMethods();
        
        // Test time comparison methods
        testTimeComparisonMethods();
        
        // Test duration methods
        testDurationMethods();
        
        // Test utility methods
        testUtilityMethods();
        
        System.out.println("===== TimeUtils Result Verification Test End =====");
    }

    /**
     * Test the basic time methods of TimeUtils.
     * <p>
     * 测试TimeUtils的基本时间方法。
     */
    private static void testBasicTimeMethods() {
        System.out.println("\n1. Testing basic time methods...");
        
        // Test now
        LocalTime result = TimeUtils.now();
        System.out.println("now() = " + result);
        if (result == null) {
            System.out.println("ERROR: now should return current time!");
            return;
        }
        
        // Test startOfDay
        LocalTime startOfDay = TimeUtils.startOfDay();
        System.out.println("startOfDay() = " + startOfDay);
        if (!LocalTime.MIN.equals(startOfDay)) {
            System.out.println("ERROR: startOfDay should return LocalTime.MIN!");
            return;
        }
        
        // Test endOfDay
        LocalTime endOfDay = TimeUtils.endOfDay();
        System.out.println("endOfDay() = " + endOfDay);
        if (!LocalTime.MAX.equals(endOfDay)) {
            System.out.println("ERROR: endOfDay should return LocalTime.MAX!");
            return;
        }
        
        System.out.println("basic time methods test passed.");
    }

    /**
     * Test the time formatting methods of TimeUtils.
     * <p>
     * 测试TimeUtils的时间格式化方法。
     */
    private static void testTimeFormattingMethods() {
        System.out.println("\n2. Testing time formatting methods...");
        
        LocalTime time = LocalTime.of(10, 30, 45);
        
        // Test format with default pattern
        String result = TimeUtils.format(time);
        System.out.println("format(10:30:45) = \"" + result + "\"");
        if (!"10:30:45".equals(result)) {
            System.out.println("ERROR: format with default pattern should return HH:mm:ss format!");
            return;
        }
        
        // Test format with custom pattern
        result = TimeUtils.format(time, "HH:mm");
        System.out.println("format(10:30:45, \"HH:mm\") = \"" + result + "\"");
        if (!"10:30".equals(result)) {
            System.out.println("ERROR: format with custom pattern should use specified pattern!");
            return;
        }
        
        // Test format with timestamp
        long timestamp = 1703476245000L; // 2023-12-25T10:30:45 in UTC
        result = TimeUtils.format(timestamp, "yyyy-MM-dd HH:mm:ss");
        System.out.println("format(1703476245000L, \"yyyy-MM-dd HH:mm:ss\") = \"" + result + "\"");
        if (!result.contains("2023-12-25")) {
            System.out.println("ERROR: format with timestamp should format correctly!");
            return;
        }
        
        System.out.println("time formatting methods test passed.");
    }

    /**
     * Test the time parsing methods of TimeUtils.
     * <p>
     * 测试TimeUtils的时间解析方法。
     */
    private static void testTimeParsingMethods() {
        System.out.println("\n3. Testing time parsing methods...");
        
        // Test parse with default pattern
        LocalTime result = TimeUtils.parse("10:30:45");
        System.out.println("parse(\"10:30:45\") = " + result);
        if (result == null || !result.equals(LocalTime.of(10, 30, 45))) {
            System.out.println("ERROR: parse with default pattern should parse HH:mm:ss format!");
            return;
        }
        
        // Test parse with custom pattern
        result = TimeUtils.parse("10:30", "HH:mm");
        System.out.println("parse(\"10:30\", \"HH:mm\") = " + result);
        if (result == null || !result.equals(LocalTime.of(10, 30))) {
            System.out.println("ERROR: parse with custom pattern should parse specified pattern!");
            return;
        }
        
        // Test parse with null string
        result = TimeUtils.parse(null, "HH:mm:ss");
        System.out.println("parse(null, \"HH:mm:ss\") = " + result);
        if (result != null) {
            System.out.println("ERROR: parse should return null for null string!");
            return;
        }
        
        System.out.println("time parsing methods test passed.");
    }

    /**
     * Test the time manipulation methods of TimeUtils.
     * <p>
     * 测试TimeUtils的时间操作方法。
     */
    private static void testTimeManipulationMethods() {
        System.out.println("\n4. Testing time manipulation methods...");
        
        LocalTime time = LocalTime.of(10, 30, 45);
        
        // Test addHours
        LocalTime result = TimeUtils.addHours(time, 2);
        System.out.println("addHours(10:30:45, 2) = " + result);
        if (result == null || !result.equals(LocalTime.of(12, 30, 45))) {
            System.out.println("ERROR: addHours should add specified hours!");
            return;
        }
        
        // Test addMinutes
        result = TimeUtils.addMinutes(time, 30);
        System.out.println("addMinutes(10:30:45, 30) = " + result);
        if (result == null || !result.equals(LocalTime.of(11, 0, 45))) {
            System.out.println("ERROR: addMinutes should add specified minutes!");
            return;
        }
        
        // Test addSeconds
        result = TimeUtils.addSeconds(time, 15);
        System.out.println("addSeconds(10:30:45, 15) = " + result);
        if (result == null || !result.equals(LocalTime.of(10, 31, 0))) {
            System.out.println("ERROR: addSeconds should add specified seconds!");
            return;
        }
        
        System.out.println("time manipulation methods test passed.");
    }

    /**
     * Test the time comparison methods of TimeUtils.
     * <p>
     * 测试TimeUtils的时间比较方法。
     */
    private static void testTimeComparisonMethods() {
        System.out.println("\n5. Testing time comparison methods...");
        
        LocalTime time1 = LocalTime.of(10, 30, 45);
        LocalTime time2 = LocalTime.of(12, 0, 0);
        LocalTime time3 = LocalTime.of(14, 0, 0);
        LocalTime time4 = LocalTime.of(22, 0, 0);
        LocalTime time5 = LocalTime.of(2, 0, 0);
        
        // Test isBetween
        boolean result = TimeUtils.isBetween(time1, LocalTime.of(9, 0, 0), LocalTime.of(11, 0, 0));
        System.out.println("isBetween(10:30:45, 09:00:00, 11:00:00) = " + result);
        if (!result) {
            System.out.println("ERROR: isBetween should return true for time in range!");
            return;
        }
        
        result = TimeUtils.isBetween(time1, LocalTime.of(11, 0, 0), LocalTime.of(13, 0, 0));
        System.out.println("isBetween(10:30:45, 11:00:00, 13:00:00) = " + result);
        if (result) {
            System.out.println("ERROR: isBetween should return false for time out of range!");
            return;
        }
        
        // Test isInPeriod (non-cross-day)
        result = TimeUtils.isInPeriod(time1, LocalTime.of(9, 0, 0), LocalTime.of(11, 0, 0));
        System.out.println("isInPeriod(10:30:45, 09:00:00, 11:00:00) = " + result);
        if (!result) {
            System.out.println("ERROR: isInPeriod should return true for time in non-cross-day range!");
            return;
        }
        
        // Test isInPeriod (cross-day)
        result = TimeUtils.isInPeriod(time4, LocalTime.of(21, 0, 0), LocalTime.of(3, 0, 0));
        System.out.println("isInPeriod(22:00:00, 21:00:00, 03:00:00) = " + result);
        if (!result) {
            System.out.println("ERROR: isInPeriod should return true for time in cross-day range!");
            return;
        }
        
        result = TimeUtils.isInPeriod(time5, LocalTime.of(21, 0, 0), LocalTime.of(3, 0, 0));
        System.out.println("isInPeriod(02:00:00, 21:00:00, 03:00:00) = " + result);
        if (!result) {
            System.out.println("ERROR: isInPeriod should return true for time in cross-day range!");
            return;
        }
        
        System.out.println("time comparison methods test passed.");
    }

    /**
     * Test the duration methods of TimeUtils.
     * <p>
     * 测试TimeUtils的间隔方法。
     */
    private static void testDurationMethods() {
        System.out.println("\n6. Testing duration methods...");
        
        // Test getDurationDescription
        Duration duration = Duration.ofHours(2).plusMinutes(30).plusSeconds(45);
        String result = TimeUtils.getDurationDescription(duration);
        System.out.println("getDurationDescription(2h30m45s) = \"" + result + "\"");
        if (!"2小时30分钟45秒".equals(result)) {
            System.out.println("ERROR: getDurationDescription should format duration correctly!");
            return;
        }
        
        duration = Duration.ofMinutes(30).plusSeconds(45);
        result = TimeUtils.getDurationDescription(duration);
        System.out.println("getDurationDescription(30m45s) = \"" + result + "\"");
        if (!"30分钟45秒".equals(result)) {
            System.out.println("ERROR: getDurationDescription should format short duration correctly!");
            return;
        }
        
        // Test millisToReadable
        long millis = 86400000; // 1天
        result = TimeUtils.millisToReadable(millis);
        System.out.println("millisToReadable(86400000) = \"" + result + "\"");
        if (!"1天".equals(result)) {
            System.out.println("ERROR: millisToReadable should format 1 day correctly!");
            return;
        }
        
        millis = 3661000; // 1小时1分钟1秒
        result = TimeUtils.millisToReadable(millis);
        System.out.println("millisToReadable(3661000) = \"" + result + "\"");
        if (!"1小时1分钟1秒".equals(result)) {
            System.out.println("ERROR: millisToReadable should format 1h1m1s correctly!");
            return;
        }
        
        System.out.println("duration methods test passed.");
    }

    /**
     * Test the utility methods of TimeUtils.
     * <p>
     * 测试TimeUtils的工具方法。
     */
    private static void testUtilityMethods() {
        System.out.println("\n7. Testing utility methods...");
        
        // Test timed
        long time = TimeUtils.timed(() -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println("timed(sleep 100ms) = " + time + "ms");
        if (time < 90) {
            System.out.println("ERROR: timed should measure approximately 100ms!");
            return;
        }
        
        // Test isAM/isPM
        LocalTime morning = LocalTime.of(10, 0);
        LocalTime afternoon = LocalTime.of(14, 0);
        
        boolean isAM = TimeUtils.isAM(morning);
        System.out.println("isAM(10:00) = " + isAM);
        if (!isAM) {
            System.out.println("ERROR: isAM should return true for 10:00!");
            return;
        }
        
        boolean isPM = TimeUtils.isPM(afternoon);
        System.out.println("isPM(14:00) = " + isPM);
        if (!isPM) {
            System.out.println("ERROR: isPM should return true for 14:00!");
            return;
        }
        
        // Test roundToNearestMinutes
        LocalTime timeToRound = LocalTime.of(10, 13);
        LocalTime rounded = TimeUtils.roundToNearestMinutes(timeToRound, 15);
        System.out.println("roundToNearestMinutes(10:13, 15) = " + rounded);
        if (!LocalTime.of(10, 15).equals(rounded)) {
            System.out.println("ERROR: roundToNearestMinutes should round 10:13 to 10:15!");
            return;
        }
        
        timeToRound = LocalTime.of(10, 8);
        rounded = TimeUtils.roundToNearestMinutes(timeToRound, 15);
        System.out.println("roundToNearestMinutes(10:08, 15) = " + rounded);
        if (!LocalTime.of(10, 15).equals(rounded)) {
            System.out.println("ERROR: roundToNearestMinutes should round 10:08 to 10:15!");
            return;
        }
        
        System.out.println("utility methods test passed.");
    }
}