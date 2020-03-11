package Dao;

import Dto.Cc;
import java.util.ArrayList;

/*
 * Made By
 * Name: Michael Lawson
 * Student Number: D00185184
 */

/**
 *
 * @author USER
 */
public interface CcDAOInterface {
    boolean updateCc(Cc cc);
        ArrayList<Cc> selectAllCcs();
        boolean deleteCc(int uid);
        boolean insertCc(String cc_name); 
}
