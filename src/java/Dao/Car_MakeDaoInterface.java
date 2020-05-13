/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Dto.Car_Make;

/**
 *
 * @author snake
 */
public interface Car_MakeDaoInterface {

    public Object getAllMakes();

    public Object insertMake(Car_Make cm);
}
