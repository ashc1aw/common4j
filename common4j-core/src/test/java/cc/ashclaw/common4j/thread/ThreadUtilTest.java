// SPDX-License-Identifier: Apache-2.0
// Copyright (c) 2026 AshClaw.

package cc.ashclaw.common4j.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Test class for ThreadUtil result verification.
 * <p>
 * ThreadUtil结果验证测试类。
 *
 * @author b1itz7
 * @since 1.0.0
 */
public class ThreadUtilTest {

    public static void main(String[] args) {
        System.out.println("===== ThreadUtil Result Verification Test Start =====");
        
        // Test sleep methods
        testSleepMethods();
        
        // Test thread creation and management
        testThreadCreation();
        
        // Test thread name operations
        testThreadNameOperations();
        
        // Test thread group operations
        testThreadGroupOperations();
        
        // Test wait methods
        testWaitMethods();
        
        // Test thread utilities
        testThreadUtilities();
        
        System.out.println("===== ThreadUtil Result Verification Test End =====");
    }

    /**
     * Test the sleep methods of ThreadUtil.
     * <p>
     * 测试ThreadUtil的休眠方法。
     */
    private static void testSleepMethods() {
        System.out.println("\n1. Testing sleep methods...");
        
        // Test sleep with milliseconds
        long startTime = System.currentTimeMillis();
        ThreadUtil.sleep(100);
        long endTime = System.currentTimeMillis();
        long elapsed = endTime - startTime;
        System.out.println("sleep(100ms) elapsed time: " + elapsed + "ms");
        if (elapsed < 100) {
            System.out.println("ERROR: sleep(100ms) should sleep at least 100ms!");
            return;
        }
        
        // Test sleep with TimeUnit
        startTime = System.currentTimeMillis();
        ThreadUtil.sleep(100, TimeUnit.MILLISECONDS);
        endTime = System.currentTimeMillis();
        elapsed = endTime - startTime;
        System.out.println("sleep(100, MILLISECONDS) elapsed time: " + elapsed + "ms");
        if (elapsed < 100) {
            System.out.println("ERROR: sleep(100, MILLISECONDS) should sleep at least 100ms!");
            return;
        }
        
        // Test sleep with negative time (should do nothing)
        startTime = System.currentTimeMillis();
        ThreadUtil.sleep(-100);
        endTime = System.currentTimeMillis();
        elapsed = endTime - startTime;
        System.out.println("sleep(-100ms) elapsed time: " + elapsed + "ms");
        if (elapsed > 10) {
            System.out.println("ERROR: sleep(-100ms) should not sleep!");
            return;
        }
        
        System.out.println("sleep methods test passed.");
    }

    /**
     * Test the thread creation methods of ThreadUtil.
     * <p>
     * 测试ThreadUtil的线程创建方法。
     */
    private static void testThreadCreation() {
        System.out.println("\n2. Testing thread creation methods...");
        
        // Test createThread without name
        Thread thread1 = ThreadUtil.createThread(() -> {
            System.out.println("Thread1 is running: " + Thread.currentThread().getName());
        });
        System.out.println("createThread(runnable) thread name: " + thread1.getName());
        if (!thread1.getName().startsWith("Common4j-Thread-")) {
            System.out.println("ERROR: createThread should generate default name!");
            return;
        }
        
        // Test createThread with name
        Thread thread2 = ThreadUtil.createThread("TestThread", () -> {
            System.out.println("Thread2 is running: " + Thread.currentThread().getName());
        });
        System.out.println("createThread(\"TestThread\", runnable) thread name: " + thread2.getName());
        if (!"TestThread".equals(thread2.getName())) {
            System.out.println("ERROR: createThread should use provided name!");
            return;
        }
        
        // Test startThread without name
        Thread thread3 = ThreadUtil.startThread(() -> {
            System.out.println("Thread3 is running: " + Thread.currentThread().getName());
        });
        System.out.println("startThread(runnable) thread name: " + thread3.getName());
        if (!thread3.isAlive()) {
            System.out.println("ERROR: startThread should start the thread!");
            return;
        }
        
        // Test startThread with name
        Thread thread4 = ThreadUtil.startThread("StartedTestThread", () -> {
            System.out.println("Thread4 is running: " + Thread.currentThread().getName());
        });
        System.out.println("startThread(\"StartedTestThread\", runnable) thread name: " + thread4.getName());
        if (!thread4.isAlive()) {
            System.out.println("ERROR: startThread should start the thread!");
            return;
        }
        
        // Wait for threads to complete
        ThreadUtil.waitForAll(thread1, thread2, thread3, thread4);
        
        System.out.println("thread creation methods test passed.");
    }

    /**
     * Test the thread name operations of ThreadUtil.
     * <p>
     * 测试ThreadUtil的线程名称操作方法。
     */
    private static void testThreadNameOperations() {
        System.out.println("\n3. Testing thread name operations...");
        
        // Test getCurrentThreadName
        String originalName = ThreadUtil.getCurrentThreadName();
        System.out.println("getCurrentThreadName() before change: " + originalName);
        
        // Test setCurrentThreadName
        ThreadUtil.setCurrentThreadName("TestCurrentThread");
        String newName = ThreadUtil.getCurrentThreadName();
        System.out.println("getCurrentThreadName() after change: " + newName);
        if (!"TestCurrentThread".equals(newName)) {
            System.out.println("ERROR: setCurrentThreadName should change thread name!");
            return;
        }
        
        // Restore original name
        ThreadUtil.setCurrentThreadName(originalName);
        
        // Test setCurrentThreadName with null
        ThreadUtil.setCurrentThreadName(null);
        String nameAfterNull = ThreadUtil.getCurrentThreadName();
        System.out.println("getCurrentThreadName() after null: " + nameAfterNull);
        if (!originalName.equals(nameAfterNull)) {
            System.out.println("ERROR: setCurrentThreadName with null should not change thread name!");
            return;
        }
        
        System.out.println("thread name operations test passed.");
    }

    /**
     * Test the thread group operations of ThreadUtil.
     * <p>
     * 测试ThreadUtil的线程组操作方法。
     */
    private static void testThreadGroupOperations() {
        System.out.println("\n4. Testing thread group operations...");
        
        // Test getCommon4jThreadGroup
        ThreadGroup group = ThreadUtil.getCommon4jThreadGroup();
        System.out.println("getCommon4jThreadGroup() name: " + group.getName());
        if (!"Common4j-Threads".equals(group.getName())) {
            System.out.println("ERROR: getCommon4jThreadGroup should return correct thread group!");
            return;
        }
        
        System.out.println("thread group operations test passed.");
    }

    /**
     * Test the wait methods of ThreadUtil.
     * <p>
     * 测试ThreadUtil的等待方法。
     */
    private static void testWaitMethods() {
        System.out.println("\n5. Testing wait methods...");
        
        // Test waitForAll
        final CountDownLatch latch = new CountDownLatch(2);
        
        Thread thread1 = ThreadUtil.startThread(() -> {
            try {
                Thread.sleep(200);
                latch.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        
        Thread thread2 = ThreadUtil.startThread(() -> {
            try {
                Thread.sleep(100);
                latch.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        
        long startTime = System.currentTimeMillis();
        ThreadUtil.waitForAll(thread1, thread2);
        long endTime = System.currentTimeMillis();
        long elapsed = endTime - startTime;
        
        System.out.println("waitForAll() elapsed time: " + elapsed + "ms");
        if (elapsed < 200) {
            System.out.println("ERROR: waitForAll should wait for all threads to complete!");
            return;
        }
        
        System.out.println("wait methods test passed.");
    }

    /**
     * Test the thread utility methods of ThreadUtil.
     * <p>
     * 测试ThreadUtil的线程工具方法。
     */
    private static void testThreadUtilities() {
        System.out.println("\n6. Testing thread utilities...");
        
        // Test newCountDownLatch
        CountDownLatch latch1 = ThreadUtil.newCountDownLatch(3);
        System.out.println("newCountDownLatch(3) count: " + latch1.getCount());
        if (latch1.getCount() != 3) {
            System.out.println("ERROR: newCountDownLatch(3) should create latch with count 3!");
            return;
        }
        
        // Test newCountDownLatch with negative count
        CountDownLatch latch2 = ThreadUtil.newCountDownLatch(-1);
        System.out.println("newCountDownLatch(-1) count: " + latch2.getCount());
        if (latch2.getCount() != 1) {
            System.out.println("ERROR: newCountDownLatch with negative count should create latch with count 1!");
            return;
        }
        
        // Test newThreadLocal
        ThreadLocal<String> threadLocal = ThreadUtil.newThreadLocal();
        if (threadLocal == null) {
            System.out.println("ERROR: newThreadLocal should create a new ThreadLocal!");
            return;
        }
        
        // Test newThreadLocal with initial value
        ThreadLocal<Integer> threadLocalWithInitial = ThreadUtil.newThreadLocal(() -> 42);
        if (threadLocalWithInitial == null) {
            System.out.println("ERROR: newThreadLocal with supplier should create a new ThreadLocal!");
            return;
        }
        System.out.println("newThreadLocal with supplier initial value: " + threadLocalWithInitial.get());
        if (threadLocalWithInitial.get() != 42) {
            System.out.println("ERROR: newThreadLocal with supplier should set initial value!");
            return;
        }
        
        // Test removeThreadLocal
        threadLocalWithInitial.set(100);
        System.out.println("threadLocal value before remove: " + threadLocalWithInitial.get());
        ThreadUtil.removeThreadLocal(threadLocalWithInitial);
        System.out.println("threadLocal value after remove: " + threadLocalWithInitial.get());
        
        // Test interrupt methods
        Thread testThread = ThreadUtil.createThread(() -> {
            while (!ThreadUtil.isInterrupted()) {
                ThreadUtil.sleep(10);
            }
            System.out.println("Thread was interrupted as expected");
        });
        testThread.start();
        ThreadUtil.sleep(50);
        ThreadUtil.interrupt(testThread);
        ThreadUtil.waitForAll(testThread);
        
        System.out.println("thread utilities test passed.");
    }
}
