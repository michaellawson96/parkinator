/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Dto.Lot;
import Dto.ParkedCars;
import Dto.User;
import Dto.Zone;

/**
 *
 * @author Lukas
 */
public interface LotDaoInterface {

    String addLot(Lot lot);

    String removeLot(Lot lot);

    String addzone(Zone zone);

    String addBooking(ParkedCars pc);

    Object selectAllLots();
    
    Object selectLotsByUserId(int userId);

    Object selectAllZones();
    
    Object selectZoneById(int zoneId);

    Object selectAllBookings();

    Object selectAllBookingsByUserId(User u);

    Object selectAllZoneByLotId(Lot l);
    
    String updateBooking(ParkedCars pc);
    
    String removeBooking(ParkedCars pc);
    
    String removeZone(Zone zone);
    
    String updateZone(Zone zone);
    
    Object selectLotsByCounty(Lot l);
    
    Object checkBooking(ParkedCars pc);
}
