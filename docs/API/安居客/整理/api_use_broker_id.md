##### 在整理的时候,api这边适用情况分为两类,第一类是直接使用二手房经纪人id,读取经纪人的表(anjuke_db.ajk_brokerextend),来获取二手房经纪人的信息.第二类是根据 hz_broker_id、jp_broker_id,取mapping表里面查二手房经纪人id,然后查表获取经纪人信息
## anjuke-mobile-api
#### 第一类使用情况
##### 1. class Dao_Anjuke_AjkBrokerextend
    $sql = "SELECT * FROM `ajk_brokerextend` WHERE `BrokerId` = ? limit 1";
    $sql = "SELECT * FROM `ajk_brokerextend` WHERE `UserMobile` = ? limit 1";
      $sql = "SELECT * FROM " . self::TABLE_NAME . " WHERE `BrokerId` IN (" . implode(',', $modes) . ")";
## haozu
#### 第二类使用情况
##### 1. Orm_Broker_AjkBroker
    public function get_ajk_broker_id($id, $cache_flg=false) {
        $db_config          = ORM_Dbconfig::rent_db();
        $obj_ajk            = new ORM_ORMFactory($db_config, $this->tb_broker_mapping);
        $where              = "brokerid = ?";
        $params             = array($id);
        $result             = $obj_ajk->getList($where, $params, $cache_flg);
        return $result;
    }
     public function get_hz_broker_id($ajkbrokerid, $cache_flg=false) {
        $db_config          = ORM_Dbconfig::rent_db();
        $obj_ajk            = new ORM_ORMFactory($db_config, $this->tb_broker_mapping);
        $where              = "ajkbrokerid = ?";
        $params             = array($ajkbrokerid);
        $result             = $obj_ajk->getList($where, $params, $cache_flg);
        return $result;
    }
    public function get_ajk_broker_ids($ids, $cache_flg=false) {
        $ids_str = implode(',', $ids);
        $db_config          = ORM_Dbconfig::rent_db();
        $obj_ajk            = new ORM_ORMFactory($db_config, $this->tb_broker_mapping);
        $where              = "brokerid in (".$ids_str.")";
        $params             = array();
        $result             = $obj_ajk->getList($where, $params, $cache_flg);
        return $result;
    }
     public function get_ajkinfo_bymobile($mobile, $cache_flg=false) {
    	$db_config 			= ORM_DbConfig::anjuke_db();
    	$obj_login 			= new ORM_ORMFactory($db_config, $this->tb_ajk_brokerextend);
    	$obj_login->usermobile	= $mobile;
    	$result 				= $obj_login->getRowByField('usermobile', $cache_flg);
    	return $result;
    }
##### 由于solr搜索的时候传递的是租房经纪人id,租房solr不支持二手房经纪人id,(租房solr马上要弃用,使用uesearch,uesearch支持二手房经纪人id 搜索,需要等庞龙那边uesearch上线,然后api这边才能进行修改),
## new-api
#### 第一类情况
##### 1. Dao_Ershou_BrokerextendDao
    public function getDaoInfo()
    {
        $daoInfo = new Apf_Db_Dao_DaoInfo();
        $daoInfo->masterDsn = 'master';
        $daoInfo->slaveDsn = 'slave';
        $daoInfo->tableName = 'ajk_brokerextend';
        $daoInfo->pkName = 'BrokerId';
        $daoInfo->dbname = 'anjuke_db';
        $daoInfo->modelClass = 'Dao_Model_Ershou_Brokerextend';
        $daoInfo->convertToObject = true;
        return $daoInfo;
    }
#### 第二类情况
##### 1. Mobile_Core_Service_Anjuke_Search
     public function getZfCountByBrokerIds($brokerIds, $cityId, $useCache=true){

        $search_solr = new Mobile_Core_Search_SearchSolr();
        $server = $this->getZuPropSolrUrl($cityId);
        $brokerIdsMapping = $this->getZuBrokerIdsByAjkBrokerIds($brokerIds);
        $zuBrokerIds = array_values($brokerIdsMapping);
        $queryParams = array(
            'queryfields' => array('id'), //or just one field like 'queryfields' => score 需要获取的字段 fl
            'facet' => array(
                'facet_count' => 0,
                'facet_sort' => 'false', //or false
                'facet_mincount' => 1,
            ),
            'rows' => 0, //每页数量
            'page' => 1, //第几页
        );
        foreach ($zuBrokerIds as $brokerId){
            //$queryParams['facet']['facet_query']['broker_id'][] = $brokerId;
            $facet_query[] = 'facet.query=broker_id:'.urlencode($brokerId);
        }
        $postParams = implode('&', $facet_query);
        $queryRs = $search_solr->query($server, $queryParams, 'post', $postParams);

        $queryRs = $queryRs ? json_decode($queryRs, true) : array();
        $rs = array();
        $brokerIdsMapping = array_flip($brokerIdsMapping);
        if ($queryRs && $queryRs['facet_counts']['facet_queries']){
            foreach ($queryRs['facet_counts']['facet_queries'] as $facetKey=>$facetV){
                $zu_broker_id = substr($facetKey, 10);
                $ajk_broker_id = $brokerIdsMapping[$zu_broker_id];
                $rs[$ajk_broker_id] = $facetV;
            }
        }
        return $rs;
    }
## user-site
#### 第一类情况
##### 1. Mobile_Api_Sale_SaleService
     $broker_infos = $this->brokerBaseService->getBrokerBaseInfoById($broker_ids);
##### 2. Mobile_Core_Service_Broker_ExtendService
      public function getBrokerInfoByBrokerIds(array $brokerIds, $with_chatInfo=false){
        $where = array("BrokerId"=>$brokerIds);
        $extendList = $this->brokerextendDao->find($where);
        if (empty($extendList)){
            return array();
        }
#### 第二类情况
##### 1. Mobile_Api_Rent_RentListService
    $broker_mapping = $this->brokerRentService->getBrokerMapping($rent_broker_ids);
##### 2. Mobile_Core_Service_Anjuke_Search
     public function getZuBrokerIdsByAjkBrokerIds($brokerIds){
        apf_require_class('Broker_Core_Broker_Rent_Dao_BrokerMappingDao');
        $broker_mapping_dao = new Broker_Core_Broker_Rent_Dao_BrokerMappingDao();
        $infos = $broker_mapping_dao->find(array("ajkbrokerid"=>$brokerIds));
        $rs = array();
        if ($infos){
            foreach ($infos as $info){
                $rs[$info['ajkbrokerid']] = $info['brokerid'];
            }
        }
        return $rs;
    }


-------
## jinpu
---

##### 1. StoreOnline_BrokerPortInfoController 、StoreOnline_PortDistributionController
		//安居客经纪人不存在
		$DAjkMember = Bll_SiteMergeMemberBll::get_instance()->getAjkMemberInfoByAjkId($broker_id);
		if($DAjkMember == false){
			$ret = array(
				"status" => "error",
				"info" => "not found broker"
			);
			echo json_encode($ret);
			return true;
		}
		
		//安居客经纪人存在，金铺经纪人不存在
		$DJPMember = Bll_SiteMergeMemberBll::get_instance()->getJPMemberByAjkId($broker_id);
		if($DJPMember == false){
			$ret = array(
				"status" => "ok",
				"info" => array()
			);
			echo json_encode($ret);
			return true;
		}
		
		//安居客、金铺经纪人都存在
		$DPorts = Bll_SiteMergePortBll::get_instance()->getPortsByMemberId($DJPMember->id);
		$ret = array(
			"status" => "ok",
			"info" => $DPorts
		);
		
##### 2. Bll_SiteMergePortBll 主要为调用 其他 Api 【Bll_SiteMergeMemberBll】

Bll_SiteMergeMemberBll::get_instance()->getAjkMemberInfoByAjkId($ajk_broker_id) == false)


##### 3. Bll_V2_BrokerResourceBll

    public function get_broker_main_business_by_broker_id($ajk_broker_id){
    	try{
	    	$da = Model_Anjuke_BrokerMainBusiness::data_access();
	    	$DBrokerMainBusiness = $da->filter(Model_Anjuke_BrokerMainBusiness::AJK_BROKERID, $ajk_broker_id)->find_one();
	    	unset($da);
    	}catch (Exception $e){
    		$DBrokerMainBusiness = null;
    	}
    	$type = !is_null($DBrokerMainBusiness)?$DBrokerMainBusiness->type:Model_Anjuke_BrokerMainBusiness::ENUM_TYPE_RESIDENCE;
    	return $type;
    }
		
		
		
##### 4. Model_Anjuke_BrokerMainBusiness

    public static function get_mapping() {
        return array(
            'table' => 'ajk_broker_main_business',
            'key' => self::ID,
            'columns' => array(
                self::ID => 'id',
                self::MEMBER_ID => 'member_id',
                self::AJK_BROKERID => 'ajk_brokerid',
                self::HZ_BROKERID => 'hz_brokerid',
                self::JP_BROKERID => 'jp_brokerid',
                self::TYPE => 'type',
                self::UPDATETIME => 'updatetime'
            )
        );
    }
    
    
##### 5. Bll_V2_ReportManagerActionBll


	public function get_my_broker_num_b($mid, $level, $city_id){
		if($level == 'P') $level_sql = "left(a.level, 1) not in ('A', 'B', 'C', 		'D', 'E', 'F', 'G', 'H')";
		else $level_sql = "left(a.level, 1)='{$level}'";
		$da = Model_Member::data_access();
		$sql = "select count(*) as num from e_member a left join e_member_ext b on a.id=b.member_id left join e_store_jinpu c on b.real_store_id=c.id where c.mid ={$mid} and c.state=1 and c.city_id={$city_id} and ".$level_sql." and b.city_id={$city_id} and b.is_in_library=".Model_MemberExt::ENUM_LIBRARY_IN;
		$values = array();
		$result = $da->native_sql($sql, $values);
    	unset($da);
    	return $result[0]['num'];
    }
    
    
	public function get_my_broker_ids($manager_id, $level){
		$broker_ids = array();
		if($level == 'P'){
			$da = Model_Member::data_access();
			$sql = "select a.id as id from e_member a left join e_member_ext b on a.id=b.member_id left join e_store_jinpu c on b.real_store_id=c.id where c.mid ={$manager_id} and c.state=1 and a.level='' and b.is_in_library=".Model_MemberExt::ENUM_LIBRARY_IN;
			$values = array();
			$result = $da->native_sql($sql, $values);
	    	unset($da);
	    	if($result){
		    	foreach ($result as $value){
					$broker_ids[] = $value['id'];
				}
	    	}
	    	
		}else{
			$da = Model_Stats_ManagerInfoDetail::data_access();
	    	$DBrokers = $da->filter(Model_Stats_ManagerInfoDetail::MANAGER_ID, $manager_id)
	    				   ->filter_by_op(Model_Stats_ManagerInfoDetail::LEVEL, 'like', $level.'%')
	    				   ->find();
	    	unset($da);
	    	if($DBrokers){
	    		foreach ($DBrokers as $DBroker){
	    			$broker_ids[] = $DBroker->member_id;
	    		}
	    	}
		}
    	return $broker_ids;
    }
    		

##### 6. Model_BrokerPackageIdx
	 public static function get_mapping(){
        return array(
            'table' => 'i_broker_package',
            'key' => self::ID,
            'columns' => array(
              self::ID => 'id',
              self::BROKER_ID => 'broker_id',
              self::PACKAGE_ID => 'package_id',
              self::START_TIME => 'start_time',
              self::END_TIME => 'end_time',
            )
        );
    }
    
    
##### 7. Model_IconManage   
     public static function get_mapping() {
        return array(
            'table' => 'e_icon_manage',
            'key' => self::ID,
            'columns' => array(
                self::ID => 'id',
                self::BROKER_ID => 'broker_id',
                self::OFFICE_RENT_PUBLIC => 'office_rent_public',
                self::OFFICE_RENT_MANAGE => 'office_rent_manage',
                self::OFFICE_SALE_PUBLIC => 'office_sale_public',
                self::OFFICE_SALE_MANAGE => 'office_sale_manage',
                self::SHOP_RENT_PUBLIC => 'shop_rent_public',
                self::SHOP_RENT_MANAGE => 'shop_rent_manage',
                self::SHOP_SALE_PUBLIC => 'shop_sale_public',
                self::SHOP_SALE_MANAGE => 'shop_sale_manage',
                self::ANALYSIS => 'analysis',
                self::CREATE_TIME => 'create_time',
            )
        );
    }
    
    
##### 8. Model_Ark_ProClick

  		return array(
            'table' => 'hp_global_pro_click_' . $suffix,
            'key' => self::ID,
            'columns' => array(
                self::ID => 'id',
                self::PRO_ID => 'pro_id',
                self::PLAN_ID => 'plan_id',
                self::BROKER_ID => 'broker_id',
                self::BROKER_TYPE => 'broker_type',
                self::COMM_ID => 'comm_id',
                self::CITY_ID => 'city_id',
                self::USER_TYPE => 'user_type',
                self::TRADE_TYPE => 'trade_type',
                self::CHANNEL => 'channel',
                self::ENTRY => 'entry',
                self::UID => 'uid',
                self::GUID => 'guid',
                self::IP => 'ip',
                self::CLICK_TIME => 'click_time'
            )
        );    		
		
##### 9. Model_Ark_ProClickInvalidate

 		public static function get_mapping($suffix = 'today') {
            $suffix = $suffix == 'today' ? date('Ymd') : $suffix;
            return array(
            'table' => 'hp_global_pro_click_invalidate_'.$suffix,
            'key' => self::ID,
            'columns' => array(
                self::ID => 'id',
                self::PRO_ID => 'pro_id',
                self::PLAN_ID => 'plan_id',
                self::BROKER_ID => 'broker_id',
                self::BROKER_TYPE => 'broker_type',
                self::COMM_ID => 'comm_id',
                self::CITY_ID => 'city_id',
                self::USER_TYPE => 'user_type',
                self::TRADE_TYPE => 'trade_type',
                self::UID => 'uid',
                self::GUID => 'guid',
                self::IP => 'ip',
                self::CLICK_TIME => 'click_time',
                self::CHANNEL => 'channel',
                self::ENTRY => 'entry',
                self::STATUS => 'status',
                self::REF => 'ref',
                self::IR => 'ir',
                self::UNIQID => 'uniqid',
                self::RULE_IDX => 'rule_idx'
            )
        );
    }		
    
    
##### 10. Model_Crm_BrokerPackage
 		public static function get_mapping() {
        return array(
            'table' => 'i_broker_package',
            'key' => self::ID,
            'columns' => array(
                self::ID => 'id',
                self::BROKER_ID => 'broker_id',
                self::PACKAGE_ID => 'package_id',
                self::PACKAGE_NAME => 'package_name',
                self::START_TIME => 'start_time',
                self::END_TIME => 'end_time'
            )
        );
    }    
    
##### 11. Model_Crm_ContractPortLog

    public static function get_mapping() {
        return array(
            'table' => 'e_contract_port_log',
            'key' => self::PORT_ID,
            'columns' => array(
                self::PORT_ID => 'port_id',
                self::BROKER_ID => 'broker_id',
                self::START => 'start',
                self::END => 'end'
            )
        );
    }
    
    
##### 12. MOdel_Log_Broker

	public static function get_mapping(){
		return array(
			'table' => 'l_broker',
			'key' => self::ID,
			'columns' => array(
				self::ID => 'id',
				self::BROKER_ID => 'broker_id',
				self::BROKER_NAME => 'broker_name',
				self::CONTENT_JSON => 'content_json',
				self::FORMER_JSON => 'former_json',
				self::CITY_ID => 'city_id',
				self::COMMENT => 'comment',
				self::RESULT_JSON => 'result_json',
				self::CRM_USER_ID => 'crm_user_id',
				self::CREATE_TIME => 'create_time',
				self::IS_FROM => 'is_from'
			)
		);
	}
	
##### 13. Model_Log_BrokerDelete

		public static function get_mapping() {
        return array(
            'table' => 'l_broker_delete',
            'key' => self::ID,
            'columns' => array(
                self::ID => 'id',
                self::COMPANY_ACCOUNT_ID => 'company_account_id',
                self::GROUP_ACCOUNT_ID => 'group_account_id',
                self::BROKER_ID => 'broker_id',
                self::SOURCE => 'source',
                self::CREATE_TIME => 'create_time'
            )
        );
    }	
    
##### 14. Model_PPC_PricingClick

	return array(
            'table' => 'hp_global_pricing_click_' . $suffix,
            'key' => self::ID,
            'columns' => array(
                self::ID => 'id',
                self::PLAN_ID => 'plan_id',
                self::PRO_ID => 'pro_id',
                self::BROKER_ID => 'broker_id',
                self::BROKER_TYPE => 'broker_type',
                self::COMM_ID => 'comm_id',
                self::CITY_ID => 'city_id',
                self::USER_TYPE => 'user_type',
                self::TRADE_TYPE => 'trade_type',
                self::CHANNEL => 'channel',
                self::ENTRY => 'entry',
                self::UID => 'uid',
                self::GUID => 'guid',
                self::IP => 'ip',
                self::CLICK_TIME => 'click_time'
            )
        );   
        
        
##### 15. Model_PPC_ProClickInvalidate

 	public static function get_mapping($suffix = 'today') {
        $suffix = $suffix == 'today' ? date('Ymd') : $suffix;
        return array(
            'table' => 'hp_global_pricing_click_invalidate_' . $suffix,
            'key' => self::ID,
            'columns' => array(
                self::ID => 'id',
                self::PRO_ID => 'pro_id',
                self::PLAN_ID => 'plan_id',
                self::BROKER_ID => 'broker_id',
                self::BROKER_TYPE => 'broker_type',
                self::COMM_ID => 'comm_id',
                self::CITY_ID => 'city_id',
                self::USER_TYPE => 'user_type',
                self::TRADE_TYPE => 'trade_type',
                self::UID => 'uid',
                self::GUID => 'guid',
                self::IP => 'ip',
                self::CLICK_TIME => 'click_time',
                self::CHANNEL => 'channel',
                self::ENTRY => 'entry',
                self::STATUS => 'status',
                self::REF => 'ref',
                self::IR => 'ir',
                self::UNIQID => 'uniqid',
                self::RULE_IDX => 'rule_idx'
            )
        );
    }  
    
##### 16. Model_Stats_WGBroker

	 public static function get_mapping() {
        return array(
            'table' => 'solly_jp_wg_broker_stop',
            'key' => self::ID,
            'columns' => array(
                self::ID => 'id',
                self::LOG_DT => 'log_dt',
                self::CITY_ID => 'city_id',
                self::BROKER_ID => 'broker_id',
                self::BROKER_NAME => 'broker_name',
                self::START_DT => 'start_dt',
                self::END_DT => 'end_dt'
            )
        );
    }          
         
	
