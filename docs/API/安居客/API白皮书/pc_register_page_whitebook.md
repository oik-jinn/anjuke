## PC 用户注册白皮书
##### 
### 1. 页面URL
```
http://user.anjuke.com/register/
```
### 2. 页面说明
```
用户注册页面,注册包括手机号注册和邮箱注册,获取图片验证码,验证图片码验证,获取手机验证码
```
    
### 3. 页面模块列表
|编号|URL|仓库|机器|负责人|功能|
| --- |--- | --- | --- | --- | --- |
|3.1|http://user.anjuke.com/register?chktype=mobile&phone=13764567254|anjuke-site|app10-148|王其春|检查用户手机号或者邮箱是否注册过|
|3.2|http://member.anjuke.com/captcha|anjuke-site|app10-119|王其春|获取图形验证码|
|3.3|http://member.anjuke.com/checkcaptcha/?captcha=wiz|anjuke-site|app10-148|王其春|检验图形验证码是否正确|
|3.4|http://user.anjuke.com/register?chktype=verifyformat|anjuke-site|app10-148|王其春|发送手机验证码|
|3.5|http://member.anjuke.com/register|anjuke-site|app10-148|王其春|用户注册|
### 4. 依赖的外部URL
##### 4.1 URL-3.1,3.4 的依赖
|配置名|URL|功能|其它|
| --- | --- | --- | --- |
|api_member_domain|member.anjuke.com|member 的域名|用户中心域名|
|无|member.anjuke.com/ajax/registernew|验证手机号是否注册过|用户中心api|
|host|shipper.logger.a.ajkdns.com|nlogger地址|
|port|24226|nlogger端口||
|无|member.anjuke.com/checkcaptcha/|验证图形验证码是否正确|用户中心接口|
|无|member.anjuke.com/memberapi/m?act=send_phone_code|发送手机验证码|用户中心接口|
##### 4.2 URL-3.5依赖
|配置名|URL|功能|其它|
| --- | --- | --- | --- |
|broker_service|http://10.10.6.6:8080/service-internal/rest/brokers/|经纪人服务||

### 5. 数据库和表:
##### 5.1 URL-3.1,3.4,3.5 依赖
|数据库|表名称|作用|读写|是否独有|
| --- | --- | --- | --- | --- |
|anjuke_db|ajk_members|用户基本信息|读写|否|
|anjuke_db|ajk_membersother|用户扩展信息|读写|否|
|anjuke_db|ajk_member_logins|用户登录记录|读写|否|
|anjuke_db|ajk_td_mobile|退订手机短信的表|读|否|
### 6. [Memcache&Redis](http://gitlab.corp.anjuke.com/_site/docs/blob/master/API/%E5%AE%89%E5%B1%85%E5%AE%A2/cache/memcache_redis.md)
### 7. 关联job
|job名称|job管理地址|功能|负责人|
|--- | --- | --- | --- |


