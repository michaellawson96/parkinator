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

        
    }
}
