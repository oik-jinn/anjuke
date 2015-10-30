#### member测试要注意的相关地方

member的PG测试和新老框架下的测试不太一样，需要先去deploy里面创建新版本分支，然后自己点击更新才可以在pg上测试,member的测试接口地址类似为
http://member.anjuke.test/memberapi/m/?act=register_mobile&userMobile=13508699420&userPwd=123&detail=1。后面的参数根据测试函数的不同而不同