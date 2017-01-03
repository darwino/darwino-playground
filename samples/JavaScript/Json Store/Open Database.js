session.getDatabase("playground").then(function(database) {
	darwino.Utils.setText("content","Database: {0}, label: {1}",
                      database.getId(),
                      database.getLabel());
});
