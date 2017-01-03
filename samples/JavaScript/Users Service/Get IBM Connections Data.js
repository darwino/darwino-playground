var social_baseUrl = "$darwino-social";
var userService = darwino.services.createUserService(social_baseUrl+"/users");
var s = ""

function xml(xml) {
	xml = xml.replace(/\r|\n/g, '');
	xml = xml.replace(/(>)\s*(<)(\/*)/g, '$1\r\n$2$3');
    return xml;
}

userService.findUser('cn=al mass,o=darwinoplayground')
.then(function(u) {
	return u.getUserData('connections').getContentAsString('payload')
})
.then(function(d) {
	s += xml(d)+"\n";
	darwino.Utils.setText("content","{0}",s);
})
