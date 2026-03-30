package com.Adaptix.utilities;

import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.*;

public class EmailUtil {

    public static void sendEmail() {

        Properties prop = new Properties();
        prop.put("mail.smtp.host", TestConfig.server);
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(prop, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(TestConfig.from, TestConfig.password);
            }
        });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(TestConfig.from));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(String.join(",", TestConfig.to)));

            message.setSubject(TestConfig.subject);

            // Body
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(TestConfig.messageBody);

            // Attachment
            MimeBodyPart attachmentPart = new MimeBodyPart();
            attachmentPart.attachFile(TestConfig.attachmentPath);

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            multipart.addBodyPart(attachmentPart);

            message.setContent(multipart);

            Transport.send(message);

            System.out.println("Email Sent Successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}