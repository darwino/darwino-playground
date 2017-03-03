session.setAsync(false);

var brand = "Bally";

var sql = "SELECT P.$.name name"
	+" FROM pinball P"
	+" WHERE LOWER(P.$.brand)=LOWER(:brand)";

var pinballs = "";
var count = session.openJsqlCursor().database("playground")
			.query(sql).param("brand",brand)
			.find(function(entry) {
				pinballs += "\n"+entry.getColumn("name");
				return true;
			});

darwino.Utils.setText("content","Pinballs Count:{0}{1}",count,pinballs);