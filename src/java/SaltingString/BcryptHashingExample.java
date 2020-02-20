package SaltingString;

import java.security.NoSuchAlgorithmException;

public class BcryptHashingExample {

    public static void main(String[] args) throws NoSuchAlgorithmException {
        String originalPassword = "mc mahon";
        System.out.println(originalPassword);

        //String salt = "$2a$12$a89cCNvuOWo14P0YO2tymO";

                //BCrypt.gensalt(12);

        String generatedSecuredPasswordHash = "$2a$12$a89cCNvuOWo14P0YO2tymONK0Ma/z3Ht/x1FUZBqMw14Nqaxlmkcu";
                
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
