session.setAsync(false);

// This snippet shows how to access a document using a REST service
// Only the JSON content part is read from the server

var database = "playground"
var store = "_default"
var att = "myatt.txt"

// Create a document with no attachment
var doc = session.getDatabase(database).getStore(store).newDocument()
var ct = darwino.jstore.createBinaryContent("text/plain","A new attachment created through the API");
var na = doc.createAttachment(att,ct)
doc.save();


// Create the new attachment to the existing doc
var unid = doc.getUnid()
var url = "$darwino-jstore/databases/"+database+"/stores/"+store+"/documents/"+unid+"/attachments/"+att;
$.ajax({
  url: url,
  method: "PUT",
  dataType: "text",
  data: "The attachment text is updated, from REST"
}).then(function(data) {
  var doc = session.getDatabase(database).getStore(store).loadDocument(unid)
  var val = doc.getAttachment(att).readAsString()
  var s = "//\n// Attachment\n//\n"+val+"\n";
  darwino.Utils.setText("content","{0}",s);
  doc.deleteDocument();
}, function error(err) {
  darwino.Utils.setText("content","{0}",darwino.Utils.toJson(err,false));
  doc.deleteDocument();
});  
