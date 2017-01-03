session.setAsync(false);

var store = session.getDatabase("playground").getStore("pinball");
var s = "";

s += ">>> Select a document by UNID\n";
var c = store.openCursor().range(0,5);

// Select by UNID
c.unid("1000").find(function(e) {
  s += "  value:"+darwino.Utils.toJson(e.getValue(),false)+"\n";
});  
s += "\n";


darwino.Utils.setText("content","{0}",s);