session.setAsync(false);

var store = session.getDatabase("playground").getStore("temp2");
var s="";


// Delete all the documents from a particular store
// The database has the same method, which then includes all the stores
store.deleteAllDocuments();

s += ">>> Documents deleted\n";

darwino.Utils.setText("content","{0}",s);