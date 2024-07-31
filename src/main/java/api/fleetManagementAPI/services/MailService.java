package api.fleetManagementAPI.services;

import api.fleetManagementAPI.components.MailManager;
import api.fleetManagementAPI.models.Trajectory;
import api.fleetManagementAPI.repositories.TrajectoryRepository;
import jakarta.mail.MessagingException;
import jakarta.servlet.ServletOutputStream;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;

@Service
public class MailService {

    @Autowired
    MailManager mailManager;
    @Autowired
    TrajectoryRepository trajectoryRepository;

    public ResponseEntity<Object> sendMessageTo(String email,
                                                Integer taxi_id,
                                                String date) throws MessagingException, IOException {
        mailManager.sendMail(email, taxi_id, date);
        System.out.println("Email enviado a "+ email +" exitósamente");
        return ResponseEntity.ok().body("Respuesta exitosa. Email enviado a "+ email);
    }

}
