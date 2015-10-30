调试:

### web 开发调试
* debug调试信息查看，在url后面添加 debug=1，如果还不能看到，按下F9就可以看到
* __config=1 可以查看对应的配置,route,api,database,cache等相关配置
* cc=cc 很多情况下会自动更新cache数据. 

###  本地调试去掉cache , cache_type设置为1
/Users/chenny/anjuke_work/system-ext/config/dao.php
$config['cache_type'] = 1; 
 
### 查看当前连接mysql的服务器ip和数据库db名

/Users/chenny/anjuke_work/system/classes/apf/db/PDO.php
在exec(28行)函数中
var_dump($this->config['dsn’);


### job中的游标如何工作的,如何查看游标记录?
主要是记录job中的执行位置,保存在mysql的24机器的job_db的job_runed_cursor表中. 
以便于异常中断后. 可用继续执行job,尽量保存数据原子性和可持续性.


### sql demo
/Users/chenny/anjuke_work/user-site/app-user-test/classes/user/system/dao/PdoProcessorTest.php





###   job 传递参数和获取参数 
通过getOptArgs函数指定那些参数是可以被接受的. 
function getOptArgs()
    {
        return array(
                'start:',
                'end:',
                'cc:',
        );
    }
—cc=xx —aa=43 只有cc可用接受

### log4j 调试
/Users/chenny/anjuke_work/user-site/app-ershou-job/config/log4j.php
rootLogger的level 值: info改为 debug

### 对某个dao屏蔽cache
$daoInfo->skipCache = true;

### nlogger记录
/Users/chenny/anjuke_work/system-ext/config/nlogger.php
$config['tag_prefix'] = 'applog.anjuke_dev.';
$config['host'] = '10.10.3.76';
$config['port'] = '24225';