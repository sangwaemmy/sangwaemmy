/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stock_management.pdf_commons;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
public class Pdf_common extends Commons {

    public void doc_title(HttpServletResponse resp, String _title) {
        try {
            OutputStream out = resp.getOutputStream();
            
            PdfWriter.getInstance(document, out);
            title = _title;
            document.open();
            Font title_font = new Font(Font.FontFamily.TIMES_ROMAN, title_font_size, Font.BOLD, BaseColor.BLUE);
            par1.add(new Phrase(title, title_font));
            par1.setAlignment(Element.ALIGN_CENTER);
            par1.add(new Phrase(Chunk.NEWLINE));
        } catch (DocumentException | IOException e) {
            System.out.println(e.toString());
        }
    }

    public void title_newline() {
        try {
            document.add(par1);
            document.add(Chunk.NEWLINE);
        } catch (DocumentException ex) {
            Logger.getLogger(Pdf_common.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(Pdf_common.class.getName() + " - Error: " + ex.toString());
        }
    }

}
