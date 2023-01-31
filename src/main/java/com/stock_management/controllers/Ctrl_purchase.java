/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stock_management.controllers;

import com.google.gson.Gson;
import com.stock_management.dao.Dao_person;
import com.stock_management.dao.Dao_product;
import com.stock_management.dao.Dao_purchase;
import com.stock_management.dao.Pub_methods;
import com.stock_management.model.Mdl_person;
import com.stock_management.model.Mdl_product;
import com.stock_management.model.Mdl_purchase;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Etienne
 */
@WebServlet(name = "/purchase", urlPatterns = {"/purchase"})
public class Ctrl_purchase extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static String INSERT_OR_EDIT = "/Mdl_purchase.jsp";
    private static String LIST_PURCHASE = "forms/frm_purchases.jsp";
    private Dao_purchase dao_purchase;
    private Dao_product dao_product;
    private Dao_person dao_person;
    // calling of pagination
    Pagination pg = new Pagination();

    public Ctrl_purchase() {
        super();
        dao_purchase = new Dao_purchase();
        dao_product = new Dao_product();
        dao_person = new Dao_person();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String forward = "";
        String action = request.getParameter("action");
        String page = "";
        pg.setStart((request.getParameter("start") != null) ? Integer.parseInt(request.getParameter("start")) : 1);

        // map for all list
        Map<String, List<Mdl_purchase>> map = new HashMap<>();

        if (action.equalsIgnoreCase("delete")) {
            int purchase_id = Integer.parseInt(request.getParameter("purchase_id"));
            dao_purchase.delete_purchase(purchase_id);
            forward = LIST_PURCHASE;
            request.setAttribute("purchase", dao_purchase.getAllpurchase());
            response.getWriter().print("deleted successfully");

        } else if (action.equalsIgnoreCase("edit")) {
//            System.out.println("purchase:" + request.getParameter("purchase_id"));
            forward = LIST_PURCHASE;
            int purchase_id = Integer.parseInt(request.getParameter("purchase_id"));
            Mdl_purchase mdl_purchase = dao_purchase.getPurchaseByid(purchase_id);
            request.setAttribute("purchase", mdl_purchase);
            request.setAttribute("purchases", dao_purchase.getAllpurchase());
            //purchase
            List<Mdl_purchase> mdl_purchase2 = dao_purchase.getAllpurchase();

            request.setAttribute("purchase", mdl_purchase);
            map.put("purchase", mdl_purchase2);

            Map<String, Object> purchase_map = new HashMap<>();
            purchase_map.put("purchases", mdl_purchase);
            new Gson().toJson(purchase_map, response.getWriter());
            //get_pur_prd_prs

        } else if (action.equalsIgnoreCase("get_pur_prd_prs")) {
            forward = LIST_PURCHASE;
//get_pur_prd_prs
            List<Mdl_purchase> mdl_purchase2 = dao_purchase.get_pur_prd_prs(pg.getStart(), pg.getEnd());
            List<Mdl_product> mdl_prod_cbo = dao_product.getAllprod_cbo();
            List<Mdl_person> mdl_persons = dao_person.getAllperson_by_category("supplier");

            request.setAttribute("get_pur_prd_prs", mdl_purchase2);

            // count total pages before searching
            request.setAttribute("get_all_products", mdl_prod_cbo);
//            request.setAttribute("get_prod_qty", products);
            request.setAttribute("suppliers", mdl_persons);
            request.setAttribute("tot", dao_purchase.get_count_Purchase());
            request.setAttribute("start", request.getParameter("start"));
            map.put("get_pur_prd_prs", mdl_purchase2);

            RequestDispatcher view = request.getRequestDispatcher(forward);
            view.forward(request, response);

        } else if (action.equalsIgnoreCase("get_purch_prdct_prs_by_date")) {
            forward = LIST_PURCHASE;
            String date1 = request.getParameter("txt_date1");
            String date2 = request.getParameter("txt_date2");
            List<Mdl_purchase> mdl_purchase2 = dao_purchase.get_pur_prd_prs_by_dt(date1, date2, pg.getStart(), pg.getEnd());
            map.put("get_pur_pr_ps_by_date", mdl_purchase2);

            request.setAttribute("purc_by_date", mdl_purchase2);
            //count total pages by searching
            request.setAttribute("tot", dao_purchase.get_count_by_dt(date1, date2));
            request.setAttribute("bydate", "yes");
            request.setAttribute("date1", date1);
            request.setAttribute("date2", date2);
            request.setAttribute("start", request.getParameter("start"));
            System.out.println("Start - " + request.getParameter("start"));
            RequestDispatcher view = request.getRequestDispatcher(forward);
            view.forward(request, response);

        } else if (action.equalsIgnoreCase("purchase_by_date")) {
            forward = LIST_PURCHASE;
            String date1 = request.getParameter("purchase_date1");
            String date2 = request.getParameter("purchase_date2");
            request.setAttribute("sales_by_date", dao_purchase.get_pur_prd_prs_by_dt(date1, date2, pg.getStart(), pg.getEnd()));
            request.setAttribute("purchase_date1", date1);
            request.setAttribute("purchase_date1", date2);
            RequestDispatcher view = request.getRequestDispatcher(forward);
            view.forward(request, response);

        } else if (action.equalsIgnoreCase("get_purch_prdct_prs_by_nm")) {
            String pro_name = request.getParameter("txt_name");

            List<Mdl_purchase> mdl_purchase2 = dao_purchase.get_pur_prd_prs_by_p_nm(pro_name);
            map.put("get_pur_pr_ps_by_nm", mdl_purchase2);

            new Pub_methods().response_format_o(response);
            new Gson().toJson(map, response.getWriter());

      }else if (action.equalsIgnoreCase("get_pro_qty")) {
            int quantity = new Dao_product().get_quantity(Integer.parseInt(request.getParameter("txt_product")));
            
            Map<String, Integer> quant = new HashMap<>();
            quant.put("get_pro_qty", quantity);

            new Pub_methods().response_format_o(response);
            new Gson().toJson(quant, response.getWriter());

        } else if (action.equalsIgnoreCase("Frm_purchase")) {
            forward = LIST_PURCHASE;
            request.setAttribute("purchases", dao_purchase.getAllpurchase());
        } else {
            forward = INSERT_OR_EDIT;
        }
        //</editor-fold>

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        Mdl_purchase mdl_purchase = new Mdl_purchase();
        //<editor-fold defaultstate="collapsed" desc="session code">

        HttpSession session = session = request.getSession();
        int userid = Integer.parseInt(String.valueOf(session.getAttribute("userid")));
        //</editor-fold>

        String purchase_id = "";
        
        try {
            purchase_id = request.getParameter("purchase_id");
            mdl_purchase.setPurchase_id(Integer.parseInt(purchase_id));
        } catch (NumberFormatException e) {
        }

        try {
            //current and purchased quantity
            mdl_purchase.setProduct(Integer.parseInt(request.getParameter("txt_product")));
            mdl_purchase.setPurchase_qty(Integer.parseInt(request.getParameter("txt_purchase_qty")));
            mdl_purchase.setAccount(userid);
            mdl_purchase.setPerson(Integer.parseInt(request.getParameter("txt_person")));
            mdl_purchase.setTotal(0);
           //<editor-fold defaultstate="collapsed" desc=" product items & avarage">
            Mdl_product products = new Mdl_product();
            int product_id = Integer.parseInt(request.getParameter("txt_product"));
            int qty = Integer.parseInt(request.getParameter("txt_purchase_qty"));
            int searched_qty = new Dao_product().get_quantity(product_id);
            int new_quantity = qty + searched_qty;
            products.setQuantity(new_quantity);
            mdl_purchase.setCurrent_qty(new_quantity);
            int entered_unit_cost = new Dao_product().get_uc(product_id);
            int aval_unit_cost = Integer.parseInt(request.getParameter("txt_unit_cost"));
            
            int new_unit_cost = (entered_unit_cost > 0) ? (aval_unit_cost + entered_unit_cost) / 2 : aval_unit_cost;
            products.setUnit_cost(new_unit_cost);
            
            products.setSale_cost(0);
            products.setProduct_id(Integer.parseInt(request.getParameter("txt_product")));

//</editor-fold>
            mdl_purchase.setDate(new Pub_methods().TodayDate());

            if (purchase_id == null || purchase_id.isEmpty()) {
                dao_purchase.add_purchase(mdl_purchase);
                dao_product.update_pro_uc_sc(products);//update prduct

//                System.out.println("update:" + products.getSale_cost());
            } else {

                dao_purchase.update_purchase(mdl_purchase);

            }

        } catch (NumberFormatException ne) {
            System.out.println("Caught error: " + ne.toString());
        }

        PrintWriter pr = response.getWriter();
        pr.print("Saved product successfully");

//        RequestDispatcher view = request.getRequestDispatcher(LIST_PURCHASE);
//        request.setAttribute("purchases", dao_purchase.getAllpurchase());
//        view.forward(request, response);
    }

}
