###用户中心pad登录功能详细设计

####用户中心模块

用户中心在三网代码库中都有相关代码，用户登录需要判断用户是否已经登录，所以将会变动3套用户中心的控制器和视图代码。

* 控制器部分：

    * 用户id的获取方法：APF::get_instance()->get_request()->getUserId();

    * 用户类型的获取方法：APF::get_instance()->get_request()->getUserType();

* 视图部分：

```php
<?php if($user_id && $user_type):?>
    <!-- 登录后样式 -->
<?php else: ?>
    <!-- 未登录样式 -->
<?php endif ?>
```

```javascript
var userid = '<?=$user_id ?>';
var usertype = '<?=$user_type ?>';
```

#####接口返回参数说明

**参数** | **说明**
--- | ---
userid | 用户id
usertype | 用户类型


用户未登录时({"userid":0,"usertype":null})显示用户登录弹层。

####验证表单模块

`登录名`，`密码`由javascript进行过滤判断，用户输入字符前输入框显示灰色提示信息。

`登录名`提示信息为：“请输入邮箱/手机号/用户名”；

`密码`提示信息为：“请输入密码”。

以上输入框失去焦点时检查用户输入的内容，用户输入的内容为空时在输入框下方红字显示提示信息。

`登录名`至少需要输入4个字符。

`密码`不能含有以下字符：
"，<，'，>，&，[空格]

####提交登录模块

登录用的信息验证有效以后，点击登录按钮，由js处理将`登录名`和`密码`以post的方式提交给site接口。

表单post请求的接口为：

* http://member.anjuke.com/login

接口返回举例(传递给接口的callback值为`window.parent.global.loginDailog.callbackLoginSuccessPad`)：

* 失败：

```html
<script type='text/javascript'>window.parent.global.loginDailog.callbackLoginSuccessPad({result:1})</script>
```

* 成功：

```html
<script type='text/javascript'>window.parent.global.loginDailog.callbackLoginSuccessPad({result:0,data:{"MSG":1,"USERTYPE":"1","USERID":"8888888"}})</script>
```

site端登录接口参数、说明和使用方式：

* 用户中心内部API wiki： 

```
http://wiki.corp.anjuke.com/index.php?title=%E7%94%A8%E6%88%B7%E4%B8%AD%E5%BF%83API
```

* 第三方登录接口[QQ]：

```
http://member.anjuke.com/extlogin/?sid=anjuke&url={url}&logintype={logintype}&from={from}
```

* 第三方登录接口[微博]：

```
http://member.anjuke.com/extlogin/?sid=anjuke&url={url}&logintype={logintype}&from={from}
```
     
**参数** | **说明**
--- | ---
sid | 站点标识，定值anjuke
url | 登录成功的回调地址，base64编码
logintype | QQ为qq，微博为weibo
from | 加码


* 获取手机验证码接口：

```
http://user.anjuke.com/register?chktype=verifyformat&phone={phone}&referer={url}
```

**参数** | **说明**
--- | ---
phone | 手机号
url | 当前页面地址，base64编码


* 验证手机验证码接口：

```
http://user.anjuke.com/register?chktype=checkVerifyCode&verifycode={code}&referer={url}&phone={phone}
```

**参数** | **说明**
--- | ---
phone | 手机号
code | 验证码
url | 当前页面地址，base64编码


* 用户登录接口：http://member.anjuke.com/login

POST参数说明：

**参数** | **说明**
--- | ---
callback | 回调函数
history | 当前页面地址，base64编码
url | 用户登录成功与否的回调接口
isp | 1
password | 密码
sid | 站点标识
username | 用户名{手机号或者邮箱地址}

* 用户登录成功与否的回调接口：http://shanghai.anjuke.com/account/loginsuccess/

* 用户注册接口{wiki}：

```
http://wiki.corp.anjuke.com/index.php?title=Member_new_user
```

####其他

site接口将根据传入的参数就行site登录的业务逻辑，将返回通过callback的方式发送给页面。

当登录发生错误时，在密码输入框下方，以红字提示"登陆名或密码错误，请重新输入"；

当登录完成时，显示登录成功的提示浮层。返回成功前将自动进行网站单点登录操作，种植cookie的过程在隐藏的iframe中完成，不会产生页面跳转。登录成功时用户收藏会自动进行同步。

用户关闭登录弹层时刷新页面。

####修订历史

```
2014-03-24
1. 点击登录按钮时于site不同，不再进行ajax的用户登录验证请求。
2014-03-28
1. 用户登录接口history参数测试无效，当前地址参数由history改为url。
2014-04-02
1. 用户登录接口history参数保留，url参数添加，url地址为返回调用回调的接口。
```
