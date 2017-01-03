Store store = session.getDatabase("playground").getStore("tempsocial");
String s = "";

// Create a new document mark it read
Document doc = store.newDocument(); doc.save();
s += "Created a new document, read="+doc.isRead()+"\n";
doc.markRead(false);
s += "Marked the document as unread, read="+doc.isRead()+"\n";
doc.markRead(true);
s += "Marked the document as read, read="+doc.isRead()+"\n";
s += "\n";

// Create a new document and do not mark it read
Document doc1 = store.newDocument(); doc1.save(Document.SAVE_NOREAD);
s += "Created a new document with no read mark, read="+doc1.isRead()+"\n";

// Load the document -> should be marked as read now
doc.markRead(false);
Document doc2 = store.loadDocument(doc.getUnid(),Store.DOCUMENT_NOREADMARK);
s += "Read the document with no read mark, read="+doc.isRead()+"\n";


// Load the document -> should be marked as read now
doc2 = store.loadDocument(doc.getUnid());
s += "Read the document, read="+doc.isRead()+"\n";


// Clean-up...
doc.deleteDocument(); doc1.deleteDocument();

_formatText("{0}",s);
