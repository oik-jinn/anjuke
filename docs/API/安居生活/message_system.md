### 消息表message的设计

#### 基础表 notify_msg_basic

|字段|类型|长度|是否为空|默认值|备注|
| --- | --- | --- | --- | --- | --- |
|id|bigint|20|not||主键|
|user_id|int||not|0|消息接收方id|
|biz_type|tinyint|2|not|0|消息的业务类型,0代表未知,1代表物业,2代表兴趣组消息|
|sub_biz_type|tinyint|2|not|0|消息的子业务类型,0代表未知,[biz_type 和sub_biz_type之间的关系](http://gitlab.corp.anjuke.com/_site/docs/blob/master/API/%E5%AE%89%E5%B1%85%E7%94%9F%E6%B4%BB/%E5%AE%89%E5%B1%85%E7%94%9F%E6%B4%BBv1.0-P2-%E6%B6%88%E6%81%AF.md)|
|status|tinyint|2|not|0|消息状态,0表示状态未知,1代表未拉取状态,2代表已经拉取|
|create_time|timestamp||not|当前时间|消息的创建时间|
|update_time|timestatmp||not|当前时间|消息的更新时间,根据更新操作自动更新|

#### 扩展表 notify_msg_ext

|字段|类型|长度|是否为空|默认值|备注|
| --- | --- | --- | --- | --- | --- |
|id|bigint|20|not||主键|
|user_id|int||not|0|消息接收方id|
|content|varchar|4096|not|''|消息的内容|

## 业务api调用接口
### 发送通知类消息(包含通知你有新消息和推送新消息)

#### sendNotifyMsgByUserId()
##### 入参

|参数|类型|备注|
| --- | --- | --- |
|user_ids|array|用户id|
|biz_type|int|消息的类型,1代表物业,2代表兴趣组|
|sub_biz_type|int|子业务的消类型,0代表未知|
|content|array|消息的内容|

##### 出参
true,false,表示消息发送成功和失败

### 获取未读消息数目
#### getUserUnreadMsgNum()
##### 入参

|参数|类型|备注|
| ---| ---| ---|
|user_id|int|用户id|
|biz_type|int|消息的类型,0代表所有,1代表物业,2代表兴趣组|

##### 出参
##### 返回数组,数组的每个元素格式如下:

|参数|类型|备注|
| --- | --- | --- |
|biz_type|int|消息业务类型,1代表物业,2代表兴趣组|
|number|int|未读消息的数量|

### 获取消息列表
#### getUserMsgList()
#### 入参
|参数|类型|备注|
| --- | --- | ---|
|user_id|int|用户id|
|type|int|消息的类型,1代表物业,2代表兴趣组|
|max_msg_id|bigint|要拉去的消息的最大id(拉取的消息id小于此id),如果max_msg_id=0,那么从最新的消息拉取|
|size|int|拉取消息的个数|
##### 出参
返回数组,每个元素的格式如下:

|参数|类型|备注|
| --- | --- | --- |
|id|bigint|消息id|
|user_id|int|消息接收方id|
|biz_type|int|消息类型,0代表未知,1代表物业,2代表兴趣组消息|
|sub_biz_type|int|消息的子业务类型|
|status|int|消息状态,0表示状态未知,1代表未拉取状态|
|content|string|消息的内容,json 格式|
|create_time|timestamp|消息的创建时间|
|update_time|timestatmp|消息的更新时间|

### 消息推送的格式(app的接口)

    {
        message: 消息的提示内容,比如你有一条新消息
        msg_type:消息的类型,1代表通知类消息,2代表聊天类消息
        msg_content:{
            biz_type:业务类型,1代表物业,2代表兴趣组
            sub_biz_type:业务子类型
            is_notify:bool,true是有新消息通知,false表示具体的消息
            msg_id:消息的id
            other_content:{
                create_time:创建时间,时间戳形式
                ...
            }
            
        }
    }

### 调用宋的长连接

pg 测试地址  http://app20-011.i.ajkdns.com:1905/message/send

例子:

curl -v -d '{"toUserId":2000961757,"toDeviceId":"xxx","toAppName":"a-ajk","data":{"msgType":"push","body":{"message":"推荐楼盘更新了，进来看看吧"}}}' http://app20-011.i.ajkdns.com:1905/message/send

协议：json

必须满足下面的框架，如果有扩展的，放到 body 里面

{
  "toUserId": 2000961757,
  "toDeviceId": "xxx",
  "toAppName": "i-anlife",
  "data": {
    "msgType": "push",
    "body": {
      "message": "推荐楼盘更新了，进来看看吧”,
      “xxx”:”yyy”,
    }
  }
}

[消息推送序列图](http://gitlab.corp.anjuke.com/_site/docs/blob/master/API/%E5%AE%89%E5%B1%85%E7%94%9F%E6%B4%BB/msg_sequence.pdf)