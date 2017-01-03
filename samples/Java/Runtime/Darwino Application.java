// A single Darwino application is available per application
//   The actual implementation and the capability depend on the runtime platform!
// A manifest object describes the application
DarwinoApplication app = DarwinoApplication.get();
DarwinoManifest mf = app.getManifest();

_formatText("Label: {0}",mf.getLabel());
_formatText("Description: {0}",mf.getDescription());
_formatText("Main Database: {0}",mf.getMainDatabase());
