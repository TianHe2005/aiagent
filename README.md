# 楠签到 - AI Agent 系统

## 项目介绍
楠签到 - AI Agent 系统是一个集成了用户管理、新闻通知、AI问答等功能的综合性平台，专为学校或组织打造的智能信息管理系统。

## 技术栈
- **后端框架**: Spring Boot 3.5.6
- **数据库访问**: Spring Data JPA
- **数据库**: MySQL 8.0
- **安全框架**: Spring Security + JWT
- **构建工具**: Maven
- **Java版本**: Java 17
- **前端交互**: RESTful API

## 核心功能模块

### 1. 用户管理
- 用户注册与登录
- 个人信息管理
- 密码修改与头像更新
- 角色权限控制（学生/教师）

### 2. 新闻管理
- 新闻发布与删除
- 新闻列表查询（支持分页、关键词搜索）
- 最新新闻获取

### 3. 通知管理
- 通知发布与删除
- 通知列表查询
- 最新通知获取

### 4. 轮播图管理
- 轮播图的增删改查
- 激活状态控制
- 排序功能

### 5. 热门问题
- 热门问题的增删改查
- 浏览次数统计

### 6. 文件上传
- 支持头像上传
- 支持新闻封面上传
- 支持通知封面上传

### 7. AI 功能
- 基础问答功能
- 对话会话管理
- 对话历史记录

## 快速开始

### 环境要求
- JDK 17 或更高版本
- Maven 3.8+ 或更高版本
- MySQL 8.0 或更高版本

### 数据库配置
1. 创建数据库：
```sql
CREATE DATABASE aiagent DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

2. 导入项目根目录下的 `aiagent.sql` 文件创建表结构

### 项目配置
修改 `src/main/resources/application.properties` 文件中的数据库连接信息：
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/aiagent?useSSL=false&serverTimezone=UTC&characterEncoding=utf8
spring.datasource.username=root
spring.datasource.password=123456
```

### 构建与运行
1. 使用 Maven 构建项目：
```bash
mvn clean package
```

2. 运行 Spring Boot 应用：
```bash
java -jar target/aiagent-0.0.1-SNAPSHOT.jar
```

3. 应用启动后，可通过以下 URL 访问：
```
http://localhost:8080/api
```

## 项目结构

```
src/main/java/com/example/aiagent/
├── AiagentApplication.java        # 应用入口
├── config/                        # 配置类
│   ├── SecurityConfig.java        # 安全配置
│   └── WebConfig.java             # Web配置
├── controller/                    # 控制器
│   ├── UserController.java        # 用户相关接口
│   ├── NewsController.java        # 新闻相关接口
│   ├── InfoController.java        # 通知相关接口
│   ├── CarouselController.java    # 轮播图相关接口
│   └── HotQuestionController.java # 热门问题相关接口
├── dto/                           # 数据传输对象
│   └── ResponseResult.java        # 统一响应结果
├── entity/                        # 实体类
│   ├── User.java                  # 用户实体
│   ├── News.java                  # 新闻实体
│   ├── Info.java                  # 通知实体
│   ├── Carousel.java              # 轮播图实体
│   └── HotQuestion.java           # 热门问题实体
├── exception/                     # 异常处理
│   └── GlobalExceptionHandler.java # 全局异常处理器
├── repository/                    # 数据访问层
│   ├── UserRepository.java        # 用户数据访问
│   ├── NewsRepository.java        # 新闻数据访问
│   ├── InfoRepository.java        # 通知数据访问
│   ├── CarouselRepository.java    # 轮播图数据访问
│   └── HotQuestionRepository.java # 热门问题数据访问
├── security/                      # 安全相关
│   ├── JwtService.java            # JWT工具类
│   └── JwtAuthenticationFilter.java # JWT认证过滤器
└── service/                       # 服务层
    ├── UserService.java           # 用户服务
    ├── NewsService.java           # 新闻服务
    ├── InfoService.java           # 通知服务
    ├── CarouselService.java       # 轮播图服务
    └── HotQuestionService.java    # 热门问题服务
```

## API 接口文档
详细的API接口说明请参考项目根目录下的 `接口文档.md` 文件。

## 安全配置
本项目使用 JWT 进行身份验证，所有API（除登录和注册外）都需要在请求头中添加有效的 JWT Token。

## 开发说明
1. 代码风格遵循 Spring Boot 最佳实践
2. 使用 Lombok 简化实体类编写
3. 使用 Spring Data JPA 简化数据库操作
4. 所有接口返回统一的响应格式

## License
MIT