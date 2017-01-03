session.setAsync(false);

var store = session.getDatabase("playground").getStore("pinball");
var s = "";

// All keys
s += ">>> All documents\n";
var c1 = store.openCursor().range(0,8);
c1.find(function(e) {
  s += "  Key:"+e.getKey()+"\n";
});  
s += "  ...\n\n";

// Select by Start Key only
s += ">>> Select 5 documents using Start key '1100'\n";
var c1 = store.openCursor().range(0,5);
c1.startKey("1100").find(function(e) {
  s += "  Key:"+e.getKey()+"\n";
});  
s += "\n";

// Select by Start Key only, excluded
s += ">>> Select 5 documents using Start key '1100', excluded\n";
var c1 = store.openCursor().range(0,5);
c1.startKey("1100",true).find(function(e) {
  s += "  Key:"+e.getKey()+"\n";
});  
s += "\n";

// Select by End Key only
s += ">>> Select 5 documents using end key '1176'\n";
var c1 = store.openCursor().range(0,5);
c1.endKey("1176").find(function(e) {
  s += "  Key:"+e.getKey()+"\n";
});  
s += "\n";

// Select by End Key only, excluded
s += ">>> Select 5 documents using end key '1176', excluded\n";
var c1 = store.openCursor().range(0,5);
c1.endKey("1176",true).find(function(e) {
  s += "  Key:"+e.getKey()+"\n";
});  
s += "\n";

// Select by Key Range
s += ">>> Select 5 documents using key range '1100'...'1322', both excluded\n";
var c1 = store.openCursor().range(0,5);
c1.startKey("1100").endKey("1322").find(function(e) {
  s += "  Key:"+e.getKey()+"\n";
});  
s += "\n";

// Select by Key Range, excluded
s += ">>> Select 5 documents using key range '1100'...'1322'\n";
var c1 = store.openCursor().range(0,5);
c1.startKey("1100",true).endKey("1322",true).find(function(e) {
  s += "  Key:"+e.getKey()+"\n";
});  
s += "\n";

darwino.Utils.setText("content","{0}",s);