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
     public static CcResource cr = new CcResource();
    public static void main(String[] args) {
        System.out.println(cr.ccUpdate("{\"cc_id\":6,\"cc_name\":\"Clamputen\"}"));
        //{"user_id":11}
}
}
