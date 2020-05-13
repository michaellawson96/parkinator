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
        String  expResult = "[County{countyID=1, countyName=Antrim}, County{countyID=2, countyName=Armagh}, County{countyID=3, countyName=Carlow}, County{countyID=4, countyName=Cavan}, County{countyID=5, countyName=Clare}, County{countyID=6, countyName=Cork}, County{countyID=7, countyName=Derry}, County{countyID=8, countyName=Donegal}, County{countyID=9, countyName=Down}, County{countyID=10, countyName=Dublin}, County{countyID=11, countyName=Fermanagh}, County{countyID=12, countyName=Galway}, County{countyID=13, countyName=Kerry}, County{countyID=14, countyName=Kildare}, County{countyID=15, countyName=Kilkenny}, County{countyID=16, countyName=Laois}, County{countyID=17, countyName=Leitrim}, County{countyID=18, countyName=Limerick}, County{countyID=19, countyName=Longford}, County{countyID=20, countyName=Louth}, County{countyID=21, countyName=Mayo}, County{countyID=22, countyName=Meath}, County{countyID=23, countyName=Monaghan}, County{countyID=24, countyName=Offaly}, County{countyID=25, countyName=Roscommon}, County{countyID=26, countyName=Sligo}, County{countyID=27, countyName=Tipperary}, County{countyID=28, countyName=Tyrone}, County{countyID=29, countyName=Waterford}, County{countyID=30, countyName=Westmeath}, County{countyID=31, countyName=Wexford}, County{countyID=32, countyName=Wicklow}]";
        String result = instance.selectAllCounties().toString();
        assertEquals(expResult, result);
    }
    
}
