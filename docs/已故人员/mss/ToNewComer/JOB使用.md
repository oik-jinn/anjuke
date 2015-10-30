# JOB使用教程


## 各个环境job启动执行方式
### Dev开发环境:
>php launcher.php --class=Ershou_Job_Seo_Sourcetobase --start=B

### PG环境:
>php /home/www/usersite/jobs/launcher.php   --class=Ershou_Job_Seo_Basetodraft --job_path=app-ershou-job --load_path=pmt-20717-site

### Beta环境:
>php /home/www/user/usersite/app-ershou-job/launcher-beta.php --class=Ershou_Job_Seo_Drafttoonlin --only_category=B3 --only_city=11  > /home/www/logs/seo/Drafttoonlineb3.run

### GA环境:
>php /home/www/user/usersite/app-ershou-job/launcher.php --class=Ershou_Job_Seo_Drafttoonlin --only_category=B3 --only_city=11  > /home/www/logs/seo/Drafttoonlineb3.run


## JOB游标操作和查看

### 程序操作游标
* 设置游标:
>$this->saveRunedCursor(self::RUNED_CURSOR, $data); 
* 获取游标进度:
>$this->getRunedCursor(self::RUNED_CURSOR);


### 游标 DB记录位置
* HOST
	* DEV:192.168.1.24
	* PG: 10.20.3.80
* DB: job_db
* table: job_runed_cursor

## 如何获取参数
### 需要重构getOptArgs(),只有预置参数可接受.
* demo,例如要接受 city_id 参数

   function getOptArgs() {
        return array(
            'city_id:',
        );
    }

 
