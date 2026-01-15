// SPDX-License-Identifier: Apache-2.0
// Copyright (c) 2026 AshClaw.

package cc.ashclaw.core.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cc.ashclaw.common4j.core.thread.ThreadUtil;
import org.junit.jupiter.api.Test;

/**
 * Test class for ThreadUtil result verification using JUnit 5.
 * <p>
 * ThreadUtil结果验证测试类，使用JUnit 5。
 *
 * @author b1itz7
 * @since 1.0.0
 */
public class ThreadUtilTest {

    /**
     * Test the sleep methods of ThreadUtil.
     * <p>
     * 测试ThreadUtil的休眠方法。
     */
    @Test
    void testSleepMethods() {
        // Test sleep with milliseconds
        long startTime = System.currentTimeMillis();
        ThreadUtil.sleep(100);
        long endTime = System.currentTimeMillis();
        long elapsed = endTime - startTime;
        assertTrue(elapsed >= 100, "sleep(100ms) should sleep at least 100ms");
        
        // Test sleep with TimeUnit
        startTime = System.currentTimeMillis();
        ThreadUtil.sleep(100, TimeUnit.MILLISECONDS);
        endTime = System.currentTimeMillis();
        elapsed = endTime - startTime;
        assertTrue(elapsed >= 100, "sleep(100, MILLISECONDS) should sleep at least 100ms");
        
        // Test sleep with negative time (should do nothing)
        startTime = System.currentTimeMillis();
        ThreadUtil.sleep(-100);
        endTime = System.currentTimeMillis();
        elapsed = endTime - startTime;
        assertTrue(elapsed <= 10, "sleep(-100ms) should not sleep");
    }

    /**
     * Test the thread creation methods of ThreadUtil.
     * <p>
     * 测试ThreadUtil的线程创建方法。
     */
    @Test
    void testThreadCreation() {
        // Test createThread without name
        Thread thread1 = ThreadUtil.createThread(() -> {
            // Do nothing
        });
        assertTrue(thread1.getName().startsWith("Common4j-Thread-"), "createThread should generate default name");
        
        // Test createThread with name
        Thread thread2 = ThreadUtil.createThread("TestThread", () -> {
            // Do nothing
        });
        assertEquals("TestThread", thread2.getName(), "createThread should use provided name");
        
        // Test startThread without name
        Thread thread3 = ThreadUtil.startThread(() -> {
            // Do nothing
        });
        assertTrue(thread3.isAlive(), "startThread should start the thread");
        
        // Test startThread with name
        Thread thread4 = ThreadUtil.startThread("StartedTestThread", () -> {
            // Do nothing
        });
        assertTrue(thread4.isAlive(), "startThread should start the thread");
        
        // Wait for threads to complete
        ThreadUtil.waitForAll(thread1, thread2, thread3, thread4);
    }

    /**
     * Test the thread name operations of ThreadUtil.
     * <p>
     * 测试ThreadUtil的线程名称操作方法。
     */
    @Test
    void testThreadNameOperations() {
        // Test getCurrentThreadName
        String originalName = ThreadUtil.getCurrentThreadName();
        
        // Test setCurrentThreadName
        ThreadUtil.setCurrentThreadName("TestCurrentThread");
        String newName = ThreadUtil.getCurrentThreadName();
        assertEquals("TestCurrentThread", newName, "setCurrentThreadName should change thread name");
        
        // Restore original name
        ThreadUtil.setCurrentThreadName(originalName);
        
        // Test setCurrentThreadName with null
        ThreadUtil.setCurrentThreadName(null);
        String nameAfterNull = ThreadUtil.getCurrentThreadName();
        assertEquals(originalName, nameAfterNull, "setCurrentThreadName with null should not change thread name");
    }

    /**
     * Test the thread group operations of ThreadUtil.
     * <p>
     * 测试ThreadUtil的线程组操作方法。
     */
    @Test
    void testThreadGroupOperations() {
        // Test getCommon4jThreadGroup
        ThreadGroup group = ThreadUtil.getCommon4jThreadGroup();
        assertEquals("Common4j-Threads", group.getName(), "getCommon4jThreadGroup should return correct thread group");
    }

    /**
     * Test the wait methods of ThreadUtil.
     * <p>
     * 测试ThreadUtil的等待方法。
     */
    @Test
    void testWaitMethods() {
        // Test waitForAll
        final CountDownLatch latch = new CountDownLatch(2);
        
        Thread thread1 = ThreadUtil.startThread(() -> {
            try {
                Thread.sleep(200);
                latch.countDown();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        
        Thread thread2 = ThreadUtil.startThread(() -> {
            try {
                Thread.sleep(100);
                latch.countDown();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        
        long startTime = System.currentTimeMillis();
        ThreadUtil.waitForAll(thread1, thread2);
        long endTime = System.currentTimeMillis();
        long elapsed = endTime - startTime;
        
        assertTrue(elapsed >= 200, "waitForAll should wait for all threads to complete");
    }

    /**
     * Test the thread utility methods of ThreadUtil.
     * <p>
     * 测试ThreadUtil的线程工具方法。
     */
    @Test
    void testThreadUtilities() {
        // Test newCountDownLatch
        CountDownLatch latch1 = ThreadUtil.newCountDownLatch(3);
        assertEquals(3, latch1.getCount(), "newCountDownLatch(3) should create latch with count 3");
        
        // Test newCountDownLatch with negative count
        CountDownLatch latch2 = ThreadUtil.newCountDownLatch(-1);
        assertEquals(1, latch2.getCount(), "newCountDownLatch with negative count should create latch with count 1");
        
        // Test newThreadLocal
        ThreadLocal<String> threadLocal = ThreadUtil.newThreadLocal();
        assertNotNull(threadLocal, "newThreadLocal should create a new ThreadLocal");
        
        // Test newThreadLocal with initial value
        ThreadLocal<Integer> threadLocalWithInitial = ThreadUtil.newThreadLocal(() -> 42);
        assertNotNull(threadLocalWithInitial, "newThreadLocal with supplier should create a new ThreadLocal");
        assertEquals(42, threadLocalWithInitial.get(), "newThreadLocal with supplier should set initial value");
        
        // Test removeThreadLocal
        threadLocalWithInitial.set(100);
        assertEquals(100, threadLocalWithInitial.get(), "threadLocal should have value 100 before remove");
        ThreadUtil.removeThreadLocal(threadLocalWithInitial);
        assertEquals(42, threadLocalWithInitial.get(), "threadLocal should return initial value 42 after remove");
        // Test removeThreadLocal for ThreadLocal without initial value
        threadLocal.set("test");
        assertEquals("test", threadLocal.get(), "threadLocal should have value 'test' before remove");
        ThreadUtil.removeThreadLocal(threadLocal);
        assertNull(threadLocal.get(), "threadLocal without initial value should be null after remove");

        
        // Test interrupt methods
        Thread testThread = ThreadUtil.createThread(() -> {
            while (!ThreadUtil.isInterrupted()) {
                ThreadUtil.sleep(10);
            }
        });
        testThread.start();
        ThreadUtil.sleep(50);
        ThreadUtil.interrupt(testThread);
        ThreadUtil.waitForAll(testThread);
        assertFalse(testThread.isAlive(), "interrupted thread should be terminated");
    }
}