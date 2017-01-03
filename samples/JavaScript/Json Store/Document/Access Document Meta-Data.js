session.setAsync(false);

var store = session.getDatabase("playground").getStore("pinball");
var doc = store.loadDocument("1000");

var s = "";
s += "Database: "+doc.getDatabase().getId()+"\n"
s += "Store: "+doc.getStore().getId()+"\n"
s += "\n"
s += "Unid: "+doc.getUnid()+"\n"
s += "Id: "+doc.getDocId()+"\n"
s += "Parent Unid: "+doc.getParentUnid()+"\n"
s += "Sync Master Unid: "+doc.getSyncMasterUnid()+"\n"
s += "Replica Id: "+doc.getReplicaId()+"\n"
s += "\n"
s += "Creation Date: "+doc.getCreationDate()+"\n"
s += "Creation User: "+doc.getCreationUser()+"\n"
s += "Last Modification Date: "+doc.getLastModificationDate()+"\n"
s += "Last Modification User: "+doc.getLastModificationUser()+"\n"
s += "eTag: "+doc.getETag()+"\n"
s += "\n"
s += "Sequence Id: "+doc.getSeqId()+"\n"
s += "Update Id: "+doc.getUpdateId()+"\n"
s += "Changes: "+doc.getChanges()+"\n"

s += "\n"
s += "isNewDocument(): "+doc.isNewDocument()+"\n"
s += "childrenCount(): "+doc.childrenCount()+"\n"
s += "getChildren(): "+doc.getChildren()+"\n"
s += "getSyncSlaves(): "+doc.getSyncSlaves()+"\n"

darwino.Utils.setText("content","{0}",s);
