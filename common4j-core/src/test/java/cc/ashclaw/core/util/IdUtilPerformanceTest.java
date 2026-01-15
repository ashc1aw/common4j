// SPDX-License-Identifier: Apache-2.0
// Copyright (c) 2026 AshClaw.

package cc.ashclaw.core.util;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import cc.ashclaw.common4j.core.util.IdUtil;
import org.junit.jupiter.api.Test;

/**
 * Performance test class for IdUtil using JUnit 5.
 * <p>
 * IdUtil性能测试类，使用JUnit 5。
 *
 * @author b1itz7
 * @since 1.0.0
 */
public class IdUtilPerformanceTest {

    /**
     * Test single thread performance.
     * <p>
     * 测试单线程性能。
     */
    @Test
    void testSingleThreadPerformance() {
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
    @Test
    void testMultiThreadPerformance() throws InterruptedException {
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
        boolean completed = latch.await(60, TimeUnit.SECONDS);
        executorService.shutdown();
        
        if (!completed) {
            System.out.println("Multi-thread performance test timed out after 60 seconds");
            return;
        }
        
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        double idsPerSecond = (double) totalIds / duration * 1000;
        
        System.out.println("Multi-thread performance (" + threadCount + " threads):");
        System.out.println("Generated " + totalIds + " IDs in " + duration + " ms");
        System.out.println("Performance: " + String.format("%.2f", idsPerSecond) + " IDs/second");
    }
}