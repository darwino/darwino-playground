// This snippet shows how to access a document using a REST service
// Only the JSON content part is read from the server

var database = "pinball"
var store = "pinball"
var att = "picture.png"
var unid = "1000";


// Create the new attachment to the existing doc
var url = "$darwino-jstore/databases/"+database+"/stores/"+store+"/documents/"+unid+"/attachments/"+att;
$.ajax({
  url: url,
}).then(function(data) {
  var s = "//\n// Attachment\n//\n"+data+"\n";
  darwino.Utils.setText("content","{0}",s);
}, function error(err) {
  darwino.Utils.setText("content","{0}",darwino.Utils.toJson(err,false));
});  
