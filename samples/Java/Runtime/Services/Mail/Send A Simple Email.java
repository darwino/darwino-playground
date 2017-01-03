// This is done through a MailService
// WARN: this is a example that do not send real email!
MailService mailService = Platform.getService(MailService.class);

MailMessage m = new MailMessage();
m.setFrom("playground@darwino.com");
m.setTo("darwinounit2@gmail.com");
m.setSubject("Simple email");
m.setContentText("This email is a simple one");
mailService.send(m);

_formatText("Email sent!");