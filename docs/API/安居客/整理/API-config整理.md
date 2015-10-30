## API——config整理

### anjuke-mobile-api
* anjuke-mobile-api/app-api/config/ajk_performance.php

    ```
    无线上配置
    性能分析zmq host
    $config['performance_zmq_host'] = 'tcp://10.10.3.67:7555';
    ```

* anjuke-mobile-api/app-api/config/aps_services.php

    ```
    线上无配置
    $config['api'] = array(
        'client' => array('tcp://10.10.3.35:6000'),
    );
    $config['mss'] = array(
        'client' => array('tcp://10.10.3.40:6000'),
    );
    
    //敏感词
    线下：
    $config['sensitive'] = array(
        'client' => array('tcp://10.10.9.36:8965'),
    )
    线上：
    $config['sensitive'] = array(
        'client' => array(
		     'tcp://10.10.9.76:8966',
		     'tcp://10.10.9.76:8967',
	     ),
	 )
    
    //site 违禁词
    线下：
    $config['prohibited'] = array(
        'client' => array('tcp://10.10.9.36:8967'),
    );
    线上：
    $config['prohibited'] = array(
        'client' => array(
            'tcp://10.10.9.76:8966',
            'tcp://10.10.9.76:8967',
         ),  
    );
    ```
* anjuke-mobile-api/app-api/config/common.php

    ```
    仅线上：
    $config['internal_api'] = "http://10.10.6.6:8080/service-internal/rest/hplan/batch/isHaoplan?json";
    $config['internal_ppc'] = "http://10.10.6.6:8080/service-ppc/rest/ppc/batch/isPpc?json";
    $config['internal_api_url'] = "http://10.10.6.6";
    //google坐标维护登录接口地址
$config['google_auth_url'] = "http://10.10.6.6:8080/RoleService/roleService/login";

    线上同线下
    $config["solrKeywords"] = "http://10.10.6.219:8999/seg/pkuseg";
    线上无配置
    $config["prop_info_url"] = "http://10.10.6.6:8080/service-ppc/rest/cache/searchListPageAllInfo";
    
    线下：$config["exp_get_equid"] = "http://10.20.3.9:8099/service-exp/rest/exp/getEquid";
    线上：$config["exp_get_equid"] = "http://10.10.6.6:8080/service-exp/rest/exp/getEquid"
    
    线下：$config["exp_property_page_init_process"] = "http://10.20.3.9:8099/service-exp/rest/exp/propertyPageInitProcess";
    线上：$config["exp_property_page_init_process"] = "http://10.10.6.6:8080/service-exp/rest/exp/propertyPageInitProcess"
    
    线上无配置
    $config['broker_service'] = 'http://10.10.6.6:8080/service-internal/rest/brokers/';
    ```
    
* anjuke-mobile-api/common/config/nlogger.php
    
    ```
    线上无配置(应该没用)
    $config['host'] = '10.10.3.76';
    ```
    
### haozu
* haozu/app-haozu-api/config/ajk_performance.php

    ```
    //性能分析zmq host
    $config['performance_zmq_host'] = 'tcp://10.10.3.67:7555';
    ```
* haozu/app-haozu-api/config/aps_services.php

    ```
    线上无配置
    $config['api'] = array(
        'client' => array('tcp://127.0.0.1:6000')
    );
    $config['pinyin'] = array(
        'client' => array('tcp://10.10.6.219:50000')
    );
    $prohibited_host = array("tcp://10.10.9.36:8966","tcp://10.10.9.36:8967","tcp://10.10.9.36:8965");
    $config['prohibited'] = array('client' => $prohibited_host[rand(0, 2)]);
    
    线下：
    $config['mss'] = array(
        'client' => array('tcp://10.10.3.46:6000')
    );
    线上：
    $config['mss'] = array(
        'client' => array('tcp://10.10.3.40:6000')
    );
    
    线下：
    $config['sensitive'] = array(
        'client' => array('tcp://10.10.3.40:6000')
    );
    线上：
    $config['sensitive'] = array(
        'client' => array('tcp://10.10.3.46:6000')
    );

    ```
    
* haozu/app-haozu-api/config/common.php

    ```
    仅线上：
    $config['autosearchQueryUrl'] = 'http://10.0.1.102:8984/search-suggestion/select?';
    
    //页面点击
    线下：
    $config["exp_property_page_init_process"] = "http://10.20.3.9:8099/service-exp/rest/exp/propertyPageInitProcess";
    线上：
    $config["exp_property_page_init_process"] = "http://10.10.6.6:8080/service-exp/rest/exp/propertyPageInitProcess";
    
    //获取曝光ID
    线下：$config["exp_get_equid"] = "http://10.20.3.9:8099/service-exp/rest/exp/getEquid";
    线上：
    $config["exp_get_equid"] = "http://10.10.6.6:8080/service-exp/rest/exp/getEquid";
    
    线上无配置
    $config['base_domain'] = "192.168.1.191";
    $config['internal_api_url'] = "http://10.10.6.6";
    $config["poi_geocode"] = "http://10.10.6.219/poi/geocode.php";
    $config["poi_place"] = "http://10.10.6.219/poi/geocode.php";
    
    ```
* haozu/app-haozu-core/config/aps_services.php

    ```
    线上无配置
    $config['pinyin'] = array(
        'client' => array('tcp://10.10.6.219:50000')
    );
    $config['bayes'] = array(
        'client' => array('tcp://10.10.3.46:51000')
    );
    ```
* haozu/app-haozu-core/config/search.php

    ```
    线上无配置
    $config['bayes_url'] = "http://10.10.3.46/bayes/guess.php";
    $config['bayes_all_url'] = "http://10.10.3.46/bayes/guess_all.php";
    $config['segment_url'] = "http://10.10.3.46:8999/seg/pkuseg";
    $config['poi_url'] = "http://10.10.3.46/poi/geocode.php";
    ```
* haozu/app-haozu-core/config/service.php

    ```
    线上无配置
    $config['selectCommunity'] 	= "http://10.0.1.111:8080/RentService/rest/search/communities/";
    $config['ajk_property']		= 'http://10.0.1.111:8080/api4haozu/propertyinfo?proid=';
    $config['ajk_broker']       = 'http://10.0.1.111:8080/api4haozu/brokers?brokerid=';
    $config['ajk_propimages']   = 'http://10.0.1.111:8080/api4haozu/propertypic?proid=';
    $config['ajk_commimages']   = 'http://10.0.1.111:8080/api4haozu/communitypic?commid=';
    $config['bayes_train'] = "http://10.10.3.46/bayes/train.php";
    $config['bayes_untrain'] = "http://10.10.3.46/bayes/untrain.php";
    $config['bayes_delete'] = "http://10.10.3.46/bayes/delete.php";
    //添加手机黑名单
    $config['add_black_phone'] = 'http://10.10.6.6:8080/service-internal/rest/brokers/addBrokerPhone';
    //删除手机黑名单
    $config['del_black_phone'] = 'http://10.10.6.6:8080/service-internal/rest/brokers/delBrokerPhone';
    $config['pkuseg_url']	= 'http://10.10.6.219:8999/seg/pkuseg?text=';
    // 过滤非法关键字
    $config['filter_keyword'] = "http://10.10.6.133:8080/service-keywords-release/rest/keyword/listKeywordsByGroupId/10/";

    ```
    
* haozu/app-haozu-core/config/web3service.php

    ```
    <?php
    $config['broker_info']	= 'http://10.10.6.6:8080/service-internal/rest/brokers/';
    //根据手机号码获取经纪人信息
    $config['broker_mobile']  = 'http://10.10.6.6:8080/service-internal/rest/brokers/getInfo?mobile=';
    // 在安居客登录
    $config['broker_ajk_login']	= 'http://10.10.6.6:8080/service-internal/rest/brokers/login?json';
    // 修改经纪人密码
    $config['broker_modifyPwd']	= 'http://10.10.6.6:8080/service-internal/rest/brokers/changeByMobile?json';
    // 经纪人注册
    $config['broker_registerBroker'] = 'http://10.10.6.6:8080/service-internal/rest/brokers/regist?json';
    // 经纪人保存名片
    $config['broker_modifyCmy'] 	= 'http://10.10.6.6:8080/service-internal/rest/brokers/saveBnsCard?json';
    $config['broker_updateCmy']		= 'http://10.10.6.6:8080/service-internal/rest/brokers/updateBnsCard?json';
    // 保存头像
    $config['broker_createHead']	= 'http://10.10.6.6:8080/service-internal/rest/brokers/saveHead?json';
    // 创建身份证
    $config['broker_createCard']	= 'http://10.10.6.6:8080/service-internal/rest/brokers/saveIdCard?json';
    // 修改头像
    $config['broker_modifyHead']	= 'http://10.10.6.6:8080/service-internal/rest/brokers/updateHead?json';
    // 修改email
    $config['broker_modifyEmail']	= 'http://10.10.6.6:8080/service-internal/rest/brokers/changeMail?json';
    // 经纪人送审
    $config['broker_approve']		= 'http://10.10.6.6:8080/service-internal/rest/brokers/toApprove?json';
    // 修改手机
    $config['broker_changeMobile']		= 'http://10.10.6.6:8080/service-internal/rest/brokers/changeMobile?json';
    // 验证手机是否释放过
    $config['mobile_release']        = "http://10.10.6.6:8080/service-internal/rest/brokers/mobileReleaseCheck?mobile=";
    // 获取端口
    $config['port']['get']				= 'http://10.10.6.6:8080/service-internal/rest/iport/findOrderById/';
    // 操作失败
$config['port']['error']			= 'http://10.10.6.6:8080/service-internal/rest/iport/sendErr';
    //端口创建成功
$config['port']['createOk']			= 'http://10.10.6.6:8080/service-internal/rest/iport/createOrderOK';
    //端口创建进人激活成功
$config['port']['createInActiveOk']	= 'http://10.10.6.6:8080/service-internal/rest/iport/createToActiveOK';
    // 端口进人成功
$config['port']['inPortFirstOk']	= 'http://10.10.6.6:8080/service-internal/rest/iport/inBrokerOK';
    // 端口激活成功
$config['port']['activePortOk']		= 'http://10.10.6.6:8080/service-internal/rest/iport/activeOrderOK';
    // 开通预约端口
$config['port']['activeAppPortOk']		= 'http://10.10.6.6:8080/service-internal/rest/iport/openUnuseOrderOK';
    // 端口换人成功
$config['port']['changePortOk']		= 'http://10.10.6.6:8080/service-internal/rest/iport/changeBrokerOK';
    // 端口出人成功
$config['port']['outPortOk']		= 'http://10.10.6.6:8080/service-internal/rest/iport/brokerOutOrderOk';
    // 端口关闭成功
$config['port']['closePortOk']		= 'http://10.10.6.6:8080/service-internal/rest/iport/closeOrderOK';
    // 端口提前成功
$config['port']['advancePortOk']	= 'http://10.10.6.6:8080/service-internal/rest/iport/advanceOrderOK';
    // 端口进人激活
    $config['port']['inActivePortOk'] 	= 'http://10.10.6.6:8080/service-internal/rest/iport/inbrokerToActiveOK';
    // 端口升级成功
    $config['port']['upgradePortOk']   = 'http://10.10.6.6:8080/service-internal/rest/iport/upgradeOrderOK';
    //经纪人线上购买好租端口打通
    $config['port']['haozuPayNotify']   = 'http://10.10.6.6:8080/service-internal/rest/logger/haozuPayNotify';
?>
    ```
* haozu/app-haozu-web/config/common.php
    
    ```
    线下：
    $config['autosearchQueryUrl'] = 'http://10.0.0.130:8983/search-suggestion/select?';
    线上：
    $config['autosearchQueryUrl'] = 'http://10.0.1.102:8984/search-suggestion/select?';
    ```
    
### jinpu
* jinpu/app-common/config/aps_services.php

    ```
    线上无配置
    $config['performance'] = 'tcp://10.10.3.67:7555';
    $config['site_merge'] = array(
        //'host' => 'fp04.java.dev.anjuke.com',
        'host' => '192.168.201.190',
        'port' => '5672',
        'vhost' => '/',
        'login' => 'guest',
        'password' => 'guest'
    	);

    $config['device'] = array(
        'mysql' => array(
            'frontend' => 'tcp://10.10.3.44:18889',
            'backend'  => 'itcp://10.10.3.44:18890'
        ),
        'solr'  => array(
            'frontend' => 'ipc:///tmp/solr-client.ipc',
            'backend'  => 'ipc:///tmp/solr-server.ipc'
    ),
        'redis' => array(
            'frontend' => 'ipc:///tmp/redis-client.ipc',
            'backend'  => 'ipc:///tmp/redis-server.ipc'
        )
    );
    线下同线上
    $config['mss'] = array(
        'client' => array('tcp://10.10.3.150:52006')
    );
    
    线下：
    $config['search_mss'] = array(
        'client' => array('tcp://10.10.3.46:6000')
    );
    线上：
    $config['search_mss'] = array(
		  'client' => array('tcp://10.10.3.40:6000')
    );
    ```
* jinpu/app-common/config/common.php

    ```
    线上无配置
    $config['aa_js']['test']              = 'http://192.168.1.106/bb.js';
    $config['sms']                        = 'http://10.10.6.202/send.php';
    //获取曝光ID
    $config["exp_get_equid"] = "http://10.10.6.6:8080/service-exp/rest/exp/getEquid";
    //页面点击
    $config["exp_property_page_init_process"] = "http://10.10.6.6:8080/service-exp/rest/exp/propertyPageInitProcess";
    
    线下：
    $config['anjuke_merge_searver']        = "http://10.20.6.102:8080";
    线上：
    $config['anjuke_merge_searver']       = 'http://10.10.6.6:8080';
    ```
    
* jinpu/app-common/config/k3.php

    ```
    线上无配置
    //K3系统WebService Domain
    $config['domain'] = 'http://192.168.1.60/ruitingshanghai/';
    ```
* jinpu/app-common/config/resource.php  

    ```
    线上无配置
    $config['file_post_url'] = "192.168.10.202:8080/upload";
    $config['file_download_url']     = "192.168.10.202:8080/download";
    ```
* amqp.php

    ```
    仅线下
    $config['default'] = array(
    'host' => '10.10.6.190',
    'port' => '5672',
    'vhost' => '/',
    'login' => 'guest',
    'password' => 'guest'
);
    ```
* socket.php

    ```
    仅线上
     $config['pinyin'] = array('address' => '10.10.6.195','port' => '5677','protocol' => SOL_TCP,'domain' => AF_INET,'type' => SOCK_STREAM);
    ```
* solr.php

    ```
    仅线上
    $config['solrcloud']['hostname'] = '10.10.6.51';
$config['solrcloud']['port'] = 8983;
    ```