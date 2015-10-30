## 欢迎来到Apache Solr维基

    内容
    1. 欢迎来到Apache Solr维基
    2. Solr文档
    3. 整体信息
    4. Solr开发
    5. 使用Solr
        1. 安装与配置
        2. 搜索与索引
        3. Solr云
        4. 先进工具
        5. 提示、技巧和使用用例
        6. Solr客户端
        7. 运行与生产(Operations and Production)
        8. 用户捐赠内容
    6. 如何编辑维基

## Solr文档

[Official documentation](http://lucene.apache.org/solr/documentation.html) for the latest release of Solr can be found on the Solr website. Of particular note is the [Solr Reference](https://cwiki.apache.org/confluence/display/solr/) Guide which is published by the project after each minor release.

The rest of this wiki is community edited and captures version agnostic information, User submitted Tips & Tricks, historical information on Solr, as well as some areas of Solr not yet covered in the Reference Guide.

## 整体信息

## Solr开发

## 使用Solr

### 安装与配置

* 通常的安装步骤
    * 包括在特殊环境下常用设置的信息

* Solr记录(Logging)

* 配置Solr
    * Solr配置xml
    * 图表xml
    * 图表RESTAPI
    * 分析解析令牌过滤
    * Solr与HTTP缓存
    * 编码管理
    
* Solr管理GUI

### 搜索与索引

* 索引文档
    * 通过 xml 格式添加文档 - 涵盖xml添加、删除、提交、优化语法
    * 通过 json 格式添加文档 - 涵盖json添加、删除、提交、优化语法
    * 通过 CSV 格式添加文档
    * 分析处理 - 不依赖索引分析文档
    * Solr内容扩展库(Solr Cell) - 涵盖如果使用Solr Cell索引MS Word, PDF等。参见旧版本 UpdateRichDocuments。
    * 更新处理器 - 更新处理器定义如何被处理一次更新请求
    * 重复数据删除 - 防止或标记重复的文档

* 搜索Solr
    * 请求处理程序(Request Handlers) - 控制用于处理请求的逻辑。Solr中包含一些不同的请求处理程序，同时你也可以编写自己风格的执行程序。
    * 响应写程序(Response Writers) - 控制按照请求处理程序生成响应的格式。Solr中包含一些不同的写程序，同时你也可以编写自己风格的写程序。
    * 输入变量
        * 查询参数索引 - 查询参数的索引
        * 核心查询参数
        * 公共查询参数
        * 简单的筛选参数
        * 高亮参数
        * 更喜欢
    * 搜索组件 - 搜索组件提供核心功能的请求处理程序
    * 查询语法 - 查询分析的默认语法，以及如何指定一个查询分析器
    * 方法查询 - Using the values in fields in functions and as factors in scoring
    * (Geo)特殊查询 - Find results near a point
    * 域崩溃/结果分组(Field Collapsing / Result Grouping) - 将拥有公共域值的文档分组
    * Join - 在文档中使用类似数据库的Join语句

### Solr云

### 先进工具

### 提示、技巧和使用用例

### Solr客户端

### 运行与生产(Operations and Production)

### 用户捐赠内容

## 如何编辑维基