## 安居客精选房源全量rebuild

### job目录

Ershou_Job_Property_Temp_AuctionFixRebuild

### 参数说明

cursor_id 游标

### 经纪人端精选判断

* 数据源 
   SELECT id,prop_id,city_id,broker_id,spreadstartdate,spreadenddate,updated_datetime,
   bidVersion,status,offer,hpratio,comms_hpratio,comms_hpratio_a FROM ark_db.ajk_propspread

* 判断是否精选
  
