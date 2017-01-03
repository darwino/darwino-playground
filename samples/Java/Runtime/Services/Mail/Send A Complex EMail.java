// This is done through a MailService
// WARN: this is a example that do not send real email!
MailService mailService = Platform.getService(MailService.class);

MailMessage m = new MailMessage();
m.setFrom("sender@darwino.com");
m.setTo("darwinounit2@gmail.com");
m.setSubject("EMail with Attachment");
m.setContentText("This email with an attachment");

// HTML part
MailMimePart ht = new MailMimePart();
ht.setContent(new TextContent("Alternate <b>HTML</b> email representation",TextContent.UTF_ENCODING,HttpBase.MIME_HTML));

// Text attachment
MailMimePart at1 = new MailMimePart();
at1.setContent(new TextContent("This attachement is just made of text"));
at1.setName("Attachment.txt");
m.addMimePart(at1);

// HTML attachment
MailMimePart at2 = new MailMimePart();
at2.setContent(new TextContent("This one is <b>HTML</b>",TextContent.UTF_ENCODING,HttpBase.MIME_HTML));
at2.setName("Attachment.html");
m.addMimePart(at2);

// Image attachment
MailMimePart at3 = new MailMimePart();
String IMAGE = "iVBORw0KGgoAAAANSUhEUgAAAA8AAAAQCAMAAAD+iNU2AAAAIGNIUk0AAHolAACAgwAA+f8AAIDpAAB1MAAA6mAAADqYAAAXb5JfxUYAAAMAUExURQAAAACg4ASh4Aii4Aqj4RKm4hWn4hio4hqp4xuq4x2q4yGs5CSt5Cuw5TCx5Tq150C351O+6lbA6ljA6m3I7XLK7XTL7nfM7gAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA71Y1UAAAEAdFJOU////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////wBT9wclAAAACXBIWXMAAC4iAAAuIgGq4t2SAAAAGHRFWHRTb2Z0d2FyZQBwYWludC5uZXQgNC4wLjVlhTJlAAAAbElEQVQYV23O2w4CMQgEUGddL1ur697//0vHAUqMiTwUDqEtJ/7Gfw8Abkd6JosaeDaj03FX4+XuVamxepJtFJs6FW95d9sEH5C3r4s55o0XjGlp8uT/VcYrJbyQZ+NVtTmudFozrW1aTkeQH7GTlDOPqLKjAAAAAElFTkSuQmCC";
at3.setContent(new Base64Content(IMAGE,HttpBase.MIME_IMAGE_PNG));
at3.setName("Attachment.png");
m.addMimePart(at3);

mailService.send(m);

_formatText("Email sent!");