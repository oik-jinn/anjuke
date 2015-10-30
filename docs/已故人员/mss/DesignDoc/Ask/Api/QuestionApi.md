# 作用

- 问答API

# 接口说明

## 添加问题

- 参数

>
    action = add（必填）
    user_id（必填，用户Id）
    title（必填，问题标题，最大长度为50）
    content（选填，问题描述）
    exprt_type（选填，指定哪类专家回答，1-法律专家,2-贷款专家,3-装修专家,4-风水专家,5-房产专家）
    from_module(暂时只支持default,mobile两种来源，不填的话，值默认为default)

- 返回结果

>
    status：true
    result：{
        xxx
    }

 - 返回结果说明
 
>
    status 为 true ，表示添加成功
    status 为false ，表示添加失败，失败的原因会在 result 中提示
    错误原因分为以下几种
        参数错误
        没有此操作
        标题超过允许的最大长度了
        标题过滤无意义词后为空！
        描述超过允许的最大长度了
        当前时间段不允许提问
        不允许重复提交问题，请稍后再试！
        短时间内你发布问题过于频繁，请稍后再试！
        非一般用户不允许提问
        用户经验值不够不允许提问
        用户被禁言
        内容含非法词汇禁止提问
        当前ip被禁用！
        新年啦，问答专家正与家人团聚，你也快回家拥抱家人吧！2月7日，为您服务。
        
- 调用

>
    http://shanghai.pmt-25016-site.anjuke.test/ajax/question/?action=add&user_id=3572667&title=%E5%97%A8&content=%E9%97%AE%E9%A2%98%E6%8F%8F%E8%BF%B0&exprt_type=3&from_module=mobile


## 问题列表

- 参数

>
    action = list（必填）
    user_id（必填，用户Id）
    page_num（选填，默认、参数拼写错误均为1）
    page_size（选填，默认、参数拼写错误为20）

- 返回结果

>
    status：true
    result：[
        {
            QID
            TITLE
            DESCRIPTION
            ANSWERNUM
       }
    ]

- 调用及其他参数说明

>
    http://shanghai.pmt-25016-site.anjuke.test/ajax/question/?action=list&user_id=3572667
    
## 问题单页

- 参数

>
    action = view（必填）
    question_id（必填，问题Id）
    page_num（选填，默认、参数拼写错误均为1）
    page_size（选填，默认、参数拼写错误为20）

- 返回结果

>
    status：true
    result：{
        QID
        TITLE
        QUETIME
        ANSWERNUM
        ANSWERS:[
           {
                CONTENT
                ANSWERTIME
           }
        ]          
    }


- 调用及其他参数说明

>
    http://shanghai.pmt-25016-site.anjuke.test/ajax/question/?action=view&question_id=1571430
    
## 查询用户信息

- 参数

>
    action = member（必填）
    user_ids（必填，用户Id，多个用户用 ‘,’ 分割）

- 返回结果

>
    status：true
    result：{
        USER_ID
        BROKER_ID
        LEVEL_NAME
    }


- 调用及其他参数说明

>
    http://shanghai.pmt-25016-site.anjuke.test/ajax/question/?action=member&user_ids=17960,1334,15860

- 备注

    此接口暂时只返回了用户的称号，二期会进行扩展，比如返回用户头像等等
    
## 已解决问题数量

- 参数

>
    action = qcn（必填）

- 返回结果

>
    status：true
    result：3131885


- 调用及其他参数说明

>
    http://shanghai.pmt-25016-site.anjuke.test/ajax/question/?action=qcn
    
- 备注

    产品定义：已解决问题即为所有问题列表中最大的ID
