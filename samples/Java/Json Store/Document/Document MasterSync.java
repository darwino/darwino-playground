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
Document docMaster = createDoc.call(store1, "PGMaster"); 
Document docSlave1 = createDoc.call(store1, "PGSlave1");
Document docSlave2 = createDoc.call(store1, "PGSlave2");
Document docSlave3 = createDoc.call(store2, "PGSlave3");
Document docSlave4 = createDoc.call(store2, "PGSlave4");

// Make the first doc the master of all the other slave documents
// The document, or the UNID can be used to assign the master sync
// A master sync must be in the same database than the slaves
// When the document is in the same store, then only the UNID is stored
// When the document is in a different store, the the UNID+StoreID are stored
// The parent ID is stored in a 'reserved' field within the JSON document
docSlave1.setSyncMaster(docMaster); docSlave1.save();
docSlave2.setSyncMasterUnid(docMaster.getUnid()); docSlave2.save();
docSlave3.setSyncMaster(docMaster); docSlave3.save();
docSlave4.setSyncMasterUnid(docMaster.getUnid()+Document.STORE_UNID_SEP+docMaster.getStore().getId()); docSlave4.save();


s += ">>> Check Master/Slaves\n";
s += "docMaster: "+docMaster.getParentUnid()+"\n";
s += "    slaves: "+docMaster.getSyncSlaves()+"\n";
s += "    json: "+docMaster.getJsonString()+"\n";
s += "docSlave1["+docSlave1.getDocId()+"]: "+docSlave1.getSyncMasterUnid()+"\n";
s += "    json: "+docSlave1.getJsonString()+"\n";
s += "docSlave2["+docSlave2.getDocId()+"]: "+docSlave2.getSyncMasterUnid()+"\n";
s += "    json: "+docSlave2.getJsonString()+"\n";
s += "docSlave3["+docSlave3.getDocId()+"]: "+docSlave3.getSyncMasterUnid()+"\n";
s += "    json: "+docSlave3.getJsonString()+"\n";
s += "docSlave4["+docSlave4.getDocId()+"]: "+docSlave4.getSyncMasterUnid()+"\n";
s += "    json: "+docSlave4.getJsonString()+"\n";

_formatText("{0}",s);