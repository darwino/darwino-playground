String query = "Json Store/Document/Read Document Fields";

GraphQLSession gqlSession = Platform.getService(GraphQLSessionFactory.class).createSession(null);
Object result = gqlSession.executePredefined(query);

Object json = result.toString();

_formatText("Result={0}",json);
