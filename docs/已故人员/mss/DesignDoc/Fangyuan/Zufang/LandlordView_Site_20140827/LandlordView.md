租房个人房源单页改版项目设计
=====

项目背景:
----
* 配合安居客全站的风格统一改版，提升全站个人租房房源用户体验，优化UI与一些细节字段
* 范围:全国.


功能模块:
----
 

### 1. 举报经纪人冒充个人 

* js & html
* ajax接口：http://sh.yongshengwei.zu.dev.anjuke.com/ajax/doreport/

### 2. 发送到手机
* 接口获取文案的修改
* ajax接口地址
	* 获取文案：http://sh.yongshengwei.zu.dev.anjuke.com/ajax/getMsgSentToPhone/
	* 发送请求：http://sh.yongshengwei.zu.dev.anjuke.com/ajax/mobilesend
	* （发送请求参数中：f参数修改为个人）

### 3. 房源信息
* 修改租房房源service，支持获取个人房源

		Biz_Zufang_Rent_RentBiz->getPropertyAndImagesInfo()
		|
		Zufang_Core_Rent_Service_RentService->getRentAllInfo()

### 4. 房源发布者信息
* 发布者信息;
* 发布者的在线房源数量



### 5. 感兴趣房源（右侧）
* ajax获取,调用老的接口:
	* (同租房经纪人单页：url参数值不同(type=1))
    * http://sh.yongshengwei.zu.dev.anjuke.com/api/ajax/recommendpropall/



### 6. 小区附近房源
* 新增加在老框架中接口
* ajax请求接口：http://sh.yongshengwei.zu.dev.anjuke.com/api/rec/getcommlastestprop/?city_id=11&comm_id=185&limit=4&del_prop_id=88889092

        city_id:城市id
        comm_id:小区id
        limit:条数
        del_prop_id:需要过滤掉的房源id


### 7. 其他
* 其他同租房经纪人房源单页一样

服务器部署
----
* 配置以下url走新框架:
    * 租房个人房源单页: http://{城市}.zu.anjuke.com/gfangyuan/{房源ID}
