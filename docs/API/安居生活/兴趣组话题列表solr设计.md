# 兴趣组话题列表排序优化

------

# 背景：
目前兴趣组内的话题排序是按话题的创建时间排序，产品需要修改为按话题的最新回复时间排序,可以理解为热门的话题向上顶。经过思考和讨论以现在的数据库设计很难单从数据查询去达到排序需求，通过solr去实现优化排序的目的是最佳选择。

# 可行性分析：
不用solr前的，两种方案存在不可行性：

1. 通过在兴趣组中查询所有topic id，然后从reply（回复） 表中查询这些topic id对应的回复的最新更新时间，然后通过更新时间进行排序。
不可行性分析：
> * 分页问题不能很好处理
> * 处理逻辑相对复杂

2. 在topic info表中新建一个与回复相关的时间更新字段，每次有新回复时，更新该字段。
不可性分析：
> * 访问量大时，存在频繁写表情况
> * 不利于后期topic可能存在的模糊查询的优化


# Solr实施方案

`1. solr存储结构`：

| Field		| Type	| Indexed	| Stored	| Required	| Description |
| --------- | ----- | --------- | --------- | --------- | ----------- |
| topic_id	| int	| Y			| Y			| Y			| 话题id	  |
| user_id	| int	| Y			| Y			| Y			| 话题作者id  |
| group_id	| int	| Y			| Y			| Y			| 话题兴趣组id|
| status    | int	| Y		    | Y			| N			| 话题状态	  |
| updated_time | int| Y		    | Y			| N			| 最新更新时间|



`2. solr方法`（在现有的group service 中的solr servive 新增话题列表select,update和delete方法）

> * 查询兴趣组话题列表

入参：array  : 查询所需字段
```
public function searchGroupTopicListSolr($search_field)

```
出参：return array(
            'total'     => $total,
            'has_more'  => $has_more,
            'list'      => $docs,
        );
        
>* 删除话题（正常删除，违规删除）

入参：array: 需要删除的话题id
```
public function deleteGroupTopicListSolr($topic_ids)
```
出参：删除是否成功状态:true or false


> * 新增话题,新增回复

入参： array :topic_id, group_id,update_time,user_id等
```
public function updateGroupTopicListSolr($data)
```
出参：更新是否成功状态:true or false
