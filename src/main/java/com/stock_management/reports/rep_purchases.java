/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stock_management.reports;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfPTable;
import com.stock_management.dao.Dao_purchase;
import com.stock_management.model.Mdl_purchase;
import com.stock_management.pdf_commons.Pdf_common;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
public class rep_purchases extends Pdf_common {

    public rep_purchases(HttpServletResponse resp, String title,int start,int end ,String purchase_date1,String  purchase_date2, String flag ) throws DocumentException {
        document = new Document();
        document.setPageSize(PageSize.A4.rotate());

        cell_pad = 6;
        table_cols = 10;
        resp.setContentType("application/pdf");

        super.doc_title(resp, title);
        title_newline();
        table = doc_body(purchase_date1,purchase_date2,start, end,flag);
        document.close();
    }

    private void make_cell() {
//        super.add_cell("purchase ID");
        super.add_cell("Product Name");
        super.add_cell("Date");
        super.add_cell("purchases(kg)");
        super.add_cell("current(kg)");
        super.add_cell("Product(kg)");
        super.add_cell("Total(Rwf)");
        super.add_cell("Name");
        super.add_cell("Surname");
        super.add_cell("Gender");
        super.add_cell("Telephone");
    }

    public PdfPTable doc_body(String purchase_date1,String  purchase_date2 ,int start, int end ,String flag ) throws DocumentException {
        //Get the list of purchases
        
         List<Mdl_purchase> mdl_purchase=null;
         if ("all_purchases".equals(flag)) {// flag for print all
            mdl_purchase = new Dao_purchase().get_pur_prd();//database= Dao
            
         }else if (!"".equalsIgnoreCase(purchase_date1)&& !"".equals(purchase_date2)) {//date
            mdl_purchase= new Dao_purchase().get_pur_prd_prs_by_dt(purchase_date1, purchase_date2,start, end);//database= Dao
            
        }else {
            
        mdl_purchase = new Dao_purchase().get_pur_prd_prs(start, end);//database= Dao
          
        }
        super.create_table();
        float[] columnWidths = new float[]{ 8f, 10f, 10f, 8f, 8f, 8f, 8f, 8f, 10f,10f};

        table.setWidths(columnWidths);
        this.make_cell();

        //<editor-fold defaultstate="collapsed" desc="--for loop">
        for (int j = 0; j < mdl_purchase.size(); j++) {
//            make_each_cell_contents(String.valueOf(mdl_purchase.get(j).getPurchase_id()));
            make_each_cell_contents(String.valueOf(mdl_purchase.get(j).getName()));
            make_each_cell_contents(String.valueOf(mdl_purchase.get(j).getDate()));
            make_each_cell_contents(String.valueOf(mdl_purchase.get(j).getPurchase_qty()));
            make_each_cell_contents(String.valueOf(mdl_purchase.get(j).getCurrent_qty()));
            make_each_cell_contents(String.valueOf(mdl_purchase.get(j).getQuantity()));
            make_each_cell_contents(String.valueOf(mdl_purchase.get(j).getTotal()));
            make_each_cell_contents(String.valueOf(mdl_purchase.get(j).getP_name()));
            make_each_cell_contents(String.valueOf(mdl_purchase.get(j).getSurname()));
            make_each_cell_contents(String.valueOf(mdl_purchase.get(j).getGender()));
            make_each_cell_contents(String.valueOf(mdl_purchase.get(j).getPhone_number()));
        }
//</editor-fold>
        table.completeRow();
        document.add(table);
        return table;
    }
}


