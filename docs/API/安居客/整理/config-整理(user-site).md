## 1 外部配置
### 1.1 [app-chat-web](http://gitlab.corp.anjuke.com/_online-config/usersite-config/tree/master/config/chat-web)
##### 没有删除的配置
### 1.2 [app-member-web](http://gitlab.corp.anjuke.com/_online-config/usersite-config/tree/master/config/member-web)
##### 没有删除的配置

## 2 内层配置(需要添加到外层的配置)
### 2.1 app-chat-web
#### 2.1.1 需要添加到外层的配置(无)
### 2.2 app-member-core
##### 2.2.1 文件 config/common.php
    $config['anjuke_index_page'] = 'http://www.anjuke.com';
    $config['aifang_index_page'] = 'http://fang.anjuke.com';
    $config['haozu_index_page'] = 'http://www.haozu.com';
    $config['jinpu_index_page'] = 'http://www.jinpu.com';
##### 2.2.2 文件 config/login.php
    $config['user_center_base_domain'] = 'user.anjuke.com';
### 2.3 app-mobile-api
##### 2.3.1 文件 config/weiliao.php
    $config['recommend_url'] = 'http://api.anjuke.com/anjuke/4.0/property/rec/home';
    $config['send_message_url'] = 'http://api.anjuke.com/weiliao/message/publicservice/sendMessage/{service_id}/1?from_idc=1';
    $config['get_loupan_list'] = 'http://api.fang.anjuke.com/m/iphone/1.3/loupan/IDList/';
    $config['get_zf_list'] = 'http://api.haozu.com/mobile/2.1/searchbypropids';
    $config['api_xinfang_loupan'] = 'http://api.fang.anjuke.com/m/iphone/1.3/loupan/';
### 2.4 app-mobile-core
##### 2.4.1 需要添加到外层的配置(无)
### 2.5 app-mobile-job
##### 2.5.1 文件 config/common.php
    $config['user_push_msg_url'] = 'http://api.a.ajkdns.com/amtp-proxy/message/send/';
    $config['broker_update_location_url'] = 'http://api.anjuke.com/mobile-ajk-broker/1.0/broker/updateLocation/';
    $config['char_push_service_url'] = "http://api.anjuke.com/weiliao";
### 2.6 app-mobile-web
##### 2.6.1 文件 config/url.php
    $config['get_chat_content'] = 'http://api.anjuke.com/anjuke/4.0/chat/content';
    $config['get_sale_content'] = 'http://api.anjuke.com/mobile/1.3/property.get';
    $config['get_community_round'] = 'http://api.anjuke.com/anjuke/4.0/community/round';
    $config['get_rent_content'] = 'http://api.haozu.com/mobile/2.0/property.get';
### 2.7 app-site-api
##### 2.7.1 需要添加到外层的配置(无)
### 2.8 app-user-api
##### 2.8.1 需要添加到外层的配置(无)
### 2.9 app-public-api,app-public-core,app-public-job
##### 2.9.1 需要添加到外层的配置(无)

