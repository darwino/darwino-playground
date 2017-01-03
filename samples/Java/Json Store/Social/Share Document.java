Store store = session.getDatabase("playground").getStore("tempsocial");
String s = "";

// Create a new document
Document doc = store.newDocument(); doc.save();
s += "Created a new document, shared="+doc.isShared()+", share count="+doc.getShareCount()+"\n";
doc.share(true);
s += "Marked the document as shared, shared="+doc.isShared()+", share count="+doc.getShareCount()+"\n";
doc.share(false);
s += "Marked the document as not shared, shared="+doc.isShared()+", share count="+doc.getShareCount()+"\n";
s += "\n";

// Clean-up...
doc.deleteDocument();

_formatText("{0}",s);
