## 用户端业务数据整体架构

由客户线负责通知数据的变动消息，用户线读取消息，写入用户线自己的db,solr和缓存中，前台页面直接读取用户线的db,solr和缓存。不再和客户线共享db


![](http://pic1.ajkimg.com/display/xinfang/18dad66d596a95a323308d2933df750f/1024x800n.jpg)

## 二手房业务数据架构

![](http://pic1.ajkimg.com/display/xinfang/503a44328c04c684735f960f474a0884/1024x800n.jpg)