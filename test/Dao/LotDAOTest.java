/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Dto.Car;
import Dto.Lot;
import Dto.ParkedCars;
import Dto.User;
import Dto.Zone;
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
import java.sql.Date;
import org.junit.Ignore;

/**
 *
 * @author snake
 */
public class LotDAOTest {

    public LotDAOTest() {
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
     * Test of selectAllLots method, of class LotDAO.
     */
    @Test
    public void testSelectAllLots() throws SQLException {
        Lot L1 = new Lot(1, "Test Lot1", 1, "Meath");
        Lot L2 = new Lot(2, "Test Lot2", 2, "Louth");
        Lot L3 = new Lot(3, "Test Lot3", 3, "Doublin");

        ArrayList<Lot> expectedResults = new ArrayList();

        expectedResults.add(L1);
        expectedResults.add(L2);
        expectedResults.add(L3);

        // Create mock objects
        SqlConnection sql = mock(SqlConnection.class);
        Connection conn = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);

        // Fill mock objects with appropriatel dummy data
        when(sql.getConn()).thenReturn(conn);
        when(conn.prepareStatement("select * from parking_lots")).thenReturn(ps);
        when(sql.getPs()).thenReturn(ps);
        when(ps.executeQuery()).thenReturn(rs);

        // Want 3 results in the resultset, so need true to be returned 3 times
        when(rs.next()).thenReturn(true, true, true, false);

        // Fill in the resultset
        when(rs.getInt("lot_id")).thenReturn(L1.getLot_id(), L2.getLot_id(), L3.getLot_id());
        when(rs.getString("parking_name")).thenReturn(L1.getParking_name(), L2.getParking_name(), L3.getParking_name());
        when(rs.getInt("cc_id")).thenReturn(L1.getCc_id(), L2.getCc_id(), L3.getCc_id());
        when(rs.getString("County")).thenReturn(L1.getCounty(), L2.getCounty(), L3.getCounty());

        int numUsersInTable = 3;
        LotDAO lotDao = new LotDAO(sql);
        Object result = lotDao.selectAllLots();

        assertEquals(expectedResults, result);
    }

    /**
     * Test of selectAllBookings method, of class LotDAO.
     */
    @Test
    public void testSelectAllBookigns() throws SQLException {

        SqlConnection sql = mock(SqlConnection.class);
        LotDAO lotDao = new LotDAO(sql);

        ParkedCars p1 = new ParkedCars(1, 1, new Date(11 / 11 / 11), new Date(11 / 11 / 11), 1);
        ParkedCars p2 = new ParkedCars(2, 2, new Date(22 / 11 / 22), new Date(22 / 11 / 22), 1);
        ParkedCars p3 = new ParkedCars(3, 3, new Date(33 / 11 / 33), new Date(33 / 11 / 33), 1);

        ArrayList<ParkedCars> expectedResults = new ArrayList();

        expectedResults.add(p1);
        expectedResults.add(p2);
        expectedResults.add(p3);

        // Create mock objects
        Connection conn = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);

        // Fill mock objects with appropriatel dummy data
        when(sql.getConn()).thenReturn(conn);
        when(conn.prepareStatement("select * from parked_cars")).thenReturn(ps);
        when(sql.getPs()).thenReturn(ps);
        when(ps.executeQuery()).thenReturn(rs);

        // Want 3 results in the resultset, so need true to be returned 3 times
        when(rs.next()).thenReturn(true, true, true, false);

        // Fill in the resultset
        when(rs.getInt("zone_id")).thenReturn(p1.getZone_id(), p2.getZone_id(), p3.getZone_id());
        when(rs.getInt("car_id")).thenReturn(p1.getCar_id(), p2.getCar_id(), p3.getCar_id());
        when(rs.getDate("book_from")).thenReturn(LotDAO.convertUtilToSql(p1.getBookFrom()), LotDAO.convertUtilToSql(p3.getBookFrom()), LotDAO.convertUtilToSql(p3.getBookFrom()));
        when(rs.getDate("book_to")).thenReturn(LotDAO.convertUtilToSql(p1.getBookTo()), LotDAO.convertUtilToSql(p3.getBookTo()), LotDAO.convertUtilToSql(p3.getBookTo()));
        when(rs.getInt("user_id")).thenReturn(p1.getUser_id(), p2.getUser_id(), p3.getUser_id());

        int numUsersInTable = 3;
        Object result = lotDao.selectAllBookings();

        assertEquals(expectedResults, result);
    }

    @Test
    public void testSelectAllBookignsByUserId() throws SQLException {

        SqlConnection sql = mock(SqlConnection.class);
        LotDAO lotDao = new LotDAO(sql);

        ParkedCars p1 = new ParkedCars(1, 1, new Date(11 / 11 / 11), new Date(11 / 11 / 11), 1);
        ParkedCars p2 = new ParkedCars(2, 2, new Date(22 / 11 / 22), new Date(22 / 11 / 22), 1);
        ParkedCars p3 = new ParkedCars(3, 3, new Date(33 / 11 / 33), new Date(33 / 11 / 33), 1);
        User u1 = new User(1, "Testing User1", "testinguser1@gmail.com", "$2a$12$Fodl2oDf233P40qSfkbVLOmX8R9a6kzuugosLS685hiVZr1qp7KWS", "user", "what is your mother's maiden name", "$2a$12$8XW5CMg.1ssMt9dvm5yMdeGjCTP51HfwFB8O5WDtNeFnNyxJmSBY6", false);

        ArrayList<ParkedCars> expectedResults = new ArrayList();

        expectedResults.add(p1);
        expectedResults.add(p2);
        expectedResults.add(p3);

        // Create mock objects
        Connection conn = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);

        // Fill mock objects with appropriatel dummy data
        when(sql.getConn()).thenReturn(conn);
        when(conn.prepareStatement("select * from parked_cars WHERE user_id = ?")).thenReturn(ps);
        when(sql.getPs()).thenReturn(ps);
        when(ps.executeQuery()).thenReturn(rs);

        // Want 3 results in the resultset, so need true to be returned 3 times
        when(rs.next()).thenReturn(true, true, true, false);

        // Fill in the resultset
        when(rs.getInt("zone_id")).thenReturn(p1.getZone_id(), p2.getZone_id(), p3.getZone_id());
        when(rs.getInt("car_id")).thenReturn(p1.getCar_id(), p2.getCar_id(), p3.getCar_id());
        when(rs.getDate("book_from")).thenReturn(LotDAO.convertUtilToSql(p1.getBookFrom()), LotDAO.convertUtilToSql(p3.getBookFrom()), LotDAO.convertUtilToSql(p3.getBookFrom()));
        when(rs.getDate("book_to")).thenReturn(LotDAO.convertUtilToSql(p1.getBookTo()), LotDAO.convertUtilToSql(p3.getBookTo()), LotDAO.convertUtilToSql(p3.getBookTo()));
        when(rs.getInt("user_id")).thenReturn(p1.getUser_id(), p2.getUser_id(), p3.getUser_id());

        int numUsersInTable = 3;
        Object result = lotDao.selectAllBookingsByUserId(u1);

        assertEquals(expectedResults, result);
    }

    /**
     * Test of selectAllZones method, of class LotDAO.
     */
    @Ignore
    public void testSelectAllZones() throws SQLException {
        Zone z1 = new Zone(1, "TestingZone1", 99, true, 1, 1, 0.0, 0.0, 23);
        Zone z2 = new Zone(2, "TestingZone2", 99, true, 2, 2, 0.0, 0.0, 12);
        Zone z3 = new Zone(3, "TestingZone3", 99, false, 3, 3, 0.0, 0.0, 11);

        ArrayList<Zone> expectedResults = new ArrayList();

        expectedResults.add(z1);
        expectedResults.add(z2);
        expectedResults.add(z3);

        // Create mock objects
        SqlConnection sql = mock(SqlConnection.class);
        Connection conn = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);

        LotDAO lotDao = new LotDAO(sql);

        // Fill mock objects with appropriatel dummy data
        when(sql.getConn()).thenReturn(conn);
        when(conn.prepareStatement("select * from parking_zones")).thenReturn(ps);
        when(sql.getPs()).thenReturn(ps);
        when(ps.executeQuery()).thenReturn(rs);

        // Want 3 results in the resultset, so need true to be returned 3 times
        when(rs.next()).thenReturn(true, true, true, false);

        // Fill in the resultset
        when(rs.getInt("zone_id")).thenReturn(z1.getZone_id(), z2.getZone_id(), z3.getZone_id());
        when(rs.getString("zone_name")).thenReturn(z1.getZone_name(), z2.getZone_name(), z3.getZone_name());
        when(rs.getInt("max_spaces")).thenReturn(z1.getMax_spaces(), z2.getMax_spaces(), z3.getMax_spaces());
        when(rs.getBoolean("is_vip")).thenReturn(z1.isIs_vip(), z2.isIs_vip(), z3.isIs_vip());
        when(rs.getInt("lot_id")).thenReturn(z1.getLot_id(), z2.getLot_id(), z3.getLot_id());
        when(rs.getInt("max_disabled_spaces")).thenReturn(z1.getMax_disabled_spaces(), z2.getMax_disabled_spaces(), z3.getMax_disabled_spaces());
        when(rs.getDouble("altitude")).thenReturn(z1.getLat(), z2.getLat(), z3.getLat());
        when(rs.getDouble("longitude")).thenReturn(z1.getLng(), z2.getLng(), z3.getLng());
        when(rs.getDouble("price")).thenReturn(z1.getPrice(), z2.getPrice(), z3.getPrice());

        Object result = lotDao.selectAllZones();

        assertEquals(expectedResults, result);
    }

    @Test
    public void testSelectAllZoneByLotId() throws SQLException {
        Zone z1 = new Zone(1, "TestingZone1", 99, true, 1, 1, 0.0, 0.0, 12);
        Zone z2 = new Zone(2, "TestingZone2", 99, true, 1, 2, 0.0, 0.0, 32);
        Zone z3 = new Zone(3, "TestingZone3", 99, false, 1, 3, 0.0, 0.0, 12);

        Lot L1 = new Lot(1, "Test Lot1", 1, "Meath");

        ArrayList<Zone> expectedResults = new ArrayList();

        expectedResults.add(z1);
        expectedResults.add(z2);
        expectedResults.add(z3);

        // Create mock objects
        SqlConnection sql = mock(SqlConnection.class);
        Connection conn = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);

        LotDAO lotDao = new LotDAO(sql);

        // Fill mock objects with appropriatel dummy data
        when(sql.getConn()).thenReturn(conn);
        when(conn.prepareStatement("select * from parking_zones WHERE lot_id = ?")).thenReturn(ps);
        when(sql.getPs()).thenReturn(ps, ps);
        when(ps.executeQuery()).thenReturn(rs);

        // Want 3 results in the resultset, so need true to be returned 3 times
        when(rs.next()).thenReturn(true, true, true, false);

        // Fill in the resultset
        when(rs.getInt("zone_id")).thenReturn(z1.getZone_id(), z2.getZone_id(), z3.getZone_id());
        when(rs.getString("zone_name")).thenReturn(z1.getZone_name(), z2.getZone_name(), z3.getZone_name());
        when(rs.getInt("max_spaces")).thenReturn(z1.getMax_spaces(), z2.getMax_spaces(), z3.getMax_spaces());
        when(rs.getBoolean("is_vip")).thenReturn(z1.isIs_vip(), z2.isIs_vip(), z3.isIs_vip());
        when(rs.getInt("lot_id")).thenReturn(z1.getLot_id(), z2.getLot_id(), z3.getLot_id());
        when(rs.getInt("max_disabled_spaces")).thenReturn(z1.getMax_disabled_spaces(), z2.getMax_disabled_spaces(), z3.getMax_disabled_spaces());
        when(rs.getDouble("altitude")).thenReturn(z1.getLat(), z2.getLat(), z3.getLat());
        when(rs.getDouble("longitude")).thenReturn(z1.getLng(), z2.getLng(), z3.getLng());
        when(rs.getDouble("price")).thenReturn(z1.getPrice(), z2.getPrice(), z3.getPrice());

        Object result = lotDao.selectAllZoneByLotId(L1);

        assertEquals(expectedResults, result);
    }

    @Test
    public void testSelectAllZoneByLotId_fail() throws SQLException {
        Zone z1 = new Zone(1, "TestingZone1", 99, true, 1, 1, 0.0, 0.0, 11);
        Zone z2 = new Zone(2, "TestingZone2", 99, true, 1, 2, 0.0, 0.0, 12);
        Zone z3 = new Zone(3, "TestingZone3", 99, false, 1, 3, 0.0, 0.0, 13);

        Lot L1 = new Lot(1, "Test Lot1", 1, "Louth");

        ArrayList<Zone> expectedResults = new ArrayList();

        // Create mock objects
        SqlConnection sql = mock(SqlConnection.class);
        Connection conn = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);

        LotDAO lotDao = new LotDAO(sql);

        // Fill mock objects with appropriatel dummy data
        when(sql.getConn()).thenReturn(conn);
        when(conn.prepareStatement("select * from parking_zones WHERE lot_id = ?")).thenReturn(ps);
        when(sql.getPs()).thenReturn(ps, ps);
        when(ps.executeQuery()).thenReturn(rs);

        // Want 3 results in the resultset, so need true to be returned 3 times
        when(rs.next()).thenReturn(false);

        Object result = lotDao.selectAllZoneByLotId(L1);

        assertEquals(expectedResults, result);
    }

    /**
     * Test of addLot method, of class LotDAO.
     */
    @Test
    public void testAddLot() throws SQLException {
        Lot L1 = new Lot(1, "Test Lot1", 1, "Meath");
        Lot L2 = new Lot(2, "Test Lot2", 2, "Louth");
        Lot L3 = new Lot(3, "Test Lot3", 3, "Dublin");

        String expectedResults = "{\"status_code\":1,\"message\":\"Parking Lot added Successfully\"}";

        // Create mock objects
        SqlConnection sql = mock(SqlConnection.class);
        Connection conn = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);

        when(sql.getConn()).thenReturn(conn);
        when(conn.prepareStatement("select * from parking_lots WHERE parking_name = ?")).thenReturn(ps);
        when(sql.getPs()).thenReturn(ps, ps);
        when(ps.executeQuery()).thenReturn(rs);

        // Want 3 results in the resultset, so need true to be returned 3 times
        when(rs.next()).thenReturn(false);

        when(sql.getConn()).thenReturn(conn);
        when(conn.prepareStatement("select * from parking_lots WHERE parking_name = ?")).thenReturn(ps);
        when(sql.getPs()).thenReturn(ps, ps);
        when(ps.executeQuery()).thenReturn(rs);

        // Want 3 results in the resultset, so need true to be returned 3 times
        when(rs.next()).thenReturn(false);

        // Fill mock objects with appropriatel dummy data
        when(sql.getConn()).thenReturn(conn);
        when(conn.prepareStatement("INSERT INTO parking_lots(Parking_name,cc_id) VALUES (?,?)")).thenReturn(ps);
        when(sql.getPs()).thenReturn(ps);

        LotDAO lotDao = new LotDAO(sql);
        Object result = lotDao.addLot(L3);

        assertEquals(expectedResults, result);

    }

    /**
     * Test of removeLot method, of class LotDAO.
     */
    @Test
    public void testRemoveLot() throws SQLException {
        Lot L1 = new Lot(1, "Test Lot1", 1, "Meath");
        Lot L2 = new Lot(2, "Test Lot2", 2, "Louth");
        Lot L3 = new Lot(3, "Test Lot3", 3, "Dublin");

        String expectedResults = "{\"status_code\":1,\"message\":\"Parking Lot Deleted Successfully\"}";

        // Create mock objects
        SqlConnection sql = mock(SqlConnection.class);
        Connection conn = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);

        // Fill mock objects with appropriatel dummy data
        when(sql.getConn()).thenReturn(conn);
        when(conn.prepareStatement("DELETE FROM parking_lots WHERE lot_id = ?")).thenReturn(ps);
        when(sql.getPs()).thenReturn(ps);

        LotDAO lotDao = new LotDAO(sql);
        Object result = lotDao.removeLot(L3);

        assertEquals(expectedResults, result);
    }

    /**
     * Test of addzone method, of class LotDAO.
     */
    @Test
    public void testAddzone() throws SQLException {
        Zone z1 = new Zone(1, "TestingZone1", 99, true, 1, 1, 0.0, 0.0, 12);
        Zone z2 = new Zone(2, "TestingZone2", 99, true, 2, 2, 0.0, 0.0, 23);
        Zone z3 = new Zone(3, "TestingZone3", 99, false, 3, 3, 0.0, 0.0, 32);

        String expectedResults = "{\"status_code\":1,\"message\":\"Parking Zone added Successfully\"}";

        // Create mock objects
        SqlConnection sql = mock(SqlConnection.class);
        Connection conn = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);

        when(sql.getConn()).thenReturn(conn);
        when(conn.prepareStatement("select * from parking_zones WHERE zone_name = ?")).thenReturn(ps);
        when(sql.getPs()).thenReturn(ps, ps);
        when(ps.executeQuery()).thenReturn(rs);

        // Want 3 results in the resultset, so need true to be returned 3 times
        when(rs.next()).thenReturn(false);

        when(sql.getConn()).thenReturn(conn);
        when(conn.prepareStatement("select * from parking_zones WHERE zone_name = ?")).thenReturn(ps);
        when(sql.getPs()).thenReturn(ps, ps);
        when(ps.executeQuery()).thenReturn(rs);

        // Want 3 results in the resultset, so need true to be returned 3 times
        when(rs.next()).thenReturn(false);

        // Fill mock objects with appropriatel dummy data
        when(sql.getConn()).thenReturn(conn);
        when(conn.prepareStatement("INSERT INTO parking_zones(zone_name,max_spaces,is_vip,lot_id,max_disabled_spaces) VALUES (?,?,?,?,?)")).thenReturn(ps);
        when(sql.getPs()).thenReturn(ps, ps, ps, ps, ps, ps);

        LotDAO lotDao = new LotDAO(sql);
        String result = lotDao.addzone(z3);

        assertEquals(expectedResults, result);
    }

    /**
     * Test of addBooking method, of class LotDAO.
     */
    @Ignore
    public void testAddBooking() throws SQLException {
        Zone z1 = new Zone(1, "TestingZone1", 99, true, 1, 1, 0.0, 0.0, 23);
        Zone z2 = new Zone(2, "TestingZone2", 99, true, 2, 2, 0.0, 0.0, 31);
        Zone z3 = new Zone(3, "TestingZone3", 99, false, 3, 3, 0.0, 0.0, 12);

        String expectedResults = "";

        SqlConnection sql = mock(SqlConnection.class);
        Connection conn = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);

        LotDAO lotDao = new LotDAO(sql);

        when(sql.getConn()).thenReturn(conn);
        when(conn.prepareStatement("SELECT max_spaces FROM parking_zones WHERE zone_id = ?")).thenReturn(ps);
        when(sql.getPs()).thenReturn(ps, ps);
        when(ps.executeQuery()).thenReturn(rs);

        when(sql.getConn()).thenReturn(conn);
        when(conn.prepareStatement("SELECT COUNT(*) FROM parked_cars WHERE zone_id = ?")).thenReturn(ps);
        when(sql.getPs()).thenReturn(ps, ps);
        when(ps.executeQuery()).thenReturn(rs);

        when(rs.next()).thenReturn(true, true);

        when(rs.getInt("max_spaces")).thenReturn(z1.getMax_spaces());
        when(rs.getInt(1)).thenReturn(z1.getZone_id());

        when(sql.getConn()).thenReturn(conn);
        when(conn.prepareStatement("INSERT INTO parked_cars(zone_id,car_id,book_from,book_to,user_id) VALUES (?,?,?,?,?)")).thenReturn(ps);
        when(sql.getPs()).thenReturn(ps, ps, ps, ps, ps);

        Object result = lotDao.selectAllBookings();

        assertEquals(expectedResults, result);
    }

    @Test
    public void CheckIfzoneExist() throws SQLException {
        Zone z1 = new Zone(1, "TestingZone1", 99, true, 1, 1, 0.0, 0.0, 12);
        Zone z2 = new Zone(2, "TestingZone2", 99, true, 1, 2, 0.0, 0.0, 32);
        Zone z3 = new Zone(3, "TestingZone3", 99, false, 1, 3, 0.0, 0.0, 12);

        boolean expectedResults = true;

        // Create mock objects
        SqlConnection sql = mock(SqlConnection.class);
        Connection conn = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);

        LotDAO lotDao = new LotDAO(sql);

        // Fill mock objects with appropriatel dummy data
        when(sql.getConn()).thenReturn(conn);
        when(conn.prepareStatement("select * from parking_zones WHERE lot_id = ?")).thenReturn(ps);
        when(sql.getPs()).thenReturn(ps, ps);
        when(ps.executeQuery()).thenReturn(rs);

        // Want 3 results in the resultset, so need true to be returned 3 times
        when(rs.next()).thenReturn(true);

        Object result = lotDao.CheckIfzoneExist(z3);

        assertEquals(expectedResults, result);
    }

    @Test
    public void CheckIfzoneExist_fail() throws SQLException {
        Zone z1 = new Zone(1, "TestingZone1", 99, true, 1, 1, 0.0, 0.0, 31);
        Zone z2 = new Zone(2, "TestingZone2", 99, true, 1, 2, 0.0, 0.0, 12);
        Zone z3 = new Zone(3, "TestingZone3", 99, false, 1, 3, 0.0, 0.0, 1);

        boolean expectedResults = false;

        // Create mock objects
        SqlConnection sql = mock(SqlConnection.class);
        Connection conn = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);

        LotDAO lotDao = new LotDAO(sql);

        // Fill mock objects with appropriatel dummy data
        when(sql.getConn()).thenReturn(conn);
        when(conn.prepareStatement("select * from parking_zones WHERE zone_name = ?")).thenReturn(ps);
        when(sql.getPs()).thenReturn(ps, ps);
        when(ps.executeQuery()).thenReturn(rs);

        // Want 3 results in the resultset, so need true to be returned 3 times
        when(rs.next()).thenReturn(false);

        Object result = lotDao.CheckIfzoneExist(z3);

        assertEquals(expectedResults, result);
    }

    @Test
    public void CheckIfLotExist() throws SQLException {
        Lot L1 = new Lot(1, "Test Lot1", 1, "Meath");
        Lot L2 = new Lot(2, "Test Lot2", 2, "Louth");
        Lot L3 = new Lot(3, "Test Lot3", 3, "Dublin");

        boolean expectedResults = true;

        // Create mock objects
        SqlConnection sql = mock(SqlConnection.class);
        Connection conn = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);

        LotDAO lotDao = new LotDAO(sql);

        // Fill mock objects with appropriatel dummy data
        when(sql.getConn()).thenReturn(conn);
        when(conn.prepareStatement("select * from parking_lots WHERE parking_name = ?")).thenReturn(ps);
        when(sql.getPs()).thenReturn(ps, ps);
        when(ps.executeQuery()).thenReturn(rs);

        // Want 3 results in the resultset, so need true to be returned 3 times
        when(rs.next()).thenReturn(true);

        Object result = lotDao.CheckIfLotExist(L3);

        assertEquals(expectedResults, result);
    }

    @Test
    public void CheckIfLotExist_fail() throws SQLException {
        Lot L1 = new Lot(1, "Test Lot1", 1, "Meath");
        Lot L2 = new Lot(2, "Test Lot2", 2, "Louth");
        Lot L3 = new Lot(3, "Test Lot3", 3, "Dublin");

        boolean expectedResults = false;

        // Create mock objects
        SqlConnection sql = mock(SqlConnection.class);
        Connection conn = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);

        LotDAO lotDao = new LotDAO(sql);

        // Fill mock objects with appropriatel dummy data
        when(sql.getConn()).thenReturn(conn);
        when(conn.prepareStatement("select * from parking_lots WHERE parking_name = ?")).thenReturn(ps);
        when(sql.getPs()).thenReturn(ps, ps);
        when(ps.executeQuery()).thenReturn(rs);

        // Want 3 results in the resultset, so need true to be returned 3 times
        when(rs.next()).thenReturn(false);

        Object result = lotDao.CheckIfLotExist(L3);

        assertEquals(expectedResults, result);
    }

    @Test
    public void testCheckIfBookingExistUnderThatZone() throws SQLException {

        SqlConnection sql = mock(SqlConnection.class);
        LotDAO lotDao = new LotDAO(sql);

        ParkedCars p1 = new ParkedCars(1, 1, new Date(11 / 11 / 11), new Date(11 / 11 / 11), 1);
        ParkedCars p2 = new ParkedCars(2, 2, new Date(22 / 11 / 22), new Date(22 / 11 / 22), 1);
        ParkedCars p3 = new ParkedCars(3, 3, new Date(33 / 11 / 33), new Date(33 / 11 / 33), 1);

        boolean expectedResults = true;

        // Create mock objects
        Connection conn = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);

        // Fill mock objects with appropriatel dummy data
        when(sql.getConn()).thenReturn(conn);
        when(conn.prepareStatement("select * from parked_cars WHERE zone_id = ?")).thenReturn(ps);
        when(sql.getPs()).thenReturn(ps);
        when(ps.executeQuery()).thenReturn(rs);

        // Want 3 results in the resultset, so need true to be returned 3 times
        when(rs.next()).thenReturn(true);

        Object result = lotDao.CheckIfBookingExistUnderThatZone(p3);

        assertEquals(expectedResults, result);
    }

    @Test
    public void testCheckIfBookingExistUnderThatZone_fail() throws SQLException {

        SqlConnection sql = mock(SqlConnection.class);
        LotDAO lotDao = new LotDAO(sql);

        ParkedCars p1 = new ParkedCars(1, 1, new Date(11 / 11 / 11), new Date(11 / 11 / 11), 1);
        ParkedCars p2 = new ParkedCars(2, 2, new Date(22 / 11 / 22), new Date(22 / 11 / 22), 1);
        ParkedCars p3 = new ParkedCars(3, 3, new Date(33 / 11 / 33), new Date(33 / 11 / 33), 1);

        boolean expectedResults = false;

        // Create mock objects
        Connection conn = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);

        // Fill mock objects with appropriatel dummy data
        when(sql.getConn()).thenReturn(conn);
        when(conn.prepareStatement("select * from parked_cars WHERE zone_id = ?")).thenReturn(ps);
        when(sql.getPs()).thenReturn(ps);
        when(ps.executeQuery()).thenReturn(rs);

        // Want 3 results in the resultset, so need true to be returned 3 times
        when(rs.next()).thenReturn(false);

        Object result = lotDao.CheckIfBookingExistUnderThatZone(p3);

        assertEquals(expectedResults, result);
    }

    @Test
    public void testGetBookingDate() throws SQLException {

        SqlConnection sql = mock(SqlConnection.class);
        LotDAO lotDao = new LotDAO(sql);

        ParkedCars p1 = new ParkedCars(1, 1, new Date(11 / 11 / 11), new Date(11 / 11 / 11), 1);
        ParkedCars p2 = new ParkedCars(2, 2, new Date(22 / 11 / 22), new Date(22 / 11 / 22), 1);
        ParkedCars p3 = new ParkedCars(3, 3, new Date(33 / 11 / 33), new Date(33 / 11 / 33), 1);

        boolean expectedResults = true;

        // Create mock objects
        Connection conn = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);

        // Fill mock objects with appropriatel dummy data
        when(sql.getConn()).thenReturn(conn);
        when(conn.prepareStatement("select * from parked_cars WHERE book_from = ?")).thenReturn(ps);
        when(sql.getPs()).thenReturn(ps);
        when(ps.executeQuery()).thenReturn(rs);

        // Want 3 results in the resultset, so need true to be returned 3 times
        when(rs.next()).thenReturn(true);

        Object result = lotDao.getBookingDate(p3);

        assertEquals(expectedResults, result);
    }

    @Test
    public void testGetBookingDate_fail() throws SQLException {

        SqlConnection sql = mock(SqlConnection.class);
        LotDAO lotDao = new LotDAO(sql);

        ParkedCars p1 = new ParkedCars(1, 1, new Date(11 / 11 / 11), new Date(11 / 11 / 11), 1);
        ParkedCars p2 = new ParkedCars(2, 2, new Date(22 / 11 / 22), new Date(22 / 11 / 22), 1);
        ParkedCars p3 = new ParkedCars(3, 3, new Date(33 / 11 / 33), new Date(33 / 11 / 33), 1);

        boolean expectedResults = false;

        // Create mock objects
        Connection conn = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);

        // Fill mock objects with appropriatel dummy data
        when(sql.getConn()).thenReturn(conn);
        when(conn.prepareStatement("select * from parked_cars WHERE book_from = ?")).thenReturn(ps);
        when(sql.getPs()).thenReturn(ps);
        when(ps.executeQuery()).thenReturn(rs);

        // Want 3 results in the resultset, so need true to be returned 3 times
        when(rs.next()).thenReturn(false);

        Object result = lotDao.getBookingDate(p3);

        assertEquals(expectedResults, result);
    }

    @Test
    public void testRemoveBooking() throws SQLException {
        ParkedCars p1 = new ParkedCars(1, 1, new Date(11 / 11 / 11), new Date(11 / 11 / 11), 1);
        ParkedCars p2 = new ParkedCars(2, 2, new Date(22 / 11 / 22), new Date(22 / 11 / 22), 1);
        ParkedCars p3 = new ParkedCars(3, 3, new Date(33 / 11 / 33), new Date(33 / 11 / 33), 1);

        String expectedResults = "{\"status_code\":1,\"message\":\"Booking Deleted Successfully\"}";

        // Create mock objects
        SqlConnection sql = mock(SqlConnection.class);
        Connection conn = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);

        // Fill mock objects with appropriatel dummy data
        when(sql.getConn()).thenReturn(conn);
        when(conn.prepareStatement("DELETE FROM parked_cars WHERE car_id = ? AND user_id = ? AND zone_id = ?")).thenReturn(ps);
        when(sql.getPs()).thenReturn(ps);

        LotDAO lotDao = new LotDAO(sql);
        Object result = lotDao.removeBooking(p3);

        assertEquals(expectedResults, result);
    }

    @Test
    public void testUpdateBooking() throws SQLException {
        ParkedCars p1 = new ParkedCars(1, 1, new Date(11 / 11 / 11), new Date(11 / 11 / 11), 1);
        ParkedCars p2 = new ParkedCars(2, 2, new Date(22 / 11 / 22), new Date(22 / 11 / 22), 1);
        ParkedCars p3 = new ParkedCars(3, 3, new Date(33 / 11 / 33), new Date(33 / 11 / 33), 1);

        String expectedResults = "{\"status_code\":1,\"message\":\"Booking Updated Successfully\"}";

        // Create mock objects
        SqlConnection sql = mock(SqlConnection.class);
        Connection conn = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);

        // Fill mock objects with appropriatel dummy data
        when(sql.getConn()).thenReturn(conn);
        when(conn.prepareStatement("UPDATE parked_cars SET car_id = ? WHERE user_id = ? AND zone_id = ?")).thenReturn(ps);
        when(sql.getPs()).thenReturn(ps);

        LotDAO lotDao = new LotDAO(sql);
        Object result = lotDao.updateBooking(p3);

        assertEquals(expectedResults, result);
    }

    @Test
    public void testRemoveZone() throws SQLException {
        Zone z1 = new Zone(1, "TestingZone1", 99, true, 1, 1, 0.0, 0.0, 23);

        String expectedResults = "{\"status_code\":1,\"message\":\"Parking Zone Deleted Successfully\"}";

        // Create mock objects
        SqlConnection sql = mock(SqlConnection.class);
        Connection conn = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);

        // Fill mock objects with appropriatel dummy data
        when(sql.getConn()).thenReturn(conn);
        when(conn.prepareStatement("DELETE FROM parking_zones WHERE zone_id = ?")).thenReturn(ps);
        when(sql.getPs()).thenReturn(ps);

        LotDAO lotDao = new LotDAO(sql);
        Object result = lotDao.removeZone(z1);

        assertEquals(expectedResults, result);
    }

    @Test
    public void testUpdateZone() throws SQLException {
        Zone z1 = new Zone(1, "TestingZone1", 99, true, 1, 1, 0.0, 0.0, 23);

        String expectedResults = "{\"status_code\":1,\"message\":\"Zone Updated Successfully\"}";

        // Create mock objects
        SqlConnection sql = mock(SqlConnection.class);
        Connection conn = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);

        // Fill mock objects with appropriatel dummy data
        when(sql.getConn()).thenReturn(conn);
        when(conn.prepareStatement("UPDATE parking_zones SET zone_name = ? WHERE  zone_id = ?")).thenReturn(ps);
        when(sql.getPs()).thenReturn(ps);

        LotDAO lotDao = new LotDAO(sql);
        Object result = lotDao.updateZone(z1);

        assertEquals(expectedResults, result);
    }

    @Test
    public void testCheckBooking() throws SQLException {
        ParkedCars p1 = new ParkedCars(1, 1, new Date(11 / 11 / 11), new Date(11 / 11 / 11), 1);

        String expectedResults = "{\"status_code\":1,\"message\":\"You Can Book Your Car Spot\"}";

        // Create mock objects
        SqlConnection sql = mock(SqlConnection.class);
        Connection conn = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);

        // Fill mock objects with appropriatel dummy data 
        //Mocking for CheckIfBookingExistUnderThatZone
        when(sql.getConn()).thenReturn(conn);
        when(conn.prepareStatement("select * from parked_cars WHERE zone_id = ?")).thenReturn(ps);
        when(sql.getPs()).thenReturn(ps, ps);
        when(ps.executeQuery()).thenReturn(rs);

        // Mocking get booking by date
        when(sql.getConn()).thenReturn(conn);
        when(conn.prepareStatement("select * from parked_cars WHERE book_from = ?")).thenReturn(ps);
        when(sql.getPs()).thenReturn(ps, ps);
        when(ps.executeQuery()).thenReturn(rs);

        when(rs.next()).thenReturn(false, false);
        
        when(sql.getConn()).thenReturn(conn);
        when(conn.prepareStatement("select * from parked_cars WHERE zone_id = ?")).thenReturn(ps);
        when(sql.getPs()).thenReturn(ps, ps);
        when(ps.executeQuery()).thenReturn(rs);

        // Mocking get booking by date
        when(sql.getConn()).thenReturn(conn);
        when(conn.prepareStatement("select * from parked_cars WHERE book_from = ?")).thenReturn(ps);
        when(sql.getPs()).thenReturn(ps, ps);
        when(ps.executeQuery()).thenReturn(rs);

        when(rs.next()).thenReturn(false, false);        

        LotDAO lotDao = new LotDAO(sql);
        Object result = lotDao.checkBooking(p1);

        assertEquals(expectedResults, result);
    }

}
