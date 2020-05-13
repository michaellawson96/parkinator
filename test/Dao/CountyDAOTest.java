/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Dto.County;
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
 * @author SeppQ
 */
public class CountyDAOTest {

    public CountyDAOTest() {
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
     * Test of selectAllCounties method, of class CountyDAO.
     */
    @Test
    public void testSelectAllCounties() throws SQLException {
        County c1 = new County(1, "Meath");
        County c2 = new County(2, "Louth");
        County c3 = new County(3, "Dublin");

        ArrayList<County> expectedResults = new ArrayList();

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
        when(conn.prepareStatement("select * from counties")).thenReturn(ps);
        when(sql.getPs()).thenReturn(ps);
        when(ps.executeQuery()).thenReturn(rs);
        // Want 3 results in the resultset, so need true to be returned 3 times
        when(rs.next()).thenReturn(true, true, true, false);
        // Fill in the resultset
        when(rs.getInt("countyID")).thenReturn(c1.getCountyID(), c2.getCountyID(), c3.getCountyID());
        when(rs.getString("countyName")).thenReturn(c1.getCountyName(), c2.getCountyName(), c3.getCountyName());

        
        CountyDAO countyDao = new CountyDAO(sql);
        ArrayList<County> result = countyDao.selectAllCounties();
        // An alternative approach is to use the arraylist of Users we created 
        // as expected results at the start
        // If this equals the arraylist we got back from our method being tested, 
        // then the method worked as expected
        assertEquals(expectedResults, result);

    }

}
