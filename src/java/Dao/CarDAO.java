/*
 * Made By
 * Name: Michael Lawson
 * Student Number: D00185184
 */
package Dao;

import Dto.Car;
import SqlConnection.SqlConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 *
 * @author USER
 */
public class CarDAO implements CarDAOInterface {

    private SqlConnection sql = new SqlConnection();
    
    @Override
    public boolean insertCar(Car car) {
        try {
                sql.setPs(sql.getConn().prepareStatement("INSERT INTO cars(car_reg, car_details, user_id) VALUES (?,?,?)"));

                sql.getPs().setString(1, car.getCarReg());
                sql.getPs().setString(2, car.getCarDetails());
                sql.getPs().setInt(3, car.getUserNo());

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
    public boolean updateCar(Car car) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deleteCar(Car car) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Car> getAllUserCars(int userNo) {
        try {
            sql.setPs(sql.getConn().prepareStatement("select * from cars wher user_id = ?"));
            sql.getPs().setInt(1, userNo);
            ResultSet rst;
            // Execute the query
            rst = sql.getPs().executeQuery();
            ArrayList<Car> cars = new ArrayList<>();
            while (rst.next()) {
                cars.add(new Car(rst.getString("car_reg"),rst.getString("car_details"),rst.getInt("user_id")));
            }
            System.out.println("Car has been added.");

            return cars;
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
    
}
