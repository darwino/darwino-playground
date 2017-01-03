var s = "";

session.getDatabase("playground",null,function(database) {
  var store = database.getStore("pinball");

  // The document is loaded asynchronously
  store.loadDocument("1000", null, 
  { success: function(doc) {
      s += "\n>> Document\n"
      s += "  Unid: "+doc.getUnid()+"\n"
      s += "  Id: "+doc.getDocId()+"\n"
      s += "  Json: "+doc.getJsonString()+"\n"
      darwino.Utils.setText("content","{0}",s);
  }, failure: function error() {
      s += "!!! Should never be displayed as the document exist\n"
      darwino.Utils.setText("content","{0}",s);
  }
  });
  
  // This document does not exist
  // So the function is only call in case of success
  store.loadDocument("1000FAKE", null, 
  { success: function(doc) {
      s += "!!! Should never be displayed as the document does not exist\n"
      darwino.Utils.setText("content","{0}",s);
  }, failure: function(error) {
      s += "\n>> Caught Error\n"
      s += "  Error: "+error.toString()+"\n"
      darwino.Utils.setText("content","{0}",s);
  }
  });

});

s += "Start loading documents, asynchronously...\n"

darwino.Utils.setText("content","{0}",s);
