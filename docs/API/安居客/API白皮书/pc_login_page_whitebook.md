## PC 用户登录白皮书
##### 
### 1. 页面URL
```
http://user.anjuke.com/my/login?history=aHR0cDovL3NoYW5naGFpLmFuanVrZS5jb20v
```
### 2. 页面说明
```
用户登录页面,包含注册过的账号登录,手机号+手机验证码登录,第三方登录(qq,新浪微博,微信),
```
    
### 3. 页面模块列表
|编号|URL|仓库|机器|负责人|功能|
| --- |--- | --- | --- | --- | --- |
|3.1|http://member.anjuke.com/api/login/submit|anjuke-site|app10-119|王其春|普通账号登录|
|3.2|http://member.anjuke.com/captcha|anjuke-site|app10-119|王其春|获取图形验证码|
|3.3|http://member.anjuke.com/checkcaptcha/?captcha=wiz|anjuke-site|app10-148|王其春|检验图形验证码是否正确|
|3.4|http://member.anjuke.com/api/login/sendtophone?phone=13764567254|anjuke-site|app10-119|王其春|发送手机验证码|
|3.5|http://member.anjuke.com/api/login/phonesubmit|anjuke-site|app10-119|王其春|用户手机号+手机验证码登录|
|3.6|http://member.anjuke.com/extcallback/|anjuke-site|app10-148|王其春|用户第三方登录回调接口|

### 4. 依赖的外部URL
##### 4.1 URL-3.2,3.3 的[依赖,主要是memcache和redis](http://gitlab.corp.anjuke.com/_site/docs/blob/master/API/%E5%AE%89%E5%B1%85%E5%AE%A2/API%E7%99%BD%E7%9A%AE%E4%B9%A6/pc_register_page_whitebook.md)
##### 4.2 URL-3.1 的依赖
|配置名|URL|功能|其它|
| --- | --- | --- | --- |
|broker_service|http://10.10.6.6:8080/service-internal/rest/brokers/|经纪人服务||
##### 4.3 URL-3.4依赖
|配置名|URL|功能|其它|
| --- | --- | --- | --- |
|send_sms_url|http://ajksms.a.ajkdns.com/send.php|发送手机短信验证码通道||
##### 4.4 URL-3.6 依赖
|配置名|URL|功能|其它|
| --- | --- | --- | --- |
|hardcode写在代码里|https://graph.qq.com/oauth2.0/token|根据qq的code获取access_token||
|hardcode写在代码里|https://graph.qq.com/oauth2.0/me|根据access_token取key||
|hardcode写在代码里|https://graph.qq.com/user/get_user_info|根据key取用户的信息|
|hardcode写在代码里|https://api.weibo.com/oauth2/access_token|获取weiboaccess_token|
|hardcode写在代码里|http://open.weibo.com/wiki/Oauth2/authorize|获取微博用户信息|
|hardcode写在代码里|https://api.weixin.qq.com/sns/oauth2/access_token|获取微信用户token|
|hardcode写在代码里|https://api.weixin.qq.com/sns/userinfo|根据token获取用户信息|
|hardcode|http://upd1.ajkimg.com/upload|上传图片地址||
### 5. 数据库和表:
##### 5.1 URL-3.1,3.4,3.5
|数据库|表名称|作用|读写|是否独有|
| --- | --- | --- | --- | --- |
|anjuke_db|ajk_html_content|存储ip黑名单|读|否|
|anjuke_db|ajk_members|用户基本信息|读|否|
|anjuke_db|ajk_membersother|用户扩展信息|读|否|
|anjuke_db|ajk_member_logins|用户登录记录|读写|否|
##### 5.2 URL-3.6
|数据库|表名称|作用|读写|是否独有|
| --- | --- | --- | --- | --- |
|anjuke_db|ext_member_mapping|获取第三方id和用户id的对应关系|读写|是|
|anjuke_db|ajk_membersother|用户扩展信息|读写|否|
|anjuke_db|ajk_member_options|用户标签|读写|是|
|anjuke_db|ajk_members|用户基本信息|读写|否|
### 6. [Memcache&Redis](http://gitlab.corp.anjuke.com/_site/docs/blob/master/API/%E5%AE%89%E5%B1%85%E5%AE%A2/cache/memcache_redis.md)
### 7. 关联job
|job名称|job管理地址|功能|负责人|
|--- | --- | --- | --- |


