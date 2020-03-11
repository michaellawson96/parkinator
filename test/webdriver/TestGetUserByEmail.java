/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webdriver;

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
    @Test
    public void GetUserByEmail() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\snake\\OneDrive - Dundalk Institute of Technology\\Attachments\\Web Testing\\chromedriver_win32 (1)\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String baseUrl = "http://localhost:8080/parkinator/test-resbeans.html";
        driver.get(baseUrl);

        driver.findElement(By.linkText("UserDetails")).click();

        driver.findElement(By.id("blobParam")).clear();

        driver.findElement(By.id("blobParam")).sendKeys("{\"email\":\"testinguser1@gmail.com\"}");
        
        driver.findElement(By.linkText("Test")).click();
        
        String expResult = "true";
        
        String result = driver.findElement(By.id("rawContent")).getText();
        
        assertEquals(expResult, result);

        driver.close();
    }
    
    
}
