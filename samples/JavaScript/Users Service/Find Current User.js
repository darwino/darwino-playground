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

// GetCurrentUser: returns a user
// When the user is not yet loaded, it returns a temporary one with the loading flag set
var usr = userService.getCurrentUser();
printUser("getUser - inline",usr)

userService.getCurrentUser(function(u) {
	printUser("\ngetCurrentUser - Function",u)
});
userService.getCurrentUser( {
  success: function(u) {
	printUser("\ngetCurrentUser - Function, no error",u)
  },
  failure: function(u) {
	// no error...
  },
});


// FindCurrentUser: returns a Promise
userService.findCurrentUser()
	.then(function(u) {
		printUser("\nfindCurrentUser - Promise",u)
	});

userService.findCurrentUser()
	.then(function(u) {
		printUser("\nfindCurrentUser - Promise no error",u)
    }, function(error) {
	// no error...
    });

s += "\n*** End of file\n\n";
