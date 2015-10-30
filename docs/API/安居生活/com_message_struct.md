### 安居生活后台消息推送的格式(app的接口)
    {
        message: 消息的提示内容,比如你有一条新消息
        msg_type: 1  代表通知类消息,
        msg_content:{
            biz_type:业务类型 3 公共通知
            sub_biz_type:  1、启动应用 2、跳转邻里小组首页 3、跳转上门首页 4、跳转html页面 5、跳转兴趣组首页
            is_notify:bool,true是有新消息通知,false表示具体的消息
            msg_id:消息的id
            other_content:{
                create_time:创建时间,时间戳形式
                action_type: 1、跳转小区广场首页  2、跳转闲置首页
		        url:'' 跳转html页面
		        content:''
            }
            
        }
    }

