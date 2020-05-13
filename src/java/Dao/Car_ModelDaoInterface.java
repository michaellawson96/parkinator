/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Dto.Car_Make;
import Dto.Car_Model;

/**
 *
 * @author snake
 */
public interface Car_ModelDaoInterface {
    public Object getAllModels();

    public Object insertModel(Car_Model cm);
    
    public Object getAllModelsByCarMakeId(int cm);
}
