// SPDX-License-Identifier: Apache-2.0
// Copyright (c) 2026 AshClaw.

package cc.ashclaw.common4j.util;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Test class for IdUtil result verification.
 * <p>
 * IdUtil结果验证测试类。
 *
 * @author b1itz7
 * @since 1.0.0
 */
public class IdUtilTest {

    public static void main(String[] args) {
        System.out.println("===== IdUtil Result Verification Test Start =====");
        
        // Test basic functionality
        testBasicFunctionality();
        
        // Test different machine IDs
        testDifferentMachineIds();
        
        // Test thread safety
        testThreadSafety();
        
        System.out.println("===== IdUtil Result Verification Test End =====");
    }

    /**
     * Test basic functionality of IdUtil.
     * <p>
     * 测试IdUtil的基本功能。
     */
    private static void testBasicFunctionality() {
        System.out.println("\n1. Testing basic functionality...");
        
        IdUtil idUtil = IdUtil.getInstance();
        
        // Generate 10 IDs
        for (int i = 0; i < 10; i++) {
            long id = idUtil.nextId();
            // Verify ID is positive
            if (id <= 0) {
                System.out.println("ERROR: Generated negative ID: " + id);
                return;
            }
            System.out.println("Generated ID " + (i + 1) + ": " + id);
        }
        
        // Test generateId method
        long id = IdUtil.generateId();
        if (id <= 0) {
            System.out.println("ERROR: Generated negative ID via generateId(): " + id);
            return;
        }
        System.out.println("Generated ID via generateId(): " + id);
        
        System.out.println("Basic functionality test passed.");
    }

    /**
     * Test different machine IDs.
     * <p>
     * 测试不同的机器ID。
     */
    private static void testDifferentMachineIds() {
        System.out.println("\n2. Testing different machine IDs...");
        
        // Get instances with different machine IDs
        IdUtil idUtil1 = IdUtil.getInstance(1);
        IdUtil idUtil2 = IdUtil.getInstance(2);
        IdUtil idUtil3 = IdUtil.getInstance(3);
        
        // Generate IDs from different instances
        long id1 = idUtil1.nextId();
        long id2 = idUtil2.nextId();
        long id3 = idUtil3.nextId();
        
        // Verify all IDs are positive
        if (id1 <= 0 || id2 <= 0 || id3 <= 0) {
            System.out.println("ERROR: Generated negative ID with machine ID:");
            System.out.println("Machine ID 1: " + id1);
            System.out.println("Machine ID 2: " + id2);
            System.out.println("Machine ID 3: " + id3);
            return;
        }
        
        System.out.println("Machine ID 1 generated: " + id1);
        System.out.println("Machine ID 2 generated: " + id2);
        System.out.println("Machine ID 3 generated: " + id3);
        
        // Verify they are different
        if (id1 != id2 && id1 != id3 && id2 != id3) {
            System.out.println("Different machine IDs test passed.");
        } else {
            System.out.println("Different machine IDs test failed!");
        }
    }

    /**
     * Test thread safety.
     * <p>
     * 测试线程安全性。
     */
    private static void testThreadSafety() {
        System.out.println("\n3. Testing thread safety...");
        
        int threadCount = 10;
        int idsPerThread = 1000;
        Set<Long> generatedIds = new HashSet<>();
        CountDownLatch latch = new CountDownLatch(threadCount);
        
        // Create thread pool
        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
        
        // Submit tasks
        for (int i = 0; i < threadCount; i++) {
            executorService.submit(() -> {
                try {
                    IdUtil idUtil = IdUtil.getInstance(42);
                    for (int j = 0; j < idsPerThread; j++) {
                        long id = idUtil.nextId();
                        // Verify ID is positive
                        if (id <= 0) {
                            System.err.println("ERROR: Generated negative ID in thread: " + id);
                            return;
                        }
                        synchronized (generatedIds) {
                            generatedIds.add(id);
                        }
                    }
                } finally {
                    latch.countDown();
                }
            });
        }
        
        // Wait for all threads to finish
        try {
            latch.await(30, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Thread safety test interrupted: " + e.getMessage());
            return;
        } finally {
            executorService.shutdown();
        }
        
        // Verify all IDs are unique
        int expectedSize = threadCount * idsPerThread;
        int actualSize = generatedIds.size();
        
        System.out.println("Expected unique IDs: " + expectedSize);
        System.out.println("Actual unique IDs: " + actualSize);
        
        if (actualSize == expectedSize) {
            System.out.println("Thread safety test passed.");
        } else {
            System.out.println("Thread safety test failed! Duplicate IDs found: " + (expectedSize - actualSize));
        }
    }


}