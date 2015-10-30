## 供应商渠道管理系统

### 代码仓库
* [git@gitlab.corp.anjuke.com:_mobile-api/supplierchannel.git](git@gitlab.corp.anjuke.com:_mobile-api/supplierchannel.git)

### 访问地址
* [http://supplier.anjuke.com/login](http://supplier.anjuke.com/login)

### 部署服务器
* app10-127

### DB说明

* 供应商&管理员帐户表`mobile_api_db.supplier_channel_accounts`

    ```
    密码md5加密
    
    根据要查询的渠道名称 查询渠道的id
    SELECT id FROM supplier_channel_accounts where `supplier`='电信推送' ORDER BY `id` LIMIT 0,100
    ```
    
* 渠道包表`mobile_api_db.supplier_channel_channels`

    ```
    根据渠道id查询渠道相关信息
    SELECT * FROM supplier_channel_channels where `supplier_id`=21 ORDER BY `id` LIMIT 0,100
    ```
    
* 渠道效果数据表（DW上传）`ajk_dw_stats.da_mobile_channel_effect_upload`

    ```
    根据app名称和渠道id查询渠道数据
    SELECT * FROM da_mobile_channel_effect_upload 
	 where 
	 pm in ('b324', 'b336')  
	 and 
	 `app_name` = 'ajk' 
	 ORDER BY `id` desc LIMIT 0,100
	 
	 激活数量：
		安卓 fud * 0.75 + 0.5
		ios fud * 0.9 + 0.5
    ```
 
### 秘密
* 关于账号

    ```
    管理员admin/shifei
    其他：自己md5解密查询
    ``` 
 