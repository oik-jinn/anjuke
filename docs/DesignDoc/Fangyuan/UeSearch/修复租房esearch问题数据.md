修复esearch问题数据
## 二手房
## 问题分类
## 租房
## 问题分类
* 五八房源归档未通知esearch下架  (--repair_type=3)
 * 处理流程 
    * 时时归档 已删除房源 往五八队列表发一条下架消息
        * 消息格式： flag=3 from_type>30000 from_type 最后两位存放房源类型
    * 脏数据修复 
        * 扫描0-127张esearch.index_source_xxx表 
        * 选择五八的房源 查房源基本信息表 如果不存在 通知esearch下架
* 修复方法
    *  shell 传入参数顺序 --repair_type=? --reset=1
    *  重置修复index_source_xxx 游标 --reset=1
    *  传入修复的类别 --repair_type=? --reset=0
    * 二手房job 地址
        *  [pgjob]() 
        *  [linejob](http://drone.corp.anjuke.com/scheduler/job/458/view)
    * 租房job 地址
        *  [pgjob]() 
        *  [linejob](http://drone.corp.anjuke.com/scheduler/job/459/view)