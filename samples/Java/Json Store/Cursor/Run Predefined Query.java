Cursor c = session.getDatabase("playground").getStore("pinball").openCursor().load("Pinball/Brands");
final StringBuilder s = new StringBuilder(); 
c.find(new CursorHandler() {
  public boolean handle(CursorEntry e) throws JsonException {  
  	s.append("  "+e.getString("name")+"\n");
    return true;
  }
});  

_formatText("{0}",s.toString());