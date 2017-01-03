var s = "";
var app = darwino.jstore.createRemoteApplication("$darwino-jstore");

// There is a session that is available in the playground, and usable right away.
// This makes it easier for the playground samples
s += "Pre-created session, user="+session.getUser().getCommonName()+"\n";


// Outside of the playground, here is how a session using the currently authenticated user should be created:
var session2 = app.createSession();
s += "Current user session, user="+session2.getUser().getCommonName()+"\n";

// An a session can also be created using a user/password but only when dealing with a different server
// so the basic authentication parameters are passed to the request
// In case of the same server, then the session authentication is used and the parameters are ignored
var session3 = app.createBasicSession("amass@triloggroup.com","password");
s += "Al Mass session, user="+session3.getUser().getCommonName()+"\n";


darwino.Utils.setText("content","{0}",s);
