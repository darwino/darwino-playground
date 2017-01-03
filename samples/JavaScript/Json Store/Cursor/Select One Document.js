session.setAsync(false);

var store = session.getDatabase("playground").getStore("owners");
var s = "";

s += ">>> Retrieve the first document from the cursor, using a callback, limited to 1\n";
var c = store.openCursor().range(0,1);
c.find(function(e) {
  s += "  "+e.getString("firstName")+" "+e.getString("lastName")+"\n";
});  
s += "\n";

s += ">>> Retrieve the first document from the cursor, using a callback, and stop\n";
var c = store.openCursor().range(0,10);
c.find(function(e) {
  s += "  "+e.getString("firstName")+" "+e.getString("lastName")+"\n";
  return false;
});  
s += "\n";

s += ">>> Retrieve the first document from the cursor, as a cursor entry\n";
var c = store.openCursor();
var e = c.findOne();
s += "  "+e.getString("firstName")+" "+e.getString("lastName")+"\n";


darwino.Utils.setText("content","{0}",s);