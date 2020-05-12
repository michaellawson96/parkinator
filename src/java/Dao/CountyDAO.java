/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Dto.Cc;
import Dto.County;
import SqlConnection.SqlConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author SeppQ
 */
public class CountyDAO implements CountyDAOInterface{
   private SqlConnection sql = null;
    private HttpStatusBase hsb = new HttpStatusBase();
    public CountyDAO(SqlConnection sql) {
        this.sql = sql;
    }

    public CountyDAO() {
        this.sql = new SqlConnection();
    }

    @Override
    public ArrayList<County> selectAllCounties() {
        try {
            sql.setPs(sql.getConn().prepareStatement("select * from counties"));
            ResultSet rst;
            // Execute the query
            rst = sql.getPs().executeQuery();
            ArrayList<County> c = new ArrayList<>();
            while (rst.next()) {
                c.add(new County( rst.getInt("countyID"),rst.getString("countyName")));
            }


            return c;
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
