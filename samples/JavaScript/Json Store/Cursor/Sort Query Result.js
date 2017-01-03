session.setAsync(false);

var store = session.getDatabase("playground").getStore("owners");
var s = "";

s += ">>> Sort documents by first name, limited to 5\n";
var c = store.openCursor().range(0,5).orderBy('@firstName');
c.find(function(e) {
  s += "  "+e.getString("firstName")+" "+e.getString("lastName")+"\n";
});  
s += "\n";

s += ">>> Sort documents by first name, descending, limited to 5\n";
var c = store.openCursor().range(0,5).orderBy('@firstName desc');
c.find(function(e) {
  s += "  "+e.getString("firstName")+" "+e.getString("lastName")+"\n";
});  
s += "\n";


darwino.Utils.setText("content","{0}",s);