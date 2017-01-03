session.setAsync(false);

var store = session.getDatabase("playground").getStore("pinball");
var doc = store.loadDocument("1000");
var s = "";

var docURL = doc.getDocumentUrl();
var docURL2 = doc.getDocumentUrl()+"?.pretty";
var attURL = doc.getAttachmentUrl("picture.png");

s += ">> Document URL</br><a target='_blank' href='"
		+docURL+"'>"+docURL+"</a><br/></br>"
s += ">> Document URL - Indented</br><a target='_blank' href='"
		+docURL2+"'>"+docURL2+"</a><br/></br>"
s += ">> Attachment URL</br><a target='_blank' href='"
		+attURL+"'>"+attURL+"</a><br/>"

document.getElementById("content").innerHTML = s
