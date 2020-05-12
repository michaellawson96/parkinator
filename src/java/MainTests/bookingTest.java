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
        //System.out.println(ar.addBooking(c));
        //{"zone_id":1,"car_id":3,"book_from":"2021-03-11","book_to":"2020-03-11","user_id":17}; 
        //System.out.println(ar.getBookings());
         System.out.println(ar.checkBooking(c));
        
        //{"zone_id":29,"car_id":4,"book_from":"2020-05-22","book_to":"2020-05-22","user_id":17}
    }
}
