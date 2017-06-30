session.setAsync(false);

// This snippet shows how to access a document using a REST service
// Only the JSON content is sent to the server, no meta-data
var database = "playground"
var store = "_default"

// Create a document
var doc = session.getDatabase(database).getStore(store).newDocument(); // Id is generated automatically
doc.setJson({v1:'Value1'});
doc.save();

// Content only
var json = {
  f1:	'field value 1',
  f2:	'field value 2'
}
var unid = doc.getUnid();
var url = "$darwino-jstore/databases/"+database+"/stores/"+store+"/documents/"+unid+"/json"

$.ajax({
  url: url,
  method: "PUT",
  dataType: 'json',
  data: JSON.stringify(json)
}).then(function(data,text,req) {
  var s = "//\n// Returned result\n//\n"
  			+"UNID="+req.getResponseHeader("x-dwo-jstore-unid")+"\n"
  			+"ID="+req.getResponseHeader("x-dwo-jstore-id")+"\n"
  			+darwino.Utils.toJson(data,false)+"\n";
  darwino.Utils.setText("content","{0}",s);
  
  // Delete the doc from the db...
  doc.deleteDocument()
}, function error(err) {
  darwino.Utils.setText("content","{0}",darwino.Utils.toJson(err,false));
});  
