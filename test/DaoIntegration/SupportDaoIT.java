/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DaoIntegration;

import Dao.SupportDao;
import Dto.Support;
import java.util.Date;
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
public class SupportDaoIT {
    
    public SupportDaoIT() {
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
    public void testInsertMessage() {
        System.out.println("insertMessage");
        Support sup = new Support(8,"Need Help " , "Greatly hearted has who believe. Drift allow green... " ,new java.sql.Date(2020 / 05 / 05) , 18 , "In Progres");
        SupportDao instance = new SupportDao();
        String expResult = "[{\"status_code\":1,\"message\":\"Your post has been Sent\"}]";
        String result = instance.insertMessage(sup);
        assertEquals(expResult, result);

    }

    /**
     * Test of selectAllMessage method, of class SupportDao.
     */
    @Test
    public void testSelectAllMessage() {
        System.out.println("selectAllMessage");
        SupportDao instance = new SupportDao();
        String expResult = "[Support{message_id=9, title=help, message=asdasda, date=2020-05-04, user_id=18, status=Done}, Support{message_id=10, title=help, message=asdsadasdasd, date=2020-05-04, user_id=18, status=Done}, Support{message_id=11, title=Need Help , message=Greatly hearted has who believe. Drift allow green... , date=1969-12-31, user_id=18, status=In Progres}]";
        String result = instance.selectAllMessage().toString();
        assertEquals(expResult, result);

    }

    /**
     * Test of selectAllMessageByUserId method, of class SupportDao.
     */
    @Test
    public void testSelectAllMessageByUserId() {
        System.out.println("selectAllMessageByUserId");
        Support support = new Support(8,"" , " " ,new java.sql.Date(2020 / 05 / 05) , 18 , "");
        SupportDao instance = new SupportDao();
        String expResult = "Support{message_id=9, title=help, message=asdasda, date=2020-05-04, user_id=18, status=Done}, Support{message_id=10, title=help, message=asdsadasdasd, date=2020-05-04, user_id=18, status=Done}, Support{message_id=11, title=Need Help , message=Greatly hearted has who believe. Drift allow green... , date=1969-12-31, user_id=18, status=In Progres}";
        String result = instance.selectAllMessageByUserId(support).toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of statusUpdate method, of class SupportDao.
     */
    @Test
    public void testStatusUpdate() {
        System.out.println("statusUpdate");
        Support sup = new Support(8,"Need Help " , "Greatly hearted has who believe. Drift allow green... " ,new java.sql.Date(2020 / 05 / 05) , 18 , "Done");
        SupportDao instance = new SupportDao();
        String expResult = "{\"status_code\":1,\"message\":\"Status has been changed toGreatly hearted has who believe. Drift allow green... \"}";
        String result = instance.statusUpdate(sup).toString();
        assertEquals(expResult, result);

    }

    /**
     * Test of removeMessage method, of class SupportDao.
     */
    @Test
    public void testRemoveMessage() {
        System.out.println("removeMessage");
        Support sup = new Support(8,"Need Help " , "Greatly hearted has who believe. Drift allow green... " ,new java.sql.Date(2020 / 05 / 05) , 18 , "Done");
        SupportDao instance = new SupportDao();
        String expResult = "{\"status_code\":1,\"message\":\"Message Removed\"}";
        String result = instance.removeMessage(sup);
        assertEquals(expResult, result);

    }
    
}
