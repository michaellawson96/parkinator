/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Dto.User;
import Dto.UserImage;
import SaltingString.BCrypt;
import SqlConnection.SqlConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Jonas
 */
public class UserDao implements UserDAOInterface {

    private SqlConnection sql = null;
    private HttpStatusBase hsb = new HttpStatusBase();

    public UserDao(SqlConnection sql) {
        this.sql = sql;
    }

    public UserDao() {
        this.sql = new SqlConnection();
    }

    @Override
    public ArrayList<User> selectAllUsers() {
        try {
            sql.setPs(sql.getConn().prepareStatement("select * from users"));
            ResultSet rst;
            // Execute the query
            rst = sql.getPs().executeQuery();
            ArrayList<User> users = new ArrayList<>();
            while (rst.next()) {
                users.add(new User(rst.getInt("user_id"), rst.getString("user_fullname"), rst.getString("email"),
                        rst.getString("hash"), rst.getString("user_type"), rst.getString("question"),
                        rst.getString("answer_hash"), rst.getBoolean("has_disabled_badge")));
            }
            System.out.println("User has been added.");

            return users;
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
    public boolean login(String email, String hash) {
        try {
            //System.out.println(email);
            if (checkUserExistsByEmail(email)) {
                User u = getUserByEmail(email);
                return BCrypt.checkpw(hash, u.getUserHash());
            } else {
                System.out.println("didn't exist");
                return false;
            }

        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean register(String fullname, String email, String hash, String user_Type,
            String question, String answer_hash, boolean has_disabled_badge) {

        try {

            if (email.contains("@") && email.contains(".") && hash.matches(".*[a-z].*") && hash.length() <= 30 && hash.length() >= 7 && hash.matches(".*\\d.*")) {
                String[] HashedSaltedPw = SaltANDHash(hash);
                String[] HashedSaltedAnswer = SaltANDHash(answer_hash);

                System.out.println("valid email supplied");

                if (!checkUserExistsByEmail(email)) {
                    sql.setPs(sql.getConn().prepareStatement("INSERT INTO users(user_fullname, email, hash, user_type, question, answer_hash, has_disabled_badge) VALUES (?,?,?,?,?,?,?)"));
                    sql.getPs().setString(1, fullname);
                    sql.getPs().setString(2, email);
                    sql.getPs().setString(3, HashedSaltedPw[1]);
                    sql.getPs().setString(4, user_Type);
                    sql.getPs().setString(5, question);
                    sql.getPs().setString(6, HashedSaltedAnswer[1]);
                    sql.getPs().setBoolean(7, has_disabled_badge);

                    sql.getPs().executeUpdate();

                    System.out.println("user recorded");

                    User u = getUserByEmail(email);

                    sql.setPs(sql.getConn().prepareStatement("INSERT INTO salt(user_id, salt, answer_salt) VALUES (?,?,?)"));
                    sql.getPs().setInt(1, u.getUserNo());
                    sql.getPs().setString(2, HashedSaltedPw[0]);
                    sql.getPs().setString(3, HashedSaltedAnswer[0]);

                    sql.getPs().executeUpdate();

                    System.out.println("salt recorded");

                    return true;
                }
            } else {
                return false;
            }

        } catch (SQLException se) {
            System.out.println("SQL Exception occurred: " + se.getMessage());
            se.printStackTrace();
            return false;
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
        return false;
    }

    public static String[] SaltANDHash(String needHashSalt) {
        String[] SaltUsedAndHash = new String[2];

        SaltUsedAndHash[0] = BCrypt.gensalt(12);

        SaltUsedAndHash[1] = BCrypt.hashpw(needHashSalt, SaltUsedAndHash[0]);

        return SaltUsedAndHash;
    }

    @Override
    public boolean checkUserExistsByEmail(String email) {
        try {
            sql.setPs(sql.getConn().prepareStatement("select * from users WHERE email=?"));
            sql.getPs().setString(1, email);

            ResultSet rst;
            rst = sql.getPs().executeQuery();

            if (rst.next()) {
                System.out.println("user exi");
                return true;
            }
            System.out.println("user doesn't exist yet");
            return false;

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
    public boolean updateUser(User user
    ) {
        try {
            if (checkUserExistsByEmail(user.getEmail())) {
                sql.setPs(sql.getConn().prepareStatement("UPDATE users SET user_fullname=?, user_type=?,question=?,answer_hash=?, has_disabled_badge=? WHERE email = ?"));

                sql.getPs().setString(1, user.getUserFullname());

                sql.getPs().setString(2, user.getUserType());

                sql.getPs().setString(3, user.getQuestion());
                sql.getPs().setString(4, user.getAnswer_hash());
                sql.getPs().setBoolean(5, user.getHasDisabledBadge());

                sql.getPs().setString(6, user.getEmail());

                sql.getPs().executeUpdate();
                return true;
            } else {
                return false;
            }

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
    public User getUserByEmail(String email) {
        try {
            sql.setPs(sql.getConn().prepareStatement("select * from users WHERE email = ?"));
            sql.getPs().setString(1, email);
            ResultSet rst;
            // Execute the query
            rst = sql.getPs().executeQuery();
            User user = null;
            if (rst.next()) {
                user = new User(rst.getInt("user_id"), rst.getString("user_fullname"), rst.getString("email"),
                        rst.getString("hash"), rst.getString("user_type"), rst.getString("question"),
                        rst.getString("answer_hash"), rst.getBoolean("has_disabled_badge"));
            }

            return user;
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
    public String checkUserExistsByEmailRecovery(User user) {
        try {
            sql.setPs(sql.getConn().prepareStatement("select * from users where email=?"));
            sql.getPs().setString(1, user.getEmail());

            ResultSet rst;
            rst = sql.getPs().executeQuery();

            if (rst.next()) {

                return "{\"Qeustion\":\" " + rst.getString("question") + "  \"}";
            }
            System.out.println("user doesn't exist yet");
            return "false";

        } catch (SQLException se) {
            System.out.println("SQL Exception occurred: " + se.getMessage());
            se.printStackTrace();
            return "false";
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
            e.printStackTrace();
            return "false";
        }
    }

    @Override
    public boolean checkUserRecoveryAnswer(User user) {
        try {

            sql.setPs(sql.getConn().prepareStatement("select * from users WHERE email = ?"));
            sql.getPs().setString(1, user.getEmail());
            ResultSet rst;
            // Execute the query
            rst = sql.getPs().executeQuery();
            if (rst.next()) {
                return BCrypt.checkpw(user.getAnswer_hash(), rst.getString("answer_hash"));
            }
            return false;

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
    public boolean updateUserPassword(User user) {
        try {

            if (user.getUserHash().matches(".*[a-z].*") && user.getUserHash().length() <= 30 && user.getUserHash().length() >= 7 && user.getUserHash().matches(".*\\d.*")) {

                String[] HashedSaltedPw = SaltANDHash(user.getUserHash());

                sql.setPs(sql.getConn().prepareStatement("UPDATE users SET hash = ? WHERE email = ?"));

                sql.getPs().setString(1, HashedSaltedPw[1]);
                sql.getPs().setString(2, user.getEmail());

                sql.getPs().executeUpdate();

                User u = getUserByEmail(user.getEmail());

                sql.setPs(sql.getConn().prepareStatement("UPDATE salt SET salt = ? WHERE user_id = ?"));

                sql.getPs().setString(1, HashedSaltedPw[0]);
                sql.getPs().setInt(2, u.getUserNo());
                sql.getPs().executeUpdate();

                return true;

            } else {
                return false;
            }

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
    public boolean adminDeletesUser(User user) {
        try {
            if (!checkUserExistsByEmail(user.getEmail())) {
                sql.setPs(sql.getConn().prepareStatement("DELETE FROM salt WHERE user_id = ?"));

                sql.getPs().setInt(1, user.getUserNo());

                sql.getPs().executeUpdate();

                sql.setPs(sql.getConn().prepareStatement("DELETE FROM users WHERE user_id = ?"));

                sql.getPs().setInt(1, user.getUserNo());

                sql.getPs().executeUpdate();

                return true;
            } else {
                return false;
            }

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
    public boolean adminUpdatesUserTypes(User user) {
        try {
            if (checkUserExistsByEmail(user.getEmail())) {
                sql.setPs(sql.getConn().prepareStatement("UPDATE users SET user_type = ? WHERE email = ?"));

                sql.getPs().setString(1, user.getUserType());
                sql.getPs().setString(2, user.getEmail());

                sql.getPs().executeUpdate();

                return true;
            } else {
                return false;
            }

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
    public Object imageUpload(UserImage ui) {

        try {
            if(checkUserExistsByEmailImages(ui.getEmail()) == false){
            sql.setPs(sql.getConn().prepareStatement("INSERT INTO userimages(Image,ImageName) VALUES (?,?)"));
            sql.getPs().setString(1, ui.getImage());
            sql.getPs().setString(2, ui.getEmail());

            sql.getPs().executeUpdate();

            return hsb.createMessage(1, "Image has Been Uploaded");
            }else{
                sql.setPs(sql.getConn().prepareStatement("UPDATE userimages SET Image=? WHERE ImageName = ?"));

                sql.getPs().setString(1, ui.getImage());
                sql.getPs().setString(2, ui.getEmail());

                sql.getPs().executeUpdate();    
                return hsb.createMessage(1, "Image has Been Uploaded");
            }

        } catch (SQLException se) {
            System.out.println("SQL Exception occurred: " + se.getMessage());
            se.printStackTrace();
            return hsb.SQlError();
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
            e.printStackTrace();
            return hsb.exceptionError();
        }
        

    }
    
    @Override
    public Object getImage(UserImage ui) {

        try {

            sql.setPs(sql.getConn().prepareStatement("SELECT Image, ImageName FROM userimages WHERE ImageName=?"));
            sql.getPs().setString(1, ui.getEmail());

            ResultSet rst;
            // Execute the query
            rst = sql.getPs().executeQuery();
            UserImage userImage = null;
            if (rst.next()) {
                userImage = new UserImage( rst.getString("ImageName"),rst.getString("Image"));
            }else{
                return hsb.createMessage(-1, "No Image");
            }

            return userImage;

        } catch (SQLException se) {
            System.out.println("SQL Exception occurred: " + se.getMessage());
            se.printStackTrace();
            return hsb.SQlError();
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
            e.printStackTrace();
            return hsb.exceptionError();
        }

    }    
    
        public boolean checkUserExistsByEmailImages(String email) {
        try {
            sql.setPs(sql.getConn().prepareStatement("select * from userimages WHERE imageName=?"));
            sql.getPs().setString(1, email);

            ResultSet rst;
            rst = sql.getPs().executeQuery();

            if (rst.next()) {
                System.out.println("user exi");
                return true;
            }
            System.out.println("user doesn't exist yet");
            return false;

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
    public Object selectUserById(int userId) {
        try {
            sql.setPs(sql.getConn().prepareStatement("select * from users where user_id = ?"));
            sql.getPs().setInt(1, userId);
            ResultSet rst;
            // Execute the query
            rst = sql.getPs().executeQuery();
            User user = new User();
            while (rst.next()) {
                user = new User(rst.getInt("user_id"), rst.getString("user_fullname"), rst.getString("email"),
                        rst.getString("hash"), rst.getString("user_type"), rst.getString("question"),
                        rst.getString("answer_hash"), rst.getBoolean("has_disabled_badge"));
            }
            System.out.println("User has been added.");

            return user;
        } catch (SQLException se) {
            System.out.println("SQL Exception occurred: " + se.getMessage());
            se.printStackTrace();
            return hsb.SQlError();
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
            e.printStackTrace();
            return hsb.exceptionError();
        }
    }
}
