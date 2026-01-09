// SPDX-License-Identifier: Apache-2.0
// Copyright (c) 2026 AshClaw.

package cc.ashclaw.common4j.constant;

import java.time.ZoneId;

/**
 * Provides timezone-related constants and identifiers.
 * <p>
 * 时区相关常量
 * <p>
 * Includes commonly used timezone identifiers such as UTC, GMT, and regional timezones.
 * <p>
 * 包含常用的时区标识，如UTC、GMT和各地区时区。
 *
 * @author b1itz7
 * @since 1.0.0
 */
public final class TimeZones {
    /**
     * Prevent instantiation.
     * <p>
     * 防止实例化
     */
    private TimeZones() {
        throw new UnsupportedOperationException("TimeZones cannot be instantiated");
    }

    /**
     * UTC timezone identifier.
     * <p>
     * UTC 时区标识
     */
    public static final String UTC = "UTC";

    /**
     * GMT timezone identifier.
     * <p>
     * GMT 时区标识
     */
    public static final String GMT = "GMT";

    /**
     * China Standard Time (Asia/Shanghai).
     * <p>
     * 中国标准时间
     */
    public static final String CHINA_STANDARD_TIME = "Asia/Shanghai";

    /**
     * US Eastern Time (America/New_York).
     * <p>
     * 美国东部时间
     */
    public static final String US_EASTERN_TIME = "America/New_York";

    /**
     * US Pacific Time (America/Los_Angeles).
     * <p>
     * 美国太平洋时间
     */
    public static final String US_PACIFIC_TIME = "America/Los_Angeles";

    /**
     * Central European Time (Europe/Paris).
     * <p>
     * 欧洲中部时间
     */
    public static final String EUROPE_CENTRAL_TIME = "Europe/Paris";

    /**
     * Tokyo Time (Asia/Tokyo).
     * <p>
     * 东京时间
     */
    public static final String TOKYO_TIME = "Asia/Tokyo";

    /**
     * System default timezone.
     * <p>
     * 系统默认时区
     */
    public static final ZoneId SYSTEM_ZONE = ZoneId.systemDefault();


}
