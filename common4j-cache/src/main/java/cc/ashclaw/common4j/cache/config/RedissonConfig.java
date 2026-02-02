package cc.ashclaw.common4j.cache.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for Redisson client.
 * <p>
 * Redisson客户端的配置类。
 * <p>
 * This configuration class provides a Redisson client bean
 * configured with properties from the cache configuration.
 * <p>
 * 此配置类提供一个Redisson客户端bean，使用缓存配置中的属性进行配置。
 *
 * @author b1itz7
 * @since 1.1.0
 */
@Configuration
@ConditionalOnClass(RedissonClient.class)
@ConditionalOnProperty(name = "common4j.cache.enabled", havingValue = "true")
public class RedissonConfig {
    
    /**
     * Creates a Redisson client configured with cache properties.
     * <p>
     * 创建一个使用缓存属性配置的Redisson客户端。
     *
     * @param cacheProperties the cache properties configuration
     *                        <p>
     *                        缓存属性配置
     * @return the configured Redisson client
     *         <p>
     *         配置好的Redisson客户端
     */
    @Bean
    @ConditionalOnMissingBean(RedissonClient.class)
    public RedissonClient redissonClient(CacheProperties cacheProperties) {
        Config config = new Config();
        
        CacheProperties.RedissonProperties redissonProps = cacheProperties.getRedisson();
        
        SingleServerConfig singleServerConfig = config.useSingleServer()
                .setAddress(redissonProps.getAddress())
                .setDatabase(redissonProps.getDatabase())
                .setConnectTimeout((int) redissonProps.getConnectionTimeout().toMillis())
                .setTimeout((int) redissonProps.getCommandTimeout().toMillis());
        
        if (redissonProps.getPassword() != null && !redissonProps.getPassword().isEmpty()) {
            singleServerConfig.setPassword(redissonProps.getPassword());
        }
        
        return Redisson.create(config);
    }
}