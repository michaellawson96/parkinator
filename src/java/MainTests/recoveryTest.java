/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainTests;

import REST.RecoveryPasswordResource;
import REST.RecoveryResource;
import REST.UserDetailsResource;
import REST.UserResource;
import java.util.Scanner;

/**
 *
 * @author SeppQ
 */
public class recoveryTest {
    public static void main(String[] args) {
        
    
        UserResource ur = new UserResource();
        UserDetailsResource r = new UserDetailsResource();
        Scanner sc = new Scanner(System.in);
        RecoveryResource rr = new RecoveryResource();
        RecoveryPasswordResource rpr = new RecoveryPasswordResource();
       String c = sc.next();
//        System.out.println(rr.checkEmail(c));   
        System.out.println(rpr.checkQuestionAnswer(c));
       // {"email":"lukas@gmail.com","answer_hash":"asd"}
    }
}
