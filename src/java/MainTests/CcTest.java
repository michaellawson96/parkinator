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
public class CcTest {
    static CcResource ccr = new CcResource();
    public static void main(String[] args) {
        
      //  System.out.println(ccr.ccUpdate("{\"cc_id\":6,\"cc_name\":\"Clamputen\"}"));
      Scanner sc = new Scanner(System.in);
   //   String test = sc.next();
        System.out.println(ccr.getCarRegUnderAllZone()); //{"zone_id":23}
        
    }
}
