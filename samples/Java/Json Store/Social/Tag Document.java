final Store store = session.getDatabase("playground").getStore("tempsocial");
String s = ""; final StringBuilder b = new StringBuilder();

store.deleteAllDocuments();
// Create a few documents
Function.e3<Document,JsonException,String,String,String> createDoc = new Function.e3<Document,JsonException,String,String,String>() {
  public Document call(String unid, String val, String tags) throws JsonException {
  	Document doc = store.loadDocument(unid,Store.DOCUMENT_CREATE);
  	if(doc.isNewDocument()) {
      JsonObject o = new JsonObject();
      o.putArray("_tags",(JsonArray)JsonJavaFactory.instance.fromJson(tags));
      o.putString("val",val);
	  doc.setJson(o);
      doc.save();
    }
    return doc;
  }
};
// Create new documents and tag them
Document doc1 = createDoc.call("TAG-1","DOC#1","['MA','CA','TX']");
Document doc2 = createDoc.call("TAG-2","DOC#2","['MA','NH']");
Document doc3 = createDoc.call("TAG-3","DOC#3","['FL','VT']");

// Search by Tags
s += "Searching for MA\n";
Cursor c = store.openCursor().tags("MA").range(0,10);
b.setLength(0);
c.find(new CursorHandler() {
  public boolean handle(CursorEntry e) throws JsonException {  
  	b.append("  "+e.getString("val")+", tags:"+e.get("_tags")+"\n");
    return true;
  }
});  
s += b.toString()+"\n";

s += "Searching for MA AND NH\n";
c = store.openCursor().tags("MA","NH").range(0,10);
b.setLength(0);
c.find(new CursorHandler() {
  public boolean handle(CursorEntry e) throws JsonException {  
  	b.append("  "+e.getString("val")+", tags:"+e.get("_tags")+"\n");
    return true;
  }
});  
s += b.toString()+"\n";

s += "Searching for MA OR FL\n";
c = store.openCursor().tags("MA","FL").options(Cursor.TAGS_OR).range(0,10);
b.setLength(0);
c.find(new CursorHandler() {
  public boolean handle(CursorEntry e) throws JsonException {  
  	b.append("  "+e.getString("val")+", tags:"+e.get("_tags")+"\n");
    return true;
  }
});  
s += b.toString()+"\n";

s += "Searching for VT\n";
c = store.openCursor().tags("VT").range(0,10);
b.setLength(0);
c.find(new CursorHandler() {
  public boolean handle(CursorEntry e) throws JsonException {  
  	b.append("  "+e.getString("val")+", tags:"+e.get("_tags")+"\n");
    return true;
  }
});  
s += b.toString()+"\n";

// Clean-up...
doc1.deleteDocument(); doc2.deleteDocument(); doc3.deleteDocument();

_formatText("{0}",s);