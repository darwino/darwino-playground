Store store = session.getDatabase("playground").getStore("owners");
String s = ""; final StringBuilder b = new StringBuilder();

s += ">>> Extract first and last names, limited to 5\n";
Cursor c = store.openCursor().extract("{first:'firstName',last:'lastName'}").range(0,5);

b.setLength(0);
c.find(new CursorHandler() {
  public boolean handle(CursorEntry entry) throws JsonException {  
  	b.append("  "+JsonJavaFactory.instance.toJson(entry.getJson())+"\n");
    return true;
  }
});  
s += b.toString()+"\n";

_formatText("{0}",s);