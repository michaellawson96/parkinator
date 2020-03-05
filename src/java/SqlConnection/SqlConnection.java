/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SqlConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

/**
 *
 * @author snake
 */
public class SqlConnection {

    private String driver;
    private String url;
    private String username;
    private String password;

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Boolean end = false;

    public SqlConnection() {
        ResourceBundle config = ResourceBundle.getBundle("SqlConnection.DBConfig");
        String dbName = config.getString("database");

        driver = "com.mysql.jdbc.Driver";
        url = "jdbc:mysql://127.0.0.1:3306/" + dbName;
        username = "root";
        password = "This_Is_The_Password";
        
        try
        {
            // Load the database driver
            Class.forName(driver);

            // Get a connection to the database
            conn = DriverManager.getConnection(url, username, password);

        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public Connection getConn() {
        return conn;
    }

    public PreparedStatement getPs() {
        return ps;
    }

    public ResultSet getRs() {
        return rs;
    }

    public Boolean getEnd() {
        return end;
    }

    public void setPs(PreparedStatement ps) {
        this.ps = ps;
    }

}
