package com.stock_management.dao;

import com.stock_management.model.Mdl_acc_category;
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
public class Dao_acc_category {

    private Connection connection;

    public Dao_acc_category() {
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

    public void add_acc_category(Mdl_acc_category acc_category) {
        connect_db();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("insert into acc_category(name)values(?)");
            // Parameters start with 1
            preparedStatement.setString(1, acc_category.getName());
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

    public void delete_acc_category(int acc_category_id) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("delete from acc_category where acc_category_id=?");
            // Parameters start with 1
            preparedStatement.setInt(1, acc_category_id);
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

    public void update_acc_category(Mdl_acc_category acc_category) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("update acc_category set name=? "
                    + "where acc_category_id=?");
            // Parameters start with 1
            preparedStatement.setString(1, acc_category.getName());
            preparedStatement.setInt(2, acc_category.getAcc_category_id());
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

    public List<Mdl_acc_category> getAllacc_category() {
        connect_db();
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        List<Mdl_acc_category> acc_categorys = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            rs = statement.executeQuery("select * from acc_category limit 80 ");
            while (rs.next()) {
                Mdl_acc_category acc_category = new Mdl_acc_category();
                acc_category.setAcc_category_id(rs.getInt("acc_category_id"));
                acc_category.setName(rs.getString("name"));
                acc_categorys.add(acc_category);
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
        return acc_categorys;
    }

    public Mdl_acc_category getAcc_categoryByid(int acc_category_id) {
        connect_db();
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        Mdl_acc_category acc_category = new Mdl_acc_category();
        try {
            preparedStatement = connection.prepareStatement("select * from acc_category where acc_category_id=?");
            preparedStatement.setInt(1, acc_category_id);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                acc_category.setAcc_category_id(rs.getInt("acc_category_id"));
                acc_category.setName(rs.getString("name"));
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

        return acc_category;
    }

    public Mdl_acc_category get_last_category() {
        connect_db();
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        Mdl_acc_category category = new Mdl_acc_category();
        try {
            preparedStatement = connection.prepareStatement("select * from acc_category order by acc_category.acc_category_id desc limit 1");
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                category.setAcc_category_id(rs.getInt("acc_category_id"));
                category.setName(rs.getString("name"));

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

        return category;
    }
}
