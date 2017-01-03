session.setAsync(false);

var store = session.getDatabase("playground").getStore("pinball");

var doc = store.loadDocument("1000");
var s = doc.getJsonString()

darwino.Utils.setText("content","{0}",s);
                      