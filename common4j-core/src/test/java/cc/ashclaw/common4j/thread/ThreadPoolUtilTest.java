// SPDX-License-Identifier: Apache-2.0
// Copyright (c) 2026 AshClaw.

package cc.ashclaw.common4j.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for ThreadPoolUtil result verification using JUnit 5.
 * <p>
 * ThreadPoolUtil结果验证测试类，使用JUnit 5。
 *
 * @author b1itz7
 * @since 1.0.0
 */
public class ThreadPoolUtilTest {

    /**
     * Test the thread pool creation methods of ThreadPoolUtil.
     * <p>
     * 测试ThreadPoolUtil的线程池创建方法。
     */
    @Test
    void testThreadPoolCreation() {
        // Test newCachedThreadPool
        ExecutorService cachedPool = ThreadPoolUtil.newCachedThreadPool();
        assertNotNull(cachedPool, "newCachedThreadPool() should create a non-null ExecutorService");
        
        // Test newFixedThreadPool
        ExecutorService fixedPool = ThreadPoolUtil.newFixedThreadPool(5);
        assertNotNull(fixedPool, "newFixedThreadPool(5) should create a non-null ExecutorService");
        
        // Test newFixedThreadPool with negative size
        ExecutorService fixedPoolDefault = ThreadPoolUtil.newFixedThreadPool(-1);
        assertNotNull(fixedPoolDefault, "newFixedThreadPool(-1) should create a non-null ExecutorService");
        
        // Test newScheduledThreadPool
        ScheduledExecutorService scheduledPool = ThreadPoolUtil.newScheduledThreadPool(3);
        assertNotNull(scheduledPool, "newScheduledThreadPool(3) should create a non-null ScheduledExecutorService");
        
        // Test newScheduledThreadPool with negative size
        ScheduledExecutorService scheduledPoolDefault = ThreadPoolUtil.newScheduledThreadPool(-1);
        assertNotNull(scheduledPoolDefault, "newScheduledThreadPool(-1) should create a non-null ScheduledExecutorService");
        
        // Test newSingleThreadExecutor
        ExecutorService singleThreadPool = ThreadPoolUtil.newSingleThreadExecutor();
        assertNotNull(singleThreadPool, "newSingleThreadExecutor() should create a non-null ExecutorService");
        
        // Shutdown all pools
        ThreadPoolUtil.shutdown(cachedPool);
        ThreadPoolUtil.shutdown(fixedPool);
        ThreadPoolUtil.shutdown(fixedPoolDefault);
        ThreadPoolUtil.shutdown(scheduledPool);
        ThreadPoolUtil.shutdown(scheduledPoolDefault);
        ThreadPoolUtil.shutdown(singleThreadPool);
    }

    /**
     * Test the thread pool operations of ThreadPoolUtil.
     * <p>
     * 测试ThreadPoolUtil的线程池操作方法。
     */
    @Test
    void testThreadPoolOperations() {
        // Test executing tasks in thread pool
        ExecutorService pool = ThreadPoolUtil.newFixedThreadPool(3);
        
        AtomicInteger taskCounter = new AtomicInteger(0);
        int taskCount = 5;
        
        for (int i = 0; i < taskCount; i++) {
            final int taskId = i + 1;
            pool.execute(() -> {
                ThreadUtil.sleep(100); // Simulate work
                taskCounter.incrementAndGet();
            });
        }
        
        // Wait for tasks to complete
        ThreadUtil.sleep(600); // Wait longer than all tasks combined
        
        assertEquals(taskCount, taskCounter.get(), "All tasks should be completed");
        
        ThreadPoolUtil.shutdown(pool);
    }

    /**
     * Test the thread pool shutdown methods of ThreadPoolUtil.
     * <p>
     * 测试ThreadPoolUtil的线程池关闭方法。
     */
    @Test
    void testThreadPoolShutdown() {
        // Test shutdown with normal pool
        ExecutorService pool1 = ThreadPoolUtil.newFixedThreadPool(2);
        pool1.execute(() -> {
            ThreadUtil.sleep(200);
        });
        pool1.execute(() -> {
            ThreadUtil.sleep(300);
        });
        
        ThreadUtil.sleep(100); // Let tasks start
        ThreadPoolUtil.shutdown(pool1);
        
        // Test shutdown with already shutdown pool
        ThreadPoolUtil.shutdown(pool1); // Should not throw exception
        
        // Test shutdown with null
        ThreadPoolUtil.shutdown(null); // Should not throw exception
        
        // Test shutdown with long running task
        ExecutorService pool2 = ThreadPoolUtil.newFixedThreadPool(2);
        pool2.execute(() -> {
            try {
                Thread.sleep(10000); // Long running task
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        
        ThreadUtil.sleep(100); // Let task start
        ThreadPoolUtil.shutdown(pool2);
    }

    /**
     * Test the timeout execution methods of ThreadPoolUtil.
     * <p>
     * 测试ThreadPoolUtil的超时执行方法。
     */
    @Test
    void testTimeoutExecution() {
        // Test executeWithTimeout with quick task
        String result1 = ThreadPoolUtil.executeWithTimeout(() -> {
            ThreadUtil.sleep(100);
            return "Quick task result";
        }, 500, TimeUnit.MILLISECONDS, "Default value");
        assertEquals("Quick task result", result1, "executeWithTimeout should return task result for quick task");
        
        // Test executeWithTimeout with timeout
        String result2 = ThreadPoolUtil.executeWithTimeout(() -> {
            ThreadUtil.sleep(1000);
            return "Slow task result";
        }, 500, TimeUnit.MILLISECONDS, "Default value");
        assertEquals("Default value", result2, "executeWithTimeout should return default value for timeout");
        
        // Test executeWithTimeout with null supplier
        String result3 = ThreadPoolUtil.executeWithTimeout(null, 500, TimeUnit.MILLISECONDS, "Default value");
        assertEquals("Default value", result3, "executeWithTimeout should return default value for null supplier");
        
        // Test executeWithTimeout with integer result
        Integer result4 = ThreadPoolUtil.executeWithTimeout(() -> {
            ThreadUtil.sleep(100);
            return 42;
        }, 500, TimeUnit.MILLISECONDS, 0);
        assertEquals(42, result4, "executeWithTimeout should return correct integer result");
    }
}