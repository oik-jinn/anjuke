## app经纪人白皮书

### 1、页面URL
```
打开安居客app ---> 点击二手房或者租房频道 ---> 点击列表页中的一套房源进入房源单页 ---> 在房源单页点击经纪人信息
```

### 2、页面说明
经纪人主要有以下接口：

* 2.1、获取经纪人信息

    ```
    根据微聊id获取经纪人信息
    根据手机号码获取经纪人信息
    根据经纪人id获取经纪人信息
    根据经纪人ID批量获取微聊信息
    ```
* 2.2、获取经纪人各类房源数量
* 2.3、关注/取消关注 经纪人
* 2.4、微聊手机号查找经纪人
* 2.5、微聊附近经纪人

### 3、页面模块列表
|序号|URL|仓库|负责人|功能|
| --- |--- | --- | --- | --- | --- |
|2.1|http://api.anjuke.com/anjuke/4.0/broker/info|anjuke-mobile-api|晓路|获取经纪人信息|
|2.2|http://api.anjuke.com/anjuke/4.0/focus/action|anjuke-mobile-api|晓路|关注经纪人|
|2.3|http://api.anjuke.com/anjuke/4.0/property/count|anjuke-mobile-api|晓路|获取经纪人各类房源数量|
|2.4|http://api.anjuke.com/anjuke/4.0/broker/chatinfo|anjuke-mobile-api|晓路|手机号找经纪人|
|2.5|http://api.anjuke.com/mobile/v5/nearby/brokers/|new-api|晓路|微聊附近经纪人|

### 4、依赖的内部服务或config(指的是其它仓库的服务)
|序号|服务名|仓库地址|负责人(或部门)|功能|配置列表|内层/外层|
|---| --- | --- | --- | --- | --- | --- | --- |
|2.1|common.php|anjuke-mobile-api|晓路|添加关注|外层|focus_add|

### 5、依赖的外部URL
|序号|配置名|URL|功能|其它|
|---| --- | --- | --- | --- |
|2.1|focus_add|http://api.anjuke.test/weiliao/focus/add||外层|
    
### 6、数据库和表
|序号|数据库|表名称|作用|读写|是否独有|
|---| --- | --- | --- | --- | --- |
|2.1|mobile_db|broker_chatinfo|经纪人微聊信息|读|是|
|2.2|anjuke_db|ajk_brokerextend|经纪人信息|读|是|
|||shop_list|经纪人商铺信息|读|是|

### 7、Memcache&Redis
|名称|类型|地址|申请大小|使用率|是否统一管理|功能|负责人|key|
|--- | --- | --- | --- | --- | --- | --- | --- | --- |

### 8、关联job
|job名称|job管理地址|功能|负责人|
|--- | --- | --- | --- |
