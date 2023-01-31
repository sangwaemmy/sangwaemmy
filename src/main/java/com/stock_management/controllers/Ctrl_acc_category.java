
package com.stock_management.controllers;

import com.stock_management.dao.Dao_acc_category;
import com.stock_management.model.Mdl_acc_category;
import java.io.IOException;
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
@WebServlet(name = "/acc_category", urlPatterns = {"/acc_category"})
public class Ctrl_acc_category extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static String INSERT_OR_EDIT = "/Mdl_acc_category.jsp";
    private static String LIST_ACCOUNT_CATEGORY = "forms/frm_acc_category.jsp";
    private Dao_acc_category dao_acc_category;

    public Ctrl_acc_category() {
        super();
        dao_acc_category = new Dao_acc_category();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward = "";
        String action = request.getParameter("action");

        if (action.equalsIgnoreCase("delete")) {
            int acc_category_id = Integer.parseInt(request.getParameter("acc_category_id"));
            dao_acc_category.delete_acc_category(acc_category_id);
            forward = LIST_ACCOUNT_CATEGORY;
            request.setAttribute("acc_categorys", dao_acc_category.getAllacc_category());
        } else if (action.equalsIgnoreCase("edit")) {
            forward = LIST_ACCOUNT_CATEGORY;
            int acc_category_id = Integer.parseInt(request.getParameter("acc_category_id"));
            Mdl_acc_category mdl_acc_category = dao_acc_category.getAcc_categoryByid(acc_category_id);
            request.setAttribute("acc_category", mdl_acc_category);
            request.setAttribute("acc_categorys", dao_acc_category.getAllacc_category());
        } else if (action.equalsIgnoreCase("Frm_account_category")) {
            forward = LIST_ACCOUNT_CATEGORY;
            request.setAttribute("acc_categorys", dao_acc_category.getAllacc_category());
        } else {
            forward = INSERT_OR_EDIT;
        }

        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Mdl_acc_category mdl_acc_category = new Mdl_acc_category();
        try {
            mdl_acc_category.setAcc_category_id(Integer.parseInt(request.getParameter("txt_acc_category_id")));
        } catch (NumberFormatException ne) {
        }
        mdl_acc_category.setName(request.getParameter("txt_name"));
        String acc_category_id = request.getParameter("acc_category_id");
        if (acc_category_id == null || acc_category_id.isEmpty()) {
            dao_acc_category.add_acc_category(mdl_acc_category);
        } else {
            mdl_acc_category.setAcc_category_id(Integer.parseInt(acc_category_id));
            dao_acc_category.update_acc_category(mdl_acc_category);
        }
        RequestDispatcher view = request.getRequestDispatcher(LIST_ACCOUNT_CATEGORY);
        request.setAttribute("account_categorys", dao_acc_category.getAllacc_category());
        view.forward(request, response);
    }
}
