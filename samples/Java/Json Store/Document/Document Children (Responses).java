Store store1 = session.getDatabase("playground").getStore("temp");
Store store2 = session.getDatabase("playground").getStore("temp2");
String s="";

// Create a few documents
Function.e2<Document,JsonException,Store,String> createDoc = new Function.e2<Document,JsonException,Store,String>() {
  public Document call(Store store, String unid) throws JsonException {
  	Document doc = store.loadDocument(unid,Store.DOCUMENT_CREATE);
  	if(doc.isNewDocument()) {
		doc.setJsonString("{field:'My field value'}");
    	doc.save();
    }
  	return doc;
  }
};
Document docParent = createDoc.call(store1, "PGParent"); 
Document docChild1 = createDoc.call(store1, "PGChild1");
Document docChild2 = createDoc.call(store1, "PGChild2");
Document docChild3 = createDoc.call(store2, "PGChild3");
Document docChild4 = createDoc.call(store2, "PGChild4");

// Make the first doc the parent of all the children
// The document, or the UNID can be used to assign the parent
// A parent must be in the same database than the children
// When the document is in the same store, then only the UNID is stored
// When the document is in a different store, the the UNID+StoreID are stored
// The parent ID is stored in a 'reserved' field within the JSON document
docChild1.setParent(docParent); docChild1.save();
docChild2.setParentUnid(docParent.getUnid()); docChild2.save();
docChild3.setParent(docParent); docChild3.save();
docChild4.setParentUnid(docParent.getUnid()+Document.STORE_UNID_SEP+docParent.getStore().getId()); docChild4.save();


s += ">>> Check parents\n";
s += "docParent: "+docParent.getParentUnid()+"\n";
s += "    children: "+docParent.childrenCount()+"\n";
s += "    children: "+docParent.getChildren()+"\n";
s += "    json: "+docParent.getJsonString()+"\n";
s += "docChild1["+docChild1.getDocId()+"]: "+docChild1.getParentUnid()+"\n";
s += "    json: "+docChild1.getJsonString()+"\n";
s += "docChild2["+docChild2.getDocId()+"]: "+docChild2.getParentUnid()+"\n";
s += "    json: "+docChild2.getJsonString()+"\n";
s += "docChild3["+docChild3.getDocId()+"]: "+docChild3.getParentUnid()+"\n";
s += "    json: "+docChild3.getJsonString()+"\n";
s += "docChild4["+docChild4.getDocId()+"]: "+docChild4.getParentUnid()+"\n";
s += "    json: "+docChild4.getJsonString()+"\n";

_formatText("{0}",s);