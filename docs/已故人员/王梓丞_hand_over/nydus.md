1.erlang.cookie  Erlang的[Magic Cookie](http://www.erlang.org/doc/reference_manual/distributed.html)文件   
   这里也有关于erlang　longname 和shortname以及dns的一些问题    
　线上服务器在089　ansible中有配置,如果手工新增节点，需要配置，不然可能无法通信    
   打包脚本有点问题,有几个变量字母打错了,(好像是before shell那里)    
   
2.es日志已经不使用了,但是在spring管理中还有配置，web中查看日志现在是挂掉的.      
   es集群也迁移了一部分,注意property中的配置      
   日志目前是通过log4j写的,crontab中有定时任务清理log，有定时任务把新log推送到新的[ods](http://ods10-013.i.ajkdns.com:50070/explorer.html#/nydus)中     
   
3.我在线上服务器中做了一些测试，结果都在[这里](http://gitlab.corp.anjuke.com/Ericwang/rabbit_mq_test)     


4.关于丢包,我查下来可能是因为nydus的长链接问题,本地可以开启tracing插件去查，不过[tracing](http://www.rabbitmq.com/plugins.html) 还在实验阶段，不适合线上使用     

5.rabbitmq的log中,有一个特别占磁盘的,主要记录端口的情况,这个需要定时清理一下    

6.目前nydus代码仓库已经迁移到gitlab上了,部署的时候注意一下，    
　我改了一些bug,主要都是nydus启动时,空server会导致spring管理的mq连接资源会初始化而导致空指针异常
