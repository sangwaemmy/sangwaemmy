package com.stock_management.controllers;

import com.stock_management.dao.Dao_account;
import com.stock_management.model.Mdl_account;
import com.stock_management.model.Mdl_login;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Admin
 */
@WebServlet(name = "Ctrl_login", urlPatterns = {"/login"})
public class Ctrl_login extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //  authenticate on click login
        String username = request.getParameter("username");
        String pasw = request.getParameter("password");
        
        Mdl_login mdl_login = new Mdl_login();
        mdl_login.setUsername(username);
        mdl_login.setPassword(pasw);
        
        Mdl_account mdl_userfound = new Dao_account().get_login(mdl_login);
        PrintWriter pr = response.getWriter();
        System.out.println("account: "+mdl_userfound.getAccount_id());
        if (mdl_userfound.getAccount_id()>0) {
            HttpSession session = request.getSession(true);
            session.setAttribute("userid", mdl_userfound.getAccount_id());
            session.setAttribute("name", mdl_userfound.getName());
            pr.print("success");
        } else {
            pr.print("Login failed");
        }

    }

}
