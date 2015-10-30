### 获取曝光id

* a、通过http api接口请求

		请求方式： POST
		接口地址
			pg环境：http://10.20.3.9:8099/service-exp/rest/exp/getEquid
			ga环境：http://10.10.6.6:8080/service-exp/rest/exp/getEquid

* b、请求参数名： expJson ，值为json字符串，其中json包含如下参数值

		userId:登录用户id
		channel:渠道
		sn:序号［曝光房源序号］
		propId:房源编号
		entry:展示得位置
		tradeType:房源类型
		hpType:点击类型

* c、入参 eg:

		expJson = 
		{
			"channel": 1,
			"userId": 1,
			"expList": [
			     {
		  		    "entry": 1,
    			    "sn": "1",
           			"tradeType": 1,
            		"hpType": 1,
            		"proId": 1
        		},
        		{
            		"entry": 2,
            		"sn": "2",
            		"tradeType": 2,
            		"hpType": 2,
            		"proId": 2
        		}
    		]
		}

		
* c、出参 eg:
		
		{
    		"status": "ok",
			"exps": [
        		{
            		"equid": "20140703890ede89-35",
           			"sn": "1"
        		},
        		{
            		"equid": "201407030f40f8c5-f4",
            		"sn": "2"
        		}
    		],
    		"count": 2
		}


* 新增获取曝光id类文件

	app-common/classes/ppc/Exposure.php
	//获取曝光id			
	public function getExposureIdBYApi(){} 


服务器config配置:
----
* 老框架 app-anjuke/config/api.php 增加如下配置

  //获取曝光id
  $config['api_get_equid_url'] = "http://10.10.6.6:8080/service-exp/rest/exp/getEquid";		//毫秒		$config['api_get_equid_url_timeout'] = 10;

