session.setAsync(false);

var store = session.getDatabase("playground").getStore("forum");
var s = "";

s += ">>> Select documents with one hierarchical level\n";
var c = store.openCursor().range(0,50);

c.hierarchical(2).find(function(e) {
  var indent = "                    ".substring(0,e.getIndentLevel()*2);    
  s += indent+"title:"+e.getString('title')+"\n";
});  
s += "\n";


darwino.Utils.setText("content","{0}",s);