
 
 package com.stock_management.controllers;

import com.google.gson.Gson;
import com.stock_management.dao.Dao_expense;
import com.stock_management.dao.Pub_methods;
import com.stock_management.model.Mdl_expense;
import com.stock_management.model.Mdl_paydebts;
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

@WebServlet(name = "/expenses", urlPatterns = {"/expenses"})
public class Ctrl_expense extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static String INSERT_OR_EDIT = "/Mdl_expense.jsp";
    private static String LIST_EXPENSE= "forms/frm_expense.jsp";
    private Dao_expense dao_expense;
   

    public Ctrl_expense() {
        super();
        dao_expense = new Dao_expense();
       
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward = "";
        String action = request.getParameter("action");
        Map<String, List<Mdl_expense>> map = new HashMap<>();

        if (action.equalsIgnoreCase("delete")) {
            int expense_id = Integer.parseInt(request.getParameter("expense_id"));
            dao_expense.delete_expense(expense_id);
            forward = LIST_EXPENSE;
            request.setAttribute("expense", dao_expense.getAllexpense());
            response.getWriter().print("deleted successfully");

        } else if (action.equalsIgnoreCase("edit")) {
            forward = LIST_EXPENSE;
            
             int expense_id = Integer.parseInt(request.getParameter("expense_id"));

            Mdl_expense mdl_expense = dao_expense.getExpenseByid(expense_id);

            request.setAttribute("expense", mdl_expense);
            request.setAttribute("expenses", dao_expense.getAllexpense());

            List<Mdl_expense> mdl_expenses = dao_expense.getAllexpense();

            map.put("expense", mdl_expenses);

            Map<String, Object> expense_map = new HashMap<>();
            expense_map.put("expenses", mdl_expenses);

            new Gson().toJson(expense_map, response.getWriter());

        } else if (action.equalsIgnoreCase("Frm_expenses")) {
            forward = LIST_EXPENSE;
            List<Mdl_expense> mdl_expenses = dao_expense.getAllexpense();
            request.setAttribute("expenses", mdl_expenses);
                                
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
        Mdl_expense mdl_expense = new Mdl_expense();

        try {
            mdl_expense.setExpense_id(Integer.parseInt(request.getParameter("txt_expense_id")));
        } catch (NumberFormatException ne) {
        }
        mdl_expense.setName(request.getParameter("txt_name"));
        mdl_expense.setAmount(Integer.parseInt(request.getParameter("txt_amount")));
    
        mdl_expense.setDate(new Pub_methods().TodayDate());

        String expense_id = request.getParameter("expense_id");

        if (expense_id == null || expense_id.isEmpty()) {

            dao_expense.add_expense(mdl_expense);

            pr.print("Saved Expense successfully");

        } else {
            mdl_expense.setExpense_id(Integer.parseInt(expense_id));
            dao_expense.update_expense(mdl_expense);

            pr.print("Updated expense successfully");
        }

    }
}
