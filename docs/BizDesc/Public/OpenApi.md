# 安居客开放API
---

### 开放接口说明

* 域名基路径：http://api.anjuke.com/site/

#### 公共参数说明

* 传入参数
   
	Field|Type|Required|Description	---|---|---|---	app_id|int|Y|应用的的唯一标识id
	sign|string|Y|请求的签名值,以app secret key密钥签名，具体见签名算法
	timestamp|int|Y|UNIX时间戳，API服务端允许客户端请求时间误差为10分钟
	format|string|N|定义返回值格式，默认值:json，暂只支持json
	
	
	注：<b>所有提供的API在调用时都要传入以上必选公共参数</b>


* 应用密钥
    * 每个应用会分配一个密钥app secret key，用于签名，调用API时不用传入

#### 签名算法

假设请求API需要3个参数，分别是“k1”、“k2”、“k3”，它们的值分别是“v1”、“v2”、“v3”，API的应用的私钥Secret Key为“secret_key”，计算方法如下所示：

1. 将请求参数格式化为“key=value”格式，如“k1=v1”、“k2=v2”、“k3=v3”；
2. 将格式化好的参数除sign参数以外键值对以字典序升序排列后，拼接在一起，如“k1=v1k2=v2k3=v3”；
3. 在拼接好的字符串末尾追加上应用的Secret Key，得到“k1=v1k2=v2k3=v3secret_key”
4. 上述字符串以32位的MD5加密即时签名的值

注：

1. <b>所有GET参数都要纳入签名</b>
2. <b>但参加签名参数不包括sign参数</b>


### API列表
   
#### 注册新用户
* 地址：
    * v1/user/register/
* http请求方式：
    * GET
* 公共参数：
    * 见公共参数说明
* 私有参数：
   
   Field|Type|Required|Description	---|---|---|---
   name|string|N|用户名,当用户名已存在时，系统自动生成   phone|string|N|手机号
   email|string|N|邮箱
   password|string|N|密码，不传时，系统自动生成
   
   注：<b>name,phone,email至少有一项必传</b>
   

* 请求示例：

	```
	http://[域名]/v1/user/register/?app_id=10001&timestamp=1442888568&format=json&name=ken&email=ken@anjuke.com&phone=13988888888&password=anjuke2015&sign=5cefc3438fbf787875ffec6cd596346d
	
	```

* 返回参数说明: 

   Field|Type|Description	---|---|---|---
	code|int|见返回码说明   id|int|用户id,如果注册成功，返回用户id
   

#### 获得用户信息
* 地址：
    * v1/user/get-info/
* http请求方式：
    * GET
* 公共参数：
    * 见公共参数说明
* 私有参数：
   
   Field|Type|Required|Description	---|---|---|---   account|string|N|账户
   type|string|N|账户类型:email/phone/id表时account的类型
   
   注：<b>当type=email表示通过account是通过email的方式获得信息</b>
   

* 请求示例：

	```
	http://[域名]/v1/user/get-info/?app_id=10001&timestamp=1442888568&format=json&account=13988888888&type=phone&sign=b1e5d7ef6093218e44cf66313f0ab656
	或
	http://[域名]/v1/user/get-info/?app_id=10001&timestamp=1442888568&format=json&account=17895896&type=id&&sign=717392bbc0210a65cc260a58e041795e
	```

* 返回参数说明: 

   Field|Type|Description	---|---|---|---
   code|int|见返回码说明   id|int|用户id
   name|string|用户名
   phone|string|用户手机
   email|string|用户邮箱
   
   
### 返回码说明
 * 正常返回：code=200  
 * 错误码说明：
 
	Error Code|Description	---|---
	400|参数错误
	401|没有权限，签名等验证失败
	403|禁止访问，访问ip被禁止
	1000|注册失败，重新尝试或联系管理员	1001|注册失败，手机号已经存在
	1002|注册失败，邮箱已经存在
	1010|获得信息失败，重新尝试或联系管理员
	1011|获得信息失败，用户不存在
	