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
        boolean checkUserExistsByEmail(String email);
        boolean login(String email, String hash); 
        boolean register(String fullname, String email, String hash, String user_Type, 
                String question, String answer_Hash, boolean has_disabled_badge); 
        
        User getUserByEmail(String email);
        String checkUserExistsByEmailRecovery(User user);
        boolean checkUserRecoveryAnswer(User user);
        boolean updateUserPassword(User user);
        boolean adminDeletesUser(User user);
        boolean adminUpdatesUserTypes(User user);
}
