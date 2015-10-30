#房源上下架之竞价和定价
##下架即删除操作没有任何改动,主要说明上架逻辑
	1.获取传入propid并按组进行拆分后更新队列,对应
		a.数据库:queue_db
		b.表名:esf_auction_queue_01_201407
		(其中esf_auction_queue_为前缀,01_为对应队列前缀,_201407为当前月份)
		c.SQL语句如下:
		select id,city_id,broker_id,pro_id,flag,hpstarttime,hpendtime,hpplanid,update_time from esf_auction_queue_'.$suffix.' where pro_id in ('.implode(',',$proIds).') order by id asc;
		d.对结果进行去重处理,以最后一次更新类型为准,根据城市对应编号确定所在队列
		e.房源上下架对应数据存放在该表中	
		备注:对于竞价来说,只是改变了对应的表前缀,即esf_pricing_queue_01_2014
		
	2.根据PropIds获取房源数据
		a.数据库:分库 --根据CityId进行判断选取(本次以上海为例) property_db_sh
		b.表名:ajk_propertys
		c.$config['rank_solr_update_prop_mapping'] = array(
		    0	=>	'prop_other_slave',
    		4	=>	'prop_other_04_slave',
    		11	=>	'prop_shanghai_slave',
    		14	=>	'prop_beijing_slave'
		  );
		 d.SQL语句
		 	select * from ajk_propertys where proId in($strProIds);
		 e.数据为空的话记录相关信息Log_Sale:add();
		 
	3.检查房源编号
		根据第二步得到的数据进行判断 TRADETYPE != 1 || OPERATESTATE != 1 || ISVISIBLE == 		0时,将对应的房源数据加入到删除数据中,Log_Sale::add(); -- 非相关流程跳过
	
	4.获取非抓取且不存在房源扩展信息的房源扩展信息
		a.数据库:anjuke_db
		b.表名:ajk_propertysale
		c.SQL语句:
		select * from ajk_propertysale where PROID=11211;
		
	5.对应更新扩展信息的房源获取合并小区commid
		a.数据库:anjuke_db
		b.表名:ajk_commextend
		c.SQL语句:
			select * from ajk_commextend where commid='11211' limit 1;
			
		d.如果得到的信息中IsMerge为1,则更新commoid
			d.1 数据库:anjuke_db
			d.2 表名:ajk_merge_comm
			d.3 SQL语句:
				select * from ajk_merge_comm where CommId='11211' order by POSTTIME desc limit 1
		
	6.生成solr所需数据
		其他逻辑保持不变,对房源信息中字段名为`COMMITIONTYPE`进行判断,如果为2,则solr中对应的tags标签增加A010标示
		
	7.job对应目录
		anjuke-site/app-jobs/bin/sale/pricing_to_solr.php
		anjuke-site/app-jobs/bin/sale/pricing_update_solr.php
		anjuke-site/app-jobs/bin/sale/auction_to_solr.php
		anjuke-site/app-jobs/bin/sale/auction_update_solr.php