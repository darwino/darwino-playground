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
var unid = doc.getUnid();
var url = "$darwino-jstore/databases/"+database+"/stores/"+store+"/documents/"+unid

$.ajax({
  url: url,
  method: "DELETE",
  dataType: "text"
}).then(function(data) {
  var s = "// Document deleted!";
  darwino.Utils.setText("content","{0}",s);
}, function error(err) {
  darwino.Utils.setText("content","{0}",darwino.Utils.toJson(err,false));
  doc.deleteDocument()
});  
