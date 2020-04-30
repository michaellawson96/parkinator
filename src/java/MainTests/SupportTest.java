/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainTests;

import REST.SupportResource;
import java.util.Scanner;

/**
 *
 * @author SeppQ
 */
public class SupportTest {

    public static void main(String[] args) {
        SupportResource sr = new SupportResource();
        Scanner sc = new Scanner(System.in);
        String c = sc.next();
        // System.out.println(ar.DeletesUser(c));
        // {"user_id":16}       
       System.out.println(sr.InsertMessage(c));
        //{"message_id":1"title":"help","message":"a","date_sent":"2018-12-2","user_id":18}        
       // System.out.println(sr.getMessages());
       // System.out.println(sr.removeMessage(c));
    }
}
