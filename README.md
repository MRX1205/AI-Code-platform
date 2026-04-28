# 掠影航猫 AI 零代码应用生成平台

一个基于大语言模型的 **AI 零代码应用生成平台**，用户只需输入一句自然语言描述，即可由 AI 自动生成完整的前端网站应用（HTML 单文件或多文件），并支持实时预览与一键部署。

---

## 项目简介

本平台的核心功能是让每个人都能在**无需编写任何代码**的前提下，快速创建属于自己的网站应用。

### 主要功能

- **AI 代码生成**：基于 [LangChain4j](https://github.com/langchain4j/langchain4j) 集成 DeepSeek 大模型，通过自然语言描述自动生成可运行的前端代码。
- **实时流式输出**：使用 SSE（Server-Sent Events）技术，AI 生成过程实时流式呈现。
- **在线预览**：生成的网站在页面右侧即时渲染，无需额外操作。
- **一键部署**：支持将生成的网站部署并获得独立访问链接。
- **应用管理**：用户可创建、编辑、删除自己的应用，并查看历史对话记录。
- **精选案例**：展示平台内的优质生成案例，供用户参考和学习。
- **用户系统**：支持注册、登录、权限管理（普通用户 / 管理员）。

### 代码生成模式

| 模式 | 说明 |
|------|------|
| `html`（原生 HTML 模式） | 生成单个 HTML 文件，包含完整的 CSS 和 JavaScript |
| `multi_file`（原生多文件模式） | 生成多个文件（HTML + CSS + JS 分离） |

---

## 技术栈

### 后端

| 技术 | 版本 | 说明 |
|------|------|------|
| Spring Boot | 3.5.5 | 主框架 |
| Java | 21 | 运行环境 |
| LangChain4j | 1.1.0 | AI 大模型集成框架 |
| MyBatis-Flex | 1.11.0 | ORM 框架 |
| MySQL | 8.x | 持久化存储 |
| Redis | 7.x | 对话记忆存储 |
| Knife4j | 4.4.0 | API 文档（基于 OpenAPI 3） |
| HikariCP | — | 数据库连接池 |
| Hutool | 5.8.38 | Java 工具库 |

### 前端

| 技术 | 版本 | 说明 |
|------|------|------|
| Vue 3 | ^3.5.17 | 前端框架 |
| TypeScript | ~5.8.0 | 类型支持 |
| Vite | ^7.0.0 | 构建工具 |
| Ant Design Vue | ^4.2.6 | UI 组件库 |
| Pinia | ^3.0.3 | 状态管理 |
| Vue Router | ^4.5.1 | 路由管理 |
| Axios | ^1.12.2 | HTTP 客户端 |
| markdown-it | ^14.1.0 | Markdown 渲染 |

---

## 环境要求

在运行本项目之前，请确保已安装以下软件：

- **JDK 21**
- **Maven 3.8+**
- **Node.js 22+** 和 **npm**
- **MySQL 8.x**
- **Redis 7.x**

---

## 快速开始

### 1. 克隆项目

```bash
git clone https://github.com/MRX1205/AI-Code-platform.git
cd AI-Code-platform
```

### 2. 数据库初始化

启动 MySQL 服务，然后执行项目根目录下的 SQL 脚本：

```bash
mysql -u root -p < sql/create_table.sql
```

或在 MySQL 客户端中手动执行 `sql/create_table.sql` 文件，该脚本会自动创建数据库 `lyhm_ai_code_platform` 及所需的表（`user`、`app`、`chat_history`）。

### 3. 配置后端

#### 修改数据库连接

编辑 `src/main/resources/application.yml`，根据实际情况修改数据库用户名和密码：

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/lyhm_ai_code_platform
    username: root
    password: 你的数据库密码
  data:
    redis:
      host: localhost
      port: 6379
      password:   # 如果 Redis 设置了密码，在此填写
```

#### 配置 AI 模型 API Key

在 `src/main/resources/application-local.yml` 中配置 AI 服务（默认使用 DeepSeek）：

```yaml
langchain4j:
  open-ai:
    chat-model:
      base-url: https://api.deepseek.com
      api-key: 你的DeepSeek_API_Key
      model-name: deepseek-chat
      max-tokens: 8192
      strict-json-schema: true
      response-format: json_object
    streaming-chat-model:
      base-url: https://api.deepseek.com
      api-key: 你的DeepSeek_API_Key
      model-name: deepseek-chat
      max-tokens: 8192
```

> **提示**：DeepSeek API Key 可在 [DeepSeek 开放平台](https://platform.deepseek.com/) 注册后获取。也可以替换为其他兼容 OpenAI 接口协议的 API。

### 4. 启动后端服务

```bash
./mvnw spring-boot:run
```

或者在 IntelliJ IDEA 等 IDE 中直接运行 `LyhmAiCodePlatformApplication.java`。

后端服务默认监听端口 **8123**，API 基础路径为 `/api`。

启动成功后可访问 API 文档：
- **Knife4j 文档**：http://localhost:8123/api/doc.html

### 5. 配置前端

进入前端目录，复制环境变量示例文件：

```bash
cd lyhm-ai-code-platform-frontend
cp src/config/env.example.ts /dev/null   # 该文件仅供参考，无需复制
```

在前端目录下创建 `.env.local` 文件，配置 API 地址：

```env
# API 基础地址（指向后端服务）
VITE_API_BASE_URL=http://localhost:8123/api

# 应用部署域名（用于生成部署链接预览）
VITE_DEPLOY_DOMAIN=http://localhost:8123
```

### 6. 启动前端服务

```bash
# 在 lyhm-ai-code-platform-frontend 目录下
npm install
npm run dev
```

前端开发服务器默认运行在 **http://localhost:5173**。

---

## 使用说明

### 注册与登录

1. 打开浏览器访问 http://localhost:5173
2. 点击右上角**注册**，填写账号和密码完成注册。
3. 使用注册的账号**登录**平台。

### 创建 AI 应用

1. 在首页的输入框中，用自然语言描述你想要的网站，例如：
   > "帮我创建一个现代化的个人博客网站，包含文章列表、分类标签和搜索功能"
2. 点击发送按钮（或按 **Enter**）提交描述。
3. 系统会自动创建应用并跳转到**应用对话页**。

### 与 AI 对话生成网站

1. 在应用对话页，AI 将根据你的描述自动生成网站代码。
2. 生成过程以流式方式实时展示在左侧对话区域。
3. 生成完成后，右侧**预览区域**将自动渲染生成的网站。
4. 可以继续在对话框中描述修改需求，迭代优化网站。

### 部署应用

1. 在应用对话页右上角，点击**部署**按钮。
2. 部署成功后将获得一个独立的访问链接。
3. 任何人都可以通过该链接访问你生成的网站。

### 管理应用

- **查看详情**：点击右上角「应用详情」按钮查看应用信息。
- **编辑应用**：在详情页点击编辑，可修改应用名称、封面等信息。
- **删除应用**：在详情页可删除应用（仅应用所有者或管理员）。

---

## 项目结构

```
AI-Code-platform/
├── src/                                    # 后端源代码（Spring Boot）
│   └── main/
│       ├── java/com/lyhm/lyhmaicodeplatform/
│       │   ├── ai/                         # AI 代码生成服务
│       │   ├── annotation/                 # 自定义注解（权限校验）
│       │   ├── aop/                        # AOP 拦截器
│       │   ├── common/                     # 通用响应/分页类
│       │   ├── config/                     # 配置类（跨域、Redis 等）
│       │   ├── constant/                   # 常量定义
│       │   ├── controller/                 # RESTful API 控制器
│       │   ├── core/                       # 核心业务逻辑（代码解析/保存）
│       │   ├── exception/                  # 全局异常处理
│       │   ├── mapper/                     # MyBatis 数据访问层
│       │   ├── model/                      # 实体/DTO/VO/枚举
│       │   ├── saver/                      # 生成代码文件保存策略
│       │   └── service/                    # 业务逻辑层
│       └── resources/
│           ├── application.yml             # 主配置
│           ├── application-local.yml       # 本地环境配置（AI Key 等）
│           ├── application-prod.yml        # 生产环境配置
│           ├── mapper/                     # MyBatis XML 映射文件
│           └── prompt/                     # AI Prompt 模板
├── lyhm-ai-code-platform-frontend/        # 前端源代码（Vue 3 + Vite）
│   ├── src/
│   │   ├── api/                            # 后端 API 接口调用
│   │   ├── assets/                         # 静态资源
│   │   ├── components/                     # 公共组件
│   │   ├── config/                         # 环境变量配置
│   │   ├── layouts/                        # 页面布局
│   │   ├── pages/                          # 页面组件
│   │   │   ├── HomePage.vue                # 首页
│   │   │   ├── app/                        # 应用相关页面
│   │   │   ├── admin/                      # 管理员页面
│   │   │   └── user/                       # 用户相关页面
│   │   ├── router/                         # 路由配置
│   │   ├── stores/                         # Pinia 状态管理
│   │   └── utils/                          # 工具函数
│   └── package.json
├── sql/
│   └── create_table.sql                    # 数据库建表脚本
└── pom.xml                                 # Maven 构建配置
```

---

## 页面路由

| 路径 | 说明 |
|------|------|
| `/` | 首页（应用创建入口 + 我的作品 + 精选案例） |
| `/user/login` | 用户登录 |
| `/user/register` | 用户注册 |
| `/app/chat/:id` | 应用对话与代码生成页 |
| `/app/edit/:id` | 编辑应用信息 |
| `/admin/userManage` | 用户管理（管理员） |
| `/admin/appManage` | 应用管理（管理员） |
| `/admin/chatManage` | 对话管理（管理员） |

---

## 常见问题

**Q：后端启动报 Redis 连接失败？**  
A：请确保本地 Redis 服务已启动（默认端口 6379）。可执行 `redis-server` 或通过服务管理器启动。

**Q：AI 生成没有响应或报错？**  
A：请检查 `application-local.yml` 中的 `api-key` 是否正确填写，以及网络是否可以访问 DeepSeek API。

**Q：前端显示 "获取应用信息失败"？**  
A：请确认后端服务已正常启动（端口 8123），并检查前端 `.env.local` 中的 `VITE_API_BASE_URL` 是否配置正确。

---

## 作者

[@掠影航猫 (MRX1205)](https://github.com/MRX1205)
