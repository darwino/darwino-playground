session.setAsync(false);

var store = session.getDatabase("playground").getStore("owners");
var s = "";

s += ">>> Query owners from 'CA', limited to 5\n";
var c = store.openCursor()
			.query({state:'CA'})
			.range(0,5);
c.find(function(e) {
  s += "  "+e.getString("state")+", "+e.getString("firstName")+" "+e.getString("lastName")+"\n";
});  
s += "\n";


s += ">>> Query owners from 'CA', using a field, limited to 5\n";
var c = store.openCursor()
			.query({'@state':'CA'})
			.range(0,5);
c.find(function(e) {
  s += "  "+e.getString("state")+", "+e.getString("firstName")+" "+e.getString("lastName")+"\n";
});  

s += "\n";
s += ">>> Query owners from 'MI' or 'TX', limited to 10\n";
var c = store.openCursor()
			.query({$or: [{state:'MI'},{state:'TX'}]})
			.range(0,10);
c.find(function(e) {
  s += "  "+e.getString("state")+", "+e.getString("firstName")+" "+e.getString("lastName")+"\n";
});  
s += "\n";


darwino.Utils.setText("content","{0}",s);