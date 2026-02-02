package cc.ashclaw.common4j.cache.config;

import cc.ashclaw.common4j.cache.core.Cache;
import cc.ashclaw.common4j.cache.core.CacheManager;
import cc.ashclaw.common4j.cache.enums.CacheType;
import cc.ashclaw.common4j.cache.impl.caffeine.CaffeineCacheManager;
import cc.ashclaw.common4j.cache.impl.multilevel.MultiLevelCacheImpl;
import cc.ashclaw.common4j.cache.impl.redisson.RedissonCacheManager;
import cc.ashclaw.common4j.cache.support.CacheKeyGenerator;
import cc.ashclaw.common4j.cache.support.CacheSerializer;
import cc.ashclaw.common4j.cache.support.ExpressionEvaluator;
import org.redisson.api.RedissonClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Spring Boot auto-configuration class for cache components.
 * <p>
 * 缓存组件的Spring Boot自动配置类。
 * <p>
 * This configuration class provides automatic setup for cache-related beans
 * including cache managers, key generators, serializers, and expression evaluators.
 * It supports multiple cache types including Redisson, Caffeine, and multi-level caching.
 * <p>
 * 此配置类为缓存相关bean提供自动设置，包括缓存管理器、键生成器、序列化器和表达式评估器。
 * 它支持多种缓存类型，包括Redisson、Caffeine和多级缓存。
 *
 * @author b1itz7
 * @since 1.1.0
 */
@Configuration
@EnableConfigurationProperties(CacheProperties.class)
@ConditionalOnProperty(name = "common4j.cache.enabled", havingValue = "true", matchIfMissing = true)
public class CacheAutoConfiguration {
    
    /**
     * Creates a cache key generator bean if one is not already defined.
     * <p>
     * 如果尚未定义缓存键生成器，则创建一个缓存键生成器bean。
     *
     * @return the cache key generator instance
     *         <p>
     *         缓存键生成器实例
     */
    @Bean
    @ConditionalOnMissingBean
    public CacheKeyGenerator cacheKeyGenerator() {
        return new CacheKeyGenerator();
    }
    
    /**
     * Creates a cache serializer bean if one is not already defined.
     * <p>
     * 如果尚未定义缓存序列化器，则创建一个缓存序列化器bean。
     *
     * @return the cache serializer instance
     *         <p>
     *         缓存序列化器实例
     */
    @Bean
    @ConditionalOnMissingBean
    public CacheSerializer cacheSerializer() {
        return new CacheSerializer();
    }
    
    /**
     * Creates an expression evaluator bean if one is not already defined.
     * <p>
     * 如果尚未定义表达式评估器，则创建一个表达式评估器bean。
     *
     * @return the expression evaluator instance
     *         <p>
     *         表达式评估器实例
     */
    @Bean
    @ConditionalOnMissingBean
    public ExpressionEvaluator expressionEvaluator() {
        return new ExpressionEvaluator();
    }
    
    /**
     * Creates a Redisson cache manager bean if Redisson client is available
     * and no redisson cache manager is already defined.
     * <p>
     * 如果Redisson客户端可用且尚未定义redisson缓存管理器，则创建一个Redisson缓存管理器bean。
     *
     * @param redissonClient the Redisson client instance
     *                       <p>
     *                       Redisson客户端实例
     * @param cacheProperties the cache properties configuration
     *                        <p>
     *                        缓存属性配置
     * @return the Redisson cache manager instance
     *         <p>
     *         Redisson缓存管理器实例
     */
    @Bean
    @ConditionalOnBean(RedissonClient.class)
    @ConditionalOnMissingBean(name = "redissonCacheManager")
    public CacheManager redissonCacheManager(RedissonClient redissonClient, CacheProperties cacheProperties) {
        return new RedissonCacheManager(redissonClient, cacheProperties);
    }
    
    /**
     * Creates a Caffeine cache manager bean if Caffeine library is available
     * and no caffeine cache manager is already defined.
     * <p>
     * 如果Caffeine库可用且尚未定义caffeine缓存管理器，则创建一个Caffeine缓存管理器bean。
     *
     * @param cacheProperties the cache properties configuration
     *                        <p>
     *                        缓存属性配置
     * @return the Caffeine cache manager instance
     *         <p>
     *         Caffeine缓存管理器实例
     */
    @Bean
    @ConditionalOnClass(name = "com.github.benmanes.caffeine.cache.Caffeine")
    @ConditionalOnMissingBean(name = "caffeineCacheManager")
    public CacheManager caffeineCacheManager(CacheProperties cacheProperties) {
        return new CaffeineCacheManager(cacheProperties);
    }
    
    /**
     * Creates a multi-level cache manager bean as the primary cache manager
     * if both Redisson client and Caffeine library are available.
     * <p>
     * 如果Redisson客户端和Caffeine库都可用，则创建一个多级缓存管理器bean作为主缓存管理器。
     *
     * @param redissonCacheManager the Redisson cache manager instance
     *                            <p>
     *                            Redisson缓存管理器实例
     * @param caffeineCacheManager the Caffeine cache manager instance
     *                            <p>
     *                            Caffeine缓存管理器实例
     * @param cacheProperties the cache properties configuration
     *                        <p>
     *                        缓存属性配置
     * @return the multi-level cache manager instance
     *         <p>
     *         多级缓存管理器实例
     */
    @Bean
    @Primary
    @ConditionalOnBean({RedissonClient.class})
    @ConditionalOnClass(name = "com.github.benmanes.caffeine.cache.Caffeine")
    @ConditionalOnMissingBean(name = "multiLevelCacheManager")
    public CacheManager multiLevelCacheManager(
            CacheManager redissonCacheManager,
            CacheManager caffeineCacheManager,
            CacheProperties cacheProperties) {
        return new CacheManager() {
            @Override
            public Cache getCache(String name) {
                return new MultiLevelCacheImpl(
                        caffeineCacheManager.getCache(name),
                        redissonCacheManager.getCache(name),
                        cacheProperties
                );
            }
            
            @Override
            public CacheType getCacheType() {
                return CacheType.MULTI_LEVEL;
            }
            
            @Override
            public Collection<String> getCacheNames() {
                // 合并两个缓存管理器的缓存名称
                Set<String> cacheNames = new HashSet<>();
                cacheNames.addAll(caffeineCacheManager.getCacheNames());
                cacheNames.addAll(redissonCacheManager.getCacheNames());
                return cacheNames;
            }
            
            @Override
            public Cache createCache(String name) {
                return new MultiLevelCacheImpl(
                        caffeineCacheManager.createCache(name),
                        redissonCacheManager.createCache(name),
                        cacheProperties
                );
            }
            
            @Override
            public void destroyCache(String name) {
                caffeineCacheManager.destroyCache(name);
                redissonCacheManager.destroyCache(name);
            }
        };
    }
}