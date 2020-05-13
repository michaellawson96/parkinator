/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Dto.PaymentLogs;
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
 * @author SeppQ
 */
public class PaymentLogDAOTest {
    
    public PaymentLogDAOTest() {
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
     * Test of insertPaymentLogs method, of class PaymentLogDAO.
     */
    @Test
    public void testInsertPaymentLogs() throws SQLException {
        PaymentLogs pl1 = new PaymentLogs("2020-05-05T20:38:37Z","123asdga-123","CAPTURE","COMPLETED");
        PaymentLogs pl2 = new PaymentLogs("2020-05-05T20:38:37Z","123asd213dasdaga-123","CAPTURE","FAILED");
        PaymentLogs pl3 = new PaymentLogs("2020-05-05T20:38:37Z","123asd213advbga-123","CAPTURE","COMPLETED");

        String expectedResults ="{\"status_code\":1,\"message\":\"Payment Log has Been Added\"}";


        // Create mock objects
        SqlConnection sql = mock(SqlConnection.class);
        Connection conn = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);

        // Fill mock objects with appropriatel dummy data
        when(sql.getConn()).thenReturn(conn);
        when(conn.prepareStatement("INSERT INTO paymentlogs(id,create_time,intent,status) VALUES (?,?,?,?)")).thenReturn(ps);
        when(sql.getPs()).thenReturn(ps ,ps ,ps,ps,ps);

        // Want 3 results in the resultset, so need true to be returned 3 times
        PaymentLogDAO plDao = new PaymentLogDAO(sql);
        Object result = plDao.insertPaymentLogs(pl3);

        assertEquals(expectedResults, result);
    }

    /**
     * Test of selectPaymentLogs method, of class PaymentLogDAO.
     */
    @Test
    public void testSelectPaymentLogs() throws SQLException {
        PaymentLogs pl1 = new PaymentLogs("123asdga-123","2020-05-05T20:38:37Z","CAPTURE","COMPLETED");
        PaymentLogs pl2 = new PaymentLogs("123asd213dasdaga-123","2020-05-05T20:38:37Z","CAPTURE","FAILED");
        PaymentLogs pl3 = new PaymentLogs("123asd213advbga-123","2020-05-05T20:38:37Z","CAPTURE","COMPLETED");

        ArrayList<PaymentLogs> expectedResults = new ArrayList();

        expectedResults.add(pl1);
        expectedResults.add(pl2);
        expectedResults.add(pl3);

        // Create mock objects
        SqlConnection sql = mock(SqlConnection.class);
        Connection conn = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);

        // Fill mock objects with appropriatel dummy data
        when(sql.getConn()).thenReturn(conn);
        when(conn.prepareStatement("select * from paymentlogs")).thenReturn(ps);
        when(sql.getPs()).thenReturn(ps);
        when(ps.executeQuery()).thenReturn(rs);

        // Want 3 results in the resultset, so need true to be returned 3 times
        when(rs.next()).thenReturn(true, true, true, false);

        // Fill in the resultset
        
        when(rs.getString("id")).thenReturn(pl1.getId(), pl2.getId(),pl3.getId());
        when(rs.getString("create_time")).thenReturn(pl1.getCreate_time(), pl2.getCreate_time(), pl3.getCreate_time());
        when(rs.getString("intent")).thenReturn(pl1.getIntent(), pl2.getIntent(), pl3.getIntent());
        when(rs.getString("status")).thenReturn(pl1.getStatus(), pl2.getStatus(), pl3.getStatus());
        

        PaymentLogDAO plDao = new PaymentLogDAO(sql);
        Object result = plDao.selectPaymentLogs();

        assertEquals(expectedResults, result);

    }
    
}
