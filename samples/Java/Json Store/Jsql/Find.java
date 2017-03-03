String brand = "Bally";

String sql = "SELECT P.$.name name"
	+" FROM pinball P"
	+" WHERE LOWER(P.$.brand)=LOWER(:brand)";

final StringBuilder pinballs = new StringBuilder();
int count = session.openJsqlCursor().database("playground")
			.query(sql).param("brand",brand)
			.find(new JsqlHandler() {
				public boolean handle(JsqlEntry entry) throws JsonException {
					pinballs.append("\n"+entry.getColumn("name"));
					return true;
				}
			});

_formatText("Pinballs Count:{0}{1}",count,pinballs);
