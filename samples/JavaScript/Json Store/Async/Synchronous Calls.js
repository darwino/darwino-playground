session.setAsync(false);

var store = session.getDatabase("playground").getStore("pinball");
var s = "";

// The document is simply loaded synchronously
var doc = store.loadDocument("1000");

s += "\n>> Document\n"
s += "  Unid: "+doc.getUnid()+"\n"
s += "  Id: "+doc.getDocId()+"\n"
s += "  Json: "+doc.getJsonString()+"\n"


// This one is not available
try {
	var doc = store.loadDocument("1000FAKE");
} catch(e) {
	s += "\nCaught exception!\n";
	s += e.toString();
}

darwino.Utils.setText("content","{0}",s);