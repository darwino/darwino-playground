var social_baseUrl = "$darwino-social";
var userService = darwino.services.createUserService(social_baseUrl+"/users");
var s = "";

function printUser(m,u) {
	s += m+"\n";
	s += "  Dn="+u.getDn()+"\n";
	s += "  Cn="+u.getCn()+"\n";
	s += "  Email="+u.getAttribute('email')+"\n";
	s += "  Loading="+u.isLoading()+"\n";
	s += "  Exists="+u.exists()+"\n";
	darwino.Utils.setText("content","{0}",s);
}

// GetUser: returns a user
// When the user is not yet loaded, it returns a temporary one with the loading flag set
var usr = userService.getUserByLoginId('amass');
printUser("getUser - inline",usr)

userService.getUser('amass', function(u) {
	printUser("\ngetUser - Function",u)
});
userService.getUser('amass - FAKE', {
  success: function(u) {
		// error...
  },
  failure: function(u) {
    var u = userService.getUser('amass - FAKE');
	printUser("\ngetUser - Function with error",u)
  },
});


// FindUser: returns a Promise
userService.findUser('amass')
	.then(function(u) {
		printUser("\ngetUser - Promise",u)
	});

userService.findUser('amass - FAKE')
	.then(function(u) {
		// error...
    }, function(error) {
        var u = userService.getUser('amass - FAKE');
		printUser("\ngetUser - Promise with error",u)
    });

s += "\n*** End of file\n\n";
