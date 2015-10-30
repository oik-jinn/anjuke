var mongodb = require('mongodb');
var server = new mongodb.Server('localhost',27017,{auto_reconnect:true});
var db = new mongodb.Db('mobile_db',server,{safe:true});
//connect find
db.open(function(err,db){
    if(!err) {
        db.collection('test',{safe:true},function(err,collection){
            collection.find({title:'hello'}).toArray(function(err,docs){
                console.log('find');
                console.log(docs);
            });
        });
    }else{
        console.log(err);
    }
});