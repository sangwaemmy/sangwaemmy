/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stock_management.controllers;

import com.google.gson.Gson;
import com.stock_management.dao.Dao_debts;
import com.stock_management.dao.Dao_person;
import com.stock_management.dao.Dao_product;
import com.stock_management.dao.Pub_methods;
import com.stock_management.model.Mdl_debts;
import com.stock_management.model.Mdl_person;
import com.stock_management.model.Mdl_product;
import com.stock_management.model.Mdl_sales;
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

@WebServlet(name = "/debts", urlPatterns = {"/debts"})
public class Ctrl_debts extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static String INSERT_OR_EDIT = "/Mdl_debts.jsp";
    private static String LIST_DEBTS = "forms/frm_debts.jsp";
    private Dao_debts dao_debts;
    private Dao_product dao_product;
    private Dao_person dao_person;

    public Ctrl_debts() {
        super();
        dao_debts = new Dao_debts();
        dao_product = new Dao_product();
        dao_person = new Dao_person();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward = "";
        String action = request.getParameter("action");
        Map<String, List<Mdl_debts>> map = new HashMap<>();

        if (action.equalsIgnoreCase("delete")) {
            int debts_id = Integer.parseInt(request.getParameter("debts_id"));
            dao_debts.delete_debts(debts_id);
            forward = LIST_DEBTS;
            request.setAttribute("debt", dao_debts.getAlldebts());

            response.getWriter().print("deleted successfully");

        } else if (action.equalsIgnoreCase("edit")) {
            forward = LIST_DEBTS;
            int debts_id = Integer.parseInt(request.getParameter("debts_id"));

            Mdl_debts mdl_debts = dao_debts.getDebtsByid(debts_id);

            request.setAttribute("debt", mdl_debts);
            request.setAttribute("debts", dao_debts.getAlldebts());

            List<Mdl_debts> mdl_debtses = dao_debts.getAlldebts();

            map.put("debt", mdl_debtses);

            Map<String, Object> debts_map = new HashMap<>();
            debts_map.put("debts", mdl_debts);

            new Gson().toJson(debts_map, response.getWriter());

        } else if (action.equalsIgnoreCase("Frm_debts")) {
            forward = LIST_DEBTS;
            List<Mdl_debts> mdl_debtses = dao_debts.getAlldebts();
            List<Mdl_product> mdl_products = dao_product.getAllprod_cbo();
            List<Mdl_person> mdl_persons = dao_person.getAllperson_by_category("customer");

            request.setAttribute("Frm_debts", mdl_debtses);
            request.setAttribute("get_all_products", mdl_products);
            request.setAttribute("customers", mdl_persons);

            RequestDispatcher view = request.getRequestDispatcher(forward);
            view.forward(request, response);

        } else {
            forward = INSERT_OR_EDIT;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = session = request.getSession();
        int userid = Integer.parseInt(String.valueOf(session.getAttribute("userid")));

        PrintWriter pr = response.getWriter();
        Mdl_debts mdl_debts = new Mdl_debts();

        try {
            mdl_debts.setDebts_id(Integer.parseInt(request.getParameter("txt_debts_id")));
        } catch (NumberFormatException ne) {
        }
        mdl_debts.setAmount(Integer.parseInt(request.getParameter("txt_amount")));
        mdl_debts.setAccount(userid);
        mdl_debts.setProduct(Integer.parseInt(request.getParameter("txt_product")));
        mdl_debts.setTotal_debts(0);
        mdl_debts.setPerson(Integer.parseInt(request.getParameter("txt_person")));
        String mydate = new Pub_methods().TodayDate();
        mdl_debts.setDate_debts(mydate);

        String debts_id = request.getParameter("debts_id");

        if (debts_id == null || debts_id.isEmpty()) {

            dao_debts.add_debts(mdl_debts);

            pr.print("Saved debts successfully");

        } else {
            mdl_debts.setDebts_id(Integer.parseInt(debts_id));
            dao_debts.update_debts(mdl_debts);

            pr.print("Updated debts successfully");
        }

    }
}
