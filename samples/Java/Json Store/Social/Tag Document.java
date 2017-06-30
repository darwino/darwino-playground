final Store store = session.getDatabase("playground").getStore("tempsocial");
String s = ""; final StringBuilder b = new StringBuilder();

store.deleteAllDocuments();

// Create a few documents
Document doc1 = store.loadDocument("TAG-1",Store.DOCUMENT_CREATE).setJsonString("{val: 'DOC#1', _tags: ['MA','CA','TX']}").save();
Document doc2 = store.loadDocument("TAG-2",Store.DOCUMENT_CREATE).setJsonString("{val: 'DOC#2', _tags: ['MA','NH']}").save();
Document doc3 = store.loadDocument("TAG-3",Store.DOCUMENT_CREATE).setJsonString("{val: 'DOC#3', _tags: ['FL','VT']}").save();

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