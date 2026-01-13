# 🚀 common4j-core - common4j 的核心模块

## 🌐 语言切换

🇨🇳 [中文](./README_zh_CN.md) | 🇺🇸 [英文](./README.md)

## 📖 模块简介

common4j-core 是 common4j 库的核心模块，提供了一套全面的工具类和函数，构成了整个库的基础。基于 JDK 25 构建，它为日常 Java 开发提供了必要的实用工具。

## ✨ 核心特性

- **丰富的工具集合**：涵盖了广泛的常见开发场景
- **现代 Java 特性**：充分利用 JDK 25+ 的能力
- **零依赖**：纯 Java 实现，不依赖第三方库
- **高性能**：针对效率和速度进行了优化
- **易于使用**：简洁直观的 API 设计

## 📦 包含的工具类别

### 基础工具
- `util.StringUtils`：字符串操作和验证
- `util.NumberUtil`：数值运算和转换
- `util.IdUtil`：ID 生成工具
- `util.HexUtil`：十六进制转换工具
- `util.TreeUtil`：树结构操作

### 语言扩展
- `lang.ArrayUtil`：数组操作工具
- `lang.ClassUtil`：类反射工具
- `lang.ObjectUtil`：对象操作和验证
- `lang.Pair`：键值对数据结构
- `lang.Tuple`：元组数据结构

### 日期和时间
- `date.DateUtils`：日期操作和转换
- `date.TimeUtils`：时间操作工具
- `date.DateTimeUtils`：日期时间组合工具
- `date.DateTimeFormatters`：日期时间格式化
- `date.DateBetween`：日期区间计算
- `constant.DateFormats`：常用日期格式常量

### 异常处理
- `exception.BaseException`：基础异常类
- `exception.CloneException`：克隆相关异常
- `exception.ServiceException`：服务层异常
- `exception.file.*`：文件操作异常

### 线程和并发
- `thread.ThreadUtil`：线程操作工具
- `thread.ThreadPoolUtil`：线程池管理

### 反射
- `reflect.ReflectUtil`：反射工具

### 克隆
- `clone.CloneUtil`：对象克隆工具

### 国际化
- `i18n.I18nUtil`：国际化支持

### 常量
- `constant.*`：各种常量（日期格式、正则表达式、分隔符等）

### 枚举
- `enums.HttpStatus`：HTTP 状态码
- `enums.FileResultCode`：文件操作结果码
- `enums.ResultCode`：通用结果码

## 🛠️ 安装与使用

### Maven (待发布)

项目正式发布后，在 pom.xml 中添加以下依赖：

```xml
<dependency>
    <groupId>cc.ashclaw</groupId>
    <artifactId>common4j-core</artifactId>
    <version>1.0.0</version>
</dependency>
```

### 源码构建

```bash
# 1. 克隆项目
git clone https://github.com/ashclaw/common4j.git
# 2. 进入项目目录
cd common4j
# 3. 编译打包核心模块
mvn clean package -pl common4j-core -am
# 4. 安装到本地仓库
mvn clean install -pl common4j-core -am
```

## 📝 使用示例

### StringUtils
```java
// 检查字符串是否为空
boolean isBlank = StringUtils.isBlank("   "); // true

// 使用分隔符连接字符串
String joined = StringUtils.join(", ", "apple", "banana", "cherry"); // "apple, banana, cherry"
```

### DateUtils
```java
// 获取当前日期
LocalDate today = DateUtils.today();

// 向日期添加天数
LocalDate nextWeek = DateUtils.plusDays(today, 7);
```

### IdUtil
```java
// 生成 UUID
String uuid = IdUtil.uuid();

// 生成雪花 ID
long snowflakeId = IdUtil.snowflake().nextId();
```

## 🤝 贡献指南

请参考 [主项目的贡献指南](../README_zh_CN.md#🤝-贡献指南) 了解如何为核心模块做出贡献。

## 📜 开源协议

[Apache License 2.0](http://www.apache.org/licenses/LICENSE-2.0)

## 🎉 支持项目

如果您觉得这个模块对您有帮助，请考虑：

- 在 GitHub 上给项目点个 ⭐
- 向您的同事和朋友推荐
- 参与代码贡献或文档改进

感谢您对 common4j-core 的关注与支持！