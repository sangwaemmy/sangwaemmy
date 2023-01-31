package com.stock_management.dao;

import com.stock_management.model.Mdl_person;
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
public class Dao_person {

    private Connection connection;

    public Dao_person() {
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

    public void add_person(Mdl_person person) {
        connect_db();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("insert into person (name,surname,category,gender,phone_number)values (?,?,?,?,?)");
            // Parameters start with 1
            preparedStatement.setString(1, person.getName());
            preparedStatement.setString(2, person.getSurname());
            preparedStatement.setInt(3, person.getCategory());
            preparedStatement.setString(4, person.getGender());
            preparedStatement.setString(5, person.getPhone_number());
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

    public void delete_person(int person_id) {
        connect_db();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("delete from person where person_id=?");
            // Parameters start with 1
            preparedStatement.setInt(1, person_id);
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

    public void update_person(Mdl_person person) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("update person set name=?, surname=?, category=?,gender=?,phone_number=?"
                    + " where person_id=?");
            // Parameters start with 1
            preparedStatement.setString(1, person.getName());
            preparedStatement.setString(2, person.getSurname());
            preparedStatement.setInt(3, person.getCategory());
            preparedStatement.setString(4, person.getGender());
            preparedStatement.setString(5, person.getPhone_number());
            preparedStatement.setInt(6, person.getPerson_id());
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

    public List<Mdl_person> getAllperson() {
        connect_db();
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        List<Mdl_person> persons = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            rs = statement.executeQuery("select * from person limit 80 ");
            while (rs.next()) {
                Mdl_person person = new Mdl_person();
                person.setPerson_id(rs.getInt("person_id"));
                person.setName(rs.getString("name"));
                person.setSurname(rs.getString("surname"));
                person.setCategory(rs.getInt("category"));
                person.setGender(rs.getString("gender"));
                person.setPhone_number(rs.getString("phone_number"));
                persons.add(person);
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
        return persons;
    }

    public List<Mdl_person> getAllperson_by_category(String name) {
        connect_db();
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        List<Mdl_person> persons = new ArrayList<>();
        try {
            String sql = "select * from person  "
                    + " join acc_category on acc_category.acc_category_id=person.category"
                    + " where acc_category.name=? ";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Mdl_person person = new Mdl_person();
                person.setPerson_id(rs.getInt("person_id"));
                person.setName(rs.getString("name"));
                person.setSurname(rs.getString("surname"));
                person.setCategory(rs.getInt("category"));
                person.setGender(rs.getString("gender"));
                person.setPhone_number(rs.getString("phone_number"));
                persons.add(person);
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
        return persons;
    }

    public Mdl_person getPersonByid(int person_id) {
        connect_db();
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        Mdl_person person = new Mdl_person();
        try {
            preparedStatement = connection.prepareStatement("select * from person where person_id=?");
            preparedStatement.setInt(1, person_id);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                person.setPerson_id(rs.getInt("person_id"));
                person.setName(rs.getString("name"));
                person.setSurname(rs.getString("surname"));
                person.setCategory(rs.getInt("category"));
                person.setGender(rs.getString("gender"));
                person.setPhone_number(rs.getString("phone_number"));
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

        return person;
    }

    public Mdl_person get_last_person() {
        connect_db();
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        Mdl_person person = new Mdl_person();
        try {
            preparedStatement = connection.prepareStatement("select * from person order by person.person_id desc limit 1");
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                person.setPerson_id(rs.getInt("person_id"));
                person.setName(rs.getString("name"));
                person.setSurname(rs.getString("surname"));
                person.setCategory(rs.getInt("category"));
                person.setGender(rs.getString("gender"));
                person.setPhone_number(rs.getString("phone_number"));

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

        return person;
    }

    //addon
    public List<Mdl_person> get_per_cat_acc() {
        connect_db();
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        List<Mdl_person> persons = new ArrayList<>();

        try {
            preparedStatement = connection.prepareStatement("select person.person_id,person.name,person.surname,person.category,person.gender,person.phone_number,acc_category.name as acc_name,"
                    + "                     account.username,account.password,account.category as a_category,account.person"
                    + "                     from person\n"
                    + "                     join account on account.person = person.person_id\n"
                    + "                     join acc_category on acc_category.acc_category_id =account.category");

            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Mdl_person person = new Mdl_person();
                person.setPerson_id(rs.getInt("person_id"));
                person.setName(rs.getString("name"));
                person.setSurname(rs.getString("surname"));
                person.setCategory(rs.getInt("category"));
                person.setGender(rs.getString("gender"));
                person.setPhone_number(rs.getString("phone_number"));
                //acc_category
                person.setAcc_name(rs.getString("acc_name"));
                //account
                person.setUsername(rs.getString("username"));
                person.setPassword(rs.getString("password"));
                person.setA_category(rs.getInt("a_category"));
                person.setPerson(rs.getInt("person"));

                persons.add(person);
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

        return persons;
    }

}
