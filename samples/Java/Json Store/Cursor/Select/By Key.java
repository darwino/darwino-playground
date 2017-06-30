Store store = session.getDatabase("playground").getStore("pinball");
String s = ""; final StringBuilder b = new StringBuilder();

s += ">>> Select a document by Key\n";
Cursor c = store.openCursor().range(0,5);
b.setLength(0);
c.key("1000").find(new CursorHandler() {
  public boolean handle(CursorEntry e) throws JsonException {  
  	b.append("  value:"+e.getJson()+"\n");
    return true;
  }
});  
s += b.toString()+"\n";


_formatText("{0}",s);