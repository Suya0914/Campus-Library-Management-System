# 📖 薄荷图书馆 · 图书管理系统

一个基于 **Spring Boot** 的图书管理系统，采用薄荷绿清新风格的前端界面，支持图书入库/出库、借还书、读者管理等核心功能。

---

## ✨ 功能模块

| 功能 | 说明 |
|------|------|
| 🏠 首页展示 | 轮播图 + 快捷功能入口 + 馆藏图书展示 |
| 📥 图书入库 | 新增图书到馆藏（ISBN 唯一校验）|
| 📤 图书出库 | 按 ISBN 下架图书 |
| 🔍 图书查询 | 按书名、作者、ISBN 模糊搜索 |
| 📖 借书 | 读者凭借阅证号借书（每人限借 3 本）|
| 🔄 还书 | 读者归还已借图书 |
| 📋 查看库存 | 浏览全部馆藏图书 |
| 👥 查看读者 | 查看所有注册读者 |
| 👤 新增读者 | 添加新读者（借阅证号唯一）|

---

## 🛠️ 技术栈

| 层级 | 技术 |
|------|------|
| 后端框架 | Spring Boot 2.7.18 |
| 运行环境 | Java 17 |
| 构建工具 | Maven |
| 数据存储 | HashMap（内存级，无需安装数据库）|
| 前端 | HTML5 + CSS3 + JavaScript（原生）|
| 接口风格 | RESTful API（JSON）|

---

## 🚀 快速开始

### 1. 环境要求

- **JDK 17** 或更高版本
- **Maven**（或使用项目内置的 Maven Wrapper）

### 2. 运行项目

```bash
# 进入项目目录
cd library-system

# 编译并启动
mvn spring-boot:run
```

### 3. 访问系统

浏览器打开：**http://localhost:8080**

### 4. 预置数据

| 类型 | 数据 |
|------|------|
| 图书 | Java从入门到精通（ISBN: 9787302567890）|
| 图书 | 数据结构与算法（ISBN: 9787301456789）|
| 读者 | 王五（借阅证号: 123456）|
| 读者 | 赵六（借阅证号: 789012）|

---

## 📡 API 接口一览

| 请求方式 | 路径 | 功能 |
|---------|------|------|
| POST | `/api/book/add` | 图书入库 |
| POST | `/api/book/out` | 图书出库 |
| POST | `/api/book/search` | 图书查询 |
| GET | `/api/book/list` | 库存列表 |
| POST | `/api/reader/add` | 新增读者 |
| GET | `/api/reader/list` | 读者列表 |
| POST | `/api/operate/borrow` | 借书 |
| POST | `/api/operate/return` | 还书 |
| POST | `/api/auth/login` | 管理员登录 |

---

## 📁 项目结构

```
library-system/
├── pom.xml                          # Maven 配置文件
├── src/main/
│   ├── java/com/library/
│   │   ├── LibraryApplication.java   # 启动类
│   │   ├── controller/               # 控制器层（API 接口）
│   │   ├── service/                  # 业务逻辑层
│   │   ├── entity/                   # 实体类
│   │   ├── dto/                      # 数据传输对象
│   │   └── util/                     # 工具类
│   └── resources/
│       ├── application.yml           # 应用配置
│       └── static/                   # 前端静态页面
│           ├── index.html            # 首页
│           ├── login.html            # 登录页
│           ├── addBook.html          # 图书入库
│           ├── outBook.html          # 图书出库
│           ├── searchBook.html       # 图书查询
│           ├── borrowBook.html       # 借书
│           ├── returnBook.html       # 还书
│           ├── stockBooks.html       # 库存查看
│           ├── addReader.html        # 新增读者
│           ├── listReaders.html      # 读者列表
│           ├── css/common.css        # 样式
│           └── js/request.js         # 请求封装
```

---

## 📸 界面截图

> 🖼️ 薄荷绿主题 UI，侧边栏可展开/收缩，响应式设计

---http://localhost:8080/addReader.html
