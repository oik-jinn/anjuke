
### 租房词库服务

	敏感词、屏蔽词、分词、分类词、搜索词、同义词、删词、无意词、基础词、门牌词、意图词
	
#### 1.调研背景

	a.租房列表页迁移词库相关说明
	
	b.梳理现有搜索逻辑
	
#### 2.名词解释

	租房列表页目前共涉及11类词,分别为敏感词、屏蔽词、分词(暂无)、分类词、搜索词、同义词、删词、无意词、基础词、门牌词、意图词.
	
	以上词名的具体解释说明如下:
	
		a.敏感词:一般是指带有敏感政治倾向（或反执政党倾向）、暴力倾向、不健康色彩的词或不文明语。eg:他妈的。
		
		b.屏蔽词:同敏感词。
		
		c.分词:将整个字符串进行分词处理。eg:中国是大国。会被分为中国 是 大国。
		
		d.分类词:按照KeyWords进行划分，eg:小区词、医院词等。
		
		e.搜索词:公司内部词库规定的词名。
		
		f.同义词:几个声音不同而意义相同或基本相同的词。
		
		g.删词:将KeyWords中无意义的词语进行过滤，拼凑为最终的搜索结果使用。
		
		h.无意词:公司内部特制修饰词，叹词等。
		
		i.基础词:公司内部定义。eg:二期、二区、二室等。
		
		j.门牌词:表名门牌号码的词。eg:号、弄、楼、平方等。
		
		k.意图词:公司内部定义。eg:二手房、租房、商铺等。
		
#### 3.敏感词/屏蔽词

    词库:banwords-dict
    
    method:mss.match
    
    方式:APS
    
    详情:略
   [参考地址](http://gitlab.corp.anjuke.com/_incubator/mss/tree/master)
   
#### 4.搜索词

	Path:haozu-site/app-haozu-web/classes/module/proplist/KeywordCategory.php
 	
 	method:search
 	
 	方式:APS
 	
 	详情:
 	
 		搜索词匹配->分类->小区词->医院经纬度->公园经纬度->viewport(视口)经纬度->学校经纬度->公交站点经纬度->公交路线
 [词库](http://git.corp.anjuke.com/liangshan/mss-dict/browse/master/ac.txt)
       
#### 5.同义词

	Path:
	
		/haozu-site/app-haozu-core/classes/dao/word/WordsMemcache.php
		
	Object and Function:Dao_Word_WordsMemcache中的get_word()
		
	DB:rent_db
	
	Table:zf_area_control
	

#### 6.意图词、无意词、基础词、门牌词

	Path:haozu-site/app-haozu-core/classes/dao/dict/DictMemcache.php
	
	Object and Function:Dao_Dict_DictMemcache中的get_all_words_by_type()
		
	DB:rent_db
	
	Table:zf_dict
	
	
	
#### 7.分词

[参考地址](http://gitlab.corp.anjuke.com/_incubator/chseg/blob/master/README.md)

	
[调用地址](http://10.10.6.219:8999/seg/pkuseg?text=')
	
#### 8.二手房搜索流程图
[参考地址](http://gitlab.corp.anjuke.com/_site/docs/blob/master/DesignDoc/Fangyuan/Ershou/search/SaleSearch.md)



