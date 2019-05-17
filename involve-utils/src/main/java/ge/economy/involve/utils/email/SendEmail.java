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
import javax.mail.util.ByteArrayDataSource;
import java.io.ByteArrayOutputStream;
import java.io.File;
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
    private String host = "mail.economy.ge"; //10.20.21.38
    //private String port = "587";
    private String port = "25"; //443
    //private String username = "chaertege@gmail.com";
    //private String password = "chaertege@";
    private String username = "info@you.gov.ge";
    private String password = "4rfv%TGB";
    public static final String LOGO_PATH = "/usr/share/glassfish4/glassfish/domains/domain1/applications/involve/resources/imgs/logoMail.png";
    public static final String LOGO_PATH2 = "C:\\upload\\logoMail.png";
    private static final Logger logger = Logger.getLogger(SendEmail.class);


    public void send() {

        Properties properties = System.getProperties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.user", from);
        properties.put("mail.smtp.password", password);
        properties.put("mail.smtp.auth", auth);

       /* properties.put("mail.smtps.starttls.enable", "true");
        properties.put("mail.smtp.socketFactory.port", "25");
        properties.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.socketFactory.fallback", "false");


        properties.put("mail.smtp.auth", auth);
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.password", password);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.socketFactory.port", port);
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.socketFactory.fallback", "false");

        Properties props = new Properties();
        props.put("mail.smtp.host", "mail.economy.ge");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.socketFactory.port", "25");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "25");

        */

        /*
        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username,password);
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("nino.lomineisvili@gmail.com"));
            message.setSubject("Testing Subject");
            message.setText("Test Mail");
            Transport.send(message);
            System.out.println("Done");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
*/
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

    public void sendWithPdf(ByteArrayOutputStream outputStream) {

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

            byte[] bytes = outputStream.toByteArray();

            DataSource dataSource = new ByteArrayDataSource(bytes, "application/pdf");
            BodyPart filePart = new MimeBodyPart();
            filePart.setDataHandler(new DataHandler(dataSource));
            filePart.setFileName("Initiative.pdf");


           /*
            attachment for logo
            MimeBodyPart attachLogo = new MimeBodyPart();
            String attachFile = LOGO_PATH;
            DataSource source = new FileDataSource(attachFile);
            attachLogo.setDataHandler(new DataHandler(source));
            attachLogo.setFileName(new File(attachFile).getName());*/

            String cid = "abcdd";
            BodyPart logoBodyPart = new MimeBodyPart();
            String htmlText = "<img src=\"cid:" + cid + "\"> <br><br><br><br>";
            logoBodyPart.setContent(htmlText, "text/html");


            /*MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent(body, "text/plain; charset=UTF-8");*/

            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent("<html><body>" + body + "<br/>" +
                    "<div ><img src=\"http://www.you.gov.ge/images/logoMail2.png\" /></div>" +
                    "<br/><br/><br/></body></html>", "text/html; charset=UTF-8");

           /* MimeBodyPart imagePart = new MimeBodyPart();
            imagePart.attachFile(LOGO_PATH2);
            imagePart.setContentID("<" + cid + ">");
            imagePart.setDisposition(MimeBodyPart.INLINE);*/


            Multipart multipart = new MimeMultipart("related");
            multipart.addBodyPart(messageBodyPart);
            multipart.addBodyPart(filePart);

            message.setContent(multipart);

            message.setSubject(MimeUtility.encodeText(subject, "utf-8", "B"));
            Transport transport = session.getTransport("smtp");
            transport.connect(host, from, password);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();

            logger.info("Sent message with pdf successfully....");
        } catch (Exception mex) {
            logger.error(mex.getMessage());
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