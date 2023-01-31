package com.stock_management.controllers;

import com.google.gson.Gson;
import com.stock_management.dao.Dao_product;
import com.stock_management.model.Mdl_product;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
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
@WebServlet(name = "/product", urlPatterns = {"/product"})

public class Ctrl_product extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static String INSERT_OR_EDIT = "/Mdl_product.jsp";
    private static String LIST_PRODUCT = "forms/frm_product.jsp";
    private Dao_product dao_product;

    Pagination pg = new Pagination();

    public Ctrl_product() {
        super();
        dao_product = new Dao_product();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //<editor-fold defaultstate="collapsed" desc="if else on pagination">

        pg.setStart((request.getParameter("start") != null) ? Integer.parseInt(request.getParameter("start")) : 1);
        //</editor-fold>

//        String page = "";

        String forward = "";
        String action = request.getParameter("action");

        if (action.equalsIgnoreCase("delete")) {
            int product_id = Integer.parseInt(request.getParameter("product_id"));
            dao_product.delete_product(product_id);
            forward = LIST_PRODUCT;
            request.setAttribute("product", dao_product.getAllproduct(pg.getStart(), pg.getEnd()));
            
        } else if (action.equalsIgnoreCase("edit")) {
//            System.out.println("product:" + request.getParameter("product_id"));
            forward = LIST_PRODUCT;
            
            int product_id = Integer.parseInt(request.getParameter("product_id"));
            Mdl_product mdl_product = dao_product.getProductByid(product_id);

            request.setAttribute("product", mdl_product);
            request.setAttribute("products", dao_product.getAllproduct(pg.getStart(), pg.getEnd()));

            Map<String, Object> product_map = new HashMap<>();
            product_map.put("products", mdl_product);
            new Gson().toJson(product_map, response.getWriter());

        } else if (action.equalsIgnoreCase("Frm_pruduct")) {
            forward = LIST_PRODUCT;
            request.setAttribute("products", dao_product.getAllproduct(pg.getStart(), pg.getEnd()));
            request.setAttribute("tot", dao_product.get_count_Products());
            request.setAttribute("start", request.getParameter("start"));//test 
            RequestDispatcher view = request.getRequestDispatcher(forward);
            view.forward(request, response);

        } else if (action.equalsIgnoreCase("prod_by_name")) {
            forward = LIST_PRODUCT;
            String name = request.getParameter("prod_name");
            request.setAttribute("prod_by_name", dao_product.getprod_by_name(name));
            request.setAttribute("prod_name", name);

            RequestDispatcher view = request.getRequestDispatcher(forward);
            view.forward(request, response);

        } else {
            forward = INSERT_OR_EDIT;
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        setAccess(response);
        Mdl_product mdl_product = new Mdl_product();
        try {
            mdl_product.setProduct_id(Integer.parseInt(request.getParameter("txt_product_id")));
        } catch (NumberFormatException ne) {
        }
        mdl_product.setName(request.getParameter("txt_name"));
        mdl_product.setQuantity(0);
        mdl_product.setUnit_cost(0);
        mdl_product.setSale_cost(0);
        mdl_product.setTotal(0);
//        int qty =Integer.parseInt(request.getParameter("txt_quantity")) ;
//        int unit_cost =Integer.parseInt(request.getParameter("txt_unit_cost"));
//        float Total =0;
//        Total = qty*unit_cost;

        String product_id = request.getParameter("product_id");
        if (product_id == null || product_id.isEmpty()) {
            dao_product.add_product(mdl_product);
        } else {
            mdl_product.setProduct_id(Integer.parseInt(product_id));
            dao_product.update_product(mdl_product);
        }

        PrintWriter pr = response.getWriter();
        pr.print("Saved product successfully");

    }

    private void setAccess(HttpServletResponse response) {
        response.addHeader("Access-Control-Allow-Origin", "http://localhost:9080/Applicaation_headers");
        response.setStatus(HttpServletResponse.SC_OK);
        response.addHeader("Access-Control-Allow-MethodsS", "POST");
        response.addHeader("Access-Control-Allow-MethodsS", "GET");
    }

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         setAccess(resp);
        
        
    }
    
    

}
