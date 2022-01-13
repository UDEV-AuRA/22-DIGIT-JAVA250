package com.example.demo.service.export;

import com.example.demo.entity.Facture;
import com.example.demo.entity.LigneFacture;
import com.example.demo.repository.FactureRepository;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.OutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@Service
public class FactureExportPDFService {

    @Autowired
    private FactureRepository factureRepository;

    public void export(OutputStream outputStream, Long idFacture) throws Exception {
        Facture facture = factureRepository.findById(idFacture).get();

        Document document = new Document();
        PdfWriter.getInstance(document, outputStream);
        document.open();
        document.add(new Paragraph("Facture " + idFacture));

        PdfPTable table = new PdfPTable(3);
        table.addCell(new PdfPCell(new Phrase("Articles")));
        table.addCell(new PdfPCell(new Phrase("Quantité")));
        table.addCell(new PdfPCell(new Phrase("PrixUnitaire")));
        for (LigneFacture ligneFacture : facture.getLigneFactures()) {
            table.addCell(new PdfPCell(new Phrase(ligneFacture.getArticle().getLibelle())));
            table.addCell(new PdfPCell(new Phrase("" + ligneFacture.getQuantite())));
            table.addCell(new PdfPCell(new Phrase("" + ligneFacture.getArticle().getPrix())));
        }
        PdfPCell cellTotalLibelle = new PdfPCell(new Phrase("PRIX TOTAL"));
        cellTotalLibelle.setColspan(2);
        table.addCell(cellTotalLibelle);

        table.addCell(new PdfPCell(new Phrase("" + "a calculer")));
        document.add(table);
        document.close();
    }

}
