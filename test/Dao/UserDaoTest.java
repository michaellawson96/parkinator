/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Dto.User;
import SqlConnection.SqlConnection;
import java.util.ArrayList;
import org.junit.*;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author snake
 */
public class UserDaoTest {

    private static UserDao userDao;

    public UserDaoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        userDao = new UserDao();
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
    public void testSelectAllUsers() {
        System.out.println("selectAllUsers");
        ArrayList<User> result = userDao.selectAllUsers();
        int numUserInTable = 4;
        assertEquals(numUserInTable, result.size());
    }

    /**
     * Test of Login method, of class UserDao.
     */
    @Test
    public void testLogin() {
        System.out.println("Login = true");
        String email = "testinguser1@gmail.com";
        String hash = "Testinguser1";
        boolean result = userDao.Login(email, hash);
        boolean expResult = true;
        assertEquals(expResult, result);
    }

    /**
     * Test of register method, of class UserDao.
     */
    @Test
    public void testRegister() {
        System.out.println("register");
        String fullname = "Testing User4";
        String email = "testinguser4@gmail.com";
        String hash = "Testinguser4";
        String user_Type = "admin";
        String question = "what is your mother's maiden name";
        String answer_hash = "testinguser4";
        boolean has_disabled_badge = false;
        UserDao instance = new UserDao();
        boolean expResult = false;
        boolean result = instance.register(fullname, email, hash, user_Type, question, answer_hash, has_disabled_badge);
        assertEquals(expResult, result);
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
    @Test
    public void testCheckUserExistsByEmail() {
        System.out.println("CheckUserExistsByEmail = true");
        String email = "testinguser1@gmail.com";
        boolean expResult = true;
        boolean result = userDao.CheckUserExistsByEmail(email);
        assertEquals(expResult, result);
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
    public void testGetUserByEmail() {
        System.out.println("getUserByEmail");
        String email = "testinguser1@gmail.com";
        User expResult = new User(1, "Testing User1", "testinguser1@gmail.com", "$2a$12$Fodl2oDf233P40qSfkbVLOmX8R9a6kzuugosLS685hiVZr1qp7KWS", "user", "what is your mother's maiden name", "$2a$12$8XW5CMg.1ssMt9dvm5yMdeGjCTP51HfwFB8O5WDtNeFnNyxJmSBY6", false);
        User result = userDao.getUserByEmail(email);
        assertEquals(expResult, result);
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
    public void testAdminDeletesUser() {
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
