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
        String hash = "";

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            Class.forName(driver);

            conn = DriverManager.getConnection(url, username, hash);

            ps = conn.prepareStatement("SELECT * FROM users WHERE email='" + us.getEmail() + "'");
            rs = ps.executeQuery();

            while (rs.next()) {
                User u = new User();
                u.setUserNo(rs.getInt("user_id"));
                u.setUserFullname(rs.getString("user_fullname"));
                u.setUserType(rs.getString("user_type"));
                u.setEmail(rs.getString("email"));
                u.setQuestion(rs.getString("question"));
                u.setHasDisabledBadge(rs.getBoolean("has_disabled_badge"));
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
                        rst.getString("hash"), rst.getString("user_type"), rst.getString("question"),
                        rst.getString("answer_hash"), rst.getBoolean("has_disabled_badge")));
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
    public boolean Login(String email, String hash) {
        try {
            sql.setPs(sql.getConn().prepareStatement("select * from users where email=? and hash=?"));
            sql.getPs().setString(1, email);
            sql.getPs().setString(2, hash);

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
    public boolean register(String fullname, String email, String hash, String user_Type,
            String question, String answer_hash, boolean has_disabled_badge) {

        try {

            if (CheckUserExistsByEmail(email)) {
                sql.setPs(sql.getConn().prepareStatement("INSERT INTO users(user_fullname, email, hash, user_type, question, answer_hash, has_disabled_badge) VALUES (?,?,?,?,?,?,?)"));

                sql.getPs().setString(1, fullname);
                sql.getPs().setString(2, email);
                sql.getPs().setString(3, hash);
                sql.getPs().setString(4, user_Type);
                sql.getPs().setString(5, question);
                sql.getPs().setString(6, answer_hash);
                sql.getPs().setBoolean(7, has_disabled_badge);

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
            sql.setPs(sql.getConn().prepareStatement("UPDATE users SET user_fullname=?,question=?,answer_hash=?, has_disabled_badge=? WHERE email = ?"));

            sql.getPs().setString(1, user.getUserFullname());
            sql.getPs().setString(2, user.getQuestion());
            sql.getPs().setString(3, user.getAnswer_hash());
            sql.getPs().setBoolean(4, user.getHasDisabledBadge());
            sql.getPs().setString(5, user.getEmail());

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
                        rst.getString("hash"), rst.getString("user_type"), rst.getString("question"),
                        rst.getString("answer_hash"), rst.getBoolean("has_disabled_badge"));
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
