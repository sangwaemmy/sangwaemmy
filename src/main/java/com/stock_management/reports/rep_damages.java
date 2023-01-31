/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stock_management.reports;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import com.stock_management.dao.Dao_damages;
import com.stock_management.model.Mdl_damages;
import static com.stock_management.pdf_commons.Commons.cell_pad;
import static com.stock_management.pdf_commons.Commons.table_cols;
import com.stock_management.pdf_commons.Pdf_common;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
public class rep_damages extends Pdf_common {

    public rep_damages(HttpServletResponse resp, String title, int start, int end, String name ,String flag) throws DocumentException {
        document = new Document();
//        document.setPageSize(PageSize.A4.rotate());

        cell_pad = 5;
        table_cols = 3;
        resp.setContentType("application/pdf");

        super.doc_title(resp, title);
        title_newline();
        table = doc_body(start, end, name,flag);
        document.close();
    }

    private void make_cell() {
//        super.add_cell("Damages ID");
        super.add_cell("Product Name");
        super.add_cell("Damage quantity");
        super.add_cell("Date");

    }

    public PdfPTable doc_body(int start, int end, String name,String flag) throws DocumentException {
        //Get the list of purchases
        List<Mdl_damages> mdl_damages=null;
        if ("all_damage".equals(flag)) {
            mdl_damages = new Dao_damages().get_dam_prd();//database= Dao
        }else if (!"".equals(name)) {

             mdl_damages = new Dao_damages().get_dam_prd_prs_p_nm(name,start,end);//database= Dao
        } else {
           mdl_damages = new Dao_damages().get_dam_prd_prs(start, end);//database= Dao

        }
        super.create_table();
        float[] columnWidths = new float[]{ 3f, 3f, 2f};

        table.setWidths(columnWidths);
        this.make_cell();

        //<editor-fold defaultstate="collapsed" desc="--for loop">
        for (int j = 0; j < mdl_damages.size(); j++) {
//            make_each_cell_contents(String.valueOf(mdl_damages.get(j).getDamage_id()));
            make_each_cell_contents(String.valueOf(mdl_damages.get(j).getName()));
            make_each_cell_contents(String.valueOf(mdl_damages.get(j).getDamage_qty()));
            make_each_cell_contents(String.valueOf(mdl_damages.get(j).getDate()));

        }
//</editor-fold>
        table.completeRow();
        document.add(table);
        return table;
    }

}
