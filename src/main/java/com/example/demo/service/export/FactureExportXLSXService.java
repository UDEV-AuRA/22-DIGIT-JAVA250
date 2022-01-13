package com.example.demo.service.export;

import com.example.demo.repository.FactureRepository;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.OutputStream;

@Service
public class FactureExportXLSXService {

    @Autowired
    private FactureRepository factureRepository;

    public void export(OutputStream outputStream) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("test sheet");
        workbook.write(outputStream);
        workbook.close();
    }

}
