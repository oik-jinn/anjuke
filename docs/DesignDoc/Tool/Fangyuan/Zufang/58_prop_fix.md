## 项目名： 租房58房源个人上下架修复工具设计
## 设计背景： 
### 我们始终相信上下架job是可靠的,如果不可靠,将会导致大批量房源出问题.以下设计均以此为原则.
### 功能描述：
```
    1. 提供查询队列表消息功能。
    
    2. 提供游标查询。
    
    3. 提供消息队列插入功能。
```
### 流程图：
![Alt text](http://gitlab.corp.anjuke.com/_site/docs/raw/master/DesignDoc/Tool/Fangyuan/Zufang/58%E6%88%BF%E6%BA%90%E4%B8%8A%E4%B8%8B%E6%9E%B6job%E5%AE%9A%E4%BD%8D%E5%92%8C%E4%BF%AE%E5%A4%8D%E5%B7%A5%E5%85%B7.png "Optional title")

### 参数说明：
参数|说明|是否必须|默认值
  ---|---|---|---
 prop_id|房源id|是|无
act|操作类型（del为删除solr数据，act=add为新增，act=none不会插入消息队列）|是|无

### 使用方法:

 * 给出房源id和操作类型,建议先第一次查询使用先定位问题,然后根据具体情况再次指定操作类型运行job.
 * 请仔细查看job日志,日志会给出详细的定位信息