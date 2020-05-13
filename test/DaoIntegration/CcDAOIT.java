/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DaoIntegration;

import Dao.CcDAO;
import Dto.Cc;
import Dto.ParkedCars;
import java.sql.Date;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

/**
 *
 * @author SeppQ
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CcDAOIT {
    
    public CcDAOIT() {
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
    public void A_testSelectAllCcs() {
        System.out.println("selectAllCcs");
        CcDAO instance = new CcDAO();
        String expResult = "[Cc{ccName=example2313, ccNo=1}, Cc{ccName=asdasd, ccNo=2}, Cc{ccName=ClampIt, ccNo=3}, Cc{ccName=asdsadad, ccNo=4}, Cc{ccName=asdasd, ccNo=5}]";
        ArrayList<Cc> result = instance.selectAllCcs();
        assertEquals(expResult, result);
    }

    /**
     * Test of insertCc method, of class CcDAO.
     */
    @Test
    public void B_testInsertCc() {
        System.out.println("insertCc");
        String cc_name = "ClampingTest1";
        CcDAO instance = new CcDAO();
        boolean expResult = true;
        boolean result = instance.insertCc(cc_name);
        assertEquals(expResult, result);

    }

    /**
     * Test of updateCc method, of class CcDAO.
     */
    @Test
    public void C_testUpdateCc() {
        System.out.println("updateCc");
        Cc cc = new Cc("Test2",5);
        CcDAO instance = new CcDAO();
        boolean expResult = true;
        boolean result = instance.updateCc(cc);
        assertEquals(expResult, result);
    }

    /**
     * Test of deleteCc method, of class CcDAO.
     */
    @Test
    public void D_testDeleteCc() {
        int id = 5;
        CcDAO instance = new CcDAO();
        boolean expResult = true;
        boolean result = instance.deleteCc(id);
        assertEquals(expResult, result);

    }

    /**
     * Test of getAllregPlatesUnderAZone method, of class CcDAO.
     */
    @Test
    public void E_testGetAllregPlatesUnderAZone() {
        System.out.println("getAllregPlatesUnderAZone");
        ParkedCars pc = new ParkedCars(29,18, new Date(2020 / 05 / 22), new Date(2020 / 05 / 22),18);
        CcDAO instance = new CcDAO();
        String expResult = "[Car{carNo=18, alias=null, carReg=asasdasd, carColour=null, carMake=null, carModel=null, userNo=0}]";
        String result = instance.getAllregPlatesUnderAZone(pc).toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of getAllregPlatesUnderAllZone method, of class CcDAO.
     */
    @Test
    public void F_testGetAllregPlatesUnderAllZone() {
        System.out.println("getAllregPlatesUnderAllZone");
        CcDAO instance = new CcDAO();
        String expResult = "[BookingDetailsCC{car_reg=asasdasd, zone_name=Dundalk - Woodies, parking_name=Woodies, fullname=Peter Pollis}]";
        String result = instance.getAllregPlatesUnderAllZone().toString();
        assertEquals(expResult, result);
    }
    
}
