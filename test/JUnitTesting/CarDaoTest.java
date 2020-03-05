/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JUnitTesting;

import Dao.CarDAO;
import Dto.Car;
import SqlConnection.SqlConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;
import static org.mockito.Mockito.*;

/*
 * Made By
 * Name: Michael Lawson
 * Student Number: D00185184
 */
public class CarDaoTest {
    public CarDaoTest()
    {
    }
    
    // Put in code to, for example, create object that will be tested (instead of
    // recreating it over and over)
    @BeforeClass
    public static void setUpClass(){
    }

    /**
     * Test No: 1
     * Test Description: Intakes a valid user's id and outputs all cars owned by that user
     * Mocked Data: 3 cars belonging to one user
     * Expected Result: 3 cars returned
     * @throws java.sql.SQLException
     */
    @Test
    public void testGetAllUserCars_validUserID_usersCarsReturned() throws SQLException
    {
        // Create expected results
        Car c1 = new Car(1,"09-MN-6919","Red Renault Megane",1);
        Car c2 = new Car(2,"151-D-1233","Blue Mini One",1);
        Car c3 = new Car(3,"00-D-1234","Green Nissan Micra",1);
        ArrayList<Car> expectedResults = new ArrayList();
        expectedResults.add(c1);
        expectedResults.add(c2);
        expectedResults.add(c3);
        

        // Create mock objects
        SqlConnection sql = mock(SqlConnection.class);
        Connection conn = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);
        
        // Fill mock objects with appropriatel dummy data
        when(sql.getConn()).thenReturn(conn);
        when(sql.getPs()).thenReturn(ps);
        when(conn.prepareStatement("Select * from cars")).thenReturn(ps);
        when(ps.executeQuery()).thenReturn(rs);
        // Want 3 results in the resultset, so need true to be returned 3 times
        when(rs.next()).thenReturn(true, true, true, false);
        // Fill in the resultset
        when(rs.getInt("car_id")).thenReturn(c1.getCarNo(),c2.getCarNo(),c3.getCarNo());
        when(rs.getString("car_reg")).thenReturn(c1.getCarReg(),c2.getCarReg(),c3.getCarReg());
        when(rs.getString("car_details")).thenReturn(c1.getCarDetails(),c2.getCarDetails(),c3.getCarDetails());
        when(rs.getInt("user_id")).thenReturn(c1.getUserNo(),c2.getUserNo(),c3.getUserNo());

        //provide values for the method parameters
        int userNo = 1;
        
        CarDAO carDao = new CarDAO(sql);
        List<Car> result = carDao.getAllUserCars(userNo);
        
        // If this equals the arraylist we got back from our method being tested, 
        // then the method worked as expected
        assertEquals(expectedResults, result);
        
        System.out.println(result);
    }    
    
    /**
     * Test No: 2
     * Test Description: Intakes an invalid user's id and outputs no cars
     * Mocked Data: 3 cars belonging to one user
     * Expected result: No cars returned
     * @throws java.sql.SQLException
     */
    @Test
    public void testGetAllUserCars_invalidUserID_noCarsReturned() throws SQLException
    {
        // Create expected results
        ArrayList<Car> expectedResults = new ArrayList();
        

        // Create mock objects
        SqlConnection sql = mock(SqlConnection.class);
        Connection conn = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);
        
        // Fill mock objects with appropriatel dummy data
        when(sql.getConn()).thenReturn(conn);
        when(sql.getPs()).thenReturn(ps);
        when(conn.prepareStatement("Select * from cars where user_id")).thenReturn(ps);
        when(ps.executeQuery()).thenReturn(rs);
        // Want 3 results in the resultset, so need true to be returned 3 times
        when(rs.next()).thenReturn(false);


        //provide values for the method parameters
        int userNo = 2;
        
        CarDAO carDao = new CarDAO(sql);
        List<Car> result = carDao.getAllUserCars(userNo);
        
        // If this equals the arraylist we got back from our method being tested, 
        // then the method worked as expected
        assertEquals(expectedResults, result);
        
        System.out.println(result);
    }    
}

