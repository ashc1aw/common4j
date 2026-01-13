// SPDX-License-Identifier: Apache-2.0
// Copyright (c) 2026 AshClaw.

package cc.ashclaw.common4j.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Test class for ThreadPoolUtil result verification.
 * <p>
 * ThreadPoolUtil结果验证测试类。
 *
 * @author b1itz7
 * @since 1.0.0
 */
public class ThreadPoolUtilTest {

    public static void main(String[] args) {
        System.out.println("===== ThreadPoolUtil Result Verification Test Start =====");
        
        // Test thread pool creation
        testThreadPoolCreation();
        
        // Test thread pool operations
        testThreadPoolOperations();
        
        // Test thread pool shutdown
        testThreadPoolShutdown();
        
        // Test timeout execution
        testTimeoutExecution();
        
        System.out.println("===== ThreadPoolUtil Result Verification Test End =====");
    }

    /**
     * Test the thread pool creation methods of ThreadPoolUtil.
     * <p>
     * 测试ThreadPoolUtil的线程池创建方法。
     */
    private static void testThreadPoolCreation() {
        System.out.println("\n1. Testing thread pool creation methods...");
        
        // Test newCachedThreadPool
        ExecutorService cachedPool = ThreadPoolUtil.newCachedThreadPool();
        System.out.println("newCachedThreadPool() created");
        
        // Test newFixedThreadPool
        ExecutorService fixedPool = ThreadPoolUtil.newFixedThreadPool(5);
        System.out.println("newFixedThreadPool(5) created");
        
        // Test newFixedThreadPool with negative size
        ExecutorService fixedPoolDefault = ThreadPoolUtil.newFixedThreadPool(-1);
        System.out.println("newFixedThreadPool(-1) created with default size");
        
        // Test newScheduledThreadPool
        ScheduledExecutorService scheduledPool = ThreadPoolUtil.newScheduledThreadPool(3);
        System.out.println("newScheduledThreadPool(3) created");
        
        // Test newScheduledThreadPool with negative size
        ScheduledExecutorService scheduledPoolDefault = ThreadPoolUtil.newScheduledThreadPool(-1);
        System.out.println("newScheduledThreadPool(-1) created with default size");
        
        // Test newSingleThreadExecutor
        ExecutorService singleThreadPool = ThreadPoolUtil.newSingleThreadExecutor();
        System.out.println("newSingleThreadExecutor() created");
        
        // Shutdown all pools
        ThreadPoolUtil.shutdown(cachedPool);
        ThreadPoolUtil.shutdown(fixedPool);
        ThreadPoolUtil.shutdown(fixedPoolDefault);
        ThreadPoolUtil.shutdown(scheduledPool);
        ThreadPoolUtil.shutdown(scheduledPoolDefault);
        ThreadPoolUtil.shutdown(singleThreadPool);
        
        System.out.println("thread pool creation methods test passed.");
    }

    /**
     * Test the thread pool operations of ThreadPoolUtil.
     * <p>
     * 测试ThreadPoolUtil的线程池操作方法。
     */
    private static void testThreadPoolOperations() {
        System.out.println("\n2. Testing thread pool operations...");
        
        // Test executing tasks in thread pool
        ExecutorService pool = ThreadPoolUtil.newFixedThreadPool(3);
        
        AtomicInteger taskCounter = new AtomicInteger(0);
        int taskCount = 5;
        
        for (int i = 0; i < taskCount; i++) {
            final int taskId = i + 1;
            pool.execute(() -> {
                System.out.println("Executing task " + taskId + " in thread: " + Thread.currentThread().getName());
                ThreadUtil.sleep(100); // Simulate work
                taskCounter.incrementAndGet();
            });
        }
        
        // Wait for tasks to complete
        ThreadUtil.sleep(600); // Wait longer than all tasks combined
        
        System.out.println("Tasks completed: " + taskCounter.get() + " / " + taskCount);
        if (taskCounter.get() != taskCount) {
            System.out.println("ERROR: Not all tasks were completed!");
            ThreadPoolUtil.shutdown(pool);
            return;
        }
        
        ThreadPoolUtil.shutdown(pool);
        
        System.out.println("thread pool operations test passed.");
    }

    /**
     * Test the thread pool shutdown methods of ThreadPoolUtil.
     * <p>
     * 测试ThreadPoolUtil的线程池关闭方法。
     */
    private static void testThreadPoolShutdown() {
        System.out.println("\n3. Testing thread pool shutdown methods...");
        
        // Test shutdown with normal pool
        ExecutorService pool1 = ThreadPoolUtil.newFixedThreadPool(2);
        pool1.execute(() -> {
            ThreadUtil.sleep(200);
            System.out.println("Task 1 completed");
        });
        pool1.execute(() -> {
            ThreadUtil.sleep(300);
            System.out.println("Task 2 completed");
        });
        
        ThreadUtil.sleep(100); // Let tasks start
        System.out.println("Shutting down pool1 normally");
        ThreadPoolUtil.shutdown(pool1);
        
        // Test shutdown with already shutdown pool
        System.out.println("Shutting down already shutdown pool1");
        ThreadPoolUtil.shutdown(pool1);
        
        // Test shutdown with null
        System.out.println("Shutting down null pool");
        ThreadPoolUtil.shutdown(null);
        
        // Test shutdown with long running task
        ExecutorService pool2 = ThreadPoolUtil.newFixedThreadPool(2);
        pool2.execute(() -> {
            try {
                Thread.sleep(10000); // Long running task
                System.out.println("Long task completed");
            } catch (InterruptedException e) {
                System.out.println("Long task was interrupted as expected");
            }
        });
        
        ThreadUtil.sleep(100); // Let task start
        System.out.println("Shutting down pool2 with long running task");
        ThreadPoolUtil.shutdown(pool2);
        
        System.out.println("thread pool shutdown methods test passed.");
    }

    /**
     * Test the timeout execution methods of ThreadPoolUtil.
     * <p>
     * 测试ThreadPoolUtil的超时执行方法。
     */
    private static void testTimeoutExecution() {
        System.out.println("\n4. Testing timeout execution methods...");
        
        // Test executeWithTimeout with quick task
        String result1 = ThreadPoolUtil.executeWithTimeout(() -> {
            ThreadUtil.sleep(100);
            return "Quick task result";
        }, 500, TimeUnit.MILLISECONDS, "Default value");
        
        System.out.println("executeWithTimeout with quick task: " + result1);
        if (!"Quick task result".equals(result1)) {
            System.out.println("ERROR: executeWithTimeout should return task result for quick task!");
            return;
        }
        
        // Test executeWithTimeout with timeout
        String result2 = ThreadPoolUtil.executeWithTimeout(() -> {
            ThreadUtil.sleep(1000);
            return "Slow task result";
        }, 500, TimeUnit.MILLISECONDS, "Default value");
        
        System.out.println("executeWithTimeout with timeout: " + result2);
        if (!"Default value".equals(result2)) {
            System.out.println("ERROR: executeWithTimeout should return default value for timeout!");
            return;
        }
        
        // Test executeWithTimeout with null supplier
        String result3 = ThreadPoolUtil.executeWithTimeout(null, 500, TimeUnit.MILLISECONDS, "Default value");
        
        System.out.println("executeWithTimeout with null supplier: " + result3);
        if (!"Default value".equals(result3)) {
            System.out.println("ERROR: executeWithTimeout should return default value for null supplier!");
            return;
        }
        
        // Test executeWithTimeout with integer result
        Integer result4 = ThreadPoolUtil.executeWithTimeout(() -> {
            ThreadUtil.sleep(100);
            return 42;
        }, 500, TimeUnit.MILLISECONDS, 0);
        
        System.out.println("executeWithTimeout with integer result: " + result4);
        if (result4 != 42) {
            System.out.println("ERROR: executeWithTimeout should return correct integer result!");
            return;
        }
        
        System.out.println("timeout execution methods test passed.");
    }
}
