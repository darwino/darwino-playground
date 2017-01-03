Database database = session.getDatabase("playground");

_formatText("Database: {0}, label: {1}",
            database.getId(),
            database.getLabel());
