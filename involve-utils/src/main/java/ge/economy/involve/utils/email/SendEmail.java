package ge.economy.involve.utils.email;

/**
 * Created by nino on 6/30/16.
 */

import org.apache.log4j.Logger;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;


public class SendEmail {

    private String from = "info@you.gov.ge";
    private String to = "";
    private List<FileAttachment> fileUrls = new ArrayList<>();
    private String subject = "YOU.GOV.GE Registration";
    private String body = "";
    private String auth = "true";
    //private String host = "smtp.gmail.com";
    private String host = "mail.economy.ge";
    //private String port = "587";
    private String port = "443";
    //private String username = "chaertege@gmail.com";
    //private String password = "chaertege@";
    private String username = "info@you.gov.ge";
    private String password = "4rfv%TGB";

    private static final Logger logger = Logger.getLogger(SendEmail.class);


    public void send() {

        Properties properties = System.getProperties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.user", from);
        properties.put("mail.smtp.password", password);
        properties.put("mail.smtp.auth", auth);

        Session session = Session.getDefaultInstance(properties);

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            if (to.contains(",")) {
                String[] recipients = to.split(",");
                message.addRecipient(Message.RecipientType.TO,
                        new InternetAddress(recipients[0]));
                for (int i = 1; i < recipients.length; i++) {
                    message.addRecipient(Message.RecipientType.BCC, new InternetAddress(recipients[i].trim()));
                }
            } else {
                message.addRecipient(Message.RecipientType.TO,
                        new InternetAddress(to));
            }
            message.setHeader("Content-Type", "text/plain; charset=UTF-8");
            message.setSubject(MimeUtility.encodeText(subject, "utf-8", "B"));
            message.setContent(body, "text/plain; charset=UTF-8");
            Transport transport = session.getTransport("smtp");
            transport.connect(host, from, password);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
            System.out.println("Sent message successfully....");
        } catch (Exception mex) {
            System.err.println(mex.getMessage());
        }

    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public static void main(String... args) throws EmailNotSentException {

        SendEmail attachment = new SendEmail();
        attachment.body = "test body";
        attachment.to = "nino.lomineisvili@gmail.com";

        attachment.send();
    }
}