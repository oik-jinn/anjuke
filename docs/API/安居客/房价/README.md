## API整理－房价

### 接口说明
* 接口地址

    ```
    http://api.beckyxu.dev.anjuke.com/anjuke/4.0/city/get?city_id=127&from=mobile&is_nocheck=1
    ```

* 代码位置

    ```
    仓库：二手房API（anjuke-mobile-api）
    类：V20_City_GetController
    ```
    
* 调用第三方接口

    ```
    调用了pc接口：
    http://www.anjuke.com/api/area/citypricetrend?cityid=127&wbegin=201507&wend=201518&mbegin=201405&mend=201504
    
    仓库：anjuke-site
    类:Api_Area_PriceWeekAndMonthController
    
    获取日均价
    select cal_dt,cityid,areacode,price_daily from ajk_dw_stats.dw_pricing_areacode_daily where areacode=4620 and cal_dt>='2015-05-12' and cal_dt<='2015-05-12'
    
    先获取城市／区域／板块的areacode
    anjuke_db
    select TYPEID,CITYID,TYPENAME,PARENTID,TYPECODE from ajk_commtype where CITYID=11 and PARENTID=0
    
    取DB表查询
    ajk_dw_stats
    select cal_dt,cityid,areacode,price_weekly from dw_pricing_areacode_weekly where areacode=? and cal_dt>=? and cal_dt<=?
    ```

### 逻辑分析

* 日均价`dayly`
* 
* 周房价`last_12w_data`
