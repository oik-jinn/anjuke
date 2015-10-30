# 目标

- 读hadoop日志，将每个监控页面的速度、点击量等信息写入数据库
- 各模块、被监控页面可以灵活的维护

# 数据库设计

- 管理员：admin

>

    CREATE TABLE IF NOT EXISTS `admin` (
      `id` int(11) NOT NULL AUTO_INCREMENT,
      `username` varchar(50) NOT NULL COMMENT '用户名',
      PRIMARY KEY (`id`),
      UNIQUE KEY `username` (`username`)
    )

- 日志过滤条件表：log_condition

>

    CREATE TABLE IF NOT EXISTS `log_condition` (
      `id` int(11) NOT NULL AUTO_INCREMENT,
      `field` int(11) DEFAULT NULL COMMENT '过滤的字段下标',
      `condition` varchar(500) DEFAULT NULL COMMENT '条件',
      `operation` int(50) DEFAULT NULL COMMENT '匹配类型',
      PRIMARY KEY (`id`)
    )
匹配类型(暂时支持以下几种)：
<br/>1-startsWith、2-endsWith、3-equals、4-reg、5-contains、6-between、7-ncontains
<br/>例子:
<br/>http_code 在200-400之间的时统计，则filed = 11, condition = 200-400, operation = 6
<br/>hostname 不包含 10.10.3.12:20080，则filed = 8, condition = 10.10.3.12:20080, operation = 7

- 模块表：module

>

    CREATE TABLE IF NOT EXISTS `module` (
      `id` int(11) NOT NULL AUTO_INCREMENT,
      `name` varchar(50) DEFAULT NULL COMMENT '模块名',
      `domain` varchar(50) NOT NULL COMMENT '域名',
      `peak` int(11) NOT NULL DEFAULT '10' COMMENT '高峰期，默认时间为10点',
      `description` varchar(500) DEFAULT NULL COMMENT '模块描述',
      `parent_module_id` int(11) NOT NULL COMMENT '父级模块',
      `enable` int(1) DEFAULT '1' COMMENT '是否监控,0-否;1-是',
      PRIMARY KEY (`id`),
      UNIQUE KEY `domain` (`domain`)
    )

- 模块过滤条件表：module_condition

>

    CREATE TABLE IF NOT EXISTS `module_condition` (
      `id` int(11) NOT NULL AUTO_INCREMENT,
      `module_id` int(11) DEFAULT NULL COMMENT '模块ID',
      `field` int(11) DEFAULT NULL COMMENT '过滤的字段下标',
      `condition` varchar(500) DEFAULT NULL COMMENT '条件',
      `operation` varchar(50) DEFAULT NULL COMMENT '匹配类型',
      PRIMARY KEY (`id`)
    )

- 页面基本信息表：page

>

    CREATE TABLE IF NOT EXISTS `page` (
      `id` int(11) NOT NULL AUTO_INCREMENT,
      `module_id` int(11) DEFAULT NULL COMMENT '模块ID',
      `name` varchar(50) DEFAULT NULL COMMENT '页面别名',
      `peak` int(11) NOT NULL COMMENT '高峰期',
      `description` varchar(500) DEFAULT NULL COMMENT '模块描述',
      `url_example` varchar(50) DEFAULT NULL COMMENT '监控的URL例子',
      `enable` int(1) DEFAULT '1' COMMENT '是否监控,0-否;1-是',
      PRIMARY KEY (`id`)
    )

- 页面过滤条件表：page_condition

>

    CREATE TABLE IF NOT EXISTS `page_condition` (
      `id` int(11) NOT NULL AUTO_INCREMENT,
      `page_id` int(11) DEFAULT NULL COMMENT '页面ID',
      `field` int(11) DEFAULT NULL COMMENT '过滤的字段下标',
      `condition` varchar(500) DEFAULT NULL COMMENT '条件',
      `operation` varchar(50) DEFAULT NULL COMMENT '匹配类型，startsWith、endsWith、equals、reg、contains',
      PRIMARY KEY (`id`)
    )
备注：
<br/>以下字段为必须在新建页面时录入
<br/><table>
    <tr><th>字段</th><th>备注</th><th>是否必须</th></tr>
    <tr>
        <td>reg</td>
        <td>匹配request_uri字段的reg</td>
        <td>必须</td>
    </tr>
    <tr>
        <td>method</td>
        <td>请求方法(all,get,post)，默认为all</td>
        <td>必须</td>
    </tr>
</table>
<br/>正则表达式只能匹配url的?之前，且不能以'/'结尾
<br/>例子：
<br/>url：/fangyuan/28003768/?hfilter=filterlist&from=Filter_1
<br/>reg：/fangyuan/[0-9]+$

- 速度表：speed

>

    CREATE TABLE IF NOT EXISTS `hour_speed` (
      `id` int(11) NOT NULL AUTO_INCREMENT,
      `page_id` int(11) DEFAULT NULL COMMENT '页面ID',
      `date` timestamp NULL DEFAULT NULL COMMENT '时间',
      `speed80` int(11) DEFAULT '0' COMMENT '速度/80',
      `speed85` int(11) DEFAULT '0' COMMENT '速度/85',
      `speed90` int(11) DEFAULT '0' COMMENT '速度/90',
      `speed95` int(11) DEFAULT '0' COMMENT '速度/95',
      `count` int(11) DEFAULT '0' COMMENT '点击量',
      `size` int(11) DEFAULT '0' COMMENT '页面大小',
      PRIMARY KEY (`id`)
    )

<br/><table>
    <tr><th>日志</th><th>job运行时间</th></tr>
    <tr>
        <td>昨天</td>
        <td>昨天整点</td>
    </tr>
</table>

# 功能设计

- 后台速度读
 - 解析每条记录line
 - 过滤line，过滤条件在log_condition维护
 - 模块匹配
<br /> 根据domain、ua等字段与module匹配，得到module_id
 - 监控页面匹配
<br /> 根据module_id得到该模块的页面的正则表达式regs，request_uri依次与regs匹配，得到page_id
 - 计算speed、size
 - 将（key=page_id,value=speed size）写入map
- 后台速度写
 - 计算百分之[80、85、90、95]的speed、点击数、size，并写入db

- 数据展示（php）
 - 基本信息：
<br />pagename、url、域名、时间、昨日速度、当日速度、上周平均速度、上月平均速度、上周同比增长、上月同比增长、点击量、详情
 - 详情：
<br />当天平均速度、点击量
<br />24小时整点速度
<br />近期高峰时段速度
<br />趋势（图）
- 各模块、监控页面维护（php）
 - 模块的增删改查
 - 页面的增删改查
