Store store = session.getDatabase("playground").getStore("owners");
String s = "";

s += ">>> Count the whole query result\n";
Cursor c = store.openCursor();
int count = c.count();
s += "  count="+count+"\n";
s += "\n";

s += ">>> Count the query result, limited to a range(0,30)\n";
c = store.openCursor().range(0,30);
count = c.countWithLimit();
s += "  count="+count+"\n";

_formatText("{0}",s);