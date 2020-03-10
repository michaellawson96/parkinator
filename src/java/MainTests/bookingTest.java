/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainTests;

import REST.BookingsResource;
import REST.ZoneResource;
import java.util.Scanner;

/**
 *
 * @author SeppQ
 */
public class bookingTest {

    public static void main(String[] args) {
        BookingsResource ar = new BookingsResource();
        Scanner sc = new Scanner(System.in);
        String c = sc.next();
         System.out.println(ar.addBooking(c));
        // {"zone_id":2,"car_id":19,"bookFrom":"12/03/1999","bookTo":"12/03/2000"}      
    }
}
