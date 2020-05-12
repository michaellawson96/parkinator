/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Dto.County;
import java.util.ArrayList;

/**
 *
 * @author SeppQ
 */
public interface CountyDAOInterface {
    ArrayList<County> selectAllCounties() ;
}
