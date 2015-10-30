|数据库|表名|读写|是否独有|读访问量|写访问量|是否接受延迟|说明|
| --- | --- | --- | --- | --- | --- | --- | --- |
|mobile_api_db|fortune_item|读|不确定|1k|0||财富的信息|
|mobile_api_db| fortune_price_period |读|不确定|1k|0||获取鉴定价格信息|
|mobile_api_db| identify_text |读|不确定|1k|0||获取鉴定相关信息|
|mobile_api_db|user_focus|读写|是|未知|未知||用关注小区的信息|
|mobile_api_db|focus_count|读写|是|未知|未知||小区关注数量|
|mobile_api_db|fix_comm_count |读写|是|1M|1M||针对小区浏览量和关注量进行修正|
|mobile_api_db|id_from_phone_number|读写|是|未知|未知||市场活动相关信息:URL没有访问量|
|mobile_api_db|id_generator |读写|是|未知|未知||发号器当前最大编号查询|
|mobile_api_db|view_comm_price_history|读写|是|0.1k|0.1k||可能会感兴趣的小区|
|ajk_dw_stats|solly_mpr_open_table|读|未知|5k|0||推荐相关的表名称|
|ajk_dw_stats|dw_pricing_comm_quartile|读|未知|0.3M|0||小区日均价|
|ajk_dw_stats|dw_pricing_comm_weekly|读|未知|0.3M|0||小区周均价|
|ajk_dw_stats|hu_dingcai_broker_score|读|未知|16k|0||经纪人ufs分数|
|ajk_dw_stats|price_trend_areacode_monthly|读|未知|1M|0||区域板块在区间内的房价|
|ajk_dw_stats|price_trend_comm_monthly|读|未知|1.2M|0||小区价格趋势(按月)|
|ajk_dw_stats|hu_guanlian_bk_all_sale|读|未知|未知|未知||看过的房源获取相似的房源|
|ajk_dw_stats|hu_guanlian_sale_new_bu|读|未知|未知|未知||看过的房源获取相似的房源|
|ajk_dw_stats|comm_view_count|读|未知|1M|0||小区浏览量|
|chat_db|broadcast_msg_basic|读写|独有|0.7M|极少||对用户发送的广播消息|
|chat_db|broadcast_msg_basic_app|读写|独有|0.7M|极少||微聊,对设备发送的广播消息|
|chat_db|broadcast_msg_receive_last|读写|独有|0.7M|极少||微聊拉取用户未读消息|
|chat_db|broadcast_msg_receive_last_app|读写|独有|0.7M|极少||微聊拉取设备未读消息|
|chat_db|broker_ext|读写|独有|0.1k|极少||查看经纪人是否开通短信提醒服务||
|chat_db|broker_info|读写|独有|1M|3k||微聊添加朋友|
|chat_db|chat_basic|读写|独有|1M|未知||微聊回话信息|
|chat_db|device_basic|读写|独有|7k|1k||微聊中设备基本信息|
|chat_db|device_setting|读写|独有|0.7M|未知||微聊设备设置,主要是是否允许推荐|
|chat_db|focus_account|读写|独有|0.4M|未知||微聊获得用户的关注列表,new-api的job在用|
|chat_db|focus_app|读写|独有|0.2M|未知||设备关注信息,new-api的job在用|
|chat_db|job_remind_broker|读写|独有|未知|未知||微聊job在用,记录提醒'经纪人需要回复任务'的信息|
|chat_db|menu_basic|读写|独有|未知|未知||菜单信息|
|chat_db|menu_group|读写|独有|未知|未知||菜单组信息|
|chat_db|msg_basic|读写|独有|0.7M|||微聊消息(发给用户),以及user-site下的job(用于信息的归档)|
|chat_db|msg_basic_app|读写|独有|0.7M|6k||微聊消息(发给设备),以及user-site下的job(用于信息的归档)|
|chat_db|msg_black_list|读写|独有|0.7M|20||加入黑名单的用户|
|chat_db|msg_black_list_app|读写|独有|0.7M|20||加入黑名单的设备|
|chat_db|msg_ext|读写|独有|0.7M|||微聊消息,以及user-site下的job(用于信息的归档)|
|chat_db|msg_ext_origin|读写|独有||||微聊消息,以及user-site下的job(用于信息的归档)|
|chat_db|msg_type|读写均无|独有|0|0||消息的类型常量,代码中没有读,写数据库的代码,只是使用了DAO中的常量|
|chat_db|no_reply_brokers|读写|独有|未知|未知||未回复经纪人数据,job在写|
|chat_db|public_service_relation|读写|独有||||服务号和用户类型之间的好友关系,用于发送消息|
|chat_db|service_basic|读写|独有||||服务号和公众号的基本信息|
|chat_db|user_app_chat_relation|读写|独有|0.1M|未知||用户和设备之间的好友关系|
|chat_db|user_basic|读写|独有|5k|||用户的基本信息|
|chat_db|user_broker_mapping|读写|独有|1k|||用户id和经纪人id之间的对应关系,|
|chat_db|user_device_mapping|读写|独有|5k|未知||用户和设备id之间的对应关系|
|chat_db|user_ext|读写|独有|未知|2k||用户的其它信息,主要是第三方信息|
|chat_db|user_ext_mapping|读写|独有|未知|2k||用户和第三方的对应关系,|
|chat_db|user_last_access|读写|独有|未知|5k||用户最后一次登录时间以及使用的设备信息|
|chat_db|user_login_history|读写|独有|未知|5k||记录用户的登录历史|
|chat_db|user_password|读写|独有|未知|未知||记录用户的密码|
|chat_db|user_phone_mapping|读写|独有|1k|1k||记录用户的id和手机号的对应关系|
|chat_db|user_relation|读写|独有|0.2M|未知||记录好友关系的相关信息|
|chat_db|user_relation_category|只读|未知|未知|独有||记录好友关系中关系的分类,在表中数据为空|
|chat_db|user_relation_ext|读写|独有||3k||记录好友关系中加好友时的附加信息|
|chat_db|user_type|不读,不写|独有|0|0||账户类型表,在业务中只是使用dao里的常量,但是没有读写表|
|anjuke_db|ajk_members|读写|独有|0.4M|||此表存储用户基本信息,用户注册,登录,修改个人信息等业务均会操作此表,anjuke-site,user-site 均使用了此表|
|anjuke_db|ajk_membersother|读写|独有|0.4M|||此表存储用户的扩展信息,包含用户所在的小区,城市,用户头像等信息,用户注册,获得用户信息,绑定手机号会读写这张表,anjuke-site,user-site 均使用了此表|
|anjuke_db|ajk_member_logins|读写|独有|0.4M|||此表存储用户的登录信息,用户的注册(查询手机号是否被占用),登录均用到了此表,anjuke-site,user-site 均使用了此表|
|anjuke_db|ajk_membersextend|读写|独有||||此表存储了用户的扩展信息(主要是经纪人),包括用户回答的问题,用户的的信誉评价,发布的房源质量等信息,anjuke-site,user-site 均使用了此表|
|anjuke_db|ajk_checkcode|读写|独有||||此表主要存储验证信息,用于邮箱验证 anjuke-site,user-site 均使用了此表|
|anjuke_db|ajk_getpasswd|读写|独有||||此表存储了用户找回密码的映射关系,主要用于用户找回密码,用找回的密码登录,|
|anjuke_db|ajk_subscribe_email|读写|独有||||此表存储了用户订阅的邮箱信息,主要用于用户的订阅|
|anjuke_db|ajk_member_options|读写|独有||||此表存储用户的扩展标签,即用户的选项标签,比如用户提交了手机号,是一种标签,提交了手机号也是一种标签,anjuke-site, user-site 均在使用此表|
|anjuke_db|ajk_member_token|读写|独有|10K|10k||此表存储了第三方登录时,token和userid的对应关系|
|anjuke_db|ext_member_mapping|读写|独有|10K|||此表存储第三方登录的信息,主要是第三方用户和安居客用户的映射关系,在第三方登录时会读写此表,anjuke-site,user-site 均使用了此表|
