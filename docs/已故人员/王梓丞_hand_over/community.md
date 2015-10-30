1.小区列表现在还在旧框架     
2.小区概况,二手房,租房房源tab已经在新框架了,剩下的都在老框架     
　房型图的罗辑有点乱,迁移的话需要认真理一下    
　价格行情永胜改过一版，大多数表的数据还是取自bi统计的表,迁移的时候注意一下缓存问题    
3.小区服务现在只有３个城市在用,都是走配置的,如果不用,去掉线上的配置就可以了,小区服务写的不是很好，速度一般,需要有一个缓存预热,这个我写了一个[job](http://drone.corp.anjuke.com/scheduler/job/296/view)    
做隔离的话，建议优先使用redis    
4.小区solr　build脚本  分为４个    
   http://drone.corp.anjuke.com/scheduler/job/181/view    
   http://drone.corp.anjuke.com/scheduler/job/180/view    
   http://drone.corp.anjuke.com/scheduler/job/148/view    
   http://drone.corp.anjuke.com/scheduler/job/132/view    
   其实是根据city_group区分的    
   如果有某个小区出现问题,可以用这个[job](http://drone.corp.anjuke.com/scheduler/job/179/view)看一下问题    
   这些job会将二手房租房的都build出来，因为现在租房的小区solr还没有完全废除，所以还在build，以后可以考虑只build二手房    
   以上是生产者    
   消费者　　是[这个](http://drone.corp.anjuke.com/daemon/job/43/view)    

 5.关于租房小区基本信息，是读redis的，有个脚本一直去同步二手房小区到redis中,租房搜索，pad,地图上都有在用,废弃的时候注意一下    
 6.小区均价,这块都是走的均价service,均价service 在corepirce 目录中有设计,这里就不多写了  