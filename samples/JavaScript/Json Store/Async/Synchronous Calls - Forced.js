// This is forced at the session level!
session.setAsync(false);

var store = session.getDatabase("playground").getStore("pinball");
var s = "";

// The document is simply loaded synchronously
var doc = store.loadDocument("1000");

s += ">> Document\n"
s += "  Unid: "+doc.getUnid()+"\n"
s += "  Id: "+doc.getDocId()+"\n"
s += "  Json: "+doc.getJsonString()+"\n"

darwino.Utils.setText("content","{0}",s);