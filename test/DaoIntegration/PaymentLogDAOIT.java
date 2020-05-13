/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DaoIntegration;

import Dao.PaymentLogDAO;
import Dto.PaymentLogs;
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
public class PaymentLogDAOIT {
    
    public PaymentLogDAOIT() {
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
    public void testInsertPaymentLogs() {
        System.out.println("insertPaymentLogs");
        PaymentLogs pl = new PaymentLogs("213-123","23asdadasd","Capture","Completed");
        PaymentLogDAO instance = new PaymentLogDAO();
        String expResult = "[{\"status_code\":1,\"message\":\"Payment Log has Been Added\"}]";
        String result = instance.insertPaymentLogs(pl);
        assertEquals(expResult, result);

    }

    /**
     * Test of selectPaymentLogs method, of class PaymentLogDAO.
     */
    @Test
    public void testSelectPaymentLogs() {
        System.out.println("selectPaymentLogs");
        PaymentLogDAO instance = new PaymentLogDAO();
        String expResult = "[PaymentLogs{id=213-123, create_time=23asdadasd, intent=Capture, status=Completed}, PaymentLogs{id=27154722CU290264C, create_time=2020-05-13T19:52:46Z, intent=CAPTURE, status=COMPLETED}, PaymentLogs{id=04H34574JA5006335, create_time=2020-05-11T23:51:14Z, intent=CAPTURE, status=COMPLETED}, PaymentLogs{id=98R28774JK961181F, create_time=2020-05-11T18:35:25Z, intent=CAPTURE, status=COMPLETED}, PaymentLogs{id=6T2143745K199243D, create_time=2020-05-11T18:34:06Z, intent=CAPTURE, status=COMPLETED}, PaymentLogs{id=851164437W508401S, create_time=2020-05-11T18:31:28Z, intent=CAPTURE, status=COMPLETED}, PaymentLogs{id=8D727859VT8630710, create_time=2020-05-10T20:53:59Z, intent=CAPTURE, status=COMPLETED}, PaymentLogs{id=4H467889TF5527237, create_time=2020-05-05T23:21:58Z, intent=CAPTURE, status=COMPLETED}, PaymentLogs{id=06C52471EY300933G, create_time=2020-05-05T20:55:07Z, intent=CAPTURE, status=COMPLETED}, PaymentLogs{id=10847218H5582313N, create_time=2020-05-05T20:38:37Z, intent=CAPTURE, status=COMPLETED}]";
        String result = instance.selectPaymentLogs().toString();
        assertEquals(expResult, result);

    }
    
}
