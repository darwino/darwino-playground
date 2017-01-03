// Use whatever Application object you have here
// As the Playground is runninon top of IBM Domino, it creates a DarwinoDominoApplication
String s = "";
LocalJsonDBServer server = DarwinoDominoApplication.get().getLocalJsonDBServer();

// Create a new Session
User user = new UserImpl("james@007.com","James Bond",null,null);
Session session2 = server.createSession(user,null);
s += "Current user session, user="+session2.getUser().getCn()+"<br/>";

out.println(s);	
