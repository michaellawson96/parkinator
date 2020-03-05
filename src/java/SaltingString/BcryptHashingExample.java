package SaltingString;

import java.security.NoSuchAlgorithmException;

public class BcryptHashingExample {

    public static void main(String[] args) throws NoSuchAlgorithmException {
        String originalPassword = "User1 Testing";
        System.out.println(originalPassword);

        String salt = "$2a$12$Fodl2oDf233P40qSfkbVLO";

                //BCrypt.gensalt(12);

        String generatedSecuredPasswordHash = "$2a$12$8XW5CMg.1ssMt9dvm5yMdeGjCTP51HfwFB8O5WDtNeFnNyxJmSBY6"; //BCrypt.hashpw(originalPassword, salt);
                //"$2a$12$NRQPtYkgEC71y0YpIT.EyO9AAfz9ISs.grgTxYZRTNaxUTZIDaAZC";
               // $2a$12$NRQPtYkgEC71y0YpIT.EyOmS2AhA6BW9hwnkyhNz2v0bKPqFpk6By
                

        //BCrypt.hashpw(originalPassword, salt);
        System.out.println(generatedSecuredPasswordHash);

        boolean matched = BCrypt.checkpw(originalPassword, generatedSecuredPasswordHash);
        System.out.println(matched);

        System.out.println(generatedSecuredPasswordHash.length());
        //System.out.println(salt.length());

        
    }
}
