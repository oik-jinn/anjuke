##房价页面的小区房源推荐的数据流程

 涉及到的控制器及文件
 Ershou_Web_Market_MarketController@ handleRequestInner
####代码块


``` php
  //小区列表数据是直接提供给页面端使用的
   $comm_list = $this->getCommList();
   $this->request->set_attribute('comm_list',$comm_list);
  .......
  
protected function getCommList(){
        //获取热门小区ids
$hot_comms = $this->communityPricesService->getHotCommsByAreacode($this->current_info['area_code']); //通过区域code来查找当前区域下的热门小区对应的数据表如1-1所示
$comm_ids = array_filter(array_unique(explode(',', $hot_comms->comm_ids)));

$comm_list = $this->communityPricesService->getPropsByCommIds($comm_ids); //通过小区id获取小区下面对应的房源 涉及的数据表如1-2所示

 foreach($comm_list as $comm){
            $prop_ids_res = array_filter(array_unique(explode(',', $comm->prop_ids)));
            foreach($prop_ids_res as $prop_id){
                $prop_id_info = explode('|', $prop_id);
                $prop_ids[] = array(
                    'id' => $prop_id_info[1],
                    'source_type' => $prop_id_info[0],
                    'protype' => $prop_id_info[3],
                );
                $p_flag[$prop_id_info[1]] = $prop_id_info[2];//是否是精选标识
            }
        }
.........

 //获取房源基本信息
 $prop_list_info = $this->buildPropDatas($prop_ids, $p_flag);
............
}

/**
 * 根据房源ids获取房源信息
 */
protected function buildPropDatas($props, $p_flag){
    $params['img_params']['width'] = 240;
    $params['img_params']['height'] = 180;
    $prop_list_res = $this->listBiz->getPropertyInfosBySolrInfos($this->city_id, $props, $params , 0, 0, 0);   //先查隔离表再查房源表58只有隔离表
    .......
}
    

```
 

#### 表1-1(user_prop_db.ajk_hot_community)
字段	类型	是否允许为空	关键字（PRI主键约束; UNI唯一约束; MUL可以重复）	默认值	Extra	备注
id	int(11) unsigned	NO	PRI		auto_increment	
area_code	varchar(20)	NO	UNI			城市、区域、板块的typecode
comm_ids	varchar(200)	NO				热门小区id,以,分割
update_time	timestamp	NO		CURRENT_TIMESTAMP	on update CURRENT_TIMESTAMP	更新时间

查询结果样式
| id	| area_code	|  comm_ids	| update_time|
1|	060006010602	|640952,218748,204825,185543,184197,182850,182847,182641,179262,177453,171758,171752,171745,98498,77098|2015-07-28 03:03:14


#### 表1-2(user_prop_db.ajk_hot_community_props)

字段	类型	是否允许为空	关键字（PRI主键约束; UNI唯一约束; MUL可以重复）	默认值	Extra	备注
id	int(11) unsigned	NO	PRI		auto_increment	
comm_id	int(10) unsigned	NO	UNI	0		小区id
mid_price	int(10) unsigned	NO		0		小区本月均价
mid_change	float	NO		0		小区价格环比
prop_ids	varchar(250)	NO				房源id,以,分割
comm_trend	varchar(200)	NO				小区6个月的走势信息
update_time	timestamp	NO		CURRENT_TIMESTAMP	on update CURRENT_TIMESTAMP	更新时间
city_id	smallint(3) unsigned	NO		0	

查询结果样式	
comm_id	mid_price	mid_change	prop_ids	comm_trend	update_time	city_id
53536	628430	5785	0	E|31826936|0|3,E|20576823|0|3,E|22085726|0|3,E|22174806|0|3	a:6:{i:201502;s:4:"6422";i:201503;s:4:"6422";i:201504;s:4:"6422";i:201505;s:4:"5762";i:201506;s:4:"5785";i:201507;s:4:"5785";}

- **隔离表**
user_prop_s0(city_id%3)_db.broker_property_(city_id)

- **房源信息表(根据city_id实现分库)**
> propertys_db_sh.ajk_propertys     --->  city_id对应北京的城市房源表
>propertys_db_bj_ajk_propertys     --->  city_id对应上海的城市房源表
> properys_db_04.ajk_propertys     --->  city_id >40的城市房源表
>propertys_db_other.ajk_propertys ---> city_id<40且非上海和北京的城市的房源表
