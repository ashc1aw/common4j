// SPDX-License-Identifier: Apache-2.0
// Copyright (c) 2026 AshClaw.

package cc.ashclaw.common4j.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marks a class or method as thread-safe.
 * <p>
 * 标记类或方法是线程安全的。
 * <p>
 * This annotation is used to identify classes or methods that can be safely accessed by multiple threads
 * concurrently without requiring additional synchronization measures.
 * <p>
 * 该注解用于标识类或方法可以安全地被多个线程并发访问，
 * 不需要额外的同步措施。
 * <p>
 * Example usage:
 * <pre>
 * {@code @ThreadSafe}
 * public class ConcurrentCache {
 *     // Thread-safe implementation
 * }
 *
 * public class Counter {
 *     {@code @ThreadSafe}
 *     public synchronized void increment() {
 *         // Thread-safe method
 *     }
 * }
 * </pre>
 * <p>
 * 示例用法：
 * <pre>
 * {@code @ThreadSafe}
 * public class ConcurrentCache {
 *     // 线程安全的实现
 * }
 *
 * public class Counter {
 *     {@code @ThreadSafe}
 *     public synchronized void increment() {
 *         // 线程安全的方法
 *     }
 * }
 * </pre>
 *
 * @author b1itz7
 * @since 1.0.0
 */
@Documented
@Retention(RetentionPolicy.CLASS)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface ThreadSafe {
}
