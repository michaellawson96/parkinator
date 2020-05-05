/*
 * Made By
 * Name: Michael Lawson
 * Student Number: D00185184
 */
package Dao;

import Dto.User;
import Dto.Vip;
import Dto.Zone;
import java.util.ArrayList;

/**
 *
 * @author USER
 */
public interface VipDAOInterface {
    ArrayList<Object> selectAllVips();
    ArrayList<Object> selectAllZoneVips(int zoneId);
    ArrayList<Object> selectAllUserVips(int userId);
    String addVip(Vip v);
    String removeVip(Vip v);
    
    public boolean vipExists(Vip v);
}
