Store store = session.getDatabase("playground").getStore("owners");
String s = ""; final StringBuilder b = new StringBuilder();

s += ">>> Select all documents in store, limited to 5\n";
Cursor c = store.openCursor().range(0,5);
b.setLength(0);
c.find(new CursorHandler() {
  public boolean handle(CursorEntry e) throws JsonException {  
  	b.append("  "+e.getString("firstName")+" "+e.getString("lastName")+"\n");
    return true;
  }
});  
s += b.toString()+"\n";

s += ">>> Select all documents in store, an the next 3\n";
c = store.openCursor().range(5,3);
b.setLength(0);
c.find(new CursorHandler() {
  public boolean handle(CursorEntry e) throws JsonException {  
	b.append("  "+e.getString("firstName")+" "+e.getString("lastName")+"\n");
    return true;
  }
});  
s += b.toString()+"\n";


_formatText("{0}",s);