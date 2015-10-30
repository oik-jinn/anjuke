## slow query处理记录
### 问题1
* slow query

    ```
    1894	
    SELECT proid, areanum FROM ajk_propertys 
    	WHERE commid = 'S' AND roomnum = 'S' 
    	LIMIT N
    Query sample:
    	SELECT ProId, AreaNum FROM ajk_propertys 
    	WHERE CommId = '3691' AND RoomNum = '4' LIMIT 1
    #v2:Api_Base@Info.php (582) propertys_sh_db 1445961748;
    ```
* 处理过程

    > 查找slow query来源
    
    ```
    全局搜索代码仓库
    api的代码仓库没找到，于是查了下pc的代码仓库
    最终找到是pc的一个接口导致
    导致slow的请求：
    	http://www.anjuke.com/api/comm/pageinfo/?commid=1930&rsl=328
    rsl含义
    	const BIT_BASE_INFO = 1;//基本信息
    	const BIT_CODE_INFO = 2;//城市区域板块信息
    	const BIT_DEFIMG = 4;//默认图信息
    	const BIT_DAILYPRICE = 8;//今日均价
    	const BIT_LASTWEEKPRICE = 16;//上周周均价
    	const BIT_CURRENTMONTH = 32;//本月均价
    	const BIT_ROOMLIST = 64;//户型列表
    	const BIT_ROUNG = 128;//小区周边信息
    	const BIT_INFOINROOM = 256;//小区各户型下的统计信息
    	const BIT_RANK = 512;//小区的排名信息
    	const BIT_LASTYEARPRICE = 1024;//去年当月的价格
    	const BIT_ONEYEARPRICE_COMM = 2048;//小区近一年的月均价
    	const BIT_TWELVEWEEKPRICE_COMM = 4096;//小区近12个周的周均价
    	const BIT_ONEYEARPRICE_AREA = 8192;//区域近一年的月均价
    	const BIT_TWELVEWEEKPRICE_AREA = 16384;//区域近12个周的周均价
    	const BIT_COMM_COUNT_IN_AREA = 32768;//区域下的小区数量
    ``` 
	> 查找调用以上请求的api
	
	```
	因为该slow是api这边因为的
	所以查找api调用上面pc接口的地方
	api接口：
		1、二手房单页接口：
			http://api.anjuke.com/mobile/1.3/property.getdescandcomms?id=11-356242949&guid=95a3bbdc7eaae1ca7215b2c469fa51e5&source_type=1&api_key=eb8cd4ef60fde7580260cf9cf4250a24&sig=0778dc088218f22889e5bd7b87cf3843&v=5.0.2&i=867068022325251&cv=9.0&o=leo-user+5.0.2+LRX22G+V6.7.2.0.LXHCNCH+release-keys&macid=33cd5109c031dccffadb4fcfa277dfea&cid=11&pm=dev111111&m=Android-MI+NOTE+Pro&app=a-ajk
			其中字段property.props来源以上接口
			含义描述：每种户型下的房源数量加起来的总数
		调用以上接口地址：
			http://www.anjuke.com/api/comm/pageinfo/?commid=268470&rsl=379
		使用到的字段：
			ROOM_LIST
 			INFO_IN_ROOM_LIST
 		2、小区单页接口：
 			http://api.anjuke.com/anjuke/4.0/comm/get?community_id=268470&is_nocheck=1
 			其中字段room_list来源以上接口
 			含义描述：户型列表，包括均价、面积、房源数量
 		调用以上接口地址：
			http://www.anjuke.com/api/comm/pageinfo/?commid=268470&rsl=65407
		使用到的字段：
			ROOM_LIST
	 		INFO_IN_ROOM_LIST
	```
	> 确认接口字段是否还在使用
	
	```
	1、看了下页面展示需求，以上字段应该不再使用
	2、跟app沟通（包括android和ios）
		以上两个接口中的字段确实不再使用
	```
	> 修改方案
	
	```
	因为是老的接口，为防止app crash，不能直接去掉接口中的对应字段
	最好的方式的保留字段key，value置空
	
	根据小区接口提供的rsl参数，去掉导致slow的字段的获取
	二手房单页
		rsl改为315
		http://www.anjuke.com/api/comm/pageinfo/?commid=268470&rsl=315
	小区单页
		rsl改为65343
		http://www.anjuke.com/api/comm/pageinfo/?commid=268470&rsl=65343
	```
