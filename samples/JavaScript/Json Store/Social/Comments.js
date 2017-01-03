session.setAsync(false);

var store = session.getDatabase("playground").getStore("tempsocial");
var cstore = session.getDatabase("playground").getStore(darwino.jstore.Database.STORE_COMMENTS);
var s = "";

var doc = store.newDocument(""); doc.save();

// Create comments
function createComment(parent,title,content) {
  	var c = store.newDocument();
	c.setJson(
      {author:"Phil", title:title, content:content}
	);
  	c.setParent(parent);
    c.save();
  	return c;
}
var c1 = createComment(doc,"Comment 1","My content #1");
var c11 = createComment(c1,"Comment 11","My content #1-1");
var c12 = createComment(c1,"Comment 12","My content #1-2");
var c121 = createComment(c12,"Comment 121","My content #1-2-1");
var c2 = createComment(doc,"Comment 2","My content #2");
var c21 = createComment(c2,"Comment 21","My content #2-1");

// Search the comment to the just created document
s += "Direct comments for doc: "+doc.getUnid()+"\n";
var c = store.openCursor().parent(doc).orderBy("_id").range(0,5);
c.find(function(e) {
  s += "  "+e.getString("title")+","+e.getString("content")+"\n";
});  
s += "\n";

s += "All comments for doc: "+doc.getUnid()+"\n";
var c = store.openCursor().hierarchical(9).parent(doc).orderBy("_id").range(0,5);
c.find(function(e) {
  s += "  "+e.getString("title")+","+e.getString("content")+"\n";
});  
s += "\n";

// Clean-up...
doc.deleteDocument(darwino.jstore.Store.DELETE_CHILDREN);

darwino.Utils.setText("content","{0}",s);