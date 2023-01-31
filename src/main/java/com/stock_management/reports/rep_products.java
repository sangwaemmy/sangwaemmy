package com.stock_management.reports;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import com.stock_management.dao.Dao_product;
import com.stock_management.model.Mdl_product;
import com.stock_management.pdf_commons.Pdf_common;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
public class rep_products extends Pdf_common {

    public rep_products(HttpServletResponse resp, String title, int start, int end, String name,String flag) throws DocumentException {
        document = new Document();
//        document.setPageSize(PageSize.A4.rotate());

        cell_pad = 5;
        table_cols = 4;
        resp.setContentType("application/pdf");
        super.doc_title(resp, title);
        title_newline();
        table = doc_body(start, end, name,flag);
        document.close();
    }

    private void make_cell() {
//        super.add_cell("Product ID");
        super.add_cell("Product name");
        super.add_cell("Produt(kg)");
        super.add_cell("Sale Cost");
        super.add_cell("Total");
    }

    public PdfPTable doc_body(int start, int end, String name, String flag) throws DocumentException {
        //Get the list of product
        List<Mdl_product> mdl_product = null;
        if ("all_prod".equals(flag)) {// flag action for print all
            mdl_product = new Dao_product().getAllprod_cbo();//database= Dao
            
        } else if (!"".equals(name)) {//name
            mdl_product = new Dao_product().getprod_by_name(name);//database= Dao
        } else {
            mdl_product = new Dao_product().getAllproduct(start, end);//database= Dao

        }
        super.create_table();
        float[] columnWidths = new float[]{ 10f,6f,6f,6f};

        table.setWidths(columnWidths);
        this.make_cell();

        //<editor-fold defaultstate="collapsed" desc="--for loop">
        for (int j = 0; j < mdl_product.size(); j++) {
//            make_each_cell_contents(String.valueOf(mdl_product.get(j).getProduct_id()));
            make_each_cell_contents(String.valueOf(mdl_product.get(j).getName()));
            make_each_cell_contents(String.valueOf(mdl_product.get(j).getQuantity()));
            make_each_cell_contents(String.valueOf(mdl_product.get(j).getSale_cost()));
            make_each_cell_contents(String.valueOf(mdl_product.get(j).getTotal()));
        }
        //</editor-fold>
        table.completeRow();
        document.add(table);
        return table;
    }
}
