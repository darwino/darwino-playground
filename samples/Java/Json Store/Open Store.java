Store store = session.getDatabase("playground").getStore("owners");

_formatText("Store: {0}, label: {1}",
            store.getId(),
            store.getLabel());
