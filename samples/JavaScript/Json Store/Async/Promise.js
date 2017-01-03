var s = "";

session.getDatabase("playground")
.then(function (database) {
	var store = database.getStore("pinball");
  var ps = store.loadDocument("1000");
  
  ps.then(function(doc) {
      s += "\n>> Document\n"
      s += "  Unid: "+doc.getUnid()+"\n"
      s += "  Id: "+doc.getDocId()+"\n"
      s += "  Json: "+doc.getJsonString()+"\n"
      darwino.Utils.setText("content","{0}",s);
  }, function (error) {
      s += "!!! Should never be displayed as the document exist\n"
      darwino.Utils.setText("content","{0}",s);
  });
  
  // This document does not exist
  // So the function is only call in case of success
  ps = store.loadDocument("1000FAKE");
  
  ps.then( function(doc) {
      s += "!!! Should never be displayed as the document does not exist\n"
      darwino.Utils.setText("content","{0}",s);
  }, function(error) {
      s += "\n>> Caught Error\n"
      s += "  Error: "+error.toString()+"\n"
      darwino.Utils.setText("content","{0}",s);
  });
})


s += "Start loading documents, asynchronously...\n"

darwino.Utils.setText("content","{0}",s);
