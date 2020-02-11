/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package REST;


/**
 *
 * @author snake
 */
import REST.UserResource;
public class registerTest {
    public static void main(String[] args) {
        UserResource ur = new UserResource();
        
        ur.update("{\"pass_question\":\"what is your mother's maiden name\", \"password\":\"michael1\", \"user_type\":\"user\", \"user_fullname\":\"Michael Lawson\",\"pass_answer\":\"mc mahon\", \"email\":\"michael@gmail.com\", \"has_disabled_badge\":false, \"user_id\":2}");
        //{"user_fullname":"asdasd","email":"asdsad@gmail.com","password":"Lukiukas","user_type":"regular","pass_question":"asdasd","pass_answer":"asdasd"}
    }
    
    
}
