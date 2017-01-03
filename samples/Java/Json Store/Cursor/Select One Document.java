Store store = session.getDatabase("playground").getStore("owners");
String s = ""; final StringBuilder b = new StringBuilder();

s += ">>> Retrieve the first document from the cursor, using a callback, limited to 1\n";
Cursor c = store.openCursor().range(0,1);
b.setLength(0);
c.find(new CursorHandler() {
  public boolean handle(CursorEntry e) throws JsonException {  
  	b.append("  "+e.getString("firstName")+" "+e.getString("lastName")+"\n");
    return true;
  }
});  
s += b.toString()+"\n";

s += ">>> Retrieve the first document from the cursor, using a callback, and stop\n";
c = store.openCursor().range(0,10);
b.setLength(0);
c.find(new CursorHandler() {
  public boolean handle(CursorEntry e) throws JsonException {  
  	b.append("  "+e.getString("firstName")+" "+e.getString("lastName")+"\n");
    return false;
  }
});  
s += b.toString()+"\n";

s += ">>> Retrieve the first document from the cursor, as a cursor entry\n";
c = store.openCursor();
CursorEntry e = c.findOne();
s += "  "+e.getString("firstName")+" "+e.getString("lastName")+"\n";


_formatText("{0}",s);