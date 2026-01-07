# 🚀 common4j - Java Utility Library based on JDK 25

## 🌐 Language

🇨🇳 [中文](./README_zh_CN.md) | 🇺🇸 [English](./README.md)

## 📖 Project Introduction

common4j is a lightweight, high-performance Java utility library built on JDK 25, providing concise and practical utility classes and functions for daily development, helping developers improve coding efficiency and code quality.

## ✨ Core Features

- **Modern Java**: Fully utilize the new features of JDK 25+
- **Lightweight with no dependencies**: Pure utility collection without introducing third-party dependencies
- **High performance**: Focus on the execution efficiency of utility methods
- **Ready to use**: Clean API design with low learning curve

## 🛠️ Installation and Usage

### Maven (Coming Soon)

After the project is officially released, add the following dependency to your pom.xml:

```xml
<dependency>
    <groupId>cc.ashclaw</groupId>
    <artifactId>common4j</artifactId>
    <version>1.0.0</version>
</dependency>
```

### Build from Source

```bash
# 1. Clone the project
git clone https://github.com/ashclaw/common4j.git
# 2. Enter project directory
cd common4j
# 3. Compile and package
mvn clean package
# 4. Install to local repository
mvn clean install
```

## 🤝 Contribution Guide

We greatly welcome and appreciate all forms of contributions! Whether it's reporting bugs, suggesting new features, or submitting code improvements, they are all valuable support for the project.

### Contribution Process

1. **Fork the repository**: Fork this project on GitHub
2. **Create a branch**:
   ```bash
   git checkout -b feature/your-feature-name
   # or
   git checkout -b fix/your-bug-fix
   ```
3. **Commit changes**:
   ```bash
   git add .
   git commit -m "feat: add xxx feature"  # or fix:, docs:, style:, refactor:, test:
   ```
4. **Push the branch**:
   ```bash
   git push origin feature/your-feature-name
   ```
5. **Create PR**: Go to the GitHub repository page, click "New Pull Request", select your branch and target branch (usually main), and fill in a clear PR description

### Code Standards

- Follow official Java coding standards
- Use JDK 25 syntax features
- All public APIs must include Javadoc comments
- New features must include unit tests
- Ensure all existing tests pass (mvn test)

### Commit Message Standards

We follow the Conventional Commits standard:

- `feat`: New feature
- `fix`: Bug fix
- `docs`: Documentation update
- `style`: Code format adjustment
- `refactor`: Code refactoring
- `test`: Test-related
- `chore`: Build process or auxiliary tool changes

## 📅 Project Roadmap

### Short-term Goals (1-3 months)

- Complete basic functionality of core utility module (common4j-core)
- Implement 50+ common utility methods
- Unit test coverage ≥ 90%
- Write complete API documentation and usage examples
- Release first preview version (v0.1.0)

### Medium-term Goals (3-6 months)

- Release first stable version (v1.0.0)
- Develop other modules
- Improve internationalization support
- Establish continuous integration/continuous deployment (CI/CD) process
- Submit to Maven Central Repository

### Long-term Goals (6-12 months)

- Develop supporting toolchain and plugins
- Expand enterprise application scenario support
- Build an active developer community
- Reach 1000+ GitHub Stars

## 💬 Issues and Feedback

If you encounter any issues or have improvement suggestions, please provide feedback through the following channels:

- **GitHub Issues**: Report bugs or suggest new features
- **Discussion Forum**: Participate in feature design and technical discussions

## 📜 License

[Apache License 2.0](http://www.apache.org/licenses/LICENSE-2.0)

## 🎉 Support the Project

If you find this project helpful, please consider:

- Giving the project a ⭐ on GitHub
- Recommending it to your colleagues and friends
- Participating in code contributions or documentation improvements
- Sharing your experience cases in use

Thank you for your attention and support to common4j!