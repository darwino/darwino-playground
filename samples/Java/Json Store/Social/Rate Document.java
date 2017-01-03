Store store = session.getDatabase("playground").getStore("tempsocial");
String s = "";

// Create a new document
Document doc = store.newDocument(); doc.save();

s += "Created a new document, rate="+doc.getRate()+", rate average="+doc.getRateAvg()+"\n";

doc.rate(3);
s += "Set the document rate to 3, rate="+doc.getRate()+", rate average="+doc.getRateAvg()+"\n";

doc.rate(-1);
s += "Reset the document rate (-1), rate="+doc.getRate()+", rate average="+doc.getRateAvg()+"\n";


// Clean-up...
doc.deleteDocument();

_formatText("{0}",s);
