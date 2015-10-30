## speed用padweb核心页面正则表达式

#### 1. 首页 x (会涵盖所有二手房url)

http://shanghai.anjuke.com/ 

```
^/$
```

#### 2. 房源列表 √

http://shanghai.anjuke.com/sale/

```
^/(sale|sales|list)(/?)$
^/(sale|sales|list)/(.*)$
```

#### 3. 经纪人单页 √

http://shanghai.anjuke.com/pad/prop/view/223693032

```
^/pad/prop/view/([0-9]+)$
```

#### 4. 个人房源单页 √

http://shanghai.anjuke.com/pad/prop/viewyz/223693032

```
^/pad/prop/viewyz/([0-9]+)$
```

#### 5. 地图列表页 √

http://shanghai.anjuke.com/map/sale/

```
^/(map|ditu)/(sale|rent)(/|)$
^/(map2|ditu2)/(sale|rent)/$
^/map3/?$'
```

#### 6. 地图的单页 即 房源单页 不做区分 √

#### 7. 小区列表页 √

http://shanghai.anjuke.com/community/

```
^/community/props/(sale|rent)/([0-9]+)
^/xiaoqu/props/(sale|rent)/([0-9]+)
```

#### 8. 小区单页 √

http://shanghai.anjuke.com/pad/community/view/106

```
^/pad/community/view/([0-9]+)$
```

#### 9. 小区在售房源列表页 √

http://shanghai.anjuke.com/community/props/sale/106

```
^/community/props/(sale|rent)/([0-9]+)
^/xiaoqu/props/(sale|rent)/([0-9]+)
```

#### 10. 我的收藏 √

http://user.anjuke.com/favorite/fangyuan/

```
^/favorite/fangyuan$
^/favorite/fangyuan/(.*)$
```

http://user.anjuke.com/zu/favorite/zufang/

```
^/zu/favorite/zufang
^/favorite/zufang
```

#### 11. 浏览历史 √

http://user.anjuke.com/prop/myviewed/

```
^/prop/myviewed/
^/prop/myviewed/(.*)
```

http://user.anjuke.com/zu/prop/viewed/

```
^/prop/viewed/(.*)?$
^/prop/viewed/$
^/zu/prop/viewed/(.*)?$
^/zu/prop/viewed/$
```

#### 12. 意见反馈 √

http://user.anjuke.com/suggestion/

```
^/suggestion
```

#### 13. 租房列表

http://sh.zu.anjuke.com/

```
^/$
```

http://sh.zu.anjuke.com/fangyuan/ √

```
^/fangyuan/([a-zA-Z0-9]+)/([a-zA-Z0-9\-]+)/?
^/fangyuan/?(.*)/?
```

#### 14. 租房经纪人房源单页 √

http://sh.zu.anjuke.com/fangyuan/29803438

```
^/fangyuan/([0-9]+)$
```

#### 15. 租房二手房经纪人房源单页 √

http://nn.zu.anjuke.com/afangyuan/217566582

```
^/afangyuan/([0-9]+)-([0-9]+)$
^/afangyuan/([0-9]+)$
```

#### 16. 租房个人房源单页 √

http://ly.zu.anjuke.com/gfangyuan/29955439

```
/gfangyuan/([0-9]+)-([0-9]+)$'
/gfangyuan/([0-9]+)$
```

#### 17. 租房地图 √

http://sh.zu.anjuke.com/ditu/

```
^/ditu/*
```

#### 18. 租房地图单页 即 列表单页 不做区分 √

#### 19. 租房小区列表页 x (会涵盖租房小区相关url)

http://sh.zu.anjuke.com/xiaoqu/

```
^/xiaoqu/?(.*)
```

#### 20. 租房小区单页 √

http://sh.zu.anjuke.com/xiaoqu/106?J1405480679607

```
^/xiaoqu/([0-9]+)/?
```

#### 21. 租房小区房源列表 √

http://sh.zu.anjuke.com/xiaoqu/props/106/

```
^/xiaoqu/props/(.*)
```
