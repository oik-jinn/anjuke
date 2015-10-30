### 依赖配置整理
#### anjuke-chat
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