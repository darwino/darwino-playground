Store store = session.getDatabase("playground").getStore("owners");
String s = ""; final StringBuilder b = new StringBuilder();

s += ">>> Sort documents by first name, limited to 5\n";
Cursor c = store.openCursor().range(0,5).orderBy("@firstName");
b.setLength(0);
c.find(new CursorHandler() {
  public boolean handle(CursorEntry e) throws JsonException {  
  	b.append("  "+e.getString("firstName")+" "+e.getString("lastName")+"\n");
    return true;
  }
});  
s += b.toString()+"\n";

s += ">>> Sort documents by first name, descending, limited to 5\n";
c = store.openCursor().range(0,5).orderBy("@firstName desc");
b.setLength(0);
c.find(new CursorHandler() {
  public boolean handle(CursorEntry e) throws JsonException {  
  	b.append("  "+e.getString("firstName")+" "+e.getString("lastName")+"\n");
    return true;
  }
});  
s += b.toString()+"\n";


_formatText("{0}",s);