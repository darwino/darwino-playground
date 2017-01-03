PreferencesService prefService = Platform.getService(PreferencesService.class);

Preferences p = prefService.getPreferences("user1","pref1");

// This default service has pref1 & pref2 defined, but not pref3
_formatText("  pref1: {0}",p.get("pref1"));
_formatText("  pref2: {0}",p.get("pref2"));
_formatText("  pref3: {0}",p.get("pref3"));
