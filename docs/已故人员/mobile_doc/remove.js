/**
 * Created by ruanxianhuo on 14-11-5.
 */
var mongodb = require('mongodb');
var server = new mongodb.Server('localhost',27017,{auto_reconnect:true});
var db = new mongodb.Db('mobile_db',server,{safe:true});
//connect remove
db.open(function(err,db){
    if(!err) {
        db.collection('test',{safe:true},function(err,collection){
            collection.remove({title:'hello'},function(err,docs){
                console.log('remove');
                console.log(docs);
            });
        });
    }else{
        console.log(err);
    }
});
