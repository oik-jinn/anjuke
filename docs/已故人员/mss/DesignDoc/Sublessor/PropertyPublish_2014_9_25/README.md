## 大业主房源推广、下架、删除设计
---
### 需求

* 大业主可以对自己的房源进行推广、保存、下架、删除处理
* 大业主的以上操作都应该有操作日志备查
* 大业主随时可以下架自己推广中的房源
* 大业主随时可以删除自己的房源
* 大业主安币数量≥40时可以自由推广自己的房源
* 大业主安币数量<40但>0时，将无法推广<b>下架中</b>和<b>编辑后的推广中房源</b>，编辑后的推广房源应变为下架状态
* 大业主安币数量=0时，大业主`所有`推广中的房源将自动下架，自动下架处理过程应有日志记录

---
### 设计

#### 上下架队列
* 推广房源时需要将房源推送至上下架队列
    * [大业主房源上下架job设计](http://gitlab.corp.anjuke.com/zhaoyuanshi/doc/tree/master/sublessor)
    * 发送上下架队列方法 Biz_Sublessor_Rent_PublishBiz::postZmq($flag, $pro_id, $user_id, $city_id)
* 大业主上下架队列发送参数

参数|类型|说明
---|---|---
flag|int|上下架标志 1:上架 2:下架
pro_id|int|房源id
user_id|int|大业主用户id
city_id|int|城市id


#### 房源操作日志
* 房源状态(status)变动时需要记录相关log
    * [大业主房源操作日志设计]()
    * 发送操作日志方法 Sublessor_Core_Rent_Service_OptLogService::recordOptLog($params)
    * 对应db-表 actlog_db::sublessor_opt_log_{Ym}
* 房源操作日志params参数说明表

参数|类型|说明
---|---|---
user_id|int|房源所属大业主id
prop_id|int|房源id
opt_id|操作类型|详细见操作类型说明表
opt_backup|操作备注


* 大业主房源操作日志操作类型opt_id值说明表

值|说明
---|---
0|删除
1|新建
2|修改
3|上架
4|下架
5|上下架log 发送上下架队列后获得接口返回的log
6|job上架(暂时不会用到)
7|job下架


#### 房源推广

    推广功能入口
        1. 发布页推广按钮
        2. 编辑页推广按钮
        3. 大业主房源管理页推广开关

##### 发布页推广流程

![发布页推广流程](http://gitlab.corp.anjuke.com/_site/docs/raw/master/DesignDoc/Sublessor/PropertyPublish_2014_9_25/ajaxpublish.png)

##### 编辑页推广流程

![编辑页推广流程](http://gitlab.corp.anjuke.com/_site/docs/raw/master/DesignDoc/Sublessor/PropertyPublish_2014_9_25/editrentbutton.png)

##### 大业主房源管理页推广

![大业主房源管理页推广](http://gitlab.corp.anjuke.com/_site/docs/raw/master/DesignDoc/Sublessor/PropertyPublish_2014_9_25/ajaxpublish.png)

#### 房源下架

略

#### 房源删除

略

#### 房源保存

略