String query = "pinballs/List Manufacturers";

JsonArray json = (JsonArray)session.openJsqlCursor().load(query)
			.database("playground").findJson();

_formatText("Result={0}",json);