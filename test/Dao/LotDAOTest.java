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
        ParkedCars p1 = new ParkedCars(1, 1, new java.sql.Date(11/11/11), new java.sql.Date(11/11/11));
        ParkedCars p2 = new ParkedCars(2, 2, new java.sql.Date(22/11/22), new java.sql.Date(22/11/22));
        ParkedCars p3 = new ParkedCars(3, 3, new java.sql.Date(33/11/33), new java.sql.Date(33/11/33));

        ArrayList<ParkedCars> expectedResults = new ArrayList();

        expectedResults.add(p1);
        expectedResults.add(p2);
        expectedResults.add(p3);

        // Create mock objects
        SqlConnection sql = mock(SqlConnection.class);
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
        when(rs.getDate("bookFrom")).thenReturn(p1.getBookFrom(), p2.getBookFrom(), p3.getBookFrom());
        when(rs.getDate("bookTo")).thenReturn(p1.getBookTo(),p2.getBookTo(), p3.getBookTo());

        int numUsersInTable = 3;
        LotDAO lotDao = new LotDAO(sql);
        Object result = lotDao.selectAllBookigns();

        assertEquals(expectedResults, result);
    }

    /**
     * Test of selectAllZones method, of class LotDAO.
     */
    @Test
    public void testSelectAllZones() {
        System.out.println("selectAllZones");
        LotDAO instance = new LotDAO();
        Object expResult = null;
        Object result = instance.selectAllZones();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of AddLot method, of class LotDAO.
     */
    @Test
    public void testAddLot() {
        System.out.println("AddLot");
        Lot lot = null;
        LotDAO instance = new LotDAO();
        String expResult = "";
        String result = instance.AddLot(lot);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of RemoveLot method, of class LotDAO.
     */
    @Test
    public void testRemoveLot() {
        System.out.println("RemoveLot");
        Lot lot = null;
        LotDAO instance = new LotDAO();
        String expResult = "";
        String result = instance.RemoveLot(lot);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of Addzone method, of class LotDAO.
     */
    @Test
    public void testAddzone() {
        System.out.println("Addzone");
        Zone zone = null;
        LotDAO instance = new LotDAO();
        String expResult = "";
        String result = instance.Addzone(zone);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of AddBooking method, of class LotDAO.
     */
    @Test
    public void testAddBooking() {
        System.out.println("AddBooking");
        ParkedCars pc = null;
        LotDAO instance = new LotDAO();
        String expResult = "";
        String result = instance.AddBooking(pc);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
