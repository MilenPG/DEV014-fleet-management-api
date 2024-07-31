package api.fleetManagementAPI.components;

import api.fleetManagementAPI.services.ExcelService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;

@Component
public class MailManager {  //implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private ExcelService excelService;
    @Value("${spring.mail.username}")
    private String sender;

    public void sendMail(String email,
                         Integer taxi_id,
                         String date) throws MessagingException, IOException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setSubject("Trajectories Taxi ID: "+taxi_id);
        helper.setTo(email);
        helper.setText("Hola, este es un mensaje generado automáticamente por Fleet Management API. \nLa solicitud de trayectorias para el Taxi "+taxi_id+" fue realizada con éxito. \nResultado con lista de trayectorias en archivo adjunto.");
        helper.setFrom(sender);

        ByteArrayInputStream excelStream = this.excelService.createExcel(taxi_id, date);
        InputStreamSource attachment = new ByteArrayResource(excelStream.readAllBytes());

        helper.addAttachment("trajectories.xlsx", attachment);


        javaMailSender.send(message);


    }
}