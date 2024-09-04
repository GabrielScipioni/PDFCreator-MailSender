package Gabriel.Dev.com;

import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class EmailSender {

    private String host;
    private String username;
    private String password;

    public EmailSender(String username, String password) {
        this.host = "smtp.gmail.com";
        this.username = username;
        this.password = password;
    }

    public void sendEmail(String to, String subject, String body, String pdfPath) throws MessagingException, IOException {
        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        message.setSubject(subject);

        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setText(body);

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);

        // Crear el archivo adjunto
        MimeBodyPart attachmentPart = new MimeBodyPart();
        File file = new File(pdfPath);
        DataSource source = new FileDataSource(file);
        attachmentPart.setDataHandler(new DataHandler(source));
        attachmentPart.setFileName(file.getName());

        multipart.addBodyPart(attachmentPart);
        message.setContent(multipart);

        Transport.send(message);
    }
}
