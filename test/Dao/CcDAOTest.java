/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Dto.BookingDetailsCC;
import Dto.Car;
import Dto.Cc;
import Dto.Lot;
import Dto.ParkedCars;
import Dto.User;
import Dto.Zone;
import SqlConnection.SqlConnection;
import java.sql.Connection;
import java.sql.Date;
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
public class CcDAOTest {

    public CcDAOTest() {
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
     * Test of selectAllCcs method, of class CcDAO.
     */
    @Test
    public void testSelectAllCcs() throws SQLException {
        Cc c1 = new Cc("Testing1", 1);
        Cc c2 = new Cc("Testing2", 2);
        Cc c3 = new Cc("Testing3", 3);

        ArrayList<Cc> expectedResults = new ArrayList();

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
        when(conn.prepareStatement("select * from clamping_companies")).thenReturn(ps);
        when(sql.getPs()).thenReturn(ps);
        when(ps.executeQuery()).thenReturn(rs);

        // Want 3 results in the resultset, so need true to be returned 3 times
        when(rs.next()).thenReturn(true, true, true, false);

        // Fill in the resultset
        when(rs.getString("cc_name")).thenReturn(c1.getCcName(), c2.getCcName(), c3.getCcName());
        when(rs.getInt("cc_id")).thenReturn(c1.getCcNo(), c2.getCcNo(), c3.getCcNo());

        int numUsersInTable = 3;
        CcDAO ccDao = new CcDAO(sql);
        ArrayList<Cc> result = ccDao.selectAllCcs();

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
     * Test of insertCc method, of class CcDAO.
     */
    @Test
    public void testInsertCc() throws SQLException {
        Cc c1 = new Cc("Testing1", 1);
        boolean expResults = true;
        // Create mock objects
        SqlConnection sql = mock(SqlConnection.class);
        Connection conn = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);

        // Fill mock objects with appropriatel dummy data
        when(sql.getConn()).thenReturn(conn);
        when(conn.prepareStatement("INSERT INTO clamping_companies(cc_name) VALUES (?)")).thenReturn(ps);
        when(sql.getPs()).thenReturn(ps, ps);

        CcDAO ccDao = new CcDAO(sql);
        boolean result = ccDao.insertCc(c1.getCcName());

        assertEquals(expResults, result);
    }

    /**
     * Test of updateCc method, of class CcDAO.
     */
    @Test
    public void testUpdateCc() throws SQLException {
        Cc c1 = new Cc("Testing1", 1);

        boolean expResult = true;

        // Create mock objects
        SqlConnection sql = mock(SqlConnection.class);
        Connection conn = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);

        when(sql.getConn()).thenReturn(conn);
        when(conn.prepareStatement("UPDATE clamping_companies SET cc_name=? WHERE cc_id = ?")).thenReturn(ps);
        when(sql.getPs()).thenReturn(ps, ps, ps);

        CcDAO ccDao = new CcDAO(sql);
        boolean result = ccDao.updateCc(c1);

        assertEquals(expResult, result);
    }

    /**
     * Test of deleteCc method, of class CcDAO.
     */
    @Test
    public void testDeleteCc() throws SQLException {
        Cc c1 = new Cc("Testing1", 1);

        boolean expResult = true;

        // Create mock objects
        SqlConnection sql = mock(SqlConnection.class);
        Connection conn = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);

        when(sql.getConn()).thenReturn(conn);
        when(conn.prepareStatement("DELETE from clamping_companies WHERE cc_id = ?")).thenReturn(ps);
        when(sql.getPs()).thenReturn(ps, ps);

        CcDAO ccDao = new CcDAO(sql);
        boolean result = ccDao.deleteCc(c1.getCcNo());

        assertEquals(expResult, result);
    }

    @Test
    public void getAllregPlatesUnderAZone() throws SQLException {
        Cc cc1 = new Cc("Testing1", 1);
        ParkedCars p1 = new ParkedCars(1, 1, new Date(11 / 11 / 11), new Date(11 / 11 / 11), 1);
        ParkedCars p2 = new ParkedCars(1, 2, new Date(22 / 11 / 22), new Date(22 / 11 / 22), 1);
        ParkedCars p3 = new ParkedCars(1, 3, new Date(33 / 11 / 33), new Date(33 / 11 / 33), 1);

        Car c1 = new Car(1,"home1", "01 ca 00001", "testColour", "testMake", "testMode1", 1);
        Car c2 = new Car(2,"home2", "02 ca 00002", "testColour2", "testMake2", "testMode2", 1);
        Car c3 = new Car(3,"home3", "03 ca 00003", "testColour3", "testMake3", "testMode3", 1);

        ArrayList<Car> expResult = new ArrayList<>();

        expResult.add(new Car(c1.getCarNo(), c1.getCarReg()));
        expResult.add(new Car(c2.getCarNo(), c2.getCarReg()));
        expResult.add(new Car(c3.getCarNo(), c3.getCarReg()));

        // Create mock objects
        SqlConnection sql = mock(SqlConnection.class);
        Connection conn = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);

        when(sql.getConn()).thenReturn(conn);
        when(conn.prepareStatement("select cars.car_id, cars.car_reg from parked_cars JOIN cars on parked_cars.car_id = cars.car_id WHERE zone_id = ?")).thenReturn(ps);
        when(sql.getPs()).thenReturn(ps);
        when(ps.executeQuery()).thenReturn(rs);

        when(rs.next()).thenReturn(true, true, true, false);

        when(rs.getInt("car_id")).thenReturn(c1.getCarNo(), c2.getCarNo(), c3.getCarNo());
        when(rs.getString("car_reg")).thenReturn(c1.getCarReg(), c2.getCarReg(), c3.getCarReg());

        CcDAO ccDao = new CcDAO(sql);
        Object result = ccDao.getAllregPlatesUnderAZone(p1);

        assertEquals(expResult, result);
    }

    @Test
    public void getAllregPlatesUnderAZone_fail() throws SQLException {
        Cc cc1 = new Cc("Testing1", 1);
        ParkedCars p1 = new ParkedCars(1, 1, new Date(11 / 11 / 11), new Date(11 / 11 / 11), 1);
        ParkedCars p2 = new ParkedCars(1, 2, new Date(22 / 11 / 22), new Date(22 / 11 / 22), 1);
        ParkedCars p3 = new ParkedCars(1, 3, new Date(33 / 11 / 33), new Date(33 / 11 / 33), 1);

        Car c1 = new Car(1,"home1", "01 ca 00001", "testColour", "testMake", "testMode1", 1);
        Car c2 = new Car(2,"home2", "02 ca 00002", "testColour2", "testMake2", "testMode2", 1);
        Car c3 = new Car(3,"home3", "03 ca 00003", "testColour3", "testMake3", "testMode3", 1);

        ArrayList<Car> expResult = new ArrayList<>();
        
        // Create mock objects
        SqlConnection sql = mock(SqlConnection.class);
        Connection conn = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);

        when(sql.getConn()).thenReturn(conn);
        when(conn.prepareStatement("select cars.car_id, cars.car_reg from parked_cars JOIN cars on parked_cars.car_id = cars.car_id WHERE zone_id = ?")).thenReturn(ps);
        when(sql.getPs()).thenReturn(ps);
        when(ps.executeQuery()).thenReturn(rs);

        when(rs.next()).thenReturn(false);

        CcDAO ccDao = new CcDAO(sql);
        Object result = ccDao.getAllregPlatesUnderAZone(p1);

        assertEquals(expResult, result);
    }
    
    @Test
    public void getAllregPlatesUnderAllZone() throws SQLException {
        Cc cc1 = new Cc("Testing1", 1);
        
        User u1 = new User(1, "Testing User1", "testinguser1@gmail.com", "$2a$12$Fodl2oDf233P40qSfkbVLOmX8R9a6kzuugosLS685hiVZr1qp7KWS", "user", "what is your mother's maiden name", "$2a$12$8XW5CMg.1ssMt9dvm5yMdeGjCTP51HfwFB8O5WDtNeFnNyxJmSBY6", false);
        User u2 = new User(2, "Testing User2", "testinguser2@gmail.com", "$2a$12$jgxPw.sQUTLOG2Yb1xCeFOVHgD5bbH8bkvzNufPIJ9xRnKOghpw9W", "user", "what is your mother's maiden name", "$2a$12$nXti9bKgnGXGHg5.TGTbEOUmYH2lqdduy0RvMIorAgihWVpaEwKKC", true);
        User u3 = new User(3, "Testing User3", "testinguser3@gmail.com", "$2a$12$b3uCgzPSHx94wQunCwzPiOMyCbgGp1qE6UEhUgtqNMXZL28tk.zOq", "user", "what is your mother's maiden name", "$2a$12$omWIaLeVXOwpGFveppMrluN9EsvXR1ufV4YheHYWXtftgn0HZ67oG", false);

        ParkedCars p1 = new ParkedCars(1, 1, new Date(11 / 11 / 11), new Date(11 / 11 / 11), 1);
        ParkedCars p2 = new ParkedCars(1, 2, new Date(22 / 11 / 22), new Date(22 / 11 / 22), 1);
        ParkedCars p3 = new ParkedCars(1, 3, new Date(33 / 11 / 33), new Date(33 / 11 / 33), 1);

        Car c1 = new Car(1,"home1", "01 ca 00001", "testColour", "testMake", "testMode1", 1);
        Car c2 = new Car(2,"home2", "02 ca 00002", "testColour2", "testMake2", "testMode2", 1);
        Car c3 = new Car(3,"home3", "03 ca 00003", "testColour3", "testMake3", "testMode3", 1);
        
        Lot L1 = new Lot(1, "Test Lot1", 1,"MEath");
        Lot L2 = new Lot(2, "Test Lot2", 2,"Louth");
        Lot L3 = new Lot(3, "Test Lot3", 3,"Dublin");
        
        Zone z1 = new Zone(1, "TestingZone1", 99, false, 1, 1, 0.0, 0.0,21);        

        ArrayList<BookingDetailsCC> expResult = new ArrayList<>();

        expResult.add(new BookingDetailsCC(c1.getCarReg(), z1.getZone_name(), L1.getParking_name(), u1.getUserFullname()));
        expResult.add(new BookingDetailsCC(c2.getCarReg(), z1.getZone_name(), L1.getParking_name(), u2.getUserFullname()));
        expResult.add(new BookingDetailsCC(c3.getCarReg(), z1.getZone_name(), L1.getParking_name(), u3.getUserFullname()));

        // Create mock objects
        SqlConnection sql = mock(SqlConnection.class);
        Connection conn = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);

        when(sql.getConn()).thenReturn(conn);
        when(conn.prepareStatement("select cars.car_reg , parking_zones.zone_name , parking_lots.parking_name,(SELECT users.user_fullname FROM parked_cars JOIN users on parked_cars.user_id = users.user_id where parked_cars.user_id = pc.user_id GROUP BY users.user_fullname) as fullname from cars join parked_cars pc on cars.car_id = pc.car_id join parking_zones on parking_zones.zone_id = pc.zone_id JOIN parking_lots on parking_zones.lot_id = parking_lots.lot_id")).thenReturn(ps);
        when(sql.getPs()).thenReturn(ps);
        when(ps.executeQuery()).thenReturn(rs);

        when(rs.next()).thenReturn(true, true, true, false);

        when(rs.getString("car_reg")).thenReturn(c1.getCarReg(), c2.getCarReg(), c3.getCarReg());
        when(rs.getString("zone_name")).thenReturn(z1.getZone_name(), z1.getZone_name(), z1.getZone_name());
        when(rs.getString("parking_name")).thenReturn(L1.getParking_name(), L1.getParking_name(), L1.getParking_name());
        when(rs.getString("fullname")).thenReturn(u1.getUserFullname(), u2.getUserFullname(), u3.getUserFullname());

        CcDAO ccDao = new CcDAO(sql);
        Object result = ccDao.getAllregPlatesUnderAllZone();

        assertEquals(expResult, result);
    }
    
    @Test
    public void getAllregPlatesUnderAllZone_fail() throws SQLException {
        Cc cc1 = new Cc("Testing1", 1);
        
        User u1 = new User(1, "Testing User1", "testinguser1@gmail.com", "$2a$12$Fodl2oDf233P40qSfkbVLOmX8R9a6kzuugosLS685hiVZr1qp7KWS", "user", "what is your mother's maiden name", "$2a$12$8XW5CMg.1ssMt9dvm5yMdeGjCTP51HfwFB8O5WDtNeFnNyxJmSBY6", false);
        User u2 = new User(2, "Testing User2", "testinguser2@gmail.com", "$2a$12$jgxPw.sQUTLOG2Yb1xCeFOVHgD5bbH8bkvzNufPIJ9xRnKOghpw9W", "user", "what is your mother's maiden name", "$2a$12$nXti9bKgnGXGHg5.TGTbEOUmYH2lqdduy0RvMIorAgihWVpaEwKKC", true);
        User u3 = new User(3, "Testing User3", "testinguser3@gmail.com", "$2a$12$b3uCgzPSHx94wQunCwzPiOMyCbgGp1qE6UEhUgtqNMXZL28tk.zOq", "user", "what is your mother's maiden name", "$2a$12$omWIaLeVXOwpGFveppMrluN9EsvXR1ufV4YheHYWXtftgn0HZ67oG", false);

        ParkedCars p1 = new ParkedCars(1, 1, new Date(11 / 11 / 11), new Date(11 / 11 / 11), 1);
        ParkedCars p2 = new ParkedCars(1, 2, new Date(22 / 11 / 22), new Date(22 / 11 / 22), 1);
        ParkedCars p3 = new ParkedCars(1, 3, new Date(33 / 11 / 33), new Date(33 / 11 / 33), 1);

        Car c1 = new Car(1,"home1", "01 ca 00001", "testColour", "testMake", "testMode1", 1);
        Car c2 = new Car(2,"home2", "02 ca 00002", "testColour2", "testMake2", "testMode2", 1);
        Car c3 = new Car(3,"home3", "03 ca 00003", "testColour3", "testMake3", "testMode3", 1);
        
        Lot L1 = new Lot(1, "Test Lot1", 1,"Meath");
        Lot L2 = new Lot(2, "Test Lot2", 2,"Louth");
        Lot L3 = new Lot(3, "Test Lot3", 3,"Dublin");
        
        Zone z1 = new Zone(1, "TestingZone1", 99, false, 1, 1, 0.0, 0.0,23);        

        ArrayList<BookingDetailsCC> expResult = new ArrayList<>();

        // Create mock objects
        SqlConnection sql = mock(SqlConnection.class);
        Connection conn = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);

        when(sql.getConn()).thenReturn(conn);
        when(conn.prepareStatement("select cars.car_reg , parking_zones.zone_name , parking_lots.parking_name,(SELECT users.user_fullname FROM parked_cars JOIN users on parked_cars.user_id = users.user_id where parked_cars.user_id = pc.user_id GROUP BY users.user_fullname) as fullname from cars join parked_cars pc on cars.car_id = pc.car_id join parking_zones on parking_zones.zone_id = pc.zone_id JOIN parking_lots on parking_zones.lot_id = parking_lots.lot_id")).thenReturn(ps);
        when(sql.getPs()).thenReturn(ps);
        when(ps.executeQuery()).thenReturn(rs);

        when(rs.next()).thenReturn(false);

        CcDAO ccDao = new CcDAO(sql);
        Object result = ccDao.getAllregPlatesUnderAllZone();

        assertEquals(expResult, result);
    }
}
