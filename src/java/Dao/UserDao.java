/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Dto.User;
import SqlConnection.SqlConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Jonas
 */
public class UserDao implements UserDAOInterface {

    private SqlConnection sql = new SqlConnection();
    @Override
    public ArrayList<User> getUsers(User us) {
        ArrayList<User> user = new ArrayList();

        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://127.0.0.1:3306/parkinator";
        String username = "root";
        String password = "";

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            Class.forName(driver);

            conn = DriverManager.getConnection(url, username, password);

            ps = conn.prepareStatement("SELECT * FROM users WHERE email='" + us.getEmail() + "'");
            rs = ps.executeQuery();

            while (rs.next()) {
                User u = new User();
                u.setUserNo(rs.getInt("user_id"));
                u.setUserFullname(rs.getString("user_fullname"));
                u.setUserType(rs.getString("user_type"));
                u.setEmail(rs.getString("email"));
                user.add(u);
            }

        } catch (SQLException se) {
            System.out.println("SQL Exception occurred: " + se.getMessage());
            se.printStackTrace();
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    System.out.println("Exception occurred when attempting to close ResultSet: " + ex.getMessage());
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    System.out.println("Exception occurred when attempting to close the PreparedStatement: " + ex.getMessage());
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    System.out.println("Exception occurred when attempting to close the Connection: " + ex.getMessage());
                }
            }
        }

        return user;

    }
    @Override
    public ArrayList<User> selectAllUsers() {
        try {
            sql.setPs(sql.getConn().prepareStatement("select * from users"));
            ResultSet rst;
            // Execute the query
            rst = sql.getPs().executeQuery();
            ArrayList<User> users = new ArrayList<>();
            while (rst.next()) {
                users.add(new User(rst.getInt("user_id"), rst.getString("user_fullname"), rst.getString("email"),
                        rst.getString("password"), rst.getString("user_type"), rst.getString("pass_question"),
                        rst.getString("pass_answer")));
            }
            System.out.println("User has been added.");

            return users;
        } catch (SQLException se) {
            System.out.println("SQL Exception occurred: " + se.getMessage());
            se.printStackTrace();
            return null;
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean Login(String email, String password) {
        try {
            sql.setPs(sql.getConn().prepareStatement("select * from users where email=? and password=?"));
            sql.getPs().setString(1, email);
            sql.getPs().setString(2, password);

            ResultSet rst;
            rst = sql.getPs().executeQuery();

            if (!rst.next()) {
                return false;
            }
            return true;

        } catch (SQLException se) {
            System.out.println("SQL Exception occurred: " + se.getMessage());
            se.printStackTrace();
            return false;
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean Register(String fullname, String email, String password, String user_Type,
            String pass_question, String pass_Answer) {

        try {

            if (CheckUserExistsByEmail(email)) {
                sql.setPs(sql.getConn().prepareStatement("INSERT INTO users(user_fullname, email, password, user_type, pass_question, pass_answer) VALUES (?,?,?,?,?,?)"));

                sql.getPs().setString(1, fullname);
                sql.getPs().setString(2, email);
                sql.getPs().setString(3, password);
                sql.getPs().setString(4, user_Type);
                sql.getPs().setString(5, pass_question);
                sql.getPs().setString(6, pass_Answer);

                sql.getPs().executeUpdate();
                return true;
            } else {
                return false;
            }
        } catch (SQLException se) {
            System.out.println("SQL Exception occurred: " + se.getMessage());
            se.printStackTrace();
            return false;
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean CheckUserExistsByEmail(String email) {
        try {
            sql.setPs(sql.getConn().prepareStatement("select * from users where email=?"));
            sql.getPs().setString(1, email);

            ResultSet rst;
            rst = sql.getPs().executeQuery();

            if (rst.next()) {
                return false;
            }
            return true;

        } catch (SQLException se) {
            System.out.println("SQL Exception occurred: " + se.getMessage());
            se.printStackTrace();
            return false;
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateUser(User user) {
        try {
            sql.setPs(sql.getConn().prepareStatement("UPDATE users SET user_fullname=?,pass_question=?,pass_answer=? WHERE email = ?"));
            sql.getPs().setString(1, user.getEmail());

            sql.getPs().setString(1, user.getUserFullname());
            sql.getPs().setString(2, user.getPass_Question());
            sql.getPs().setString(3, user.getPass_answer());
            sql.getPs().setString(4, user.getEmail());

            sql.getPs().executeUpdate();

            return true;

        } catch (SQLException se) {
            System.out.println("SQL Exception occurred: " + se.getMessage());
            se.printStackTrace();
            return false;
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public User getUserByEmail(String email) {
        try {
            sql.setPs(sql.getConn().prepareStatement("select * from users WHERE email = ?"));
            sql.getPs().setString(1, email);
            ResultSet rst;
            // Execute the query
            rst = sql.getPs().executeQuery();
            User user = null;
            while (rst.next()) {
                user = new User(rst.getInt("user_id"), rst.getString("user_fullname"), rst.getString("email"),
                        rst.getString("password"), rst.getString("user_type"), rst.getString("pass_question"),
                        rst.getString("pass_answer"));
            }
            System.out.println("User has been added.");

            return user;
        } catch (SQLException se) {
            System.out.println("SQL Exception occurred: " + se.getMessage());
            se.printStackTrace();
            return null;
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
            e.printStackTrace();
            return null;
        }    }

}
