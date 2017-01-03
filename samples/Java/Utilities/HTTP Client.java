// Darwino provides a convenient HTTP client for calling services
// It has many helper methods, and it uses what the platform provides underneath
String url = "$darwino-jstore";
HttpClient c = ((HttpClientService)Platform.getService(HttpClientService.class))
  					.createHttpClient(url,"Al Mass","floflo");

// Call the information service and interpret the result as JSON
//    <base-url>
Object r = c.getAsJson(StringArray.EMPTY_ARRAY);                      
_formatText("JSON Store information: {0}",r);

// Call the user service and interpret the result as JSON
//    <base-url>/user
Object r2 = c.getAsJson(new String[]{"user"});                      
_formatText("JSON Store User: {0}",r2);

// Call the service that check is a document exists and returns the flag as JSON
//    [context path]/databases/{database_id}/stores/{store_id}/documentexists/{unid}
Object r3 = c.getAsJson(new String[]{
  "databases","playground","stores","pinball","documentexists","1000"});                      
_formatText("Document '1000' exists: {0}",r3);

// Call the service that check is a document exists and returns the flag as JSON
// We check an instance with an instance passed as a query string parameter
//    [context path]/databases/{database_id}/stores/{store_id}/documentexists/{unid}
//    ?[instance=xxxx]
Map<String,Object> p4 = new HashMap<String,Object>();
p4.put("instance","fake-instance-name");
Object r4 = c.getAsJson(new String[]{
  "databases","playground","stores","pinball","documentexists","1000"},p4);                      
_formatText("Document '1000' exists: {0}",r4);
