// This snippet shows how to access a document using a REST service
// Only the JSON content part is read from the server

var database = "pinball"
var store = "pinball"
var unid = "1000";


// List the attachments using the direct REST service
var url = "$darwino-jstore/databases/"+database+"/stores/"+store+"/documents/"+unid+"/attachments/";
$.ajax({
  url: url,
}).then(function(data) {
  var s = "//\n// Attachmenta\n//\n"+darwino.Utils.toJson(data,false)+"\n";
  darwino.Utils.setText("content","{0}",s);
}, function error(err) {
  darwino.Utils.setText("content","{0}",darwino.Utils.toJson(err,false));
});  
