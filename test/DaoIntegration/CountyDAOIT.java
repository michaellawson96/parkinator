/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DaoIntegration;

import Dao.CountyDAO;
import Dto.County;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author SeppQ
 */
public class CountyDAOIT {
    
    public CountyDAOIT() {
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
    public void testSelectAllCounties() {
        System.out.println("selectAllCounties");
        CountyDAO instance = new CountyDAO();
        ArrayList<County> expResult = null;
        ArrayList<County> result = instance.selectAllCounties();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
