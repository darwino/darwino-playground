Store store = session.getDatabase("playground").getStore("pinball");

Document doc = store.loadDocument("1000");
String s = doc.getJsonString(false);

_formatText("{0}",s);
                      