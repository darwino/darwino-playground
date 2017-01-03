Store store = session.getDatabase("playground").getStore("owners");
String s = ""; final StringBuilder b = new StringBuilder();

s += ">>> Query owners from 'CA', limited to 5\n";
Cursor c = store.openCursor()
			.query("{state:'CA'}")
			.range(0,5);
b.setLength(0);
c.find(new CursorHandler() {
  public boolean handle(CursorEntry e) throws JsonException {  
  	b.append("  "+e.getString("state")+", "+e.getString("firstName")+" "+e.getString("lastName")+"\n");
    return true;
  }
});  
s += b.toString()+"\n";


s += ">>> Query owners from 'CA', using a field, limited to 5\n";
c = store.openCursor()
			.query("{'@state':'CA'}")
			.range(0,5);
b.setLength(0);
c.find(new CursorHandler() {
  public boolean handle(CursorEntry e) throws JsonException {  
  	b.append("  "+e.getString("state")+", "+e.getString("firstName")+" "+e.getString("lastName")+"\n");
    return true;
  }
});  
s += b.toString()+"\n";

s += "\n";
s += ">>> Query owners from 'MI' or 'TX', limited to 10\n";
c = store.openCursor()
			.query("{$or: [{state:'MI'},{state:'TX'}]}")
			.range(0,10);
b.setLength(0);
c.find(new CursorHandler() {
  public boolean handle(CursorEntry e) throws JsonException {  
  	b.append("  "+e.getString("state")+", "+e.getString("firstName")+" "+e.getString("lastName")+"\n");
    return true;
  }
});  
s += b.toString()+"\n";


_formatText("{0}",s);