// SPDX-License-Identifier: Apache-2.0
// Copyright (c) 2026 AshClaw.

package cc.ashclaw.common4j.date;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for DateTimeFormatters result verification using JUnit 5.
 * <p>
 * DateTimeFormatters结果验证测试类，使用JUnit 5。
 *
 * @author b1itz7
 * @since 1.0.0
 */
public class DateTimeFormattersTest {

    /**
     * Test the formatter instances of DateTimeFormatters.
     * <p>
     * 测试DateTimeFormatters的格式化器实例。
     */
    @Test
    void testFormatterInstances() {
        // Test FMT_DATETIME
        assertNotNull(DateTimeFormatters.FMT_DATETIME, "FMT_DATETIME should not be null");
        
        // Test FMT_DATE
        assertNotNull(DateTimeFormatters.FMT_DATE, "FMT_DATE should not be null");
        
        // Test FMT_TIME
        assertNotNull(DateTimeFormatters.FMT_TIME, "FMT_TIME should not be null");
        
        // Test FMT_COMPACT
        assertNotNull(DateTimeFormatters.FMT_COMPACT, "FMT_COMPACT should not be null");
    }

    /**
     * Test the functionality of DateTimeFormatters.
     * <p>
     * 测试DateTimeFormatters的功能。
     */
    @Test
    void testFormatterFunctionality() {
        LocalDateTime dateTime = LocalDateTime.of(2023, 12, 25, 10, 30, 45);
        LocalDate date = dateTime.toLocalDate();
        LocalTime time = dateTime.toLocalTime();
        
        // Test FMT_DATETIME
        String formatted = DateTimeFormatters.FMT_DATETIME.format(dateTime);
        assertEquals("2023-12-25 10:30:45", formatted, "FMT_DATETIME should format as yyyy-MM-dd HH:mm:ss");
        
        // Test FMT_DATE
        formatted = DateTimeFormatters.FMT_DATE.format(date);
        assertEquals("2023-12-25", formatted, "FMT_DATE should format as yyyy-MM-dd");
        
        // Test FMT_TIME
        formatted = DateTimeFormatters.FMT_TIME.format(time);
        assertEquals("10:30:45", formatted, "FMT_TIME should format as HH:mm:ss");
        
        // Test FMT_COMPACT
        formatted = DateTimeFormatters.FMT_COMPACT.format(date);
        assertEquals("20231225", formatted, "FMT_COMPACT should format as yyyyMMdd");
    }
}