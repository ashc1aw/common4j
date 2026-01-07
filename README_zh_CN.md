# 🚀 common4j - 基于 JDK 25 的 Java 工具库

## 🌐 语言切换

🇨🇳 [中文](./README_zh_CN.md) | 🇺🇸 [英文](./README.md)

## 📖 项目简介

common4j 是一个基于 JDK 25 构建的轻量级、高性能 Java 工具库，为日常开发提供简洁实用的工具类与函数，帮助开发者提升编码效率与代码质量。

## ✨ 核心特性

- **现代 Java**：充分利用 JDK 25+ 的新特性
- **轻量无依赖**：纯粹的工具集合，不引入第三方依赖
- **高性能**：注重工具方法的执行效率
- **开箱即用**：API 设计简洁，学习成本低

## 🛠️ 安装与使用

### Maven (待发布)

项目正式发布后，在 pom.xml 中添加以下依赖：

```xml
<dependency>
    <groupId>cc.ashclaw</groupId>
    <artifactId>common4j</artifactId>
    <version>1.0.0</version>
</dependency>
```

### 源码构建

```bash
# 1. 克隆项目
git clone https://github.com/ashclaw/common4j.git
# 2. 进入项目目录
cd common4j
# 3. 编译打包
mvn clean package
# 4. 安装到本地仓库
mvn clean install
```

## 🤝 贡献指南

我们非常欢迎并感谢所有形式的贡献！无论是报告 Bug、提出新功能建议，还是提交代码改进，都是对项目的宝贵支持。

### 贡献流程

1. **Fork 仓库**：在 GitHub 上 Fork 本项目
2. **创建分支**：
   ```bash
   git checkout -b feature/your-feature-name
   # 或
   git checkout -b fix/your-bug-fix
   ```
3. **提交更改**：
   ```bash
   git add .
   git commit -m "feat: 添加xxx功能"  # 或 fix:, docs:, style:, refactor:, test:
   ```
4. **推送分支**：
   ```bash
   git push origin feature/your-feature-name
   ```
5. **创建 PR**：前往 GitHub 仓库页面，点击 "New Pull Request"，选择您的分支与目标分支（通常是 main），填写清晰的 PR 描述

### 代码规范

- 遵循 Java 官方编码规范
- 使用 JDK 25 语法特性
- 所有公共 API 需添加 Javadoc 注释
- 新功能必须包含单元测试
- 确保所有现有测试通过（mvn test）

### 提交信息规范

采用 Conventional Commits 规范：

- `feat`：新功能
- `fix`：Bug 修复
- `docs`：文档更新
- `style`：代码格式调整
- `refactor`：重构代码
- `test`：测试相关
- `chore`：构建过程或辅助工具变动

## 📅 项目路线图

### 短期目标 (1-3 个月)

- 完成核心工具模块 (common4j-core) 基础功能
- 实现 50+ 个常用工具方法
- 单元测试覆盖率 ≥ 90%
- 编写完整的 API 文档与使用示例
- 发布首个预览版本 (v0.1.0)

### 中期目标 (3-6 个月)

- 发布首个稳定版本 (v1.0.0)
- 其他模块开发
- 完善国际化支持
- 建立持续集成/持续部署 (CI/CD) 流程
- 提交至 Maven 中央仓库

### 长期目标 (6-12 个月)

- 开发配套工具链与插件
- 拓展企业级应用场景支持
- 建立活跃的开发者社区
- 达到 1000+ GitHub Stars

## 💬 问题与反馈

如果您遇到任何问题或有改进建议，请通过以下方式反馈：

- **GitHub Issues**：报告 Bug 或提出功能请求
- **讨论区**：参与功能设计与技术讨论

## 📜 开源协议

[Apache License 2.0](http://www.apache.org/licenses/LICENSE-2.0)

## 🎉 支持项目

如果您觉得这个项目对您有帮助，请考虑：

- 在 GitHub 上给项目点个 ⭐
- 向您的同事和朋友推荐
- 参与代码贡献或文档改进
- 分享您在使用中的经验案例

感谢您对 common4j 的关注与支持！
