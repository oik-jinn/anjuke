# 用户中心JOB整理
#### 1、icp审查增加的用户信息的Job
##### /home/www/user/job-script/while_true.sh php /home/www/v2/jobs/launcher.php icp/examine.php

> * 脚本创建人：徐建龙
> * 脚本类型：常驻进程
> * 脚本功能：针对icp公安局审查增加的用户信息
> * 脚本涉及DB：ajk_members、
> * 功能描述如下： 

```des
    private $progressReg = '/home/tracyzhou/logs/icp/user_reg_p.log'; //注册用户
    private $progressLogin = '/home/www/logs/icp/user_login_p.log'; //登录用户

    从ajk_members表中根据注册时间、登录时间获取用户信息分别写入icp的日志文件

```
#### 2、记录用户访问房源单页数据Job（已暂停）
##### /bin/sh /home/www/user/job-script/while_true.sh php /home/www/v2/jobs/launcher.php history/Listener.php

> * 脚本创建人：刘张健
> * 脚本类型：常驻进程
> * 脚本功能：记录用户访问房源单页数据,监听用户访问行为
> * 脚本涉及DB：无
> * 功能描述如下：

```des
    根据产生vppv的访问记录的日志队列，监听用户行为
```
    
#### 3、修复用户中心username Job
##### php /home/www/user/usersite/app-public-job/launcher.php --class=Public_Job_Member_UserNameUpdateJob

> * 脚本创建人：黄亚雄
> * 脚本类型：定时脚本
> * 脚本功能：修复用户中心username
> * 脚本涉及DB：ajk_members、ajk_membersother、ajk_membersextend、ajk_member_logins
> * 功能描述如下： 

```des
    核心代码
    $_username = str_replace(array("@", "."), "_", $username);
    if(is_numeric($_username)){
       $_username = $_username.'_'.sprintf("%02d", rand(1, 99));
    }
    
    处理用户名中的特殊字符
```

#### 4、第三方登录错误数据删除Job
##### php /home/www/v2/jobs/launcher.php member/delExtmemberMapping.php

> * 脚本创建人：刘张健
> * 脚本类型：定时脚本 cron表达式【30 30 2 * * ? *】
> * 脚本功能：第三方登录错误数据删除 user_id = 0 的数据
> * 脚本涉及DB：ext_member_mapping 
> * 功能描述如下： 

```des
    核心代码
    $sql   = "DELETE FROM ext_member_mapping WHERE user_id=0";
          $stmt  = $this->pdo->prepare($sql);
          $stmt->execute();
```

#### 5、删除用户登录脏数据Job 
##### php /home/www/v2/jobs/launcher.php RemoveAjkMemberLoginDirtyData.php

> * 脚本创建人：王其春
> * 脚本类型：临时脚本
> * 脚本功能：删除用户登录脏数据
> * 脚本涉及DB：ajk_member_logins 
> * 功能描述如下： 

```des
    核心代码
    $sql = "DELETE FROM ajk_member_logins WHERE LoginId = ? and UserId=0";
        $stmt = $pdo->prepare($sql);
        $stmt->execute(array($login_id));
```    

#### 6、【用户中心】用户头像处理Job 
##### php /home/www/user/usersite/app-ershou-job/launcher-beta.php --class=Public_Job_Member_UpdateUserPhoto

> * 脚本创建人：徐晓路
> * 脚本类型：临时脚本
> * 脚本功能：头像由原来的绝对路径改为存储32位的imgid
> * 脚本涉及DB：ajk_membersother 
> * 功能描述如下： 

```des
    把头像url地址更改成 32位的imgid
    如：
    http://d.pic1.ajkimg.com/display/anjuke/7b8dbe931d4b0149852dc4f9dc0f6f5d/350x466x0x24/100x133c.jpg
    =>
    {"height":125,"hash":"6cfb60420d44feb9300a2dd4ab2a4d6f","format":"JPEG","id":"6cfb60420d44feb9300a2dd4ab2a4d6f","exists":1,"width":495,"size":53834,"host":1}
```  



