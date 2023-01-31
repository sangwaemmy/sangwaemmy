package com.stock_management.dao;

import com.google.gson.Gson;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Etienne
 */
public class Pub_methods {

    public String TodayDate() {
        Date date = new Date();
        Format formatter = new SimpleDateFormat("yyyy-MM-dd ");
        String d = formatter.format(date);
        return d;
    }

    public void response_format(HttpServletResponse response, Map<String, String> map) {
        try {
            response.setContentType("application/json");
            response.setHeader("Cache-Control", "no-cache");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(new Gson().toJson(map));
        } catch (IOException ex) {
            Logger.getLogger(Pub_methods.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void response_format2(HttpServletResponse response, Map<String, Object> map) {
        try {
            response.setContentType("application/json");
            response.setHeader("Cache-Control", "no-cache");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(new Gson().toJson(map));
        } catch (IOException ex) {
            Logger.getLogger(Pub_methods.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void response_format_o(HttpServletResponse response) {

        response.setContentType("application/json");
        response.setHeader("Cache-Control", "no-cache");
        response.setCharacterEncoding("UTF-8");

    }

    public static void get_writer(String path_file, String content) {
        PrintWriter prnt;
        try {
            prnt = new PrintWriter(new BufferedWriter(new FileWriter(path_file)));
            prnt.println(content);
            prnt.close();

        } catch (IOException ex) {
            Logger.getLogger(Pub_methods.class.getName()).log(Level.ALL.SEVERE, null, ex);
        }
    }

}
