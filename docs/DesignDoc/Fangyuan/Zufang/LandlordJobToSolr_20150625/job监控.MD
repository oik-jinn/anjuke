# 项目名： job游标监控
## 项目背景：
```
    现job平台监控不到位，一些job假死后不能及时发现。
    
```
## 项目目标：
```
    及时发现假死job，通过邮箱报警，提醒响应人员处理。
    
```
## 流程图：
![Alt text](http://gitlab.corp.anjuke.com/ruiwang_h214/design/raw/master/%E7%9B%91%E6%8E%A7job2.png "Optional title")

## 参数说明：

参数|说明|是否必须|默认值
 ---|---|---|---
targeClass|被监控运行类名|是|无
identifyFlag|进程区分标志|是|无
receiver|接受预警人员邮箱|是|无
## 使用说明:
```
  1. 该监控进程本身游标的identify_flag为类名和被监控的类名组合,故可起多个监控进程监控不同目标job.
  2. 通过Scheduler控制监控频率.
```
