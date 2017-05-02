GQuery q = new GQuery("example");
	q.field(new GField("Document")
       	.attribute("unid", "1000")
       	.attribute("database", "playground")
       	.attribute("store", "pinball")
       	.field("_id")
       	.field("_unid")
       	.field("_cdate")
       	.field("_cuser")
       	.field("_mdate")
       	.field("_muser")
       	.stringField("name","name")
       	.stringField("manufacturer","manufacturer")
       	.numberField("released","released")
       	.longField("players","players")
       	.intField("flippers","flippers")
);

String query = q.build();

GraphQLSession gqlSession = Platform.getService(GraphQLSessionFactory.class).createSession();

Object result = gqlSession.execute(query);

Object json = result.toString();

_formatText("Result={0}",json);
