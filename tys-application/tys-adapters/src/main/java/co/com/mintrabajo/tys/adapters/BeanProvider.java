package co.com.mintrabajo.tys.adapters;

import co.com.mintrabajo.tys.commons.util.CheckUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class BeanProvider {
    @Value("${notificaciones.smtp.port}")
    private int port;
    @Value("${notificaciones.smtp.host}")
    private String host;
    @Value("${notificaciones.smtp.user}")
    private String user;
    @Value("${notificaciones.smtp.password}")
    private String password;
    @Value("${notificaciones.smtp.starttls}")
    private String starttls;

    @Bean
    public JavaMailSender getJavaMailSender() {
        password = CheckUtil.checkNullString(password);
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(host);
        mailSender.setPort(port);

        mailSender.setUsername(user);
        mailSender.setPassword(password);

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.starttls.enable", starttls);
        //props.put("mail.smtp.auth", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }
}
