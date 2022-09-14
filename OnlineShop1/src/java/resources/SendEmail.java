/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author VINH
 */
public class SendEmail {

    public boolean sendEmailActivation(String reciever, String fullname) {

        boolean check = false;
        final String username = "";
        final String password = "";

        // Setup mail server
        Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        // Get the default Session object.
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // Create a default MimeMessage object.
            Message message = new MimeMessage(session);
            // Set From: header field of the header.
            message.setFrom(new InternetAddress("jb20225@gmail.com"));
            // Set To: header field of the header.
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(reciever));
            // Set Subject: header field
            message.setSubject("Password for your account !");
            // Send the actual HTML message, as big as you like
            String messageActivation = "Hi " + fullname + ", your username"
                    + " to login to our website is " + reciever + " and "
                    + "your password is 123 .";
            message.setText(messageActivation);

            Transport.send(message);

            System.out.println("Done");

            check = true;

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        return check;
    }
}
