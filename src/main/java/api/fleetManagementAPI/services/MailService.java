package api.fleetManagementAPI.services;

import api.fleetManagementAPI.config.MailManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    @Autowired
    MailManager mailManager;

    public ResponseEntity<Object> sendMessageTo(String to,
                                                String subject,
                                                String contentMessage){
        mailManager.sendMessage(to, subject, contentMessage);
        System.out.println("Email enviado a "+ to +" exitósamente");
        return ResponseEntity.ok().body("Respuesta exitosa");
    }
}
