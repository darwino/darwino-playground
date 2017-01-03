Store store = session.getDatabase("playground").getStore("pinball");
Document doc = store.loadDocument("1000");
String s = "";

Function.e1<String,JsonException,Document> dump = new Function.e1<String,JsonException,Document>() {
  public String call(Document doc) throws JsonException {
    String s = "";
	Attachment[] att = doc.getAttachments();
	for(int i=0; i<att.length; i++) {
    	Attachment a = att[i];
    	s += "  #"+i+": "+a.getName()+"\n";
    	s += "    Length: "+a.getLength()+"bytes\n";
    	s += "    Mime Type: "+a.getMimeType()+"\n";
    	s += "    Last Modification Date: "+a.getLastModificationDate()+"\n";
    	s += "    eTag: "+a.getETag()+"\n";
    	s += "    Sequence Id: "+a.getSeqId()+"\n";
    }
    return s;
  } 
};

// Delete this attachment if it was created earlier
if(doc.attachmentExists("DarwinoIcon.png")) {
	doc.getAttachment("DarwinoIcon.png").deleteAttachment();
    doc.save();
}

// Read the existing attachments to this document
s += ">> Read document attachments\n";
s += "Count: "+doc.getAttachmentCount()+"\n";
s += "Exists 'picture.png': "+doc.attachmentExists("picture.png")+"\n";
s += dump.call(doc);

// Create a new attachment to this document
s += "\n";
s += ">> Create attachment\n";
String img = "iVBORw0KGgoAAAANSUhEUgAAAA8AAAAQCAMAAAD+iNU2AAAAIGNIUk0AAHolAACAgwAA+f8AAIDpAAB1MAAA6mAAADqYAAAXb5JfxUYAAAMAUExURQAAAACg4ASh4Aii4Aqj4RKm4hWn4hio4hqp4xuq4x2q4yGs5CSt5Cuw5TCx5Tq150C351O+6lbA6ljA6m3I7XLK7XTL7nfM7gAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA71Y1UAAAEAdFJOU////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////wBT9wclAAAACXBIWXMAAC4iAAAuIgGq4t2SAAAAGHRFWHRTb2Z0d2FyZQBwYWludC5uZXQgNC4wLjVlhTJlAAAAbElEQVQYV23O2w4CMQgEUGddL1ur697//0vHAUqMiTwUDqEtJ/7Gfw8Abkd6JosaeDaj03FX4+XuVamxepJtFJs6FW95d9sEH5C3r4s55o0XjGlp8uT/VcYrJbyQZ+NVtTmudFozrW1aTkeQH7GTlDOPqLKjAAAAAElFTkSuQmCC";
Base64Content ct = new Base64Content(img,"image/png");
Attachment na = doc.createAttachment("DarwinoIcon.png",ct);
doc.save();
s += dump.call(doc);
s += "\n";

Document doc2 = store.loadDocument("1000");
String b64 = doc2.getAttachment("DarwinoIcon.png").readAsBase64();
s += "New Image: "+b64.substring(0,40)+"...\n";

s += "\n";

_formatText("{0}",s);

_out("<img src='{0}'/>",doc.getAttachmentUrl("picture.png"));
_out("<img src='{0}'/>",doc.getAttachmentUrl("DarwinoIcon.png"));
_out("<img src='{0}'/>","data:image/png;base64,"+b64);
