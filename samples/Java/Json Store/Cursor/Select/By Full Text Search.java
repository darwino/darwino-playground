Store store = session.getDatabase("playground").getStore("forum");
String s = ""; final StringBuilder b = new StringBuilder();

s += ">>> Select all documents using full text search, 'yellow'\n";
Cursor c = store.openCursor().range(0,5);

b.setLength(0);
c.ftSearch("yellow").find(new CursorHandler() {
  public boolean handle(CursorEntry e) throws JsonException {  
  	b.append("  title:"+e.getString("title")+"\n");
    return true;
  }
});  
s += b.toString()+"\n";


_formatText("{0}",s);