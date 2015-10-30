#ueSearch介绍
----

##  概述

  esearch是58搜索研发部自主研发的搜索引擎内核，基于linux c++开发，支持经纬度搜索、分组统计、精确统计、一一抽取、索引动态刷新、文档相关性/综合排序scorer、package联合索引等查询需求。
    uesearch是搜索引擎的上层应用，不依赖于业务逻辑，是读取原始数据、建索引和查询的统一框架。包含一些通用处理流程，是建索引和查询的统一接口，uesearch里面已经实现了所有的处理逻辑。


----

 
 
 
## 结构图
<img src="http://gitlab.corp.anjuke.com/_site/docs/raw/master/DesignDoc/Search/Images/flow1.png" width="610"/>
  


----



##  esearch特点:

* 大数据量的分片处理
* 各角色分工明确
* 容灾处理
* 无缝升级和切换
* 完善的监控



----



## 监控

1. **短信转发服务**。
   * 封装公司短信平台提供的原始接口，提供封装接口、人名到手机号映射、日志记录等功能；2. **crontab live检测**。
   * 每分钟检测searcher、merger、instbuilder的进程存活，如不存活则重启，需提前打通机器；3. **crontab 索引删除**。
   * 每天删除searcher中的过期索引段；4. **searcher、merger协议层检测。**
   * C++程序，通过模拟查询请求来做健康检测，可读取merger踢searcher的情况；5. **超时监控。**
   * 给业务方用的请求客户端，会将超时日志通过UDP发给日志收集服务器，然后每分钟的超时计数；6. **功能监控。**
   * 一个通用的多功能监控平台，可以进行http请求，scf请求，流水表数据库读取，最新帖子发布时间与当前时间的比较等等。7. **业务和效果监控。**
----
 
 
 
## 我们在uesearch做了什么

   * 增加http的代理
       * 对结果进行了包装,提供http的接口
       * (58.com应用层是scf的java框架, 应用层可以直接访问proxy的) 
   * 组合数据
       * 在uesearch⾥里⾯面，主键的名称并不会体现在结果中，所以要定义⼀一个通⽤用的主键名称 
		* 当多值字段只有单个值的时候，返回的不是数组，和Solr有些不⼀一样，为了避免迁移过程中遇到问题
   * 对结果进行包装
       * 仿照solr，提供了⼀一些详细的信息,比如各种errormsg,httpcode,qtime等  
   * 提供PHP版本的SDK
       * 让业务应用层不必关注uesearch的语法
       * 统一接口,添加跟踪码等



----
 
 
 
 
#搜索的规划
----


## 目标

* 搜索服务系统化,结构化
* 支持各业务的搜索,不限于房源!
* 支持全目录搜索
* 解决大数据量拆分的问题
* 搜索词算法的统一,对应用层接入更友好



----
 
 
 
 

## 已完成的

* 线上的部署
* 系统的监控
* 二手房信息的job写入
* 二手房rank的排序算法插件
* 提供搜索的SDK



----
 
 
 
 

## 要做的
* 测试自动化脚本
* 关键词匹配服务(mss)整合到搜索服务体系中
    * 统一各频道的mss服务
    * 统一匹配算法 
* 提升搜索结果的匹配度
    * 提取信息的详情摘要
* 文本相关性权重查询
* 分词服务的统一
* 支持其他类目的接入



----
 
 
 
  
#搜索的使用
----
 
        
##  应用层如何接入

* 流程
<img src="http://gitlab.corp.anjuke.com/_site/docs/raw/master/DesignDoc/Search/Images/flow2.png" width="520"/>

* 写入数据

   * 1>.将你的数据按照指定格式写到搜索数据源表
   * 2>.将的信息通知流水表

      
* 读取数据
   * 按照esearch语法去检索
   * 目前提供PHP版本的SDK 
 


----
 
 
       
##  信息表的设计
  * 如何做到不同类目信息合并在一起
      * 全站唯一信息id服务, 详情: [http://gitlab.corp.anjuke.com/_search-cloud/uesearch/issues/7](http://gitlab.corp.anjuke.com/_search-cloud/uesearch/issues/7)
      * 搜索数据源表设计, 详情: 
        [http://gitlab.corp.anjuke.com/_site/docs/blob/master/DesignDoc/Search/Db.md](http://gitlab.corp.anjuke.com/_site/docs/blob/master/DesignDoc/Search/Db.md)
      
      
      
      
      
      
      
      
      
       