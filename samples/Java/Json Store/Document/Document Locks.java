Database database = session.getDatabase("playground");
Store store = database.getStore("pinball");
String s="";

// The database should be enabled for locks
s += ">> Check if the database is enabled for document locking\n";
s += "Database lock enabled: "+database.isDocumentLockEnabled()+"\n";

// Functions
// All of this dails if the functions are executed by anonymous
try {
	Document doc = store.loadDocument("1000");

	// Lock a document
	database.lockDocument(doc.getDocId()); // default delay, 30 mins
	database.lockDocument(doc.getDocId(),10*60*1000); // 10 mins, expressed in ms

	// Read the lock
	database.getDocumentLockUser(doc.getDocId());

	// Unlock a document
	database.unlockDocument(doc.getDocId());

	// Load and lock a document, or fail
	Document docLock = store.loadDocument("1000",Document.DOCUMENT_LOCK);

	// Save and keep the document lock
	docLock.save(Document.SAVE_KEEPLOCK);

	// Save the document and release the lock
	docLock.save();

} catch(Exception e) {
	s += ">> Exception\n";
	s += e.toString();
}

_formatText("{0}",s);
