/*
 * To change this license header, choose License Headerst in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Dto.Car;
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
     * Test of getAllProducts method, of class ProductDao.
     */
    @Test
    public void testGetAllUserCars() throws SQLException
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
        Connection dbConn = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);
        ResultSet rst = mock(ResultSet.class);
        
        // Fill mock objects with appropriatel dummy data
        when(dbConn.prepareStatement("Select * from cars")).thenReturn(ps);
        when(ps.executeQuery()).thenReturn(rst);
        // Want 3 results in the resultset, so need true to be returned 3 times
        when(rst.next()).thenReturn(true, true, true, false);
        // Fill in the resultset
        when(rst.getInt("car_id")).thenReturn(c1.getCarNo(),c2.getCarNo(),c3.getCarNo());
        when(rst.getString("car_reg")).thenReturn(c1.getCarReg(),c2.getCarReg(),c3.getCarReg());
        when(rst.getString("car_details")).thenReturn(c1.getCarDetails(),c2.getCarDetails(),c3.getCarDetails());
        when(rst.getInt("user_id")).thenReturn(c1.getUserNo(),c2.getUserNo(),c3.getUserNo());

        int 
                numCarsInTable = 3,
                userNo = 1;
        
        CarDAO carDao = new CarDAO();
        List<Car> result = carDao.getAllUserCars(userNo);
        // Check that the number of entries retrieved matches the (known) number 
        // of entries in the supplied dummy data
        assertEquals(numCarsInTable, result.size());
        
        // An alternative approach is to use the arraylist of Products we created 
        // as expected results at the start
        // If this equals the arraylist we got back from our method being tested, 
        // then the method worked as expected
        assertEquals(expectedResults, result);
        
        System.out.println(result);
    }    
}

