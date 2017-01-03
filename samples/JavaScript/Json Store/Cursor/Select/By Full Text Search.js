session.setAsync(false);

var store = session.getDatabase("playground").getStore("forum");
var s = "";

s += ">>> Select all documents using full text search, 'yellow'\n";
var c = store.openCursor().range(0,5);

c.ftSearch('yellow').find(function(e) {
  s += "  title:"+e.getString("title")+"\n";
});  
s += "\n";


darwino.Utils.setText("content","{0}",s);