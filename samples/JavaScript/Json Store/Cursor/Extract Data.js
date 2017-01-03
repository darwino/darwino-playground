session.setAsync(false);

var store = session.getDatabase("playground").getStore("owners");
var s = "";

s += ">>> Extract first and last names, limited to 5\n";
var c = store.openCursor().extract({first:'firstName',last:'lastName'}).range(0,5);
c.find(function(e) {
  s += "  "+JSON.stringify(e.getValue())+"\n";
});  
s += "\n";

darwino.Utils.setText("content","{0}",s);