/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;


import Dto.Car_Make;
import SqlConnection.SqlConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author snake
 */
public class Car_MakeDaoTest {
    
    public Car_MakeDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getAllMakes method, of class Car_MakeDao.
     */
    @Test
    public void testGetAllMakes() throws SQLException {
        Car_Make c1 = new Car_Make(1, "01 ca 00001");
        Car_Make c2 = new Car_Make(2, "02 ca 00002");
        Car_Make c3 = new Car_Make(3, "03 ca 00003");
        
       ArrayList<Object> expectedResults = new ArrayList();

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
        when(conn.prepareStatement("select * from car_make")).thenReturn(ps);
        when(sql.getPs()).thenReturn(ps);
        when(ps.executeQuery()).thenReturn(rs);

        // Want 3 results in the resultset, so need true to be returned 3 times
        when(rs.next()).thenReturn(true, true, true, false);

        // Fill in the resultset
        when(rs.getInt("car_make_id")).thenReturn(c1.getCar_Make_id(), c2.getCar_Make_id(), c3.getCar_Make_id());
        when(rs.getString("car_make_name")).thenReturn(c1.getCar_Make_Name(), c2.getCar_Make_Name(), c3.getCar_Make_Name());
        

        int numUsersInTable = 3;
        Car_MakeDao carDao = new Car_MakeDao(sql);
        ArrayList<Object> result = (ArrayList<Object>) carDao.getAllMakes();

        // Check that the number of entries retrieved matches the (known) number 
        // of entries in the supplied dummy data
        assertEquals(numUsersInTable, result.size());

        // An alternative approach is to use the arraylist of Users we created 
        // as expected results at the start
        // If this equals the arraylist we got back from our method being tested, 
        // then the method worked as expected
        assertEquals(expectedResults, result);
    }

    /**
     * Test of insertMake method, of class Car_MakeDao.
     */
    @Test
    public void testInsertMake() throws SQLException {
        Car_Make c1 = new Car_Make(1, "01 ca 00001");
        boolean expResults = true;
        // Create mock objects
        SqlConnection sql = mock(SqlConnection.class);
        Connection conn = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);

        // Fill mock objects with appropriatel dummy data
        when(sql.getConn()).thenReturn(conn);
        when(conn.prepareStatement("INSERT INTO car_make(car_make_id, car_make_name) VALUES (null, ?)")).thenReturn(ps);
        when(sql.getPs()).thenReturn(ps, ps);

        Car_MakeDao carDao = new Car_MakeDao(sql);
        boolean result = (boolean) carDao.insertMake(c1);

        assertEquals(expResults, result);
    }
    
}
