/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import static Dao.UserDao.SaltANDHash;
import Dto.Car;
import Dto.Lot;
import Dto.ParkedCars;
import Dto.User;
import Dto.Zone;
import SqlConnection.SqlConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;

/**
 *
 * @author Lukas
 */
public class LotDAO implements LotDaoInterface {

    private SqlConnection sql;
    private HttpStatusBase hsb = new HttpStatusBase();

    public LotDAO(SqlConnection sql) {
        this.sql = sql;
    }

    public LotDAO() {
        this.sql = new SqlConnection();
    }

    @Override
    public Object selectAllLots() {
        try {
            sql.setPs(sql.getConn().prepareStatement("select * from parking_lots"));
            ResultSet rst;
            // Execute the query
            rst = sql.getPs().executeQuery();
            ArrayList<Lot> lot = new ArrayList<>();
            while (rst.next()) {
                lot.add(new Lot(rst.getInt("lot_id"), rst.getString("parking_name"), rst.getInt("cc_id")));
            }
            System.out.println("User has been added.");

            return lot;
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

    @Override
    public Object selectAllBookigns() {
        try {
            sql.setPs(sql.getConn().prepareStatement("select * from parked_cars"));
            ResultSet rst;
            // Execute the query
            rst = sql.getPs().executeQuery();
            ArrayList<ParkedCars> pc = new ArrayList<>();
            while (rst.next()) {
                pc.add(new ParkedCars(rst.getInt("zone_id"), rst.getInt("car_id"), rst.getDate("bookFrom"), rst.getDate("bookTo")));
            }

            return pc;
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

    @Override
    public Object selectAllZones() {
        try {
            sql.setPs(sql.getConn().prepareStatement("select * from parking_zones"));
            ResultSet rst;
            // Execute the query
            rst = sql.getPs().executeQuery();
            ArrayList<Zone> z = new ArrayList<>();
            while (rst.next()) {
                z.add(new Zone(rst.getInt("zone_id"), rst.getString("zone_name"), rst.getInt("max_spaces"), rst.getBoolean("is_vip"), rst.getInt("lot_id"), rst.getInt("max_disabled_spaces")));
            }

            return z;
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

    @Override
    public String AddLot(Lot lot) {
        try {

            sql.setPs(sql.getConn().prepareStatement("INSERT INTO parking_lots(Parking_name,cc_id) VALUES (?,?)"));
            sql.getPs().setString(1, lot.getParking_name());
            sql.getPs().setInt(2, lot.getCc_id());

            sql.getPs().executeUpdate();

            return hsb.CreateMessage(1, "Parking Lot added Successfully");

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

    @Override
    public String RemoveLot(Lot lot) {
        try {

            sql.setPs(sql.getConn().prepareStatement("DELETE FROM parking_lots WHERE lot_id = ?"));
            sql.getPs().setInt(1, lot.getLot_id());

            sql.getPs().executeUpdate();

            return hsb.CreateMessage(1, "Parking Lot Deleted Successfully");

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

    @Override
    public String Addzone(Zone zone) {
        try {

            sql.setPs(sql.getConn().prepareStatement("INSERT INTO parking_zones(zone_name,max_spaces,is_vip,lot_id,max_disabled_spaces) VALUES (?,?,?,?,?)"));
            sql.getPs().setString(1, zone.getZone_name());
            sql.getPs().setInt(2, zone.getMax_spaces());
            sql.getPs().setBoolean(3, zone.getIs_vip());
            sql.getPs().setInt(4, zone.getLot_id());
            sql.getPs().setInt(5, zone.getMax_disabled_spaces());

            sql.getPs().executeUpdate();

            return hsb.CreateMessage(1, "Parking Zone added Successfully");

        } catch (SQLException se) {
            System.out.println("SQL Exception occurred: " + se.getMessage());
            se.printStackTrace();
            return se.getMessage();//hsb.SQlError();
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
            e.printStackTrace();
            return hsb.ExceptionError();
        }
    }

    private static java.sql.Date convertUtilToSql(java.util.Date uDate) {
        java.sql.Date sDate = new java.sql.Date(uDate.getTime());
        return sDate;
    }

    @Override
    public String AddBooking(ParkedCars pc) {
        try {

            sql.setPs(sql.getConn().prepareStatement("SELECT max_spaces FROM parking_zones WHERE zone_id = ?"));
            sql.getPs().setInt(1, pc.getZone_id());
            ResultSet rst;
            rst = sql.getPs().executeQuery();
            if (rst.next()) {
                sql.setPs(sql.getConn().prepareStatement("SELECT COUNT(*) FROM parked_cars WHERE zone_id = ?"));
                sql.getPs().setInt(1, pc.getZone_id());
                ResultSet rst2;
                rst2 = sql.getPs().executeQuery();
                if (rst2.next()) {
                    int maxSpaces = rst.getInt("max_spaces");
                    int count = rst2.getInt(1);

                    if (maxSpaces > count) {

                        Date bookFrom = convertUtilToSql(pc.getBookFrom());
                        Date bookTo = convertUtilToSql(pc.getBookTo());

                        sql.setPs(sql.getConn().prepareStatement("INSERT INTO parked_cars(zone_id,car_id,book_from,book_to) VALUES (?,?,?,?)"));
                        sql.getPs().setInt(1, pc.getZone_id());
                        sql.getPs().setInt(2, pc.getCar_id());
                        sql.getPs().setDate(3, bookFrom);
                        sql.getPs().setDate(4, bookTo);

                        sql.getPs().executeUpdate();

                        return hsb.CreateMessage(1, "Parking Spot Booked Successfully From : " + pc.getBookFrom() + " To : " + pc.getBookTo());
                    } else {
                        return hsb.CreateMessage(1, "Sorry But no parking spots Left!");
                    }
                } else {
                    return hsb.CreateMessage(-1, "Something Went Wrong on out server");
                }
            } else {
                return hsb.CreateMessage(-1, "Something Went Wrong on out server");

            }
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

    @Override
    public Object selectAllBookignsByUserId(User u) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
