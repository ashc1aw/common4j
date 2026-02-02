# Common4J 缓存模块

一个强大的多级缓存解决方案，专为Spring Boot应用设计，提供Caffeine本地缓存和Redisson分布式缓存的无缝集成。

## 🚀 功能特性

### 多级缓存
- **本地缓存**：基于Caffeine的高性能内存缓存
- **分布式缓存**：基于Redis的Redisson集群缓存
- **智能同步**：本地缓存与分布式缓存自动同步

### 缓存保护机制
- **缓存穿透保护**：空值缓存防止恶意查询
- **缓存击穿保护**：分布式锁保护热点数据
- **缓存雪崩保护**：随机过期时间避免集中失效

### 分布式锁
- **可重入锁**：支持可重入的分布式锁
- **公平锁**：公平锁实现，平衡资源分配
- **读写锁**：优化的读写锁支持
- **红锁**：多节点锁实现，保证高可用性

### Spring Boot集成
- **自动配置**：Spring Boot零配置开箱即用
- **注解驱动**：完整的缓存注解支持
- **属性配置**：灵活的YAML/属性文件配置

## 📦 安装指南

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

## ⚙️ 配置说明

### 基础配置
```yaml
common4j:
  cache:
    enabled: true              # 启用缓存
    type: MULTI_LEVEL         # 缓存类型：LOCAL、DISTRIBUTED、MULTI_LEVEL
    expire-time: 1h           # 默认过期时间
    protection-enabled: true  # 启用缓存保护
    
    # Redisson配置
    redisson:
      address: redis://localhost:6379  # Redis服务器地址
      password:                        # Redis密码
      database: 0                      # 数据库索引
      
    # Caffeine配置
    caffeine:
      initial-capacity: 100    # 初始容量
      maximum-size: 1000       # 最大容量
      expire-after-write: 1h   # 写入后过期时间
      expire-after-access: 30m # 访问后过期时间
      
    # 多级缓存配置
    multi-level:
      enabled: true            # 启用多级缓存
      local-expire-time: 10m  # 本地缓存过期时间
      sync-enabled: true       # 启用同步
      sync-delay: 1s           # 同步延迟时间
```

### 缓存保护配置
```yaml
common4j:
  cache:
    penetration-expire-time: 5m    # 空值缓存过期时间
    breakdown-wait-time: 30s       # 锁等待时间
    avalanche-random-range: 10     # 过期时间随机范围（百分比）
```

## 🎯 使用示例

### 基础缓存使用
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

### 分布式锁使用
```java
@Service
public class OrderService {
    
    @CacheLock(key = "#orderId", waitTime = 10, leaseTime = 30)
    public void processOrder(Long orderId) {
        // 受分布式锁保护的临界区
        orderProcessor.process(orderId);
    }
    
    @CacheLock(key = "#productId", type = LockType.READ)
    public Product getProductDetails(Long productId) {
        // 读锁支持并发读取
        return productRepository.findById(productId);
    }
}
```

### 缓存预热
```java
@Service
public class ProductService {
    
    @CachePreheat(cacheNames = "product", key = "#id", async = true)
    public Product preheatProduct(Long id) {
        // 应用启动时执行，预加载热点数据到缓存
        return productRepository.findById(id);
    }
}
```

### 编程式缓存操作
```java
@Service
public class CacheService {
    
    @Autowired
    private CacheUtil cacheUtil;
    
    public void manualCacheOperations() {
        // 向缓存中放入值
        cacheUtil.put("myCache", "key1", "value1");
        
        // 从缓存中获取值
        String value = cacheUtil.get("myCache", "key1", String.class);
        
        // 从缓存中移除值
        cacheUtil.evict("myCache", "key1");
        
        // 清空整个缓存
        cacheUtil.clear("myCache");
    }
}
```

## 🔧 高级功能

### 自定义缓存配置
```java
@Configuration
public class CacheConfig {
    
    @Bean
    public CacheManager customCacheManager(RedissonClient redissonClient, 
                                         CacheProperties cacheProperties) {
        // 创建具有特定设置的自定义缓存管理器
        return new RedissonCacheManager(redissonClient, cacheProperties);
    }
}
```

### 缓存事件监听
```java
@Component
public class CacheEventListener {
    
    @EventListener
    public void handleCacheEvent(CacheEvent event) {
        // 处理缓存事件（放入、获取、移除、清空）
        System.out.println("缓存事件: " + event.getOperation());
    }
}
```

### 性能监控
```java
@Service
public class CacheMonitor {
    
    @Autowired
    private CacheManager cacheManager;
    
    public void monitorCachePerformance() {
        Cache cache = cacheManager.getCache("user");
        
        // 获取缓存统计信息
        long size = cache.size();
        System.out.println("缓存大小: " + size);
        
        // 检查键是否存在
        boolean exists = cacheUtil.hasKey("user", "key1");
        System.out.println("键是否存在: " + exists);
    }
}
```

## 🛡️ 缓存保护示例

### 穿透保护
```java
@Service
public class ProductService {
    
    @Cacheable(cacheNames = "product", 
               key = "#id", 
               penetrationProtection = true)
    public Product getProduct(Long id) {
        // 如果产品未找到，空值将被缓存5分钟
        return productRepository.findById(id);
    }
}
```

### 击穿保护
```java
@Service
public class HotspotService {
    
    @Cacheable(cacheNames = "hotspot", 
               key = "#hotspotId", 
               breakdownProtection = true)
    public HotspotData getHotspotData(String hotspotId) {
        // 分布式锁将保护此方法免受缓存击穿影响
        return hotspotRepository.findByHotspotId(hotspotId);
    }
}
```

### 雪崩保护
```java
@Service
public class ConfigService {
    
    @Cacheable(cacheNames = "config", 
               key = "#configKey", 
               avalancheProtection = true)
    public Config getConfig(String configKey) {
        // 过期时间将被随机化以防止缓存雪崩
        return configRepository.findByKey(configKey);
    }
}
```

## 📊 性能考虑

### 缓存层次性能
```
本地缓存 (Caffeine) → 多级缓存 → 分布式缓存 (Redisson)
    ↑                    ↑                    ↑
最快                平衡性能             分布式一致性
```

### 推荐设置
- **小对象**：使用本地缓存获得最佳性能
- **频繁访问数据**：启用多级缓存
- **大对象**：仅考虑分布式缓存
- **关键数据**：启用所有保护机制

## 🔍 故障排除

### 常见问题

#### 缓存不工作
- 检查Redis服务器是否运行
- 验证application.yml中的缓存配置
- 确保@EnableCaching注解存在

#### 性能问题
- 监控Redis连接池
- 检查缓存命中率
- 调整缓存过期时间

#### 内存问题
- 减少最大缓存大小
- 使用适当的淘汰策略
- 监控堆内存使用情况

### 日志配置
```yaml
logging:
  level:
    cc.ashclaw.common4j.cache: DEBUG
    org.redisson: INFO
    com.github.benmanes.caffeine: INFO
```

## 🤝 贡献指南

我们欢迎贡献！请查看我们的[贡献指南](CONTRIBUTING.md)了解详情。

## 📄 许可证

本项目基于Apache许可证2.0 - 查看[LICENSE](LICENSE)文件了解详情。

## 🏆 致谢

- [Spring Boot](https://spring.io/projects/spring-boot) - 优秀的框架支持
- [Caffeine](https://github.com/ben-manes/caffeine) - 高性能本地缓存
- [Redisson](https://github.com/redisson/redisson) - 强大的分布式缓存

## 📞 支持

- **问题反馈**：[GitHub Issues](https://github.com/ashclaw/common4j/issues)
- **文档**：[Common4J 文档](https://ashclaw.cc/docs/common4j)
- **邮箱**：opensource@ashclaw.cc

---

**Common4J 缓存模块** - 为现代Java应用提供可靠的缓存解决方案。🚀