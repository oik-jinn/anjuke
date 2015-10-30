一、小区信息
1.小区基本信息
    DB:
        anjuke_db
    SQL:
        SELECT * FROM ajk_communitys where typeflag = '0' and commid= ?;
    
2.扩展信息
    DB:
        anjuke_db
    SQL:
        SELECT * FROM ajk_commextend where commid= ?;
        
3.image信息--外景图
    DB:
        anjuke_db
    SQL:
        SELECT * FROM ajk_attachments_comm where commid = ? and imagesign = 1 and visibility = 1;
        
4.地图信息
    4.1 组装经纬度数据
        DB:
            anjuke_db
        SQL:
            SELECT * FROM map_communities_baidu where comm_id = ?;
        
    4.2 组装街景数据
        DB:
            anjuke_db
        SQL:
            SELECT * FROM map_communities_soso_pano  where comm_id = ?;
    
    
二、《看了又看》和《附近房源》
    请求对应的api接口进行调用
        http://sh.pmt-23286-site.zu.anjuke.test/api/rec/pagerec/?cityid=11&id=D1&type=3&limit=9&resulttype=2&from=SITE_RENT_PAGE&r=0.8112421829719096
    返回数据中far_props表示《看了又看》
    返回数据中near_props表示《附近房源》
    
三、400电话相关
    400大号全部保持一致，不一致的情况下请报BUG  -- 有效期至2014年10月1日
    400小号只要保证不同大业主的号码不同即可
    
四、测试URL
    http://sh.pmt-23286-site.zu.anjuke.test/rent/D1

五、大业主DB
    DB:user_prop_sh_db
    表：所有表
    
六、大业主信息获取方式
    PG：
        http://member.anjuke.test/memberapi/m/?act=get_user_info&uid=
    GA：
        http://member.anjuke.com/memberapi/m/?act=get_user_info&uid=