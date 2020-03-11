/*
 * Made By
 * Name: Michael Lawson
 * Student Number: D00185184
 */
package Dao;

import Dto.Cc;
import SqlConnection.SqlConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author USER
 */
public class CcDAO implements CcDAOInterface {

    private SqlConnection sql = null;

    public CcDAO(SqlConnection sql) {
        this.sql = sql;
    }

    public CcDAO() {
        this.sql = new SqlConnection();
    }

    @Override
    public ArrayList<Cc> selectAllCcs() {
        try {
            sql.setPs(sql.getConn().prepareStatement("select * from clamping_companies"));
            ResultSet rst;
            // Execute the query
            rst = sql.getPs().executeQuery();
            ArrayList<Cc> ccs = new ArrayList<>();
            while (rst.next()) {
                ccs.add(new Cc(rst.getString("cc_name"), rst.getInt("cc_id")));
            }
            System.out.println("Cc has been added.");

            return ccs;
        } catch (SQLException se) {
            System.out.println("SQL Exception occurred: " + se.getMessage());
            se.printStackTrace();
            return null;
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean insertCc(String cc_name) {

        try {

            sql.setPs(sql.getConn().prepareStatement("INSERT INTO clamping_companies(cc_name) VALUES (?)"));
            sql.getPs().setString(1, cc_name);

            sql.getPs().executeUpdate();

            System.out.println("cc recorded");

            return true;

        } catch (SQLException se) {
            System.out.println("SQL Exception occurred: " + se.getMessage());
            se.printStackTrace();
            return false;
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateCc(Cc cc) {
        try {
            sql.setPs(sql.getConn().prepareStatement("UPDATE clamping_companies SET cc_name=? WHERE cc_id = ?"));

            sql.getPs().setString(1, cc.getCcName());
            sql.getPs().setInt(2, cc.getCcNo());

            sql.getPs().executeUpdate();
            return true;

        } catch (SQLException se) {
            System.out.println("SQL Exception occurred: " + se.getMessage());
            se.printStackTrace();
            return false;
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteCc(int id) {
        try {
            sql.setPs(sql.getConn().prepareStatement("DELETE from clamping_companies WHERE cc_id = ?"));

            sql.getPs().setInt(1, id);

            sql.getPs().executeUpdate();
            return true;
        } catch (SQLException se) {
            System.out.println("SQL Exception occurred: " + se.getMessage());
            se.printStackTrace();
            return false;
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

}
