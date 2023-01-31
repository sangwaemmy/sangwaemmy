package com.stock_management.controllers;

import com.stock_management.dao.Dao_account;
import com.stock_management.model.Mdl_account;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Etienne
 */
@WebServlet(name = "/account", urlPatterns = {"/account"})
public class Ctrl_account extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static String INSERT_OR_EDIT = "/Mdl_account.jsp";
    private static String LIST_ACCOUNT = "forms/frm_account.jsp";
    private Dao_account dao_account;

    public Ctrl_account() {
        super();
        dao_account = new Dao_account();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward = "";
        String action = request.getParameter("action");
        if (action.equalsIgnoreCase("delete")) {
            int account_id = Integer.parseInt(request.getParameter("account_id"));
            dao_account.delete_account(account_id);
            forward = LIST_ACCOUNT;
            request.setAttribute("accounts", dao_account.getAllaccount());
        } else if (action.equalsIgnoreCase("edit")) {
            forward = LIST_ACCOUNT;
            int account_id = Integer.parseInt(request.getParameter("account_id"));
            Mdl_account mdl_account = dao_account.getAccountByid(account_id);
            request.setAttribute("account", mdl_account);
            request.setAttribute("accounts", dao_account.getAllaccount());
        } else if (action.equalsIgnoreCase("Frm_account")) {
            forward = LIST_ACCOUNT;
            request.setAttribute("accounts", dao_account.getAllaccount());
        } else {
            forward = INSERT_OR_EDIT;
        }
        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Mdl_account mdl_account = new Mdl_account();
        try {
            mdl_account.setAccount_id(Integer.parseInt(request.getParameter("txt_account_id")));
        } catch (NumberFormatException ne) {
        }
        mdl_account.setUsername(request.getParameter("txt_username"));
        mdl_account.setPassword(request.getParameter("txt_password"));
        mdl_account.setCategory(Integer.parseInt(request.getParameter("txt_category")));
        mdl_account.setPerson(Integer.parseInt(request.getParameter("txt_person")));
        String account_id = request.getParameter("account_id");
        if (account_id == null || account_id.isEmpty()) {
            dao_account.add_account(mdl_account);
        } else {
            mdl_account.setAccount_id(Integer.parseInt(account_id));
            dao_account.update_account(mdl_account);
        }
        RequestDispatcher view = request.getRequestDispatcher(LIST_ACCOUNT);
        request.setAttribute("accounts", dao_account.getAllaccount());
        view.forward(request, response);
    }
}
