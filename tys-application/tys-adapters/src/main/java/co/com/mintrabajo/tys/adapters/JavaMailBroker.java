package co.com.mintrabajo.tys.adapters;

import okhttp3.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;
import java.util.List;

@Component
public class JavaMailBroker {
    private static final Logger LOGGER = LogManager.getLogger(JavaMailBroker.class);
    public JavaMailSender javaMailSender;
    public TramitesBPMBroker tramitesBPMBroker;
    @Value("${notificaciones.smtp.user}")
    public String emailOrign;

    @Autowired
    public JavaMailBroker(JavaMailSender javaMailSender, TramitesBPMBroker tramitesBPMBroker) {
        this.javaMailSender = javaMailSender;
        this.tramitesBPMBroker = tramitesBPMBroker;
    }

    public void basicMessageTest() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("gymstatscol@gmail.com");
        message.setTo(new String[]{"luisgranada1213@gmail.com", "lgranada@gmail.com"});
        message.setSubject("Test");
        message.setText("Contenido");
        try {
            javaMailSender.send(message);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getLocalizedMessage());
            System.out.println(e.getCause().getMessage());
            LOGGER.error("Error enviando mensaje", e);
        }
    }

    public void enviarMensajePracticasAdolecentes(String asunto, String contenido, List<String> destinatarios, String strJsonDatosBasicosPdf) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(emailOrign);
            helper.setTo(destinatarios.toArray(new String[destinatarios.size()]));
            helper.setSubject(asunto);
            helper.setText(contenido);
            Response response = tramitesBPMBroker.fetchPdfPracticasAdolecente(strJsonDatosBasicosPdf);
            ByteArrayDataSource source = new ByteArrayDataSource(response.body().source().inputStream(), MediaType.APPLICATION_PDF_VALUE);
            helper.addAttachment("PracticasLaborales.pdf", source);
            javaMailSender.send(message);
        } catch (Exception e) {
            LOGGER.error("Error enviando mensaje con pdf adjunto", e);
        }
    }
}
