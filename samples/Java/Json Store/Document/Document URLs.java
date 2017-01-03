Store store = session.getDatabase("playground").getStore("pinball");
Document doc = store.loadDocument("1000");
String s = "";

String docURL = doc.getDocumentUrl();
String docURL2 = doc.getDocumentUrl()+"?.pretty";
String attURL = doc.getAttachmentUrl("picture.png");

s += ">> Document URL</br><a target='_blank' href='"
  		+docURL+"'>"+docURL+"</a><br/></br>";
s += ">> Document URL - Indented</br><a target='_blank' href='"
  		+docURL2+"'>"+docURL2+"</a><br/></br>";
s += ">> Attachment URL</br><a target='_blank' href='"
  		+attURL+"'>"+attURL+"</a><br/>";

_out(s)
