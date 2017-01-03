Store store = session.getDatabase("playground").getStore("temp");
String s="";

Document d1 = store.newDocument(); d1.save();
Document d2 = store.newDocument(); d2.save();
Document d3 = store.newDocument(); d3.save();

s += ">>> Documents created\n";
s += "d1 exists: "+store.documentExists(d1.getUnid())+"\n";
s += "d2 exists: "+store.documentExists(d2.getUnid())+"\n";
s += "d3 exists: "+store.documentExists(d3.getUnid())+"\n";

// The deletion methods have some options:
// You can use some delete options as well
// 		darwino.jstore.DELETE_ERASE
// 		darwino.jstore.DELETE_CHILDREN
// 		darwino.jstore.DELETE_SYNCSLAVES

// Call remove() from the Document object
d1.deleteDocument();

// Call deleteXXX() from the Store, using a UNID
store.deleteDocument(d2.getUnid());

// Call deleteXXX() from the Database, using a DocID
store.getDatabase().deleteDocumentById(d3.getDocId());

s += "\n";
s += ">>> Documents deleted\n";
s += "d1 exists: "+store.documentExists(d1.getUnid())+"\n";
s += "d2 exists: "+store.documentExists(d2.getUnid())+"\n";
s += "d3 exists: "+store.documentExists(d3.getUnid())+"\n";

_formatText("{0}",s);
