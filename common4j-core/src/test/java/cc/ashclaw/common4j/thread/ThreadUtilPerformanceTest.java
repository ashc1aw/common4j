// SPDX-License-Identifier: Apache-2.0
// Copyright (c) 2026 AshClaw.

package cc.ashclaw.common4j.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import org.junit.jupiter.api.Test;

/**
 * Performance test class for ThreadUtil using JUnit 5.
 * <p>
 * ThreadUtil的性能测试类，使用JUnit 5。
 * <p>
 * This class provides performance tests for ThreadUtil methods, measuring
 * execution time and throughput for various operations.
 * <p>
 * 此类提供ThreadUtil方法的性能测试，测量各种操作的执行时间和吞吐量。
 *
 * @author b1itz7
 * @since 1.0.0
 */
public class ThreadUtilPerformanceTest {

    /**
     * Number of iterations for performance testing.
     * <p>
     * 性能测试的迭代次数。
     */
    private static final int TEST_ITERATIONS = 1000000;

    /**
     * Number of concurrent threads for thread safety testing.
     * <p>
     * 线程安全测试的并发线程数。
     */
    private static final int CONCURRENT_THREADS = 100;

    /**
     * Test the performance of sleep methods.
     * <p>
     * 测试休眠方法的性能。
     */
    @Test
    void testSleepPerformance() {
        System.out.println("\n1. Testing sleep methods performance...");
        
        // Test short sleep performance
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            ThreadUtil.sleep(1); // 1 millisecond
        }
        long endTime = System.currentTimeMillis();
        long elapsed = endTime - startTime;
        
        System.out.printf("Short sleep (1ms): %d iterations in %d ms (%.2f operations/second)\n", 
                1000, elapsed, (1000.0 * 1000) / elapsed);
        
        // Test TimeUnit sleep performance
        startTime = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            ThreadUtil.sleep(1, TimeUnit.MILLISECONDS);
        }
        endTime = System.currentTimeMillis();
        elapsed = endTime - startTime;
        
        System.out.printf("TimeUnit sleep (1ms): %d iterations in %d ms (%.2f operations/second)\n", 
                1000, elapsed, (1000.0 * 1000) / elapsed);
    }

    /**
     * Test the performance of thread creation methods.
     * <p>
     * 测试线程创建方法的性能。
     */
    @Test
    void testThreadCreationPerformance() {
        System.out.println("\n2. Testing thread creation performance...");
        
        // Test createThread performance
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            Thread thread = ThreadUtil.createThread(() -> {});
        }
        long endTime = System.currentTimeMillis();
        long elapsed = endTime - startTime;
        
        System.out.printf("createThread: %d threads created in %d ms (%.2f operations/second)\n", 
                1000, elapsed, (1000.0 * 1000) / elapsed);
        
        // Test thread creation with start
        startTime = System.currentTimeMillis();
        Thread[] threads = new Thread[100];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = ThreadUtil.startThread(() -> {
                ThreadUtil.sleep(10); // Small sleep to ensure thread starts
            });
        }
        
        // Wait for all threads to complete
        ThreadUtil.waitForAll(threads);
        
        endTime = System.currentTimeMillis();
        elapsed = endTime - startTime;
        
        System.out.printf("startThread: %d threads started and completed in %d ms (%.2f operations/second)\n", 
                threads.length, elapsed, (threads.length * 1000.0) / elapsed);
    }

    /**
     * Test the performance of ThreadLocal operations.
     * <p>
     * 测试ThreadLocal操作的性能。
     */
    @Test
    void testThreadLocalPerformance() {
        System.out.println("\n3. Testing ThreadLocal operations performance...");
        
        // Create thread local instances
        ThreadLocal<Integer> threadLocal1 = ThreadUtil.newThreadLocal();
        ThreadLocal<String> threadLocal2 = ThreadUtil.newThreadLocal(() -> "initial");
        
        // Test get and set performance
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            threadLocal1.set(i);
            threadLocal1.get();
        }
        long endTime = System.currentTimeMillis();
        long elapsed = endTime - startTime;
        
        System.out.printf("ThreadLocal get/set: %d operations in %d ms (%.2f operations/second)\n", 
                TEST_ITERATIONS * 2, elapsed, (TEST_ITERATIONS * 2.0 * 1000) / elapsed);
        
        // Test ThreadLocal with initial value
        startTime = System.currentTimeMillis();
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            threadLocal2.get();
            threadLocal2.set("value" + i);
        }
        endTime = System.currentTimeMillis();
        elapsed = endTime - startTime;
        
        System.out.printf("ThreadLocal with initial value: %d operations in %d ms (%.2f operations/second)\n", 
                TEST_ITERATIONS * 2, elapsed, (TEST_ITERATIONS * 2.0 * 1000) / elapsed);
        
        // Test remove performance
        startTime = System.currentTimeMillis();
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            ThreadUtil.removeThreadLocal(threadLocal1);
        }
        endTime = System.currentTimeMillis();
        elapsed = endTime - startTime;
        
        System.out.printf("ThreadLocal remove: %d operations in %d ms (%.2f operations/second)\n", 
                TEST_ITERATIONS, elapsed, (TEST_ITERATIONS * 1.0 * 1000) / elapsed);
    }

    /**
     * Test the performance of CountDownLatch operations.
     * <p>
     * 测试CountDownLatch操作的性能。
     */
    @Test
    void testCountDownLatchPerformance() {
        System.out.println("\n4. Testing CountDownLatch operations performance...");
        
        // Test count down performance
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            CountDownLatch latch = ThreadUtil.newCountDownLatch(10);
            for (int j = 0; j < 10; j++) {
                latch.countDown();
            }
            try {
                latch.await(1, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        long endTime = System.currentTimeMillis();
        long elapsed = endTime - startTime;
        
        System.out.printf("CountDownLatch operations: %d cycles in %d ms (%.2f operations/second)\n", 
                1000, elapsed, (1000.0 * 1000) / elapsed);
    }

    /**
     * Test the performance of concurrent operations.
     * <p>
     * 测试并发操作的性能。
     */
    @Test
    void testConcurrentOperations() {
        System.out.println("\n5. Testing concurrent operations performance...");
        
        // Test concurrent thread name operations
        final AtomicLong operationsCount = new AtomicLong(0);
        final CountDownLatch startLatch1 = new CountDownLatch(1);
        final CountDownLatch endLatch1 = new CountDownLatch(CONCURRENT_THREADS);
        
        for (int i = 0; i < CONCURRENT_THREADS; i++) {
            ThreadUtil.startThread(() -> {
                try {
                    startLatch1.await();
                    
                    // Perform thread name operations
                    for (int j = 0; j < 10000; j++) {
                        String name = ThreadUtil.getCurrentThreadName();
                        ThreadUtil.setCurrentThreadName("ConcurrentThread-" + j);
                        ThreadUtil.setCurrentThreadName(name);
                        operationsCount.incrementAndGet();
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    endLatch1.countDown();
                }
            });
        }
        
        long startTime = System.currentTimeMillis();
        startLatch1.countDown(); // Start all threads
        
        try {
            endLatch1.await(30, TimeUnit.SECONDS); // Wait for completion
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        long endTime = System.currentTimeMillis();
        long elapsed = endTime - startTime;
        
        System.out.printf("Concurrent thread name operations: %d operations in %d ms (%.2f operations/second)\n", 
                operationsCount.get(), elapsed, (operationsCount.get() * 1000.0) / elapsed);
        
        // Test concurrent thread creation
        final CountDownLatch startLatch2 = new CountDownLatch(1);
        final CountDownLatch endLatch2 = new CountDownLatch(CONCURRENT_THREADS);
        
        for (int i = 0; i < CONCURRENT_THREADS; i++) {
            ThreadUtil.startThread(() -> {
                try {
                    startLatch2.await();
                    
                    // Create and start small tasks
                    for (int j = 0; j < 100; j++) {
                        ThreadUtil.startThread(() -> {
                            ThreadUtil.sleep(1);
                        });
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    endLatch2.countDown();
                }
            });
        }
        
        startTime = System.currentTimeMillis();
        startLatch2.countDown(); // Start all threads
        
        try {
            endLatch2.await(30, TimeUnit.SECONDS); // Wait for completion
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        endTime = System.currentTimeMillis();
        elapsed = endTime - startTime;
        long totalThreads = CONCURRENT_THREADS * 100;
        
        System.out.printf("Concurrent thread creation: %d threads in %d ms (%.2f threads/second)\n", 
                totalThreads, elapsed, (totalThreads * 1000.0) / elapsed);
    }
}