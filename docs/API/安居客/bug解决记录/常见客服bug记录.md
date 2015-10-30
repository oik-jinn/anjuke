## API整理－常见客服bug记录

### 常见微聊相关问题

#### 问题1
* 问题描述

    ```
    1、微聊无法使用，点击进去显示经纪人不存在
    2、chatID为空
    ```
* 问题原因

    ```
    经纪人修改了手机号码，没有同步到微聊表中，微聊时会去经纪人微聊表中验证该手机号是否有信息，没有则不能微聊
    1、经济人A用了手机号a
    	anjuke_db.ajk_brokerextend中A的手机号是a
    	chat_db.user_basic中A的手机号是a
    	
    2、经纪人A更换了手机号为b
    	anjuke_db.ajk_brokerextend中A的手机号是b
    	chat_db.user_basic中A的手机号仍是a
    	
    3、经纪人A或者是别的经纪人（统称为经纪人B）再用手机号a注册账号
    	anjuke_db.ajk_brokerextend中A的手机号是b
    	anjuke_db.ajk_brokerextend中B的手机号是a
    	chat_db.user_basic中A的手机号仍是a
    	因为a已经被用，所以数据插入失败，导致微聊不能进行
    	(chat_db.user_basic表的唯一索引：phone+user_type)
    ```
* 举例

    ```
    用户名:18911830197
    密码:liuxindou521
    ```

* 临时解决方案

    ```
    	1、先看下不能微聊的手机号码在微聊信息表中被哪个经纪人占用
    		DB:
    			chat_db
    		SQL:
    			SELECT * FROM user_basic where `phone`=18911830197 ORDER BY `user_id` LIMIT 0,100
    		结果：
    			broker_id：1623488
    	2、看下该经纪人在经纪人信息表中的手机号码（理论上手机号不是上面的那个了）
    		DB:
    			anjuke_db
    		SQL:
    			SELECT * FROM ajk_brokerextend where `BrokerId`= 1623488 ORDER BY `BrokerId` LIMIT 0,100
    		结果：
    			UserMobile: 1891183019X
    	3、修改微聊信息表中经纪人的手机号为2中查出来的，保证该经纪人手机号码两边一致
    		DB：
    			chat_db
    		SQL：
    			UPDATE user_basic SET phone = '1891183019X' WHERE broker_id = 1623488;
    ```

* 最终解决方案

    ```
    找到手机号码没有被同步的根本原因
    手机号码修改同步过程：
    	1、经纪人修改手机号码，会发送一个通知到消息中间件
    	2、消息中间件落表
    		action_db.log_merge_quene_new
    	3、常驻job从2中的表中读取需要更新的数据，调用api接口更新手机号码
    	4、api接口：/user/modifyBrokerInfo
    	
    其中1、2、3是经纪人端的操作，4是用户端的api
    之前出现过2没有落表的情况，经纪人端已经修复
    目前问题最有可能出现在3或者4
    ```
    
* TODO

    ```
    最终解决这个问题，并且修复历史问题数据
    ```