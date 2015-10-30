## 积分墙系统

### 项目介绍
* 接入app推广的各个渠道，计算app的下载量、激活量等信息，用于市场统计数据

### 代码仓库
* [git@gitlab.corp.anjuke.com:_mobile-api/app-activation.git](git@gitlab.corp.anjuke.com:_mobile-api/app-activation.git)

### 部署服务器
* 说明[http://gitlab.corp.anjuke.com/_mobile-api/app-activation/blob/master/readme.md](http://gitlab.corp.anjuke.com/_mobile-api/app-activation/blob/master/readme.md)

### 常见问题
* 市场获取各个渠道的下载量

    ```
    eg:获取youmi渠道的下载量
    
    渠道名称：ad
    下载时间：last_update
    http://api.anjuke.com/common/activation/1.0/queryCount?ad=youmi&last_update=2014-08-03&is_callback=1
    
    每次查询的时候，只需要修改渠道名称和下载时间就可以了。
    ```
* idfa数据核对

    ```
    可以跟渠道商核对每天的有效下载量
    http://app20-011.i.ajkdns.com:9090/activation/
    ```