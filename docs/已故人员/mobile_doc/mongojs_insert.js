/**
 * Created by ruanxianhuo on 14-11-5.
 */
var mongojs = require('mongojs');
var db = mongojs('mobile_db',['test']);
var data = {title:'test',num:1};

db.test.insert(data, function(err){
    if (err) {
        console.log(err);
    }
});

db.test.find({title:'test'},function(err,docs){
    if (err) {
        console.log(err);
    }
    console.log(docs);
});


db.test.update({title:'test'}, {$set:{num:111}}, {multi:true}, function() {
    // the update is complete
    db.test.find({title:'test'},function(err,docs){
        if (err) {
            console.log(err);
        }
        console.log(docs);
    });

});

db.test.remove({title:'test'},true , function(err){
    if (err) {
        console.log(err);
    }
})

