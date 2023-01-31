/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stock_management.controllers;

import com.google.gson.Gson;
import com.stock_management.dao.Dao_debts;
import com.stock_management.dao.Dao_expense;
import com.stock_management.dao.Dao_paydebts;
import com.stock_management.dao.Dao_person;
import com.stock_management.dao.Dao_product;
import com.stock_management.dao.Dao_sales;
import com.stock_management.dao.Pub_methods;
import com.stock_management.model.Mdl_debts;
import com.stock_management.model.Mdl_paydebts;
import com.stock_management.model.Mdl_person;
import com.stock_management.model.Mdl_product;
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
 *
 * @author Etienne
 */
@WebServlet(name = "/paydebts", urlPatterns = {"/paydebts"})
public class Ctrl_paydebts extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static String INSERT_OR_EDIT = "/Mdl_paydebts.jsp";
    private static String LIST_PAYDEBTS = "forms/frm_paydebts.jsp";
    private Dao_paydebts dao_paydebts;
    private Dao_product dao_product;
    private Dao_person dao_person;
    private Dao_sales dao_sales;
    private Dao_expense dao_expense;

    public Ctrl_paydebts() {
        super();
        dao_paydebts = new Dao_paydebts();
        dao_product = new Dao_product();
        dao_person = new Dao_person();
        dao_sales = new Dao_sales();
        dao_expense = new Dao_expense();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward = "";
        String action = request.getParameter("action");
        Map<String, List<Mdl_paydebts>> map = new HashMap<>();

        if (action.equalsIgnoreCase("delete")) {
            int paydebts_id = Integer.parseInt(request.getParameter("paydebts_id"));
            dao_paydebts.delete_paydebts(paydebts_id);
            forward = LIST_PAYDEBTS;

            request.setAttribute("paydebt", dao_paydebts.getAlldebt_pyt());

            response.getWriter().print("deleted successfully");

        } else if (action.equalsIgnoreCase("edit")) {
            forward = LIST_PAYDEBTS;
            int paydebts_id = Integer.parseInt(request.getParameter("paydebts_id"));

            Mdl_paydebts mdl_paydebts = dao_paydebts.getPaydebtsByid(paydebts_id);

            request.setAttribute("paydebt", mdl_paydebts);
            request.setAttribute("paydebts", dao_paydebts.getAlldebt_pyt());

            List<Mdl_paydebts> mdl_paydebtses = dao_paydebts.getAlldebt_pyt();

            map.put("paydebt", mdl_paydebtses);

            Map<String, Object> paydebts_map = new HashMap<>();
            paydebts_map.put("paydebts", mdl_paydebts);

            new Gson().toJson(paydebts_map, response.getWriter());

        } else if (action.equalsIgnoreCase("Frm_paydebts")) {
            forward = LIST_PAYDEBTS;
            List<Mdl_paydebts> mdl_paydebtses = dao_paydebts.getAlldebt_pyt();
            List<Mdl_product> mdl_products = dao_product.getAllprod_cbo();
            List<Mdl_person> mdl_persons = dao_person.getAllperson_by_category("customer");

            request.setAttribute("frm_paydebts", mdl_paydebtses);
            request.setAttribute("get_all_products", mdl_products);
            request.setAttribute("customers", mdl_persons);

            RequestDispatcher view = request.getRequestDispatcher(forward);
            view.forward(request, response);

        } else if (action.equals("daily_report")) {
            forward = "forms/frm_daily_report.jsp";
            String date1 = request.getParameter("txt_date1");
            String date2 = request.getParameter("txt_date2");
            if (date1 == null) {
                date1 = new Pub_methods().TodayDate();
                date2 = new Pub_methods().TodayDate();
            }

            int sales = new Dao_paydebts().get_total_sales(date1, date2);
            int payments = new Dao_paydebts().get_total_paydebts(date1, date2);
            int expenses = new Dao_paydebts().get_total_expenses(date1, date2);
            int debts = new Dao_paydebts().get_total_debts(date1, date2);
            int take_to_bank = sales + payments - (debts + expenses);

            System.out.println("Sales: " + sales);
            System.out.println("payments: " + payments);
            System.out.println("debts: " + debts);
            System.out.println("expense: " + expenses);
            System.out.println("take_to_bank: " + take_to_bank);
            System.out.println("-----------");
            System.out.println("Date1: "+ date1);
            System.out.println("Date2: "+ date2);
            
            request.setAttribute("sales", sales);
            request.setAttribute("payments", payments);
            request.setAttribute("expenses", expenses);
            request.setAttribute("debts", debts);
            request.setAttribute("take_to_bank", take_to_bank);

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
        Mdl_paydebts mdl_paydebts = new Mdl_paydebts();
        int amount_paid = Integer.parseInt(request.getParameter("txt_amt_paid"));
        try {
            mdl_paydebts.setPaydebts_id(Integer.parseInt(request.getParameter("txt_paydebts_id")));
        } catch (NumberFormatException ne) {
        }
        mdl_paydebts.setPerson(Integer.parseInt(request.getParameter("txt_person")));
        mdl_paydebts.setProduct(Integer.parseInt(request.getParameter("txt_product")));
        mdl_paydebts.setAmt_paid(amount_paid);
        mdl_paydebts.setAmt_remaining(0);
        mdl_paydebts.setAccount(userid);

        String mydate = new Pub_methods().TodayDate();
        mdl_paydebts.setDate_paid(mydate);

        String paydebts_id = request.getParameter("paydebts_id");

        if (paydebts_id == null || paydebts_id.isEmpty()) {

            dao_paydebts.add_paydebts(mdl_paydebts);
            pr.print("Saved paydebts successfully");

            //get total debt by customer
            int total_amount = new Dao_debts().getDebtsBy_customer(userid).getTotal_debts();
            int remaining = total_amount - amount_paid;
        } else {
            mdl_paydebts.setPaydebts_id(Integer.parseInt(paydebts_id));
            dao_paydebts.update_paydebts(mdl_paydebts);

            pr.print("Updated pay debts successfully");
        }

    }
}
