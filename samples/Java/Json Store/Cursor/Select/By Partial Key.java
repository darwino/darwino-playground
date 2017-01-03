Store store = session.getDatabase("playground").getStore("pinball");
String s = ""; final StringBuilder b = new StringBuilder();

s += ">>> Select a document by partial key\n";
Cursor c = store.openCursor().range(0,5);
b.setLength(0);
c.partialKey("11").find(new CursorHandler() {
  public boolean handle(CursorEntry e) throws JsonException {  
  	b.append("  Key:"+e.getKey()+"\n");
    return true;
  }
});  
s += b.toString()+"\n";


_formatText("{0}",s);