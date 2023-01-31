/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stock_management.dao;

import com.stock_management.model.Mdl_debts;
import com.stock_management.model.Mdl_paydebts;
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
public class Dao_paydebts {
     private Connection connection;

    public Dao_paydebts() {
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

    public void add_paydebts(Mdl_paydebts paydebt) {
        connect_db();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("insert into paydebts (account,product,amt_paid,amt_remaining,date_paid,person) values (?,?,?,?,?,?)");
            // Parameters start with 1
            preparedStatement.setInt(1, paydebt.getAccount());
            preparedStatement.setInt(2, paydebt.getProduct());
            preparedStatement.setInt(3, paydebt.getAmt_paid());
            preparedStatement.setInt(4, paydebt.getAmt_remaining());
            preparedStatement.setString(5,paydebt.getDate_paid());
            preparedStatement.setInt(6, paydebt.getPerson());
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
     

    public void delete_paydebts(int paydebts_id) {
        connect_db();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("delete from paydebts where paydebts_id=?");
            // Parameters start with 1
            preparedStatement.setInt(1, paydebts_id);
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

    public void update_paydebts(Mdl_paydebts paydebt) {
        connect_db();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("update paydebts set account=?,product=?,amt_paid=?,amt_remaining=?,date_paid=?,person=? "
                    + " where paydebts_id=?");
            // Parameters start with 1
            preparedStatement.setInt(1, paydebt.getAccount());
            preparedStatement.setInt(2, paydebt.getProduct());
            preparedStatement.setInt(3, paydebt.getAmt_paid());
            preparedStatement.setInt(4, paydebt.getAmt_remaining());
            preparedStatement.setString(5, paydebt.getDate_paid());
            preparedStatement.setInt(6, paydebt.getPerson());
            preparedStatement.setInt(7, paydebt.getPaydebts_id());
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

   public List<Mdl_paydebts> getAlldebt_pyt() {
        connect_db();
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        List<Mdl_paydebts> paydebts = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            rs = statement.executeQuery("select paydebts.paydebts_id,paydebts.account,paydebts.product,paydebts.amt_paid,paydebts.amt_remaining,paydebts.person,paydebts.date_paid "
                    + " from paydebts "
                    + " join account on account.account_id=paydebts.account"
                    + " join person on person.person_id=paydebts.person");

            while (rs.next()) {
                Mdl_paydebts paydebt = new Mdl_paydebts();
                paydebt.setPaydebts_id(rs.getInt("paydebts_id"));
                paydebt.setAccount(rs.getInt("account"));
                paydebt.setProduct(rs.getInt("product"));
                paydebt.setAmt_paid(rs.getInt("amt_paid"));
                paydebt.setAmt_remaining(rs.getInt("amt_remaining"));
                paydebt.setPerson(rs.getInt("person"));
                paydebt.setDate_paid(rs.getString("date_paid"));
                //person
//                paydebt.setName(rs.getString("name"));
//                paydebt.setSurname(rs.getString("surname"));
                paydebts.add(paydebt);
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
        return paydebts;
   }
    
    public Mdl_paydebts getPaydebtsByid(int paydebts_id) {
        connect_db();
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        Mdl_paydebts paydebt = new Mdl_paydebts();
        try {
            preparedStatement = connection.prepareStatement("select * from paydebts where paydebts_id=?");
            preparedStatement.setInt(1, paydebts_id);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                paydebt.setPaydebts_id(rs.getInt("paydebts_id"));
                paydebt.setAccount(rs.getInt("account"));
                paydebt.setProduct(rs.getInt("product"));
                paydebt.setAmt_paid(rs.getInt("amt_paid"));
                paydebt.setAmt_remaining(rs.getInt("amt_remaining"));
                paydebt.setPerson(rs.getInt("person"));
                paydebt.setDate_paid(rs.getString("date_paid"));
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

        return paydebt;
    }
    
    public Mdl_paydebts getDebtsBy_customer(int customer) {
        connect_db();
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        Mdl_paydebts paydebt = new Mdl_paydebts();
        try {
            preparedStatement = connection.prepareStatement("select total_paydebts  from paydebts where person=? order by paydebts_id desc limit 1");
            preparedStatement.setInt(1, customer);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                paydebt.setPaydebts_id(rs.getInt("paydebts_id"));
                paydebt.setAccount(rs.getInt("account"));
                paydebt.setProduct(rs.getInt("product"));
                paydebt.setAmt_paid(rs.getInt("amt_paid"));
                paydebt.setAmt_remaining(rs.getInt("amt_remaining"));
                paydebt.setPerson(rs.getInt("person"));
                paydebt.setDate_paid(rs.getString("date_paid"));
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

        return paydebt;
    }
   
    
    
    
    public int get_total_sales(String date1 , String date2) {
        connect_db();
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        int sum = 0;
        try {
            preparedStatement = connection.prepareStatement("select sum(sold_qty*sale_cost) as tot from sales "
                    + "where sales.date >=? and sales.date<=?   ");
            preparedStatement.setString(1, date1);
            preparedStatement.setString(2, date2);
            
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                sum = rs.getInt("tot");
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

        return sum;
    }
    
    public int get_total_paydebts(String date1, String date2) {
        connect_db();
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        int sum = 0;
        try {
            preparedStatement = connection.prepareStatement("select sum(amt_paid) as tot from paydebts "
                    + " where paydebts.date_paid >=? and paydebts.date_paid<=? ");
            preparedStatement.setString(1, date1);
            preparedStatement.setString(2, date2);
            
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                sum = rs.getInt("tot");
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

        return sum;
    }
     public int get_total_expenses(String date1, String date2) {
        connect_db();
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        int sum = 0;
        try {
            preparedStatement = connection.prepareStatement("select sum(amount) as tot from expense"
                    + " where expense.date >=? and expense.date<=?");
            preparedStatement.setString(1, date1);
            preparedStatement.setString(2, date2);
            
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                sum = rs.getInt("tot");
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

        return sum;
    }
     
        public int get_total_debts(String date1, String date2) {
        connect_db();
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        int sum = 0;
        try {
            preparedStatement = connection.prepareStatement("select sum(amount) as tot from debts"
                    + " where debts.date_debts >=? and debts.date_debts<=?");
            preparedStatement.setString(1, date1);
            preparedStatement.setString(2, date2);
            
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                sum = rs.getInt("tot");
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

        return sum;
    }
}
