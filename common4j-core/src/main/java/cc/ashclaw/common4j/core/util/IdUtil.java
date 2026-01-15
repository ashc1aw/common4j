// SPDX-License-Identifier: Apache-2.0
// Copyright (c) 2026 AshClaw.

package cc.ashclaw.common4j.core.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import cc.ashclaw.common4j.core.date.TimeUtil;

/**
 * Utility class for generating distributed unique IDs using Snowflake algorithm.
 * <p>
 * 使用雪花算法生成分布式唯一ID的工具类。
 * <p>
 * The Snowflake algorithm generates 64-bit IDs consisting of:
 * <ul>
 *     <li>1 bit: Sign bit (always 0 for positive values)</li>
 *     <li>41 bits: Timestamp (milliseconds since epoch)</li>
 *     <li>10 bits: Machine ID (configurable, defaults to 0)</li>
 *     <li>12 bits: Sequence number (resets every millisecond)</li>
 * </ul>
 * <p>
 * 雪花算法生成64位ID，由以下部分组成：
 * <ul>
 *     <li>1位：符号位（始终为0表示正数）</li>
 *     <li>41位：时间戳（自纪元以来的毫秒数）</li>
 *     <li>10位：机器ID（可配置，默认为0）</li>
 *     <li>12位：序列号（每毫秒重置）</li>
 * </ul>
 *
 * @author b1itz7
 * @since 1.0.0
 */
public final class IdUtil {

    /**
     * Start timestamp (2026-01-01 00:00:00 UTC)
     * <p>
     * 起始时间戳（2026-01-01 00:00:00 UTC）
     */
    private static final long START_TIMESTAMP = 1778371200000L;

    /**
     * Mask for ensuring positive ID
     * <p>
     * 用于确保ID为正数的掩码
     */
    private static final long POSITIVE_MASK = 0x7FFFFFFFFFFFFFFFL;

    /**
     * Number of bits for machine ID
     * <p>
     * 机器ID的位数
     */
    private static final long MACHINE_ID_BITS = 10L;

    /**
     * Number of bits for sequence
     * <p>
     * 序列号的位数
     */
    private static final long SEQUENCE_BITS = 12L;

    /**
     * Maximum value for machine ID
     * <p>
     * 机器ID的最大值
     */
    private static final long MAX_MACHINE_ID = ~(-1L << MACHINE_ID_BITS);

    /**
     * Maximum value for sequence
     * <p>
     * 序列号的最大值
     */
    private static final long MAX_SEQUENCE = ~(-1L << SEQUENCE_BITS);

    /**
     * Shift left for machine ID
     * <p>
     * 机器ID的左移位数
     */
    private static final long MACHINE_ID_SHIFT = SEQUENCE_BITS;

    /**
     * Shift left for timestamp
     * <p>
     * 时间戳的左移位数
     */
    private static final long TIMESTAMP_SHIFT = SEQUENCE_BITS + MACHINE_ID_BITS;

    /**
     * Machine ID
     * <p>
     * 机器ID
     */
    private final long machineId; // 机器ID（仅用于构造时校验，不直接参与运算）

    /**
     * Machine ID shifted left by sequence bits
     * <p>
     * 机器ID左移序列位数后的结果
     */
    private final long machineIdShifted;

    /**
     * Sequence number
     * <p>
     * 序列号
     */
    private final AtomicLong sequence = new AtomicLong(0L);

    /**
     * Last timestamp
     * <p>
     * 上次生成ID的时间戳
     */
    private final AtomicLong lastTimestamp = new AtomicLong(-1L);

    /**
     * Instances map for different machine IDs
     * <p>
     * 不同机器ID对应的实例映射
     */
    private static final Map<Long, IdUtil> INSTANCES = new ConcurrentHashMap<>();

    /**
     * Private constructor to prevent instantiation.
     * <p>
     * 私有构造方法，防止实例化。
     *
     * @param machineId the machine ID (0-1023)
     *                  <p>
     *                  机器ID（0-1023）
     * @throws IllegalArgumentException if machineId is out of range
     *                                  <p>
     *                                  如果机器ID超出范围
     */
    private IdUtil(long machineId) {
        if (machineId < 0 || machineId > MAX_MACHINE_ID) {
            throw new IllegalArgumentException("Machine ID must be between 0 and " + MAX_MACHINE_ID + ", but got " + machineId);
        }
        this.machineId = machineId;
        // Precompute machine ID shifted left by sequence bits
        this.machineIdShifted = machineId << MACHINE_ID_SHIFT;
    }

    /**
     * Gets the instance with default machine ID (0).
     * <p>
     * 获取使用默认机器ID（0）的实例。
     *
     * @return the instance
     *         <p>
     *         实例
     */
    public static IdUtil getInstance() {
        return getInstance(0L);
    }

    /**
     * Gets the instance with specified machine ID.
     * <p>
     * 获取使用指定机器ID的实例。
     *
     * @param machineId the machine ID (0-1023)
     *                  <p>
     *                  机器ID（0-1023）
     * @return the instance
     *         <p>
     *         实例
     */
    public static IdUtil getInstance(long machineId) {
        return INSTANCES.computeIfAbsent(machineId, IdUtil::new);
    }

    /**
     * Generates the next unique ID.
     * <p>
     * 生成下一个唯一ID。
     *
     * @return the generated unique ID
     *         <p>
     *         生成的唯一ID
     * @throws RuntimeException if the clock moves backwards or if an error occurs during ID generation
     *                          <p>
     *                          如果时钟回拨或ID生成过程中发生错误
     */
    public long nextId() {
        long timestamp, lastTime, seq;
        do {
            timestamp = System.currentTimeMillis();
            lastTime = lastTimestamp.get();
            
            // If current timestamp is less than last timestamp, throw exception
            if (timestamp < lastTime) {
                throw new RuntimeException("Clock moved backwards. Refusing to generate ID for " + (lastTime - timestamp) + " milliseconds");
            }
            
            // If current timestamp is equal to last timestamp, increment sequence
            if (timestamp == lastTime) {
                seq = sequence.incrementAndGet();
                
                // If sequence overflows in the same millisecond, wait until next millisecond
                if ((seq & MAX_SEQUENCE) == 0) {
                    timestamp = tilNextMillis(lastTime);
                    // Reset sequence and try again
                    sequence.set(0);
                    continue;
                }
                // Sequence incremented, use current timestamp and sequence
                break;
            } else {
                // For new millisecond, try to reset sequence to 0
                if (sequence.compareAndSet(sequence.get(), 0)) {
                    // Successfully reset sequence, use 0 as sequence number
                    seq = 0;
                    // Try to update last timestamp
                    if (lastTimestamp.compareAndSet(lastTime, timestamp)) {
                        // Successfully updated timestamp, break loop
                        break;
                    }
                }
                // Failed to reset sequence or update timestamp, try again
            }
        } while (true);

        // Generate ID and ensure it's positive
        return (((timestamp - START_TIMESTAMP) << TIMESTAMP_SHIFT)
                | machineIdShifted
                | (seq & MAX_SEQUENCE)) & POSITIVE_MASK;
    }

    /**
     * Waits until next millisecond.
     * <p>
     * 等待直到下一个毫秒。
     *
     * @param lastTimestamp the last timestamp
     *                      <p>
     *                      上次的时间戳
     * @return the next millisecond timestamp
     *         <p>
     *         下一个毫秒的时间戳
     */
    private long tilNextMillis(long lastTimestamp) {
        long timestamp = System.currentTimeMillis();
        if (timestamp <= lastTimestamp) {
            // If only 1 millisecond behind, use busy wait for better precision
            if (lastTimestamp - timestamp < 2) {
                // Busy wait for up to 2 milliseconds
                long deadline = System.nanoTime() + 2_000_000; // 2 milliseconds
                while (System.currentTimeMillis() <= lastTimestamp && System.nanoTime() < deadline) {
                    // Yield to other threads
                    Thread.yield();
                }
            } else {
                // For larger differences, use sleep to reduce CPU consumption
                try {
                    // Sleep for the required time plus a small buffer
                    Thread.sleep(lastTimestamp - timestamp + 1);
                } catch (InterruptedException e) {
                    // Preserve interruption status
                    Thread.currentThread().interrupt();
                }
            }
            timestamp = System.currentTimeMillis();
        }
        return timestamp;
    }

    /**
     * Generates the next unique ID using the default instance.
     * <p>
     * 使用默认实例生成下一个唯一ID。
     *
     * @return the generated unique ID
     *         <p>
     *         生成的唯一ID
     */
    public static long generateId() {
        return getInstance().nextId();
    }

    /**
     * Generates the next unique ID using the specified machine ID.
     * <p>
     * 使用指定的机器ID生成下一个唯一ID。
     *
     * @param machineId the machine ID
     *                  <p>
     *                  机器ID
     * @return the generated unique ID
     *         <p>
     *         生成的唯一ID
     */
    public static long generateId(long machineId) {
        return getInstance(machineId).nextId();
    }

    /**
     * Parses a Snowflake ID into its components.
     * <p>
     * 将雪花ID解析为其组成部分。
     *
     * @param id the Snowflake ID to parse
     *           <p>
     *           要解析的雪花ID
     */
    public static void parseId(long id) {
        long timestamp = ((id >> 22) & 0x1FFFFFFFFFFL) + START_TIMESTAMP;
        long machineId = (id >> 12) & 0x3FF;
        long sequence = id & 0xFFF;
        
        System.out.println("ID: " + id);
        System.out.println("Timestamp: " + timestamp + " (" + TimeUtil.format(timestamp) + ")");
        System.out.println("Machine ID: " + machineId);
        System.out.println("Sequence: " + sequence);
    }
}