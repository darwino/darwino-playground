session.setAsync(false);

var brand = "Bally";

var sql = "SELECT P.$.name name"
	+" FROM pinball P"
	+" WHERE LOWER(P.$.brand)=LOWER(:brand)";

var array = session.openJsqlCursor().database("playground")
			.query(sql).param("brand",brand).findJson();

var pinballs = "";
for(var i=0; i<array.length; i++) {
	pinballs += "\n"+array[i]["name"];
}

darwino.Utils.setText("content","Pinballs={0}",pinballs);
