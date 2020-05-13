/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DaoIntegration;

import Dao.LotDAO;
import Dto.Lot;
import Dto.ParkedCars;
import Dto.User;
import Dto.Zone;
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
public class LotDAOIT {
    
    public LotDAOIT() {
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
    public void testSelectAllLots() {
        System.out.println("selectAllLots");
        LotDAO instance = new LotDAO();
        String expResult = "[Lot{lot_id=25, Parking_name=DKIT, cc_id=1, County=Louth}, Lot{lot_id=26, Parking_name=DCU, cc_id=1, County=Dublin}, Lot{lot_id=27, Parking_name=Woodies, cc_id=1, County=Louth}, Lot{lot_id=28, Parking_name=Trim - LIDL, cc_id=1, County=Meath}, Lot{lot_id=29, Parking_name=Testing, cc_id=2, County=Meath}]";
        String result = instance.selectAllLots().toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of selectLotsByCounty method, of class LotDAO.
     */
    @Test
    public void testSelectLotsByCounty() {
        System.out.println("selectLotsByCounty");
        Lot l = new Lot(0,"",1,"Meath");
        LotDAO instance = new LotDAO();
        String expResult = "Lot{lot_id=28, Parking_name=Trim - LIDL, cc_id=1, County=Meath}";
        String result = instance.selectLotsByCounty(l).toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of selectAllBookings method, of class LotDAO.
     */
    @Test
    public void testSelectAllBookings() {
        System.out.println("selectAllBookings");
        LotDAO instance = new LotDAO();
        String expResult = "ParkedCars{zone_id=28, car_id=19, book_from=2020-05-13, book_to=2020-05-13, user_id=18}, ParkedCars{zone_id=29, car_id=18, book_from=1969-12-31, book_to=1969-12-31, user_id=18}";
        String result = instance.selectAllBookings().toString();
        assertEquals(expResult, result);

    }

    /**
     * Test of selectAllBookingsByUserId method, of class LotDAO.
     */
    @Test
    public void testSelectAllBookingsByUserId() {
        System.out.println("selectAllBookingsByUserId");
        User u = new User(0,"","","","","","",false);
        LotDAO instance = new LotDAO();
        Object expResult = "[]";
        Object result = instance.selectAllBookingsByUserId(u);
        assertEquals(expResult, result);

    }

    /**
     * Test of selectAllZoneByLotId method, of class LotDAO.
     */
    @Test
    public void testSelectAllZoneByLotId() {
        System.out.println("selectAllZoneByLotId");
        Lot l = new Lot(25,"",2,"");
        LotDAO instance = new LotDAO();
        String expResult = "Zone{zone_id=26, zone_name=Carrols, max_spaces=200, is_vip=false, lot_id=25, max_disabled_spaces=12, lat=53.982577523660815, lng=-6.392288613277246, price=3.5}, Zone{zone_id=28, zone_name=DKIT - Main Building, max_spaces=100, is_vip=false, lot_id=25, max_disabled_spaces=5, lat=53.984529950299944, lng=-6.395013607322517, price=2.12}, Zone{zone_id=30, zone_name=asdasd, max_spaces=200, is_vip=false, lot_id=25, max_disabled_spaces=23, lat=21.12, lng=12.12, price=23.0}";
        String result = instance.selectAllZoneByLotId(l).toString();
        assertEquals(expResult, result);

    }

    /**
     * Test of selectAllZones method, of class LotDAO.
     */
    @Test
    public void testSelectAllZones() {
        System.out.println("selectAllZones");
        LotDAO instance = new LotDAO();
        String expResult = "Zone{zone_id=26, zone_name=Carrols, max_spaces=200, is_vip=false, lot_id=25, max_disabled_spaces=12, lat=53.982577523660815, lng=-6.392288613277246, price=3.5}, Zone{zone_id=27, zone_name=DCU - Car Park 1, max_spaces=600, is_vip=false, lot_id=26, max_disabled_spaces=100, lat=53.38666123797299, lng=-6.257070226733232, price=7.39}, Zone{zone_id=28, zone_name=DKIT - Main Building, max_spaces=100, is_vip=false, lot_id=25, max_disabled_spaces=5, lat=53.984529950299944, lng=-6.395013607322517, price=2.12}, Zone{zone_id=29, zone_name=Dundalk - Woodies, max_spaces=150, is_vip=false, lot_id=27, max_disabled_spaces=20, lat=53.99295870477666, lng=-6.377594987700938, price=2.5}, Zone{zone_id=30, zone_name=asdasd, max_spaces=200, is_vip=false, lot_id=25, max_disabled_spaces=23, lat=21.12, lng=12.12, price=23.0}";
        String result = instance.selectAllZones().toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of selectZoneById method, of class LotDAO.
     */
    @Test
    public void testSelectZoneById() {
        System.out.println("selectZoneById");
        int zoneId = 26;
        LotDAO instance = new LotDAO();
        String expResult = "Zone{zone_id=26, zone_name=Carrols, max_spaces=200, is_vip=false, lot_id=25, max_disabled_spaces=12, lat=53.982577523660815, lng=-6.392288613277246, price=3.5}";
        String result = instance.selectZoneById(zoneId).toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of addLot method, of class LotDAO.
     */
    @Test
    public void testAddLot() {
        System.out.println("addLot");
        Lot lot = new Lot(25,"Testing",2,"Meath");
        LotDAO instance = new LotDAO();
        String expResult = "{\"status_code\":1,\"message\":\"Parking Lot added Successfully\"}";
        String result = instance.addLot(lot);
        assertEquals(expResult, result);
    }

    /**
     * Test of removeLot method, of class LotDAO.
     */
    @Test
    public void testRemoveLot() {
        System.out.println("removeLot");
        Lot lot = new Lot(30,"Testing",2,"Meath");
        LotDAO instance = new LotDAO();
        String expResult = "{\"status_code\":1,\"message\":\"Parking Lot Deleted Successfully\"}";
        String result = instance.removeLot(lot);
        assertEquals(expResult, result);

    }

    /**
     * Test of addzone method, of class LotDAO.
     */
    @Test
    public void testAddzone() {
        System.out.println("addzone");
        Zone zone = new Zone(1,"asdasd",200,false,25,23,21.12,12.12,23.0);
        LotDAO instance = new LotDAO();
        String expResult = "{\"status_code\":1,\"message\":\"Parking Zone added Successfully\"}";
        String result = instance.addzone(zone);
        assertEquals(expResult, result);

    }



    /**
     * Test of addBooking method, of class LotDAO.
     */
    @Test
    public void testAddBooking() {
        System.out.println("addBooking");
        ParkedCars pc = new ParkedCars(29, 18, new java.sql.Date(11 / 11 / 11), new java.sql.Date(11 / 11 / 11), 18);
        LotDAO instance = new LotDAO();
        String expResult = "{\"status_code\":1,\"message\":\"Parking Spot Booked Successfully From : 1970-01-01 To : 1970-01-01\"}";
        String result = instance.addBooking(pc);
        assertEquals(expResult, result);
    }

    /**
     * Test of checkBooking method, of class LotDAO.
     */
    @Test
    public void testCheckBooking() {
        System.out.println("checkBooking");
       ParkedCars pc = new ParkedCars(29, 18, new java.sql.Date(11 / 11 / 11), new java.sql.Date(11 / 11 / 11), 18);
        LotDAO instance = new LotDAO();
        String expResult = "{\"status_code\":1,\"message\":\"You Can Book Your Car Spot\"}";
        String result = instance.checkBooking(pc).toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of CheckIfLotExist method, of class LotDAO.
     */
    @Test
    public void testCheckIfLotExist() {
        System.out.println("CheckIfLotExist");
        Lot lot = new Lot(25,"",2,"");
        LotDAO instance = new LotDAO();
        Object expResult = false;
        Object result = instance.CheckIfLotExist(lot);
        assertEquals(expResult, result);

    }

    /**
     * Test of CheckIfzoneExist method, of class LotDAO.
     *////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Test
    public void testCheckIfzoneExist() {
        System.out.println("CheckIfzoneExist");
         Zone zone = new Zone(1,"asdasd",200,false,25,23,21.12,12.12,23.0);
        LotDAO instance = new LotDAO();
        Object expResult = true;
        Object result = instance.CheckIfzoneExist(zone);
        assertEquals(expResult, result);

    }
    @Test
    public void testRemoveBooking() {
        System.out.println("removeBooking");
        ParkedCars pc = new ParkedCars(29, 18, new java.sql.Date(11 / 11 / 11), new java.sql.Date(11 / 11 / 11), 18);
        LotDAO instance = new LotDAO();
        String expResult = "{\"status_code\":1,\"message\":\"Booking Deleted Successfully\"}";
        String result = instance.removeBooking(pc);
        assertEquals(expResult, result);

    }
    /**
     * Test of CheckIfBookingExistUnderThatZone method, of class LotDAO.
     */
    @Test
    public void testCheckIfBookingExistUnderThatZone() {
        System.out.println("CheckIfBookingExistUnderThatZone");
        ParkedCars pc = new ParkedCars(29, 18, new java.sql.Date(11 / 11 / 11), new java.sql.Date(11 / 11 / 11), 18);
        LotDAO instance = new LotDAO();
        Object expResult = true;
        Object result = instance.CheckIfBookingExistUnderThatZone(pc);
        assertEquals(expResult, result);

    }

    /**
     * Test of getBookingDate method, of class LotDAO.
     */
    @Test
    public void testGetBookingDate() {
        System.out.println("getBookingDate");
        ParkedCars pc = new ParkedCars(29, 18, new java.sql.Date(11 / 11 / 11), new java.sql.Date(11 / 11 / 11), 18);
        LotDAO instance = new LotDAO();
        Object expResult = false;
        Object result = instance.getBookingDate(pc);
        assertEquals(expResult, result);

    }

    /**
     * Test of removeBooking method, of class LotDAO.
     */


    /**
     * Test of updateBooking method, of class LotDAO.
     */
    @Test
    public void testUpdateBooking() {
        System.out.println("updateBooking");
        ParkedCars pc = new ParkedCars(29, 18, new java.sql.Date(11 / 11 / 11), new java.sql.Date(11 / 11 / 11), 18);
        LotDAO instance = new LotDAO();
        String expResult = "{\"status_code\":1,\"message\":\"Booking Updated Successfully\"}";
        String result = instance.updateBooking(pc);
        assertEquals(expResult, result);

    }

    /**
     * Test of removeZone method, of class LotDAO.
     */
    @Test
    public void testRemoveZone() {
        System.out.println("removeZone");
        Zone zone = null;
        LotDAO instance = new LotDAO();
        String expResult = "{\"status_code\":[408,\"message\":\"Problem With the Server Side]\"}";
        String result = instance.removeZone(zone);
        assertEquals(expResult, result);
    }

    /**
     * Test of updateZone method, of class LotDAO.
     */
    @Test
    public void testUpdateZone() {
        System.out.println("updateZone");
       Zone zone = new Zone(1,"asdasd",200,false,25,23,21.12,12.12,23.0);
        LotDAO instance = new LotDAO();
        String expResult = "{\"status_code\":1,\"message\":\"Zone Updated Successfully\"}";
        String result = instance.updateZone(zone);
        assertEquals(expResult, result);
    }
    
}
