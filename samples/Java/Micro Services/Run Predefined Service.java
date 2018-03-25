String query = "Hello World.ds";

JsonMicroServiceSession svcSession = new JsonMicroServiceSession(Platform.getService(JsonMicroServiceFactory.class));
Object result = svcSession.executePredefined(query);

Object json = result.toString();

_formatText("Result={0}",json);
