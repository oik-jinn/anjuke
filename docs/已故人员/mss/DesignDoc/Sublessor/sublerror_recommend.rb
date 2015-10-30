###大业主房源单页推荐增加大业主房源展示设计文档
1. 项目背景
	* 概述
		* 增加大业主房源曝光
		* 提高vppv到400电话的转化率
	* 详细内容
		* PRD地址为:http://p.corp.anjuke.com/project/detail?id=2384		
2. 请求接口
	* 接口地址:http://sh.zu.anjuke.com/api/rec/pagerec/
		* demo http://sh.zu.anjuke.com/api/rec/pagerec/?cityid=11&id=D130&type=4&limit=9&resulttype=2&from=SITE_RENT_PAGE&r=0.08382598147727549
	* 返回参数格式:
		* demo <pre>{
    "status": "ok",
    "results": {
        "is_main_push": 0,
        "main_push_props": [],
        "near_props": [
            {
                "CITYID": "11",
                "TITLE": "有钥匙房源，随时可看，太阳都市花园（一至二期）2室低价出租",
                "LINK": "http://sh.zu.anjuke.com/fangyuan/34469342",
                "COMMNAME": "太阳都市花园一期",
                "ROOMNUM": "2",
                "HALLNUM": "2",
                "FITNAME": "普通装修",
                "PROTYPE": 1,
                "SOJ": "MAHOUT=0",
                "IMAGESRC": "http://pic1.ajkimg.com/m/a88454435b2970066d183e246673f29d/180x135c.jpg",
                "PROID": 34469342,
                "PROPRICE": "6000"
            },
            {
                "CITYID": "11",
                "TITLE": "有钥匙房源，随时可看，太阳都市花园（一至二期）2室低价出租",
                "LINK": "http://sh.zu.anjuke.com/fangyuan/34469169",
                "COMMNAME": "太阳都市花园一期",
                "ROOMNUM": "2",
                "HALLNUM": "2",
                "FITNAME": "普通装修",
                "PROTYPE": 1,
                "SOJ": "MAHOUT=0",
                "IMAGESRC": "http://pic1.ajkimg.com/m/eec9d2cbfe737b0a36133da947bc3103/180x135c.jpg",
                "PROID": 34469169,
                "PROPRICE": "6000"
            },
            {
                "CITYID": "11",
                "TITLE": "明日星城二期 全新全配 首次出租 全南户型 二十四小时看房",
                "LINK": "http://sh.zu.anjuke.com/fangyuan/34469049",
                "COMMNAME": "明日星城（一至二期）",
                "ROOMNUM": "2",
                "HALLNUM": "2",
                "FITNAME": "精装修",
                "PROTYPE": 1,
                "SOJ": "MAHOUT=0",
                "IMAGESRC": "http://pic1.ajkimg.com/m/25c023425948e712af44e03050752fa6/180x135c.jpg",
                "PROID": 34469049,
                "PROPRICE": "7800"
            },
            {
                "CITYID": "11",
                "TITLE": "有钥匙房源，随时可看，太阳都市花园（一至二期）2室低价出租",
                "LINK": "http://sh.zu.anjuke.com/fangyuan/34468546",
                "COMMNAME": "太阳都市花园一期",
                "ROOMNUM": "2",
                "HALLNUM": "2",
                "FITNAME": "普通装修",
                "PROTYPE": 1,
                "SOJ": "MAHOUT=0",
                "IMAGESRC": "http://pic1.ajkimg.com/m/a88454435b2970066d183e246673f29d/180x135c.jpg",
                "PROID": 34468546,
                "PROPRICE": "6000"
            }
        ],
        "far_props": [
            {
                "CITYID": "11",
                "TITLE": "稀缺士林华苑住宅118.99平抢租",
                "LINK": "http://sh.zu.anjuke.com/fangyuan/34448164",
                "COMMNAME": "士林华苑",
                "ROOMNUM": "2",
                "HALLNUM": "2",
                "FITNAME": "精装修",
                "PROTYPE": 1,
                "SOJ": "MAHOUT=0&spread=scanagain_p",
                "IMAGESRC": "http://pic1.ajkimg.com/m/3674657ba9a5f422935ee7e345f82fa2/180x135c.jpg",
                "PROID": 34448164,
                "PROPRICE": "9500"
            },
            {
                "CITYID": "11",
                "TITLE": "要租房的来找我.士林华苑2室2厅9000元",
                "LINK": "http://sh.zu.anjuke.com/fangyuan/30680383",
                "COMMNAME": "士林华苑",
                "ROOMNUM": "2",
                "HALLNUM": "2",
                "FITNAME": "精装修",
                "PROTYPE": 1,
                "SOJ": "MAHOUT=0&spread=scanagain_p",
                "IMAGESRC": "http://pic1.ajkimg.com/m/3674657ba9a5f422935ee7e345f82fa2/180x135c.jpg",
                "PROID": 30680383,
                "PROPRICE": "9000"
            },
            {
                "CITYID": "11",
                "TITLE": "房子是我帮房东买进的。。。城隍庙板块。。8号10号。送车 位",
                "LINK": "http://sh.zu.anjuke.com/fangyuan/31341646",
                "COMMNAME": "士林华苑",
                "ROOMNUM": "2",
                "HALLNUM": "2",
                "FITNAME": "豪华装修",
                "PROTYPE": 1,
                "SOJ": "MAHOUT=0&spread=scanagain_p",
                "IMAGESRC": "http://pic1.ajkimg.com/m/900f10c4bfb636992aeee8213edc67eb/180x135c.jpg",
                "PROID": 31341646,
                "PROPRICE": "9500"
            },
            {
                "CITYID": "11",
                "TITLE": "地铁8，10号线，开发商统一装修，全装全配，随时入住",
                "LINK": "http://sh.zu.anjuke.com/fangyuan/31341814",
                "COMMNAME": "复地雅园",
                "ROOMNUM": "2",
                "HALLNUM": "2",
                "FITNAME": "豪华装修",
                "PROTYPE": 1,
                "SOJ": "MAHOUT=0&spread=scanagain_p",
                "IMAGESRC": "http://pic1.ajkimg.com/m/3027e939c741e3fd5e08e03d46ae96dc/180x135c.jpg",
                "PROID": 31341814,
                "PROPRICE": "8500"
            },
            {
                "CITYID": "11",
                "TITLE": "紧邻10号线。。城隍庙板块。。。看房方便，，，有钥匙",
                "LINK": "http://sh.zu.anjuke.com/fangyuan/31440015",
                "COMMNAME": "复地雅园",
                "ROOMNUM": "2",
                "HALLNUM": "2",
                "FITNAME": "普通装修",
                "PROTYPE": 1,
                "SOJ": "MAHOUT=0&spread=scanagain_p",
                "IMAGESRC": "http://pic1.ajkimg.com/m/bbfeed8cfaf108cfb407d468b3d3655e/180x135c.jpg",
                "PROID": 31440015,
                "PROPRICE": "8000"
            }
        ],
        "sojDataMainPush": {
            "v": "1.0",
            "propParam": "",
            "hp": 0
        },
        "sojDataNear": "",
        "sojDataFar": {
            "v": "2.0",
            "propParam": "34448164|1509723|0|1,30680383|1509723|0|1,31341646|1509723|0|1,31341814|1509723|0|1,31440015|1509723|0|1",
            "hp": 1,
            "entry": 61
        }
    },
    "requestTime": 0.16531205177307
}
</pre>

3. 代码位置
	* 推荐接口代码位置
		* haozu-site/app-haozu-web/controller/api/rec/PageRec.php
		* 拉去分支时需要select老好租代码仓库
	* 页面展示代码位置
		* user-site/app-zufang-web/component/zufang/web/rent/recommend/InterestRecommend.js
		* 拉去分支时需要select新框架好租代码仓库

4. 原推荐接口流程图
	
	
	![https://www.processon.com/view/54476c760cf23db8dec85a91](看了又看推荐逻辑流程图.png)
	
5. 后端数据处理方式
	* 在获取推荐房源数据之前增加获取大业主房源
		* 新增getSublessorPropsByUserId($user_id)到Orm_Prop_Prop中，返回该大业主所有房源的PropId
		* 当PropId数量大于6时，调用Module_Prop_PropInfoV2里面get_sublessor_props()获取详细信息
		* 过滤该页面本身的房源信息后，格式化房源信息
		* 在返回的房源数据中增加sublessor_props
		
		
6.前端页面显示处理




