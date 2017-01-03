Store store = session.getDatabase("playground").getStore("pinball");
Document doc = store.loadDocument("1000");

String s = "";
s += "Database: "+doc.getDatabase().getId()+"\n";
s += "Store: "+doc.getStore().getId()+"\n";
s += "\n";
s += "Unid: "+doc.getUnid()+"\n";
s += "Id: "+doc.getDocId()+"\n";
s += "Parent Unid: "+doc.getParentUnid()+"\n";
s += "Sync Master Unid: "+doc.getSyncMasterUnid()+"\n";
s += "Replica Id: "+doc.getReplicaId()+"\n";
s += "\n";
s += "Creation Date: "+doc.getCreationDate()+"\n";
s += "Creation User: "+doc.getCreationUser()+"\n";
s += "Last Modification Date: "+doc.getLastModificationDate()+"\n";
s += "Last Modification User: "+doc.getLastModificationUser()+"\n";
s += "eTag: "+doc.getETag()+"\n";
s += "\n";
s += "Sequence Id: "+doc.getSeqId()+"\n";
s += "Update Id: "+doc.getUpdateId()+"\n";
s += "Changes: "+doc.getChanges()+"\n";

s += "\n";
s += "isNewDocument(): "+doc.isNewDocument()+"\n";
s += "childrenCount(): "+doc.childrenCount()+"\n";
s += "getChildren(): "+StringUtil.toString(doc.getChildren())+"\n";
s += "getSyncSlaves(): "+StringUtil.toString(doc.getSyncSlaves())+"\n";

_formatText("{0}",s);
