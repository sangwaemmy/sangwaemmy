package com.stock_management.controllers;
import com.google.gson.Gson;
import com.stock_management.dao.Dao_acc_category;
import com.stock_management.dao.Dao_account;
import com.stock_management.dao.Dao_person;
import com.stock_management.model.Mdl_acc_category;
import com.stock_management.model.Mdl_account;
import com.stock_management.model.Mdl_person;
import java.io.IOException;
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
 * @author Etienne
 */
@WebServlet(name = "/Users", urlPatterns = {"/Users"})
public class Ctrl_person extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static String INSERT_OR_EDIT = "/Mdl_person.jsp";
    private static String LIST_PERSON = "forms/frm_users.jsp";
    private Dao_person dao_person;
    private Dao_acc_category dao_acc_category;
    private Dao_account dao_account;
    public Ctrl_person() {
        super();
        dao_person = new Dao_person();
        dao_acc_category = new Dao_acc_category();
        dao_account = new Dao_account();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward = "";
        String action = request.getParameter("action");
        Map<String, List<Mdl_person>> map = new HashMap<>();
        if (action.equalsIgnoreCase("delete")) {
            int person_id = Integer.parseInt(request.getParameter("person_id"));
            dao_person.delete_person(person_id);
            forward = LIST_PERSON;
            request.setAttribute("person", dao_person.getAllperson());
        } else if (action.equalsIgnoreCase("edit")) {
            forward = LIST_PERSON;
            int person_id = Integer.parseInt(request.getParameter("person_id"));
            Mdl_account mdl_account = dao_account.getAccountByperson_id(person_id);
            Mdl_person mdl_person = dao_person.getPersonByid(person_id);
            request.setAttribute("person", mdl_person);
            request.setAttribute("persons", dao_person.getAllperson());
            Map<String, Object> person_map = new HashMap<>();
            person_map.put("persons", mdl_person);
            person_map.put("account", mdl_account);
            new Gson().toJson(person_map, response.getWriter());
            // get_per_cat_acc
        } else if (action.equalsIgnoreCase("get_per_cat_acc")) {
            forward = LIST_PERSON;
            List<Mdl_person> mdl_person2 = dao_person.get_per_cat_acc();
            List<Mdl_acc_category> mdl_acc_categorys = dao_acc_category.getAllacc_category();
            request.setAttribute("get_per_cat_acc", mdl_person2);
            request.setAttribute("Categories", mdl_acc_categorys);
            map.put("get_per_cat_acc", mdl_person2);
            RequestDispatcher view = request.getRequestDispatcher(forward);
            view.forward(request, response);
        } else if (action.equalsIgnoreCase("Frm_person")) {
            forward = LIST_PERSON;
            request.setAttribute("persons", dao_person.getAllperson());
        } else {
            forward = INSERT_OR_EDIT;
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Mdl_person mdl_person = new Mdl_person();
        try {
            mdl_person.setPerson_id(Integer.parseInt(request.getParameter("person_id")));
        } catch (NumberFormatException ne) {
        }
        mdl_person.setName(request.getParameter("txt_name"));
        mdl_person.setSurname(request.getParameter("txt_surname"));
        mdl_person.setCategory(Integer.parseInt(request.getParameter("txt_category")));
        mdl_person.setGender(request.getParameter("txt_gender"));
        mdl_person.setPhone_number(request.getParameter("txt_phone_number"));
        String person_id = request.getParameter("person_id");
        if (person_id == null || person_id.isEmpty()) {
            dao_person.add_person(mdl_person);
            int last_person = new Dao_person().get_last_person().getPerson_id();
            //account
            Mdl_account mdl_account = new Mdl_account();
            try {

                mdl_account.setAccount_id(Integer.parseInt(request.getParameter("txt_account_id")));
            } catch (NumberFormatException e) {
            }
            try {
                mdl_account.setUsername(request.getParameter("txt_username"));
                mdl_account.setPassword(request.getParameter("txt_password"));
                mdl_account.setCategory(Integer.parseInt(request.getParameter("txt_category")));
                mdl_account.setPerson(last_person);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("err:" + e.toString());
            }
            new Dao_account().add_account(mdl_account);
        } else {
            dao_person.update_person(mdl_person);
        }
        response.getWriter().print("Saved User successfully");
    }
}
