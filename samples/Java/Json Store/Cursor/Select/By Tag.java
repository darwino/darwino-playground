Store store = session.getDatabase("playground").getStore("forum");
String s = ""; final StringBuilder b = new StringBuilder();

Cursor c = store.openCursor().range(0,5);

// Select by single tag
s += ">>> Select documents with tag 'Ramp'\n";
b.setLength(0);
c.tags("Ramp").find(new CursorHandler() {
  public boolean handle(CursorEntry e) throws JsonException {  
  	b.append("  tags: "+e.get("_tags")+"\n");
    return true;
  }
});  
s += b.toString()+"\n";

// Select with 2 tags, AND
s += ">>> Select documents with tags 'Ramp' and 'Ball'\n";
b.setLength(0);
c.tags("Ramp","Ball").find(new CursorHandler() {
  public boolean handle(CursorEntry e) throws JsonException {  
  	b.append("  tags: "+e.get("_tags")+"\n");
    return true;
  }
});  
s += b.toString()+"\n";

// Select with 2 tags, OR
s += ">>> Select documents with either tag 'Ramp' or 'Ball'\n";
b.setLength(0);
c.tags("Ramp","Ball").options(Cursor.TAGS_OR).find(new CursorHandler() {
  public boolean handle(CursorEntry e) throws JsonException {  
  	b.append("  tags: "+e.get("_tags")+"\n");
    return true;
  }
});  
s += b.toString()+"\n";


_formatText("{0}",s);