Store store = session.getDatabase("playground").getStore("_default");
String s="";

s += ">> Create a new document from a JSON object";
Document doc = store.newDocument(); // Id is generated automatically
JsonObject json = new JsonObject();
json.put("f1","field value 1");
json.put("f2","field value 2");

doc.setJson(json);
//doc.save()
s += "UNID: "+doc.getUnid()+"\n";
s += "JSON: "+doc.getJsonString()+"\n";
s += "\n";

s += ">> Create a new document from a JSON string";
Document doc2 = store.newDocument(); // Id is generated automatically
String json2 = "{f1:'field value 1',f2:'field value 2'}";
doc2.setJsonString(json2);
//doc2.save()
s += "UNID: "+doc2.getUnid()+"\n";
s += "JSON: "+doc2.getJsonString()+"\n";

_formatText("{0}",s);
                      