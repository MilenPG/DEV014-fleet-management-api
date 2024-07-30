package api.fleetManagementAPI.services;

import api.fleetManagementAPI.components.MailManager;
import api.fleetManagementAPI.models.Trajectory;
import api.fleetManagementAPI.repositories.TrajectoryRepository;
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
                                                String subject,
                                                String contentMessage,
                                                Integer taxiId,
                                                String date,
                                                Integer pageNumber,
                                                Integer pageLimit)
        throws IOException {
        ByteArrayInputStream excelFile = createExcel(taxiId, date, pageNumber, pageLimit);
        mailManager.sendMessage(email, subject, contentMessage, excelFile);
        System.out.println("Email enviado a "+ email +" exitósamente");
        return ResponseEntity.ok().body("Respuesta exitosa");
    }

    public ByteArrayInputStream createExcel(Integer taxiId, String date, Integer pageNumber, Integer pageLimit)
            throws IOException {
                Workbook workbook = new XSSFWorkbook();

                Sheet sheet = workbook.createSheet("Trajectories");
                Row header = sheet.createRow(0);

                header.createCell(0).setCellValue("Taxi ID");
                header.createCell(1).setCellValue("Plate");
                header.createCell(2).setCellValue("Latitude");
                header.createCell(3).setCellValue("Longitude");
                header.createCell(4).setCellValue("Date and Time");

                //Inyección de la data:
                Pageable page = PageRequest.of(pageNumber, pageLimit);
                List<Trajectory> trajectoriesData = trajectoryRepository.findByTaxiIdAndDate(taxiId, date, page);

                int dataRowIndex = 1;
                    for(Trajectory trajectory : trajectoriesData) {
                        Row dataRow = sheet.createRow(dataRowIndex);
                        dataRow.createCell(0).setCellValue(String.valueOf(trajectory.getTaxi().getId()));
                        dataRow.createCell(1).setCellValue(String.valueOf(trajectory.getTaxi().getPlate()));
                        dataRow.createCell(2).setCellValue(trajectory.getLatitude());
                        dataRow.createCell(3).setCellValue(trajectory.getLongitude());
                        dataRow.createCell(4).setCellValue(trajectory.getDate());
                        dataRowIndex++;
                    }

                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                workbook.write(outputStream);
                workbook.close();

                /*
                *DOC BAELDUNG - ME PIDE MANEJAR LAS EXCEPCIONES.
                File currDir = new File(".");
                String path = currDir.getAbsolutePath();
                String fileLocation = path.substring(0, path.length() - 1) + "trajectories.xlsx";

                FileOutputStream outputStream = new FileOutputStream(fileLocation);
                workbook.write(outputStream);
                workbook.close();
                */

                return new ByteArrayInputStream(outputStream.toByteArray());
                }
            /* EJEMPLO Q APARECE EN LA DOCUMENTACIÓN PARA ADJUNTAR UN RECIBO. EN ESTE CASO, HABRÍA Q MODIFICARLO PARA ADJUNTAR EXCEL???
            FileSystemResource file = new FileSystemResource(new File(pathToAttachment));
            helper.addAttachment("Invoice", file);
            */
}
