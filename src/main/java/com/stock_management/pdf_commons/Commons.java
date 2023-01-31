/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stock_management.pdf_commons;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

/**
 *
 * @author Admin
 */
public class Commons {

    public String title = "";

    public PdfPTable table;
    public Document document;

    public int title_font_size = 12;
    public String bold = "", color = "";

    public Paragraph par1 = new Paragraph();
    public static int cell_pad = 0;

    public int tuple_font_size = 9;
    public Font bold_font_roman = FontFactory.getFont("Aroma", tuple_font_size, Font.BOLD, BaseColor.BLACK);
    public Font non_bold_font_roman = FontFactory.getFont("Aroma", tuple_font_size, Font.NORMAL, BaseColor.BLACK);

    public static int table_cols =0;

    public void make_each_cell_contents(String content) {

        PdfPCell cell1 = new PdfPCell();
        cell1 = new PdfPCell(new Paragraph(String.valueOf(content), bold_font_roman));
        cell1.setPadding(cell_pad);
        cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell1);
    }
    public void add_cell(String content) {
        PdfPCell cell1 = new PdfPCell();
        cell1 = new PdfPCell(new Paragraph(content, bold_font_roman));
        cell1.setPadding(cell_pad);
        cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell1);
    }
    public void create_table() {
        table = new PdfPTable(table_cols);
        table.setWidthPercentage(100);
    }
}
