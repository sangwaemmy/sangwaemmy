package com.stock_management.dao;

import com.stock_management.model.Mdl_sales;
import com.stock_management.util.Util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Etienne
 */
public class Dao_sales {

    private Connection connection;
    //<editor-fold defaultstate="collapsed" desc="comment">
    String sales_q1 = "select sales.sale_id,sales.product,sales.sold_qty,sales.current_qty,sales.sale_cost,sales.account,sales.amt_paid,sales.person,sales.date, (sales.sale_cost*sold_qty) as total,"
            + " product.name,product.quantity,product.unit_cost,product.sale_cost as p_sale_cost,"
            + " person.name as p_name,person.surname,person.category,person.gender,person.phone_number "
            + " from sales "
            + "   join product on product.product_id=sales.product"
            + "   join person on person.person_id=sales.person";

//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="count by date">
    String sale_count_date = "select count(*) as tot from sales "
            + "   join product on product.product_id=sales.product"
            + "   join person on person.person_id=sales.person";

//</editor-fold>
    public Dao_sales() {
        connect_db();
    }

    private void connect_db() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/" + Util.db;
            String user = Util.username;
            String passwd = Util.password;
            connection = DriverManager.getConnection(url, user, passwd);
        } catch (SQLException e) {
            System.out.println("Error on db: " + e.toString());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger("The connection error: " + Util.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void add_sales(Mdl_sales sale) {
        connect_db();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("insert into sales (product,sold_qty,current_qty,sale_cost,account,amt_paid,person,date)values (?,?,?,?,?,?,?,?)");
            // Parameters start with 1
            preparedStatement.setInt(1, sale.getProduct());
            preparedStatement.setInt(2, sale.getSold_qty());
            preparedStatement.setInt(3, sale.getCurrent_qty());
            preparedStatement.setInt(4, sale.getSale_cost());
            preparedStatement.setInt(5, sale.getAccount());
            preparedStatement.setInt(6, sale.getAmt_paid());
            preparedStatement.setInt(7, sale.getPerson());
            preparedStatement.setString(8, sale.getDate());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    preparedStatement.close();
                    connection.close();
                }
            } catch (Exception e) {
            }

        }
    }

    public void delete_sales(int sale_id) {
        connect_db();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("delete from sales where sale_id=?");
            // Parameters start with 1
            preparedStatement.setInt(1, sale_id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    preparedStatement.close();
                    connection.close();
                }
            } catch (Exception e) {
            }

        }
    }

    public void update_sales(Mdl_sales sale) {
        connect_db();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("update sales set product=?,sold_qty=?, current_qty=?,sale_cost=?,account=?,amt_paid=?,amt_expected=?,person=?,date=?"
                    + " where sale_id=?");
            // Parameters start with 1
            System.out.println(Dao_sales.class.getName() + " product: " + sale.getProduct());
            preparedStatement.setInt(1, sale.getProduct());
            preparedStatement.setInt(2, sale.getSold_qty());
            preparedStatement.setInt(3, sale.getCurrent_qty());
            preparedStatement.setInt(4, sale.getSale_cost());
            preparedStatement.setInt(5, sale.getAccount());
            preparedStatement.setInt(6, sale.getAmt_paid());
            preparedStatement.setInt(7, sale.getAmt_expected());
            preparedStatement.setInt(8, sale.getPerson());
            preparedStatement.setString(9, sale.getDate());
            preparedStatement.setInt(10, sale.getSale_id());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    preparedStatement.close();
                    connection.close();
                }
            } catch (Exception e) {
            }

        }
    }

    public List<Mdl_sales> getAllsales() {
        connect_db();
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        List<Mdl_sales> sales = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            rs = statement.executeQuery("select * from sales limit 80 ");
            while (rs.next()) {
                Mdl_sales sale = new Mdl_sales();
                sale.setSale_id(rs.getInt("sale_id"));
                sale.setProduct(rs.getInt("product"));
                sale.setSold_qty(rs.getInt("sold_qty"));
                sale.setCurrent_qty(rs.getInt("current_qty"));
                sale.setSale_cost(rs.getInt("sale_cost"));
                sale.setAccount(rs.getInt("account"));
                sale.setAmt_paid(rs.getInt("amt_paid"));
                sale.setAmt_expected(rs.getInt("amt_expected"));
                sale.setPerson(rs.getInt("person"));
                sale.setDate(rs.getString("date"));
                sale.setTotal(rs.getFloat("total"));
                sales.add(sale);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("There is not data or what?" + e.toString());
        } finally {
            try {
                if (connection != null) {
                    preparedStatement.close();
                    rs.close();
                    connection.close();
                }
            } catch (Exception e) {
            }
        }
        return sales;
    }

    public int get_count_sale_dt(String date1, String date2) {
        connect_db();
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        int tot = 0;
        try {
            preparedStatement = connection.prepareStatement("select count(*) as tot from sales where sales.date>=? and sales.date<=? ");
            preparedStatement.setString(1, date1);
            preparedStatement.setString(2, date2);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                tot = rs.getInt("tot");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    preparedStatement.close();
                    connection.close();
                }
            } catch (Exception e) {
            }
        }

        return tot;
    }

    public Mdl_sales getSalesByid(int sale_id) {
        connect_db();
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        Mdl_sales sale = new Mdl_sales();
        try {
            preparedStatement = connection.prepareStatement("select * from sales where sale_id=?");
            preparedStatement.setInt(1, sale_id);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                sale.setSale_id(rs.getInt("sale_id"));
                sale.setProduct(rs.getInt("product"));
                sale.setSold_qty(rs.getInt("sold_qty"));
                sale.setCurrent_qty(rs.getInt("current_qty"));
                sale.setSale_cost(rs.getInt("sale_cost"));
                sale.setAccount(rs.getInt("account"));
                sale.setAmt_paid(rs.getInt("amt_paid"));
                sale.setAmt_expected(rs.getInt("amt_expected"));
                sale.setPerson(rs.getInt("person"));
                sale.setDate(rs.getString("date"));
                sale.setTotal(rs.getFloat("total"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    preparedStatement.close();
                    connection.close();
                }
            } catch (Exception e) {
            }
        }

        return sale;
    }

    //addon
    public List<Mdl_sales> get_sls_prd_ps_by_dt(String date1, String date2, int start, int end) {
        connect_db();
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        List<Mdl_sales> sales = new ArrayList<>();

        try {
            String sql = (sales_q1 + " where sales.date >=? and sales.date <=? limit " + start + "," + end);
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, date1);
            preparedStatement.setString(2, date2);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Mdl_sales sale = new Mdl_sales();
                sale.setSale_id(rs.getInt("sale_id"));
                sale.setProduct(rs.getInt("product"));
                sale.setSold_qty(rs.getInt("sold_qty"));
                sale.setCurrent_qty(rs.getInt("current_qty"));
                sale.setSale_cost(rs.getInt("sale_cost"));
                sale.setAccount(rs.getInt("account"));
                sale.setAmt_paid(rs.getInt("amt_paid"));
                sale.setAmt_expected(rs.getInt("amt_expected"));
                sale.setPerson(rs.getInt("person"));
                sale.setDate(rs.getString("date"));
                sale.setTotal(rs.getFloat("total"));
                //pruduct
                sale.setName(rs.getString("name"));
                sale.setQuantity(rs.getInt("quantity"));
                sale.setUnit_cost(rs.getInt("unit_cost"));
                sale.setP_sale_cost(rs.getInt("p_sale_cost"));
                //person
                sale.setP_name(rs.getString("p_name"));
                sale.setSurname(rs.getString("surname"));
                sale.setCategory(rs.getInt("category"));
                sale.setGender(rs.getString("gender"));
                sale.setPhone_number(rs.getString("phone_number"));
                sales.add(sale);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("There is not data or what?" + e.toString());
        } finally {
            try {
                if (connection != null) {
                    preparedStatement.close();
                    rs.close();
                    connection.close();
                }
            } catch (Exception e) {
            }
        }
        return sales;
    }

    public int get_count_by_dt(String date1, String date2, int start, int end) {
        connect_db();
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        int tot = 0;

        try {
            String sql = (sale_count_date + " where sales.date>=? and sales.date<=? ");
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, date1);
            preparedStatement.setString(2, date2);
            rs = preparedStatement.executeQuery();

            if (rs.next()) {
                tot = rs.getInt("tot");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    preparedStatement.close();
                    connection.close();
                }
            } catch (Exception e) {
            }
        }

        return tot;
    }

    public List<Mdl_sales> get_sls_prd_ps(int start, int end) {
        connect_db();
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        List<Mdl_sales> sales = new ArrayList<>();

        try {

            String sql = (sales_q1 + " limit " + start + "," + end);
            preparedStatement = connection.prepareStatement(sql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Mdl_sales sale = new Mdl_sales();
                sale.setSale_id(rs.getInt("sale_id"));
                sale.setProduct(rs.getInt("product"));
                sale.setSold_qty(rs.getInt("sold_qty"));
                sale.setCurrent_qty(rs.getInt("current_qty"));
                sale.setSale_cost(rs.getInt("sale_cost"));
                sale.setAccount(rs.getInt("account"));
                sale.setAmt_paid(rs.getInt("amt_paid"));
                sale.setPerson(rs.getInt("person"));
                sale.setDate(rs.getString("date"));
                sale.setTotal(rs.getFloat("total"));

                //pruduct
                sale.setName(rs.getString("name"));
                sale.setQuantity(rs.getInt("quantity"));
                sale.setUnit_cost(rs.getInt("unit_cost"));
                sale.setP_sale_cost(rs.getInt("p_sale_cost"));
                //person
                sale.setP_name(rs.getString("p_name"));
                sale.setSurname(rs.getString("surname"));
                sale.setCategory(rs.getInt("category"));
                sale.setGender(rs.getString("gender"));
                sale.setPhone_number(rs.getString("phone_number"));
                sales.add(sale);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("There is not data or what?" + e.toString());
        } finally {
            try {
                if (connection != null) {
                    preparedStatement.close();
                    rs.close();
                    connection.close();
                }
            } catch (Exception e) {
            }
        }
        return sales;
    }
    public List<Mdl_sales> get_sls_prd() {
        connect_db();
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        List<Mdl_sales> sales = new ArrayList<>();

        try {

            String sql = (sales_q1);
            preparedStatement = connection.prepareStatement(sql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Mdl_sales sale = new Mdl_sales();
                sale.setSale_id(rs.getInt("sale_id"));
                sale.setProduct(rs.getInt("product"));
                sale.setSold_qty(rs.getInt("sold_qty"));
                sale.setCurrent_qty(rs.getInt("current_qty"));
                sale.setSale_cost(rs.getInt("sale_cost"));
                sale.setAccount(rs.getInt("account"));
                sale.setAmt_paid(rs.getInt("amt_paid"));
                sale.setPerson(rs.getInt("person"));
                sale.setDate(rs.getString("date"));
                sale.setTotal(rs.getFloat("total"));

                //pruduct
                sale.setName(rs.getString("name"));
                sale.setQuantity(rs.getInt("quantity"));
                sale.setUnit_cost(rs.getInt("unit_cost"));
                sale.setP_sale_cost(rs.getInt("p_sale_cost"));
                //person
                sale.setP_name(rs.getString("p_name"));
                sale.setSurname(rs.getString("surname"));
                sale.setCategory(rs.getInt("category"));
                sale.setGender(rs.getString("gender"));
                sale.setPhone_number(rs.getString("phone_number"));
                sales.add(sale);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("There is not data or what?" + e.toString());
        } finally {
            try {
                if (connection != null) {
                    preparedStatement.close();
                    rs.close();
                    connection.close();
                }
            } catch (Exception e) {
            }
        }
        return sales;
    }

    public int get_count_ps() {
        connect_db();
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        int tot = 0;

        try {
            String sql = (sale_count_date);
            preparedStatement = connection.prepareStatement(sql);
            rs = preparedStatement.executeQuery();

            if (rs.next()) {
                tot = rs.getInt("tot");
                System.out.println("tot:" + tot);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    preparedStatement.close();
                    connection.close();
                }
            } catch (Exception e) {
            }
        }

        return tot;
    }

    public List<Mdl_sales> get_sls_prd_prs_by_p_nm(String name) {
        connect_db();
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        List<Mdl_sales> sales = new ArrayList<Mdl_sales>();

        try {
            String sql = sales_q1 + " where product.name=? ";
            preparedStatement.setString(1, name);

            preparedStatement = connection.prepareStatement(sql);
            while (rs.next()) {
                Mdl_sales sale = new Mdl_sales();
                sale.setSale_id(rs.getInt("sale_id"));
                sale.setProduct(rs.getInt("product"));
                sale.setSold_qty(rs.getInt("sold_qty"));
                sale.setCurrent_qty(rs.getInt("current_qty"));
                sale.setSale_cost(rs.getInt("sale_cost"));
                sale.setAccount(rs.getInt("account"));
                sale.setAmt_paid(rs.getInt("amt_paid"));
                sale.setPerson(rs.getInt("person"));
                sale.setDate(rs.getString("date"));
                sale.setTotal(rs.getFloat("total"));

                //pruduct
                sale.setName(rs.getString("name"));
                sale.setQuantity(rs.getInt("quantity"));
                sale.setUnit_cost(rs.getInt("unit_cost"));
                sale.setP_sale_cost(rs.getInt("p_sale_cost"));
                //person
                sale.setP_name(rs.getString("p_name"));
                sale.setSurname(rs.getString("surname"));
                sale.setCategory(rs.getInt("category"));
                sale.setGender(rs.getString("gender"));
                sale.setPhone_number(rs.getString("phone_number"));
                sales.add(sale);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("There is not data or what?" + e.toString());
        } finally {
            try {
                if (connection != null) {
                    preparedStatement.close();
                    rs.close();
                    connection.close();
                }
            } catch (Exception e) {
            }
        }
        return sales;
    }

}
