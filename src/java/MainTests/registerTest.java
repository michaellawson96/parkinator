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
        
        
        ur.register("{\"user_id\":null,\"user_fullname\":\"lukas krukonis\",\"email\":\"a@gmail.com\",\"hash\":\"Lukiukas1\",\"user_type\":\"regular\",\"question\":\"What is your oldest sibling’s birthday month and year?\",\"answer_hash\":\"asd\",\"has_disabled_badge\":false}");
        //{"user_fullname":"asdasd","email":"asdsad@gmail.com","password":"Lukiukas","user_type":"regular","pass_question":"asdasd","pass_answer":"asdasd"}
    }
    
    
}
