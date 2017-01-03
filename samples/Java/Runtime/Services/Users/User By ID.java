// Users are available through the user service
UserService us = Platform.getService(UserService.class);
String dn = "cn=al mass,o=darwinoplayground";
User u = us.findUser(dn);

if(u!=null) {  
	_formatText("User: {0}",u.toString());

	_formatText("  Dn: {0}",u.getDn());
	_formatText("  Cn: {0}",u.getCn());
	_formatText("  EMail: {0}",u.getAttribute("email"));
	_formatText("  Groups: {0}",u.getGroups());
	_formatText("  Roles: {0}",u.getRoles());
} else {
	_formatText("  Cannot find user with DN:{0}",dn);
}  
