// SPDX-License-Identifier: Apache-2.0
// Copyright (c) 2026 AshClaw.

package cc.ashclaw.common4j.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marks a class, method, or field as deprecated and not recommended for continued use.
 * <p>
 * 标记类、方法或字段已过时，不建议继续使用。
 * <p>
 * This annotation is an extension of the Java built-in {@code @Deprecated} annotation,
 * providing more detailed deprecation information including the reason for deprecation,
 * suggested alternatives, and planned removal version.
 * <p>
 * 该注解是Java内置{@code @Deprecated}注解的扩展，
 * 提供了更详细的过时信息，包括过时的原因、建议的替代方案和计划移除的版本。
 * <p>
 * Example usage:
 * <pre>
 * {@code @Deprecated}
 * public class OldUtil {
 *     // Deprecated class
 * }
 * 
 * public class NewUtil {
 *     {@code @Deprecated(reason = "Use new method instead", replacement = "newMethod", since = "1.0.0", forRemoval = true)}
 *     public void oldMethod() {
 *         // Deprecated method
 *     }
 *     
 *     public void newMethod() {
 *         // Replacement method
 *     }
 * }
 * </pre>
 * <p>
 * 示例用法：
 * <pre>
 * {@code @Deprecated}
 * public class OldUtil {
 *     // 过时的类
 * }
 * 
 * public class NewUtil {
 *     {@code @Deprecated(reason = "使用新的方法代替", replacement = "newMethod", since = "1.0.0", forRemoval = true)}
 *     public void oldMethod() {
 *         // 过时的方法
 *     }
 *     
 *     public void newMethod() {
 *         // 替代方法
 *     }
 * }
 * </pre>
 *
 * @author b1itz7
 * @since 1.0.0
 */
@Documented
@Retention(RetentionPolicy.CLASS)
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.FIELD, ElementType.CONSTRUCTOR})
public @interface Deprecated {
    /**
     * The reason for deprecation.
     * <p>
     * 过时的原因。
     *
     * @return The reason for deprecation
     */
    String reason() default "";
    
    /**
     * Suggested replacement, typically a method or class name.
     * <p>
     * 建议的替代方案，通常是方法名或类名。
     *
     * @return Suggested replacement
     */
    String replacement() default "";
    
    /**
     * The version since when this element was deprecated.
     * <p>
     * 从哪个版本开始过时。
     *
     * @return The version since deprecation
     */
    String since() default "";
    
    /**
     * Whether this element is planned for removal in a future version.
     * <p>
     * 是否计划在未来版本中移除。
     *
     * @return {@code true} if planned for removal, {@code false} otherwise
     */
    boolean forRemoval() default false;
}
