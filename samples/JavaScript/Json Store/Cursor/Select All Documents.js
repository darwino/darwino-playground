session.setAsync(false);

var store = session.getDatabase("playground").getStore("owners");
var s = "";

s += ">>> Select all documents in store, limited to 5\n";
var c = store.openCursor().range(0,5);
c.find(function(e) {
  s += "  "+e.getString("firstName")+" "+e.getString("lastName")+"\n";
});  
s += "\n";

s += ">>> Select all documents in store, an the next 3\n";
var c = store.openCursor().range(5,3);
c.find(function(e) {
  s += "  "+e.getString("firstName")+" "+e.getString("lastName")+"\n";
});  


darwino.Utils.setText("content","{0}",s);