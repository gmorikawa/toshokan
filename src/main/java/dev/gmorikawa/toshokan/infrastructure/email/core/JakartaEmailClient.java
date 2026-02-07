package dev.gmorikawa.toshokan.infrastructure.email.core;

import java.util.Properties;

import dev.gmorikawa.toshokan.core.notification.email.Email;
import dev.gmorikawa.toshokan.core.notification.email.contract.EmailClient;
import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Multipart;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;

public class JakartaEmailClient implements EmailClient {
    private final String host;
    private final int port;
    private final String username;
    private final String password;

    public JakartaEmailClient(
        String host,
        int port,
        String username,
        String password
    ) {
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
    }

    @Override
    public <Data> boolean send(Email<Data> email) {
        Properties prop = new Properties();
        prop.put("mail.smtp.auth", true);
        prop.put("mail.smtp.starttls.enable", true);
        prop.put("mail.smtp.host", host);
        prop.put("mail.smtp.port", String.valueOf(port));
        prop.put("mail.smtp.ssl.trust", host);

        Session session = createSession(prop, username, password);

        Message message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(username));
            message.setRecipients(
                Message.RecipientType.TO,
                InternetAddress.parse(email.getRecipient().toString())
            );
            message.setSubject(email.getSubject());

            String msg = "This is my first email using JavaMailer";

            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setContent(msg, "text/html; charset=utf-8");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);

            message.setContent(multipart);

            Transport.send(message);
            return true;
        } catch (MessagingException e) {
            return false;
        }
    }

    private static Session createSession(
        Properties prop,
        String username,
        String password
    ) {
        return Session.getInstance(
            prop,
            new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            }
        );
    }

}
