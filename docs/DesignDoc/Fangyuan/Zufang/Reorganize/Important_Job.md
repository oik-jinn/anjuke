## 租房重要job及其说明

### 经纪人精选房源上下架
```
命令：php /home/www/user/usersite/app-zufang-job/launcher.php --class=Zufang_Job_Rent_Spread_SpreadToSolr --tab_split=09;sleep 1s;
drone地址：http://drone.corp.anjuke.com/daemon/job?keyword=%E7%A7%9F%E6%88%BF-%E6%8E%A8%E5%B9%BF%28%E7%B2%BE%E9%80%89%29
```
### 经纪人套餐房源上下架
```
命令：sh /home/www/bin/zufang/rent_prop_run.sh spread 09,100
rent_prop_run.sh: 
while (true);
   do
       if php /home/www/config/v2/jobs/zu/launcher.php rent/prop_run.php $1 $2
       then
          echo "php done!"
       fi
       sleep 1;
  done
drone地址：http://drone.corp.anjuke.com/daemon/job?keyword=%E7%A7%9F%E6%88%BF-%E6%8E%A8%E5%B9%BF%28%E7%AB%AF%E5%8F%A3%29
```

### 个人房源上下架

```
命令：php /home/www/user/usersite/app-zufang-job/launcher-beta.php --class=Zufang_Job_Rent_Landlord_SpreadToSolr --fork_count=10 --fork_num=9 --start=176299583 
drone地址：http://drone.corp.anjuke.com/daemon/job?keyword=%E6%96%B0%E7%A7%9F%E6%88%BF-%E4%B8%AA%E4%BA%BA
```

### 58房源上下架

```
命令：/home/www/user/job-script/while_true.sh php /home/www/user/usersite/app-zufang-job/launcher.php --class=Zufang_Job_Rent_Spread_WubaRentRunJob --tab_split=09
drone地址：http://drone.corp.anjuke.com/daemon/job?keyword=58%E7%A7%9F%E6%88%BF%E6%88%BF%E6%BA%90%E4%B8%8A
```
### 房源更新

```
命令：/home/www/user/job-script/while_true.sh php /home/www/user/usersite/app-zufang-job/launcher-beta.php --class=Zufang_Job_Rent_Spread_PropertyEventToSolr --tab_split=09
drone地址：http://drone.corp.anjuke.com/daemon/job?keyword=%E7%A7%9F%E6%88%BF%E7%BB%8F%E7%BA%AA%E4%BA%BA%E6%88%BF%E6%BA%90%E6%9B%B4%E6%96%B0
```