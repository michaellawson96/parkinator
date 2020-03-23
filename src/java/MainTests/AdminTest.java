/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainTests;

import REST.AdminResource;
import REST.RecoveryPasswordResource;
import REST.RecoveryResource;
import REST.UserDetailsResource;
import REST.UserResource;
import java.util.Scanner;

/**
 *
 * @author SeppQ
 */
public class AdminTest {

    public static void main(String[] args) {

        AdminResource ar = new AdminResource();
        Scanner sc = new Scanner(System.in);
        String c = sc.next();
        // System.out.println(ar.deleteUser(c));
        // {"user_id":16}       
        System.out.println(ar.updateUserType(c));
        //{"email":"a@gmail.com","user_type":"admin"}
    }
}
