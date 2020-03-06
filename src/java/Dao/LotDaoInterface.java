/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Dto.Lot;
import Dto.ParkedCars;
import Dto.Zone;

/**
 *
 * @author Lukas
 */
public interface LotDaoInterface {
    String AddLot(Lot lot);
    String RemoveLot(Lot lot);
    String Addzone(Zone zone);
    String AddBooking(ParkedCars pc);
    String CheckOutDatedParkings(ParkedCars pc);
}
