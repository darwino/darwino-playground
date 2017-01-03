var social_baseUrl = "$darwino-social";
var userService = darwino.services.createUserService(social_baseUrl+"/users");
var s = ""

userService.query("cn=A*",null,0,3,null).then(function(r) {
	s += darwino.Utils.toJson(r)+"\n";
	darwino.Utils.setText("content","{0}",s);
}, function(error) {
	darwino.Utils.setText("content","{0}",error.toString());
});
