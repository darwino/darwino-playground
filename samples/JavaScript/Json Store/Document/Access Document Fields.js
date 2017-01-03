session.setAsync(false);

var store = session.getDatabase("playground").getStore("pinball");
var doc = store.newDocument();
var s = "";

doc.setJsonString("{a:1, b:'bstring', c:true, d:'2015-06-17T14:34:05-04:00',e:{a:2,b:'b2',c:{a:3}}}");

s += ">> Access fields, using getters\n"
s += "a: "+doc.get("a")+"\n"
s += "a as int: "+doc.getInt("a")+"\n"
s += "a as long: "+doc.getLong("a")+"\n"
s += "a as double: "+doc.getDouble("a")+"\n"
s += "b as string: "+doc.getString("b")+"\n"
s += "c as boolean: "+doc.getBoolean("c")+"\n"
s += "d as date: "+doc.getDate("d")+"\n"

s += "\n"
s += ">> Json Path\n"
s += "$.a: "+doc.jsonPath("$.a")+"\n"
s += "$.e.a: "+doc.jsonPath("$.e.a")+"\n"
s += "$.e.c.a: "+doc.jsonPath("$.e.c.a")+"\n"

s += "\n"
s += ">> Set fields, using setters\n"
doc.set("a",5)
s += "a, set to 5: "+doc.get("a")+"\n"
doc.remove("b")
s += "b, removed: "+doc.get("b")+"\n"

darwino.Utils.setText("content","{0}",s);
