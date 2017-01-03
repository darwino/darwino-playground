// A JSON parser is available through a Factory
JsonFactory f = JsonJavaFactory.instance;

JsonObject jo = (JsonObject)f.fromJson("{a:11, b:12}");
JsonArray ja = (JsonArray)f.fromJson("[21,22]");

_formatText("JSON Object: {0}",jo.toJson());
_formatText("JSON Array: {0}",ja.toJson());


// The JSON parser is more tolerating than the JSON specs as
//  - it supports object properties not in "
//  - it supports embedded comments
// More options are available from the factory
JsonObject p1 = (JsonObject)f.fromJson(
  "{a:11, 'b':12, \"c\":13}");
JsonObject p2 = (JsonObject)f.fromJson(
  "{a:11, /* multi*/ 'b':12 // mono,\n \"c\":13}");

_formatText("p1: {0}",p1.toJson());
_formatText("p2: {0}",p1.toJson());