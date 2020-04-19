/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainTests;

import REST.UserResource;
import java.util.Scanner;

/**
 *
 * @author SeppQ
 */
public class ImageTest {
    static UserResource ur = new UserResource();
    public static void main(String[] args) {
        
      Scanner sc = new Scanner(System.in);
      String test = sc.next();
        System.out.println(ur.getImage(test)); //{"imageName":"asd@gmail.com","image":"a"}
        
    }
}
