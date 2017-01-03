Store store = session.getDatabase("playground").getStore("forum");
String s = ""; final StringBuilder b = new StringBuilder();

s += ">>> Select documents with one hierarchical level\n";
Cursor c = store.openCursor().range(0,50);
b.setLength(0);
c.hierarchical(1).find(new CursorHandler() {
  public boolean handle(CursorEntry e) throws JsonException {  
    String indent = "                    ".substring(0,e.getIndentLevel()*2);    
    b.append(indent+"title:"+e.getString("title")+"\n");
    return true;
  }
});  
s += b.toString()+"\n";


_formatText("{0}",s);