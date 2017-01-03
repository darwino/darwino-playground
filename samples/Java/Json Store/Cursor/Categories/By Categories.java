Store store = session.getDatabase("playground").getStore("forum");
String s = ""; final StringBuilder b = new StringBuilder();

s += ">>> Select documents by categories\n";
Cursor c = store.openCursor().range(0,50);
b.setLength(0);
c.orderBy("@category").categories(1).find(new CursorHandler() {
  public boolean handle(CursorEntry e) throws JsonException {  
    String indent = "                    ".substring(0,e.getIndentLevel()*2);    
    if(e.isCategory()) {
  	  b.append(indent+"Category:"+e.getKey()+"\n");
    } else {
  	  b.append(indent+"title:"+e.getString("title")+"\n");
    }
    return true;
  }
});  
s += b.toString()+"\n";


_formatText("{0}",s);