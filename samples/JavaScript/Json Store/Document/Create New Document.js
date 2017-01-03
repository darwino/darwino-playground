session.setAsync(false);

var store = session.getDatabase("playground").getStore("_default");
var s="";

s += ">> Create a new document from a JSON object"
var doc = store.newDocument(); // Id is generated automatically
var json = {
  f1:	'field value 1',
  f2:	'field value 2'
}
doc.setJson(json)
//doc.save()
s += "UNID: "+doc.getUnid()+"\n";
s += "JSON: "+doc.getJsonString()+"\n";
s += "\n";

s += ">> Create a new document from a JSON string"
var doc2 = store.newDocument(); // Id is generated automatically
var json = "{f1:'field value 1',f2:'field value 2'}"
doc2.setJsonString(json)
//doc2.save()
s += "UNID: "+doc2.getUnid()+"\n";
s += "JSON: "+doc2.getJsonString()+"\n";

darwino.Utils.setText("content","{0}",s);
                      