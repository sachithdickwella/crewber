package com.earcs.grabm.util;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
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
public class Email {

    private final Logger logger = Logger.getLogger(Email.class);

    private static final String SMTP_HOST = "mail.smtp.host";
    private static final String SMTP_SOCKETFACTORY_PORT = "mail.smtp.socketFactory.port";
    private static final String SMTP_SOCKETFACTORY_CLASS = "smtp.socketFactory.class";
    private static final String SMTP_AUTH = "mail.smtp.auth";
    private static final String SMTP_PORT = "mail.smtp.port";
    private static final String SMTP_SSL_ENABLED = "mail.smtp.ssl.enable";

    private static final String APP_USER = "russelle.davis90";
    private static final String APP_PASS = "ymnxktrturpqcgil";

    private final Properties properties;

    public Email() {
        properties = new Properties();
        properties.put(SMTP_HOST, "smtp.gmail.com");
        properties.put(SMTP_SOCKETFACTORY_PORT, "465");
        properties.put(SMTP_SOCKETFACTORY_CLASS, "javax.net.ssl.SSLSocketFactory");
        properties.put(SMTP_SSL_ENABLED, "true");
        properties.put(SMTP_AUTH, "true");
        properties.put(SMTP_PORT, "465");
    }

    public void sendMail(String emailAddress, String subject, String password, String firstName) {
        Session session = Session.getInstance(properties,
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
            message.setContent(getFirstLoginEmail(firstName, password, "http://www.grabm.com/sample/url/is/here/123?noway=false"), "text/html");

            Transport.send(message);

        } catch (UnsupportedEncodingException | MessagingException ex) {
            logger.error(Arrays.asList(ex.getStackTrace()));
        }
    }

    private String getFirstLoginEmail(String firstName, String password, String link) {
        return "<!DOCTYPE html>\n"
                + "<html>\n"
                + "    <head>\n"
                + "        <title></title>\n"
                + "        <meta charset=\"UTF-8\">\n"
                + "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                + "    </head>\n"
                + "    <body>\n"
                + "        <div style=\"padding: 20px; background: #27AE5F;\">\n"
                + "            <div style=\"background: #ffffff; padding: 30px; font-family: 'Tahoma'\">\n"
                + "                <div style=\"padding-bottom: 20px;\">\n"
                + "                    <img src=\"http://123.231.8.197/img/logo.png\" style=\"width: 105px;\" alt=\"tech-logo\"/>\n"
                + "                </div>\n"
                + "                <div style=\"font-size: 18px; text-transform: uppercase; color: #888888; padding-bottom: 20px\">\n"
                + "                    Congratulations! You've got new GrabM administrative account.\n"
                + "                </div>\n"
                + "                <div style=\"color: #888888; font-size: 30px; font-family: 'Segoe UI'\">Hi, " + firstName + "</div>\n"
                + "                <div style=\"color: #888888; font-size: 14px;\"><br/><br/>You have recently created administrative account in GrabM. \n"
                + "                    Following is your temporary password.<br/>\n"
                + "                    <br/>Password:&nbsp;&nbsp;<strong>" + password + "</strong>\n"
                + "                    <br/><br/>\n"
                + "                    Use above password to confirm your identity and please change your password at following given link.\n"
                + "                </div>\n"
                + "                <div style=\"font-size: 14px; padding-top: 20px; padding-bottom: 20px;\"><a href=\"" + link + "\" style=\"color: #8bbfeb;\">Click here to first login.</a></div>\n"
                + "                <div style=\"font-size: 14px;color: #888888; padding-bottom: 10px;\">Or copy and past following text in your browser.</div>\n"
                + "                <div style=\"font-size: 14px;color: #8bbfeb; padding-bottom: 20px;\">" + link + "</div>\n"
                + "                <div style=\"font-size: 14px;color: #888888;\">Thank you,<br/> - GrabM Technical Team.</div>\n"
                + "            </div>\n"
                + "        </div>\n"
                + "    </body>\n"
                + "</html>\n"
                + "";
    }
}
