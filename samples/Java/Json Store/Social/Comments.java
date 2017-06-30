final Store store = session.getDatabase("playground").getStore("tempsocial");
final Store cstore = session.getDatabase("playground").getStore(Database.STORE_COMMENTS);
String s = ""; final StringBuilder b = new StringBuilder();

Document doc = store.newDocument(""); doc.save();

// Create comments
Document c1 = store.newDocument().setParent(doc).setJsonString("{author:'phil', title:'Comment 1', content:'My content #1'}").save();
Document c11 = store.newDocument().setParent(c1).setJsonString("{author:'phil', title:'Comment 11', content:'My content #1-1'}").save();
Document c12 = store.newDocument().setParent(c1).setJsonString("{author:'phil', title:'Comment 12', content:'My content #1-2'}").save();
Document c121 = store.newDocument().setParent(c12).setJsonString("{author:'phil', title:'Comment 121', content:'My content #1-2-1'}").save();
Document c2 = store.newDocument().setParent(doc).setJsonString("{author:'phil', title:'Comment 2', content:'My content #2'}").save();
Document c21 = store.newDocument().setParent(c2).setJsonString("{author:'phil', title:'Comment 21', content:'My content #2-1'}").save();


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