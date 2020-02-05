/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import REST.UserDetailsResource;
import REST.UserResource;
import java.util.Scanner;

/**
 *
 * @author Jonas
 */
public class LoginTest {
    public static void main(String[] args) {
        UserResource ur = new UserResource();
        UserDetailsResource r = new UserDetailsResource();
        Scanner sc = new Scanner(System.in);
        
        String c = sc.next();
        System.out.println(r.postText(c));
        
        //{"email":"asd"}
    }
}
