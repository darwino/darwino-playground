session.setAsync(false);

var model = "Revenge From Mars";

var sql = "SELECT P.$.brand brand"
	+" FROM pinball P"
	+" WHERE LOWER(P.$.name)=LOWER(:model)";

var object = session.openJsqlCursor().database("playground")
			.query(sql).param("model",model).findOneJson();

var brand = object["brand"];	

darwino.Utils.setText("content","Brand={0}",brand);
