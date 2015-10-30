## touch web  用户登录白皮书
##### 
### 1. 页面URL
```
http://m.anjuke.com/center
```
### 2. 页面说明
```
touch web 用户登录页面(账号登录)及第三方登录(qq和新浪微博)
```
    
### 3. 页面模块列表
|编号|URL|仓库|机器|负责人|功能|
| --- |--- | --- | --- | --- | --- |
|3.1|http://member.anjuke.com/login?|app10-119|王其春|普通账号登录|
|3.2|http://member.anjuke.com/extcallback/|app10-148|王其春|第三方登录回调接口|

### 4. 依赖的外部URL
##### 4.1 URL-3.1 的依赖
|配置名|URL|功能|其它|
| --- | --- | --- | --- |
|broker_service|http://10.10.6.6:8080/service-internal/rest/brokers/|经纪人服务||
##### 4.2 URL-3.2 的[依赖](http://gitlab.corp.anjuke.com/_site/docs/blob/master/API/%E5%AE%89%E5%B1%85%E5%AE%A2/API%E7%99%BD%E7%9A%AE%E4%B9%A6/pc_login_page_whitebook.m)
### 5. 数据库和表:
##### 5.1 URL-3.1
|数据库|表名称|作用|读写|是否独有|
| --- | --- | --- | --- | --- |
|anjuke_db|ajk_html_content|存储ip黑名单|读|否|
|anjuke_db|ajk_members|用户基本信息|读|否|
|anjuke_db|ajk_membersother|用户扩展信息|读|否|
|anjuke_db|ajk_member_logins|用户登录记录|读写|否|
### 7. 关联job
|job名称|job管理地址|功能|负责人|
|--- | --- | --- | --- |


