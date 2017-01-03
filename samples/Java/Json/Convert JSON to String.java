// A JSON parser is available through a Factory
JsonFactory f = JsonJavaFactory.instance;
JsonObject jo = (JsonObject)f.fromJson("{a:11, b:12, c: 13}");

// Json objects/arrays have easy to use methods
_formatText("JSON Object, compact: {0}",jo.toJson());
_formatText("JSON Object, pretty: {0}",jo.toJson(false));

// Or this can be done through a factory
_formatText("JSON Object, compact: {0}",f.toJson(jo));
_formatText("JSON Object, pretty: {0}",f.toJson(jo,false));
