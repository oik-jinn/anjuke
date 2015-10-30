### 1. 依赖外部配置
|配置名称|线下配置|线上配置|类型|作用|
| --- | --- | --- | --- | --- |
| b8_config |array('limit' => 0.95,'classify_url' =>'http://api.anjuke.com/mobile/v5/spam/b8/classify','url_source' =>'weiliao',);"|array('limit' => 0.95,'classify_url' => 'http://api.anjuke.com/mobile/v5/spam/b8/classify','url_source' => 'weiliao',);|其它仓库(new-api)|文字是垃圾信息的该类|
|default| array('class' => 'APF_DFS_MogileFS','mogilefs' => array('trackers' => '192.168.190.163:7001','domain' => 'test','class' => 'testClass',),);||外部地址|分布式文件系统地址|
|mobile_anjuke_prefix_1_3|http://api.anjuke.com/mobile/1.3|http://api.anjuke.com/mobile/1.3|其它仓库(anjuke-mobile-api)|移动api的一种链接前缀|
|mobile_anjuke_prefix_v5|http://api.anjuke.com/mobile/v5|http://api.anjuke.com/mobile/v5|其它仓库(usersite)|移动api的一种链接前缀|
|member_sms_api_prefix|http://member.anjuke.com|http://member.anjuke.com|其它仓库(anjuke-site)|用户中心接口的前缀|
|push_server_prefix|http://app20-011.i.ajkdns.com:8080|http://10.10.3.99|公司服务器地址|微聊消息推送长连接地址|
|apns_server_prefix|http://app20-011.i.ajkdns.com:50090|http://api.anjuke.com/common/notify-apns-api/1.0|公司服务器地址|apns 推送地址|
|upd1_ajkimg_prefix|http://upd1.ajkimg.com|http://upd1.ajkimg.com|公司服务|图片服务器地址|
|mobile_ajk_broker_prefix_1_0|http://api.anjuke.com/mobile-ajk-broker/1.0|http://api.anjuke.com/mobile-ajk-broker/1.0|公司内部api|经纪人api接口前缀|
|chatapi_anjuke_prefix|http://chatapi.dev.anjuke.com|http://api.anjuke.com/weiliao|公司内部api|微聊api的前缀|
|pic1_ajkimg_prefix|http://pic1.ajkimg.com|http://pic1.ajkimg.com|图片地址的前缀|图片地址前缀|
|knowing_prefix|http://10.10.3.43:9075|http://10.10.3.43:9075|公司服务器地址|knowing地址|
|mobile_web_prefix|http://192.168.192.175/user-api|http://api.anjuke.com/web|其它仓库(new-api)|移动api的一种链接前缀|
|send_sms_api_prefix|http://ajksms.kfs.dev.anjuke.test/send.php|http://ajksms.a.ajkdns.com/send.php|公司服务|短信服务|
|broker_chatId_to_brokerId|http://10.10.6.6:8080|http://10.10.6.6:8080|公司服务|查询经纪人信息接口|
|amtp_server_prefix|http://amtp.anjuke.com:8888|10.10.3.90:8888|公司服务器|通过amtp向app发送数据|
|push_group| array('endpoints' => array('tcp://10.249.7.17:9228' ), 'enable_extend_header' => true, 'client_sync_call_timeout' => 2000);|array('endpoints' => array('tcp://10.10.3.91:9228'),'enable_extend_header' => true,'client_sync_call_timeout' => 2000)|公司服务器|消息推送|
|apns_group|array('endpoints' => array('tcp://10.249.7.17:1850'),'enable_extend_header' => true,'client_sync_call_timeout' => 2000);|array('endpoints' => array('tcp://10.10.3.92:1850','tcp://10.10.3.93:1850',),'enable_extend_header' => true,'client_sync_call_timeout' => 2000);|公司服务器|apns消息推送|
|stringmatch_group|array('endpoints' => array('tcp://10.20.6.40:8994',),'enable_extend_header' => true, 'client_sync_call_timeout' => 2000);|array('endpoints' => array('tcp://10.10.9.47:8994'),'enable_extend_header' => true,'client_sync_call_timeout' => 100);|公司服务器|敏感词过滤|
|monitor_apns_0|array('endpoints' => array('tcp://10.249.7.17:1850'),'enable_extend_header' => true,'client_sync_call_timeout' => 2000);|array('endpoints' => array('tcp://10.10.3.92:1850',),'enable_extend_header' => true,'client_sync_call_timeout' => 500);|公司服务器|远程调用|
|monitor_apns_http_urls|array(http://10.249.7.17:1800/serverStatus?jsonOut=1)| array('http://app10-128.i.ajkdns.com:1800/serverStatus?jsonOut=1','http://app10-129.i.ajkdns.com:1800/serverStatus?jsonOut=1',);|公司服务器|konwing监控数据|
|monitor_convert_http_urls|array(http://10.249.7.17:1904/notify-apns-api/apns/getMonitor/?prefix=CONVERT)|array('http://app10-128.i.ajkdns.com:1904/notify-apns-api/apns/getMonitor/?prefix=CONVERT','http://app10-129.i.ajkdns.com:1904/notify-apns-api/apns/getMonitor/?prefix=CONVERT',);|公司服务器|knowing监控数据|

### 2. 数据库依赖
### 2.1 chat-db
|db 名称|表名称|域名|读写|公用/独有|谁的业务在读|
| --- | --- | --- | --- | --- | --- |
|chat_db|broadcast_msg_basic|http://api.anjuke.com/weiliao/message/sendBroadcastMessage/|读写|独有|对用户发送的广播消息|
|chat_db|broadcast_msg_basic_app|http://api.anjuke.com/weiliao/message/sendBroadcastMessage/|读写|独有|微聊,对设备发送的广播消息|
|chat_db|broadcast_msg_receive_last|http://api.anjuke.com/weiliao/message/getNewMessages, http://api.anjuke.com/weiliao/message/getAllNewMessages|读写|独有|微聊拉取用户未读消息|
|chat_db|broadcast_msg_receive_last_app|http://api.anjuke.com/weiliao/message/app/getNewMessages, http://api.anjuke.com/weiliao/message/app/getAllNewMessages|读写|独有|微聊拉取设备未读消息|
|chat_db|broker_ext|http://api.anjuke.com/weiliao/message/changeRemindBrokerSwitchStatus|读写|独有|查看经纪人是否开通短信提醒服务|
|chat_db|broker_info|http://api.anjuke.com/weiliao/user/addFriend/|读写|独有|微聊添加朋友|
|chat_db|chat_basic|http://api.anjuke.com/weiliao/message/sendAppMessageFromServer|读写|独有|微聊回话信息|
|chat_db|device_basic|http://api.anjuke.com/weiliao/user/register/|读写|公用|微聊中设备基本信息及new-api的job|
|chat_db|device_setting|http://api.anjuke.com/weiliao/app/setting|读写|独有|微聊设备设置,主要是是否允许推荐|
|chat_db|focus_account|http://api.anjuke.com/weiliao/user/getRelation, http://api.anjuke.com/weiliao/user/getFriends|读写|公用|微聊获得用户的关注列表,new-api的job在用|
|chat_db|focus_app|http://api.anjuke.com/weiliao/user/getAccountInfo|读写|公用|设备关注信息,new-api的job在用|
|chat_db|job_remind_broker|无|读写|独有|微聊job在用,记录提醒'经纪人需要回复任务'的信息|
|chat_db|menu_basic|http://api.anjuke.com/weiliao/user/innerService/createMenu|读写|独有|菜单信息|
|chat_db|menu_group|http://api.anjuke.com/weiliao/user/innerService/createMenuGroup|读写|独有|菜单组信息|
|chat_db|msg_basic|http://api.anjuke.com/weiliao/message/sendDeviceMessage,http://api.anjuke.com/weiliao/message/getAllNewMessages|读写|公用|微聊消息(发给用户),以及user-site下的job(用于信息的归档)|
|chat_db|msg_basic_app| http://api.anjuke.com/weiliao/message/app/getAllNewMessages|读写|公用|微聊消息(发给设备),以及user-site下的job(用于信息的归档)|
|chat_db|msg_black_list|http://api.anjuke.com/weiliao/message/sendDeviceMessage,http://api.anjuke.com/weiliao/message/setMsgBlackList|读写|独有|加入黑名单的用户|
|chat_db|msg_black_list_app|http://api.anjuke.com/weiliao/message/sendDeviceMessage,http://api.anjuke.com/weiliao/message/app/setMsgBlackList|读写|独有|加入黑名单的设备|
|chat_db|msg_ext|http://api.anjuke.com/weiliao/message/getAllNewMessages|读写|公用|微聊消息,以及user-site下的job(用于信息的归档)|
|chat_db|msg_ext_origin|http://api.anjuke.com/weiliao/message/getAllNewMessages|读写|公用|微聊消息,以及user-site下的job(用于信息的归档)|
|chat_db|msg_type|http://api.anjuke.com/weiliao/message/getAllNewMessages|读写均无|独有|消息的类型常量,代码中没有读,写数据库的代码,只是使用了DAO中的常量|
|chat_db|no_reply_brokers|无|读写|独有|未回复经纪人数据,job在写|
|chat_db|public_service_relation|http://api.anjuke.com/weiliao/message/sendFriendMessage/,http://api.anjuke.com/weiliao/message/sendAccountMessage|读写|独有|服务号和用户类型之间的好友关系,用于发送消息|
|chat_db|service_basic|http://api.anjuke.com/weiliao/message/sendAppMessage,http://api.anjuke.com/weiliao/user/getFriendInfo/|读写|独有|服务号和公众号的基本信息|
|chat_db|user_app_chat_relation|http://api.anjuke.com/weiliao/message/sendAppMessage|读写|独有|用户和设备之间的好友关系|
|chat_db|user_basic|http://api.anjuke.com/weiliao/user/register/,http://api.anjuke.com/weiliao/user/login/|读写|公用|用户的基本信息,new-api在读http://api.anjuke.com/ershou/prop/require/myRequire|
|chat_db|user_broker_mapping|http://api.anjuke.com/weiliao/user/register/|读写|公用|用户id和经纪人id之间的对应关系,new-api的job(Mobile_Job_BrokerNewPropNotice),在用|
|chat_db|user_device_mapping|http://api.anjuke.com/weiliao/message/sendDeviceMessage|读写|独有|用户和设备id之间的对应关系|
|chat_db|user_ext|http://api.anjuke.com/weiliao/user/modifyInfo/|读写|独有|用户的其它信息,主要是第三方信息|
|chat_db|user_ext_mapping|http://api.anjuke.com/weiliao/user/modifyInfo/|读写|独有|用户和第三方的对应关系,|
|chat_db|user_last_access|http://api.anjuke.com/weiliao/user/login/|读写|公用|用户最后一次登录时间以及使用的设备信息,new-api中推送消息的job在使用|
|chat_db|user_login_history|http://api.anjuke.com/weiliao/user/login/|读写|独有|记录用户的登录历史|
|chat_db|user_password|http://api.anjuke.com/weiliao/user/modifyPassword/|读写|独有|记录用户的密码|
|chat_db|user_phone_mapping|http://api.anjuke.com/weiliao/user/register/|读写|独有|记录用户的id和手机号的对应关系|
|chat_db|user_relation|http://api.anjuke.com/weiliao/message/sendFriendMessage/,http://api.anjuke.com/weiliao/message/sendAccountMessage|读写|独有|记录好友关系的相关信息|
|chat_db|user_relation_category|http://api.anjuke.com/weiliao/user/getFriendInfo|只读|独有|记录好友关系中关系的分类,在表中数据为空|
|chat_db|user_relation_ext|http://api.anjuke.com/weiliao/user/addFriend/|读写|独有|记录好友关系中加好友时的附加信息|
|chat_db|user_type||不读,不写|独有|账户类型表,在业务中只是使用dao里的常量,但是没有读写表|
#### 2.1 chat-db
|db 名称|表名称|域名|读写|公用/独有|谁的业务在读|
| --- | --- | --- | --- | --- | --- |
|job_db|job_runed_cursor|job在使用|读写|公用|微聊|
### 3. [Memcache&Redis依赖](http://gitlab.corp.anjuke.com/_site/docs/blob/master/API/%E5%AE%89%E5%B1%85%E5%AE%A2/cache/memcache_redis.md)
### 4. 主要Job
##### 微聊的job没有部署在[JOB统一管理平台](http://drone.corp.anjuke.com)上,它们单独部署在app-117上,使用的是crontab,job的命令文件为 /home/www/bin/weiliao_check.sh
#### 4.1 处理消息队列的job  Chat_Job_Message_NotifyProcess
##### 启动此类 job的命令格式如下
    /usr/local/anjuke-php-fpm/bin/php /home/www/config/v2/anjuke-chat/chat-job/launcher.php --class=Chat_Job_Message_NotifyProcess --queue_name=chat_json_register_device_queue
|队列名称|功能|
| --- | --- |
|chat_queue_app_notify|app端长连接通知|
|chat_register_amtp_user_queue|amtp 用户注册通知队列|
|chat_queue_app_amtp_notify|处理app端amtp通知|
|chat_queue_push_device_message_for_ios|ios 推送消息队列|
|chat_queue_push_message_retry_for_ios|ios 推送消息重试队列|
|chat_json_register_device_queue|设备注册长连接队列|
|account_online_status_notify_http_chunk|http chunk账户在线状态变更通知队列|
|hat_queue_account_message_batch_send|批量发送用户消息队列|
|chat_queue_web_notify|处理web端长连接通知|
|hat_queue_device_login_notify|处理设备登录通知队列用于猜你喜欢|
|chat_queue_device_info|采集安装app设备信息|
|chat_register_user_queue|用户注册上后检测用户是否有新消息有新消息则发送通知|
|chat_queue_no_reply_sms_notify|处理未回复短信通知消息|
|chat_queue_chat_basic_process|处理会话信息|
|account_online_status_notify_apns|消费苹果推送账户在线状态变更通知队列|
|chat_queue_read_broadcast_message_notify|处理用户读取广播消息回执队列|
|chat_queue_read_app_broadcast_message_notify|处理设备读取广播消息回执队列|
|chat_queue_read_message_sync_notify|同步回话信息|
|chat_queue_read_message_notify|已经读取消息回执对列|
|chat_queue_new_device_message_queue|设备更新消息通知队列|
|chat_queue_read_app_message_notify|处理读取设备消息回执队列|
|chat_queue_amtp_reply|处理读取设备消息回执队列|
|chat_queue_amtp_reply|处理amtp回复信号|
##### 4.2 检测通过1小时内从WEB进来，注册微聊的用户数量
    /bin/sh -c /usr/local/php-fpm/bin/php /home/www/config/v2/anjuke-chat/chat-job/launcher_beta.php –class=Chat_Job_Monitor_Chat –action=doGetRegisterUserCountFromWeb >> /data1/logs/push/doGetRegisterUserCountFromWeb.log
##### 4.3 市场推送 推送频次：每天11:30 每周五15:00
    30 11 * * * /usr/local/anjuke-php-fpm/bin/php /home/www/config/v2/user-site/app-mobile-job/launcher.php –class=Mobile_Job_OldUserPushByDevice >> /data1/logs/v2/user-job/pushchat/Mobile_Job_OldUserPushByDevice_20150525.log

    0 15 * * 5 /usr/local/anjuke-php-fpm/bin/php /home/www/config/v2/user-site/app-mobile-job/launcher.php –class=Mobile_Job_OldUserPushByDevice >> /data1/logs/v2/user-job/pushchat/Mobile_Job_OldUserPushByDevice_20150515.log
