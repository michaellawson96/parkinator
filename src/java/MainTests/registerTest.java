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
        
        
        ur.userCreate("{\"user_id\":null,\"user_fullname\":\"Mark Manson\",\"email\":\"mark1@gmail.com\",\"hash\":\"Mark123\",\"user_type\":\"regular\",\"question\":\"What is your mothers maiden name?\",\"answer_hash\":\"asd\",\"has_disabled_badge\":true}");
        //{"user_fullname":"asdasd","email":"asdsad@gmail.com","password":"Lukiukas","user_type":"regular","pass_question":"asdasd","pass_answer":"asdasd"}
    }
    
    
}
