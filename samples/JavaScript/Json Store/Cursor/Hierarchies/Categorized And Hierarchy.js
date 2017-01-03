session.setAsync(false);

var store = session.getDatabase("playground").getStore("forum");
var s = "";

s += ">>> Select documents categorized and showing all hierarchical levels\n";
var c = store.openCursor().range(0,50);

c.orderBy("@category").hierarchical(4).categories(1).find(function(e) {
  var indent = "                    ".substring(0,e.indentLevel()*2);    
  if(e.isCategory()) {
  	s += indent+"Category:"+e.getKey()+"\n";
  } else {
  	s += indent+"title:"+e.getString('title')+"\n";
  }
});  
s += "\n";


darwino.Utils.setText("content","{0}",s);