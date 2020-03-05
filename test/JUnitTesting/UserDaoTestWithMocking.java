/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JUnitTesting;

import Dao.UserDao;
import Dto.User;
import SqlConnection.SqlConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import static org.junit.Assert.*;
import org.junit.Test;
import static org.mockito.Mockito.*;

/**
 *
 * @author snake
 */
public class UserDaoTestWithMocking {

    public UserDaoTestWithMocking() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of selectAllUsers method, of class UserDao.
     */
    @Ignore
    public void testSelectAllUsers() throws SQLException {
        User u1 = new User(1, "Testing User1", "testinguser1@gmail.com", "$2a$12$Fodl2oDf233P40qSfkbVLOmX8R9a6kzuugosLS685hiVZr1qp7KWS", "user", "what is your mother's maiden name", "$2a$12$8XW5CMg.1ssMt9dvm5yMdeGjCTP51HfwFB8O5WDtNeFnNyxJmSBY6", false);
        User u2 = new User(2, "Testing User2", "testinguser2@gmail.com", "$2a$12$jgxPw.sQUTLOG2Yb1xCeFOVHgD5bbH8bkvzNufPIJ9xRnKOghpw9W", "user", "what is your mother's maiden name", "$2a$12$nXti9bKgnGXGHg5.TGTbEOUmYH2lqdduy0RvMIorAgihWVpaEwKKC", true);
        User u3 = new User(3, "Testing User3", "testinguser3@gmail.com", "$2a$12$b3uCgzPSHx94wQunCwzPiOMyCbgGp1qE6UEhUgtqNMXZL28tk.zOq", "user", "what is your mother's maiden name", "$2a$12$omWIaLeVXOwpGFveppMrluN9EsvXR1ufV4YheHYWXtftgn0HZ67oG", false);

        ArrayList<User> expectedResults = new ArrayList();

        expectedResults.add(u1);
        expectedResults.add(u2);
        expectedResults.add(u3);

        // Create mock objects
        SqlConnection sql = mock(SqlConnection.class);
        Connection conn = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);

        // Fill mock objects with appropriatel dummy data
        when(sql.getConn()).thenReturn(conn);
        when(conn.prepareStatement("select * from users")).thenReturn(ps);
        when(sql.getPs()).thenReturn(ps);
        when(ps.executeQuery()).thenReturn(rs);

        // Want 3 results in the resultset, so need true to be returned 3 times
        when(rs.next()).thenReturn(true, true, true, false);

        // Fill in the resultset
        when(rs.getInt("user_id")).thenReturn(u1.getUserNo(), u2.getUserNo(), u3.getUserNo());
        when(rs.getString("user_fullname")).thenReturn(u1.getUserFullname(), u2.getUserFullname(), u3.getUserFullname());
        when(rs.getString("email")).thenReturn(u1.getEmail(), u2.getEmail(), u3.getEmail());
        when(rs.getString("hash")).thenReturn(u1.getUserHash(), u2.getUserHash(), u3.getUserHash());
        when(rs.getString("user_type")).thenReturn(u1.getUserType(), u2.getUserType(), u3.getUserType());
        when(rs.getString("question")).thenReturn(u1.getQuestion(), u2.getQuestion(), u3.getQuestion());
        when(rs.getString("answer_hash")).thenReturn(u1.getAnswer_hash(), u2.getAnswer_hash(), u3.getAnswer_hash());
        when(rs.getBoolean("has_disabled_badge")).thenReturn(u1.getHasDisabledBadge(), u2.getHasDisabledBadge(), u3.getHasDisabledBadge());

        int numUsersInTable = 3;
        UserDao userDao = new UserDao(sql);
        ArrayList<User> result = userDao.selectAllUsers();

        // Check that the number of entries retrieved matches the (known) number 
        // of entries in the supplied dummy data
        assertEquals(numUsersInTable, result.size());

        // An alternative approach is to use the arraylist of Users we created 
        // as expected results at the start
        // If this equals the arraylist we got back from our method being tested, 
        // then the method worked as expected
        assertEquals(expectedResults, result);

        System.out.println(result);
    }

    /**
     * Ignore of Login method, of class UserDao.
     */
    @Ignore
    public void testLogin() throws SQLException {
        User u1 = new User(1, "Testing User1", "testinguser1@gmail.com", "$2a$12$Fodl2oDf233P40qSfkbVLOmX8R9a6kzuugosLS685hiVZr1qp7KWS", "user", "what is your mother's maiden name", "$2a$12$8XW5CMg.1ssMt9dvm5yMdeGjCTP51HfwFB8O5WDtNeFnNyxJmSBY6", false);
        //User u2 = new User(2, "Testing User2", "testinguser2@gmail.com", "$2a$12$jgxPw.sQUTLOG2Yb1xCeFOVHgD5bbH8bkvzNufPIJ9xRnKOghpw9W", "user", "what is your mother's maiden name", "$2a$12$nXti9bKgnGXGHg5.TGTbEOUmYH2lqdduy0RvMIorAgihWVpaEwKKC", true);
        //User u3 = new User(3, "Testing User3", "testinguser3@gmail.com", "$2a$12$b3uCgzPSHx94wQunCwzPiOMyCbgGp1qE6UEhUgtqNMXZL28tk.zOq", "user", "what is your mother's maiden name", "$2a$12$omWIaLeVXOwpGFveppMrluN9EsvXR1ufV4YheHYWXtftgn0HZ67oG", false);
        String pw = "Testinguser1";

        boolean expResult = true;
        User expResult2 = u1;

        // Create mock objects
        SqlConnection sql = mock(SqlConnection.class);
        Connection conn = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);

        System.out.println("checking if the user exists");
        //---------------------------------------------------------------------
        when(sql.getConn()).thenReturn(conn);
        when(conn.prepareStatement("select * from users WHERE email = ?")).thenReturn(ps);
        when(sql.getPs()).thenReturn(ps, ps);
        when(ps.executeQuery()).thenReturn(rs);

        // Want 3 results in the resultset, so need true to be returned 3 times
        when(rs.next()).thenReturn(true, false);

        // Fill in the resultset
        when(rs.getInt("user_id")).thenReturn(u1.getUserNo());
        when(rs.getString("user_fullname")).thenReturn(u1.getUserFullname());
        when(rs.getString("email")).thenReturn(u1.getEmail());
        when(rs.getString("hash")).thenReturn(u1.getUserHash());
        when(rs.getString("user_type")).thenReturn(u1.getUserType());
        when(rs.getString("question")).thenReturn(u1.getQuestion());
        when(rs.getString("answer_hash")).thenReturn(u1.getAnswer_hash());
        when(rs.getBoolean("has_disabled_badge")).thenReturn(u1.getHasDisabledBadge());

        UserDao userDao = new UserDao(sql);
        boolean result1 = userDao.CheckUserExistsByEmail(u1.getEmail());
        System.out.println(result1);
        assertEquals(expResult, result1);
        //---------------------------------------------------------------------

        System.out.println("Checking if user was got");
        //---------------------------------------------------------------------

        when(sql.getConn()).thenReturn(conn);
        when(conn.prepareStatement("select * from users WHERE email = ?")).thenReturn(ps);
        when(sql.getPs()).thenReturn(ps, ps);
        when(ps.executeQuery()).thenReturn(rs);

        // Want 3 results in the resultset, so need true to be returned 3 times
        when(rs.next()).thenReturn(true, false);

        // Fill in the resultset
        when(rs.getInt("user_id")).thenReturn(u1.getUserNo());
        when(rs.getString("user_fullname")).thenReturn(u1.getUserFullname());
        when(rs.getString("email")).thenReturn(u1.getEmail());
        when(rs.getString("hash")).thenReturn(u1.getUserHash());
        when(rs.getString("user_type")).thenReturn(u1.getUserType());
        when(rs.getString("question")).thenReturn(u1.getQuestion());
        when(rs.getString("answer_hash")).thenReturn(u1.getAnswer_hash());
        when(rs.getBoolean("has_disabled_badge")).thenReturn(u1.getHasDisabledBadge());

        User result2 = userDao.getUserByEmail(u1.getEmail());
        System.out.println(result2.getUserFullname());
        System.out.println(u1.getUserFullname());
        assertEquals(expResult2, result2);
        //---------------------------------------------------------------------

        boolean result3 = userDao.Login(u1.getEmail(), pw);
        System.out.println(result3);
        assertEquals(expResult, result3);
    }

    /**
     * Test of register method, of class UserDao.
     */
    @Ignore
    public void testRegister() throws SQLException {
        User u1 = new User(1, "Testing User1", "testinguser1@gmail.com", "$2a$12$Fodl2oDf233P40qSfkbVLOmX8R9a6kzuugosLS685hiVZr1qp7KWS", "user", "what is your mother's maiden name", "$2a$12$8XW5CMg.1ssMt9dvm5yMdeGjCTP51HfwFB8O5WDtNeFnNyxJmSBY6", false);
        //User u2 = new User(2, "Testing User2", "testinguser2@gmail.com", "$2a$12$jgxPw.sQUTLOG2Yb1xCeFOVHgD5bbH8bkvzNufPIJ9xRnKOghpw9W", "user", "what is your mother's maiden name", "$2a$12$nXti9bKgnGXGHg5.TGTbEOUmYH2lqdduy0RvMIorAgihWVpaEwKKC", true);
        //User u3 = new User(3, "Testing User3", "testinguser3@gmail.com", "$2a$12$b3uCgzPSHx94wQunCwzPiOMyCbgGp1qE6UEhUgtqNMXZL28tk.zOq", "user", "what is your mother's maiden name", "$2a$12$omWIaLeVXOwpGFveppMrluN9EsvXR1ufV4YheHYWXtftgn0HZ67oG", false);
        User u4 = new User(4, "Testing User4", "testinguser4@gmail.com", "Testinguser4", "user", "what is your mother's maiden name", "Testing User4", true);

        boolean expResult = true;

        // Create mock objects
        SqlConnection sql = mock(SqlConnection.class);
        Connection conn = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);

        System.out.println("Checking for existing users with information previded");
        //---------------------------------------------------------------------
        // Fill mock objects with appropriatel dummy data
        when(sql.getConn()).thenReturn(conn);
        when(conn.prepareStatement("select * from users WHERE email = ?")).thenReturn(ps);
        when(sql.getPs()).thenReturn(ps, ps);
        when(ps.executeQuery()).thenReturn(rs);

        // Want 3 results in the resultset, so need true to be returned 3 times
        when(rs.next()).thenReturn(true, false);

        // Fill in the resultset
        when(rs.getInt("user_id")).thenReturn(u4.getUserNo());
        when(rs.getString("user_fullname")).thenReturn(u4.getUserFullname());
        when(rs.getString("email")).thenReturn(u4.getEmail());
        when(rs.getString("hash")).thenReturn(u4.getUserHash());
        when(rs.getString("user_type")).thenReturn(u4.getUserType());
        when(rs.getString("question")).thenReturn(u4.getQuestion());
        when(rs.getString("answer_hash")).thenReturn(u4.getAnswer_hash());
        when(rs.getBoolean("has_disabled_badge")).thenReturn(u4.getHasDisabledBadge());
        //---------------------------------------------------------------------

        System.out.println("inserting user to database");
        //---------------------------------------------------------------------
        when(sql.getConn()).thenReturn(conn);
        when(conn.prepareStatement("INSERT INTO users(user_fullname, email, hash, user_type, question, answer_hash, has_disabled_badge) VALUES (?,?,?,?,?,?,?)")).thenReturn(ps);
        when(sql.getPs()).thenReturn(ps, ps, ps, ps, ps, ps, ps, ps);

        System.out.println("getting user's id for storing salts");
        //---------------------------------------------------------------------
        when(sql.getConn()).thenReturn(conn);
        when(conn.prepareStatement("select * from users WHERE email = ?")).thenReturn(ps);
        when(sql.getPs()).thenReturn(ps, ps);
        when(ps.executeQuery()).thenReturn(rs);

        // Want 3 results in the resultset, so need true to be returned 3 times
        when(rs.next()).thenReturn(true, false);

        // Fill in the resultset
        when(rs.getInt("user_id")).thenReturn(u4.getUserNo());
        when(rs.getString("user_fullname")).thenReturn(u4.getUserFullname());
        when(rs.getString("email")).thenReturn(u4.getEmail());
        when(rs.getString("hash")).thenReturn(u4.getUserHash());
        when(rs.getString("user_type")).thenReturn(u4.getUserType());
        when(rs.getString("question")).thenReturn(u4.getQuestion());
        when(rs.getString("answer_hash")).thenReturn(u4.getAnswer_hash());
        when(rs.getBoolean("has_disabled_badge")).thenReturn(u4.getHasDisabledBadge());
        //---------------------------------------------------------------------

        System.out.println("storing salts into database");
        //---------------------------------------------------------------------
        when(sql.getConn()).thenReturn(conn);
        when(conn.prepareStatement("INSERT INTO salt(user_id, salt, answer_salt) VALUES (?,?,?)")).thenReturn(ps);
        when(sql.getPs()).thenReturn(ps, ps, ps, ps);
        when(ps.executeQuery()).thenReturn(rs);
        //---------------------------------------------------------------------

        UserDao userDao = new UserDao(sql);
        boolean result = userDao.register(u4.getUserFullname(), u4.getEmail(), u4.getUserHash(), u4.getUserType(), u4.getQuestion(), u4.getAnswer_hash(), u4.getHasDisabledBadge());

        assertEquals(expResult, result);

        System.out.println(result);
    }

    /**
     * Test of SaltANDHash method, of class UserDao.
     */
    @Ignore
    public void testSaltANDHash() {
        System.out.println("SaltANDHash");
        String needHashSalt = "";
        String[] expResult = null;
        String[] result = UserDao.SaltANDHash(needHashSalt);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of CheckUserExistsByEmail method, of class UserDao.
     */
    @Ignore
    public void testCheckUserExistsByEmail() throws SQLException {
        User u1 = new User(1, "Testing User1", "testinguser1@gmail.com", "$2a$12$Fodl2oDf233P40qSfkbVLOmX8R9a6kzuugosLS685hiVZr1qp7KWS", "user", "what is your mother's maiden name", "$2a$12$8XW5CMg.1ssMt9dvm5yMdeGjCTP51HfwFB8O5WDtNeFnNyxJmSBY6", false);
        //User u2 = new User(2, "Testing User2", "testinguser2@gmail.com", "$2a$12$jgxPw.sQUTLOG2Yb1xCeFOVHgD5bbH8bkvzNufPIJ9xRnKOghpw9W", "user", "what is your mother's maiden name", "$2a$12$nXti9bKgnGXGHg5.TGTbEOUmYH2lqdduy0RvMIorAgihWVpaEwKKC", true);
        //User u3 = new User(3, "Testing User3", "testinguser3@gmail.com", "$2a$12$b3uCgzPSHx94wQunCwzPiOMyCbgGp1qE6UEhUgtqNMXZL28tk.zOq", "user", "what is your mother's maiden name", "$2a$12$omWIaLeVXOwpGFveppMrluN9EsvXR1ufV4YheHYWXtftgn0HZ67oG", false);

        boolean expResult = true;

        // Create mock objects
        SqlConnection sql = mock(SqlConnection.class);
        Connection conn = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);

        // Fill mock objects with appropriatel dummy data
        when(sql.getConn()).thenReturn(conn);
        when(conn.prepareStatement("select * from users WHERE email = ?")).thenReturn(ps);
        when(sql.getPs()).thenReturn(ps, ps);
        when(ps.executeQuery()).thenReturn(rs);

        // Want 3 results in the resultset, so need true to be returned 3 times
        when(rs.next()).thenReturn(true, false);

        // Fill in the resultset
        when(rs.getInt("user_id")).thenReturn(u1.getUserNo());
        when(rs.getString("user_fullname")).thenReturn(u1.getUserFullname());
        when(rs.getString("email")).thenReturn(u1.getEmail());
        when(rs.getString("hash")).thenReturn(u1.getUserHash());
        when(rs.getString("user_type")).thenReturn(u1.getUserType());
        when(rs.getString("question")).thenReturn(u1.getQuestion());
        when(rs.getString("answer_hash")).thenReturn(u1.getAnswer_hash());
        when(rs.getBoolean("has_disabled_badge")).thenReturn(u1.getHasDisabledBadge());

        UserDao userDao = new UserDao(sql);
        boolean result = userDao.CheckUserExistsByEmail(u1.getEmail());

        assertEquals(expResult, result);

        System.out.println(result);
    }

    /**
     * Test of CheckUserExistsByEmail method, of class UserDao.
     */
    @Ignore
    public void testCheckUserExistsByEmail_fail() throws SQLException {
        User u1 = new User(1, "Testing User1", "testinguser1@gmail.com", "$2a$12$Fodl2oDf233P40qSfkbVLOmX8R9a6kzuugosLS685hiVZr1qp7KWS", "user", "what is your mother's maiden name", "$2a$12$8XW5CMg.1ssMt9dvm5yMdeGjCTP51HfwFB8O5WDtNeFnNyxJmSBY6", false);
        //User u2 = new User(2, "Testing User2", "testinguser2@gmail.com", "$2a$12$jgxPw.sQUTLOG2Yb1xCeFOVHgD5bbH8bkvzNufPIJ9xRnKOghpw9W", "user", "what is your mother's maiden name", "$2a$12$nXti9bKgnGXGHg5.TGTbEOUmYH2lqdduy0RvMIorAgihWVpaEwKKC", true);
        //User u3 = new User(3, "Testing User3", "testinguser3@gmail.com", "$2a$12$b3uCgzPSHx94wQunCwzPiOMyCbgGp1qE6UEhUgtqNMXZL28tk.zOq", "user", "what is your mother's maiden name", "$2a$12$omWIaLeVXOwpGFveppMrluN9EsvXR1ufV4YheHYWXtftgn0HZ67oG", false);

        boolean expResult = false;

        // Create mock objects
        SqlConnection sql = mock(SqlConnection.class);
        Connection conn = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);

        // Fill mock objects with appropriatel dummy data
        when(sql.getConn()).thenReturn(conn);
        when(conn.prepareStatement("select * from users WHERE email = ?")).thenReturn(ps);
        when(sql.getPs()).thenReturn(ps, ps);
        when(ps.executeQuery()).thenReturn(rs);

        // Want 3 results in the resultset, so need true to be returned 3 times
        when(rs.next()).thenReturn(true, false);

        // Fill in the resultset
        when(rs.getInt("user_id")).thenReturn(u1.getUserNo());
        when(rs.getString("user_fullname")).thenReturn(u1.getUserFullname());
        when(rs.getString("email")).thenReturn(u1.getEmail());
        when(rs.getString("hash")).thenReturn(u1.getUserHash());
        when(rs.getString("user_type")).thenReturn(u1.getUserType());
        when(rs.getString("question")).thenReturn(u1.getQuestion());
        when(rs.getString("answer_hash")).thenReturn(u1.getAnswer_hash());
        when(rs.getBoolean("has_disabled_badge")).thenReturn(u1.getHasDisabledBadge());

        UserDao userDao = new UserDao(sql);
        boolean result = userDao.CheckUserExistsByEmail("notgoingtowork@gmail.com");

        assertEquals(expResult, result);

        System.out.println(result);
    }

    /**
     * Test of updateUser method, of class UserDao.
     */
    @Ignore
    public void testUpdateUser() throws SQLException {
        User u1 = new User(1, "Testing User1", "testinguser1@gmail.com", "$2a$12$Fodl2oDf233P40qSfkbVLOmX8R9a6kzuugosLS685hiVZr1qp7KWS", "user", "what is your mother's maiden name", "$2a$12$8XW5CMg.1ssMt9dvm5yMdeGjCTP51HfwFB8O5WDtNeFnNyxJmSBY6", false);
        //User u2 = new User(2, "Testing User2", "testinguser2@gmail.com", "$2a$12$jgxPw.sQUTLOG2Yb1xCeFOVHgD5bbH8bkvzNufPIJ9xRnKOghpw9W", "user", "what is your mother's maiden name", "$2a$12$nXti9bKgnGXGHg5.TGTbEOUmYH2lqdduy0RvMIorAgihWVpaEwKKC", true);
        //User u3 = new User(3, "Testing User3", "testinguser3@gmail.com", "$2a$12$b3uCgzPSHx94wQunCwzPiOMyCbgGp1qE6UEhUgtqNMXZL28tk.zOq", "user", "what is your mother's maiden name", "$2a$12$omWIaLeVXOwpGFveppMrluN9EsvXR1ufV4YheHYWXtftgn0HZ67oG", false);

        boolean expResult = true;

        // Create mock objects
        SqlConnection sql = mock(SqlConnection.class);
        Connection conn = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);

        // Fill mock objects with appropriatel dummy data
        when(sql.getConn()).thenReturn(conn);
        when(conn.prepareStatement("UPDATE users SET user_fullname=?,question=?,answer_hash=?, has_disabled_badge=? WHERE email = ?")).thenReturn(ps);
        when(sql.getPs()).thenReturn(ps, ps, ps, ps, ps, ps);

        // Want 3 results in the resultset, so need true to be returned 3 times
        when(rs.next()).thenReturn(true, false);

        UserDao userDao = new UserDao(sql);
        boolean result = userDao.updateUser(u1);

        assertEquals(expResult, result);
    }

    /**
     * Test of getUserByEmail method, of class UserDao.
     */
    @Ignore
    public void testGetUserByEmail() throws SQLException {
        User u1 = new User(1, "Testing User1", "testinguser1@gmail.com", "$2a$12$Fodl2oDf233P40qSfkbVLOmX8R9a6kzuugosLS685hiVZr1qp7KWS", "user", "what is your mother's maiden name", "$2a$12$8XW5CMg.1ssMt9dvm5yMdeGjCTP51HfwFB8O5WDtNeFnNyxJmSBY6", false);
        //User u2 = new User(2, "Testing User2", "testinguser2@gmail.com", "$2a$12$jgxPw.sQUTLOG2Yb1xCeFOVHgD5bbH8bkvzNufPIJ9xRnKOghpw9W", "user", "what is your mother's maiden name", "$2a$12$nXti9bKgnGXGHg5.TGTbEOUmYH2lqdduy0RvMIorAgihWVpaEwKKC", true);
        //User u3 = new User(3, "Testing User3", "testinguser3@gmail.com", "$2a$12$b3uCgzPSHx94wQunCwzPiOMyCbgGp1qE6UEhUgtqNMXZL28tk.zOq", "user", "what is your mother's maiden name", "$2a$12$omWIaLeVXOwpGFveppMrluN9EsvXR1ufV4YheHYWXtftgn0HZ67oG", false);

        User expResult = u1;

        // Create mock objects
        SqlConnection sql = mock(SqlConnection.class);
        Connection conn = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);

        // Fill mock objects with appropriatel dummy data
        when(sql.getConn()).thenReturn(conn);
        when(conn.prepareStatement("select * from users WHERE email = ?")).thenReturn(ps);
        when(sql.getPs()).thenReturn(ps, ps);
        when(ps.executeQuery()).thenReturn(rs);

        // Want 3 results in the resultset, so need true to be returned 3 times
        when(rs.next()).thenReturn(true, false);

        // Fill in the resultset
        when(rs.getInt("user_id")).thenReturn(u1.getUserNo());
        when(rs.getString("user_fullname")).thenReturn(u1.getUserFullname());
        when(rs.getString("email")).thenReturn(u1.getEmail());
        when(rs.getString("hash")).thenReturn(u1.getUserHash());
        when(rs.getString("user_type")).thenReturn(u1.getUserType());
        when(rs.getString("question")).thenReturn(u1.getQuestion());
        when(rs.getString("answer_hash")).thenReturn(u1.getAnswer_hash());
        when(rs.getBoolean("has_disabled_badge")).thenReturn(u1.getHasDisabledBadge());

        UserDao userDao = new UserDao(sql);
        User result = userDao.getUserByEmail(u1.getEmail());

        assertEquals(expResult, result);

        System.out.println(result);
    }

    @Ignore
    public void testGetUserByEmail_fail() throws SQLException {
        User u1 = new User(1, "Testing User1", "testinguser1@gmail.com", "$2a$12$Fodl2oDf233P40qSfkbVLOmX8R9a6kzuugosLS685hiVZr1qp7KWS", "user", "what is your mother's maiden name", "$2a$12$8XW5CMg.1ssMt9dvm5yMdeGjCTP51HfwFB8O5WDtNeFnNyxJmSBY6", false);
        User u2 = new User(2, "Testing User2", "testinguser2@gmail.com", "$2a$12$jgxPw.sQUTLOG2Yb1xCeFOVHgD5bbH8bkvzNufPIJ9xRnKOghpw9W", "user", "what is your mother's maiden name", "$2a$12$nXti9bKgnGXGHg5.TGTbEOUmYH2lqdduy0RvMIorAgihWVpaEwKKC", true);
        //User u3 = new User(3, "Testing User3", "testinguser3@gmail.com", "$2a$12$b3uCgzPSHx94wQunCwzPiOMyCbgGp1qE6UEhUgtqNMXZL28tk.zOq", "user", "what is your mother's maiden name", "$2a$12$omWIaLeVXOwpGFveppMrluN9EsvXR1ufV4YheHYWXtftgn0HZ67oG", false);

        User expResult = null;

        // Create mock objects
        SqlConnection sql = mock(SqlConnection.class);
        Connection conn = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);

        // Fill mock objects with appropriatel dummy data
        when(sql.getConn()).thenReturn(conn);
        when(conn.prepareStatement("select * from users WHERE email = ?")).thenReturn(ps);
        when(sql.getPs()).thenReturn(ps, ps);
        when(ps.executeQuery()).thenReturn(rs);

        // Want 3 results in the resultset, so need true to be returned 3 times
        when(rs.next()).thenReturn(true, false);

        // Fill in the resultset
        when(rs.getInt("user_id")).thenReturn(u1.getUserNo());
        when(rs.getString("user_fullname")).thenReturn(u1.getUserFullname());
        when(rs.getString("email")).thenReturn(u1.getEmail());
        when(rs.getString("hash")).thenReturn(u1.getUserHash());
        when(rs.getString("user_type")).thenReturn(u1.getUserType());
        when(rs.getString("question")).thenReturn(u1.getQuestion());
        when(rs.getString("answer_hash")).thenReturn(u1.getAnswer_hash());
        when(rs.getBoolean("has_disabled_badge")).thenReturn(u1.getHasDisabledBadge());

        UserDao userDao = new UserDao(sql);
        User result = userDao.getUserByEmail(u2.getEmail());

        assertEquals(expResult, result);

        System.out.println(result);
    }

    /**
     * Test of CheckUserExistsByEmailRecovery method, of class UserDao.
     */
    @Ignore
    public void testCheckUserExistsByEmailRecovery() throws SQLException {
        User u1 = new User(1, "Testing User1", "testinguser1@gmail.com", "$2a$12$Fodl2oDf233P40qSfkbVLOmX8R9a6kzuugosLS685hiVZr1qp7KWS", "user", "what is your mother's maiden name", "$2a$12$8XW5CMg.1ssMt9dvm5yMdeGjCTP51HfwFB8O5WDtNeFnNyxJmSBY6", false);
        //User u2 = new User(2, "Testing User2", "testinguser2@gmail.com", "$2a$12$jgxPw.sQUTLOG2Yb1xCeFOVHgD5bbH8bkvzNufPIJ9xRnKOghpw9W", "user", "what is your mother's maiden name", "$2a$12$nXti9bKgnGXGHg5.TGTbEOUmYH2lqdduy0RvMIorAgihWVpaEwKKC", true);
        //User u3 = new User(3, "Testing User3", "testinguser3@gmail.com", "$2a$12$b3uCgzPSHx94wQunCwzPiOMyCbgGp1qE6UEhUgtqNMXZL28tk.zOq", "user", "what is your mother's maiden name", "$2a$12$omWIaLeVXOwpGFveppMrluN9EsvXR1ufV4YheHYWXtftgn0HZ67oG", false);

        String expResult = "{\"Qeustion\":\" " + "what is your mother's maiden name" + "  \"}";

        // Create mock objects
        SqlConnection sql = mock(SqlConnection.class);
        Connection conn = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);

        // Fill mock objects with appropriatel dummy data
        when(sql.getConn()).thenReturn(conn);
        when(conn.prepareStatement("select * from users WHERE email = ?")).thenReturn(ps);
        when(sql.getPs()).thenReturn(ps, ps);
        when(ps.executeQuery()).thenReturn(rs);

        // Want 3 results in the resultset, so need true to be returned 3 times
        when(rs.next()).thenReturn(true, false);

        // Fill in the resultset
        when(rs.getInt("user_id")).thenReturn(u1.getUserNo());
        when(rs.getString("user_fullname")).thenReturn(u1.getUserFullname());
        when(rs.getString("email")).thenReturn(u1.getEmail());
        when(rs.getString("hash")).thenReturn(u1.getUserHash());
        when(rs.getString("user_type")).thenReturn(u1.getUserType());
        when(rs.getString("question")).thenReturn(u1.getQuestion());
        when(rs.getString("answer_hash")).thenReturn(u1.getAnswer_hash());
        when(rs.getBoolean("has_disabled_badge")).thenReturn(u1.getHasDisabledBadge());

        UserDao userDao = new UserDao(sql);
        String result = userDao.CheckUserExistsByEmailRecovery(u1);

        assertEquals(expResult, result);
    }

    @Ignore
    public void testCheckUserExistsByEmailRecovery_fail() throws SQLException {
        User u1 = new User(1, "Testing User1", "testinguser1@gmail.com", "$2a$12$Fodl2oDf233P40qSfkbVLOmX8R9a6kzuugosLS685hiVZr1qp7KWS", "user", "What is your childhood nickname", "$2a$12$8XW5CMg.1ssMt9dvm5yMdeGjCTP51HfwFB8O5WDtNeFnNyxJmSBY6", false);
        //User u2 = new User(2, "Testing User2", "testinguser2@gmail.com", "$2a$12$jgxPw.sQUTLOG2Yb1xCeFOVHgD5bbH8bkvzNufPIJ9xRnKOghpw9W", "user", "what is your mother's maiden name", "$2a$12$nXti9bKgnGXGHg5.TGTbEOUmYH2lqdduy0RvMIorAgihWVpaEwKKC", true);
        //User u3 = new User(3, "Testing User3", "testinguser3@gmail.com", "$2a$12$b3uCgzPSHx94wQunCwzPiOMyCbgGp1qE6UEhUgtqNMXZL28tk.zOq", "user", "what is your mother's maiden name", "$2a$12$omWIaLeVXOwpGFveppMrluN9EsvXR1ufV4YheHYWXtftgn0HZ67oG", false);

        String expResult = "false";

        // Create mock objects
        SqlConnection sql = mock(SqlConnection.class);
        Connection conn = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);

        // Fill mock objects with appropriatel dummy data
        when(sql.getConn()).thenReturn(conn);
        when(conn.prepareStatement("select * from users WHERE email = ?")).thenReturn(ps);
        when(sql.getPs()).thenReturn(ps, ps);
        when(ps.executeQuery()).thenReturn(rs);

        // Want 3 results in the resultset, so need true to be returned 3 times
        when(rs.next()).thenReturn(true, false);

        // Fill in the resultset
        when(rs.getInt("user_id")).thenReturn(u1.getUserNo());
        when(rs.getString("user_fullname")).thenReturn(u1.getUserFullname());
        when(rs.getString("email")).thenReturn(u1.getEmail());
        when(rs.getString("hash")).thenReturn(u1.getUserHash());
        when(rs.getString("user_type")).thenReturn(u1.getUserType());
        when(rs.getString("question")).thenReturn(u1.getQuestion());
        when(rs.getString("answer_hash")).thenReturn(u1.getAnswer_hash());
        when(rs.getBoolean("has_disabled_badge")).thenReturn(u1.getHasDisabledBadge());

        UserDao userDao = new UserDao(sql);
        String result = userDao.CheckUserExistsByEmailRecovery(u1);

        assertEquals(expResult, result);
    }

    /**
     * Test of CheckUserRecoveryAnswer method, of class UserDao.
     */
    @Ignore
    public void testCheckUserRecoveryAnswer() throws SQLException {
        User u1 = new User(1, "Testing User1", "testinguser1@gmail.com", "$2a$12$Fodl2oDf233P40qSfkbVLOmX8R9a6kzuugosLS685hiVZr1qp7KWS", "user", "what is your mother's maiden name", "$2a$12$8XW5CMg.1ssMt9dvm5yMdeGjCTP51HfwFB8O5WDtNeFnNyxJmSBY6", false);
        //User u2 = new User(2, "Testing User2", "testinguser2@gmail.com", "$2a$12$jgxPw.sQUTLOG2Yb1xCeFOVHgD5bbH8bkvzNufPIJ9xRnKOghpw9W", "user", "what is your mother's maiden name", "$2a$12$nXti9bKgnGXGHg5.TGTbEOUmYH2lqdduy0RvMIorAgihWVpaEwKKC", true);
        //User u3 = new User(3, "Testing User3", "testinguser3@gmail.com", "$2a$12$b3uCgzPSHx94wQunCwzPiOMyCbgGp1qE6UEhUgtqNMXZL28tk.zOq", "user", "what is your mother's maiden name", "$2a$12$omWIaLeVXOwpGFveppMrluN9EsvXR1ufV4YheHYWXtftgn0HZ67oG", false);
        User u4 = new User(4, "Testing User4", "testinguser4@gmail.com", "Testinguser4", "user", "what is your mother's maiden name", "Testing User4", true);

        boolean expResult = true;

        // Create mock objects
        SqlConnection sql = mock(SqlConnection.class);
        Connection conn = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);

        // Fill mock objects with appropriatel dummy data
        when(sql.getConn()).thenReturn(conn);
        when(conn.prepareStatement("select * from users WHERE email = ?")).thenReturn(ps);
        when(sql.getPs()).thenReturn(ps, ps);
        when(ps.executeQuery()).thenReturn(rs);

        // Want 3 results in the resultset, so need true to be returned 3 times
        when(rs.next()).thenReturn(true, false);

        // Fill in the resultset
        when(rs.getInt("user_id")).thenReturn(u1.getUserNo());
        when(rs.getString("user_fullname")).thenReturn(u1.getUserFullname());
        when(rs.getString("email")).thenReturn(u1.getEmail());
        when(rs.getString("hash")).thenReturn(u1.getUserHash());
        when(rs.getString("user_type")).thenReturn(u1.getUserType());
        when(rs.getString("question")).thenReturn(u1.getQuestion());
        when(rs.getString("answer_hash")).thenReturn(u1.getAnswer_hash());
        when(rs.getBoolean("has_disabled_badge")).thenReturn(u1.getHasDisabledBadge());

        UserDao userDao = new UserDao(sql);
        boolean result = userDao.CheckUserRecoveryAnswer(u1);

        assertEquals(expResult, result);
    }

    /**
     * Test of updateUserPassword method, of class UserDao.
     */
    @Ignore
    public void testUpdateUserPassword() throws SQLException {
        User u1 = new User(1, "Testing User1", "testinguser1@gmail.com", "$2a$12$Fodl2oDf233P40qSfkbVLOmX8R9a6kzuugosLS685hiVZr1qp7KWS", "user", "what is your mother's maiden name", "$2a$12$8XW5CMg.1ssMt9dvm5yMdeGjCTP51HfwFB8O5WDtNeFnNyxJmSBY6", false);
        //User u2 = new User(2, "Testing User2", "testinguser2@gmail.com", "$2a$12$jgxPw.sQUTLOG2Yb1xCeFOVHgD5bbH8bkvzNufPIJ9xRnKOghpw9W", "user", "what is your mother's maiden name", "$2a$12$nXti9bKgnGXGHg5.TGTbEOUmYH2lqdduy0RvMIorAgihWVpaEwKKC", true);
        //User u3 = new User(3, "Testing User3", "testinguser3@gmail.com", "$2a$12$b3uCgzPSHx94wQunCwzPiOMyCbgGp1qE6UEhUgtqNMXZL28tk.zOq", "user", "what is your mother's maiden name", "$2a$12$omWIaLeVXOwpGFveppMrluN9EsvXR1ufV4YheHYWXtftgn0HZ67oG", false);

        boolean expResult = true;

        System.out.println("Updating users table in database");
        //---------------------------------------------------------------------
        // Create mock objects
        SqlConnection sql = mock(SqlConnection.class);
        Connection conn = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);

        // Fill mock objects with appropriatel dummy data
        when(sql.getConn()).thenReturn(conn);
        when(conn.prepareStatement("UPDATE users SET hash = ? WHERE email = ?")).thenReturn(ps);
        when(sql.getPs()).thenReturn(ps, ps, ps);

        // Want 3 results in the resultset, so need true to be returned 3 times
        when(rs.next()).thenReturn(true, false);
        //---------------------------------------------------------------------

        System.out.println("Updating salts table in database");
        //---------------------------------------------------------------------
        SqlConnection sql2 = mock(SqlConnection.class);
        Connection conn2 = mock(Connection.class);
        PreparedStatement ps2 = mock(PreparedStatement.class);
        ResultSet rs2 = mock(ResultSet.class);

        when(sql2.getConn()).thenReturn(conn2);
        when(conn2.prepareStatement("UPDATE salt SET salt = ? WHERE user_id = ?")).thenReturn(ps2);
        when(sql2.getPs()).thenReturn(ps2, ps2, ps2);

        // Want 3 results in the resultset, so need true to be returned 3 times
        when(rs2.next()).thenReturn(true, false);
        //---------------------------------------------------------------------

        UserDao userDao = new UserDao(sql);
        boolean result = userDao.updateUserPassword(u1);

        assertEquals(expResult, result);
    }

    @Ignore
    public void testUpdateUserPassword_fail() throws SQLException {
        User u1 = new User(1, "Testing User1", "testinguser1@gmail.com", "$2a$12$Fodl2oDf233P40qSfkbVLOmX8R9a6kzuugosLS685hiVZr1qp7KWS", "user", "what is your mother's maiden name", "$2a$12$8XW5CMg.1ssMt9dvm5yMdeGjCTP51HfwFB8O5WDtNeFnNyxJmSBY6", false);
        //User u2 = new User(2, "Testing User2", "testinguser2@gmail.com", "$2a$12$jgxPw.sQUTLOG2Yb1xCeFOVHgD5bbH8bkvzNufPIJ9xRnKOghpw9W", "user", "what is your mother's maiden name", "$2a$12$nXti9bKgnGXGHg5.TGTbEOUmYH2lqdduy0RvMIorAgihWVpaEwKKC", true);
        //User u3 = new User(3, "Testing User3", "testinguser3@gmail.com", "$2a$12$b3uCgzPSHx94wQunCwzPiOMyCbgGp1qE6UEhUgtqNMXZL28tk.zOq", "user", "what is your mother's maiden name", "$2a$12$omWIaLeVXOwpGFveppMrluN9EsvXR1ufV4YheHYWXtftgn0HZ67oG", false);

        boolean expResult = true;

        System.out.println("Updating users table in database");
        //---------------------------------------------------------------------
        // Create mock objects
        SqlConnection sql = mock(SqlConnection.class);
        Connection conn = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);

        // Fill mock objects with appropriatel dummy data
        when(sql.getConn()).thenReturn(conn);
        when(conn.prepareStatement("UPDATE users SET hash = ? WHERE email = ?")).thenReturn(ps);
        when(sql.getPs()).thenReturn(ps, ps, ps);

        // Want 3 results in the resultset, so need true to be returned 3 times
        when(rs.next()).thenReturn(true, false);
        //---------------------------------------------------------------------

        System.out.println("Updating salts table in database");
        //---------------------------------------------------------------------
        SqlConnection sql2 = mock(SqlConnection.class);
        Connection conn2 = mock(Connection.class);
        PreparedStatement ps2 = mock(PreparedStatement.class);
        ResultSet rs2 = mock(ResultSet.class);

        when(sql2.getConn()).thenReturn(conn2);
        when(conn2.prepareStatement("UPDATE salt SET salt = ? WHERE user_id = ?")).thenReturn(ps2);
        when(sql2.getPs()).thenReturn(ps2, ps2, ps2);

        // Want 3 results in the resultset, so need true to be returned 3 times
        when(rs2.next()).thenReturn(true, false);
        //---------------------------------------------------------------------

        UserDao userDao = new UserDao(sql);
        boolean result = userDao.updateUserPassword(u1);

        assertEquals(expResult, result);
    }

    /**
     * Test of AdminDeletesYser method, of class UserDao.
     */
    @Test
    public void testAdminDeletesYser() throws SQLException {
        User u1 = new User(1, "Testing User1", "testinguser1@gmail.com", "$2a$12$Fodl2oDf233P40qSfkbVLOmX8R9a6kzuugosLS685hiVZr1qp7KWS", "user", "what is your mother's maiden name", "$2a$12$8XW5CMg.1ssMt9dvm5yMdeGjCTP51HfwFB8O5WDtNeFnNyxJmSBY6", false);
        //User u2 = new User(2, "Testing User2", "testinguser2@gmail.com", "$2a$12$jgxPw.sQUTLOG2Yb1xCeFOVHgD5bbH8bkvzNufPIJ9xRnKOghpw9W", "user", "what is your mother's maiden name", "$2a$12$nXti9bKgnGXGHg5.TGTbEOUmYH2lqdduy0RvMIorAgihWVpaEwKKC", true);
        //User u3 = new User(3, "Testing User3", "testinguser3@gmail.com", "$2a$12$b3uCgzPSHx94wQunCwzPiOMyCbgGp1qE6UEhUgtqNMXZL28tk.zOq", "user", "what is your mother's maiden name", "$2a$12$omWIaLeVXOwpGFveppMrluN9EsvXR1ufV4YheHYWXtftgn0HZ67oG", false);

        boolean expResult = true;

        System.out.println("Deleting from salts database");
        //---------------------------------------------------------------------
        // Create mock objects
        SqlConnection sql = mock(SqlConnection.class);
        Connection conn = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);

        // Fill mock objects with appropriatel dummy data
        when(sql.getConn()).thenReturn(conn);
        when(conn.prepareStatement("DELETE FROM salt WHERE user_id = ?")).thenReturn(ps);
        when(sql.getPs()).thenReturn(ps, ps);

        // Want 3 results in the resultset, so need true to be returned 3 times
        when(rs.next()).thenReturn(true, false);
        //---------------------------------------------------------------------

        System.out.println("Deleting from users database");
        //---------------------------------------------------------------------
        SqlConnection sql2 = mock(SqlConnection.class);
        Connection conn2 = mock(Connection.class);
        PreparedStatement ps2 = mock(PreparedStatement.class);
        ResultSet rs2 = mock(ResultSet.class);

        when(sql2.getConn()).thenReturn(conn2);
        when(conn2.prepareStatement("DELETE FROM users WHERE user_id = ?")).thenReturn(ps2);
        when(sql2.getPs()).thenReturn(ps2, ps2);

        // Want 3 results in the resultset, so need true to be returned 3 times
        when(rs2.next()).thenReturn(true, false);
        //---------------------------------------------------------------------

        UserDao userDao = new UserDao(sql);
        boolean result = userDao.AdminDeletesUser(u1);

        assertEquals(expResult, result);
    }

    @Test
    public void testAdminDeletesYser_fail() throws SQLException {
        User u1 = new User(1, "Testing User1", "testinguser1@gmail.com", "$2a$12$Fodl2oDf233P40qSfkbVLOmX8R9a6kzuugosLS685hiVZr1qp7KWS", "user", "what is your mother's maiden name", "$2a$12$8XW5CMg.1ssMt9dvm5yMdeGjCTP51HfwFB8O5WDtNeFnNyxJmSBY6", false);
        //User u2 = new User(2, "Testing User2", "testinguser2@gmail.com", "$2a$12$jgxPw.sQUTLOG2Yb1xCeFOVHgD5bbH8bkvzNufPIJ9xRnKOghpw9W", "user", "what is your mother's maiden name", "$2a$12$nXti9bKgnGXGHg5.TGTbEOUmYH2lqdduy0RvMIorAgihWVpaEwKKC", true);
        //User u3 = new User(3, "Testing User3", "testinguser3@gmail.com", "$2a$12$b3uCgzPSHx94wQunCwzPiOMyCbgGp1qE6UEhUgtqNMXZL28tk.zOq", "user", "what is your mother's maiden name", "$2a$12$omWIaLeVXOwpGFveppMrluN9EsvXR1ufV4YheHYWXtftgn0HZ67oG", false);

        boolean expResult = false;

        System.out.println("Deleting from salts database");
        //---------------------------------------------------------------------
        // Create mock objects
        SqlConnection sql = mock(SqlConnection.class);
        Connection conn = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);

        // Fill mock objects with appropriatel dummy data
        when(sql.getConn()).thenReturn(conn);
        when(conn.prepareStatement("DELETE FROM salt WHERE user_id = ?")).thenReturn(ps);
        when(sql.getPs()).thenReturn(ps, ps);

        // Want 3 results in the resultset, so need true to be returned 3 times
        when(rs.next()).thenReturn(true, false);
        //---------------------------------------------------------------------

        System.out.println("Deleting from users database");
        //---------------------------------------------------------------------
        SqlConnection sql2 = mock(SqlConnection.class);
        Connection conn2 = mock(Connection.class);
        PreparedStatement ps2 = mock(PreparedStatement.class);
        ResultSet rs2 = mock(ResultSet.class);

        when(sql2.getConn()).thenReturn(conn2);
        when(conn2.prepareStatement("DELETE FROM users WHERE user_id = ?")).thenReturn(ps2);
        when(sql2.getPs()).thenReturn(ps2, ps2);

        // Want 3 results in the resultset, so need true to be returned 3 times
        when(rs2.next()).thenReturn(true, false);
        //---------------------------------------------------------------------

        UserDao userDao = new UserDao(sql);
        boolean result = userDao.AdminDeletesUser(u1);

        assertEquals(expResult, result);
    }

    /**
     * Test of AdminUpdatesUserTypes method, of class UserDao.
     */
    @Ignore
    public void testAdminUpdatesUserTypes() throws SQLException {
        User u1 = new User(1, "Testing User1", "testinguser1@gmail.com", "$2a$12$Fodl2oDf233P40qSfkbVLOmX8R9a6kzuugosLS685hiVZr1qp7KWS", "user", "what is your mother's maiden name", "$2a$12$8XW5CMg.1ssMt9dvm5yMdeGjCTP51HfwFB8O5WDtNeFnNyxJmSBY6", false);
        //User u2 = new User(2, "Testing User2", "testinguser2@gmail.com", "$2a$12$jgxPw.sQUTLOG2Yb1xCeFOVHgD5bbH8bkvzNufPIJ9xRnKOghpw9W", "user", "what is your mother's maiden name", "$2a$12$nXti9bKgnGXGHg5.TGTbEOUmYH2lqdduy0RvMIorAgihWVpaEwKKC", true);
        //User u3 = new User(3, "Testing User3", "testinguser3@gmail.com", "$2a$12$b3uCgzPSHx94wQunCwzPiOMyCbgGp1qE6UEhUgtqNMXZL28tk.zOq", "user", "what is your mother's maiden name", "$2a$12$omWIaLeVXOwpGFveppMrluN9EsvXR1ufV4YheHYWXtftgn0HZ67oG", false);

        boolean expResult = true;

        // Create mock objects
        SqlConnection sql = mock(SqlConnection.class);
        Connection conn = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);

        // Fill mock objects with appropriatel dummy data
        when(sql.getConn()).thenReturn(conn);
        when(conn.prepareStatement("UPDATE users SET user_type = ? WHERE email = ?")).thenReturn(ps);
        when(sql.getPs()).thenReturn(ps, ps, ps);

        // Want 3 results in the resultset, so need true to be returned 3 times
        when(rs.next()).thenReturn(true, false);

        UserDao userDao = new UserDao(sql);
        boolean result = userDao.AdminUpdatesUserTypes(u1);

        assertEquals(expResult, result);
    }

    @Ignore
    public void testAdminUpdatesUserTypes_fail() throws SQLException {
        User u1 = new User(1, "Testing User1", "testinguser1@gmail.com", "$2a$12$Fodl2oDf233P40qSfkbVLOmX8R9a6kzuugosLS685hiVZr1qp7KWS", "user", "what is your mother's maiden name", "$2a$12$8XW5CMg.1ssMt9dvm5yMdeGjCTP51HfwFB8O5WDtNeFnNyxJmSBY6", false);
        //User u2 = new User(2, "Testing User2", "testinguser2@gmail.com", "$2a$12$jgxPw.sQUTLOG2Yb1xCeFOVHgD5bbH8bkvzNufPIJ9xRnKOghpw9W", "user", "what is your mother's maiden name", "$2a$12$nXti9bKgnGXGHg5.TGTbEOUmYH2lqdduy0RvMIorAgihWVpaEwKKC", true);
        //User u3 = new User(3, "Testing User3", "testinguser3@gmail.com", "$2a$12$b3uCgzPSHx94wQunCwzPiOMyCbgGp1qE6UEhUgtqNMXZL28tk.zOq", "user", "what is your mother's maiden name", "$2a$12$omWIaLeVXOwpGFveppMrluN9EsvXR1ufV4YheHYWXtftgn0HZ67oG", false);

        boolean expResult = false;

        // Create mock objects
        SqlConnection sql = mock(SqlConnection.class);
        Connection conn = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);

        // Fill mock objects with appropriatel dummy data
        when(sql.getConn()).thenReturn(conn);
        when(conn.prepareStatement("UPDATE users SET user_type = ? WHERE email = ?")).thenReturn(ps);
        when(sql.getPs()).thenReturn(ps, ps, ps);

        // Want 3 results in the resultset, so need true to be returned 3 times
        when(rs.next()).thenReturn(true, false);

        UserDao userDao = new UserDao(sql);
        boolean result = userDao.AdminUpdatesUserTypes(u1);

        assertEquals(expResult, result);
    }
}
