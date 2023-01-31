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
import com.stock_management.dao.Dao_sales;
import com.stock_management.model.Mdl_sales;
import static com.stock_management.pdf_commons.Commons.cell_pad;
import static com.stock_management.pdf_commons.Commons.table_cols;
import com.stock_management.pdf_commons.Pdf_common;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
public class rep_sales  extends Pdf_common{
     public rep_sales(HttpServletResponse resp, String title,String sale_date1,String sale_date2,int start, int end,String flag) throws DocumentException {
        document = new Document();
        document.setPageSize(PageSize.A4.rotate());

        cell_pad = 6;
        table_cols = 10;
        resp.setContentType("application/pdf");

        super.doc_title(resp, title);
        title_newline();
        table = doc_body(  sale_date1,sale_date2,start, end,flag);
        document.close();
    }

    private void make_cell() {
//        super.add_cell("Sales ID");
        super.add_cell("Product Name");
        super.add_cell("Date");
        super.add_cell("Sold quantity");
        super.add_cell("Sale Cost");
        super.add_cell("Amount paid");
        super.add_cell("Amount paid");
        super.add_cell("Total(Rwf)");
        super.add_cell("Customer name");
        super.add_cell("Customer Surname");
        super.add_cell("Customer phone");
    }

    public PdfPTable doc_body( String sale_date1,String sale_date2,int start, int end,String flag) throws DocumentException {
        //Get the list of purchases
         List<Mdl_sales> mdl_sales=null;
         if ("all_sales".equals(flag)) {
            mdl_sales = new Dao_sales().get_sls_prd();//database= Dao
            
         }else if (!"".equalsIgnoreCase(sale_date1)&& !"".equals(sale_date2)) {//date
            mdl_sales = new Dao_sales().get_sls_prd_ps_by_dt( sale_date1, sale_date2,start, end);//database= Dao
        }else {
          
        mdl_sales = new Dao_sales().get_sls_prd_ps(start, end);//database= Dao
        }
        super.create_table();
        float[] columnWidths = new float[]{ 2f, 2f, 3f, 2f, 3f, 3f, 3f,4f,4f,5f};

        table.setWidths(columnWidths);
        this.make_cell();

        //<editor-fold defaultstate="collapsed" desc="--for loop">
        for (int j = 0; j < mdl_sales.size(); j++) {
//            make_each_cell_contents(String.valueOf(mdl_sales.get(j).getSale_id()));
            make_each_cell_contents(String.valueOf(mdl_sales.get(j).getName()));
            make_each_cell_contents(String.valueOf(mdl_sales.get(j).getDate()));
            make_each_cell_contents(String.valueOf(mdl_sales.get(j).getSold_qty()));
            make_each_cell_contents(String.valueOf(mdl_sales.get(j).getSale_cost()));
            make_each_cell_contents(String.valueOf(mdl_sales.get(j).getAmt_paid()));
            make_each_cell_contents(String.valueOf(mdl_sales.get(j).getAmt_expected()));
            make_each_cell_contents(String.valueOf(mdl_sales.get(j).getTotal()));
            make_each_cell_contents(String.valueOf(mdl_sales.get(j).getP_name()));
            make_each_cell_contents(String.valueOf(mdl_sales.get(j).getSurname()));
            make_each_cell_contents(String.valueOf(mdl_sales.get(j).getPhone_number()));
        }
//</editor-fold>
        table.completeRow();
        document.add(table);
        return table;
    }

    
}
