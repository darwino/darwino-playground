Store store = session.getDatabase("playground").getStore("temp1");
String s = "";

s += ">> Update an existing document by updating its JSON content\n";
Document doc = store.newDocument();
doc.setJsonString("{field1:'Value 1', field2:'Value 2', field3:'Value 3'}");
doc.save();

s += "JSON when created: "+doc.getJsonString(false)+"\n";
JsonObject json = (JsonObject)doc.getJson();

// Add a new field
json.put("NewField","This is a new field");

// Modify an existing field
json.put("field1","This is an update field - Previous was: "+json.getString("field1"));

// Delete a field (remove)
json.remove("field2");
doc.save();

s += "JSON after update: "+store.loadDocument(doc.getUnid()).getJsonString(false)+"\n";

// Clean up
doc.deleteDocument();

_formatText("{0}",s);
                      