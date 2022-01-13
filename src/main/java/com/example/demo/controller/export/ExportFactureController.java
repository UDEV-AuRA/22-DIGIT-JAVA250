package com.example.demo.controller.export;

import com.example.demo.service.export.FactureExportPDFService;
import com.example.demo.service.export.FactureExportXLSXService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

/**
 * Controller pour réaliser l'export des client.
 */
@Controller
@RequestMapping("export/factures")
public class ExportFactureController {

    @Autowired
    private FactureExportXLSXService factureExportXLSXService;
    @Autowired
    private FactureExportPDFService factureExportPDFService;

    /**
     * Export des client au format CSV.
     */
    @GetMapping("xlsx")
    public void exportCSV(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("Content-Disposition", "attachment; filename=\"export-factures.xlsx\"");
        factureExportXLSXService.export(response.getOutputStream());
    }

    @GetMapping("{idFacture}/pdf")
    public void exportPDF(@PathVariable Long idFacture, HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setHeader("Content-Disposition", "attachment; filename=\"facture.pdf\"");
        OutputStream outputStream = response.getOutputStream();
        factureExportPDFService.export(outputStream, idFacture);
    }

}
