以下为文档中提及的字段

### 发布帖子对应字段

58表字段 | 必选 | 描述 | 限制 | 安居客字段 | check
--- | --- | --- | --- | --- | ---
cate_id | 必填 | 发布类别 | 需要租房相关类别值 | 单间10,合租8 | 
local_id | 必填 | 发布地区 | 需要租房相关地区值 | | <span style="color:red">需要mapping</span> 
title | 必填 | 帖子标题 | 8~30个字，不允许填写联系方式和特殊符号 | title | 
content | 必填 | 帖子内容 | 不能为空，存在字数限制，未详细调查 | descrip | 
phone | 必填 | 联系电话 | 电话格式 | mobile | 
email | 非必填 | 电子邮箱 | 电子邮箱格式 | 无 | <span style="color:red">需要测试</span>
im | 非必填 | QQ 或 msn | QQ和msn格式 | 无 | <span style="color:red">需要测试</span>
pic | 非必填 | 图片 | 未知 | rent_db.prop_images | 图片提供方式待确认
paras | 必填 | 单元参数 | 见租房字段表 | 见租房字段表 | 
time_sign | 必填 | | | 
client_id | 必填 | | | 
client_select | 必填 | | | 
58user_id | 必填 | | | 
access_token | 必填 | | | 


### 见租房字段表

58字段 | 必选 | 描述 | 限制 | 安居客字段 | check
--- | --- | --- | --- | --- | ---
MinPrice | 必填 | 租金 | 只能为整数，最多5位 | pricenum |
huxingshi | 必填 | 户型室 | 只能为1~9的整数 | roomnum |
huxingting | 必填 | 户型厅 | 只能为1~9的整数 | hallnum |
huxingwei | 必填 | 户型卫 | 只能为1~9的整数 | toiletnum |
jushishuru | 必填 | 居室输入 | 只能为1~9的整数 | roomnum |
area | 必填 | 面积 | 只能为1~9999的整数 | areanum |
xiaoqu | 必填 | 房源所在小区 | 1~30个字 | commname | <span style="color">需要值mapping</span>
goblianxiren | 必填 | 联系人姓名 | 填写联系人姓名2~6个字 | truename |
Floor | 必填 | 房屋楼层 | 只能为-9~-1或者1~99的整数 | floor |
zonglouceng | 必填 | 房屋总楼层 | 只能为1~99的整数 | totalfloor |
dizhi | 非必填 | 房屋地址 | 2~25个字 | address |
ObjectType | 非必填 | 房屋类型 | 只能为2~7的整数 | protype | <span style="color">需要值mapping</span>
FitType | 非必填 | 装修状况 | 只能为1~5的数字 | fitment | <span style="color">需要值mapping</span>
Toward | 非必填 | 房屋朝向 | 只能为1~6或者9~12的整数 | toward | <span style="color">需要值mapping</span>
HouseAllocation | 非必填 | 房屋配置 | 在6、8~14、17中选择 | deploy | <span style="color">需要值mapping</span>
fukuanfangshi | 非必填 | 付款方式 | 只能为0~8的整数 | paymoneynum | <span style="color">需要值mapping</span>
titleeditflag | 非必填 | 标题是否被编辑 | 只能为0或者1，0代表自动生成，1代表手动编辑 | 无 | 固定为0
picdesc1~ picdesc24 | 非必填 | 图片描述 | 分别对应第1张~第24图片的描述 | 无 | 不填
pictag | 非必填 | 图片标题描述 | 出租单间、客厅、厨房、卫生间、户型图、小区外景中选择 | 无 | 不填
bianhao | 非必填 | 房源编号 | 只能填写15位以内的数字或字母 | 无 | 不填
yijuhua | 非必填 | 一句话广告 | 最多35个字，不能填写超过5位的数字 | 无 | 不填


### 以下为文档中没提及，但是网页表单中存在的字段，这些字段可能不对应真正的表字段名，但是是必填项


58表字段 | 必选 | 描述 | 限制 | 安居客字段 | check
--- | --- | --- | --- | --- | ---
HireType | 必填 | 出租方式 | 0单间出租,1床位出租,2整套出租 | renttype | 对应58字段cate_id
isBiz | 必填 | 身份 | 0个人,1经纪人 | 无 | 接口默认为1
ruzhushijian | 必填 | 最早入住时间 | 日期格式 | 无 | <span style="color">校验是否必填</span>



### 未使用的安居客字段，部分字段解释需要再向经纪人端确认

字段 | 类型 | 说明
--- | --- | ---
all_days | int |
areaid | int | 小区id
blockid | int | 经纪人id
cityid | int | 城市id
commid | int | 小区id
commission_id | int | 
contentBasic | string |
created | int | 创建时间
descript | string |
endtime | int |
equipment | string | 设施
from | string |
g_pricenum_1 | int |
g_pricenum_3 | int |
g_pricenum_6 | int |
g_prop_num | int |
is_commission | int |
ispkg | int |
lastPlanTime | int |
newcommid | int |
ontime | int |
plan_time | int |
portid | int |
proId | int | 房源id
publishfrom | string |
quality | boolen |
sharesex | int | 合租性别
sharetype | int | 合租类型
spread_type | int | 推广类型
status | int |
title | int | 标题
type | int |
updated | int |
userid | int | 经纪人用户id

