package api.fleetManagementAPI.config;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class MailManager {  //implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String sender;

    public void sendMessage(String to,
                            String subject,
                            String contentMessage) {
        MimeMessage message = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setSubject(subject);
            helper.setTo(to);
            helper.setText(contentMessage);
            helper.setFrom(sender);

            /* EJEMPLO Q APARECE EN LA DOCUMENTACIÓN PARA ADJUNTAR UN RECIBO. EN ESTE CASO, HABRÍA Q MODIFICARLO PARA ADJUNTAR EXCEL???
            FileSystemResource file = new FileSystemResource(new File(pathToAttachment));
            helper.addAttachment("Invoice", file);
            */

            javaMailSender.send(message);
        }

        catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }
}