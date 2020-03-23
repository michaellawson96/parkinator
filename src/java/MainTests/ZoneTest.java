/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainTests;

import REST.AdminResource;
import REST.ZoneResource;
import java.util.Scanner;

/**
 *
 * @author SeppQ
 */
public class ZoneTest {
    public static void main(String[] args) {
                ZoneResource ar = new ZoneResource();
        Scanner sc = new Scanner(System.in);
        String c = sc.next();
        // System.out.println(ar.DeletesUser(c));
        // {"user_id":16}       
        System.out.println(ar.addZone(c));
        //{"zone_id":-1,"zone_name":"carrols","max_spaces":50,"is_vip":false,"lot_id":4,"max_disabled_spaces":4}
    }
    
}
