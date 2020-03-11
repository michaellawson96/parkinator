/*
 * Made By
 * Name: Michael Lawson
 * Student Number: D00185184
 */
package Dao;

import Dto.Car;
import Dto.User;
import java.util.ArrayList;

/**
 *
 * @author USER
 */
public interface CarDAOInterface {
    
    boolean insertCar(Car car);
    boolean updateCar(Car car);
    boolean deleteCar(Car car);
    ArrayList<Object> selectAllCars();
    /**
     *
     * @param userNo
     * @return
     */
    ArrayList<Object> getAllUserCars(int userNo);
    
}
