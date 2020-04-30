/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Dto.Support;

/**
 *
 * @author SeppQ
 */
public interface SupportDAOInterface {
    String insertMessage(Support sup);
    Object selectAllMessage();
    String removeMessage(Support sup);
    
}
