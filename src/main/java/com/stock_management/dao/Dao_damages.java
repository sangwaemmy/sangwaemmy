package com.stock_management.dao;

import com.stock_management.model.Mdl_damages;

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
 * @author etienne
 */
public class Dao_damages {

    //<editor-fold defaultstate="collapsed" desc="comment">
    String damage_q3 = "select damages.damage_id,damages.product,damages.damage_qty,damages.account,damages.date,\n"
            + "             product.name,product.quantity,product.unit_cost,product.sale_cost\n"
            + "             from damages "
            + "             join product on product.product_id=damages.product";

//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="damage by product">
    String damage_by_name = "select count(*) as tot from damages "
            + "              join product on product.product_id=damages.product";

//</editor-fold>
    private Connection connection;

    public Dao_damages() {
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
//            System.out.println("Error on db: " + e.toString());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger("The connection error: " + Util.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void add_damages(Mdl_damages damage) {
        connect_db();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("insert into damages (product,damage_qty,account,date)values (?,?,?,?)");
            // Parameters start with 1
            preparedStatement.setInt(1, damage.getProduct());
            preparedStatement.setInt(2, damage.getDamage_qty());
            preparedStatement.setInt(3, damage.getAccount());
            preparedStatement.setString(4, damage.getDate());

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

    public void delete_damages(int damage_id) {
        connect_db();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("delete from damages where damage_id=?");
            // Parameters start with 1
            preparedStatement.setInt(1, damage_id);
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

    public void update_damages(Mdl_damages damage) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("update damages set product=?,damage_qty=?,account=?,date=? "
                    + " where damage_id=?");
            // Parameters start with 1
            preparedStatement.setInt(1, damage.getProduct());
            preparedStatement.setInt(2, damage.getDamage_qty());
            preparedStatement.setInt(3, damage.getAccount());
            preparedStatement.setString(4, damage.getDate());
            preparedStatement.setInt(5, damage.getDamage_id());
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

    public List<Mdl_damages> getAlldamages() {
        connect_db();
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        List<Mdl_damages> damages = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            rs = statement.executeQuery("select * from damages limit 80 ");
            while (rs.next()) {
                Mdl_damages damage = new Mdl_damages();
                damage.setDamage_id(rs.getInt("damage_id"));
                damage.setProduct(rs.getInt("product"));
                damage.setDamage_qty(rs.getInt("damage_qty"));
                damage.setAccount(rs.getInt("account"));
                damage.setDate(rs.getString("date"));
                damages.add(damage);
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
        return damages;
    }
    
    
    
    
    
    public int get_count_dam() {
        connect_db();
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        int tot =0; 
        try {
            preparedStatement = connection.prepareStatement("select count(*) as tot from damages");
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


    public int get_count_damages(String name) {
        connect_db();
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        int tot = 0;
        try {
            preparedStatement = connection.prepareStatement("select count(*) as tot from damages where product.name=? ");
           preparedStatement.setString(1, name);
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

    public Mdl_damages getDamagesByid(int damage_id) {
        connect_db();
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        Mdl_damages damage = new Mdl_damages();
        try {
            preparedStatement = connection.prepareStatement("select * from damages where damage_id=?");
            preparedStatement.setInt(1, damage_id);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                damage.setDamage_id(rs.getInt("damage_id"));
                damage.setProduct(rs.getInt("product"));
                damage.setDamage_qty(rs.getInt("damage_qty"));
                damage.setAccount(rs.getInt("account"));
                damage.setDate(rs.getString("date"));
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

        return damage;
    }

    //addon
    public List<Mdl_damages> get_dam_prd_prs_dt(String date1, String date2) {
        connect_db();
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        List<Mdl_damages> damages = new ArrayList<>();

        try {
            String sql = (damage_q3 + " where damages.date>=? and damages.date<=? limit 80 ");
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, date1);
            preparedStatement.setString(2, date2);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Mdl_damages damage = new Mdl_damages();
                damage.setDamage_id(rs.getInt("damage_id"));
                damage.setProduct(rs.getInt("product"));
                damage.setDamage_qty(rs.getInt("damage_qty"));
                damage.setAccount(rs.getInt("account"));
                damage.setDate(rs.getString("date"));

                //pruduct
                damage.setName(rs.getString("name"));
                damage.setQuantity(rs.getInt("quantity"));
                damage.setUnit_cost(rs.getInt("unit_cost"));
                damage.setSale_cost(rs.getInt("sale_cost"));

                damages.add(damage);
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
        return damages;
    }

    public List<Mdl_damages> get_dam_prd_prs_p_nm(String name, int start, int end) {
        connect_db();
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        List<Mdl_damages> damages = new ArrayList<>();

        try {
            String sql = (damage_q3 + " where product.name=? limit "+start+","+end);
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Mdl_damages damage = new Mdl_damages();
                damage.setDamage_id(rs.getInt("damage_id"));
                damage.setProduct(rs.getInt("product"));
                damage.setDamage_qty(rs.getInt("damage_qty"));
                damage.setAccount(rs.getInt("account"));
                damage.setDate(rs.getString("date"));

                //pruduct
                damage.setName(rs.getString("name"));
                damage.setQuantity(rs.getInt("quantity"));
                damage.setUnit_cost(rs.getInt("unit_cost"));
                damage.setSale_cost(rs.getInt("sale_cost"));
                damages.add(damage);
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
        return damages;
    }

    public int get_count_by_nm(String name) {

        connect_db();
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        int tot = 0;

        try {
            String sql = (damage_by_name + " where product.name=? ");
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, name);
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

    public List<Mdl_damages> get_dam_prd_prs(int start, int end) {
        connect_db();
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        List<Mdl_damages> damages = new ArrayList<>();

        try {
            String sql = (damage_q3 + " limit " + start + "," + end);
            preparedStatement = connection.prepareStatement(sql);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Mdl_damages damage = new Mdl_damages();
                damage.setDamage_id(rs.getInt("damage_id"));
                damage.setProduct(rs.getInt("product"));
                damage.setDamage_qty(rs.getInt("damage_qty"));
                damage.setAccount(rs.getInt("account"));
                damage.setDate(rs.getString("date"));

                //pruduct
                damage.setName(rs.getString("name"));
                damage.setQuantity(rs.getInt("quantity"));
                damage.setUnit_cost(rs.getInt("unit_cost"));
                damage.setSale_cost(rs.getInt("sale_cost"));
                damages.add(damage);
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
        return damages;
    }
    public List<Mdl_damages> get_dam_prd() {
        connect_db();
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        List<Mdl_damages> damages = new ArrayList<>();

        try {
            String sql = (damage_q3 );
            preparedStatement = connection.prepareStatement(sql);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Mdl_damages damage = new Mdl_damages();
                damage.setDamage_id(rs.getInt("damage_id"));
                damage.setProduct(rs.getInt("product"));
                damage.setDamage_qty(rs.getInt("damage_qty"));
                damage.setAccount(rs.getInt("account"));
                damage.setDate(rs.getString("date"));

                //pruduct
                damage.setName(rs.getString("name"));
                damage.setQuantity(rs.getInt("quantity"));
                damage.setUnit_cost(rs.getInt("unit_cost"));
                damage.setSale_cost(rs.getInt("sale_cost"));
                damages.add(damage);
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
        return damages;
    }

}
