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
    ArrayList<Object> selectAllZoneVips(int zoneId);
    boolean addVip(Vip v);
    boolean removeVip(Vip v);
}
