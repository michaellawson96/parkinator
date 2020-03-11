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
public class CcTest {
    static CcResource ccr = new CcResource();
    public static void main(String[] args) {
        
        System.out.println(ccr.ccUpdate("{\"cc_id\":6,\"cc_name\":\"Clamputen\"}"));
    }
}
