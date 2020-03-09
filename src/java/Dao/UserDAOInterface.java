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
        boolean updateUser(User user);
        ArrayList<User> selectAllUsers();
        boolean deleteUser(int uid);
        boolean CheckUserExistsByEmail(String email);
        boolean Login(String email, String hash); 
        boolean register(String fullname, String email, String hash, String user_Type, 
                String question, String answer_Hash, boolean has_disabled_badge); 
        
        User getUserByEmail(String email);
        String CheckUserExistsByEmailRecovery(User user);
        boolean CheckUserRecoveryAnswer(User user);
        boolean updateUserPassword(User user);
        boolean AdminDeletesUser(User user);
        boolean AdminUpdatesUserTypes(User user);
}
