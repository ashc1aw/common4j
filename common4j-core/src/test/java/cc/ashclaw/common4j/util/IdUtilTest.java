// SPDX-License-Identifier: Apache-2.0
// Copyright (c) 2026 AshClaw.

package cc.ashclaw.common4j.util;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

/**
 * Test class for IdUtil result verification using JUnit 5.
 * <p>
 * IdUtil结果验证测试类，使用JUnit 5。
 *
 * @author b1itz7
 * @since 1.0.0
 */
public class IdUtilTest {

    /**
     * Test basic functionality of IdUtil.
     * <p>
     * 测试IdUtil的基本功能。
     */
    @Test
    void testBasicFunctionality() {
        IdUtil idUtil = IdUtil.getInstance();
        
        // Generate 10 IDs
        for (int i = 0; i < 10; i++) {
            long id = idUtil.nextId();
            assertTrue(id > 0, "Generated ID should be positive: " + id);
        }
        
        // Test generateId method
        long id = IdUtil.generateId();
        assertTrue(id > 0, "Generated ID via generateId() should be positive: " + id);
    }

    /**
     * Test different machine IDs.
     * <p>
     * 测试不同的机器ID。
     */
    @Test
    void testDifferentMachineIds() {
        // Get instances with different machine IDs
        IdUtil idUtil1 = IdUtil.getInstance(1);
        IdUtil idUtil2 = IdUtil.getInstance(2);
        IdUtil idUtil3 = IdUtil.getInstance(3);
        
        // Generate IDs from different instances
        long id1 = idUtil1.nextId();
        long id2 = idUtil2.nextId();
        long id3 = idUtil3.nextId();
        
        // Verify all IDs are positive
        assertTrue(id1 > 0, "Generated ID with machine ID 1 should be positive: " + id1);
        assertTrue(id2 > 0, "Generated ID with machine ID 2 should be positive: " + id2);
        assertTrue(id3 > 0, "Generated ID with machine ID 3 should be positive: " + id3);
        
        // Verify they are different
        assertNotEquals(id1, id2, "IDs from different machine IDs should be different");
        assertNotEquals(id1, id3, "IDs from different machine IDs should be different");
        assertNotEquals(id2, id3, "IDs from different machine IDs should be different");
    }

    /**
     * Test thread safety.
     * <p>
     * 测试线程安全性。
     */
    @Test
    void testThreadSafety() throws InterruptedException {
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
                        assertTrue(id > 0, "Generated ID in thread should be positive: " + id);
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
        boolean completed = latch.await(30, TimeUnit.SECONDS);
        executorService.shutdown();
        
        assertTrue(completed, "Thread safety test should complete within 30 seconds");
        
        // Verify all IDs are unique
        int expectedSize = threadCount * idsPerThread;
        int actualSize = generatedIds.size();
        
        assertEquals(expectedSize, actualSize, "All generated IDs should be unique. Expected: " + expectedSize + ", Actual: " + actualSize);
    }
}