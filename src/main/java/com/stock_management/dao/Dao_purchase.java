
package com.stock_management.dao;

import com.stock_management.model.Mdl_product;
import com.stock_management.model.Mdl_purchase;
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
public class Dao_purchase {

    //<editor-fold defaultstate="collapsed" desc="comment">
    String purchase_q2 = "select purchase.purchase_id,purchase.product,purchase.purchase_qty,purchase.current_qty,purchase.account,purchase.person,purchase.date, (unit_cost*purchase_qty) as total,"
            + " product.name,product.quantity,product.unit_cost,product.sale_cost,"
            + " person.name as p_name,person.surname,person.category,person.gender,person.phone_number "
            + " from purchase "
            + " join product on product.product_id=purchase.product"
            + " join person on person.person_id=purchase.person";

//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="count by date">
    
    String purc_date_q3 =" select count(*) as tot from purchase "
            + " join product on product.product_id=purchase.product"
            + " join person on person.person_id=purchase.person ";
    
    
    
//</editor-fold>
    private Connection connection;

    public Dao_purchase() {
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

    public void add_purchase(Mdl_purchase purchase) {
        connect_db();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("insert into purchase (product,purchase_qty,current_qty,account,person,date)values (?,?,?,?,?,?)");
            // Parameters start with 1
            preparedStatement.setInt(1, purchase.getProduct());
            preparedStatement.setInt(2, purchase.getPurchase_qty());
            preparedStatement.setInt(3, purchase.getCurrent_qty());
            preparedStatement.setInt(4, purchase.getAccount());
            preparedStatement.setInt(5, purchase.getPerson());
            preparedStatement.setString(6, purchase.getDate());
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

    public void delete_purchase(int purchase_id) {
           connect_db();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("delete from purchase where purchase_id=?");
            // Parameters start with 1
            preparedStatement.setInt(1, purchase_id);
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

    public void update_purchase(Mdl_purchase purchase) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("update purchase set product=?, purchase_qty=?, current_qty=?, account=?, person=?,date=?"
                    + " where purchase_id=?");
            // Parameters start with 1
            preparedStatement.setInt(1, purchase.getProduct());
            preparedStatement.setInt(2, purchase.getPurchase_qty());
            preparedStatement.setInt(3, purchase.getCurrent_qty());
            preparedStatement.setInt(4, purchase.getAccount());
            preparedStatement.setInt(5, purchase.getPerson());
            preparedStatement.setString(6, purchase.getDate());
            preparedStatement.setInt(7, purchase.getPurchase_id());
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

    public List<Mdl_purchase> getAllpurchase() {
        connect_db();
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        List<Mdl_purchase> purchases = new ArrayList<Mdl_purchase>();
        try {
            Statement statement = connection.createStatement();
            rs = statement.executeQuery("select * from purchase limit 80 ");
            while (rs.next()) {
                Mdl_purchase purchase = new Mdl_purchase();
                purchase.setPurchase_id(rs.getInt("purchase_id"));
                purchase.setProduct(rs.getInt("product"));
                purchase.setPurchase_qty(rs.getInt("purchase_qty"));
                purchase.setCurrent_qty(rs.getInt("current_qty"));
                purchase.setAccount(rs.getInt("account"));
                purchase.setPerson(rs.getInt("person"));
                purchase.setDate(rs.getString("date"));
                purchase.setTotal(rs.getFloat("total"));
                purchases.add(purchase);
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
        return purchases;
    }
      public int get_count_Purchase() {
        connect_db();
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        int tot =0; 
        try {
            preparedStatement = connection.prepareStatement("select count(*) as tot from purchase");
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                tot= rs.getInt("tot");
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

    public Mdl_purchase getPurchaseByid(int purchase_id) {
        connect_db();
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        Mdl_purchase purchase = new Mdl_purchase();
        try {
            preparedStatement = connection.prepareStatement("select * from purchase where purchase_id=?");
            preparedStatement.setInt(1, purchase_id);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                purchase.setPurchase_id(rs.getInt("purchase_id"));
                purchase.setProduct(rs.getInt("product"));
                purchase.setPurchase_qty(rs.getInt("purchase_qty"));
                purchase.setCurrent_qty(rs.getInt("current_qty"));
                purchase.setAccount(rs.getInt("account"));
                purchase.setPerson(rs.getInt("person"));
                purchase.setDate(rs.getString("date"));
                purchase.setTotal(rs.getFloat("total"));
                
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

        return purchase;
    }

    //addon
    
    public List<Mdl_purchase> get_pur_prd_prs_by_dt(String date1, String date2, int start, int end) {
        connect_db();
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        List<Mdl_purchase> purchases = new ArrayList<>();
        
        try {
            String sql = (purchase_q2 + " where purchase.date>=? and purchase.date<=? limit "+ start+","+end);
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, date1);
            preparedStatement.setString(2, date2);
            rs = preparedStatement.executeQuery();
         
            while (rs.next()) {
                Mdl_purchase purchase = new Mdl_purchase();
                purchase.setPurchase_id(rs.getInt("purchase_id"));
                purchase.setProduct(rs.getInt("product"));
                purchase.setPurchase_qty(rs.getInt("purchase_qty"));
                purchase.setCurrent_qty(rs.getInt("current_qty"));
                purchase.setAccount(rs.getInt("account"));
                purchase.setPerson(rs.getInt("person"));
                purchase.setDate(rs.getString("date"));
                purchase.setTotal(rs.getFloat("total"));

                //pruduct
                purchase.setName(rs.getString("name"));
                purchase.setQuantity(rs.getInt("quantity"));
                purchase.setUnit_cost(rs.getInt("unit_cost"));
                purchase.setSale_cost(rs.getInt("sale_cost"));
                //person
                purchase.setP_name(rs.getString("p_name"));
                purchase.setSurname(rs.getString("surname"));
                purchase.setCategory(rs.getInt("category"));
                purchase.setGender(rs.getString("gender"));
                purchase.setPhone_number(rs.getString("phone_number"));
                purchases.add(purchase);
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
        return purchases;
    }
    
    
    
     public int get_count_by_dt(String date1, String date2 ) {
        connect_db();
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        int tot = 0;
       
        try {
            String sql = (purc_date_q3 + " where purchase.date>=? and purchase.date<=? ");
            preparedStatement=connection.prepareStatement(sql);
          
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
     
    public List<Mdl_purchase> get_pur_prd_prs_by_p_nm(String name) {
        connect_db();
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        List<Mdl_purchase> purchases = new ArrayList<>();

        try {
            String sql = (purchase_q2 + " where product.name=? limit 80 ");
            preparedStatement.setString(1, name);
            preparedStatement = connection.prepareStatement(sql);
            rs=preparedStatement.executeQuery();
            while (rs.next()) {
                Mdl_purchase purchase = new Mdl_purchase();
                purchase.setPurchase_id(rs.getInt("sale_id"));
                purchase.setProduct(rs.getInt("product"));
                purchase.setPurchase_qty(rs.getInt("purchase_qty"));
                purchase.setCurrent_qty(rs.getInt("current_qty"));
                purchase.setAccount(rs.getInt("account"));
                purchase.setPerson(rs.getInt("person"));
                purchase.setDate(rs.getString("date"));
                purchase.setTotal(rs.getFloat("total"));

                //pruduct
                purchase.setName(rs.getString("name"));
                purchase.setQuantity(rs.getInt("quantity"));
                purchase.setUnit_cost(rs.getInt("unit_cost"));
                purchase.setSale_cost(rs.getInt("sale_cost"));
                //person
                purchase.setP_name(rs.getString("p_name"));
                purchase.setSurname(rs.getString("surname"));
                purchase.setCategory(rs.getInt("category"));
                purchase.setGender(rs.getString("gender"));
                purchase.setPhone_number(rs.getString("phone_number"));
                purchases.add(purchase);
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
        return purchases;
    }

    public List<Mdl_purchase> get_pur_prd_prs(int start,int end) {
        connect_db();
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        List<Mdl_purchase> purchases = new ArrayList<>();

        try {
            String sql = (purchase_q2 + " limit "+start+","+end);
            System.out.println(this.getClass().getName()+"  - "+ start+" - "+end);
            preparedStatement = connection.prepareStatement(sql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Mdl_purchase purchase = new Mdl_purchase();
                purchase.setPurchase_id(rs.getInt("purchase_id"));
                purchase.setProduct(rs.getInt("product"));
                purchase.setPurchase_qty(rs.getInt("purchase_qty"));
                purchase.setCurrent_qty(rs.getInt("current_qty"));
                purchase.setAccount(rs.getInt("account"));
                purchase.setPerson(rs.getInt("person"));
                purchase.setDate(rs.getString("date"));
                purchase.setTotal(rs.getFloat("total"));

                //pruduct
                purchase.setName(rs.getString("name"));
                purchase.setQuantity(rs.getInt("quantity"));
                purchase.setUnit_cost(rs.getInt("unit_cost"));
                purchase.setSale_cost(rs.getInt("sale_cost"));
                //person
                purchase.setP_name(rs.getString("p_name"));
                purchase.setSurname(rs.getString("surname"));
                purchase.setCategory(rs.getInt("category"));
                purchase.setGender(rs.getString("gender"));
                purchase.setPhone_number(rs.getString("phone_number"));
                purchases.add(purchase);
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
        return purchases;
    }
    
    public List<Mdl_purchase> get_pur_prd() {
        connect_db();
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        List<Mdl_purchase> purchases = new ArrayList<>();

        try {
            String sql = (purchase_q2 );
            preparedStatement = connection.prepareStatement(sql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Mdl_purchase purchase = new Mdl_purchase();
                purchase.setPurchase_id(rs.getInt("purchase_id"));
                purchase.setProduct(rs.getInt("product"));
                purchase.setPurchase_qty(rs.getInt("purchase_qty"));
                purchase.setCurrent_qty(rs.getInt("current_qty"));
                purchase.setAccount(rs.getInt("account"));
                purchase.setPerson(rs.getInt("person"));
                purchase.setDate(rs.getString("date"));
                purchase.setTotal(rs.getFloat("total"));

                //pruduct
                purchase.setName(rs.getString("name"));
                purchase.setQuantity(rs.getInt("quantity"));
                purchase.setUnit_cost(rs.getInt("unit_cost"));
                purchase.setSale_cost(rs.getInt("sale_cost"));
                //person
                purchase.setP_name(rs.getString("p_name"));
                purchase.setSurname(rs.getString("surname"));
                purchase.setCategory(rs.getInt("category"));
                purchase.setGender(rs.getString("gender"));
                purchase.setPhone_number(rs.getString("phone_number"));
                purchases.add(purchase);
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
        return purchases;
    }

}
