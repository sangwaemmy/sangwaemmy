/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stock_management.reports;

import com.itextpdf.text.DocumentException;
import com.stock_management.controllers.Pagination;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
@WebServlet(name = "Ctrl_reports", urlPatterns = {"/report"})
public class Ctrl_reports extends HttpServlet {

    Pagination pg = new Pagination();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {

        String action = req.getParameter("action");

//        System.out.println("start from front: " + req.getParameter("start"));
        pg.setStart(("".equals(req.getParameter("start"))) ? 1 : ((req.getParameter("start") != null) ? Integer.parseInt(req.getParameter("start")) //do
                : 1)
        );//else  do

        try {
            if (action.equals("all_prod")) {
                new rep_products(resp, "PRODUCTS REPORTS", 0, 0, "", "all_prod");
            } else if (action.equals("prod_by_name")) {
                String prod_name = req.getParameter("prod_name");
                new rep_products(resp, "PRODUCTS REPORTS", pg.getStart(), pg.getEnd(), prod_name, "");
            } else if ("prod".equals(action)) {
                new rep_products(resp, "PRODUCTS REPORTS", pg.getStart(), pg.getEnd(), "", "");
                
            } else if (action.equals("all_purchases")) {
                new rep_purchases(resp, "PURCHASES REPORTS", 0, 0, "", "", "all_purchases");

            } else if (action.equalsIgnoreCase("purchase_by_date")) {
                String purchase_date1 = req.getParameter("purchase_date1");
                String purchase_date2 = req.getParameter("purchase_date2");
                new rep_purchases(resp, "PURCHASES REPORTS", pg.getStart(), pg.getEnd(), purchase_date1, purchase_date2, "");
            } else if ("get_pur_prd_prs".equals(action)) {//another 
                new rep_purchases(resp, "PURCHASES REPORTS", pg.getStart(), pg.getEnd(), "", "", "");

            } else if (action.equals("all_sales")) {
                new rep_sales(resp, "SALES REPORTS", "", "", 0, 0, "all_sales");
            } else if (action.equalsIgnoreCase("sales_by_date")) {
                String sale_date1 = req.getParameter("sale_date1");
                String sale_date2 = req.getParameter("sale_date2");
                new rep_sales(resp, "SALES REPORTS", sale_date1, sale_date2, pg.getStart(), pg.getEnd(), "");
            } else if ("get_sls_prd_ps".equals(action)) {//another 
                new rep_sales(resp, "SALES REPORTS", "", "", pg.getStart(), pg.getEnd(), "");

            } else if (action.equals("all_damage")) {
                new rep_damages(resp, "DAMAGE REPORTS", 0, 0, "", "all_damage");
            } else if (action.equalsIgnoreCase("damage_by_name")) {
                String name = req.getParameter("damage_name");
                new rep_damages(resp, "DAMAGE REPORTS", pg.getStart(), pg.getEnd(), name, "");
            } else if ("get_dam_prd_prs".equals(action)) {//another 
                new rep_damages(resp, "DAMAGES REPORTS", pg.getStart(), pg.getEnd(), "", "");

            }
        } catch (DocumentException ex) {
            Logger.getLogger(Ctrl_reports.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
