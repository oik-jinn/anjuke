## API DB 整理

### `mobile_api_db`

|表名|读/写|说明|涉及接口
|---|---|---|---|
| fortune_item|读|财富坚定|/4.0/comm/identify/|
| fortune_price_period |读|获取鉴定价格信息|/4.0/comm/identify/|
| identify_text |读|获取鉴定相关信息|/4.0/comm/identify/|
| user_focus |读|获取小区关注列表|/4.0/comm/focuslist/|
| user_focus |读|小区关注接口（关注/取消关注）|/4.0/comm/focus/|
| user_focus |读|获取小区关注状态|/4.0/comm/focusstatus/|
| user_focus |读|添加对小区的关注|/4.0/comm/addfocus/|
| user_focus |读|取消小区关注|/4.0/comm/removefocus/|
| user_focus |读|小区单页接口获取小区关注量|/anjuke/4.0/comm/get|
| focus_count |读|获取小区关注量|/4.0/comm/focuscount/|

* anjuke-mobile-api
    * fortune_item 
    
        |使用接口|说明|是否在用|
    |---|---|---|
    |/anjuke/4.0/comm/identify/|财富坚定|待确认|
        ```
        使用SQL:
        SELECT * FROM WHERE price >= ? AND price <= ?
        ```
     * fortune_price_period 
    
        |使用接口|说明|是否在用|
    |---|---|---|
    |/anjuke/4.0/comm/identify/|财富坚定|待确认|
        ```
        使用SQL:
        SELECT * FROM fortune_price_period WHERE lower_price <= ? AND upper_price >= ?
        ```

    * identify_text 
    
        |使用接口|说明|是否在用|
    |---|---|---|
    |/anjuke/4.0/comm/identify/|财富坚定|待确认|
        ```
        使用SQL:
        SELECT * FROM identify_text WHERE identify_id = ? ?
        ```
    * user_focus

        |使用接口|说明|是否在用|
    |---|---|---|
    |/anjuke/4.0/comm/focuslist/|获取小区关注列表|待确认|
    |/anjuke/4.0/comm/focus/|小区关注接口（关注/取消关注）||
    |/anjuke/4.0/comm/focusstatus/|获取小区关注状态||
    |/anjuke/4.0/comm/addfocus/|添加对小区的关注||
    |/anjuke/4.0/comm/removefocus/| 取消小区关注 ||
    |/anjuke/4.0/comm/get|小区单页接口获取小区关注量|||

        ```
        获取设备对小区的关注状态
SELECT `id` FROM `user_focus` WHERE `item_id` = ? AND `type_id` = ? AND `device_id` = ?
获取用户对小区的关注状态(无用sql)
SELECT `id` FROM `user_focus` WHERE `item_id` = ? AND `type_id` = ? AND `user_id` = ?
添加关注信息
INSERT INTO user_focus (item_id, type_id, device_id, user_id, source_id, deleted, last_update) VALUES(?,?,?,?,?,?,?)
关注/取消关注
UPDATE `user_focus` SET `deleted` = ? , `last_update` = ? WHERE `id` = ?
根据主键获取关注信息
SELECT * FROM `user_focus` WHERE `id` = ?
获取设备对某一类物品的关注列表
SELECT `id`,`item_id` FROM `user_focus` WHERE `device_id` = ? AND `type_id` = ? AND `deleted` = ?
获取用户对某一类物品的关注列表
SELECT `id`,`item_id` FROM `user_focus` WHERE `user_id` = ? AND `type_id` = ? AND `deleted` = ?
        ```
    * focus_count
        |使用接口|说明|是否在用|
    |---|---|---|
    |/anjuke/4.0/comm/focuscount/|获取小区关注量|
            
        ```
        获取关注量
SELECT COUNT(*) as focus_count FROM user_focus WHERE item_id = ? AND type_id = ? AND deleted = ?
        ```

    * fix_comm_count
        |使用接口|说明|是否在用|
    |---|---|---|
    |/anjuke/4.0/comm/get|针对小区浏览量和关注量进行修正|
            
        ```
        获取小区的修正浏览量getFixCount()
SELECT * FROM `fix_comm_count` WHERE `comm_id` = ?
        添加小区的修正浏览量addFixCount()
INSERT INTO `fix_comm_count` (`comm_id`, `fix_view_count`,`fix_focus_count`) VALUES(?,?,?)
        删除小区的修正浏览量deleteFixCount()
DELETE FROM `fix_comm_count` WHERE `comm_id` = ?
        ```

    * id_from_phone_number
    
        |使用接口|说明|是否在用|
    |---|---|---|
    |/4.0/marketing/invitation|市场邀请活动:根据参与码查询手机信息|
    |/4.0/marketing/home|市场活动首页|
    |/4.0/marketing/ranking/total|市场邀请活动总排行榜 |
    |/4.0/marketing/awards/total|市场邀请活动总奖励|
    |/4.0/marketing/ranking/search|市场邀请活动排行信息搜索|
    |/4.0/marketing/register|市场活动注册手机获取邀请码|
    |/4.0/marketing/awards/totalbydaily|市场活动总奖励（按每日划分）|
    |/4.0/marketing/awards/daily|市场活动每日奖励|
            
        ```
        获取指定小组下手机号对应的编号selectByGroupAndPhoneNumber()
SELECT * FROM `id_from_phone_number` WHERE `group` = ? and `phone_number` = ?
        insertData()
insert into `id_from_phone_number` (`phone_id`,`group`,`phone_number`) values(?,?,?)'
        根据手机唯一编号获取手机信息selectByPhoneId()
SELECT * FROM `id_from_phone_number` WHERE `phone_id` = ? AND `group` = ? limit 1"
        根据手机唯一编号获取手机信息selectByPhoneIds()
SELECT * FROM `id_from_phone_number` WHERE `phone_id` IN (" . implode(',', $modes) . ") AND `group` = ?"
        ```

    * id_generator
        |使用接口|说明|是否在用|
    |---|---|---|
    |/4.0/generator/id|发号器接口|
    |/4.0/generator/max|发号器当前最大编号查询|

        ```
        INSERT INTO `id_generator` (`source`) values(?)
获取最大id的记录getMax()
SELECT * FROM `id_generator` order by `id` desc limit 1
```

    * view_comm_price_history
    
        |使用接口|说明|是否在用|
    |---|---|---|
    |/anjuke/4.0/guess/commlist|可能会感兴趣的小区|

        ```
        insertView()
INSERT INTO `view_comm_price_history` (`device_id`,`comm_id`,`city_id`) VALUES(?,?,?)（已经没地方写了）
获取设备在指定城市下的浏览历史getViewList()
SELECT * FROM `view_comm_price_history` WHERE `device_id` = ? AND city_id = ? ORDER BY id DESC
        ```

### `ajk_dw_stats`
|表名|读/写|说明|涉及接口
|---|---|---|---|
| solly_mpr_open_table |读|一系列推荐接口使用|/mobile/1.3/property.home、/4.0/property/list、/4.0/property/require|
| dw_pricing_comm_quartile |读|小区详情页房价信息|/4.0/community/info|
| dw_pricing_comm_weekly |读|小区详情页房价信息|/4.0/community/info|
| hu_dingcai_broker_score |读|经纪人信息ufs信息|/4.0/broker/chatinfo|
| price_trend_areacode_monthly|读|小区单页接口区域房价信息|/anjuke/4.0/comm/get|
|price_trend_comm_monthly|读|小区详情页月均价|/4.0/community/info
|price_trend_comm_monthly|读|小区单页区间房价|/anjuke/4.0/comm/get
|price_trend_comm_monthly|读|小区搜索接口房价|/mobile/1.3/community.search
|price_trend_comm_monthly|读|小区地图搜索接口房价|/mobile/1.3/community.searchCommunityMap|
|price_trend_comm_monthly|读|小区地图接口房价|/mobile/1.3/community.searchMap|
| hu_guanlian_bk_all_sale、hu_guanlian_sale_new_bu |读|推荐|/mobile/1.2/recommend.guessProperties|
| hu_guanlian_bk_all_sale、hu_guanlian_sale_new_bu |读|推荐|/mobile/1.3/recommend.guessProperties|
| hu_guanlian_bk_all_sale、hu_guanlian_sale_new_bu |读|推荐|/mobile/1.3/recommend.guessPropertiesAndDetail|
| comm_view_count |读|获取小区浏览量|/4.0/comm/viewcount/|
| comm_view_count |读|小区单页信息小区浏览量|/anjuke/4.0/comm/get|

* anjuke-mobile-api
    * solly_mpr_open_table 
    
        |使用接口|说明|是否在用|
    |---|---|---|
    |/mobile/1.3/property.home|首页推荐|待确认|
    |/4.0/property/list|二手房列表接口补充推荐|已迁移|
    |/4.0/property/require|附近关注经纪人推荐|无用|
    
        ```
        使用SQL:
        'select `table_name` from `solly_mpr_open_table` where `type` = "' . $type . '" and `is_done` = ' . $is_done;

        'select `Pro_id` from ' . $tableName . ' where `city_id` = ' . $cityId . ' and `mac_id` = "' . $macid .'" order by score*RAND() desc limit 150';
        ```
    * dw_pricing_comm_quartile、dw_pricing_comm_weekly
    
        |使用接口|说明|是否在用|
    |---|---|---|
    |/4.0/community/info|小区详情页||

        ```
        根据小区ID和日期批量获取小区的日均价信息getInfosByCommIdsAndCalDt()
    "SELECT * FROM dw_pricing_comm_quartile WHERE commid IN (" . implode(',', $modes) . ") AND cal_dt=?";
    
        根据小区ID和星期批量获取小区的周均价getInfosByCommIdsAndCalDt()
    "SELECT * FROM dw_pricing_comm_weekly WHERE commid IN (" . implode(',', $modes) . ") AND cal_dt=?";
    ```

    * hu_dingcai_broker_score
    
        |使用接口|说明|是否在用|
    |---|---|---|
    |/4.0/broker/chatinfo|经纪人微聊信息|

        ```
        根据经纪人ID批量获取信息selectByBrokerIds() 根据经纪人ID获取ufs信息
    "SELECT * FROM hu_dingcai_broker_score WHERE broker_id IN (" . implode(',', $modes) . ")";

        根据经纪人ID获取信息selectByBrokerId()
    "SELECT * FROM hu_dingcai_broker_score WHERE broker_id = ? limit 1"
    ```

    * price_trend_areacode_monthly
    
        |使用接口|说明|是否在用|
    |---|---|---|
    |/anjuke/4.0/comm/get|小区单页|

        ```
        批量获取区域板块在区间内的房价数据getInfosByAreacodesAndPeriods()
    "SELECT * FROM price_trend_areacode_monthly WHERE areacode IN (" . implode(',', $modes) . ") AND period >= ? and period <= ?";
        
        获取区域板块在区间内的房价数据getInfosByAreacodeAndPeriods()
    "SELECT * FROM price_trend_areacode_monthly WHERE areacode = ? AND period >= ? and period <= ?";
        ```

    * price_trend_comm_monthly
    
        |使用接口|说明|是否在用|
    |---|---|---|
    |/4.0/community/info|小区详情页月均价|
    |/anjuke/4.0/comm/get|小区单页区间房价|
    |/mobile/1.3/community.search|小区搜索接口房价|
    |/mobile/1.3/community.searchCommunityMap|小区地图搜索接口房价|
    |/mobile/1.3/community.searchMap|小区地图接口房价|

        ```
        根据小区ID和星期批量获取小区的周均价getInfosByCommIdsAndPeriod()
    "SELECT * FROM price_trend_comm_monthly WHERE comm_id IN (" . implode(',', $modes) . ") AND period=?";
        
        批量获取小区在区间内的房价数据getInfosByCommIdsAndPeriods()
    "SELECT * FROM price_trend_comm_monthly WHERE comm_id IN (" . implode(',', $modes) . ") AND period >= ? and period <= ?";

        获取小区在区间内的房价数据getInfosByCommIdAndPeriods()
    "SELECT * FROM price_trend_comm_monthly WHERE comm_id = ? AND period >= ? and period <= ?";

        get_by_commids()
    "SELECT comm_id,period,areacode,avg_price,mid_price,mid_change,min_price,max_price,listed_properties,is_rank_by_city,is_rank_by_region,is_rank_by_subregion FROM price_trend_comm_monthly where period=? AND comm_id in (".implode(",", array_fill(0, count($cIds), "?")).")";
        ```
    
    * hu_guanlian_bk_all_sale
    
        |使用接口|说明|是否在用|
    |---|---|---|
    |/mobile/1.2/recommend.guessProperties|推荐|
    |/mobile/1.3/recommend.guessProperties|推荐|
    |/mobile/1.3/recommend.guessPropertiesAndDetail|推荐|

        ```
        根据看过的房源获取相似的房源（推荐）get_similar_proprities_old()
    select sid1 from hu_guanlian_bk_all_sale where sid=$pro_id limit $pro_num

        get_similar_proprities_new()
    select sid1 from hu_guanlian_sale_new_bu where sid=$pro_id limit $pro_num
        ```

    * comm_view_count
        
        |使用接口|说明|是否在用|
    |---|---|---|
    |/4.0/comm/viewcount/|获取小区浏览量|
    |/anjuke/4.0/comm/get|小区单页信息小区浏览量|

        ```
        根据小区ID获取小区浏览量信息getInfoByCommId()
    SELECT * FROM `comm_view_count` where `commid` = ?
        ```
    
### chat-db
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
### anjuke_db
|db 名称|表名称|域名|读写|公用/独有|谁的业务在读|
| --- | --- | --- | --- | --- | --- |
|anjuke_db|ajk_members|http://user.anjuke.com/register|读写|公用|此表存储用户基本信息,用户注册,登录,修改个人信息等业务均会操作此表,anjuke-site,user-site 均使用了此表|
|anjuke_db|ajk_membersother|http://user.anjuke.com/ajax/verifymobile|读写|公用|此表存储用户的扩展信息,包含用户所在的小区,城市,用户头像等信息,用户注册,获得用户信息,绑定手机号会读写这张表,anjuke-site,user-site 均使用了此表|
|anjuke_db|ajk_member_logins|http://user.anjuke.com/register|读写|公用|此表存储用户的登录信息,用户的注册(查询手机号是否被占用),登录均用到了此表,anjuke-site,user-site 均使用了此表|
|anjuke_db|ajk_membersextend|http://user.anjuke.com/api/broker/add|读写|公用|此表存储了用户的扩展信息(主要是经纪人),包括用户回答的问题,用户的的信誉评价,发布的房源质量等信息,anjuke-site,user-site 均使用了此表|
|anjuke_db|ajk_checkcode|http://user.anjuke.com/member/modify/mail|读写|公用|此表主要存储验证信息,用于邮箱验证 anjuke-site,user-site 均使用了此表|
|anjuke_db|ajk_getpasswd|http://user.anjuke.com/pass?type=forget|读写|独有|此表存储了用户找回密码的映射关系,主要用于用户找回密码,用找回的密码登录,|
|anjuke_db|ajk_subscribe_email|http://user.anjuke.com/ajax/price/subscribe/|读写|独有|此表存储了用户订阅的邮箱信息,主要用于用户的订阅|
|anjuke_db|ajk_member_options|http://member.anjuke.com/extcontact/|读写|公用|此表存储用户的扩展标签,即用户的选项标签,比如用户提交了手机号,是一种标签,提交了手机号也是一种标签,anjuke-site, user-site 均在使用此表|
|anjuke_db|ajk_member_token|http://member.anjuke.com/member/1.0/login_weibo/|读写|共有|此表存储了第三方登录时,token和userid的对应关系|
|anjuke_db|ext_member_mapping|http://member.anjuke.com/extcallback/|读写|公用|此表存储第三方登录的信息,主要是第三方用户和安居客用户的映射关系,在第三方登录时会读写此表,anjuke-site,user-site 均使用了此表|