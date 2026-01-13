# 🚀 common4j-core - Core Module of common4j

## 🌐 Language

🇨🇳 [中文](./README_zh_CN.md) | 🇺🇸 [English](./README.md)

## 📖 Module Introduction

common4j-core is the core module of the common4j library, providing a comprehensive set of utility classes and functions that form the foundation of the library. Built on JDK 25, it offers essential utilities for daily Java development.

## ✨ Core Features

- **Rich utility collections**: Covers a wide range of common development scenarios
- **Modern Java features**: Fully leverages JDK 25+ capabilities
- **Zero dependencies**: Pure Java implementation without third-party libraries
- **High performance**: Optimized for efficiency and speed
- **Easy to use**: Clean and intuitive API design

## 📦 Included Utility Categories

### Basic Utilities
- `util.StringUtils`: String manipulation and validation
- `util.NumberUtil`: Numeric operations and conversions
- `util.IdUtil`: ID generation utilities
- `util.HexUtil`: Hexadecimal conversion utilities
- `util.TreeUtil`: Tree structure operations

### Language Extensions
- `lang.ArrayUtil`: Array manipulation utilities
- `lang.ClassUtil`: Class reflection utilities
- `lang.ObjectUtil`: Object operations and validation
- `lang.Pair`: Pair data structure
- `lang.Tuple`: Tuple data structures

### Date and Time
- `date.DateUtils`: Date operations and conversions
- `date.TimeUtils`: Time manipulation utilities
- `date.DateTimeUtils`: Date and time combined utilities
- `date.DateTimeFormatters`: Date and time formatting
- `date.DateBetween`: Date interval calculations
- `constant.DateFormats`: Common date format constants

### Exception Handling
- `exception.BaseException`: Base exception class
- `exception.CloneException`: Clone-related exceptions
- `exception.ServiceException`: Service layer exceptions
- `exception.file.*`: File operation exceptions

### Thread and Concurrency
- `thread.ThreadUtil`: Thread manipulation utilities
- `thread.ThreadPoolUtil`: Thread pool management

### Reflection
- `reflect.ReflectUtil`: Reflection utilities

### Cloning
- `clone.CloneUtil`: Object cloning utilities

### Internationalization
- `i18n.I18nUtil`: Internationalization support

### Constants
- `constant.*`: Various constants (date formats, regex patterns, separators, etc.)

### Enums
- `enums.HttpStatus`: HTTP status codes
- `enums.FileResultCode`: File operation result codes
- `enums.ResultCode`: General result codes

## 🛠️ Installation and Usage

### Maven (Coming Soon)

After the project is officially released, add the following dependency to your pom.xml:

```xml
<dependency>
    <groupId>cc.ashclaw</groupId>
    <artifactId>common4j-core</artifactId>
    <version>1.0.0</version>
</dependency>
```

### Build from Source

```bash
# 1. Clone the project
git clone https://github.com/ashclaw/common4j.git
# 2. Enter project directory
cd common4j
# 3. Compile and package core module
mvn clean package -pl common4j-core -am
# 4. Install to local repository
mvn clean install -pl common4j-core -am
```

## 📝 Usage Examples

### StringUtils
```java
// Check if string is blank
boolean isBlank = StringUtils.isBlank("   "); // true

// Join strings with separator
String joined = StringUtils.join(", ", "apple", "banana", "cherry"); // "apple, banana, cherry"
```

### DateUtils
```java
// Get current date
LocalDate today = DateUtils.today();

// Add days to date
LocalDate nextWeek = DateUtils.plusDays(today, 7);
```

### IdUtil
```java
// Generate UUID
String uuid = IdUtil.uuid();

// Generate Snowflake ID
long snowflakeId = IdUtil.snowflake().nextId();
```

## 🤝 Contribution Guide

Please refer to the [main project's contribution guide](../README.md#🤝-contribution-guide) for information on how to contribute to the core module.

## 📜 License

[Apache License 2.0](http://www.apache.org/licenses/LICENSE-2.0)

## 🎉 Support the Project

If you find this module helpful, please consider:

- Giving the project a ⭐ on GitHub
- Recommending it to your colleagues and friends
- Participating in code contributions or documentation improvements

Thank you for your attention and support to common4j-core!