session.setAsync(false);

var store = session.getDatabase("playground").getStore("tempsocial");
var s = "";

store.deleteAllDocuments()
// Create a few documents
function createDoc(unid,val,tags) {
  var doc = store.loadDocument(unid,darwino.jstore.Store.DOCUMENT_CREATE);
  if(doc.isNewDocument()) {
	doc.setJson(
  		{_tags: tags, val:val }
	);
    doc.save()
  }
  return doc;
}
// Create new documents and tag them
var doc1 = createDoc("TAG-1","DOC#1",['MA','CA','TX']);
var doc2 = createDoc("TAG-2","DOC#2",['MA','NH']);
var doc3 = createDoc("TAG-3","DOC#3",['FL','VT']);

// Search by Tags
s += "Searching for MA\n";
var c = store.openCursor().tags("MA").range(0,10);
c.find(function(e) {
  s += "  "+e.getString("val")+", tags:"+e.get("_tags")+"\n";
});  
s += "\n";

s += "Searching for MA AND NH\n";
var c = store.openCursor().tags("MA","NH").range(0,10);
c.find(function(e) {
  s += "  "+e.getString("val")+", tags:"+e.get("_tags")+"\n";
});  
s += "\n";

s += "Searching for MA OR FL\n";
var c = store.openCursor().tags("MA","FL").options(darwino.jstore.Cursor.TAGS_OR).range(0,10);
c.find(function(e) {
  s += "  "+e.getString("val")+", tags:"+e.get("_tags")+"\n";
});  
s += "\n";

s += "Searching for VT\n";
var c = store.openCursor().tags("VT").range(0,10);
c.find(function(e) {
  s += "  "+e.getString("val")+", tags:"+e.get("_tags")+"\n";
});  

// Clean-up...
doc1.deleteDocument(); doc2.deleteDocument(); doc3.deleteDocument();

darwino.Utils.setText("content","{0}",s);