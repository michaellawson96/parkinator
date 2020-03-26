/*
 * Made By
 * Name: Michael Lawson
 * Student Number: D00185184
 */
package MainTests;
import REST.LoginResourse;
/**
 *
 * @author USER
 */
public class loginTest {
    public static LoginResourse lr = new LoginResourse();
    public static void main(String[] args) {
        System.out.println(lr.login("{\"email\":\"michael2@gmail.com\",\"hash\":\"Michael1\"}"));
        //{"email":"michael@gmail.com","hash":"Michael1"}
    }
}
