// All the logging function are available from the platform object
// to make it platform independant

// General logging
Platform.log("Logging {0} inconditionally", "My Message");
Platform.log(new Exception(),"Logging an Exception, {0}", "Here it is");

// Access throught the log service, which allows group logging
// It 
Logger l = Platform.logManager("com.darwino.mygroup");
l.i("This is informational");
l.w("This is a warning");
l.e("This is an error");
l.t("This is a trace");
l.d("This is debug");

_formatText("All the logs have been written to the server console");
