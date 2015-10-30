## 租房交接内容大概总结了以下，如果有漏掉的地方，再交流
============================================================================
### 经常用到job : 
* 1. app-haozu-jobs/bin/solr/hz_rank_landlord.php                   更新租房个人房源solr
* 2. app-haozu-jobs/bin/solr/hz_rank_broker_haozu.php          更新租房经纪人房源solr
* 3. app-haozu-jobs/bin/solr/hz_rank_broker_ajkzf.php             更新二手房经纪人房源solr
* 4. app-haozu-jobs/bin/community/build_community_solr.php  更新小区solr (实时)
* 5. app-haozu-jobs/bin/solr/fix_hz_landlord_prop.php               修复租房个人房源solr  (主要针对单个房源)
* 6. app-haozu-jobs/bin/solr/fix_hz_broker_prop.php                  修复租房经纪人房源solr(主要针对单个房源)
* 7. app-haozu-jobs/bin/solr/fix_ajkzf_broker.php                       修复二手房经纪人房源solr(主要针对单个房源)

* 实时更新房源job会用到1、2、3，不过这个三个job现在还没迁移到新job调度系统，在job服务器app10-078 上，修改这三个job需要修改haozu_site仓库的代码
上线流程在  http://ideliver.corp.anjuke.com 下进行，代码开发仓库是 _ajkrepopool，另外修改job时最好 haozu_site和haozu_user 同步修改，方便以后job迁移

* rebuild job也会调用1、2、3，只是和实时job传参数不一样，每天跑一次，rebuild所有在线房源，
部署在job调度平台 http://drone.corp.anjuke.com (app10-095) 上id是10至24

### 房源solr地址 : 
    http://sc10-001.a.ajkdns.com:8983/hz-list-11/
    http://sc10-001.a.ajkdns.com:8983/hz-list-14/
    http://sc10-001.a.ajkdns.com:8983/hz-list-03/
    http://sc10-001.a.ajkdns.com:8983/hz-list-04/

    其中from字段代表意思  好租经纪人:3、好租个人:0、二手房经纪人 : 1

    小区solr地址 : http://sc10-001.a.ajkdns.com:8983/hz-community/ 

    sc10-001.a.ajkdns.com 对应的ip是  10.10.6.51


### 租房之前job调度平台地址 : http://online.job.dev.haozu.com/job/list.action  帐号、密码同crm后台帐号密码
----------------------------------------------------------------------------------------------------------
* 1. 租房没有自己的小区，调用的是二手房的小区信息，只是solr和二手房不一样
* 2. 租房现在的地铁信息也是和二手房调用的同一套数据
* 3. 租房区域、板块信息有自己的表维护(rent_db.area), 数据是从二手房区域、板块表同步过来的
* 4. 租房debug调试信息查看，在url后面添加 debug=1，如果还不能看到，按下F9就可以看到