package com.grabm.util;

import java.io.UnsupportedEncodingException;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.apache.log4j.Logger;

/**
 *
 * @author Sachith Dickwella
 */
public class EmailHandler implements GrabMConstant {

    private final Logger logger = Logger.getLogger(EmailHandler.class);

    private static final String SMTP_HOST = "mail.smtp.host";
    private static final String SMTP_SOCKETFACTORY_PORT = "mail.smtp.socketFactory.port";
    private static final String SMTP_SOCKETFACTORY_CLASS = "smtp.socketFactory.class";
    private static final String SMTP_AUTH = "mail.smtp.auth";
    private static final String SMTP_PORT = "mail.smtp.port";
    private static final String SMTP_SSL_ENABLED = "mail.smtp.ssl.enable";

    private static final String APP_USER = "russelle.davis90";
    private static final String APP_PASS = "ymnxktrturpqcgil";

    private final Properties properties;

    public EmailHandler() {
        properties = new Properties();
        properties.put(SMTP_HOST, "smtp.gmail.com");
        properties.put(SMTP_SOCKETFACTORY_PORT, "465");
        properties.put(SMTP_SOCKETFACTORY_CLASS, "javax.net.ssl.SSLSocketFactory");
        properties.put(SMTP_SSL_ENABLED, "true");
        properties.put(SMTP_AUTH, "true");
        properties.put(SMTP_PORT, "465");
    }

    public void sendMail(String emailAddress, String subject, String firstName, String pin) {
        Session session = Session.getDefaultInstance(properties,
                new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(APP_USER, APP_PASS);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("noreply@grabm.com", "GrabM Technical Team"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailAddress));
            message.setSubject(subject);
            message.setContent(getPINContent(firstName, pin), "text/html");

            Transport.send(message);

        } catch (UnsupportedEncodingException | MessagingException ex) {
            logger.error(EMAIL_GENERATION_FAILED, ex);
        }
    }

    private String getPINContent(String firstName, String pin) {
        return "<!DOCTYPE html>\n"
                + "<html>\n"
                + "    <head>\n"
                + "        <title></title>\n"
                + "        <meta charset=\"UTF-8\">\n"
                + "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                + "    </head>\n"
                + "    <body>\n"
                + "        <div style=\"padding: 20px; background: #39a3d8;\">\n"
                + "            <div style=\"background: #ffffff; padding: 30px; font-family: 'Tahoma'\">\n"
                + "                <div style=\"padding-bottom: 20px;\">\n"
                + "                    <img src=\"http://www.laebuy.com/images/img/GrabMLogo.png\" style=\"width: 105px;\" alt=\"tech-logo\"/>\n"
                + "                </div>\n"
                + "                <div style=\"font-size: 18px; text-transform: uppercase; color: #888888; padding-bottom: 20px\">\n"
                + "                    Congratulations! Your membership number has been verified. You're step away from the login completion.\n"
                + "                </div>\n"
                + "                <div style=\"color: #888888; font-size: 30px; font-family: 'Segoe UI'\">Hi " + firstName + ",</div>\n"
                + "                <div style=\"color: #888888; font-size: 14px;\"><br/>\n"
                + "                    Use below personal identification number to verify your identity and continue your login procedure on mobile.\n"
                + "                    <p>PIN:&nbsp;&nbsp;<strong>" + pin + "</strong></p>\n"
                + "                    <div style=\"font-size: 11px; color: #ff3333;\">(Please note this PIN expires in 24 hours and then you'll have to request new PIN)</div>\n"
                + "                </div>     \n"
                + "                <br/>\n"
                + "                <div style=\"font-size: 14px;color: #888888;\">Thank you,<br/>Crewber Technical Team</div>\n"
                + "            </div>\n"
                + "        </div>\n"
                + "    </body>\n"
                + "</html>";
    }
}
