session.setAsync(false);

var store = session.getDatabase("playground").getStore("forum");
var s = "";

s += ">>> Select documents by authors\n";
var c = store.openCursor().range(0,50);

c.orderBy("@author").categories(1).find(function(e) {
  var indent = "                    ".substring(0,e.indentLevel()*2);    
  if(e.isCategory()) {
  	s += indent+"Category:"+e.getKey()+"\n";
  } else {
  	s += indent+"title:"+e.getString('title')+"\n";
  }
});  
s += "\n";


darwino.Utils.setText("content","{0}",s);