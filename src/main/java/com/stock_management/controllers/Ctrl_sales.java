package com.stock_management.controllers;

import com.google.gson.Gson;
import com.stock_management.dao.Dao_debts;
import com.stock_management.dao.Dao_person;
import com.stock_management.dao.Dao_product;
import com.stock_management.dao.Dao_sales;
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

/**
 *
 * @author Admin
 */
@WebServlet(name = "/sales", urlPatterns = {"/sales"})
public class Ctrl_sales extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static String INSERT_OR_EDIT = "/Mdl_sales.jsp";
    private static String LIST_SALES = "forms/frm_sales.jsp";
    private Dao_sales dao_sales;
    private Dao_product dao_product;
    private Dao_person dao_person;
    Pagination pg = new Pagination();

    public Ctrl_sales() {
        super();
        dao_sales = new Dao_sales();
        dao_product = new Dao_product();
        dao_person = new Dao_person();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward = "";
        String action = request.getParameter("action");

        pg.setStart((request.getParameter("start") != null) ? Integer.parseInt(request.getParameter("start")) : 1);
        System.out.println("pages:" + pg.getStart() + "," + pg.getEnd());
        // map list for all
        Map<String, List<Mdl_sales>> map = new HashMap<>();

        if (action.equalsIgnoreCase("delete")) {
            int sale_id = Integer.parseInt(request.getParameter("sale_id"));
            dao_sales.delete_sales(sale_id);
            forward = LIST_SALES;
            request.setAttribute("sale", dao_sales.getAllsales());
            response.getWriter().print("deleted successfully");

        } else if (action.equalsIgnoreCase("edit")) {
//            System.out.println("eee:" + request.getParameter("sale_id"));
            forward = LIST_SALES;
            int sale_id = Integer.parseInt(request.getParameter("sale_id"));
            Mdl_sales mdl_sales = dao_sales.getSalesByid(sale_id);
            request.setAttribute("sale", mdl_sales);
            request.setAttribute("sales", dao_sales.getAllsales());
            //sales
//            System.out.println("reached to backend");
            request.setAttribute("sale", mdl_sales);

            Map<String, Object> sales_map = new HashMap<>();
            sales_map.put("sales", mdl_sales);
            new Gson().toJson(sales_map, response.getWriter());

            //get_sales_prdct_prs
        } else if (action.equalsIgnoreCase("get_sls_prd_ps")) {
            forward = LIST_SALES;
            List<Mdl_sales> mdl_sales2 = dao_sales.get_sls_prd_ps(pg.getStart(), pg.getEnd());
            List<Mdl_product> mdl_products = dao_product.getAllprod_cbo();
            List<Mdl_person> mdl_persons = dao_person.getAllperson_by_category("customer");

            request.setAttribute("get_sls_prd_ps", mdl_sales2);
            request.setAttribute("get_all_products", mdl_products);
            request.setAttribute("Customers", mdl_persons);
            //count total pages before searching
            request.setAttribute("tot", dao_sales.get_count_ps());
            request.setAttribute("start", request.getParameter("start"));

            map.put("get_sales_prdct_prs", mdl_sales2);

            RequestDispatcher view = request.getRequestDispatcher(forward);
            view.forward(request, response);
            //sort datalist by date
        } else if (action.equalsIgnoreCase("get_sales_prdct_prs")) {
            forward = LIST_SALES;
//            System.out.println("school:" + school_name + "  student: " + student_id);
            String date1 = request.getParameter("txt_date1");
            String date2 = request.getParameter("txt_date2");

            List<Mdl_sales> mdl_sales2 = dao_sales.get_sls_prd_ps_by_dt(date1, date2, pg.getStart(), pg.getEnd());
            request.setAttribute("get_sales_prdct_prs", mdl_sales2);

            map.put("get_sales_prdct_prs", mdl_sales2);

            request.setAttribute("sales_by_date", mdl_sales2);
            //count total pages before searching
            request.setAttribute("tot", dao_sales.get_count_sale_dt(date1, date2));
            request.setAttribute("bydate", "yes");
            request.setAttribute("date1", date1);
            request.setAttribute("date2", date2);
            request.setAttribute("start", request.getParameter("start"));

            RequestDispatcher view = request.getRequestDispatcher(forward);
            view.forward(request, response);

        } else if (action.equalsIgnoreCase("sales_by_date")) {
            forward = LIST_SALES;
            String date1 = request.getParameter("sale_date1");
            String date2 = request.getParameter("sale_date2");
            request.setAttribute("sales_by_date", dao_sales.get_sls_prd_ps_by_dt(date1, date2, pg.getStart(), pg.getEnd()));
            request.setAttribute("sales_by_date", date1);
            request.setAttribute("sales_by_date", date2);

            RequestDispatcher view = request.getRequestDispatcher(forward);
            view.forward(request, response);

            //get_sales_prdct_prs_by_p_nm
        } else if (action.equalsIgnoreCase("get_sales_prdct_prs_by_p_nm")) {
            forward = LIST_SALES;

            String pro_name = request.getParameter("txt_name");

            List<Mdl_sales> mdl_sales2 = dao_sales.get_sls_prd_prs_by_p_nm(pro_name);
            map.put("get_sales_prdct_prs_by_p_nm", mdl_sales2);

            new Pub_methods().response_format_o(response);
            new Gson().toJson(map, response.getWriter());

        } else if (action.equalsIgnoreCase("get_pro_qty")) {
            int quantity = new Dao_product().get_quantity(Integer.parseInt(request.getParameter("txt_product")));
            Map<String, Integer> quant = new HashMap<>();
            quant.put("get_pro_qty", quantity);

            new Pub_methods().response_format_o(response);
            new Gson().toJson(quant, response.getWriter());

        } else if (action.equalsIgnoreCase("get_sl_cost")) {
            int sale_cost = new Dao_product().get_sl_cost(Integer.parseInt(request.getParameter("txt_product")));
            Map<String, Integer> sal_cost = new HashMap<>();
            sal_cost.put("get_sl_cost", sale_cost);

            new Pub_methods().response_format_o(response);
            new Gson().toJson(sal_cost, response.getWriter());

        } else if (action.equalsIgnoreCase("Frm_sale")) {
            forward = LIST_SALES;
            request.setAttribute("sales", dao_sales.getAllsales());
        } else {
            forward = INSERT_OR_EDIT;
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("reached to backend");
        HttpSession session = session = request.getSession();
        int userid = Integer.parseInt(String.valueOf(session.getAttribute("userid")));
        Mdl_sales mdl_sales = new Mdl_sales();
        PrintWriter pr = response.getWriter();
        try {
            mdl_sales.setSale_id(Integer.parseInt(request.getParameter("txt_sale_id")));
        } catch (NumberFormatException ne) {
        }
        try {
            int person = Integer.parseInt(request.getParameter("txt_person"));

            mdl_sales.setProduct(Integer.parseInt(request.getParameter("txt_product")));
            mdl_sales.setSold_qty(Integer.parseInt(request.getParameter("txt_sold_qty")));
            mdl_sales.setSale_cost(Integer.parseInt(request.getParameter("txt_sale_cost")));
            mdl_sales.setPerson(person);
            mdl_sales.setAmt_paid(Integer.parseInt(request.getParameter("txt_amt_paid")));
            mdl_sales.setTotal(0);
            mdl_sales.setAccount(userid);
            String mydate = new Pub_methods().TodayDate();
            mdl_sales.setDate(mydate);
            //<editor-fold defaultstate="collapsed" desc="product items & substration">

            Mdl_product products = new Mdl_product();

            int product_id = Integer.parseInt(request.getParameter("txt_product"));
            int sold_quantity = 0;
            int avalaible_qty = 0;
            int new_quantity = 0;
            int sale_cost_db = new Dao_product().get_sl_cost_byProd(product_id);
            int sale_cost_user = Integer.parseInt(request.getParameter("txt_sale_cost"));

            avalaible_qty = new Dao_product().get_quantity(product_id);
            sold_quantity = Integer.parseInt(request.getParameter("txt_sold_qty"));
            new_quantity = avalaible_qty - sold_quantity;
            mdl_sales.setCurrent_qty(new_quantity);
            products.setQuantity(new_quantity);
            products.setProduct_id(Integer.parseInt(request.getParameter("txt_product")));
            //</editor-fold>

            //<editor-fold defaultstate="collapsed" desc="--Check debt--">
             
            int total_expected = sale_cost_user * sold_quantity;// the sale cost can change when the roduct is being sold. so the system allows the bargaining
            int amt_paid = Integer.parseInt(request.getParameter("txt_amt_paid"));
            int debt_amt = 0;

            if (total_expected > amt_paid) {
                //save the debt
                debt_amt = total_expected - amt_paid;
                //<editor-fold defaultstate="collapsed" desc="--save debt">
                Mdl_debts mdl_debts = new Mdl_debts();

                mdl_debts.setAmount(debt_amt);
                mdl_debts.setAccount(userid);//who is using the system

                mdl_debts.setProduct(product_id);
                int old_total_debt = new Dao_debts().getDebtsBy_customer(person).getTotal_debts();
                int new_total_debt = old_total_debt + debt_amt;

                mdl_debts.setTotal_debts(new_total_debt);
                mdl_debts.setPerson(person);// the customer, is from the person table

                mdl_debts.setDate_debts(mydate);
                new Dao_debts().add_debts(mdl_debts);

                //</editor-fold>
            } else if (amt_paid > total_expected) {
                pr.print("The customer cant more than expected");
            }
//</editor-fold>

            String sale_id = request.getParameter("sale_id");
            if (sold_quantity > avalaible_qty) {
                pr.print("there is not enough quantity in the stock!" + "\n" + "avalaible: " + avalaible_qty);

            } else if (sale_cost_db < sale_cost_user) {
                pr.print("The sale cost mube greater than  : " + sale_cost_db);

            } else if (sale_id == null || sale_id.isEmpty()) {
                dao_sales.add_sales(mdl_sales);
                dao_product.update_quatnity(products);//update prduct
                pr.print("Saved sales successfully");

            } else {

                mdl_sales.setSale_id(Integer.parseInt(sale_id));
                dao_sales.update_sales(mdl_sales);
                pr.print("Update sales successfully");
            }

        } catch (NumberFormatException ne) {
            System.out.println("Caught error 2: " + ne.toString());
            ne.printStackTrace();
        }

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
