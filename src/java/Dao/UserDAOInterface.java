/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Dto.User;
import java.util.ArrayList;

/**
 *
 * @author Jonas
 */
public interface UserDAOInterface {
    int insertUser(User user);
        boolean updateUser(User user);
        ArrayList<User> selectAllUsers();
        boolean Login(String email, String password);
        boolean Register(String fullname, String email, String password, String user_Type, 
                String pass_question, String pass_Answer);       
        
}
