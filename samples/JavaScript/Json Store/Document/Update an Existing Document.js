session.setAsync(false);

var store = session.getDatabase("playground").getStore("temp1");
var s = "";

s += ">> Update an existing document by updating its JSON content\n"
var doc = store.newDocument();
doc.setJson({field1:'Value 1', field2:'Value 2', field3:'Value 3'});
doc.save();

s += "JSON when created: "+doc.getJsonString(false)+"\n";
var json = doc.getJson();

// Add a new field
json['NewField'] = "This is a new field"

// Modify an existing field
json['field1'] = "This is an update field - Previous was: "+ json['field1'];

// Delete a field (remove)
delete json['field2']
doc.save()

s += "JSON after update: "+store.loadDocument(doc.getUnid()).getJsonString(false)+"\n";

// Clean up
doc.deleteDocument();

darwino.Utils.setText("content","{0}",s);
                      