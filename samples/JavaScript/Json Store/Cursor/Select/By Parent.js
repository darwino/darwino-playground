session.setAsync(false);

var store = session.getDatabase("playground").getStore("forum");
var s = "";

// Get the first forum document
var doc = store.openCursor().hierarchical(1).findOne().loadDocument();
var unid = doc.getUnid();

s += ">>> Select all documents by parent UNID, "+unid+"\n";
var c = store.openCursor().range(0,5);
c.parentUnid(unid).find(function(e) {
  s += "  value:"+darwino.Utils.toJson(e.getJson(),false)+"\n";
});  
s += "\n";

s += ">>> Select all documents using parent document\n";
var c2 = store.openCursor().range(0,5);
c2.parent(doc).find(function(e) {
  s += "  value:"+darwino.Utils.toJson(e.getJson(),false)+"\n";
});  
s += "\n";

darwino.Utils.setText("content","{0}",s);