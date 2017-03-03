String model = "Revenge From Mars";

String sql = "SELECT P.$.brand brand"
	+" FROM pinball P"
	+" WHERE LOWER(P.$.name)=LOWER(:model)";

JsonObject json = (JsonObject)session.openJsqlCursor().database("playground")
			.query(sql).param("model",model).findOneJson();

String brand = json.getString("brand");	

_formatText("Brand={0}",brand);