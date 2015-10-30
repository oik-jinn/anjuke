/**
 * Created by ruanxianhuo on 14-11-5.
 */
//批量写入
var mongodb = require('mongodb');
var server = new mongodb.Server('localhost',27017,{auto_reconnect:true});
var db = new mongodb.Db('mobile_db',server,{safe:true});
//connect insert
db.open(function(err,db){
    if(!err) {
        db.collection('test',{safe:true},function(err,collection){
            if (!err) {
                console.log(err);
            }
            var tmp1 = {};
            var j = 0;
            for(var j = 0;j < 100000;j++) {
                tmp1 = {title:'world',num:j};
                collection.insert(tmp1,{save:true},function(err,result){
                    if (!result) {
                        console.log(result);
                    }
                });
            }
        });
    }else{
        console.log(err);
    }
});
