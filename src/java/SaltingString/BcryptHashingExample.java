package SaltingString;

import java.security.NoSuchAlgorithmException;

public class BcryptHashingExample {

    public static void main(String[] args) throws NoSuchAlgorithmException {
        String originalPassword = "asd";
        System.out.println(originalPassword);

        String salt = "$2a$12$NRQPtYkgEC71y0YpIT.EyO";

                //BCrypt.gensalt(12);

        String generatedSecuredPasswordHash = BCrypt.hashpw(originalPassword, salt);
                //"$2a$12$NRQPtYkgEC71y0YpIT.EyO9AAfz9ISs.grgTxYZRTNaxUTZIDaAZC";
               // $2a$12$NRQPtYkgEC71y0YpIT.EyOmS2AhA6BW9hwnkyhNz2v0bKPqFpk6By
                //BCrypt.hashpw(originalPassword, salt);
        System.out.println(generatedSecuredPasswordHash);

        boolean matched = BCrypt.checkpw(originalPassword, generatedSecuredPasswordHash);
        System.out.println(matched);

        System.out.println(generatedSecuredPasswordHash.length());
        //System.out.println(salt.length());

        System.out.println("/////////////////////////////////////////////////");

        String s = "Jonas5";    //you can have any String here

        if (s.matches(".*\\d.*")) {
            if (s.matches(".*[A-Z].*")) {

                System.out.println("The String contains Uppercase letter(s) and number");
            } else {
                System.out.println("does not contain Uppercase letter(s)");
            }
        } else {
            System.out.println("does not contain number");
        }
    }
}
