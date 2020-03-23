/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainTests;

import REST.AdminResource;
import REST.LotsResource;
import java.util.Scanner;

/**
 *
 * @author SeppQ
 */
public class LotTest {

    public static void main(String[] args) {
        LotsResource ar = new LotsResource();
        Scanner sc = new Scanner(System.in);
        String c = sc.next();
        System.out.println(ar.AddingParkingLot(c));
        //{"lot_id":1,"parking_name":"testing123","cc_id":1}

       // System.out.println(ar.RemoveParkingLot(c));
        //{"lot_id":5}
    }

}
