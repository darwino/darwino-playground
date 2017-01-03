session.setAsync(false);

var store = session.getDatabase("playground").getStore("pinball");
var s = "";

s += ">>> Select a document by Id\n";
var c = store.openCursor().range(0,5);
var doc = store.loadDocument("1000");
var id = doc.getDocId();

// Select by Id
c.id(id).find(function(e) {
  s += "  value:"+darwino.Utils.toJson(e.getValue(),false)+"\n";
});  
s += "\n";


darwino.Utils.setText("content","{0}",s);