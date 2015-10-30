## 经纪人业务    
### 1.隔离    

 消息生产者　定时JOB:    
1)[同步经纪人扩展信息](http://drone.corp.anjuke.com/scheduler/job/273/view)    
2)[同步公司信息](http://drone.corp.anjuke.com/scheduler/job/272/view)     
3)[同步门店信息](http://drone.corp.anjuke.com/scheduler/job/269/view)    

消息生产者　一次性JOB     
1)[更新全部公司信息](http://drone.corp.anjuke.com/scheduler/job/271/view)    
2)[更新全部门店信息](http://drone.corp.anjuke.com/scheduler/job/270/view)    
3)[更新基本信息](http://drone.corp.anjuke.com/scheduler/job/266/view)    

定时ＪＯＢ每隔一段时间会检测某一段运行时间内发生改变的经纪人信息，批量发送到中间件,交由消费者处理，   
这些功能应该转由经纪人端做处理．更改后这些ｊｏｂ可以停掉   

一次性ＪＯＢ可以在这些表有添加表字段后，全量通知，消费者进行全量更新，    

需要注意的是消费者的消费速度是有瓶颈的，使用多个消费者需要注意并发问题.    
目前一部分业务在用，列表和单页好像只有部分再用，这个具体查代码吧　　　   

[消息消费者](http://drone.corp.anjuke.com/daemon/job/104/view)
消费者是批量查询的,但是更新还是单个更新


### ２.solr   
经纪人solr 的脚本也已经迁移到新框架了,经纪人列表罗辑需要好好整理一下,ufs相关的是不是需要去除,    
JOB里面我也是只跑了一部分城市,非ufs城市没有跑      

经纪人solr脚本的[生产者](http://drone.corp.anjuke.com/scheduler/job/220/view)和[消费者](http://drone.corp.anjuke.com/daemon/job/98/view)  都在我们这里，这个最好让产品定好展示罗辑，交给经纪人端处理 ,我们只负责往solr中推送数据   

如果有经纪人信息没有在solr中,可以使用[单个经纪人job更新](http://drone.corp.anjuke.com/daemon/job/104/view)

### 3.店铺    
这块我知道的不多，大多数情况都是改bug


## mapping    
### anjuke=>58 房源
这个我是放到hbase里了,主要考虑分表问题，双向查询的话也要建表，所以直接放到hbase里了,    
hbase　中表split成100块,主要为了分散压力,是按照对100求余分的 ，这个具体看代码就知道了　很简单     
主要需要考虑的时是房源过期后，应该删除，不然时间过长后，表的容量也会增大   
后面如果58=>anjuke房源mysql扛不住的话可以使用hbase，使用方法很简单
php操作hbase 可以使用restAPI　http方式的，也可以使用thrift,socket方式，这个是fb开发的

### 小区,二手房，租房区域板块    
数量比较少，查询量相对较大，我放到mysql里了,导入的数据都放在app10-137的/home/www的目录下了(具体我也忘了)　　　  
表中通过type区分小区还是区域板块的    
job(Ershou_Job_Mapping_ImportMapping)需要指定类型，文件地址，排序分割(５８在前还是安居客在前)　少一个条件都不能执行，最好确定好导入文件的罗辑    
污染了表又要truncate表，重新导了    
    