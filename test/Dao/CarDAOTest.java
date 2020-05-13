/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Dto.Car;
import Dto.User;
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
public class CarDAOTest {

    public CarDAOTest() {
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
     * Test of insertCar method, of class CarDAO.
     */
    @Test
    public void testInsertCar() throws SQLException {
        Car c1 = new Car(1, "home", "01 ca 00001", "testColour", "testMake", "testModel", 1);
        boolean expResults = true;
        // Create mock objects
        SqlConnection sql = mock(SqlConnection.class);
        Connection conn = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);

        // Fill mock objects with appropriatel dummy data
        when(sql.getConn()).thenReturn(conn);
        when(conn.prepareStatement("INSERT INTO cars(car_reg, car_colour, car_make, car_model, user_id,alias) VALUES (?,?,?,?,?,?)")).thenReturn(ps);
        when(sql.getPs()).thenReturn(ps, ps, ps, ps, ps, ps, ps);

        CarDAO carDao = new CarDAO(sql);
        boolean result = carDao.insertCar(c1);

        assertEquals(expResults, result);
    }

    /**
     * Test of updateCar method, of class CarDAO.
     */
    @Test
    public void testUpdateCar() throws SQLException {
        Car c1 = new Car(1, "home", "01 ca 00001", "testColour", "testMake", "testModel", 1);

        boolean expResult = true;

        // Create mock objects
        SqlConnection sql = mock(SqlConnection.class);
        Connection conn = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);

        when(sql.getConn()).thenReturn(conn);
        when(conn.prepareStatement("UPDATE cars SET car_reg =?, car_colour=?, car_make=?, car_model=?, user_id=? , alias = ? WHERE car_id=?")).thenReturn(ps);
        when(sql.getPs()).thenReturn(ps, ps, ps, ps, ps, ps, ps, ps);

        CarDAO carDao = new CarDAO(sql);
        boolean result = carDao.insertCar(c1);

        assertEquals(expResult, result);
    }

    /**
     * Test of deleteCar method, of class CarDAO.
     */
    @Test
    public void testDeleteCar() throws SQLException {
        Car c1 = new Car(1, "home", "01 ca 00001", "testColour", "testMake", "testModel", 1);

        boolean expResult = true;

        // Create mock objects
        SqlConnection sql = mock(SqlConnection.class);
        Connection conn = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);

        when(sql.getConn()).thenReturn(conn);
        when(conn.prepareStatement("DELETE FROM cars WHERE car_id=?")).thenReturn(ps);
        when(sql.getPs()).thenReturn(ps, ps);

        CarDAO carDao = new CarDAO(sql);
        boolean result = carDao.insertCar(c1);

        assertEquals(expResult, result);
    }

    /**
     * Test of getAllUserCars method, of class CarDAO.
     */
    @Test
    public void testGetAllUserCars() throws SQLException {
        Car c1 = new Car(1, "home1", "01 ca 00001", "testColour1", "testMake1", "testModel1", 1);
        Car c2 = new Car(2, "home2", "02 ca 00002", "testColour2", "testMake2", "testModel2", 1);
        Car c3 = new Car(3, "home3", "03 ca 00003", "testColour3", "testMake3", "testModel3", 2);

        ArrayList<Object> expectedResults = new ArrayList();

        expectedResults.add(c1);
        expectedResults.add(c2);

        // Create mock objects
        SqlConnection sql = mock(SqlConnection.class);
        Connection conn = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);

        // Fill mock objects with appropriatel dummy data
        when(sql.getConn()).thenReturn(conn);
        when(conn.prepareStatement("select * from cars where user_id = ?")).thenReturn(ps);
        when(sql.getPs()).thenReturn(ps);
        when(ps.executeQuery()).thenReturn(rs);

        // Want 3 results in the resultset, so need true to be returned 3 times
        when(rs.next()).thenReturn(true, true, false);

        // Fill in the resultset
        when(rs.getInt("car_id")).thenReturn(c1.getCarNo(), c2.getCarNo());
        when(rs.getString("car_reg")).thenReturn(c1.getCarReg(), c2.getCarReg());
        when(rs.getString("car_colour")).thenReturn(c1.getCarColour(), c2.getCarColour());
        when(rs.getString("car_make")).thenReturn(c1.getCarMake(), c2.getCarMake());
        when(rs.getString("car_model")).thenReturn(c1.getCarModel(), c2.getCarModel());
        when(rs.getInt("user_id")).thenReturn(c1.getUserNo(), c2.getUserNo());
        when(rs.getString("alias")).thenReturn(c1.getAlias(), c2.getAlias());

        int numUsersInTable = 2;
        CarDAO carDao = new CarDAO(sql);
        ArrayList<Object> result = carDao.getAllUserCars(c1.getUserNo());

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
     * Test of selectAllCars method, of class CarDAO.
     */
    @Test
    public void testSelectAllCars() throws SQLException {
        Car c1 = new Car(1, "home1", "01 ca 00001", "testColour1", "testMake1", "testModel1", 1);
        Car c2 = new Car(2, "home2", "02 ca 00002", "testColour2", "testMake2", "testModel2", 1);
        Car c3 = new Car(3, "home3", "03 ca 00003", "testColour3", "testMake3", "testModel3", 1);

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
        when(conn.prepareStatement("select * from cars")).thenReturn(ps);
        when(sql.getPs()).thenReturn(ps);
        when(ps.executeQuery()).thenReturn(rs);

        // Want 3 results in the resultset, so need true to be returned 3 times
        when(rs.next()).thenReturn(true, true, true, false);

        // Fill in the resultset
        when(rs.getInt("car_id")).thenReturn(c1.getCarNo(), c2.getCarNo(), c3.getCarNo());
        when(rs.getString("car_reg")).thenReturn(c1.getCarReg(), c2.getCarReg(), c3.getCarReg());
        when(rs.getString("car_colour")).thenReturn(c1.getCarColour(), c2.getCarColour(), c3.getCarColour());
        when(rs.getString("car_make")).thenReturn(c1.getCarMake(), c2.getCarMake(), c3.getCarMake());
        when(rs.getString("car_model")).thenReturn(c1.getCarModel(), c2.getCarModel(), c3.getCarModel());
        when(rs.getInt("user_id")).thenReturn(c1.getUserNo(), c2.getUserNo(), c3.getUserNo());
        when(rs.getString("alias")).thenReturn(c1.getAlias(), c2.getAlias(), c3.getAlias());

        int numUsersInTable = 3;
        CarDAO carDao = new CarDAO(sql);
        ArrayList<Object> result = carDao.selectAllCars();

        // Check that the number of entries retrieved matches the (known) number 
        // of entries in the supplied dummy data
        assertEquals(numUsersInTable, result.size());

        // An alternative approach is to use the arraylist of Users we created 
        // as expected results at the start
        // If this equals the arraylist we got back from our method being tested, 
        // then the method worked as expected
        assertEquals(expectedResults, result);
    }

}
