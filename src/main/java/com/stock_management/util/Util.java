package com.stock_management.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 *
 * By etienne etiennentandi@gmail.com using hp
 */
public class Util {

    private static Connection conn = null;
    public static String username = "sangwa";
    public static String password = "A.manigu125";
    public static String db = "stock_mgt";

//    public static String username = "codeguru_sangwa";
//    public static String password = "A.manigu122";
//    public static String db = "codeguru_stockmgt";

    public static Connection getConnection() {
        if (conn != null) {
            return conn;
        } else {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                String url = "jdbc:mysql://localhost:3306/" + db;
                String user = username;
                String passwd = password;
                conn = DriverManager.getConnection(url, user, passwd);
                System.out.println("Connected successfully");
            } catch (SQLException e) {
                System.out.println("Error on db: " + e.toString());
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
            }
            return conn;
        }
    }

}
