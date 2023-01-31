package com.stock_management.dao;

import com.stock_management.model.Mdl_product;
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
public class Dao_product {

    private Connection connection;

    public Dao_product() {
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

    public void add_product(Mdl_product product) {
        connect_db();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("insert into product (name,quantity,unit_cost,sale_cost)values (?,?,?,?)");
            // Parameters start with 1
            preparedStatement.setString(1, product.getName());
            preparedStatement.setInt(2, product.getQuantity());
            preparedStatement.setInt(3, product.getUnit_cost());
            preparedStatement.setInt(4, product.getSale_cost());
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
     

    public void delete_product(int product_id) {
        connect_db();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("delete from product where product_id=?");
            // Parameters start with 1
            preparedStatement.setInt(1, product_id);
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

    public void update_product(Mdl_product product) {
        connect_db();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("update product set name=?,quantity=?, unit_cost=?,sale_cost=? "
                    + " where product_id=?");
            // Parameters start with 1
            preparedStatement.setString(1, product.getName());
            preparedStatement.setInt(2, product.getQuantity());
            preparedStatement.setInt(3, product.getUnit_cost());
            preparedStatement.setInt(4, product.getSale_cost());
            preparedStatement.setInt(5, product.getProduct_id());
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
    public List<Mdl_product> getAllproduct(int start, int end) {
        connect_db();
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        List<Mdl_product> products = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            rs = statement.executeQuery("select *, (unit_cost*quantity) as total  from product limit " + start + "," + end);
            while (rs.next()) {
                Mdl_product product = new Mdl_product();
                product.setProduct_id(rs.getInt("product_id"));
                product.setName(rs.getString("name"));
                product.setQuantity(rs.getInt("quantity"));
                product.setUnit_cost(rs.getInt("unit_cost"));
                product.setSale_cost(rs.getInt("sale_cost"));
                product.setTotal(rs.getFloat("total"));
                products.add(product);
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
        return products;
    }

    public List<Mdl_product> getprod_by_name(String name) {
        connect_db();
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        List<Mdl_product> products = new ArrayList<>();
        try {
            String sql = "select *,(unit_cost*quantity) as total from product where product.name=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Mdl_product product = new Mdl_product();
                product.setProduct_id(rs.getInt("product_id"));
                product.setName(rs.getString("name"));
                product.setQuantity(rs.getInt("quantity"));
                product.setUnit_cost(rs.getInt("unit_cost"));
                product.setSale_cost(rs.getInt("sale_cost"));
                product.setTotal(rs.getInt("total"));
                products.add(product);
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
        return products;
    }
    
    //<editor-fold defaultstate="collapsed" desc="addon for intelligence system">
    public void update_pro_uc_sc(Mdl_product products) {
        connect_db();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("update product set unit_cost=?,sale_cost=?,quantity=? where product_id=?");
            // Parameters start with 1
            preparedStatement.setInt(1, products.getUnit_cost());
            preparedStatement.setInt(2, products.getSale_cost());
            preparedStatement.setInt(3, products.getQuantity());
            preparedStatement.setInt(4, products.getProduct_id());
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
    
     public void update_quatnity(Mdl_product products) {
        connect_db();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("update product set quantity=? where product_id=?");
            // Parameters start with 1
            preparedStatement.setInt(1, products.getQuantity());
            preparedStatement.setInt(2, products.getProduct_id());
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
       public  int get_sl_cost_byProd(int product_id) {
        connect_db();
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
         
        int sale_cost=0;
        try {
            String sql = "select  sale_cost  from product where product_id=? ";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, product_id);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                 
                sale_cost= rs.getInt("sale_cost");
               
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
        return sale_cost;
    }
     
       
        public void update_sl_cost(Mdl_product mdl_product) {
             int res=0;
        connect_db();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("update product set  sale_cost=? where product.product_id=?");
            // Parameters start with 1
            preparedStatement.setInt(1, mdl_product.getSale_cost());
            preparedStatement.setInt(2, mdl_product.getProduct_id());
          res=    preparedStatement.executeUpdate();

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
            System.out.println( this.getClass().getName()+  "  updated: "+ res);
    }
    public  int get_uc(int product_id) {
        connect_db();
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
         
        int uc=0;
        try {
            String sql = "select  unit_cost  from product where product.product_id=? order by product_id desc limit 1";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, product_id);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                 
                uc= rs.getInt("unit_cost");
               
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
        return uc;
    }
    public  int get_quantity(int product_id) {
     
        connect_db();
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
         
        int quantity=0;
        try {
            String sql = "select quantity from product where product.product_id=? order by product_id desc limit 1";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, product_id);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                 
                quantity= rs.getInt("quantity");
               
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
        return quantity;
    }
    public  int get_sl_cost(int product_id) {
     
        connect_db();
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
         
        int sale_cost=0;
        try {
            String sql = "select sale_cost from product where product.product_id=? order by product_id desc limit 1";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, product_id);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                 
                sale_cost= rs.getInt("sale_cost");
               
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
        return sale_cost;
    }
     //</editor-fold>

    public List<Mdl_product> getAllprod_cbo() {
        connect_db();
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        List<Mdl_product> products = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            rs = statement.executeQuery("select product_id, name, quantity, unit_cost, sale_cost, (unit_cost*quantity) as total  from product ");

            while (rs.next()) {
                Mdl_product product = new Mdl_product();
                product.setProduct_id(rs.getInt("product_id"));
                product.setName(rs.getString("name"));
                product.setQuantity(rs.getInt("quantity"));
                product.setUnit_cost(rs.getInt("unit_cost"));
                product.setSale_cost(rs.getInt("sale_cost"));
                product.setTotal(rs.getFloat("total"));

                products.add(product);
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
        return products;
    }

    public int get_count_Products() {
        connect_db();
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        int tot = 0;
        try {
            preparedStatement = connection.prepareStatement("select count(*) as tot from product ");
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

    public Mdl_product getProductByid(int product_id) {
        connect_db();
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        Mdl_product product = new Mdl_product();
        try {
            preparedStatement = connection.prepareStatement("select * from product where product_id=?");
            preparedStatement.setInt(1, product_id);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                product.setProduct_id(rs.getInt("product_id"));
                product.setName(rs.getString("name"));
                product.setQuantity(rs.getInt("quantity"));
                product.setUnit_cost(rs.getInt("unit_cost"));
                product.setSale_cost(rs.getInt("sale_cost"));
                product.setTotal(rs.getFloat("total"));

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

        return product;
    }

    public Mdl_product get_last_product() {
        connect_db();
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        Mdl_product product = new Mdl_product();
        try {
            preparedStatement = connection.prepareStatement("select product_id, name, quantity, unit_cost, sale_cost, (unit_cost*quantity) as total from product order by product.product_id desc limit 1");
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                product.setProduct_id(rs.getInt("product_id"));
                product.setName(rs.getString("name"));
                product.setQuantity(rs.getInt("quantity"));
                product.setUnit_cost(rs.getInt("unit_cost"));
                product.setSale_cost(rs.getInt("sale_cost"));
                product.setTotal(rs.getFloat("total"));

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

        return product;
    }

}
