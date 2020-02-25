/*
 * Made By
 * Name: Michael Lawson
 * Student Number: D00185184
 */
package MainTests;
import REST.*;
/**
 *
 * @author USER
 */
public class carTest {
     public static CarResource cr = new CarResource();
    public static void main(String[] args) {
        System.out.println(cr.putText("{car_details: \"dcwecwedc\", car_id: 8, car_reg: \"dcwwec\", user_id: 14}"));
        //{"user_id":11}
}
}
