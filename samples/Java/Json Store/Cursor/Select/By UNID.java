Store store = session.getDatabase("playground").getStore("pinball");
String s = ""; final StringBuilder b = new StringBuilder();

s += ">>> Select a document by UNID\n";
Cursor c = store.openCursor().range(0,5);
b.setLength(0);
c.unid("1000").find(new CursorHandler() {
  public boolean handle(CursorEntry e) throws JsonException {  
  	b.append("  value:"+e.getValue()+"\n");
    return true;
  }
});  
s += b.toString()+"\n";


_formatText("{0}",s);