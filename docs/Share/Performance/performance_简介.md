#Performance 简介

##Performance 是什么?

* Performance 是用 PHP 开发的一个函数级别的 页面 及 函数代码 执行时间的统计工具。
* 在线数据分析报告工具是用 Python 开发的 Ideliver Web在线分析工具。 
* Performance 抽样（默认百分之一）统计 应用耗时占比（page、controller……）、断点 的执行时间（精确到毫秒）、慢请求URL、APP应用服务器耗时。


##Performance 的功能

* K线图直观的显示每个监控页面的性能走势
* 今天的性能走势与上周的今天的走势对比
* 查看当天不同时间段的性能
* 查看指定日期的性能
* 性能耗时占比饼状图 包含两块的饼状图
    * frame time
    * controller
    * page
    * other
    * model time
    * cache::get
    * cache::add
    * mysql
    * solr
* 断点耗时
* 慢请求URL
* 请求app应用服务器耗时

##使用方法

* 修改 common.php 添加
    * $config['performance_is_allow'] = 1;  开启性能监控
    * $config['performance_rate'] = 100  抽样率  框架默认值是 百分之一 抽样率
* 修改配置文件  添加你所要监控的页面控制器
    * site/user-branch 代码库为 watchdog.php
    * anjuke/v2-feature 代码库为 logger.php
    * site/user-site 代码库为 logger.php
    * 修改对应代码库的配置文件  示例： 

```php 
$config['performance_controllers'][] = array(
'User_Touch_Anjuke_Property_ViewController', //单页控制器
'User_Touch_Anjuke_List_ListController', //列表页控制器
);
```

* 添加iDeliver分析数据报告页面
    * http://ideliver.corp.anjuke.com/php/
    * 点击新增页面按钮
    * 填写表单对应的值 save

