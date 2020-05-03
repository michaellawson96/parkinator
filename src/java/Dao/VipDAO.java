/*
 * Made By
 * Name: Michael Lawson
 * Student Number: D00185184
 */
package Dao;

import Dto.User;
import Dto.Vip;
import Dto.Zone;
import SqlConnection.SqlConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author USER
 */
public class VipDAO implements VipDAOInterface{

    private SqlConnection sql = new SqlConnection();
    private HttpStatusBase hsb = new HttpStatusBase();
    
    @Override
    public ArrayList<Object> selectAllZoneVips(int zoneId) {
        ArrayList<Object> objs = new ArrayList<>();
        try {
            sql.setPs(sql.getConn().prepareStatement("select * from vips where zone_id = ?"));
            sql.getPs().setInt(1, zoneId);
            ResultSet rst;
            // Execute the query
            rst = sql.getPs().executeQuery();
            while (rst.next()) {
                objs.add(new User(rst.getInt("user_id"), rst.getString("user_fullname"), rst.getString("email"),
                        rst.getString("hash"), rst.getString("user_type"), rst.getString("question"),
                        rst.getString("answer_hash"), rst.getBoolean("has_disabled_badge")));
            }
            System.out.println("User has been added.");

            return objs;
        } catch (SQLException se) {
            System.out.println("SQL Exception occurred: " + se.getMessage());
            se.printStackTrace();
            objs.add(hsb.SQlError());
            return objs;
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
            e.printStackTrace();
            objs.add(hsb.ExceptionError());
            return objs;
        }
    }

    

    @Override
    public boolean addVip(Vip v) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean removeVip(Vip v) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    
}
