<?php
if(!defined('APP_ENV')){
    define('APP_ENV','pg');
}
$config['charset'] = 'utf-8';
$config['minify_html'] = false;
$config['minify_js'] = false;

$config['base_domain'] = SUB_NAME.".zhangjianliu.zu.dev.anjuke.com";
$config['cookie_domain'] = SUB_NAME.".zu.dev.anjuke.com";
$config['cookie_time']   = 86400;
$config['cookie_path'] = '/';
$config['display_error'] = false;
$config['member_base_domain']='member.fp100.dev.anjuke.com';
$config['AuthCookieName'] = "aQQ_haozuauthinfos";
$config['AnjukeSecques']  = "Xi7@Sz";
$config['LastUserCookieName'] = "aQQ_haozulastuser";
$config['UsernameCookieName'] = "aQQ_haozuusername";
$config['guanCookieName'] = 'aQQ_Guanauthinfos';

$config['debug_allow_patterns'] = array(
    '/^127\.0\.0\./',
    '/^192\.168\.190\.*/',
);


 $config['error_handler'] = "apf_error_handler";
 $config['exception_handler'] = "apf_exception_handler";

// property images
$config['size_thumbnail']['width'] = 100;
$config['size_thumbnail']['height'] = 75;
$config['size_larger']['width'] = 420;
$config['size_larger']['height'] = 315;

$config['image_server_domain'] = "images";
$config['image_server_base_domain'] = "qa.haozustatic.com";

$config['host_id'] = 1;
$config['upload_image_domain'] = ".zu.anjuke.com";
$config['display_image_domain'] = ".zu.anjuke.com";

$config['daoPropViewClass'] = 'DAO_Prop_PropertyMemcache';
$config['daoAreaClass'] = 'DAO_Area_AreaMemcache';
$config['daoCommunityMemcacheClass'] = 'DAO_Community_CommunityMemcache';

$config['getCommunityUrl'] = 'http://www.anjuke.com';
$config['propDueto'] = 15;
$config['hq_showimgtime'] = 0;

$config['mapsort'] = array(
    '1'=>array('价格升序','pricenum asc','price asc'),
    '2'=>array('价格降序','pricenum desc','price desc'),
    '3'=>array('发布时间升序','updated asc','updated asc'),
    '4'=>array('发布时间降序','updated desc','updated desc')
);

$config['image_size'] = array(
    "50x37"    	=>  array('width'=> 50 , 'height'=>37  , 'mark' => 0,'fit'=>true, 'chop' => true),
    '80x80'   	=> array('width'=>80,  'height'=>80),
    "100x75"    =>  array('width'=> 100 , 'height'=>75  , 'mark' => 0,'fit'=>true),
    "100x75c"   =>  array('width'=> 100 , 'height'=>75  , 'mark' => 0,'fit'=>true),
    "136x102"	=>  array('width'=> 200 , 'height'=>150 , 'mark' => 0,'fit'=>true),
    '122x90'	=>  array('width'=> 122 ,  'height'=>90,   'mark' => 0,'fit'=>true),
    '122x90c'	=>  array('width'=>122,	  'height'=>90,   'mark' => 0,'fit'=>true),
    "200x200c"  =>  array('width'=> 200 , 'height'=>200 , 'mark' => 0,'fit'=>true , 'chop' => true),
    "112x112c"	=>	array('width'=>	112 , 'height'=>112 , 'mark' => 0,'fit'=>true , 'chop' => true),
    '240x180'   =>  array('width'=> 240 , 'height'=>180 , 'mark' =>0, 'fit'=>true),
    "220x170"   =>  array('width'=> 220 , 'height'=>170 , 'mark' => 0,'fit'=>true),
    "220x170c"  =>  array('width'=> 220 , 'height'=>170 , 'mark' => 0,'fit'=>true),
    "200x200"   =>  array('width'=> 200 , 'height'=>200 , 'mark' => 0,'fit'=>true),
    "420x315"   =>  array('width'=> 420 , 'height'=>315 , 'mark' => 1,'fit'=>true),
    "335x300"   =>  array('width'=> 335 , 'height'=>300 , 'mark' => 1,'fit'=>true),
    "420x420"   =>  array('width'=> 420 , 'height'=>420 , 'mark' => 1,'fit'=>true),
    "600x65"    =>  array('width'=> 600 , 'height'=>65 , 'mark' => 1,'fit'=>true),
    "600x600"   =>  array('width'=> 600 , 'height'=>600 , 'mark' => 1,'fit'=>true),
    '960x60'    =>  array('width'=> 960 , 'height'=>60 , 'mark' =>0, 'fit'=>true),
    '970x80'    =>  array('width'=> 970 , 'height'=>80 , 'mark' =>0, 'fit'=>true),
    '223x100'   =>  array('width'=> 223 , 'height'=>100 , 'mark' =>0, 'fit'=>true),
    '1280x1500'   =>  array('width'=> 1280 , 'height'=>1500 , 'mark' =>0, 'fit'=>true),
    '474x315' 	=> array('width'=>474, 'height'=>315, 'mark'=>1,'fit'=>true),
    '150x113'   => array('width'=>150, 'height'=>113,'mark'=>0,'fit'=>true),
    "1440x1440"   =>  array('width'=> 1440 , 'height'=>1440 , 'mark' => 1,'fit'=>true),
    "o"         =>  array("original" => true)
);

$config['save_curltime_path']   = "/home/www/tmp/curltime.txt";
$config['enable_save_curltime'] = false;
$config['FontsUrl']  = "/usr/share/fonts/Essai.ttf";
$config['zh_font_path'] = "/usr/share/fonts/STXIHEI.TTF";
$config['temp_save_blackpath']   = "/home/www/tmp/black.txt";
$config['varnish_ip']   = "10.10.3.366";
$config['varnish_port'] = "2000";

$config['b8_word_data'] = "/home/ch98/projects/sites/data";
$config['global_city_id'] = 10;

// anjuke显示图片
$config['pic_server_domain'] = "pic";
$config['pic_server_base_domain'] = "ajkimg.com";


//文件记录路径
//$config['file_log_options'] = array('file'=>'/data1/logs/test.log');
$config['file_log_options'] = array('file'=>'/home/www/logs/house/prop.log');

$config['broker_assign_mid'] = '/home/www/jobs/broker/assign_mid.txt';

// smtp
$config['SmtpServer']  = "smtp.corpease.net";
$config['SmtpServerPort']  = 25;
$config['SmtpUser']  = "noreply@dm.anjuke.com";
$config['SmtpPass']  = "noreply0805";

$config['IsOnline']  = true;

$config['list_cache'] = array(
    'cache_kill'	=> false,
    'cahce_time'	=> 600
);

$config['broker_bbs_base_domain'] = 'forum.haozu.com';
//手机好租
$config['haozu_mobile_url'] = 'http://www.haozu.com/app/haozu/android.html';
//获取头部导航接口
$config['header_nav_api'] = 'http://shanghai.pmt15111.anjuke.test/api/nav/?cityId=';
?>
