/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DaoIntegration;

import Dao.UserDao;
import Dto.User;
import Dto.UserImage;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author SeppQ
 */
public class UserDaoIT {
    
    public UserDaoIT() {
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
    public void testSelectAllUsers() {
        System.out.println("selectAllUsers");
        UserDao instance = new UserDao();
        String expResult = "[User{userNo=14, userFullname=Michael Lawson, Email=michael.c.k.lawson@gmail.com, userHash=$2a$12$JEV2Q3QjkQUeBt3ppnKdt.tEw3w3CbHXhgwPK5eh.wDYctKp6knCy, userType=admin, question=What is your oldest sibling's middle name?, answer_hash=$2a$12$CAi4ItWkf9y5TDmtvDybyeBwRuOrF3.ZD7ih5OZxVjTCB7mFewXNq, hasDisabledBadge=true}, User{userNo=15, userFullname=test1, Email=test1@email.com, userHash=$2a$12$jL1zKr8wqlta7zIG3RN8qu05ujys5GukLBD2wtTRvO2m4YSVolx9S, userType=admin, question=What was your childhood nickname?, answer_hash=$2a$12$/BXnO.apyFUrX0SABxypUOX0Y2ZSAmNoCU2NbBoZTo0H88x0Bu7WK, hasDisabledBadge=true}, User{userNo=16, userFullname=Test2, Email=test2@gmail.com, userHash=$2a$12$wZrluDWmlSL5Gja10diRvO4e1NnbV0xLbtKnQeZNz8HIYMRHbjXQu, userType=regular, question=What was your childhood nickname?, answer_hash=$2a$12$0Z51IHVEQmQC6gfdmjtOZucqtS02hXKxMiRV5dIQxUSfj5kcoWcdi, hasDisabledBadge=true}, User{userNo=17, userFullname=Lukas Krukonis, Email=asd@gmail.com, userHash=$2a$12$gjZAkhaleSR13LpzpewGkeJFfgoCf4Db4XO1mrd.fK0VFz0Mpd9Cm, userType=admin, question=What street did you live on in third grade?, answer_hash=$2a$12$o1EqAiErYm2T7lXswoDa2.Wf70FkdBGgAjNqsFnCnz/ND4rOaTVVa, hasDisabledBadge=false}, User{userNo=18, userFullname=Peter Pollis, Email=peter@gmail.com, userHash=$2a$12$uzaZ9zhKyd7I1yM8IuIQBOQamqEENyyrRpo.1tlZ6gVbZQpEtmC4m, userType=regular, question=What was your childhood nickname?, answer_hash=, hasDisabledBadge=false}, User{userNo=20, userFullname=Mark Manson, Email=mark@gmail.com, userHash=$2a$12$669W4fSZ00UtofDssFO9.eLxBg3.baV3/s9fL74M.UJlbgTz5bDXS, userType=regular, question=What is your mothers maiden name?, answer_hash=$2a$12$nbtYOT3hd8GN60OTVWBylebhmfXIANv//cholMSgKhla1kilJcOCm, hasDisabledBadge=true}, User{userNo=21, userFullname=Mark Manson, Email=mark1@gmail.com, userHash=$2a$12$SVwL1zflA6lx2VnXV.G29.XOcAC2DrxPrfDujtPekkoUjHzolYFbm, userType=clamping, question=What is your mothers maiden name?, answer_hash=$2a$12$SXzTcvZqzkNySAEA0bOtMOrLi/Tm.vFQrI/0QJTACX/yNUljJtovO, hasDisabledBadge=true}, User{userNo=23, userFullname=emily omally, Email=emily@gmail.com, userHash=$2a$12$COUQU3bf6ElpCeXaQ6tdiu/CbsROCmqiEB0JARvEdB4QvDvQacVJC, userType=regular, question=What was your childhood nickname?, answer_hash=$2a$12$8LZKzH.q.suuGHVEiuxCL.bK0zd5niwtfIMTvfaXbFezVVlzBiGye, hasDisabledBadge=false}, User{userNo=24, userFullname=lily , Email=lily@gmail.com, userHash=$2a$12$EOmFK2KIlb3QwItYnSWpZev/iGEmGEmU2b0GR3UlcVCjQx8FU2jFq, userType=regular, question=What was your childhood nickname?, answer_hash=$2a$12$AZVc7I04wA.u26yEdHoGtOQt2bILxiIuh301YkLIo1Q4iI2h7li2O, hasDisabledBadge=false}]";
        String result = instance.selectAllUsers().toString();
        assertEquals(expResult, result);

    }

    /**
     * Test of login method, of class UserDao.
     */
    @Test
    public void testLogin() {
        System.out.println("login");
        String email = "peter@gmail.com";
        String hash = "Peter123";
        UserDao instance = new UserDao();
        boolean expResult = true;
        boolean result = instance.login(email, hash);
        assertEquals(expResult, result);

    }

    /**
     * Test of register method, of class UserDao.
     */
    @Test
    public void testRegister() {
        System.out.println("register");
        String fullname = "James";
        String email = "James@gmail.com";
        String hash = "James123";
        String user_Type = "regular";
        String question = "asd";
        String answer_hash = "asd";
        boolean has_disabled_badge = false;
        UserDao instance = new UserDao();
        boolean expResult = true;
        boolean result = instance.register(fullname, email, hash, user_Type, question, answer_hash, has_disabled_badge);
        assertEquals(expResult, result);

    }


    /**
     * Test of checkUserExistsByEmail method, of class UserDao.
     */
    @Test
    public void testCheckUserExistsByEmail() {
        System.out.println("checkUserExistsByEmail");
        String email = "peter@gmail.com";
        UserDao instance = new UserDao();
        boolean expResult = true;
        boolean result = instance.checkUserExistsByEmail(email);
        assertEquals(expResult, result);

    }

    /**
     * Test of updateUser method, of class UserDao.
     */
    @Test
    public void testUpdateUser() {
        System.out.println("updateUser");
        User user = new User(18,"Peter Pollis","peter@gmail.com","","regular","What was your childhood nickname?","",false);
        
        UserDao instance = new UserDao();
        boolean expResult = true;
        boolean result = instance.updateUser(user);
        assertEquals(expResult, result);

    }

    /**
     * Test of getUserByEmail method, of class UserDao.
     */
    @Test
    public void testGetUserByEmail() {
        System.out.println("getUserByEmail");
        String email = "asdasdasdad@gmail.com";
        UserDao instance = new UserDao();
        User expResult = null;
        User result = instance.getUserByEmail(email);
        assertEquals(expResult, result);
    }

    /**
     * Test of checkUserExistsByEmailRecovery method, of class UserDao.
     */
    @Test
    public void testCheckUserExistsByEmailRecovery() {
        System.out.println("checkUserExistsByEmailRecovery");
        User user = new User(18,"Peter Pollis","peter@gmail.com","","regular","What was your childhood nickname?","",false);
        UserDao instance = new UserDao();
        String expResult = "{\"Qeustion\":\" What was your childhood nickname?  \"}";
        String result = instance.checkUserExistsByEmailRecovery(user);
        assertEquals(expResult, result);

    }

    /**
     * Test of checkUserRecoveryAnswer method, of class UserDao.
     */
    @Test
    public void testCheckUserRecoveryAnswer() {
        System.out.println("checkUserRecoveryAnswer");
       User user = new User(18,"Peter Pollis","peter@gmail.com","","regular","What was your childhood nickname?","asd",false);
        UserDao instance = new UserDao();
        boolean expResult = false;
        boolean result = instance.checkUserRecoveryAnswer(user);
        assertEquals(expResult, result);

    }

    /**
     * Test of updateUserPassword method, of class UserDao.
     */
    @Test
    public void testUpdateUserPassword() {
        System.out.println("updateUserPassword");
        User user = new User(18,"Peter Pollis","peter@gmail.com","Peter123","regular","What was your childhood nickname?","asd",false);
        UserDao instance = new UserDao();
        boolean expResult = true;
        boolean result = instance.updateUserPassword(user);
        assertEquals(expResult, result);

    }

    /**
     * Test of adminDeletesUser method, of class UserDao.
     */
    @Test
    public void testAdminDeletesUser() {
        System.out.println("adminDeletesUser");
        User user = new User(16,"Peter Pollis","peter@gmail.com","Peter123","regular","What was your childhood nickname?","asd",false);
        UserDao instance = new UserDao();
        boolean expResult = false;
        boolean result = instance.adminDeletesUser(user);
        assertEquals(expResult, result);

    }

    /**
     * Test of adminUpdatesUserTypes method, of class UserDao.
     */
    @Test
    public void testAdminUpdatesUserTypes() {
        System.out.println("adminUpdatesUserTypes");
        User user = new User(15,"Peter Pollis","peter@gmail.com","Peter123","regular","What was your childhood nickname?","asd",false);
        UserDao instance = new UserDao();
        boolean expResult = true;
        boolean result = instance.adminUpdatesUserTypes(user);
        assertEquals(expResult, result);

    }




    /**
     * Test of selectUserById method, of class UserDao.
     */
    @Test
    public void testSelectUserById() {
        System.out.println("selectUserById");
        int userId = 18;
        UserDao instance = new UserDao();
        Object expResult = new User(18,"Peter Pollis","peter@gmail.com","$2a$12$c8kr6kbKpvP5qqOY2eErN.7peUMxaPiQKdU2iIxa653oDXK5f3Mta","regular","What was your childhood nickname?","asd",false);
        Object result = instance.selectUserById(userId);
        assertEquals(expResult, result);

    }
    
}
