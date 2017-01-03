// The current user is available from the Darwino Context
User u = DarwinoContext.get().getUser();
_formatText("User: {0}",u.toString());


_formatText("  Dn: {0}",u.getDn());
_formatText("  Cn: {0}",u.getCn());
_formatText("  EMail: {0}",u.getAttribute("email"));
_formatText("  Groups: {0}",u.getGroups());
_formatText("  Roles: {0}",u.getRoles());

