/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainTests;

import REST.PaymentLogsResource;
import java.util.Scanner;

/**
 *
 * @author SeppQ
 */
public class PaymentLogsTest {
    public static void main(String[] args) {
        PaymentLogsResource ar = new PaymentLogsResource();
        
        Scanner sc = new Scanner(System.in);
        
        String s = sc.next();
        //{"create_time":"2020-05-05T20:38:37Z","id":"10847218H5582313N","intent":"CAPTURE","status":"COMPLETED"}
        System.out.println(ar.InsertLog(s));
    }
}
