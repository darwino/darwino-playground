Store store = session.getDatabase("playground").getStore("temp2");
String s="";


// Delete all the documents from a particular store
// The database has the same method, which then includes all the stores
store.deleteAllDocuments();

s += ">>> Documents deleted\n";

_formatText("{0}",s);