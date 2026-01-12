// SPDX-License-Identifier: Apache-2.0
// Copyright (c) 2026 AshClaw.

package cc.ashclaw.common4j.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

/**
 * Utility class for thread operations.
 * <p>
 * 线程操作工具类。
 * <p>
 * Provides methods for common thread operations such as thread sleeping,
 * interruption, thread creation, and more.
 * <p>
 * 提供常见的线程操作方法，如线程休眠、中断、线程创建等。
 *
 * @author b1itz7
 * @since 1.0.0
 */
public final class ThreadUtil {

    /**
     * Thread group for common4j threads.
     * <p>
     * Common4j线程的线程组。
     */
    private static final ThreadGroup COMMON4J_THREAD_GROUP = new ThreadGroup("Common4j-Threads");

    /**
     * Thread counter for naming threads.
     * <p>
     * 用于命名线程的线程计数器。
     */
    private static final AtomicInteger THREAD_COUNTER = new AtomicInteger(1);

    /**
     * Private constructor to prevent instantiation.
     * <p>
     * 私有构造函数，防止实例化。
     */
    private ThreadUtil() {
        throw new UnsupportedOperationException("This class cannot be instantiated.");
    }

    /**
     * Sleeps the current thread for the specified number of milliseconds.
     * <p>
     * 使当前线程休眠指定的毫秒数。
     *
     * @param milliseconds the number of milliseconds to sleep
     *                    <p>
     *                    休眠的毫秒数
     */
    public static void sleep(long milliseconds) {
        if (milliseconds <= 0) {
            return;
        }
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Sleeps the current thread for the specified duration.
     * <p>
     * 使当前线程休眠指定的时长。
     *
     * @param duration the duration to sleep
     *                 <p>
     *                 休眠的时长
     * @param unit     the time unit of the duration
     *                 <p>
     *                 时长的时间单位
     */
    public static void sleep(long duration, TimeUnit unit) {
        if (duration <= 0 || unit == null) {
            return;
        }
        try {
            unit.sleep(duration);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Interrupts the specified thread.
     * <p>
     * 中断指定的线程。
     *
     * @param thread the thread to interrupt
     *               <p>
     *               要中断的线程
     */
    public static void interrupt(Thread thread) {
        if (thread == null || !thread.isAlive()) {
            return;
        }
        thread.interrupt();
    }

    /**
     * Checks if the current thread is interrupted.
     * <p>
     * 检查当前线程是否被中断。
     *
     * @return true if the current thread is interrupted, false otherwise
     *         <p>
     *         如果当前线程被中断则返回true，否则返回false
     */
    public static boolean isInterrupted() {
        return Thread.currentThread().isInterrupted();
    }

    /**
     * Creates a new thread with the specified runnable.
     * <p>
     * 使用指定的可运行对象创建一个新线程。
     *
     * @param runnable the runnable task
     *                 <p>
     *                 可运行任务
     * @return the newly created thread
     *         <p>
     *         新创建的线程
     */
    public static Thread createThread(Runnable runnable) {
        return createThread(null, runnable);
    }

    /**
     * Creates a new thread with the specified name and runnable.
     * <p>
     * 使用指定的名称和可运行对象创建一个新线程。
     *
     * @param name     the name of the thread
     *                 <p>
     *                 线程名称
     * @param runnable the runnable task
     *                 <p>
     *                 可运行任务
     * @return the newly created thread
     *         <p>
     *         新创建的线程
     */
    public static Thread createThread(String name, Runnable runnable) {
        if (runnable == null) {
            throw new IllegalArgumentException("Runnable cannot be null");
        }
        if (name == null) {
            name = "Common4j-Thread-" + THREAD_COUNTER.getAndIncrement();
        }
        return new Thread(COMMON4J_THREAD_GROUP, runnable, name);
    }

    /**
     * Starts a new thread with the specified runnable.
     * <p>
     * 使用指定的可运行对象启动一个新线程。
     *
     * @param runnable the runnable task
     *                 <p>
     *                 可运行任务
     * @return the started thread
     *         <p>
     *         已启动的线程
     */
    public static Thread startThread(Runnable runnable) {
        return startThread(null, runnable);
    }

    /**
     * Starts a new thread with the specified name and runnable.
     * <p>
     * 使用指定的名称和可运行对象启动一个新线程。
     *
     * @param name     the name of the thread
     *                 <p>
     *                 线程名称
     * @param runnable the runnable task
     *                 <p>
     *                 可运行任务
     * @return the started thread
     *         <p>
     *         已启动的线程
     */
    public static Thread startThread(String name, Runnable runnable) {
        Thread thread = createThread(name, runnable);
        thread.start();
        return thread;
    }

    /**
     * Gets the current thread's name.
     * <p>
     * 获取当前线程的名称。
     *
     * @return the current thread's name
     *         <p>
     *         当前线程的名称
     */
    public static String getCurrentThreadName() {
        return Thread.currentThread().getName();
    }

    /**
     * Sets the current thread's name.
     * <p>
     * 设置当前线程的名称。
     *
     * @param name the new name for the current thread
     *             <p>
     *             当前线程的新名称
     */
    public static void setCurrentThreadName(String name) {
        if (name != null && !name.isEmpty()) {
            Thread.currentThread().setName(name);
        }
    }

    /**
     * Gets the thread group for common4j threads.
     * <p>
     * 获取Common4j线程的线程组。
     *
     * @return the common4j thread group
     *         <p>
     *         Common4j线程组
     */
    public static ThreadGroup getCommon4jThreadGroup() {
        return COMMON4J_THREAD_GROUP;
    }

    /**
     * Waits for all the specified threads to complete.
     * <p>
     * 等待所有指定的线程完成。
     *
     * @param threads the threads to wait for
     *               <p>
     *               要等待的线程
     */
    public static void waitForAll(Thread... threads) {
        if (threads == null || threads.length == 0) {
            return;
        }
        for (Thread thread : threads) {
            if (thread != null && thread.isAlive()) {
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }
    }

    /**
     * Creates a countdown latch with the specified count.
     * <p>
     * 使用指定的计数创建一个倒计时门闩。
     *
     * @param count the number of times {@link CountDownLatch#countDown()} must be invoked
     *             <p>
     *             必须调用{@link CountDownLatch#countDown()}的次数
     * @return the created countdown latch
     *         <p>
     *         创建的倒计时门闩
     */
    public static CountDownLatch newCountDownLatch(int count) {
        return new CountDownLatch(count > 0 ? count : 1);
    }

    /**
     * Creates a new thread local variable.
     * <p>
     * 创建一个新的线程局部变量。
     *
     * @param <T> the type of the thread local variable
     *           <p>
     *           线程局部变量的类型
     * @return the created thread local variable
     *         <p>
     *         创建的线程局部变量
     */
    public static <T> ThreadLocal<T> newThreadLocal() {
        return new ThreadLocal<>();
    }

    /**
     * Creates a new thread local variable with the specified initial value.
     * <p>
     * 使用指定的初始值创建一个新的线程局部变量。
     *
     * @param <T>          the type of the thread local variable
     *                    <p>
     *                    线程局部变量的类型
     * @param initialValue the initial value supplier
     *                    <p>
     *                    初始值提供者
     * @return the created thread local variable
     *         <p>
     *         创建的线程局部变量
     */
    public static <T> ThreadLocal<T> newThreadLocal(Supplier<T> initialValue) {
        return ThreadLocal.withInitial(initialValue);
    }

    /**
     * Removes the value of the specified thread local variable for the current thread.
     * <p>
     * 移除当前线程的指定线程局部变量的值。
     *
     * @param <T>        the type of the thread local variable
     *                   <p>
     *                   线程局部变量的类型
     * @param threadLocal the thread local variable to remove
     *                   <p>
     *                   要移除的线程局部变量
     */
    public static <T> void removeThreadLocal(ThreadLocal<T> threadLocal) {
        if (threadLocal != null) {
            threadLocal.remove();
        }
    }
}
