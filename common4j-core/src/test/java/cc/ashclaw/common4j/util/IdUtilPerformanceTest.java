// SPDX-License-Identifier: Apache-2.0
// Copyright (c) 2026 AshClaw.

package cc.ashclaw.common4j.util;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Performance test class for IdUtil.
 * <p>
 * IdUtil性能测试类。
 *
 * @author b1itz7
 * @since 1.0.0
 */
public class IdUtilPerformanceTest {

    public static void main(String[] args) {
        System.out.println("===== IdUtil Performance Test Start =====");
        
        // Single thread performance
        testSingleThreadPerformance();
        
        // Multi-thread performance
        testMultiThreadPerformance();
        
        System.out.println("===== IdUtil Performance Test End =====");
    }

    /**
     * Test single thread performance.
     * <p>
     * 测试单线程性能。
     */
    private static void testSingleThreadPerformance() {
        System.out.println("\n1. Testing single thread performance...");
        
        int idCount = 100000;
        IdUtil idUtil = IdUtil.getInstance();
        
        long startTime = System.currentTimeMillis();
        
        for (int i = 0; i < idCount; i++) {
            idUtil.nextId();
        }
        
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        double idsPerSecond = (double) idCount / duration * 1000;
        
        System.out.println("Single thread performance:");
        System.out.println("Generated " + idCount + " IDs in " + duration + " ms");
        System.out.println("Performance: " + String.format("%.2f", idsPerSecond) + " IDs/second");
    }

    /**
     * Test multi-thread performance.
     * <p>
     * 测试多线程性能。
     */
    private static void testMultiThreadPerformance() {
        System.out.println("\n2. Testing multi-thread performance...");
        
        int threadCount = 10;
        int idsPerThread = 10000;
        int totalIds = threadCount * idsPerThread;
        CountDownLatch latch = new CountDownLatch(threadCount);
        
        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
        
        long startTime = System.currentTimeMillis();
        
        for (int i = 0; i < threadCount; i++) {
            final long machineId = i % 1024; // Ensure machine ID is within range
            executorService.submit(() -> {
                try {
                    IdUtil idUtil = IdUtil.getInstance(machineId);
                    for (int j = 0; j < idsPerThread; j++) {
                        idUtil.nextId();
                    }
                } finally {
                    latch.countDown();
                }
            });
        }
        
        // Wait for all threads to finish
        try {
            latch.await(60, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Multi-thread performance test interrupted: " + e.getMessage());
            return;
        } finally {
            executorService.shutdown();
        }
        
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        double idsPerSecond = (double) totalIds / duration * 1000;
        
        System.out.println("Multi-thread performance (" + threadCount + " threads):");
        System.out.println("Generated " + totalIds + " IDs in " + duration + " ms");
        System.out.println("Performance: " + String.format("%.2f", idsPerSecond) + " IDs/second");
    }
}