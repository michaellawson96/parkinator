/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Dto.Car;
import Dto.Lot;
import Dto.ParkedCars;
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
        Lot L1 = new Lot(1, "Test Lot1", 1);
        Lot L2 = new Lot(2, "Test Lot2", 2);
        Lot L3 = new Lot(3, "Test Lot3", 3);

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

        int numUsersInTable = 3;
        LotDAO lotDao = new LotDAO(sql);
        Object result = lotDao.selectAllLots();

        assertEquals(expectedResults, result);
    }

    /**
     * Test of selectAllBookigns method, of class LotDAO.
     */
    @Test
    public void testSelectAllBookigns() throws SQLException {
        ParkedCars p1 = new ParkedCars(1, 1, new Date(11 / 11 / 11), new Date(11 / 11 / 11));
        ParkedCars p2 = new ParkedCars(2, 2, new java.sql.Date(22 / 11 / 22), new java.sql.Date(22 / 11 / 22));
        ParkedCars p3 = new ParkedCars(3, 3, new java.sql.Date(33 / 11 / 33), new java.sql.Date(33 / 11 / 33));

        ArrayList<ParkedCars> expectedResults = new ArrayList();

        expectedResults.add(p1);
        expectedResults.add(p2);
        expectedResults.add(p3);

        // Create mock objects
        SqlConnection sql = mock(SqlConnection.class);
        Connection conn = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);
        LotDAO lotDao = new LotDAO(sql);

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
        when(rs.getDate("bookFrom")).thenReturn(LotDAO.convertUtilToSql(p1.getBookFrom()), LotDAO.convertUtilToSql(p3.getBookFrom()), LotDAO.convertUtilToSql(p3.getBookFrom()));
        when(rs.getDate("bookTo")).thenReturn(LotDAO.convertUtilToSql(p1.getBookTo()), LotDAO.convertUtilToSql(p3.getBookTo()), LotDAO.convertUtilToSql(p3.getBookTo()));

        int numUsersInTable = 3;
        Object result = lotDao.selectAllBookigns();

        assertEquals(expectedResults, result);
    }

    /**
     * Test of selectAllZones method, of class LotDAO.
     */
    @Ignore
    public void testSelectAllZones() throws SQLException {
        Zone z1 = new Zone(1, "Test Zone1", 99, false, 1, 33);
        Zone z2 = new Zone(2, "Test Zone2", 99, true, 2, 33);
        Zone z3 = new Zone(3, "Test Zone3", 99, false, 3, 33);

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
        when(rs.getBoolean("is_vip")).thenReturn(z1.getIs_vip(), z2.getIs_vip(), z3.getIs_vip());
        when(rs.getInt("lot_id")).thenReturn(z1.getLot_id(), z2.getLot_id(), z3.getLot_id());
        when(rs.getInt("max_disabled_spaces")).thenReturn(z1.getMax_disabled_spaces(), z2.getMax_disabled_spaces(), z3.getMax_disabled_spaces());

        Object result = lotDao.selectAllZones();

        assertEquals(expectedResults, result);
    }

    /**
     * Test of AddLot method, of class LotDAO.
     */
    @Test
    public void testAddLot() throws SQLException {
        Lot L1 = new Lot(1, "Test Lot1", 1);
        Lot L2 = new Lot(2, "Test Lot2", 2);
        Lot L3 = new Lot(3, "Test Lot3", 3);

        String expectedResults = "{\"status_code\":1,\"message\":\"Parking Lot added Successfully\"}";

        // Create mock objects
        SqlConnection sql = mock(SqlConnection.class);
        Connection conn = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);

        // Fill mock objects with appropriatel dummy data
        when(sql.getConn()).thenReturn(conn);
        when(conn.prepareStatement("INSERT INTO parking_lots(Parking_name,cc_id) VALUES (?,?)")).thenReturn(ps);
        when(sql.getPs()).thenReturn(ps);

        LotDAO lotDao = new LotDAO(sql);
        Object result = lotDao.AddLot(L3);

        assertEquals(expectedResults, result);

    }

    /**
     * Test of RemoveLot method, of class LotDAO.
     */
    @Test
    public void testRemoveLot() throws SQLException {
        Lot L1 = new Lot(1, "Test Lot1", 1);
        Lot L2 = new Lot(2, "Test Lot2", 2);
        Lot L3 = new Lot(3, "Test Lot3", 3);

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
        Object result = lotDao.RemoveLot(L3);

        assertEquals(expectedResults, result);
    }

    /**
     * Test of Addzone method, of class LotDAO.
     */
    @Test
    public void testAddzone() throws SQLException {
        Zone z1 = new Zone(1, "Test Zone1", 99, false, 1, 33);
        Zone z2 = new Zone(2, "Test Zone2", 99, true, 2, 33);
        Zone z3 = new Zone(3, "Test Zone3", 99, false, 3, 33);

        String expectedResults = "{\"status_code\":1,\"message\":\"Parking Zone added Successfully\"}";

        // Create mock objects
        SqlConnection sql = mock(SqlConnection.class);
        Connection conn = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);

        // Fill mock objects with appropriatel dummy data
        when(sql.getConn()).thenReturn(conn);
        when(conn.prepareStatement("INSERT INTO parking_zones(zone_name,max_spaces,is_vip,lot_id,max_disabled_spaces) VALUES (?,?,?,?,?)")).thenReturn(ps);
        when(sql.getPs()).thenReturn(ps, ps, ps, ps, ps, ps);

        LotDAO lotDao = new LotDAO(sql);
        String result = lotDao.Addzone(z3);

        assertEquals(expectedResults, result);
    }

    /**
     * Test of AddBooking method, of class LotDAO.
     */
    @Ignore
    public void testAddBooking() throws SQLException {
        ParkedCars p1 = new ParkedCars(1, 1, new Date(11 / 11 / 11), new Date(11 / 11 / 11));
        ParkedCars p2 = new ParkedCars(2, 2, new java.sql.Date(22 / 11 / 22), new java.sql.Date(22 / 11 / 22));
        ParkedCars p3 = new ParkedCars(3, 3, new java.sql.Date(33 / 11 / 33), new java.sql.Date(33 / 11 / 33));

        String expectedResults = "";

        SqlConnection sql = mock(SqlConnection.class);
        Connection conn = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);
        LotDAO lotDao = new LotDAO(sql);

        // Fill mock objects with appropriatel dummy data
        when(sql.getConn()).thenReturn(conn);
        when(conn.prepareStatement("select * from parked_cars")).thenReturn(ps);
        when(sql.getPs()).thenReturn(ps, ps, ps, ps, ps);

        Object result = lotDao.selectAllBookigns();

        assertEquals(expectedResults, result);
    }

}
