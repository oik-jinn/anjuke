## *public api docs*

------------------------------------------------------------------------------


+ ## 设计初衷 ##
    
    - ##### 提高对外api的安全性，加入身份认证 #####

        公钥、请求时间戳、认证ID、请求业务参数 组合生成token

    - ##### 完善API监控，加强API的稳定性 #####

        请求次数、ip、请求时间加入knowing

-------------------

+ ## *API设计流程图* ##

    [mou打开一次后显示不了图片了，愤怒+郁闷，请猛点，自己查看图片](http://gitlab.corp.anjuke.com/_site/docs/blob/master/DesignDoc/Public/Api/img/ApiDesign.001.jpg)

-------------------

+ ## API Configure ##
    
    目前配置文件API使用的配置文件只有一个，user-site/app-public-api/config/api/apiconf.php

---


+ ## API Url
    
    - ##### 完整URL #####

        http://api.anjuke.com/site/[大类业务]/[子类业务]/[version]/?[query_string]，比如http://api.anjuke.com/site/seo//internalLinks/1.0/?authId=100001&requestTime=1111111
    
    ---
    
    - ##### 拦截器参数校验 #####

        * ***authId***

            authId需要使用api的一方申请，这边会以一个key-value形式存储在配置user-site/app-public-api/config/api/apiconf.php中，并将这个key-value键值对一并给使用API的第三方。

        * ***requestTime***

            第三方发起请求的时间戳
    
    ---
    
    - **业务参数**

        每个API对应自己的参数，除authId与requestTime参数
        
---

+ #### api使用方在Header加入token
    
    - ***publickKey***

        给第三方的公钥，58Ajk2015HoppingYear(待商议)存储在user-site/app-public-api/config/api/apiconf.php里，目前只有一个
    
    - ***有序业务参数strParams***

        按照业务的参数的自然升序对业务参数进行排序，保留业务参数键值对,然后一次将所有的value累加成一个字符串strParams
    
    - ***authValue***

        根据传递的申请authId读取配置中的authValue,只有这个authValue参于token生成机制，authId不参与token生成，这个authValue是一并给到接口使用方的
    
    - ***requestTime***

        api必传参数requestTime时间戳
    
    - ***生成token***

        token = md5(publicKey+authValue+requestTime+strParams)，比如一次请求地址如下：http://api.anjuke.com/site/seo/internalLinks/1.0/?authId=10000001&requestTime=12345678901&p3=value3&p1=value1&p2=value2,则生成的token为：

        ***token = md5(58Ajk2015HoppingYearauth_value_1000000112345678901value1value2value3)***
    
    - ***API使用方***

        将token写入header,连同请求发送过来
    
---
    
+ #### 身份认证
    
    >并不是所有的api都一定需要身份认证，如果不需要身份认证，可以在配置文件里配置相应的白名单路由，这些路由对应的controller将会跳过身份认证这一部，直接进入到业务层参数检测，但这样会导致该API缺少对应的请求量监控，因为所有的监控是在拦截器层做的！
    
    - ***拦截器身份认证***

        身份认证失败即推出此次请求并返回错误详情。
    
    - ***具体业务参数校验***

        身份认证成功后，继续各个业务层的参数校验，失败则根据自己的业务投递相关信息，这个目前没有做强制处理。
    
---

+ ## 如何禁止验证

    >1. 在白名单中配置你的路由,只支持精确url匹配，不支持正则匹配，主要是防止误操作；
    >2. 你也可以不配置白名单，根据V2框架配置你自己独立的反向拦截器，禁掉验证的全局拦截器Public_Api_ApiAuth

---

+ 测试Demo
    
    1. 58小区单页内链请求【模拟】

        [缺少参数](http://www.anjuke.com/seo/extapi/public/internallinks/v1.0/link/get/)
        [正常参数](http://www.anjuke.com/seo/extapi/public/internallinks/v1.0/link/get/?city_id=11)
        [正常请求](http://www.anjuke.com/seo/extapi/public/internallinks/v1.0/link/get/?city_id=11&api_type=default)

    2. 58租房房源单页内链请求【模拟】

        [缺少参数](http://www.anjuke.com/seo/extapi/public/internallinks/v1.0/link/get/?api_type=zfdy)
        [正常请求](http://www.anjuke.com/seo/extapi/public/internallinks/v1.0/link/get/?city_id=11&api_type=zfdy)

---

+ ## *后期可优化功能点*

    + ***压力测试***
    + ***IP访问量限制***
    + ***传输中途被第三方添加非业务参数过滤***
    + ***重复请求限制***
    + ***不同的版本需要维护多套controller***

---

+ ## *配置文件局限性*

    1. 不需要参与验证的API数量过多
    2. 不同业务对应的authId=>authValue量过大


