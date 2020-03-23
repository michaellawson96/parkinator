/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webdriver;

import java.util.ResourceBundle;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 *
 * @author Lukas
 */
public class TestGetUserByEmail {
    
    ResourceBundle config = ResourceBundle.getBundle("webdriver.WDConfig");
        String driverUrl = config.getString("driverUrl");
        String testServicesUrl = config.getString("testServicesUrl");
    
    @Test
    public void GetUserByEmail_EmailExists_GetUserSucceeds() {
        System.setProperty("webdriver.chrome.driver", driverUrl);
        WebDriver driver = new ChromeDriver();

        driver.get(testServicesUrl);

        driver.findElement(By.linkText("UserDetails")).click();

        driver.findElement(By.id("blobParam")).clear();

        driver.findElement(By.id("blobParam")).sendKeys("{\"email\":\"michael.c.k.lawson@gmail.com\"}");
        
        driver.findElement(By.linkText("Test")).click();
        
        String expResult = "{\"user_type\":\"admin\",\"question\":\"What is your oldest sibling's middle name?\",\"user_id\":14,\"answer_hash\":\"$2a$12$CAi4ItWkf9y5TDmtvDybyeBwRuOrF3.ZD7ih5OZxVjTCB7mFewXNq\",\"user_fullname\":\"Michael Lawson\",\"email\":\"michael.c.k.lawson@gmail.com\",\"hash\":\"$2a$12$JEV2Q3QjkQUeBt3ppnKdt.tEw3w3CbHXhgwPK5eh.wDYctKp6knCy\",\"has_disabled_badge\":false}";
        
        String result = driver.findElement(By.id("rawContent")).getText();
        
        assertEquals(expResult, result);

        driver.close();
    }
    
    @Test
    public void GetUserByEmail_EmailDoesNotExist_GetUserFails() {
        System.setProperty("webdriver.chrome.driver", driverUrl);
        WebDriver driver = new ChromeDriver();

        driver.get(testServicesUrl);

        driver.findElement(By.linkText("UserDetails")).click();

        driver.findElement(By.id("blobParam")).clear();

        driver.findElement(By.id("blobParam")).sendKeys("{\"email\":\"fail@fail.fail\"}");
        
        driver.findElement(By.linkText("Test")).click();
        
        String expResult = "{\"user_type\":\"admin\",\"question\":\"What is your oldest sibling's middle name?\",\"user_id\":14,\"answer_hash\":\"$2a$12$CAi4ItWkf9y5TDmtvDybyeBwRuOrF3.ZD7ih5OZxVjTCB7mFewXNq\",\"user_fullname\":\"Michael Lawson\",\"email\":\"michael.c.k.lawson@gmail.com\",\"hash\":\"$2a$12$JEV2Q3QjkQUeBt3ppnKdt.tEw3w3CbHXhgwPK5eh.wDYctKp6knCy\",\"has_disabled_badge\":false}";
        
        String result = driver.findElement(By.id("rawContent")).getText();
        
        assertEquals(expResult, result);

        driver.close();
    }
    
    
}
