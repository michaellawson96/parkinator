/*
 * Made By
 * Name: Michael Lawson
 * Student Number: D00185184
 */
package Dao;

import Dto.BookingDetailsCC;
import Dto.Car;
import Dto.Cc;
import Dto.ParkedCars;
import SqlConnection.SqlConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author USER
 */
public class CcDAO implements CcDAOInterface {

    private SqlConnection sql = null;
    private HttpStatusBase hsb = new HttpStatusBase();
    public CcDAO(SqlConnection sql) {
        this.sql = sql;
    }

    public CcDAO() {
        this.sql = new SqlConnection();
    }

    @Override
    public ArrayList<Cc> selectAllCcs() {
        try {
            sql.setPs(sql.getConn().prepareStatement("select * from clamping_companies"));
            ResultSet rst;
            // Execute the query
            rst = sql.getPs().executeQuery();
            ArrayList<Cc> ccs = new ArrayList<>();
            while (rst.next()) {
                ccs.add(new Cc(rst.getString("cc_name"), rst.getInt("cc_id")));
            }
            System.out.println("Cc has been added.");

            return ccs;
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
    public boolean insertCc(String cc_name) {

        try {

            sql.setPs(sql.getConn().prepareStatement("INSERT INTO clamping_companies(cc_name) VALUES (?)"));
            sql.getPs().setString(1, cc_name);

            sql.getPs().executeUpdate();

            System.out.println("cc recorded");

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
    public boolean updateCc(Cc cc) {
        try {
            sql.setPs(sql.getConn().prepareStatement("UPDATE clamping_companies SET cc_name=? WHERE cc_id = ?"));

            sql.getPs().setString(1, cc.getCcName());
            sql.getPs().setInt(2, cc.getCcNo());

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
    public boolean deleteCc(int id) {
        try {
            sql.setPs(sql.getConn().prepareStatement("DELETE from clamping_companies WHERE cc_id = ?"));

            sql.getPs().setInt(1, id);

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
    public Object getAllregPlatesUnderAZone(ParkedCars pc) {
        try {
            sql.setPs(sql.getConn().prepareStatement("select cars.car_id, cars.car_reg from parked_cars JOIN cars on parked_cars.car_id = cars.car_id WHERE zone_id = ?"));
            sql.getPs().setInt(1, pc.getZone_id());

            ResultSet rst;
            // Execute the query
            rst = sql.getPs().executeQuery();
            ArrayList<Car> car = new ArrayList<>();
            while (rst.next()) {
                car.add(new Car(rst.getInt("car_id"), rst.getString("car_reg")));
            }

            return car;
        } catch (SQLException se) {
            System.out.println("SQL Exception occurred: " + se.getMessage());
            se.printStackTrace();
            return hsb.SQlError();
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
            e.printStackTrace();
            return hsb.ExceptionError();
        }
    }
     //Need To be Tested
    @Override
    public Object getAllregPlatesUnderAllZone() {
        try {
            sql.setPs(sql.getConn().prepareStatement("select cars.car_reg , parking_zones.zone_name , parking_lots.parking_name,(SELECT users.user_fullname FROM parked_cars JOIN users on parked_cars.user_id = users.user_id where parked_cars.user_id = pc.user_id GROUP BY users.user_fullname) as fullname from cars join parked_cars pc on cars.car_id = pc.car_id join parking_zones on parking_zones.zone_id = pc.zone_id JOIN parking_lots on parking_zones.lot_id = parking_lots.lot_id"));

            ResultSet rst;
            // Execute the query
            rst = sql.getPs().executeQuery();
            ArrayList<BookingDetailsCC> bd = new ArrayList<>();
            while (rst.next()) {
                bd.add(new BookingDetailsCC(rst.getString("car_reg"), rst.getString("zone_name"),rst.getString("parking_name"),rst.getString("fullname")));
            }
            return bd;
        } catch (SQLException se) {
            System.out.println("SQL Exception occurred: " + se.getMessage());
            se.printStackTrace();
            return hsb.SQlError();
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
            e.printStackTrace();
            return hsb.ExceptionError();
        }
    }    
}
