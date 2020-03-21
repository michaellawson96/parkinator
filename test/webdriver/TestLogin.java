/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webdriver;

import static org.junit.Assert.*;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 *
 * @author snake
 */
public class TestLogin {

    @Test
    public void Login_Pass() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Lukas\\Desktop\\chromedriver_win32 (1)\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String baseUrl = "http://localhost:41919/parkinator/test-resbeans.html";
        driver.get(baseUrl);

        driver.findElement(By.linkText("Login")).click();

        driver.findElement(By.id("blobParam")).clear();

        driver.findElement(By.id("blobParam")).sendKeys("{\"email\":\"test1@email.com\", \"hash\":\"TestPassword1\"}");
        
        driver.findElement(By.linkText("Test")).click();
        
        String expResult = "true";
        
        String result = driver.findElement(By.id("rawContent")).getText();
        
        assertEquals(expResult, result);

        driver.close();
    }
    
    @Test
    public void Login_fail() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Lukas\\Desktop\\chromedriver_win32 (1)\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String baseUrl = "http://localhost:41919/parkinator/test-resbeans.html";
        driver.get(baseUrl);

        driver.findElement(By.linkText("Login")).click();

        driver.findElement(By.id("blobParam")).clear();

        driver.findElement(By.id("blobParam")).sendKeys("{\"email\":\"Notgoingtowork@gmail.com\", \"hash\":\"Testinguser1\"}");
        
        driver.findElement(By.linkText("Test")).click();
        
        String expResult = "false";
        
        String result = driver.findElement(By.id("rawContent")).getText();
        
        assertEquals(expResult, result);

        driver.close();
    }
    
    
}
