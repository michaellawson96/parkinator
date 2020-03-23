/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DaoIntegration;

import Dao.*;
import Dto.Car;
import Dto.Cc;
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

}
