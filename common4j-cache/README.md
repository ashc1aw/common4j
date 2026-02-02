# Common4J Cache Module

A powerful, multi-level caching solution for Spring Boot applications, providing seamless integration with Caffeine local cache and Redisson distributed cache.

## 🚀 Features

### Multi-Level Caching
- **Local Cache**: High-performance Caffeine cache for in-memory storage
- **Distributed Cache**: Redis-based Redisson cache for cluster environments
- **Smart Synchronization**: Automatic synchronization between local and distributed caches

### Cache Protection Mechanisms
- **Cache Penetration Protection**: Null value caching to prevent malicious queries
- **Cache Breakdown Protection**: Distributed locks for hotspot data protection
- **Cache Avalanche Protection**: Randomized expiration times to avoid simultaneous cache failures

### Distributed Locking
- **Reentrant Locks**: Support for reentrant distributed locks
- **Fair Locks**: Fair lock implementation for balanced resource allocation
- **Read-Write Locks**: Optimized read-write lock support
- **RedLock**: Multi-node lock implementation for high availability

### Spring Boot Integration
- **Auto-Configuration**: Zero-configuration setup with Spring Boot
- **Annotation-Driven**: Comprehensive caching annotations
- **Property-Based Configuration**: Flexible YAML/properties configuration

## 📦 Installation

### Maven
```xml
<dependency>
    <groupId>cc.ashclaw</groupId>
    <artifactId>common4j-cache</artifactId>
    <version>1.1.0</version>
</dependency>
```

### Gradle
```gradle
implementation 'cc.ashclaw:common4j-cache:1.1.0'
```

## ⚙️ Configuration

### Basic Configuration
```yaml
common4j:
  cache:
    enabled: true
    type: MULTI_LEVEL
    expire-time: 1h
    protection-enabled: true
    
    # Redisson configuration
    redisson:
      address: redis://localhost:6379
      password: 
      database: 0
      
    # Caffeine configuration
    caffeine:
      initial-capacity: 100
      maximum-size: 1000
      expire-after-write: 1h
      expire-after-access: 30m
      
    # Multi-level cache configuration
    multi-level:
      enabled: true
      local-expire-time: 10m
      sync-enabled: true
      sync-delay: 1s
```

### Cache Protection Configuration
```yaml
common4j:
  cache:
    penetration-expire-time: 5m      # Null value expiration
    breakdown-wait-time: 30s         # Lock wait time
    avalanche-random-range: 10       # Expiration time random range (%)
```

## 🎯 Usage Examples

### Basic Caching
```java
@Service
public class UserService {
    
    @Cacheable(cacheNames = "user", key = "#id", expire = 3600)
    public User getUserById(Long id) {
        return userRepository.findById(id);
    }
    
    @CacheEvict(cacheNames = "user", key = "#user.id")
    public void updateUser(User user) {
        userRepository.update(user);
    }
    
    @CachePut(cacheNames = "user", key = "#user.id")
    public User createUser(User user) {
        return userRepository.save(user);
    }
}
```

### Distributed Locking
```java
@Service
public class OrderService {
    
    @CacheLock(key = "#orderId", waitTime = 10, leaseTime = 30)
    public void processOrder(Long orderId) {
        // Critical section protected by distributed lock
        orderProcessor.process(orderId);
    }
    
    @CacheLock(key = "#productId", type = LockType.READ)
    public Product getProductDetails(Long productId) {
        // Read lock for concurrent reads
        return productRepository.findById(productId);
    }
}
```

### Cache Preheating
```java
@Service
public class ProductService {
    
    @CachePreheat(cacheNames = "product", key = "#id", async = true)
    public Product preheatProduct(Long id) {
        // This method will be executed on application startup
        // to preload hot data into cache
        return productRepository.findById(id);
    }
}
```

### Programmatic Cache Usage
```java
@Service
public class CacheService {
    
    @Autowired
    private CacheUtil cacheUtil;
    
    public void manualCacheOperations() {
        // Put value in cache
        cacheUtil.put("myCache", "key1", "value1");
        
        // Get value from cache
        String value = cacheUtil.get("myCache", "key1", String.class);
        
        // Remove value from cache
        cacheUtil.evict("myCache", "key1");
        
        // Clear entire cache
        cacheUtil.clear("myCache");
    }
}
```

## 🔧 Advanced Features

### Custom Cache Configuration
```java
@Configuration
public class CacheConfig {
    
    @Bean
    public CacheManager customCacheManager(RedissonClient redissonClient, 
                                         CacheProperties cacheProperties) {
        // Create custom cache manager with specific settings
        return new RedissonCacheManager(redissonClient, cacheProperties);
    }
}
```

### Cache Event Listeners
```java
@Component
public class CacheEventListener {
    
    @EventListener
    public void handleCacheEvent(CacheEvent event) {
        // Handle cache events (put, get, evict, clear)
        System.out.println("Cache event: " + event.getOperation());
    }
}
```

### Performance Monitoring
```java
@Service
public class CacheMonitor {
    
    @Autowired
    private CacheManager cacheManager;
    
    public void monitorCachePerformance() {
        Cache cache = cacheManager.getCache("user");
        
        // Get cache statistics
        long size = cache.size();
        System.out.println("Cache size: " + size);
        
        // Check if key exists
        boolean exists = cacheUtil.hasKey("user", "key1");
        System.out.println("Key exists: " + exists);
    }
}
```

## 🛡️ Cache Protection Examples

### Penetration Protection
```java
@Service
public class ProductService {
    
    @Cacheable(cacheNames = "product", 
               key = "#id", 
               penetrationProtection = true)
    public Product getProduct(Long id) {
        // If product not found, null value will be cached for 5 minutes
        return productRepository.findById(id);
    }
}
```

### Breakdown Protection
```java
@Service
public class HotspotService {
    
    @Cacheable(cacheNames = "hotspot", 
               key = "#hotspotId", 
               breakdownProtection = true)
    public HotspotData getHotspotData(String hotspotId) {
        // Distributed lock will protect this method from cache breakdown
        return hotspotRepository.findByHotspotId(hotspotId);
    }
}
```

### Avalanche Protection
```java
@Service
public class ConfigService {
    
    @Cacheable(cacheNames = "config", 
               key = "#configKey", 
               avalancheProtection = true)
    public Config getConfig(String configKey) {
        // Expiration time will be randomized to prevent cache avalanche
        return configRepository.findByKey(configKey);
    }
}
```

## 📊 Performance Considerations

### Cache Hierarchy Performance
```
Local Cache (Caffeine) → Multi-Level Cache → Distributed Cache (Redisson)
    ↑                    ↑                    ↑
Fastest              Balanced              Distributed
```

### Recommended Settings
- **Small Objects**: Use local cache for best performance
- **Frequently Accessed Data**: Enable multi-level caching
- **Large Objects**: Consider distributed cache only
- **Critical Data**: Enable all protection mechanisms

## 🔍 Troubleshooting

### Common Issues

#### Cache Not Working
- Check if Redis server is running
- Verify cache configuration in application.yml
- Ensure @EnableCaching is present

#### Performance Issues
- Monitor Redis connection pool
- Check cache hit ratio
- Adjust cache expiration times

#### Memory Issues
- Reduce maximum cache size
- Use appropriate eviction policies
- Monitor heap usage

### Logging Configuration
```yaml
logging:
  level:
    cc.ashclaw.common4j.cache: DEBUG
    org.redisson: INFO
    com.github.benmanes.caffeine: INFO
```

## 🤝 Contributing

We welcome contributions! Please see our [Contributing Guide](CONTRIBUTING.md) for details.

## 📄 License

This project is licensed under the Apache License 2.0 - see the [LICENSE](LICENSE) file for details.

## 🏆 Acknowledgments

- [Spring Boot](https://spring.io/projects/spring-boot) - For excellent framework support
- [Caffeine](https://github.com/ben-manes/caffeine) - For high-performance local caching
- [Redisson](https://github.com/redisson/redisson) - For robust distributed caching

## 📞 Support

- **Issues**: [GitHub Issues](https://github.com/ashclaw/common4j/issues)
- **Documentation**: [Common4J Docs](https://ashclaw.cc/docs/common4j)
- **Email**: opensource@ashclaw.cc

---

**Common4J Cache Module** - Your reliable caching solution for modern Java applications. 🚀