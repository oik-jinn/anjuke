### touch web 百度阿拉丁xml文件重复小区词处理
#### pmt-id:http://p.corp.anjuke.com/project/detail?id=23940
* A.获取重复的楼盘名称数据写入redis中
    * a.通过调用新房api批量获取该城市楼盘名称
    
        * 对应的api接口为:http://api.aifang.com/xinfang/loupanName/?city_id=14
        * 返回数据demo:
        {
        "status": "true",
        "result": {
            "city_id": 51,
            "loupan_names": [
                "中信龙潭岭",
                "中信博鳌千舟湾",
                "保亭庄园丽都"
                ]
            }
        }  
        
    * b.获取二手房小区名
    
            数据来源:
            DB:anjuke_db
            TABLE NAME:ajk_communitys
            SQL:SELECT * FROM ajk_communitys WHERE CommId>{$commnunity_id} AND CityId = {$city_id} AND TypeFlag=0 ORDER BY CommId ASC LIMIT {$limit}";


    * c.build数据到redis中
        *job位置和名称:user-site/app-ershou-job/classes/ershou/job/community/comm/GetRepeatCommunityName.php
        *job运行时间:1个月运行一次,早上3点
        *实现方法:
        
            方法名称为:isRepeatCommunityName($city_id,$community_name)
            实现策略:
                1.根据SQL查询是否返回结果进行判断
                    SELECT * FROM ajk_communitys WHERE CommName = "{$community_name}" AND CityId = {$city_id} AND TypeFlag=0 limit 1";
                方法返回值:true或者false
                2.存入redis中
                    键值:xmlrepeatcommname_{$community_id}
                3.存储方式:
                    常驻文件中
                    方法采用:filesnapshotting,修改save N M表示在N秒之内，redis至少发生M次修改则redis抓快照到磁盘，文件名称默认为dump.rdb
                  

* C.在build阿拉丁xml中引入对比方法

            job对应shell的路径:anjuke-site/app-jobs/bin/anjuke/touchwebxml_alading.sh
            job对应php的路径:anjuke-site/app-jobs/bin/anjuke/touchwebxml_alading.php
            job所在平台:job.corp.anjuke.com
            job的url:http://job.corp.anjuke.com/job/form.php?id=770
            需要更改的位置:146行froeach循环时调用isRepeatCommunityName($community_id)进行判断
            方法内容:根据community_id组成的key查询redis，根据返回结果进行判断，是重复的名称就continue
            