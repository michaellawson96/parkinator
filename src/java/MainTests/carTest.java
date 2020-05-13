/*
 * Made By
 * Name: Michael Lawson
 * Student Number: D00185184
 */
package MainTests;
import REST.*;
import java.util.Scanner;
/**
 *
 * @author USER
 */
public class carTest {
     public static CarResource cr = new CarResource();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        System.out.println(cr.postText(s));
        //{"car_reg":"asd","alias":"asd","car_id":0,"car_colour":"asd","car_make":"Austin","car_model":"Mini Cooper","user_id":18}
}
}
