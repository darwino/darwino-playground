String brand = "Bally";

String sql = "SELECT P.$.name name, P.$.brand brand"
	+" FROM pinball P"
	+" WHERE LOWER(P.$.brand)=LOWER(:brand)";

final StringBuilder columnNames = new StringBuilder();
final StringBuilder pinballs = new StringBuilder();
int count = session.openJsqlCursor().database("playground")
			.query(sql).param("brand",brand)
			.find(new com.darwino.jsonstore.jsql.JsqlHandlerWithMetadata() {
				public void columns(String[] columns) throws JsonException {
					for(int i=0; i<columns.length; i++) {
						if(i>0) columnNames.append(",");
						columnNames.append(columns[i]);
					}
				}
				public boolean handle(com.darwino.jsonstore.jsql.JsqlEntry entry) throws JsonException {
					pinballs.append("\n"+entry.getColumn("name"));
					return true;
				}
			});

_formatText("Pinballs Count:{0}, Columns:{1}{2}",count,columnNames,pinballs);
