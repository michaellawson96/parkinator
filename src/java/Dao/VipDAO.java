/*
 * Made By
 * Name: Michael Lawson
 * Student Number: D00185184
 */
package Dao;

import Dto.User;
import Dto.Vip;
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
                objs.add(new Vip(rst.getInt("zone_id"),rst.getInt("user_id")));
            }
            System.out.println("Vips have been obtained.");

            return objs;
        } catch (SQLException se) {
            System.out.println("SQL Exception occurred: " + se.getMessage());
            se.printStackTrace();
            objs.add(hsb.SQlError());
            return objs;
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
            e.printStackTrace();
            objs.add(hsb.exceptionError());
            return objs;
        }
    }
    
    @Override
    public ArrayList<Object> selectAllUserVips(int userId) {
        ArrayList<Object> objs = new ArrayList<>();
        try {
            sql.setPs(sql.getConn().prepareStatement("select * from vips where user_id = ?"));
            sql.getPs().setInt(1, userId);
            ResultSet rst;
            // Execute the query
            rst = sql.getPs().executeQuery();
            while (rst.next()) {
                objs.add(new Vip(rst.getInt("zone_id"),rst.getInt("user_id")));
            }
            System.out.println("Vips have been obtained.");

            return objs;
        } catch (SQLException se) {
            System.out.println("SQL Exception occurred: " + se.getMessage());
            se.printStackTrace();
            objs.add(hsb.SQlError());
            return objs;
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
            e.printStackTrace();
            objs.add(hsb.exceptionError());
            return objs;
        }
    }

    

    @Override
    public String addVip(Vip v) {
        if(!vipExists(v)){
            try {
                sql.setPs(sql.getConn().prepareStatement("INSERT INTO vips(user_id, zone_id) VALUES (?,?)"));

                sql.getPs().setInt(1, v.getUserId());
                sql.getPs().setInt(2, v.getZoneId());


                sql.getPs().executeUpdate();
                return hsb.success();
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
        else{
            return hsb.createMessage(69, "Vip Already Exists");
        }
    }

    @Override
    public String removeVip(Vip v) {
        if(vipExists(v)){
            try {
                    sql.setPs(sql.getConn().prepareStatement("DELETE FROM vips WHERE user_id = ? AND zone_id = ?"));

                    sql.getPs().setInt(1, v.getUserId());
                    sql.getPs().setInt(2, v.getZoneId());


                    sql.getPs().executeUpdate();
                    return hsb.success();
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
        else{
            return hsb.createMessage(70, "Vip Does not Exist");
        }
    }

@Override
    public boolean vipExists(Vip v) {
        try {
            sql.setPs(sql.getConn().prepareStatement("select * from vips WHERE user_id=? and zone_id=?"));
            sql.getPs().setInt(1, v.getUserId());
            sql.getPs().setInt(2, v.getZoneId());

            ResultSet rst;
            rst = sql.getPs().executeQuery();

            if (rst.next()) {
                System.out.println("vip exists");
                return true;
            }
            System.out.println("vip doesn't exist yet");
            return false;

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
    public ArrayList<Object> selectAllVips() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
