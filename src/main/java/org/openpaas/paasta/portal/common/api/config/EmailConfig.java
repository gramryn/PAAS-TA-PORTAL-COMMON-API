package org.openpaas.paasta.portal.common.api.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import javax.activation.DataHandler;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

@Configuration
public class EmailConfig {

    @Value("${spring.mail.smtp.host}")
    String host;

    @Value("${spring.mail.smtp.port}")
    int port;

    @Value("${spring.mail.smtp.username}")
    String username;

    @Value("${spring.mail.smtp.password}")
    String password;

    @Value("${spring.mail.smtp.useremail}")
    String useremail;

    @Value("${spring.mail.smtp.properties.auth}")
    String auth;

    @Value("${spring.mail.smtp.properties.starttls.enable}")
    String starttls_enable;

    @Value("${spring.mail.smtp.properties.starttls.required}")
    String starttls_required;

    @Value("${spring.mail.smtp.properties.maximumTotalQps}")
    String maximumTotalQps;

    @Value("${spring.mail.smtp.properties.authUrl}")
    String authUrl;

    @Value("${spring.mail.smtp.properties.imgUrl}")
    String imgUrl;

    @Value("${spring.mail.smtp.properties.subject}")
    String subject;

    @Value("${spring.mail.smtp.properties.contextUrl}")
    String contextUrl;


    public boolean sendEmail(String to, String contents) throws IOException, MessagingException {
        Boolean bRtn = false;
        try {

            // 인증
            Authenticator auth = auth();

            // 메일 세션
            Session session = Session.getInstance(properties(), auth);
            MimeMessage msg = new MimeMessage(session);
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.addHeader("format", "flowed");
            msg.addHeader("Content-Transfer-Encoding", "8bit");

            msg.setDataHandler(new DataHandler(new ByteArrayDataSource(contents, "text/html")));
            msg.setSentDate(new Date());
            msg.setSubject(subject);
            msg.setContent(contents, "text/html;charset=" + "EUC-KR");
            msg.setFrom(new InternetAddress(to, username));
            msg.setReplyTo(InternetAddress.parse(to, false));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
            Transport.send(msg);
            bRtn = true;
        } catch (IOException | MessagingException e) {
            e.printStackTrace();

        }
        return bRtn;

    }

    @Bean
    public MailProperties mailProperties() {

        MailProperties mailProperties = new MailProperties();
        mailProperties.setHost(host);
        mailProperties.setPort(port);
        mailProperties.setUsername(useremail);
        mailProperties.setPassword(password);
        return mailProperties;
    }

    @Bean
    public JavaMailSender mailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(mailProperties().getHost());
        if (mailProperties().getPort() != null) {
            mailSender.setPort(mailProperties().getPort());
        }
        mailSender.setUsername(mailProperties().getUsername());
        mailSender.setPassword(mailProperties().getPassword());
        return mailSender;
    }

    @Bean
    public Properties properties() {
        MailProperties mailProperties = mailProperties();
        Properties props = new Properties();
        // SSL 사용하는 경우
        props.put("mail.smtp.host", mailProperties.getHost()); //SMTP Host
        props.put("mail.smtp.socketFactory.port", mailProperties.getPort()); //SSL Port
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); //SSL Factory Class
        props.put("mail.smtp.port", mailProperties.getPort());
        props.put("mail.smtp.auth", auth); //Enabling SMTP Authentication
        props.put("mail.smtp.maximumTotalQps", mailProperties);
        props.put("mail.smtp.subject", subject);
        props.put("mail.smtp.username", username);
        props.put("mail.smtp.userEmail", useremail);
        props.put("mail.smtp.contextUrl", contextUrl);
        return props;
    }

    // 인증
    @Bean
    public Authenticator auth() {
        Authenticator auth = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                MailProperties mailProperties = mailProperties();
                return new PasswordAuthentication(mailProperties.getUsername(), mailProperties.getPassword());
            }

        };
        return auth;
    }


    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
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

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public String getStarttls_enable() {
        return starttls_enable;
    }

    public void setStarttls_enable(String starttls_enable) {
        this.starttls_enable = starttls_enable;
    }

    public String getStarttls_required() {
        return starttls_required;
    }

    public void setStarttls_required(String starttls_required) {
        this.starttls_required = starttls_required;
    }

    public String getMaximumTotalQps() {
        return maximumTotalQps;
    }

    public void setMaximumTotalQps(String maximumTotalQps) {
        this.maximumTotalQps = maximumTotalQps;
    }

    public String getAuthUrl() {
        return authUrl;
    }

    public void setAuthUrl(String authUrl) {
        this.authUrl = authUrl;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContextUrl() {
        return contextUrl;
    }

    public void setContextUrl(String contextUrl) {
        this.contextUrl = contextUrl;
    }
}