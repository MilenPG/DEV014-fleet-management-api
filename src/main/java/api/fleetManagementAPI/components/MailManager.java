package api.fleetManagementAPI.components;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.util.ByteArrayDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;

@Component
public class MailManager {  //implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String sender;

    public void sendMessage(String to,
                            String subject,
                            String contentMessage, ByteArrayInputStream excelFile) {
        MimeMessage message = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setSubject(subject);
            helper.setTo(to);
            helper.setText(contentMessage);
            helper.setFrom(sender);
           // helper.addAttachment("Trajectories.xlsx", attachment);

            javaMailSender.send(message);
        }

        catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }
}