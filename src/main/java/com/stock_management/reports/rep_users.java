/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stock_management.reports;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import com.stock_management.dao.Dao_person;
import com.stock_management.model.Mdl_person;
import com.stock_management.pdf_commons.Pdf_common;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
public class rep_users extends Pdf_common {
    
    public rep_users(HttpServletResponse resp, String title) throws DocumentException {
        document = new Document();
//        document.setPageSize(PageSize.A4.rotate());

        cell_pad = 5;
        table_cols = 7;
        resp.setContentType("application/pdf");

        super.doc_title(resp, title);
        title_newline();
        table = doc_body();
        document.close();
    }

    private void make_cell() {
//        super.add_cell("person Id");
        super.add_cell("Customer Name");
        super.add_cell("Customer surname");
        super.add_cell("Customer gender");
        super.add_cell("Customer phone number");
        super.add_cell("Category name");
        super.add_cell("Username");
        super.add_cell(" Password");
        
    }

    public PdfPTable doc_body() throws DocumentException {
        //Get the list of purchases
         List<Mdl_person> mdl_person=null;
         if ("get_per_cat_acc".equals("")) {
            mdl_person = new Dao_person().get_per_cat_acc();//database= Dao
        }
        super.create_table();
        float[] columnWidths = new float[]{ 2f, 2f, 3f, 2f, 3f, 3f, 3f};

        table.setWidths(columnWidths);
        this.make_cell();

        //<editor-fold defaultstate="collapsed" desc="--for loop">
        for (int j = 0; j < mdl_person.size(); j++) {
//            make_each_cell_contents(String.valueOf(mdl_person.get(j).getPerson_id()));
            make_each_cell_contents(String.valueOf(mdl_person.get(j).getName()));
            make_each_cell_contents(String.valueOf(mdl_person.get(j).getSurname()));
            make_each_cell_contents(String.valueOf(mdl_person.get(j).getGender()));
            make_each_cell_contents(String.valueOf(mdl_person.get(j).getPhone_number()));
            make_each_cell_contents(String.valueOf(mdl_person.get(j).getClass()));
            make_each_cell_contents(String.valueOf(mdl_person.get(j).getUsername()));
            make_each_cell_contents(String.valueOf(mdl_person.get(j).getPhone_number()));
        }
//</editor-fold>
        table.completeRow();
        document.add(table);
        return table;
    }
    
}
