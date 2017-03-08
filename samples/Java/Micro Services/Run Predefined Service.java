String query = "Hello World.ds";

JsonMicroServiceSession svcSession = Platform.getService(JsonMicroServiceSessionFactory.class).createSession(null);
Object result = svcSession.executePredefined(query);

Object json = result.toString();

_formatText("Result={0}",json);
