var social_baseUrl = "$darwino-social";
var userService = darwino.services.createUserService(social_baseUrl+"/users");
var s = ""

var url = userService.getUserPhotoUrl('cn=al mass,o=darwinoplayground')
s += url+"\n"

document.getElementById("pic1").src = url

darwino.Utils.setText("content","{0}",s);
