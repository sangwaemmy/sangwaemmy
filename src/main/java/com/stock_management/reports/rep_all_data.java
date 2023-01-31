/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stock_management.reports;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import com.stock_management.dao.Dao_product;
import com.stock_management.model.Mdl_product;
import static com.stock_management.pdf_commons.Commons.cell_pad;
import static com.stock_management.pdf_commons.Commons.table_cols;
import com.stock_management.pdf_commons.Pdf_common;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
public class rep_all_data extends Pdf_common{
    
    public rep_all_data(HttpServletResponse resp, String title, String flag) throws DocumentException {
        document = new Document();
//        document.setPageSize(PageSize.A4.rotate());

        cell_pad = 5;
        table_cols = 5;
        resp.setContentType("application/pdf");
        super.doc_title(resp, title);
        title_newline();
        table = doc_body( flag);
        document.close();
    }
    private void make_cell() {
        super.add_cell("Product ID");
        super.add_cell("Product name");
        super.add_cell("Quantity");
        super.add_cell("Unit Cost");
        super.add_cell("Sale Cost");
    }

    public PdfPTable doc_body(String flag) throws DocumentException {
        //Get the list of product
        List<Mdl_product> mdl_product = null;
        if ("all_prod".equals(flag)) {//name
            mdl_product = new Dao_product().getAllprod_cbo();//database= Dao
        } else if (true) {// maybe sales,..
            
        }
 
           

        
        super.create_table();
        float[] columnWidths = new float[]{10f, 10f, 10f, 10f, 10f};

        table.setWidths(columnWidths);
        this.make_cell();

        //<editor-fold defaultstate="collapsed" desc="--for loop">
        for (int j = 0; j < mdl_product.size(); j++) {
            make_each_cell_contents(String.valueOf(mdl_product.get(j).getProduct_id()));
            make_each_cell_contents(String.valueOf(mdl_product.get(j).getName()));
            make_each_cell_contents(String.valueOf(mdl_product.get(j).getQuantity()));
            make_each_cell_contents(String.valueOf(mdl_product.get(j).getUnit_cost()));
            make_each_cell_contents(String.valueOf(mdl_product.get(j).getSale_cost()));
        }
        //</editor-fold>
        table.completeRow();
        document.add(table);
        return table;
    }
}
