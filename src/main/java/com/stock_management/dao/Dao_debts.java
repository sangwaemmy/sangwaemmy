/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stock_management.dao;

import com.stock_management.model.Mdl_debts;
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
public class Dao_debts {
     private Connection connection;

    public Dao_debts() {
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

    public void add_debts(Mdl_debts debt) {
        connect_db();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("insert into debts (amount,account,product,date_debts,total_debts,person)values (?,?,?,?,?,?)");
            // Parameters start with 1
            preparedStatement.setInt(1, debt.getAmount());
            preparedStatement.setInt(2, debt.getAccount());
            preparedStatement.setInt(3, debt.getProduct());
            preparedStatement.setString(4,debt.getDate_debts());
            preparedStatement.setInt(5, debt.getTotal_debts());
            preparedStatement.setInt(6, debt.getPerson());
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
     

    public void delete_debts(int debts_id) {
        connect_db();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("delete from product where product_id=?");
            // Parameters start with 1
            preparedStatement.setInt(1, debts_id);
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

    public void update_debts(Mdl_debts debt) {
        connect_db();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("update product set amount=?,account=?,product=?, date_debts=?,total_debts=? ,person=? "
                    + " where debts_id=?");
            // Parameters start with 1
            preparedStatement.setInt(1, debt.getAccount());
            preparedStatement.setInt(2, debt.getAccount());
            preparedStatement.setInt(3, debt.getProduct());
            preparedStatement.setString(4, debt.getDate_debts());
            preparedStatement.setInt(5, debt.getTotal_debts());
            preparedStatement.setInt(6, debt.getPerson());
            preparedStatement.setInt(7, debt.getDebts_id());
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

   public List<Mdl_debts> getAlldebts() {
        connect_db();
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        List<Mdl_debts> debts = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            rs = statement.executeQuery("select debts.debts_id, debts.amount,debts.account,debts.product,debts.total_debts,debts.person,debts.date_debts,"
                    + " person.name,person.surname "
                    + "  from debts "
                    + " join account on account.account_id=debts.account"
                    + " join person on person.person_id=debts.person");

            while (rs.next()) {
                Mdl_debts debt = new Mdl_debts();
                debt.setDebts_id(rs.getInt("debts_id"));
                debt.setAmount(rs.getInt("amount"));
                debt.setAccount(rs.getInt("account"));
                debt.setProduct(rs.getInt("product"));
                debt.setTotal_debts(rs.getInt("total_debts"));
                debt.setPerson(rs.getInt("person"));
                debt.setDate_debts(rs.getString("date_debts"));
                
                //person
                debt.setName(rs.getString("name"));
                debt.setSurname(rs.getString("surname"));
                debts.add(debt);
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
        return debts;
   }
    
    public Mdl_debts getDebtsByid(int debts_id) {
        connect_db();
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        Mdl_debts debt = new Mdl_debts();
        try {
            preparedStatement = connection.prepareStatement("select * from debts where debts_id=?");
            preparedStatement.setInt(1, debts_id);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                debt.setDebts_id(rs.getInt("debts_id"));
                debt.setAmount(rs.getInt("amount"));
                debt.setAccount(rs.getInt("account"));
                debt.setProduct(rs.getInt("product"));
                debt.setTotal_debts(rs.getInt("total_debts"));
                debt.setPerson(rs.getInt("person"));
                debt.setDate_debts(rs.getString("date_debts"));
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

        return debt;
    }
    
    public Mdl_debts getDebtsBy_customer(int customer) {
        connect_db();
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        Mdl_debts debt = new Mdl_debts();
        try {
            preparedStatement = connection.prepareStatement("select total_debts  from debts where person=? order by debts_id desc limit 1");
            preparedStatement.setInt(1, customer);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                debt.setDebts_id(rs.getInt("debts_id"));
                debt.setAmount(rs.getInt("amount"));
                debt.setAccount(rs.getInt("account"));
                debt.setProduct(rs.getInt("product"));
                debt.setTotal_debts(rs.getInt("total_debts"));
                debt.setPerson(rs.getInt("person"));
                debt.setDate_debts(rs.getString("date_debts"));
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

        return debt;
    }
    

}
    
