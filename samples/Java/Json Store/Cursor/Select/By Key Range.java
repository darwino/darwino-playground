Store store = session.getDatabase("playground").getStore("pinball");
String s = ""; final StringBuilder b = new StringBuilder();

// All keys
s += ">>> All documents\n";
Cursor c1 = store.openCursor().range(0,8);
b.setLength(0);
c1.find(new CursorHandler() {
  public boolean handle(CursorEntry e) throws JsonException {  
  	b.append("  Key:"+e.getKey()+"\n");
    return true;
  }
});  
s += b.toString()+"  ...\n\n";

// Select by Start Key only
s += ">>> Select 5 documents using Start key '1100'\n";
c1 = store.openCursor().range(0,5);
b.setLength(0);
c1.startKey("1000").find(new CursorHandler() {
  public boolean handle(CursorEntry e) throws JsonException {  
  	b.append("  Key:"+e.getKey()+"\n");
    return true;
  }
});  
s += b.toString()+"\n";

// Select by Start Key only, excluded
s += ">>> Select 5 documents using Start key '1100', excluded\n";
c1 = store.openCursor().range(0,5);
b.setLength(0);
c1.startKey("1000",true).find(new CursorHandler() {
  public boolean handle(CursorEntry e) throws JsonException {  
  	b.append("  Key:"+e.getKey()+"\n");
    return true;
  }
});  
s += b.toString()+"\n";

// Select by End Key only
s += ">>> Select 5 documents using End key '1176'\n";
c1 = store.openCursor().range(0,5);
b.setLength(0);
c1.endKey("1176").find(new CursorHandler() {
  public boolean handle(CursorEntry e) throws JsonException {  
  	b.append("  Key:"+e.getKey()+"\n");
    return true;
  }
});  
s += b.toString()+"\n";

// Select by End Key only, excluded
s += ">>> Select 5 documents using End key '1176', excluded\n";
c1 = store.openCursor().range(0,5);
b.setLength(0);
c1.endKey("1176",true).find(new CursorHandler() {
  public boolean handle(CursorEntry e) throws JsonException {  
  	b.append("  Key:"+e.getKey()+"\n");
    return true;
  }
});  
s += b.toString()+"\n";

// Select by Key Range
s += ">>> Select 5 documents using key range '1100'...'1322'\n";
c1 = store.openCursor().range(0,5);
b.setLength(0);
c1.startKey("1100").endKey("1322").find(new CursorHandler() {
  public boolean handle(CursorEntry e) throws JsonException {  
  	b.append("  Key:"+e.getKey()+"\n");
    return true;
  }
});  
s += b.toString()+"\n";

// Select by Key Range, excluded
s += ">>> Select 5 documents using key range '1100'...'1322', excluded\n";
c1 = store.openCursor().range(0,5);
b.setLength(0);
c1.startKey("1100",true).endKey("1322",true).find(new CursorHandler() {
  public boolean handle(CursorEntry e) throws JsonException {  
  	b.append("  Key:"+e.getKey()+"\n");
    return true;
  }
});  
s += b.toString()+"\n";


_formatText("{0}",s);
