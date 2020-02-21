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
     public static UserCarResource ucr = new UserCarResource();
    public static void main(String[] args) {
        System.out.println(ucr.getUserCars("{\"user_id\":11}"));
        //{"user_id":11}
}
}
