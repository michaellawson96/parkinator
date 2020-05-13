/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Dto.Car_Make;
import Dto.Car_Model;
import SqlConnection.SqlConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author snake
 */
public class Car_ModelDao implements Car_ModelDaoInterface {

    private SqlConnection sql = new SqlConnection();
    private HttpStatusBase hsb = new HttpStatusBase();

    public Car_ModelDao(SqlConnection sql) {
        this.sql = sql;
    }

    public Car_ModelDao() {
    }

    @Override
    public Object getAllModels() {
        ArrayList<Object> objs = new ArrayList<>();
        try {
            sql.setPs(sql.getConn().prepareStatement("select * from car_model"));
            ResultSet rst;
            // Execute the query
            rst = sql.getPs().executeQuery();
            while (rst.next()) {
                objs.add(new Car_Model(rst.getInt("car_model_id"), rst.getString("car_model_name"), rst.getInt("car_make_id")));
            }
            System.out.println("Car Models has been added.");

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
    public Object insertModel(Car_Model cm) {
        try {
            sql.setPs(sql.getConn().prepareStatement("INSERT INTO car_model(car_model_id, car_model_name, car_make_id) VALUES (null,?, ?)"));

            sql.getPs().setString(1, cm.getCar_Model_Name());
            sql.getPs().setInt(2, cm.getCar_Make_ID());

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
    public Object getAllModelsByCarMakeId(int cm) {        
        ArrayList<Object> objs = new ArrayList<>();
        try {
            sql.setPs(sql.getConn().prepareStatement("SELECT * FROM car_model WHERE car_make_id=?"));
            sql.getPs().setInt(1, cm);
            ResultSet rst;
            // Execute the query
            rst = sql.getPs().executeQuery();
            while (rst.next()) {
                objs.add(new Car_Model(rst.getInt("car_model_id"),rst.getString("car_model_name"),rst.getInt("car_make_id")));
            }
            System.out.println("Car models has been added.");

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

}
