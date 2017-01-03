final Store store = session.getDatabase("playground").getStore("tempsocial");
final Store cstore = session.getDatabase("playground").getStore(Database.STORE_COMMENTS);
String s = ""; final StringBuilder b = new StringBuilder();

Document doc = store.newDocument(""); doc.save();

// Create comments
Function.e3<Document,JsonException,Document,String,String> createComment = new Function.e3<Document,JsonException,Document,String,String>() {
  public Document call(Document parent, String title, String content) throws JsonException {
  	Document c = store.newDocument();
    JsonObject o = new JsonObject();
    o.putString("author","Phil");
    o.putString("title",title);
    o.putString("content",content);
	c.setJson(o);
  	c.setParent(parent);
    c.save();
  	return c;
  }
};

Document c1 = createComment.call(doc,"Comment 1","My content #1");
Document c11 = createComment.call(c1,"Comment 11","My content #1-1");
Document c12 = createComment.call(c1,"Comment 12","My content #1-2");
Document c121 = createComment.call(c12,"Comment 121","My content #1-2-1");
Document c2 = createComment.call(doc,"Comment 2","My content #2");
Document c21 = createComment.call(c2,"Comment 21","My content #2-1");

// Search the comment to the just created document
s += "Direct comments for doc: "+doc.getUnid()+"\n";
Cursor c = store.openCursor().parent(doc).orderBy("_id").range(0,5);
b.setLength(0);
c.find(new CursorHandler() {
  public boolean handle(CursorEntry e) throws JsonException {  
  	b.append("  "+e.getString("title")+","+e.getString("content")+"\n");
    return true;
  }
});  
s += b.toString()+"\n";

s += "All comments for doc: "+doc.getUnid()+"\n";
c = store.openCursor().hierarchical(9).parent(doc).orderBy("_id").range(0,5);
b.setLength(0);
c.find(new CursorHandler() {
  public boolean handle(CursorEntry e) throws JsonException {  
  	b.append("  "+e.getString("title")+","+e.getString("content")+"\n");
    return true;
  }
});  
s += b.toString()+"\n";

// Clean-up...
doc.deleteDocument(Store.DELETE_CHILDREN);

_formatText("{0}",s);