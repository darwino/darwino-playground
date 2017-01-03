var s = "";

session.getDatabase("playground",null,function(database) {
  var store = database.getStore("pinball");
  
  // The document is loaded asynchronously
  store.loadDocument("1000", null, function(doc) {
      s += ">> Document\n"
      s += "  Unid: "+doc.getUnid()+"\n"
      s += "  Id: "+doc.getDocId()+"\n"
      s += "  Json: "+doc.getJsonString()+"\n"
      darwino.Utils.setText("content","{0}",s);
  });
  
  // This document does not exist
  // So the function is only called in case of success
  // Nothing happens in case of an error
  store.loadDocument("1000FAKE", null, function(doc) {
      s += "!!! Should never be displayed as the document does not exist\n"
      darwino.Utils.setText("content","{0}",s);
  });
    
});


s += "Loading document...\n"

darwino.Utils.setText("content","{0}",s);
