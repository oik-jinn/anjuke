配置
-

* 新增排序特性
   需要c++内核支持package包, 更新替换so(merger + searcher)
   
* search配置
   * 路径: indexdata/config下面
   * base.confcontrol_port:更新索引的search_port:用于查询的
schema:
       * 分为三个块: fields(所有字段的定义) index(倒排字段) store(正排字段)
       * 文本相关性: 定义新的字段
       
       ```
   "package_name" : "title",
       "multi_fields_package" : true,
       "package_fields":
        [
             {"field_name":"titletext",      "boost" : 77, "term_weight" : true},
             {"field_name":"localtext",      "boost" : 10},
             {"field_name":"categorytext",   "boost" : 6},
             {"field_name":"keywordstext",   "boost" : 6},
             {"field_name":"parametertext",  "boost" : 1}
        ]
        注意: 排序字段和要返回结果的字段必须建立正     排, 其它字段没有必要
       ```

       * resource.conf 暂时用不到
       * scorer.conf 二次排序, 暂时用不到, 对第一次的top结果进行二次排序, 可以删除
			       
			```
			"SingleScorers": [
			    {"MethodName":"sorttest","SingleScorer":"sorttest"},
			    {"MethodName":"sortrand","SingleScorer":"sortrand"}
			  ]
			plugin.conf
			so.name_list = distance,sorttest,sortrand
			segment.confsearcher数据是分段的, 小段会向大段合并,段分为内存中或磁盘中, ondisk=true

			"Segments_Config": [
			    {
			      "LifeTime": 100000000,
			      "MaxCount": 1,
			      "OnDisk": true,
			      "CacheCapacity": 20000000,
			      "CountLifeInCache": 60,
			      "DocListLifeInCache": 30, //排序的缓存时间,单位:s
			      "DocListReduancyInCache": 10, //
			      "MaxDocListNumInCache": 1050
			    }

			so.sortrand.path=package/libgeneralscorer.so
			so.sortrand.parameters = primary_sort_field,primary_fulltitle_field,primary_titletext_field,primaysort_field_cache,algorithm,scorer_type
			so.sortrand.primary_fulltitle_field=title
			so.sortrand.primary_titletext_field=titletext
			so.sortrand.primary_sort_field = paramo5923
			so.sortrand.primaysort_field_cache=1000
			so.sortrand.algorithm=singleint[floor] * 1000000 + 10000 * singleint[floor_num] + random[info_id]
			so.sortrand.scorer_type=default
			```

       * 当你排序要修改, 见倒数第二行
       * 当你新增的时候, 复制上面代码, 再配置一下第一行
    * rank排序的说明:
    
	    ```
	       so.sortrand.algorithm=singleint[rank_level] * 1000000 + 10000 * singleint[rank_score] + random[info_id]
    ```
       * proxy/service/deploy/community/uesearchproxy.properties也要配置哦
       * community.sortnamemap=distance_distance_m,sorttest_sorttest_m,sortrand_sortrand_m

* 监控:
    * /home/www/release/esearch-universal-v1/ctrlcenter/controll
        * monitorAll.sh调用了三个脚本
        * mergermonitor.sh
        * instflowmonitor.sh
    * /home/www/release/esearch-universal-v1/monitor-vertical/warn-conf/warner.conf