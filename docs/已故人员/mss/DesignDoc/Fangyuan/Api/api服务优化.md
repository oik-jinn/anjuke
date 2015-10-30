### api服务优化
	
#### 1.老支付服务
	eg:请求地址，/netPayService/rest/.....
	是否调用:否
	
#### 2.老支付日志服务
	eg:请求地址，/netPayServicelog/rest/.....
	是否调用:是
	
#### 3.广告服务
	 eg:请求地址，/service-advertisements-release/rest/.....
	 是否调用:否
#### 4.诚信指数服务
	eg:请求地址，/service-honestyindex-release/rest/.....
	是否调用:否

#### 5.关键词搜索服务 
	eg:请求地址，/service-keywords-release/rest/.....
	是否调用:是
	A.
	使用场景:虚假房源
	文件:haozu-site/app-haozu-jobs/bin/synchronous_prop.php
	是否线上:否
	
	B.
	使用场景:敏感词过滤
	文件:haozu-site/app-haozu-jobs/bin/filter_illegal_keyword.php
	是否线上:否
	
	C.
	使用场景:敏感词过滤-test
	文件:haozu-site/app-haozu-jobs/bin/test-yshang.php
	是否线上:否
	
#### 6.手机layar服务 
	eg:请求地址，/service-layar/rest/.....
	是否调用:否
	
#### 7.老端口服务
	eg:请求地址，/service-ports-release/rest/.....
	是否调用:否
	
#### 8.老端口服务
	eg:请求地址，/service-ports-transaction/rest/.....
	是否调用:否
	
#### 9.权限服务
	eg:请求地址，/service-role/rest/.....
	是否调用:否