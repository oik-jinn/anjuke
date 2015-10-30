## touch web  用户注册白皮书
##### 
### 1. 页面URL
```
http://m.anjuke.com/register
```
### 2. 页面说明
```
touch web 用户注册页面(包含获取图形验证码,检查图形验证码,获取手机验证码,)
```
    
### 3. 页面模块列表
|编号|URL|仓库|机器|负责人|功能|
| --- |--- | --- | --- | --- | --- |
|3.1|http://member.anjuke.com/captcha|app10-119|王其春|获取图形验证码|
|3.2|http://member.anjuke.com/checkcaptcha|app10-119|王其春|检查图形验证码|
|3.3|http://m.anjuke.com/register/message/get|app10-151|王其春|发送手机验证码(java)|
|3.4|http://m.anjuke.com/register/message/verify|app10-052|王其春|验证用户手机验证码(java)|
|3.5|http://m.anjuke.com/register/verify|app10-151|王其春|用户注册(java)|

### 4. 依赖的外部URL
##### 4.1 URL-3.1,3.2 的[依赖](http://gitlab.corp.anjuke.com/_site/docs/blob/master/API/%E5%AE%89%E5%B1%85%E5%AE%A2/API%E7%99%BD%E7%9A%AE%E4%B9%A6/pc_register_page_whitebook.md)
##### 4.2 URL-3.3,3.4,3.5 的依赖
|配置名|URL|功能|其它|
| --- | --- | --- | --- |
|hard code 在代码里|http://user.anjuke.com/register?chktype=verifyformat|发送手机验证码|用户中心api|
|hard code 在代码里|http://user.anjuke.com/register?chktype=checkVerifyCode|验证手机验证码是否正确|用户中心api|
|hard code 在代码里|http://member.anjuke.com//memberapi/m/?act=add_user_by_subscribe|用户注册|用户中心api|
### 5. 数据库和表:
### 6. 关联job
|job名称|job管理地址|功能|负责人|
|--- | --- | --- | --- |


