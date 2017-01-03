session.getDatabase("playground").then(function(database) {
	var store = database.getStore("owners");
	darwino.Utils.setText("content","Store: {0}, label: {1}",
                      store.getId(),
                      store.getLabel());
});

