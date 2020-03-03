/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

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
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;
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
    @Test
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
        when(rs.next()).thenReturn(true, true, true , false);

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
     * Test of Login method, of class UserDao.
     */
    @Ignore
    public void testLogin() {
        System.out.println("Login");
        String email = "";
        String hash = "";
        UserDao instance = new UserDao();
        boolean expResult = false;
        boolean result = instance.Login(email, hash);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of register method, of class UserDao.
     */
    @Ignore
    public void testRegister() {
        System.out.println("register");
        String fullname = "";
        String email = "";
        String hash = "";
        String user_Type = "";
        String question = "";
        String answer_hash = "";
        boolean has_disabled_badge = false;
        UserDao instance = new UserDao();
        boolean expResult = false;
        boolean result = instance.register(fullname, email, hash, user_Type, question, answer_hash, has_disabled_badge);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
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
    public void testCheckUserExistsByEmail() {
        System.out.println("CheckUserExistsByEmail");
        String email = "";
        UserDao instance = new UserDao();
        boolean expResult = false;
        boolean result = instance.CheckUserExistsByEmail(email);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateUser method, of class UserDao.
     */
    @Ignore
    public void testUpdateUser() {
        System.out.println("updateUser");
        User user = null;
        UserDao instance = new UserDao();
        boolean expResult = false;
        boolean result = instance.updateUser(user);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUserByEmail method, of class UserDao.
     */
    @Test
    public void testGetUserByEmail() throws SQLException {
        User u1 = new User(1, "Testing User1", "testinguser1@gmail.com", "$2a$12$Fodl2oDf233P40qSfkbVLOmX8R9a6kzuugosLS685hiVZr1qp7KWS", "user", "what is your mother's maiden name", "$2a$12$8XW5CMg.1ssMt9dvm5yMdeGjCTP51HfwFB8O5WDtNeFnNyxJmSBY6", false);
        User u2 = new User(2, "Testing User2", "testinguser2@gmail.com", "$2a$12$jgxPw.sQUTLOG2Yb1xCeFOVHgD5bbH8bkvzNufPIJ9xRnKOghpw9W", "user", "what is your mother's maiden name", "$2a$12$nXti9bKgnGXGHg5.TGTbEOUmYH2lqdduy0RvMIorAgihWVpaEwKKC", true);
        User u3 = new User(3, "Testing User3", "testinguser3@gmail.com", "$2a$12$b3uCgzPSHx94wQunCwzPiOMyCbgGp1qE6UEhUgtqNMXZL28tk.zOq", "user", "what is your mother's maiden name", "$2a$12$omWIaLeVXOwpGFveppMrluN9EsvXR1ufV4YheHYWXtftgn0HZ67oG", false);

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
        when(rs.getInt("user_id")).thenReturn(u1.getUserNo(), u2.getUserNo(), u3.getUserNo());
        when(rs.getString("user_fullname")).thenReturn(u1.getUserFullname(), u2.getUserFullname(), u3.getUserFullname());
        when(rs.getString("email")).thenReturn(u1.getEmail(), u2.getEmail(), u3.getEmail());
        when(rs.getString("hash")).thenReturn(u1.getUserHash(), u2.getUserHash(), u3.getUserHash());
        when(rs.getString("user_type")).thenReturn(u1.getUserType(), u2.getUserType(), u3.getUserType());
        when(rs.getString("question")).thenReturn(u1.getQuestion(), u2.getQuestion(), u3.getQuestion());
        when(rs.getString("answer_hash")).thenReturn(u1.getAnswer_hash(), u2.getAnswer_hash(), u3.getAnswer_hash());
        when(rs.getBoolean("has_disabled_badge")).thenReturn(u1.getHasDisabledBadge(), u2.getHasDisabledBadge(), u3.getHasDisabledBadge());

        UserDao userDao = new UserDao(sql);
        User result = userDao.getUserByEmail(u1.getEmail());
        
        assertEquals(expResult, result);

        System.out.println(result);
    }

    /**
     * Test of CheckUserExistsByEmailRecovery method, of class UserDao.
     */
    @Ignore
    public void testCheckUserExistsByEmailRecovery() {
        System.out.println("CheckUserExistsByEmailRecovery");
        User user = null;
        UserDao instance = new UserDao();
        String expResult = "";
        String result = instance.CheckUserExistsByEmailRecovery(user);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of CheckUserRecoveryAnswer method, of class UserDao.
     */
    @Ignore
    public void testCheckUserRecoveryAnswer() {
        System.out.println("CheckUserRecoveryAnswer");
        User user = null;
        UserDao instance = new UserDao();
        boolean expResult = false;
        boolean result = instance.CheckUserRecoveryAnswer(user);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateUserPassword method, of class UserDao.
     */
    @Ignore
    public void testUpdateUserPassword() {
        System.out.println("updateUserPassword");
        User user = null;
        UserDao instance = new UserDao();
        boolean expResult = false;
        boolean result = instance.updateUserPassword(user);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of AdminDeletesYser method, of class UserDao.
     */
    @Ignore
    public void testAdminDeletesYser() {
        System.out.println("AdminDeletesYser");
        User user = null;
        UserDao instance = new UserDao();
        boolean expResult = false;
        boolean result = instance.AdminDeletesUser(user);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of AdminUpdatesUserTypes method, of class UserDao.
     */
    @Ignore
    public void testAdminUpdatesUserTypes() {
        System.out.println("AdminUpdatesUserTypes");
        User user = null;
        UserDao instance = new UserDao();
        boolean expResult = false;
        boolean result = instance.AdminUpdatesUserTypes(user);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
