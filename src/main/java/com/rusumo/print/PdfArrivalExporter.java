/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rusumo.print;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.rusumo.models.Mdl_tallying;
import com.rusumo.print.controller.Commons;
import java.awt.Color;
import java.io.IOException;
import java.util.List;
import static java.util.Map.entry;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author SANGWA Emmanuel code [CODEGURU - info@codeguru.com]
 */
public class PdfArrivalExporter extends Commons {

    private List<Mdl_tallying> arrival;// we can call it anythign the imprtant is that it retrieves the: tallying->arrival->entry->client

    public PdfArrivalExporter(List<Mdl_tallying> arrival) {
        this.arrival = arrival;
    }

    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);

        com.lowagie.text.Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);
        cell.setPhrase(new Phrase("Item", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Quantity", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Weight", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Description", font));
        table.addCell(cell);

    }

    private void writeTableData(PdfPTable table) {
        for (Mdl_tallying arriv : arrival) {
            table.addCell(String.valueOf(arriv.getId()));
            table.addCell(arriv.getItem_name());
            table.addCell(arriv.getQuantity());
            table.addCell(arriv.getWeight());
            table.addCell(arriv.getDescription());
        }
    }

    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();
        com.lowagie.text.Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLUE);

        com.lowagie.text.Font date_font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        date_font.setSize(10);
        Paragraph datePar = new Paragraph(new Commons().getCurrentDateTime(), date_font);
        datePar.setAlignment(Paragraph.ALIGN_RIGHT);
        datePar.setFont(date_font);
        document.add(datePar);

        Paragraph p = new Paragraph("AVIS D'ARRIVE " + new Commons().getCurrentDateTime(), font);
        p.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(p);

        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100f);
        table.setWidths(new float[]{1.5f, 3.5f, 3.0f, 3.0f});
        table.setSpacingBefore(10);
        writeTableHeader(table);
        writeTableData(table);
        document.add(table);
        document.close();

    }
}
