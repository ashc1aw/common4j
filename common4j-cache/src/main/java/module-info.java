/**
 * Module definition for the Common4J Cache library.
 * <p>
 * Common4J 缓存库的模块定义。
 * <p>
 * This module provides caching capabilities including local caching with Caffeine,
 * distributed caching with Redisson, and multi-level caching strategies.
 * <p>
 * 此模块提供缓存功能，包括使用Caffeine的本地缓存、使用Redisson的分布式缓存以及多级缓存策略。
 *
 * @author b1itz7
 * @since 1.1.0
 */
module common4j.cache {
    requires spring.context;
    requires spring.boot.autoconfigure;
    requires com.github.benmanes.caffeine;
    requires spring.boot;
    requires redisson;
    requires spring.core;
    requires org.aspectj.runtime;
    requires spring.beans;
    requires spring.expression;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires static lombok;
}