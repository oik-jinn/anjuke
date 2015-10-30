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
            var tmp = {title:'hello',num:1};
            var tmp1 = {title:'world',num:2};
            collection.insert(tmp,{save:true},function(err,result){
                console.log(result);
            });
            collection.insert(tmp1,{save:true},function(err,result){
                console.log(result);
            });
        });
    }else{
        console.log(err);
    }
});
