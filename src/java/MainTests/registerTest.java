/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainTests;


/**
 *
 * @author snake
 */
import REST.UserResource;
import REST.UserResource;
public class registerTest {
    public static void main(String[] args) {
        UserResource ur = new UserResource();
        
        
        ur.register("{\"user_type\":\"user\",\"question\":\"what is your mother's maiden name\",\"user_id\":4,\"answer_hash\":\"Mc Mahon\",\"user_fullname\":\"Michael Lawson\",\"email\":\"michael5@gmail.com\",\"hash\":\"Michael1\",\"has_disabled_badge\":false}");
        //{"user_fullname":"asdasd","email":"asdsad@gmail.com","password":"Lukiukas","user_type":"regular","pass_question":"asdasd","pass_answer":"asdasd"}
    }
    
    
}
