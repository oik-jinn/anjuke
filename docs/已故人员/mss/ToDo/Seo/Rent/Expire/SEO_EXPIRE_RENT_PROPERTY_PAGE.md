### SEO过期房源之租房单页设计文档

#### 1.项目调研
	a.复审PRD	
		PRD地址为:http://p.corp.anjuke.com/project/detail?id=25028
		实际差异:
			1.目前只处理经纪人和个人单页，大业主和抓取单页暂不做处理
			2.过期时间不做处理
	b.第三方服务
		使用孙志文提供的HBase进行数据的存储和读取
		协议:HTTP
		地址:
			线下为http://10.20.3.82:8080
			线上为http://10.10.8.2:80
	c.团队合作相关约定
		读取方:董宏亚
		存储方:叶晨

#### 2.概要设计
![Hbase logic png](HBase.png)
#### 3.详细设计
##### 读取HBase获取房源信息

	1.组装HBase Key
	
	思路:房源ID对100取模为前缀,用'-'进行连接,最后跟房源ID
	
	实现方法:
	
	public function getPropertyHBaseKey($prop_id,$type){
        if($type == Zufang_Core_Const_Rent::RENT_TYPE_BROKER){
            $mod_id = $prop_id % 100;
            return $mod_id .'-'.$type.'-'.$prop_id;
        }else{
            return FALSE;
        }
    }
    
    2.拼接URL,使用HTTP协议请求API获取数据
    
    前提:
    	增加HBase请求的配置信息
    		Path:user-site/app-user-common/config/hbase.php
    		Conent:
    			$config['hz_property_servers'] = array(
    			'rest_api' => 'http://10.20.3.82:8080',
			    'table_name' => 'prop:hz_property',
			    'column' => 'info',
			);
    
    
    思路:提供Table Name和HBase Key,调用User_Common_Hbase_Hbase中得getRowFromHbase获取数据,对原始数据进行第一次format
    
    实现方法:
    
    public function getRentPropertyArchive($prop_id,$type){
        $archive_key = $this->getPropertyHBaseKey($prop_id,$type);
        if(!empty($archive_key)){
            $h_data =  $this->hbase->getRowFromHbase('hz_property_servers',$archive_key);
            if(is_array($h_data)){
                $info = new stdClass();
                $hBase_config = APF::get_instance()->get_config('hz_property_servers', 'hbase');
                foreach($h_data as $k => $v){
                    $field = str_replace($hBase_config['column'].':','',$k);
                    $info->$field = $field == 'extend' ? json_decode($v) : $v;
                }
            }else{
                $info = array();
            }
            return $info;
        }else{
            return FALSE;
        }
    }
    
##### format数据格式

	思路:
		以目前的数据格式为标准,进行对应的格式化,新建方法进行format.只格式化prop中archive为true的情况.

	实现方法:
		public function formatHBaseArchiveProp($prop){
        $content_basic = json_decode($prop->content_basic);
        $property_extend = new Zufang_Core_Rent_Dto_RentExtend();
        $result->pro_id = $prop->proid;
        $result->city_id = $prop->cityid;
        if(is_object($content_basic)){
            $result->comm_id = $content_basic->commid;
            $result->comm_name = $content_basic->commname;
            $result->area_id = $content_basic->areaid;
            $result->block_id = $content_basic->blockid;
            $result->romm_num = $content_basic->roomnum;
            $result->pro_name = $content_basic->title;
            $result->user_id = $content_basic->userid;
            $result->address = $content_basic->address;
            $result->room_num = $content_basic->roomnum;
            $result->hall_num = $content_basic->hallnum;
            $result->toilet_num = $content_basic->toilnetnum;
            $result->pro_floor = $content_basic->floor;
            $result->floor_num = $content_basic->totalfloor;
            $result->rent_type = $content_basic->renttype;
            $rent_type = $this->rentDictService->getRentTypeInfo($result->rent_type);
            $result->rent_type_name = $rent_type->name;
            $result->area_num = $content_basic->areanum ? $content_basic->areanum : $content_basic->square;
            $result->pro_type = $content_basic->protype;
            $house_type = $this->rentDictService->getHouseTypeInfo($result->city_id,$result->pro_type);
            $result->pro_type_name = $house_type->name;
            $result->fitment = $content_basic->fitment;
            $fitment_type = $this->rentDictService->getFitmentTypeInfo($result->city_id,$result->fitment);
            $result->fitment_name = $fitment_type->name;
            $result->toward = $content_basic->toward;
            $toward_type = $this->rentDictService->getTowardTypeInfo($result->toward);
            $result->toward_name = $toward_type->name;
            $result->price_num = $content_basic->pricenum;
            $result->pay_money_num = $content_basic->paymoneynum;
            $pay_type = $this->rentDictService->getPayTypeInfo($result->pay_money_num);
            $result->pay_money_name = $pay_type->name;
            $result->deploy = $content_basic->deploy;

            $deploy_info = $this->rentDictService->getDeploymentInfo($result->deploy);
            $result->deploy_info = $deploy_info;

            $result->quality = $content_basic->quality;
            $result->created = $content_basic->created;
            $result->updated = $content_basic->updated;
            $result->on_time = $content_basic->ontime;
            $result->all_days = $content_basic->all_days;
            $result->share_type = $content_basic->sharetype;
            $dict_info = $this->rentDictService->getShareTypeInfo($result->share_sex);
            $result->share_type_name = $dict_info->name;

            $result->share_sex = $content_basic->sharesex;
            $dict_info = $this->rentDictService->getShareSexInfo($result->share_sex);
            $result->share_sex_name = $dict_info->name;

            $result->is_pkg = $content_basic->ispkg;
            $result->port_id = $content_basic->portid;
            $result->new_comm_id = $content_basic->newcommid;
            $result->spread_type = $content_basic->spread_type;
            $result->g_prop_sum = $content_basic->g_prop_sum;
            $result->g_price_num_1 = $content_basic->g_pricenum_1;
            $result->g_price_num_3 = $content_basic->g_pricenum_3;
            $result->g_price_num_6 = $content_basic->g_pricenum_6;
            $result->suite_id = $content_basic->suiteid;
            $result->type = $content_basic->type;
            $result->status = $content_basic->status;
            $result->add_explan = $content_basic->descript ? $content_basic->descript : $content_basic->descrip ;
            $result->sid        = $content_basic->sid;
            $result->hz_spider_type = $content_basic->hz_spider_type;
            $result->spider_broker_mobile = $content_basic->spider_broker_mobile;
            $result->spider_broker_name = $content_basic->spider_broker_name;
        }
        $content_other = json_decode($prop->content_other);
        if(is_object($content_other)){
            $property_extend->housetype = $content_other->housetype;
            $property_extend->mobile = $content_other->mobile;
            $property_extend->guid = $content_other->guid;
            $property_extend->user_id = $content_other->userid;
            $property_extend->sid = $content_other->sid;
            $property_extend->surname = $content_other->surname;
            $property_extend->usercall = $content_other->usercall;
            $property_extend->slng = $content_other->slng;
            $property_extend->slat = $content_other->slat;
            $property_extend->ishigh = $content_other->ishigh;
            $property_extend->ip = $content_other->ip;
            $property_extend->img = $content_other->img;
            $property_extend->usercall = $content_other->user_type;
            $property_extend->post_date = $content_other->postdate;
            $property_extend->owner400 = $content_other->owner400;
            /*数据表中没有如下字段：经纪人那边可能去掉了一下字段*/
            $property_extend->email = $content_other->email;
            $property_extend->qq = $content_other->qq;
            $property_extend->msn = $content_other->msn;
            $property_extend->protection = $content_other->protection;
            $property_extend->pro_type = $content_other->protype;
            $property_extend->mobile_open = $content_other->mobileopen;
            $property_extend->over_date = $content_other->overdate;
            $property_extend->over_type = $content_other->overtype;
        }
        $result->property_extend = $property_extend;

        if ($is_format == true && $type == self::IS_LANDLORD_PROP){
            $result = $this->formatLandlordPropData($result);
        }
        return $result;
    }		
##### 确认房源status
	
	思路:使用默认处理方法
	
	实现方法:
		Path：user-site/app-biz/classes/biz/zufang/rent/RentBiz.php
		方法:Biz_Zufang_Rent_RentBiz中的getPropertyShowStatus
		
##### 页面屏蔽联系方式
	
	思路:根据prop中得archive进行判断
	
	实现方法:
		Path:user-site/app-zufang-web/page/zufang/web/rent/yep/View.phtml
		方法:93行增加 
			<?php if($property->archive):?>
			
			<?php else: ?>
			<div class="broker_icon broker_tel dark_grey"><i class="p_icon icon_tel"></i><?=User_Util_StringUtils::formatMobileNumber($broker->broker_base->mobile);?></div>
			
			<?php endif; ?>
			
#### 4.应急方案
	
	预防目标:HBase服务异常
	
	思路:增加Hbase开关来确认是否需要开启过期页面
	
	实现方法:
		Path:user-site/app-user-common/config/hbase.php
		Conent:$config['rent_hbase_switch'] = true;
	
#### 5.To do list
	增加HBase相关配置
	Path:
		user-site/app-user-common/config/hbase.php
	Conent:
		PG:
			//房源信息Hbase配置
			$config['hz_property_servers'] = array(
		    'rest_api' => 'http://10.20.3.82:8080',
		    'table_name' => 'prop:hz_property',
		    'column' => 'info',
			);

			/**
			 * 增加hbase服务开关
			 */
			$config['rent_hbase_switch'] = true;
		GA:
			//房源信息Hbase配置
			$config['hz_property_servers'] = array(
		    'rest_api' => 'http://10.10.8.2:80',
		    'table_name' => 'prop:hz_property',
		    'column' => 'info',
			);

			/**
			 * 增加hbase服务开关
			 */
			$config['rent_hbase_switch'] = true;

#### 6.其他说明
	基础数据依赖《叶晨》批量入HBase,需要提前准备.
	请求方式:http://10.20.3.82:8080/
	入参:房源类型(type)和房源ID(prop_id)
	出参:
	HBase返回数据格式:
```
stdClass Object
(
    [content_basic] => {"renttype":"1","title":"\u5317 \u4eac \u627e \u5c0f \u59d0 \u670d \u52a1 \u7535 \u8bdd","areaid":"213","blockid":"218","commid":"111035","commname":"\u767d\u4e91\u697c","square":"123","roomnum":"1","hallnum":"1","toilnetnum":"1","sharetype":"5","sharesex":"0","areanum":"","pricenum":"1111","paymoneynum":"2","floor":"1","totalfloor":"1","wytype":"","fitment":"15","toward":"1","deploy":["3","4","6","5","9","8","1","11","10","7"],"intime":"","intimedate":"","descrip":"a","status":5,"cityid":"12","address":"b","version":"2","shareage":"","sharework":"","sharedate":""}
    [content_other] => {"housetype":"1","mobile":"18600082722","email":"","qq":"","msn":"","protection":"","protype":"","guid":"3934F033-784D-534B-C7D3-986D05492F8E","userid":"7085690","sid":"0","mobileopen":"","postdate":"1371764865","overdate":"","overtype":"","transfer":"","surname":"\u83f2\u83f2","usercall":"","slng":"113.291565","slat":"23.125907","ishigh":0,"ip":"221.233.254.10","img":"http:\/\/pic1.ajkimg.com\/display\/hz\/d7e19228fcb99ee5e7e11ed7c815b074\/122x90.jpg","user_type":"1","hostavatar":"","mobilepub":"1","publishby":"web","refreshdate":"1371764865","publish-save":"1"}
    [proid] => 15824109
    [updated] => 1371765221
)
```						
			