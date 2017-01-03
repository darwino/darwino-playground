session.setAsync(false);

var store = session.getDatabase("playground").getStore("pinball");
var s = "";

s += ">>> Select a document by Partial Key\n";
var c = store.openCursor().range(0,5);

// Select by Partial Key
c.partialKey("11").find(function(e) {
  s += "  Key:"+e.getKey()+"\n";
});  
s += "\n";


darwino.Utils.setText("content","{0}",s);