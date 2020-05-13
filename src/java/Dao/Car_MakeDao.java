/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Dto.Car_Make;
import SqlConnection.SqlConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author snake
 */
public class Car_MakeDao implements Car_MakeDaoInterface {

    private SqlConnection sql = new SqlConnection();
    private HttpStatusBase hsb = new HttpStatusBase();

    public Car_MakeDao(SqlConnection sql) {
        this.sql = sql;
    }

    public Car_MakeDao() {
    }

    @Override
    public Object getAllMakes() {
        ArrayList<Object> objs = new ArrayList<>();
        try {
            sql.setPs(sql.getConn().prepareStatement("select * from car_make"));
            ResultSet rst;
            // Execute the query
            rst = sql.getPs().executeQuery();
            while (rst.next()) {
                objs.add(new Car_Make(rst.getInt("car_make_id"), rst.getString("car_make_name")));
            }
            System.out.println("Car Makes has been added.");

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
    public Object insertMake(Car_Make cm) {
        try {
            sql.setPs(sql.getConn().prepareStatement("INSERT INTO car_make(car_make_id, car_make_name) VALUES (null, ?)"));

            sql.getPs().setString(1, cm.getCar_Make_Name());

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

}
