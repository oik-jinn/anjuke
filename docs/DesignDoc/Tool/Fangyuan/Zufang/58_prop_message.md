# 项目名： 58房源消息中间表监控
## 项目背景：
```
    消息中间有时不可靠,落表数据不正常。
    
```
## 项目目标：
```
    通过对表的主键监控,及时发现消息队列表数据不再增长，通过邮箱报警，提醒响应人员处理。
    
```
## 流程图：
![Alt text](http://gitlab.corp.anjuke.com/_site/docs/raw/master/DesignDoc/Tool/Fangyuan/Zufang/58%E6%88%BF%E6%BA%90%E4%B8%AD%E9%97%B4%E4%BB%B6%E8%90%BD%E8%A1%A8%E7%9B%91%E6%8E%A7.png "Optional title")

## 参数说明：

参数|说明|是否必须|默认值
 ---|---|---|---
dao|表对应的dao|是|无
receiver|接受预警人员邮箱,多个预警接收邮箱用,隔开|是|无
## 使用说明:
```
  1. 该监控进程只能对58房源消息队列表监控,不能监控其他系列表.
  2. 通过Scheduler控制监控频率.
  3. 该监控job能根据当前月份自动切换到相应的消息队列一系表.
```
## job平台地址:

http://drone.corp.anjuke.com/scheduler/job/382/view