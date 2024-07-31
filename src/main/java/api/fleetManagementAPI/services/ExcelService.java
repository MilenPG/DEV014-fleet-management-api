package api.fleetManagementAPI.services;

import api.fleetManagementAPI.models.Trajectory;
import api.fleetManagementAPI.repositories.TrajectoryRepository;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class ExcelService {

    @Autowired
    private TrajectoryRepository trajectoryRepository;

    public ByteArrayInputStream createExcel(Integer taxi_id, String date) throws IOException {

        //Inyección de la data:
        List<Trajectory> trajectoriesData = trajectoryRepository.findByTaxiIdAndDateExport(taxi_id, date);

        Workbook workbook = new XSSFWorkbook();

        Sheet sheet = workbook.createSheet("Trajectories");
        Row header = sheet.createRow(0);

        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillForegroundColor(IndexedColors.LAVENDER.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        XSSFFont font = ((XSSFWorkbook) workbook).createFont();
        font.setFontName("Calibri");
        font.setFontHeightInPoints((short) 14);
        font.setBold(true);
        headerStyle.setFont(font);

        Cell headerCell = header.createCell(0);
        headerCell.setCellValue("Taxi ID");
        headerCell.setCellStyle(headerStyle);
        headerCell = header.createCell(1);
        headerCell.setCellValue("Plate");
        headerCell.setCellStyle(headerStyle);
        headerCell = header.createCell(2);
        headerCell.setCellValue("Date and Time");
        headerCell.setCellStyle(headerStyle);
        headerCell = header.createCell(3);
        headerCell.setCellValue("Latitude");
        headerCell.setCellStyle(headerStyle);
        headerCell = header.createCell(4);
        headerCell.setCellValue("Longitude");
        headerCell.setCellStyle(headerStyle);

        /*header.createCell(1).setCellValue("Plate");
        header.createCell(2).setCellValue("Date and Time");
        header.createCell(3).setCellValue("Latitude");
        header.createCell(4).setCellValue("Longitude");*/

        CellStyle style = workbook.createCellStyle();
        style.setWrapText(true);

        int dataRowIndex = 1;
        for (Trajectory trajectory : trajectoriesData) {
            Row dataRow = sheet.createRow(dataRowIndex);

            dataRow.createCell(0).setCellValue(String.valueOf(trajectory.getTaxi().getId()));
            dataRow.createCell(1).setCellValue(String.valueOf(trajectory.getTaxi().getPlate()));
            dataRow.createCell(2).setCellValue(trajectory.getDate().toString());
            dataRow.createCell(3).setCellValue(trajectory.getLatitude());
            dataRow.createCell(4).setCellValue(trajectory.getLongitude());

            dataRowIndex++;
        }

        for (int i = 0; i < header.getPhysicalNumberOfCells(); i++) {
            sheet.autoSizeColumn(i);
        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        workbook.close();

        return new ByteArrayInputStream(outputStream.toByteArray());
    }
}
