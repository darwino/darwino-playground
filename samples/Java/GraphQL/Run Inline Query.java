String query = 
"{"
+"	doc: Document(unid:\"1000\", database:\"playground\", store:\"pinball\") {"
+"		_id,"
+"		_unid,"
+"		_cdate,"
+"		_cuser,"
+"		_mdate,"
+"		_muser,"
+"		name: string(path:\"name\"),"
+"		manufacturer: string(path:\"manufacturer\"),"
+"		released: number(path:\"released\"),"
+"		players: long(path:\"players\"),"
+"		flippers: int(path:\"flippers\")"
+"	}"
+"}";

GraphQLSession gqlSession = Platform.getService(GraphQLSessionFactory.class).createSession();
Object result = gqlSession.execute(query);

Object json = result.toString();

_formatText("Result={0}",json);
