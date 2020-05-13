/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Dto.Car_Model;
import Dto.Car_Model;
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
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author snake
 */
public class Car_ModelDaoTest {

    public Car_ModelDaoTest() {
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
     * Test of getAllModels method, of class Car_ModelDao.
     */
    @Test
    public void testGetAllModels() throws SQLException {
        Car_Model c1 = new Car_Model(1, "01 ca 00001", 1);
        Car_Model c2 = new Car_Model(2, "02 ca 00002", 2);
        Car_Model c3 = new Car_Model(3, "03 ca 00003", 3);

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
        when(conn.prepareStatement("select * from car_model")).thenReturn(ps);
        when(sql.getPs()).thenReturn(ps);
        when(ps.executeQuery()).thenReturn(rs);

        // Want 3 results in the resultset, so need true to be returned 3 times
        when(rs.next()).thenReturn(true, true, true, false);

        // Fill in the resultset
        when(rs.getInt("car_model_id")).thenReturn(c1.getCar_Model_ID(), c2.getCar_Model_ID(), c3.getCar_Model_ID());
        when(rs.getString("car_model_name")).thenReturn(c1.getCar_Model_Name(), c2.getCar_Model_Name(), c3.getCar_Model_Name());
        when(rs.getInt("car_make_id")).thenReturn(c1.getCar_Make_ID(), c2.getCar_Make_ID(), c3.getCar_Make_ID());

        int numUsersInTable = 3;
        Car_ModelDao carDao = new Car_ModelDao(sql);
        ArrayList<Object> result = (ArrayList<Object>) carDao.getAllModels();

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
     * Test of insertModel method, of class Car_ModelDao.
     */
    @Test
    public void testInsertModel() throws SQLException {
        Car_Model c1 = new Car_Model(1, "01 ca 00001", 1);
        boolean expResults = true;
        // Create mock objects
        SqlConnection sql = mock(SqlConnection.class);
        Connection conn = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);

        // Fill mock objects with appropriatel dummy data
        when(sql.getConn()).thenReturn(conn);
        when(conn.prepareStatement("INSERT INTO car_model(car_model_id, car_model_name, car_make_id) VALUES (null,?, ?)")).thenReturn(ps);
        when(sql.getPs()).thenReturn(ps, ps, ps);

        Car_ModelDao carDao = new Car_ModelDao(sql);
        boolean result = (boolean) carDao.insertModel(c1);

        assertEquals(expResults, result);
    }

    /**
     * Test of getAllModelsByCarMakeId method, of class Car_ModelDao.
     *///
    @Test
    public void testGetAllModelsByCarMakeId() throws SQLException {
        Car_Model c1 = new Car_Model(1, "01 ca 00001", 1);
        Car_Model c2 = new Car_Model(2, "02 ca 00002", 2);
        Car_Model c3 = new Car_Model(3, "03 ca 00003", 1);

        ArrayList<Object> expectedResults = new ArrayList();

        expectedResults.add(c1);
        expectedResults.add(c3);

        // Create mock objects
        SqlConnection sql = mock(SqlConnection.class);
        Connection conn = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);

        // Fill mock objects with appropriatel dummy data
        when(sql.getConn()).thenReturn(conn);
        when(conn.prepareStatement("SELECT * FROM car_model WHERE car_make_id=?")).thenReturn(ps);
        when(sql.getPs()).thenReturn(ps);
        when(ps.executeQuery()).thenReturn(rs);

        // Want 3 results in the resultset, so need true to be returned 3 times
        when(rs.next()).thenReturn(true, true, false);

        // Fill in the resultset
        when(rs.getInt("car_model_id")).thenReturn(c1.getCar_Model_ID(), c3.getCar_Model_ID());
        when(rs.getString("car_model_name")).thenReturn(c1.getCar_Model_Name(), c3.getCar_Model_Name());
        when(rs.getInt("car_make_id")).thenReturn(c1.getCar_Make_ID(), c3.getCar_Make_ID());

        int numUsersInTable = 3;
        Car_ModelDao carDao = new Car_ModelDao(sql);
        ArrayList<Object> result = (ArrayList<Object>) carDao.getAllModelsByCarMakeId(c1.getCar_Make_ID());

        assertEquals(expectedResults, result);
    }

}
