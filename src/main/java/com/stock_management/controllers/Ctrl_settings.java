package com.stock_management.controllers;

import com.stock_management.dao.Dao_product;
import com.stock_management.dao.Dao_purchase;
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

/**
 *
 * @author Admin
 */
@WebServlet(name = "Ctrl_settings", urlPatterns = {"/settings"})
public class Ctrl_settings extends HttpServlet {

    private Dao_purchase dao_purchase;
    private Dao_product dao_product;

    public Ctrl_settings() {
        super();

        dao_product = new Dao_product();

    }

    private static final long serialVersionUID = 1L;

    private static String LIST_SETTINGS = "forms/frm_settings.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String forward = "";
        String action = request.getParameter("action");
//        Map<String, List<Mdl_purchase>> map = new HashMap<>();

        if (action.equalsIgnoreCase("settings")) {
            forward = LIST_SETTINGS;

            List<Mdl_product> mdl_products = dao_product.getAllprod_cbo();

//            request.setAttribute("settings", purchase_id);
            request.setAttribute("get_all_products", mdl_products);
            RequestDispatcher view = request.getRequestDispatcher(forward);
            view.forward(request, response);

        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Mdl_purchase mdl_purchase = new Mdl_purchase();
        String purchase_id = "";
        try {
            purchase_id = request.getParameter("purchase_id");
            mdl_purchase.setPurchase_id(Integer.parseInt(purchase_id));
//            System.out.println("purchase:" + purchase_id);
        } catch (NumberFormatException e) {
        }

        try {

            //search uc by prod
            int product_id = Integer.parseInt(request.getParameter("txt_product"));
            int unit_cost = new Dao_product().get_uc(product_id);
            int sale_cost = Integer.parseInt(request.getParameter("txt_sale_cost"));
            
            Mdl_product mdl_product = null;
            if (unit_cost > sale_cost) {//
                PrintWriter pr = response.getWriter();
                pr.print("sale cost must be greater than unit cost");
            } else {
                mdl_product = new Mdl_product();
                mdl_product.setProduct_id(Integer.parseInt(request.getParameter("txt_product")));
                mdl_product.setSale_cost(Integer.parseInt(request.getParameter("txt_sale_cost")));
                
                dao_product.update_sl_cost(mdl_product);
                PrintWriter pr = response.getWriter();
                pr.print("Saved product successfully");
            }

        } catch (NumberFormatException ne) {
            System.out.println("Caught error: " + ne.toString());
        }
    }

}
