session.setAsync(false);

var store = session.getDatabase("playground").getStore("owners");
var s = "";

s += ">>> Count the whole query result\n";
var c = store.openCursor();
var count = c.count();
s += "  count="+count+"\n";
s += "\n";

s += ">>> Count the query result, limited to a range(0,30)\n";
var c = store.openCursor().range(0,30);
var count = c.countWithLimit();
s += "  count="+count+"\n";

darwino.Utils.setText("content","{0}",s);