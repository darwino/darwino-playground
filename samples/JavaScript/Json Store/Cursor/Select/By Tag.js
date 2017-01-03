session.setAsync(false);

var store = session.getDatabase("playground").getStore("forum");
var s = "";

var c = store.openCursor().range(0,5);

// Select by single tag
s += ">>> Select documents with tag 'Ramp'\n";
c.tags("Ramp").find(function(e) {
  s += "  tags: ["+e.get("_tags")+"] - "+e.getUnid()+"\n";
});  
s += "\n";

// Select with 2 tags, AND
s += ">>> Select documents with tags 'Ramp' and 'Ball'\n";
c.tags("Ramp","Ball").find(function(e) {
  s += "  tags: ["+e.get("_tags")+"] - "+e.getUnid()+"\n";
});  
s += "\n";

// Select with 2 tags, OR
s += ">>> Select documents with either tag 'Ramp' or 'Ball'\n";
c.tags("Ramp","Ball").options(darwino.jstore.Cursor.TAGS_OR).find(function(e) {
  s += "  tags: ["+e.get("_tags")+"] - "+e.getUnid()+"\n";
});  
s += "\n";


darwino.Utils.setText("content","{0}",s);