###siteMap分表-搜索词找房-概要设计
###Route 配置
```php
修改
//sitemap-总表
$config['mappings'][$prefix.'Seo_Sitemap'] = array(
    '^/[a-z]+/sitemap.*'     
);
为
$config['mappings'][$prefix.'Seo_Sitemap'] = array(
    '^/[a-z]+/sitemap/?$'     
);
新增 sitemap-分词
$config['mappings'][$prefix.'Seo_SitemapSearchTerms'] = array(
    '^/[a-z]+/sitemap/(esf|zf)/fang-([a-zA-Z])-[pP]([\d]+)$'     
);
```
###页面URL
	1. domain/city/sitemap/esf(zf)/fang-{大写字母}-{页数 (P1)}
###页面TITLE
	title  --   {city}二手房信息，按字母｛A｝查找，第｛数字｝页-{city}安居客
###面包屑
	1. {城市}安居客 > {城市}二手房/租房 > 字母
	2. 点击城市 进入筛选项不限的 二手/租房 房源列表页
	3. 字母不可点击
###标题
	1.二手房/租房
###首字母
####首字母需求
	1.一次性加载，默认收起，可展开/收起。
	2.click字母 显示 高亮首字母开头的搜索词列表
####首字母实现方案
	1.调用 Ershou_Core_Seo_Service_SitemapService -> getInitialNav 方法 获取首字母
###区域名&搜索词
####区域名&搜索词 需求
	1.显示区域名
	2.在对应区域下显示对应区域的搜索词
	3.100个搜索词分页
####区域名&搜索词 实现方案
	1.调用 Ershou_Core_Seo_Service_SitemapService -> getFanglistByInitial 方法 获取 区域&搜索词
###分类
	1.命中二手房或租房
	2.字母表可展开/收起
###底部
	1.通用底部
###增加pagename
	pub_seo_sitemapSearchTerms
	