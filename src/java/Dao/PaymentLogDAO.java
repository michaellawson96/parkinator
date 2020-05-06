/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Dto.PaymentLogs;
import SqlConnection.SqlConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author SeppQ
 */
public class PaymentLogDAO implements PaymentLogDAOInterface {

    private SqlConnection sql;
    private HttpStatusBase hsb = new HttpStatusBase();

    public PaymentLogDAO(SqlConnection sql) {
        this.sql = sql;
    }

    public PaymentLogDAO() {
        this.sql = new SqlConnection();
    }


    @Override
    public String insertPaymentLogs(PaymentLogs pl) {
        try {
            sql.setPs(sql.getConn().prepareStatement("INSERT INTO paymentlogs(id,create_time,intent,status) VALUES (?,?,?,?)"));

            sql.getPs().setString(1, pl.getId());
            sql.getPs().setString(2, pl.getCreate_time());
            sql.getPs().setString(3, pl.getIntent());
            sql.getPs().setString(4, pl.getStatus());

            sql.getPs().executeUpdate();

            return hsb.createMessage(1, "Payment Log has Been Added");

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
    public Object selectPaymentLogs() {
        try {
            sql.setPs(sql.getConn().prepareStatement("select * from paymentlogs"));
            ResultSet rst;
            // Execute the query
            rst = sql.getPs().executeQuery();
            ArrayList<PaymentLogs> pl = new ArrayList<>();
            while (rst.next()) {
                pl.add(new PaymentLogs(rst.getString("id"), rst.getString("create_time"), rst.getString("intent"),rst.getString("status")));
            }
            

            return pl;
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
