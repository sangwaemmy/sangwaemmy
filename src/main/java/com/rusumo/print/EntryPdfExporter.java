/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rusumo.print;

import com.lowagie.text.DocumentException;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.Document;
import com.rusumo.models.Mdl_entry;
import com.rusumo.print.controller.Commons;
import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
 
/**
 *
 * @author SANGWA Emmanuel code [CODEGURU - info@codeguru.com]
 */
public class EntryPdfExporter extends Commons {

    private List<Mdl_entry> listAccounts;

    public EntryPdfExporter(List<Mdl_entry> listUsers) {
        this.listAccounts = listUsers;
    }

    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);

        com.lowagie.text.Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);
        cell.setPhrase(new Phrase("Entry ID", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Client", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Plate No", font));
        table.addCell(cell);
      
    }

    private void writeTableData(PdfPTable table) {
        for (Mdl_entry entry : listAccounts) {
            table.addCell(String.valueOf(entry.getId()));
            table.addCell(entry.getMdl_client().getName()+" "+ entry.getMdl_client().getSurname());
            table.addCell(entry.getPlate_no());
           
        }
    }

    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();
        com.lowagie.text.Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLUE);

        Paragraph p = new Paragraph("BON D'ENTRE AU PARKING AU "+ new Commons().getCurrentDateTime(), font);
        p.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(p);

        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(100f);
        table.setWidths(new float[]{1.5f, 3.5f, 3.0f});
        table.setSpacingBefore(10);
        writeTableHeader(table);
        writeTableData(table);
        document.add(table);
        document.close();

    }
}
