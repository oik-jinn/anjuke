金铺房源api   
=====================
------------------


####一、  房源信息详情
    
事例地址(可更新代码替换成自己的域名)：

**http://sh.jieliu.xzl.dev.anjuke.com/getHouse?fid=6000415_6000330_6000495&imgw=122&imgh=69**

传入参数：

    @fid 房源id ，支持多个，'_'隔开
    @imgw @imgh 取出默认图的大小

返回数据(json格式)：

``` 
{

    "6000330":{
        "id":"6000330", //房源id
        "title":"111111111111111111",//房源标题
        "area":"111",//面积
        "floor":"第1层",//楼层（个人房源没有）
        "total_price":111,//总价
        "total_price_unit":"万元",//总价单位
        "unit_price":"10000",//单价
        "unit_price_unit":"元/平米",//单价单位
        "list_time":1375434324,//展示时间
        "expire_time":1375434324,//过期时间
        "is_offline":true,//是否过期，过期则为true
        "house_type":"4",//房源类型
        "property_id":"190026",//个人房源此处可为0
        "property_info":{//楼盘信息
                "id":"190026",//楼盘id
                "address":"望园路1588弄",//楼盘地址
                "name":"绿地新城1号",//楼盘名称
                "district_name":"近郊",//区域
                "block_name":"奉贤",//板块
                "developer":"上海绿地奉贤置业有限公司"//开发商
        },
        "property_type_name":"写字楼",//楼盘类型
        "member_info":{//经纪人信息
                "id":"594102",
                "real_name":"sugar",//真实名字
                "mobile":"15800647806",//显示经纪人手机
                "show_member_name":"sugar"//显示经纪人名称（经纪人名字，个人房源联系人）
        },
        "default_img_url":"http://pic1.ajkimg.com/display/hj/5d3e59ebd90e2c884152d938d1dee212/120x90c.jpg",//房源默认图
        "show_house_imgs_url":"http://sh.jieliu.sp.dev.anjuke.com/zu/6000330#shopimage",//房源单页图片锚点
        "show_house_detail_url":"http://sh.jieliu.sp.dev.anjuke.com/shou/6000330",//房源单页详情锚点
        "show_house_url":"http://sh.jieliu.sp.dev.anjuke.com/shou/6000330",//房源单页
        "show_house_map_url":""//房源单页的地图锚点
    } ,
    "6000495":{
        "id":"6000495",
        "title":"测试推广房源总数20",
        "area":"11",
        "floor":"第1层",
        "monthly_rent":11,//月租金
        "monthly_rent_unit":"元/月",//月租金单位
        "daily_rent":0.03,//日租金
        "daily_rent_unit":"元/平米•天",//日租金单位
        "list_time":1375437559,
        "expire_time":1375437559,
        "is_offline":true,
        "house_type":"3",
        "property_id":"101096",
        "property_info":{
            "id":"101096",
            "address":"吴中路369号",
            "name":"美恒大厦1号",
            "district_name":"徐汇",
            "block_name":"万体馆",
            "developer":"上海美恒大厦管理有限公司"
        },
        "property_type_name":"写字楼",
        "member_info":{
            "id":"575927",
            "real_name":"崔鹏",
            "mobile":"18516113886",
            "show_member_name":"崔鹏"
        },
        "default_img_url":"http://pic1.ajkimg.com/display/hj/8cb6ea524d8fcb2ac6ca785c86b3ba62/120x90c.jpg",
        "show_house_imgs_url":"http://sh.jieliu.sp.dev.anjuke.com/zu/6000495#shopimage",
        "show_house_detail_url":"http://sh.jieliu.sp.dev.anjuke.com/zu/6000495",
        "show_house_url":"http://sh.jieliu.sp.dev.anjuke.com/zu/6000495",
        "show_house_map_url":"http://sh.jieliu.sp.dev.anjuke.com/zu/6000495#map"
    },
    "6000415":{
        //
    },
}

```  


ps：有什么问题，请及时联系，谢谢。