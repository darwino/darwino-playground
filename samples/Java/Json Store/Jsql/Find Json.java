String brand = "Bally";

String sql = "SELECT P.$.name name"
	+" FROM pinball P"
	+" WHERE LOWER(P.$.brand)=LOWER(:brand)";

JsonArray array = (JsonArray)session.openJsqlCursor().database("playground")
			.query(sql).param("brand",brand).findJson();

String pinballs = "";
for(int i=0; i<array.size(); i++) {
	pinballs += "\n"+array.getObject(i).getString("name");
}

_formatText("Pinballs={0}",pinballs);
