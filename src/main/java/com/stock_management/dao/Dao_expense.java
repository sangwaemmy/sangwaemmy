/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stock_management.dao;

import com.stock_management.model.Mdl_expense;
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
public class Dao_expense {

    private Connection connection;

    public Dao_expense() {
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

    public void add_expense(Mdl_expense expense) {
        connect_db();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("insert into expense (name,amount,date)values (?,?,?)");
            // Parameters start with 1
            preparedStatement.setString(1, expense.getName());
            preparedStatement.setInt(2, expense.getAmount());
            preparedStatement.setString(3, expense.getDate());

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

    public void delete_expense(int expense_id) {
        connect_db();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("delete from expense where expense_id=?");
            // Parameters start with 1
            preparedStatement.setInt(1, expense_id);
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

    public void update_expense(Mdl_expense expense) {
        connect_db();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("update expense set name=?,amount=?,date=? "
                    + " where expense_id=?");
            // Parameters start with 1
            preparedStatement.setString(1, expense.getName());
            preparedStatement.setInt(2, expense.getAmount());
            preparedStatement.setString(3, expense.getDate());
            preparedStatement.setInt(4, expense.getExpense_id());
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

    public List<Mdl_expense> getAllexpense() {
        connect_db();
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        List<Mdl_expense> expenses = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            rs = statement.executeQuery("select * from expense limit 80");
            while (rs.next()) {
                Mdl_expense expense = new Mdl_expense();
                expense.setExpense_id(rs.getInt("expense_id"));
                expense.setName(rs.getString("name"));
                expense.setAmount(rs.getInt("amount"));
                expense.setDate(rs.getString("date"));
                expenses.add(expense);
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
        return expenses;
    }

    public Mdl_expense getExpenseByid(int expense_id) {
        connect_db();
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        Mdl_expense expense = new Mdl_expense();
        try {
            preparedStatement = connection.prepareStatement("select * from expense where expense_id=?");
            preparedStatement.setInt(1, expense_id);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                expense.setExpense_id(rs.getInt("expense_id"));
                expense.setName(rs.getString("name"));
                expense.setAmount(rs.getInt("amount"));
                expense.setDate(rs.getString("date"));

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

        return expense;
    }

}
