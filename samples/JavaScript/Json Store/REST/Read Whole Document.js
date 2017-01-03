// This snippet shows how to access a document using a REST service
// Whole document, including the meta-data and attachments, is read.

var database = "pinball"
var store = "pinball"
var unid = "1000"
var url = "$darwino-jstore/databases/"+database+"/stores/"+store+"/documents/"+unid;

// Whole document, including the JSON content, the meta-data and the attachments
$.ajax({
  url: url
}).then(function(data) {
  var s = "//\n// Whole document\n//\n"+darwino.Utils.toJson(data,false)+"\n";
  darwino.Utils.setText("content","{0}",s);
}, function error(err) {
  darwino.Utils.setText("content","{0}",darwino.Utils.toJson(err,false));
});  
