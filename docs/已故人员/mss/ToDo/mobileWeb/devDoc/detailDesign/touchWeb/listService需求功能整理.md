##TouchWeb 二手房列表页Service需求功能梳理
    功能主要分为两块
    1.筛选项
    2.搜索功能
##所需接口
    1.下拉词接口 
        1.1 下拉词内容是 小区名 (根据搜索词模糊匹配)
        1.2 API接口地址 http://hiapi.qa.anjuke.com/2.0/testPage/anjuke.php?api_id=121
    2.小区基本信息接口
        2.1 可根据 小区ID 或 搜索词 完全匹配小区 
        2.2 返回小区的基本信息 小区名、涨跌幅、均价……
        2.3 API接口地址 http://hiapi.qa.anjuke.com/2.0/testPage/anjuke.php?api_id=123
    3.筛选项接口
        3.1 筛选项可按需调用
        3.2 无数据的筛选项最好能排除
        3.3 搜索词命中小区不显示 区域/板块
        3.4 API接口地址 
            3.4.1 http://hiapi.qa.anjuke.com/2.0/testPage/anjuke.php?api_id=119 
            3.4.2 http://hiapi.qa.anjuke.com/2.0/testPage/anjuke.php?api_id=120
    4.搜索接口
        4.1 违禁词
        4.2 同小区
        4.3 同学区
        4.4 附近房源
        4.5 同首付房源
        4.6 API接口地址 http://hiapi.qa.anjuke.com/2.0/testPage/anjuke.php?api_id=165
    5.搜零推荐接口
    6.筛零推荐接口
    7.新房推荐接口
