// A lot of string handling functions are available in StringUtil

// Important: a empty string ("") and null are considered the same
// This can be tested with isEmpty(), while equality is tested with equals()
_formatText("Is empty: {0}, not empty: {1}",StringUtil.isEmpty(null),StringUtil.isNotEmpty(null));
_formatText("Is empty: {0}, not empty: {1}",StringUtil.isEmpty(""),StringUtil.isNotEmpty(""));
_formatText("Is empty: {0}, not empty: {1}",StringUtil.isEmpty("abd"),StringUtil.isNotEmpty("abd"));

_formatText("Is equals: {0}",StringUtil.equals(null,""));

// toString() gives something useful to the developers
_formatText("toString: {0}",StringUtil.toString(new String[]{"a","b","c"}));

// Very fast string splitting is achieved with spliString()
_formatText("Split: {0}",(Object)StringUtil.splitString("a,b,c",','));
