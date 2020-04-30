/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Dto.Support;
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
public class SupportDaoTest {
    
    public SupportDaoTest() {
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
     * Test of insertMessage method, of class SupportDao.
     */
    @Test
    public void testInsertMessage() throws SQLException {
        Support s1 = new Support(1, "testing1", "testing message1", new Date(11 / 11 / 11), 1);
        Support s2 = new Support(2, "testing2", "testing message2", new Date(22 / 11 / 11), 2);
        Support s3 = new Support(3, "testing3", "testing message3", new Date(23 / 11 / 11), 3);

        String expectedResults = "{\"status_code\":1,\"message\":\"Your post has been Sent\"}";

        // Create mock objects
        SqlConnection sql = mock(SqlConnection.class);
        Connection conn = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);

        // Fill mock objects with appropriatel dummy data
        when(sql.getConn()).thenReturn(conn);
        when(conn.prepareStatement("INSERT INTO support(title,message,date,user_id) VALUES (?,?,?,?)")).thenReturn(ps);
        when(sql.getPs()).thenReturn(ps, ps, ps, ps, ps);

        SupportDao supportDao = new SupportDao(sql);
        Object result = supportDao.insertMessage(s3);

        assertEquals(expectedResults, result);
    }

    /**
     * Test of selectAllMessage method, of class SupportDao.
     */
    @Test
    public void testSelectAllMessage() throws SQLException {
        Support s1 = new Support(1, "testing1", "testing message1", new Date(11 / 11 / 11), 1);
        Support s2 = new Support(2, "testing2", "testing message2", new Date(22 / 11 / 11), 2);
        Support s3 = new Support(3, "testing3", "testing message3", new Date(23 / 11 / 11), 3);


        ArrayList<Support> expectedResults = new ArrayList();

        expectedResults.add(s1);
        expectedResults.add(s2);
        expectedResults.add(s3);

        // Create mock objects
        SqlConnection sql = mock(SqlConnection.class);
        Connection conn = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);

        // Fill mock objects with appropriatel dummy data
        when(sql.getConn()).thenReturn(conn);
        when(conn.prepareStatement("select * from support")).thenReturn(ps);
        when(sql.getPs()).thenReturn(ps);
        when(ps.executeQuery()).thenReturn(rs);

        // Want 3 results in the resultset, so need true to be returned 3 times
        when(rs.next()).thenReturn(true, true, true, false);

        // Fill in the resultset
        when(rs.getInt("message_id")).thenReturn(s1.getMessage_id(), s2.getMessage_id(), s3.getMessage_id());
        when(rs.getString("title")).thenReturn(s1.getTitle(), s2.getTitle(), s3.getTitle());
        when(rs.getString("message")).thenReturn(s1.getMessage(), s2.getMessage(), s3.getMessage());
        when(rs.getDate("date")).thenReturn(SupportDao.convertUtilToSql(s1.getDate()), SupportDao.convertUtilToSql(s1.getDate()), SupportDao.convertUtilToSql(s1.getDate()));
        when(rs.getInt("user_id")).thenReturn(s1.getUser_id(), s2.getUser_id(), s3.getUser_id());

        int numUsersInTable = 3;
        SupportDao supportDao = new SupportDao(sql);
        Object result = supportDao.selectAllMessage();

        assertEquals(expectedResults, result);
    }

    /**
     * Test of removeMessage method, of class SupportDao.
     */
    @Test
    public void testRemoveMessage() throws SQLException {
        Support s1 = new Support(1, "testing1", "testing message1", new Date(11 / 11 / 11), 1);
        Support s2 = new Support(2, "testing2", "testing message2", new Date(22 / 11 / 11), 2);
        Support s3 = new Support(3, "testing3", "testing message3", new Date(23 / 11 / 11), 3);

        String expectedResults = "{\"status_code\":1,\"message\":\"Message Removed\"}";

        // Create mock objects
        SqlConnection sql = mock(SqlConnection.class);
        Connection conn = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);

        // Fill mock objects with appropriatel dummy data
        when(sql.getConn()).thenReturn(conn);
        when(conn.prepareStatement("DELETE FROM support WHERE message_id = ?")).thenReturn(ps);
        when(sql.getPs()).thenReturn(ps,ps);

        SupportDao supportDao = new SupportDao(sql);
        Object result = supportDao.removeMessage(s3);

        assertEquals(expectedResults, result);
    }
    
}
