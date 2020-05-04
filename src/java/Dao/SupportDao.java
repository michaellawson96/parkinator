/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Dto.Lot;
import Dto.ParkedCars;
import Dto.Support;
import Dto.User;
import SqlConnection.SqlConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author SeppQ
 */
public class SupportDao implements SupportDAOInterface{
    
    private SqlConnection sql;
    private HttpStatusBase hsb = new HttpStatusBase();
    
    public SupportDao(SqlConnection sql) {
        this.sql = sql;
    }

    public SupportDao() {
        this.sql = new SqlConnection();
    }
    
    public static java.sql.Date convertUtilToSql(java.util.Date uDate) {
        java.sql.Date sDate = new java.sql.Date(uDate.getTime());
        return sDate;
    }
    
    @Override
    public String insertMessage(Support sup) {
        try {
                sql.setPs(sql.getConn().prepareStatement("INSERT INTO support(title,message,date,user_id) VALUES (?,?,?,?)"));

                sql.getPs().setString(1, sup.getTitle());
                sql.getPs().setString(2, sup.getMessage());
                sql.getPs().setDate(3, convertUtilToSql(sup.getDate()));
                sql.getPs().setInt(4, sup.getUser_id());

                sql.getPs().executeUpdate();
                
                return hsb.createMessage(1, "Your post has been Sent");
        } catch (SQLException se) {
            System.out.println("SQL Exception occurred: " + se.getMessage());
            se.printStackTrace();
            return hsb.SQlError();
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
            e.printStackTrace();
            return hsb.exceptionError();
        }
    }
    
    @Override
    public Object selectAllMessage() {
        try {
            sql.setPs(sql.getConn().prepareStatement("select * from support"));
            ResultSet rst;
            // Execute the query
            rst = sql.getPs().executeQuery();
            ArrayList<Support> sup = new ArrayList<>();
            while (rst.next()) {
                sup.add(new Support(rst.getInt("message_id"), rst.getString("title"), rst.getString("message"), rst.getDate("date"), rst.getInt("user_id")));
            }

            return sup;
        } catch (SQLException se) {
            System.out.println("SQL Exception occurred: " + se.getMessage());
            se.printStackTrace();
            return hsb.SQlError();
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
            e.printStackTrace();
            return hsb.exceptionError();
        }
    }    
    
    @Override
    public String removeMessage(Support sup) {
        try {

            sql.setPs(sql.getConn().prepareStatement("DELETE FROM support WHERE message_id = ?"));
            sql.getPs().setInt(1, sup.getMessage_id());

            sql.getPs().executeUpdate();

            return hsb.createMessage(1, "Message Removed");

        } catch (SQLException se) {
            System.out.println("SQL Exception occurred: " + se.getMessage());
            se.printStackTrace();
            return hsb.SQlError();
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
            e.printStackTrace();
            return hsb.exceptionError();
        }
    }    
}
