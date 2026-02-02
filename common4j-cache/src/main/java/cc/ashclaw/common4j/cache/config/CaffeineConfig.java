package cc.ashclaw.common4j.cache.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * Configuration class for Caffeine cache builder.
 * <p>
 * Caffeine缓存构建器的配置类。
 * <p>
 * This configuration class provides a Caffeine cache builder bean
 * configured with properties from the cache configuration.
 * <p>
 * 此配置类提供一个Caffeine缓存构建器bean，使用缓存配置中的属性进行配置。
 *
 * @author b1itz7
 * @since 1.1.0
 */
@Configuration
@ConditionalOnClass(Caffeine.class)
@ConditionalOnProperty(name = "common4j.cache.enabled", havingValue = "true")
public class CaffeineConfig {
    
    /**
     * Creates a Caffeine cache builder configured with cache properties.
     * <p>
     * 创建一个使用缓存属性配置的Caffeine缓存构建器。
     *
     * @param cacheProperties the cache properties configuration
     *                        <p>
     *                        缓存属性配置
     * @return the configured Caffeine cache builder
     *         <p>
     *         配置好的Caffeine缓存构建器
     */
    @Bean
    public Caffeine<Object, Object> caffeineBuilder(CacheProperties cacheProperties) {
        CacheProperties.CaffeineProperties caffeineProps = cacheProperties.getCaffeine();
        
        Caffeine<Object, Object> builder = Caffeine.newBuilder()
                .initialCapacity(caffeineProps.getInitialCapacity())
                .maximumSize(caffeineProps.getMaximumSize());
        
        if (caffeineProps.getExpireAfterWrite() != null) {
            builder.expireAfterWrite(caffeineProps.getExpireAfterWrite().toMillis(), TimeUnit.MILLISECONDS);
        }
        
        if (caffeineProps.getExpireAfterAccess() != null) {
            builder.expireAfterAccess(caffeineProps.getExpireAfterAccess().toMillis(), TimeUnit.MILLISECONDS);
        }
        
        if (caffeineProps.isStatsEnabled()) {
            builder.recordStats();
        }
        
        return builder;
    }
}