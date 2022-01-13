package com.example.demo.service.export;

import com.example.demo.entity.Client;
import com.example.demo.repository.ClientRepository;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.OutputStream;

@Service
public class ClientExportXLSXService {

    @Autowired
    private ClientRepository clientRepository;

    public void export(OutputStream outputStream) throws IOException {
        Workbook workbook = new XSSFWorkbook();

        CellStyle headerStyle = createStyle(workbook);
        Font font = workbook.createFont();
        font.setBold(true);
        font.setColor(IndexedColors.PINK.getIndex());
        headerStyle.setFont(font);

        CellStyle dataStyle = createStyle(workbook);

        Sheet sheet = workbook.createSheet("Clients");
        Row headerRow = sheet.createRow(0);

        Cell cellHeaderPrenom = headerRow.createCell(0);
        cellHeaderPrenom.setCellValue("Prénom");
        cellHeaderPrenom.setCellStyle(headerStyle);

        Cell cellHeaderNom = headerRow.createCell(1);
        cellHeaderNom.setCellValue("Nom");
        cellHeaderNom.setCellStyle(headerStyle);

        int iRow = 1;
        for (Client client : clientRepository.findAll()) {
            Row row = sheet.createRow(iRow++);

            Cell cellDataPrenom = row.createCell(0);
            cellDataPrenom.setCellValue(client.getPrenom());
            cellDataPrenom.setCellStyle(dataStyle);

            Cell cellDataNom = row.createCell(1);
            cellDataNom.setCellValue(client.getNom());
            cellDataNom.setCellStyle(dataStyle);
        }
        workbook.write(outputStream);
        workbook.close();
    }

    private CellStyle createStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();

        style.setBorderLeft(BorderStyle.THICK);
        style.setBorderRight(BorderStyle.THICK);
        style.setBorderTop(BorderStyle.THICK);
        style.setBorderBottom(BorderStyle.THICK);

        style.setBottomBorderColor(IndexedColors.BLUE.getIndex());
        style.setTopBorderColor(IndexedColors.BLUE.getIndex());
        style.setLeftBorderColor(IndexedColors.BLUE.getIndex());
        style.setRightBorderColor(IndexedColors.BLUE.getIndex());

        return style;
    }

}
