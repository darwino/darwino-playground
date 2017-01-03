// ***********************
// Create A JSON Object in memory and set properties
// The getters/setters can by typed or not
JsonObject jo = new JsonObject();
jo.put("value1",19);
jo.putInt("value2",22);
jo.put("value3","a string");
jo.putString("value4","another one");

_formatText("JSON Object: {0}",jo.toJson());
_formatText("Value1: {0}",jo.get("value1"));
_formatText("Value2, typed: {0}",jo.getInt("value1"));


// ***********************
// Create A JSON Array in memory and set items
// The accessors can by typed or not
JsonArray ja = new JsonArray();
ja.add(19);
ja.addInt(19);
ja.putInt(0,22);
ja.add("a string");
ja.addString("another one");

_formatText("JSON Array: {0}",ja.toJson());
_formatText("Value[0]: {0}",ja.get(0));
_formatText("Value[1], typed: {0}",ja.getInt(1));

