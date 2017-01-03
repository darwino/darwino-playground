// A context obect is created per request (or is a singleton for mobile apps)
// It gives access to the current user and the default JSON store session
DarwinoContext ctx = DarwinoContext.get();

_formatText("User: {0}",ctx.getUser());
_formatText("JsonStore Session: {0}",ctx.getSession()!=null);
