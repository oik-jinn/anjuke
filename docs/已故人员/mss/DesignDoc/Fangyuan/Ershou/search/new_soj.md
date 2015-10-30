房源列表新增曝光id设计
=====

项目背景:
----
* 为了精确统计点击量，新增曝光id来跟踪用户浏览足迹
* 相关地址：http://git.corp.anjuke.com/_sketch/sketch/issues/29


目标:
----
* 曝光ID和点击、扣费串联起来


涉及到的业务
----

* 业务频道：二手房 & 单页
* 上线城市：上海（随新框架中的房源单页同步推向全国）


代码结构:
----

### 1. 获取曝光id

##### 1.1 api接口说明 

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


##### 1.2 代码说明

* 新增获取曝光id类文件

		app-common/classes/ppc/Exposure.php
			//获取曝光id			
			public function getExposureIdBYApi(){} 


### 2. 列表页逻辑

* 为了不影响列表页逻辑，在controller中直接对生成好了的soj数据进行修改（增加曝光id，并修改版本为3.0），并修改房源数据（修改房源url－增加曝光id），逻辑如下：

		判断城市是否是上海
		↓
		生成请求api接口需要的参数
		↓
		请求api接口
		↓
		获取曝光id
		↓
		修改生成好了的soj数据 & 修改房源url地址
		↓
		返回新soj、房源数据

* 代码逻辑如下

		app-anjuke/controller/finding/Sale.php
			修改房源数据
			protected function updatePropAddEquid(){}
			生成请求api接口需要的参数
			private function createGetEquidParams(){}
			修改生成好了的soj数据
			private function updateSojAddEquid(){}
			修改房源url地址
			private function updatePropUrlAddEquid(){}
			
			

### 3. 单页逻辑
 * 判断url参数中是否包含equid值：

		如果存在
			则在发送soj数据中增加曝光字段值 equid:xxxxx ，并将版本修改为3.0；
			
		不存在
			则不修改



服务器config配置:
----
* 老框架 app-anjuke/config/api.php 增加如下配置

		//获取曝光id		$config['api_get_equid_url'] = "http://10.10.6.6:8080/service-exp/rest/exp/getEquid";		//毫秒		$config['api_get_equid_url_timeout'] = 10;

