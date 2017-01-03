PreferencesService prefService = Platform.getService(PreferencesService.class);

prefService.deletePreferences("user1","myprefs");

// Preference is set locally in the object
Preferences p = prefService.getPreferences("user1","myprefs");
p.set("custom","value 1");
_formatText("  custom: {0}",p.get("custom"));

// Does not appear because it is not yet saved
Preferences p2 = prefService.getPreferences("user1","myprefs");
_formatText("  custom: {0}",p2.get("custom"));

// Save it, and it is now in the DB
p.save();
Preferences p3 = prefService.getPreferences("user1","myprefs");
_formatText("  custom: {0}",p3.get("custom"));
