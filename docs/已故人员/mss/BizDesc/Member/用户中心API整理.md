## 用户中心对外api整理---

### master部分
```
register_mobile

功能说明：用于提供移动用户，大业主和分销平台用户到用户中心注册
参数
userMobile：用户手机号（即注册账号）；
userPwd：用户密码；（可选，不传此参数的时候会设置默认密码，对应场景为手机验证码用户）；
detail:可传参数为0或者1，为1的时候返回用户的详细信息（可选）；
nickname：用户昵称（可选）；
ico：用户头像（可选）;
nick_name_pinyin：用户昵称拼音（由移动API端生成传入过来，可选）；
gender：用户性别，为了和用户中心统一，我们约定1代表男，0代表女，2为保密;
force: 当用户存在的时候是否强制更新用户数据（密码等），1代表是强制更新；
userType: 用户类型，目前支持类型为1（普通用户），9（大业主）和10（分销平台用户）;
trueName: 用户真实姓名，大业主类型用户需要传此参数
调用方式
http://member.anjuke.test/memberapi/m/?act=register_mobile&userMobile=13508699419&userPwd=123&detail=1&nickname=继诚&ico='www.baidu.com'&nick_name_pinyin=jicheng&gender=0&force=1;
当用户注册成功的时候返回数据示例
detail=1时
{"status":"OK","datas":{"UserId":"7116010","UserName":"mobile13518688819","UserPhoto":"'www.baidu.com'","NickName":"'\u7ee7\u8bda'","TrueName":"","UserSex":"2","UserMobile":"13518688819","IsOpen":"0","Signature":null,"commid":"0","OtherAddr":"","FromType":"0","updateDate":"1407731831","CityId":"0","host":"0","mobile_verify":"0","Wealth":"0","UserBirthday":"0000-00-00","has_password":"1","nick_name_pinyin":"jicheng"},"error_message":" ","error_code":0}

detail=0或者不传的时候
{"status":"OK","datas":{"UserId":"7116011"},"error_message":" ","error_code":0}

当force=1的时候都是返回用户信息，同样根据detail字段来确定是否返回详细信息

失败的时候都是返回用户已经注册信息
{"status":"error","datas":[],"error_message":"\u60a8\u5df2\u7ecf\u662f\u6211\u4eec\u7684\u9ad8\u7ea7\u4f1a\u5458\uff0c\u65e0\u9700\u518d\u6b21\u6ce8\u518c\u3002","error_code":205}
```

```
login_mobile

功能说明：提供用户登录接口
参数
userMobile：用户手机号（即登录账号）;
userPwd：用户密码;（可选，不传此参数的时候会使用默认密码，对应场景为手机验证码用户，让其也可以成功登录）；
调用方式
member.anjuke.com/memberapi/m/?act=login_mobile&userMobile=13608699419&userPwd=123
数据返回
{"status":"OK","datas":{"UserId":"7116011","UserName":"mobile13528688819","UserPhoto":"'www.baidu.com'","NickName":"'\u7ee7\u8bda'","TrueName":"","UserSex":"2","UserMobile":"13528688819","IsOpen":"0","Signature":null,"commid":"0","OtherAddr":"","FromType":"0","updateDate":"1407731854","CityId":"0","host":"0","mobile_verify":"0","Wealth":"0","UserBirthday":"0000-00-00","has_password":"1","nick_name_pinyin":"jicheng"},"error_message":" ","error_code":0}

失败的时候提示用户登录账户不存在或者密码错误
{"status":"error","datas":[],"error_message":"\u5f53\u524d\u5bc6\u7801\u4e0d\u6b63\u786e","error_code":118}
```

```
update_user_other_info

功能说明：更新用户信息接口
参数
uid：用户的User_id;
userMobile：要修改成的手机号;
nickname:要修改成的用户昵称(可选);
gender:要修改成的用户性别(可选);
nick_name_pinyin：要修改成的用户昵称拼音(可选);
ico:要修改成的用户图片信息（可选），其格式是图片的ImageId进行md5加密之后的值
userPwd:要修改成的用户密码(可选);

调用方式
member.anjuke.com/memberapi/m/?act=update_user_other_info&uid=7115964&nickname=继诚&gender=1&nick_name_pinyin=jicheng&userPwd=123&userMobile=13262883995
数据返回
当uid参数不正确的时候
{"status":"error","datas":[],"error_message":"","error_code":-9995}
当缺少uid参数的时候
{"status":"error","datas":[],"error_message":"\u53c2\u6570\u65e0\u6548","error_code":-9998}
成功的时候：
{"status":"OK","datas":[],"error_message":" ","error_code":0}
```

```
get_user_other_info

功能说明：得到用户信息表里的信息接口
参数
uid：用户的User_id;

调用方式
member.anjuke.com/memberapi/m/?act=get_user_other_info&uid=7115964
数据返回
成功的时候：
{"status":"OK","datas":{"UserId":"7115964","UserName":"mobile13608699419","UserPhoto":" ","NickName":" ","TrueName":"","UserSex":"2","UserMobile":"13608699419","IsOpen":"0","Signature":null,"commid":"0","OtherAddr":"","FromType":"0","updateDate":"1407380536","CityId":"0","host":"0","mobile_verify":"0","Wealth":"0","UserBirthday":"0000-00-00","has_password":"0","nick_name_pinyin":" "},"error_message":" ","error_code":0}
失败的时候
{"status":"error","datas":[],"error_message":"","error_code":-9995}
```

```
get_user_member_info

功能说明：得到用户表里的信息接口
参数
uid：用户的User_id;

调用方式
member.anjuke.com/memberapi/m/?act=get_user_member_info&uid=7115964
数据返回
成功的时候：
{"status":"OK","datas":{"UserId":"7115964","UserName":"1372224415CvP","CityId":"14","UserPwd":"ZWI3MWE1NTgyZWVmYzRhMWE4ZThiMjFhZGU2MDVhOTU=","UserPhone":null,"UserAddress":null,"UserEmail":null,"EmailPass":"0","UserType":"2","RegDate":"1372224415","LastLogDate":"1372224418","LogTimes":"2","ClientIP":"111.192.74.182","NameUpper":"1372224415CVP","WorkerNum":"2978","TestAccount":"1","Points":"0","YearPoint":"1","Fuid":"772732","CurrentCity":"14","registerFrom":"HZ","updated_datetime":"2014-06-27 07:45:20","isInUse":"1"},"error_message":" ","error_code":0}
失败的时候
{"status":"error","datas":[],"error_message":"","error_code":-9995}
```

```
updateMemberMobile

功能说明：这是修改手机号的接口，
参数
uid：用户的User_id;
newMobile：用户需要更改的手机号
userType：用户的用户类型

调用方式
member.anjuke.com/memberapi/m/?act=updateMemberMobile&uid=7115964&newMobile=13262883995&userType=1
数据返回
已经注册过的手机号是不会让其注册成功的，返回值为
{
    "status": "error",
    "datas": [],
    "error_message": "手机已经被注册",
    "error_code": 205
}
成功的时候：
{"status":"ok","datas":{"UserId":"7115964","UserName":"mobile13262883994","UserPhoto":null,"NickName":"\u7ee7\u8bda","TrueName":"","UserSex":"2","UserMobile":"13262883994","IsOpen":"0","Signature":null,"commid":"0","OtherAddr":"","FromType":"0","updateDate":null,"CityId":"0","host":"0","mobile_verify":"1","Wealth":"0","UserBirthday":null,"has_password":"1","nick_name_pinyin":"jicheng"},"error_message":"","error_code":0}
```

```
get_user_info

功能说明：获取用户信息（包括用户信息表和用户表）
参数
uid：用户的User_id;

调用方式
member.anjuke.com/memberapi/m/?act=get_user_info&uid=7115964
数据返回
成功的时候：
{"UserId":"7115964","UserName":"1372224415CvP","UserPhoto":null,"NickName":null,"TrueName":"","UserSex":"2","UserMobile":null,"IsOpen":"0","Signature":null,"commid":"0","OtherAddr":"","FromType":"0","updateDate":null,"CityId":"0","host":"1","mobile_verify":null,"Wealth":"0","UserBirthday":null,"update_time":"2014-11-03 18:37:36","has_password":"1","nick_name_pinyin":" ","UserEmail":null,"UserType":"2","NameUpper":"1372224415CVP","ClientIP":"111.192.74.182","RegDate":"1372224415"}
失败的时候
false
```

```
get_user_info_byphone

功能说明：通过手机号获取用户信息（包括用户信息表和用户表）
参数
userMobile：用户的手机号;

调用方式
member.anjuke.com/memberapi/m/?act=get_user_info_byphone&userMobile=13262883995
数据返回
成功的时候：
{"status":"ok","datas":{"UserId":"7119072","UserName":"mobile13508699406","UserPhoto":"","NickName":"","TrueName":"","UserSex":"0","UserMobile":"13508699406","IsOpen":"0","Signature":null,"commid":"0","OtherAddr":"","FromType":"0","updateDate":"1407756031","CityId":"0","host":"0","mobile_verify":"0","Wealth":"0","UserBirthday":null,"has_password":"1","nick_name_pinyin":"","UserPwd":"ZmNlYTkyMGY3NDEyYjVkYTdiZTBjZjQyYjhjOTM3NTk=","UserPhone":"13508699406","UserAddress":null,"UserEmail":null,"EmailPass":"0","UserType":"1","RegDate":"1407756031","LastLogDate":"1411468982","LogTimes":"25","ClientIP":"10.20.6.105","NameUpper":null,"WorkerNum":"","TestAccount":"1","Points":"0","YearPoint":"0","Fuid":"0","CurrentCity":"0","registerFrom":"","updated_datetime":"2014-09-24 11:01:45","isInUse":"1"},"error_message":"","error_code":0}
失败的时候
{"status":"error","datas":[],"error_message":"\u8bf7\u586b\u5199\u6b63\u786e\u7684\u624b\u673a\u53f7\u7801\u3002","error_code":-9996}
```

```
check_user_loginState

功能说明：检查手机号的注册状态
参数
userMobile：用户的手机号;

调用方式
member.anjuke.com/memberapi/m/?act=check_user_loginState&userMobile=13262883995
数据返回
可以注册
{"status":"ok","datas":[],"error_message":"\u624b\u673a\u53f7\u7801\u53ef\u7528","error_code":200}
已经被注册
{"status":"error","datas":[],"error_message":"\u60a8\u5df2\u7ecf\u662f\u6211\u4eec\u7684\u9ad8\u7ea7\u4f1a\u5458\uff0c\u65e0\u9700\u518d\u6b21\u6ce8\u518c\u3002","error_code":205}
```

```
check_phonePwd

功能说明：检查其用户和密码是否匹配
参数
uid：用户的UserId;
userPwd：用户的密码

调用方式
member.anjuke.com/memberapi/m/?act=check_phonePwd&uid=7115964&userPwd=123456
数据返回
密码不正确
{
    "status": "error",
    "datas": 0,
    "error_message": "请检查密码",
    "error_code": 107
}
密码正确
{'status' => 'OK', 'datas' => $rz, 'error_message' => ' ', 'error_code' => 0}
```

```
update_password

功能说明：需要新老密码确认修改密码
update_password
参数
old:用户的老密码
new:用户的新密码
uid:被修改密码的用户id
http://member.anjuke.test/memberapi/m/?act=update_password&uid=9037909&old=1234567&new=123456

老密码错误
{
    "status": "error",
    "error": 118,
    "message": "当前密码不正确"
}

成功修改
{
    "status": "ok"
}
```

```
update_password

功能说明：需要新老密码确认修改密码
update_password
参数
old:用户的老密码
new:用户的新密码
uid:被修改密码的用户id
http://member.anjuke.test/memberapi/m/?act=update_password&uid=9037909&old=1234567&new=123456

老密码错误
{
    "status": "error",
    "error": 118,
    "message": "当前密码不正确"
}

成功修改Master.php文件里的接口如下<hr />
```

```
update_email

功能说明：更改邮箱接口
参数
uid:用户的UserId
pwd:用户的密码
email:修改后的邮箱
请求示例
http://member.anjuke.test/memberapi/m/?act=update_email&uid=9037909&email=tracyzhou@anjuke.com

成功修改
{
    "status": "ok"
}
错误信息
{
    "status": "error",
    "error": 118,
    "message": "当前密码不正确"
}
```


## 用户中心对外api整理---slave篇


```
调用方式如下
代码级别的调用方式一般是通过curl;
请求地址示例：member.anjuke.test/memberapi/m/act={"method_name"}&params={"params"}
通用参数说明：
act（必须有）：代表调用的哪个接口函数.
prams：对应接口函数要求传入的参数值
```

通用错误代码说明<hr />
```
-9999 参数不完整
-9998 参数无效
```

### slave部分
对Slave.php文件里的接口如下<hr />

```
#### get_user_info
功能说明：根据UserId获取用户的信息
参数
uid：用的UserId；
调用方式
member.anjuke.test/memberapi/s/?act=get_user_info&uid=123
查询成功
return $ArrayInfo,值为指定uid的用户信息。
查询失败
return false
```

```
#### get_user_infos
功能说明：根据传入的一些列UserId值获取这些用户的信息
参数
uids：以逗号分隔的UserId字符串；
调用方式
member.anjuke.test/memberapi/s/?act=get_user_infos&uids=123,1234
查询成功
return $ArrayInfo,值为多个uid的用户信息。
查询失败
return false
```

```
#### check_username
功能说明：判断用户名是否存在，存在返回true，否则返回false
参数
username：需要判断的用户名;
调用方式
member.anjuke.test/memberapi/s/?act=check_username&username=继诚
查询成功
return true
查询失败
return false
```

```
#### check_username_invalid
功能说明：判断用户名是否合法，合法返回true，否则返回false
参数
username：需要判断的用户名;
调用方式
member.anjuke.test/memberapi/s/?act=check_username_invalid&username=继诚
查询成功
return true
查询失败
return false
```

```
#### check_email
功能说明：判断这个邮箱是否被注册
参数
email：邮箱的值;
调用方式
member.anjuke.test/memberapi/s/?act=check_email&email=tracyzhou@anjuke.com
查询成功
return true
查询失败
return false
```

```
#### check_account
功能说明：判断这个账户的用户名和密码是否匹配
参数
username：用户名;
password：用户密码;
调用方式
member.anjuke.test/memberapi/s/?act=check_email&email=tracyzhou@anjuke.com
查询成功
return true
查询失败
return false
```


