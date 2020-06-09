package main.java.utils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class JavaMailUtils {
    public static void sendMail(String recipient, String subject, String msg) throws MessagingException {
        System.out.println("Sending email!");
        Properties properties = new Properties();

        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        String accountEmail = "parkingapplication3@gmail.com";
        String password = "ParkingApplication16";

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(accountEmail, password);
            }
        });

        Message message = prepareMessage(session, accountEmail, recipient, subject, msg);

        Transport.send(message);
        System.out.println("Email sent!");
    }

    private static Message prepareMessage(Session session, String accountEmail, String recipient, String subject, String msg) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(accountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject(subject);
            message.setText(msg);

            return message;
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        return null;
    }
}
