/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import static Dao.UserDao.SaltANDHash;
import Dto.Lot;
import Dto.User;
import SqlConnection.SqlConnection;
import java.sql.SQLException;

/**
 *
 * @author Lukas
 */
public class LotDAO {

    private SqlConnection sql = new SqlConnection();
    private HttpStatusBase hsb = new HttpStatusBase();

    public String AddLot(Lot lot) {
        try {

            sql.setPs(sql.getConn().prepareStatement("INSERT INTO parking_lots(Parking_name,cc_id) VALUES (?,?)"));
            sql.getPs().setString(1, lot.getParking_name());
            sql.getPs().setInt(2, lot.getCc_id());

            sql.getPs().executeUpdate();

            return hsb.CreateMessage(1, "Parking lot added Successfully");

        } catch (SQLException se) {
            System.out.println("SQL Exception occurred: " + se.getMessage());
            se.printStackTrace();
            return hsb.SQlError();
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
            e.printStackTrace();
            return hsb.ExceptionError();
        }
    }
}
