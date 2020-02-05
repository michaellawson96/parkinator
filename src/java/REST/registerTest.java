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
        
        ur.Register("{\"fullname\":\"Jonas\", \"email\":\"Jonas@gmail.com\", \"password\":\"jonny\", \"user_type\":\"Manager\", \"pass_question\":\"are you really real?\", \"pass_answer\":\"no not really\"}");
        //{"user_fullname":"asdasd","email":"asdsad@gmail.com","password":"Lukiukas","user_type":"regular","pass_question":"asdasd","pass_answer":"asdasd"}
    }
    
    
}
