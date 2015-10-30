### 推送消息记录表push_msg设计

#### 基础表 cms_push_rule

|字段|类型|长度|是否为空|默认值|备注|
| --- | --- | --- | --- | --- | --- |
|rule_id|int|11|not||主键|
|title|varchar|100|not|0|标题|
|content|varchar|1024|not|0|消息内容|
|redundant|text||not|1|各种筛选条件的json字符串|
|send_num|int|11|not|0|发送人数|
|status|tinyint|4|not|1|1、未发送 2、发送中 3、已发送|
|create_time|timestamp||not|当前时间|消息的创建时间|
|update_time|timestatmp||not|当前时间|消息的更新时间,根据更新操作自动更新|


### 安居生活后台消息推送的格式(app的接口)
    {
        message: 消息的提示内容,比如你有一条新消息
        msg_type: 1  代表通知类消息,
        msg_content:{
            biz_type:业务类型 3 公共通知
            sub_biz_type:  1、启动应用 2、跳转兴趣小组首页 3、跳转上门首页 4、跳转html页面 5、跳转兴趣组详情页
            is_notify:bool,true是有新消息通知,false表示具体的消息
            msg_id:消息的id
            other_content:{
                rule_id:规则id
                create_time:创建时间,时间戳形式
                action_type: 1、跳转小区广场首页  2、跳转闲置首页
		        url:'' 跳转html页面
		        content:''
            }
            
        }
    }

