// This is done through a MailService
// WARN: this is a example that do not send real email!
MailService mailService = Platform.getService(MailService.class);

MailMessage m = new MailMessage();
m.setFrom("sender@darwino.com");
m.setTo("darwinounit2@gmail.com");
m.setSubject("EMail with Attachment");
m.setContentText("This email with an attachment");

// Attachments are using the Content object
MailMimePart at1 = new MailMimePart();
at1.setContent(new TextContent("This attachement is just made of text"));
at1.setName("Attachment1.txt");
m.addMimePart(at1);

mailService.send(m);

_formatText("Email sent!");