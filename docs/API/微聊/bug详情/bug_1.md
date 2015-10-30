##### chat-cms 后台无法向全国经纪人发送公众号信息([详情](http://ibug.corp.anjuke.com/ticket/detail?ticket_id=59622))
##### 分析过程
##### 1. chat-cms 后台调用接口http://chatcms.anjuke.com/message/massive,请求方式post
##### 2. http://chatcms.anjuke.com/message/massive 对应的controller(在仓库chat-cms)调用http://api.anjuke.com/anjuke/4.0/chat/public/pust/?is_nocheck=1'来发送消息
##### 3. http://api.anjuke.com/anjuke/4.0/chat/public/pust/?is_nocheck=1'(仓库是在anjuke-mobile_api)只是写amqp队列,队列的名称是 app_chat_push
##### 4. 搜索所有api的仓库,看到仓库new-api的job Mobile_Job_DealServicePush 在读这个队列
##### 5. 读这个job的代码,发现这个job在写日志.
##### 6. 在[日志平台](http://10.10.6.99/)查看日志,发现日志中报错,companyid是空,经过排查发现可能是从队列中取数据回调函数中参数为空造成的,所以在代码中加上了日志,以观察具体的情况.
##### 7. 代码上线,需要重启job
##### 8. 在[job统一管理平台](http://drone.corp.anjuke.com/)搜索job的名称,没有找到改job
##### 9. 在单独部署job的机器app10-117 上查询是否有这个job.执行如下命令(crontab -l | grep Mobile_Job_DealServicePush)
##### 10. 查询执行该job的进程,杀死进程,然后进程自动重启(有其它的进程在监视此进程).
##### 11. 运营重新发送消息,发现日志中可以发送消息,以往的情况无法重现.
##### 12. 需要再次出现类似情况的时候,才能查明具体是什么造成的.