Store store = session.getDatabase("playground").getStore("forum");
String s = ""; final StringBuilder b = new StringBuilder();

// Get the first forum document
Document doc = store.openCursor().hierarchical(1).findOne().loadDocument();
String unid = doc.getUnid();

s += ">>> Select all documents by parent UNID, "+unid+"\n";
Cursor c = store.openCursor().range(0,5);

b.setLength(0);
c.parentUnid(unid).find(new CursorHandler() {
  public boolean handle(CursorEntry e) throws JsonException {  
  	b.append("  value:"+e.getValue()+"\n");
    return true;
  }
});  
s += b.toString()+"\n";

s += ">>> Select all documents using parent document\n";
Cursor c2 = store.openCursor().range(0,5);
b.setLength(0);
c2.parent(doc).find(new CursorHandler() {
  public boolean handle(CursorEntry e) throws JsonException {  
  	b.append("  value:"+e.getValue()+"\n");
    return true;
  }
});  
s += b.toString()+"\n";


_formatText("{0}",s);