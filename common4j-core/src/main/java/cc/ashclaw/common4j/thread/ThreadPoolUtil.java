// SPDX-License-Identifier: Apache-2.0
// Copyright (c) 2026 AshClaw.

package cc.ashclaw.common4j.thread;

import cc.ashclaw.common4j.exception.ThreadPoolException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

/**
 * Utility class for thread pool operations.
 * <p>
 * 线程池操作工具类。
 * <p>
 * Provides methods for common thread pool operations such as creating different
 * types of thread pools, shutting down thread pools, and executing tasks with timeout.
 * <p>
 * 提供常见的线程池操作方法，如创建不同类型的线程池、关闭线程池以及执行带超时的任务。
 *
 * @author b1itz7
 * @since 1.0.0
 */
public final class ThreadPoolUtil {

    /**
     * Default thread pool size.
     * <p>
     * 默认线程池大小。
     */
    private static final int DEFAULT_POOL_SIZE = Runtime.getRuntime().availableProcessors() * 2;

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
    private ThreadPoolUtil() {
        throw new UnsupportedOperationException("This class cannot be instantiated.");
    }

    /**
     * Validates if the executor service is valid and not shutdown.
     * <p>
     * 验证执行器服务是否有效且未关闭。
     *
     * @param executor the executor service to validate
     *                 <p>
     *                 要验证的执行器服务
     * @throws ThreadPoolException if the executor is null or already shutdown
     *                             <p>
     *                             如果执行器为null或已关闭则抛出ThreadPoolException
     */
    private static void validateExecutor(ExecutorService executor) {
        if (executor == null) {
            throw new ThreadPoolException("Executor service cannot be null.");
        }
        if (executor.isShutdown()) {
            throw new ThreadPoolException("Executor service has already been shutdown.");
        }
    }

    /**
     * Creates a new cached thread pool.
     * <p>
     * 创建一个新的缓存线程池。
     *
     * @return the created thread pool
     *         <p>
     *         创建的线程池
     */
    public static ExecutorService newCachedThreadPool() {
        return Executors.newCachedThreadPool(r -> {
            String name = "Common4j-Cached-Thread-" + THREAD_COUNTER.getAndIncrement();
            return new Thread(COMMON4J_THREAD_GROUP, r, name);
        });
    }

    /**
     * Creates a new fixed thread pool with the specified size.
     * <p>
     * 创建一个新的固定大小的线程池。
     *
     * @param nThreads the number of threads in the pool
     *                 <p>
     *                 线程池中的线程数
     * @return the created thread pool
     *         <p>
     *         创建的线程池
     */
    public static ExecutorService newFixedThreadPool(int nThreads) {
        if (nThreads <= 0) {
            nThreads = DEFAULT_POOL_SIZE;
        }
        return Executors.newFixedThreadPool(nThreads, r -> {
            String name = "Common4j-Fixed-Thread-" + THREAD_COUNTER.getAndIncrement();
            return new Thread(COMMON4J_THREAD_GROUP, r, name);
        });
    }

    /**
     * Creates a new scheduled thread pool with the specified core size.
     * <p>
     * 创建一个新的具有指定核心大小的计划线程池。
     *
     * @param corePoolSize the number of core threads in the pool
     *                     <p>
     *                     线程池中的核心线程数
     * @return the created scheduled thread pool
     *         <p>
     *         创建的计划线程池
     */
    public static ScheduledExecutorService newScheduledThreadPool(int corePoolSize) {
        if (corePoolSize <= 0) {
            corePoolSize = Runtime.getRuntime().availableProcessors();
        }
        return Executors.newScheduledThreadPool(corePoolSize, r -> {
            String name = "Common4j-Scheduled-Thread-" + THREAD_COUNTER.getAndIncrement();
            return new Thread(COMMON4J_THREAD_GROUP, r, name);
        });
    }

    /**
     * Creates a new single-threaded executor.
     * <p>
     * 创建一个新的单线程执行器。
     *
     * @return the created single-threaded executor
     *         <p>
     *         创建的单线程执行器
     */
    public static ExecutorService newSingleThreadExecutor() {
        return Executors.newSingleThreadExecutor(r -> {
            String name = "Common4j-Single-Thread-" + THREAD_COUNTER.getAndIncrement();
            return new Thread(COMMON4J_THREAD_GROUP, r, name);
        });
    }

    /**
     * Shuts down the specified executor service gracefully.
     * <p>
     * 优雅地关闭指定的执行器服务。
     *
     * @param executor the executor service to shut down
     *                 <p>
     *                 要关闭的执行器服务
     */
    public static void shutdown(ExecutorService executor) {
        if (executor == null || executor.isShutdown()) {
            return;
        }
        executor.shutdown();
        try {
            if (!executor.awaitTermination(5, TimeUnit.SECONDS)) {
                executor.shutdownNow();
                executor.awaitTermination(1, TimeUnit.SECONDS);
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Executes a task with timeout.
     * <p>
     * 执行带有超时的任务。
     *
     * @param <T>        the result type of the task
     *                   <p>
     *                   任务的结果类型
     * @param supplier   the task supplier
     *                   <p>
     *                   任务提供者
     * @param timeout    the timeout duration
     *                   <p>
     *                   超时持续时间
     * @param unit       the time unit of the timeout
     *                   <p>
     *                   超时的时间单位
     * @param defaultVal the default value to return if timeout occurs
     *                   <p>
     *                   如果超时发生则返回的默认值
     * @return the result of the task, or the default value if timeout occurs
     *         <p>
     *         任务的结果，如果超时发生则返回默认值
     */
    public static <T> T executeWithTimeout(Supplier<T> supplier, long timeout, TimeUnit unit, T defaultVal) {
        if (supplier == null) {
            return defaultVal;
        }
        ExecutorService executor = newSingleThreadExecutor();
        try {
            Future<T> future = executor.submit(() -> supplier.get());
            return future.get(timeout, unit);
        } catch (Exception e) {
            return defaultVal;
        } finally {
            shutdown(executor);
        }
    }
}