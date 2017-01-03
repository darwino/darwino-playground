// Note the $darwino-jstore is specific to the playground, as it extracts the actual URL from the current environment
String s = "";
HttpJsonDBServer server = new HttpJsonDBServerImpl("$darwino-jstore");

// Create a new Session
// Note that this will fail as the user/password is invalid!
// This is provided for infomration only
Session session2 = server.createBasicSession("Al Mass","floflo",null);
s += "Current user session, user="+session2.getUser().getCn()+"<br/>";

out.println(s);	
