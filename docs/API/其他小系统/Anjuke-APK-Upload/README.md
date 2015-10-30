## Anjuke APK Upload

### 描述
```
该系统的主要功能是：
	1、上传apk包
	2、增加版本信息（app检测新版本）
	3、版本提醒设置
	4、上传ios补丁包
```

### 访问地址
* 线上：http://app.a.ajkdns.com/apk-upload/upload.php

### 环境部署
* 线上
    ```
    app10-127:/home/www/v2/apk-upload01/src
    ```

### TODO
```
1、使用git管理代码
2、发布走deploy流程
```

### 修改记录
* 20150825——添加anlife的apk上传包功能

    ```
    1、上传新增应用名称：
    	文件：views/upload.phtml
    	修改：<option value="8">安居生活</option>

    2、新增应用名称配置
    	文件：/home/www/v2/apk-upload01/config.php
    	修改：$config['app'] = array (
              "1" => "Anjuke",
              "2" => "Broker",
              "3" => "Aifang",
              "4" => "Haozu",
              "5" => "HaozuLandlord",
              "6" => "Xinfang",
              "7" => "Jinpu",
              "8" => "Anlife”,//新增安居生活
          );
    ```