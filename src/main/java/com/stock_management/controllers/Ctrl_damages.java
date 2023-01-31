package com.stock_management.controllers;

import com.google.gson.Gson;
import com.stock_management.dao.Dao_damages;
import com.stock_management.dao.Dao_product;
import com.stock_management.dao.Pub_methods;
import com.stock_management.model.Mdl_damages;
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
 * @author Admin
 */
@WebServlet(name = "/damages", urlPatterns = {"/damages"})
public class Ctrl_damages extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static String INSERT_OR_EDIT = "/Mdl_damages.jsp";
    private static String LIST_DAMAGES = "forms/frm_damages.jsp";
    private Dao_damages dao_damages;
    private Dao_product dao_product;
    Pagination pg = new Pagination();

    public Ctrl_damages() {
        super();
        dao_damages = new Dao_damages();
        dao_product = new Dao_product();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        pg.setStart((request.getParameter("start") != null) ? Integer.parseInt(request.getParameter("start")) : 1);

        String forward = "";
        String action = request.getParameter("action");
        Map<String, List<Mdl_damages>> map = new HashMap<>();

        if (action.equalsIgnoreCase("delete")) {
            int damage_id = Integer.parseInt(request.getParameter("damage_id"));
            dao_damages.delete_damages(damage_id);
            forward = LIST_DAMAGES;
            response.getWriter().print("deleted successfully");

        } else if (action.equalsIgnoreCase("edit")) {
//            System.out.println("We are the backend");
            forward = LIST_DAMAGES;
            int damage_id = Integer.parseInt(request.getParameter("damage_id"));
            Mdl_damages mdl_damages = dao_damages.getDamagesByid(damage_id);
            request.setAttribute("damage", mdl_damages);
            request.setAttribute("damages", dao_damages.getAlldamages());

            //damages
            Map<String, Object> damage_map = new HashMap<>();
            damage_map.put("damages", mdl_damages);
            new Gson().toJson(damage_map, response.getWriter());

            //get_dam_prdct_prs_date
        } else if (action.equalsIgnoreCase("get_dam_prdct_prs_date")) {
            forward = LIST_DAMAGES;
            String date1 = request.getParameter("txt_date1");
            String date2 = request.getParameter("txt_date2");
            List<Mdl_damages> mdl_damageses2 = dao_damages.get_dam_prd_prs_dt(date1, date2);
            map.put("get_dam_prd_prs_dt", mdl_damageses2);
            new Pub_methods().response_format_o(response);
            new Gson().toJson(map, response.getWriter());
            //get_dam_prdct_prs
        } else if (action.equalsIgnoreCase("get_dam_prdct_prs")) {
            forward = LIST_DAMAGES;
            List<Mdl_damages> mdl_damageses2 = dao_damages.get_dam_prd_prs(pg.getStart(), pg.getEnd());
            List<Mdl_product> mdl_prod_cbo = dao_product.getAllprod_cbo();
            request.setAttribute("get_dam_prdct_prs", mdl_damageses2);
            request.setAttribute("get_all_products", mdl_prod_cbo);
            // count total pages before searching
            request.setAttribute("tot", dao_damages.get_count_dam());
            request.setAttribute("start", request.getParameter("start"));
            map.put("get_dam_prd_prs", mdl_damageses2);
            RequestDispatcher view = request.getRequestDispatcher(forward);
            view.forward(request, response);
            new Pub_methods().response_format_o(response);
            new Gson().toJson(map, response.getWriter());
        } else if (action.equalsIgnoreCase("get_dam_prd_prs_p_nm")) {//
            forward = LIST_DAMAGES;
//            String name = request.getParameter("txt_name");
            String name = request.getParameter("pr_name");
            List<Mdl_damages> mdl_damageses2 = dao_damages.get_dam_prd_prs_p_nm(name, pg.getStart(), pg.getEnd());
            map.put("get_dam_prd_prs_nm", mdl_damageses2);
            request.setAttribute("pr_name", name);
            request.setAttribute("byname", "yes");
            request.setAttribute("start", request.getParameter("start"));
            // count total pages by searching
            request.setAttribute("tot", dao_damages.get_count_by_nm(name));
            request.setAttribute("damage_by_name", dao_damages.get_dam_prd_prs_p_nm(name, pg.getStart(), pg.getEnd()));
            RequestDispatcher view = request.getRequestDispatcher(forward);
            view.forward(request, response);
        } else if (action.equalsIgnoreCase("Frm_damage")) {
            forward = LIST_DAMAGES;
            request.setAttribute("damages", dao_damages.getAlldamages());
        } else if (action.equalsIgnoreCase("damage_by_name")) {//
            forward = LIST_DAMAGES;
            String pr_name = request.getParameter("pr_name");
            request.setAttribute("damage_by_name", dao_damages.get_dam_prd_prs_p_nm(pr_name, pg.getStart(), pg.getEnd()));
            request.setAttribute("tot", dao_damages.get_count_by_nm(pr_name));
            request.setAttribute("byname", "yes");
            request.setAttribute("pr_name", pr_name);
            request.setAttribute("start", request.getParameter("start"));
            RequestDispatcher view = request.getRequestDispatcher(forward);
            view.forward(request, response);
        } else {
            forward = INSERT_OR_EDIT;
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = session = request.getSession();
        int userid = Integer.parseInt(String.valueOf(session.getAttribute("userid")));
        System.out.println("reached to backend");
        Mdl_damages mdl_damages = new Mdl_damages();
        PrintWriter pr = response.getWriter();
        try {
            mdl_damages.setDamage_id(Integer.parseInt(request.getParameter("txt_damage_id")));
        } catch (NumberFormatException ne) {
        }
        try {
            mdl_damages.setProduct(Integer.parseInt(request.getParameter("txt_product")));
            mdl_damages.setDamage_qty(Integer.parseInt(request.getParameter("txt_damage_qty")));
            mdl_damages.setAccount(userid);
            String mydate = new Pub_methods().TodayDate();
            mdl_damages.setDate(mydate);
            Mdl_product products = new Mdl_product();
            int prodid = Integer.parseInt(request.getParameter("txt_product"));
            int damage_qty = 0;
            int availa_qty = 0;
            int new_qty = 0;
            availa_qty = new Dao_product().get_quantity(prodid);
            damage_qty = Integer.parseInt(request.getParameter("txt_damage_qty"));
            new_qty = availa_qty - damage_qty;
            products.setQuantity(new_qty);
            products.setProduct_id(Integer.parseInt(request.getParameter("txt_product")));
            
            String damage_id = request.getParameter("damage_id");
            
            if (damage_qty > availa_qty) {
                pr.print("there is not enough quantity in the stock!" + "\n" + "avalaible: " + availa_qty);
                
            } else if (damage_id == null || damage_id.isEmpty()) {
                
                dao_damages.add_damages(mdl_damages);
                dao_product.update_quatnity(products);//update prduct
                
                pr.print("Saved damges successfully");
                
            } else {
                mdl_damages.setDamage_id(Integer.parseInt(damage_id));
                
                dao_damages.update_damages(mdl_damages);
                
                pr.print("Saved damages successfully");
            }
            
        } catch (NumberFormatException ne) {
            System.out.println("Caught error 2: " + ne.toString());
            ne.printStackTrace();
        }
    }
}
