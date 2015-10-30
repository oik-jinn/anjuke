#添加／取消 收藏 详细设计

##实现了哪些功能

* `功能实现`
    *  未登陆添加收藏
    *  未登陆取消收藏
    *  登陆添加收藏
    *  登陆添加收藏

* 功能逻辑
    *   未登陆状态：
        **  添加/取消 收藏　ADD/DEL 至COOKIE 
    *   登陆状态：
        **  添加／取消　收藏　ADD / DEL 至用户中心
        **  当房源大于20亿时　ADD/DEL 至COOKIE
* 使用示例
    * 说明  登陆和未登陆 调用地址一样
    * add   http://m.jeyzhu.dev.anjuke.com/tw/favorite?type=add&guid=B94B10F3-6C1C-6969-B5B3-D17E32B66E7B&fid=209450409
    * del   http://m.jeyzhu.dev.anjuke.com/tw/favorite?type=del&guid=B94B10F3-6C1C-6969-B5B3-D17E32B66E7B&fid=209450409

## 添加/取消 收藏 

###调用方式
* `url = '/tw/favorites／';`

**参数名** | **类型** | 是否必须 | 说明
--- | --- | --- | ---
type | string | 是 | {add/del 添加/取消 收藏}
guid | string | 否 | {guid 未登陆必传 已登陆无需传}
fid | int | 是 | {房源ID }
###返回格式 json
#### {"status":"ok"} {"status":"failed"}

##PHP 内部实现
```php
<?php
class User_Touch_Public_FavoriteAjaxController extends User_Touch_AbstractController {
   
    public function handleRequestInner () {
        //根据 type 参数来 控制执行的方法
        User_Common_Favorite_Favorite :: get_instance -> favorite_route ($type);
    }    
    
}
?>

<?php
/*
* add/del 收藏 操作类
*/
class User_Common_Favorite_Favorite {
    
    /*
    * 收藏 route
    * type: add(添加) / del(取消)
    */
    public function favorite_route ($type) {
        if ($this -> is_login () == 1) {
            //已登陆
            switch ($type) {
                case 'add':
                    return $this -> add_favorite ();
                break;
                case 'del':
                    return $this -> del_favorite ();
                break;
                default:
                    return false;
                break;
            }
        } else {
            //未登陆
            switch ($type) {
                case 'add':
                    return $this -> add_no_login_favorite ();
                break;
                case 'del':
                    return $this -> del_no_login_favorite ();
                break;
                default:
                    return false;
                break;
            }
        }
    }
    
    /*
    * 添加未登陆收藏
    */
    protected function add_no_login_favorite () {
    }
    
    /*
    * 已登陆 添加收藏
    */
    protected function add_favorite () {
    }
    
    /*
    * 取消未登陆收藏
    */
    protected function del_no_login_favorite () {
    }
    
    /*
    * 已登陆 取消收藏
    */
    protected function del_favorite () {
    }
    
    /*
    * 是否登陆
    */
    private function is_login () {
        
    }
}
?>



```



