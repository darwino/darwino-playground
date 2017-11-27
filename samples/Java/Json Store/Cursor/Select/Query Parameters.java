Store store = session.getDatabase("playground").getStore("owners");
String s = ""; 

Cursor c = store.openCursor()
			.query("{state:'$$state'}")
			.range(0,5);

s += ">>> Query owners from 'CA', limited to 5\n";
final StringBuilder b1 = new StringBuilder();
c.param("state","CA");
c.find(new CursorHandler() {
  public boolean handle(CursorEntry e) throws JsonException {  
  	b1.append("  "+e.getString("state")+", "+e.getString("firstName")+" "+e.getString("lastName")+"\n");
    return true;
  }
});  
s += b1.toString()+"\n";

s += ">>> Query owners from 'MA', limited to 5\n";
final StringBuilder b2 = new StringBuilder();
c.param("state","MA");
c.find(new CursorHandler() {
  public boolean handle(CursorEntry e) throws JsonException {  
  	b2.append("  "+e.getString("state")+", "+e.getString("firstName")+" "+e.getString("lastName")+"\n");
    return true;
  }
});  
s += b2.toString()+"\n";



_formatText("{0}",s);