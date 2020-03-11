/*
 * Made By
 * Name: Michael Lawson
 * Student Number: D00185184
 */
package webdriver;


import static org.junit.Assert.*;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
/**
 *
 * @author USER
 */
public class TestRegisterUsersCar {
    @Test
    public void RegisterUsersCar_Pass() {
        
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\USER\\Downloads\\chromedriver_win32 (2)\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String baseUrl = "http://localhost:33986/parkinator/test-resbeans.html";
        driver.get(baseUrl);

        driver.findElement(By.linkText("car")).click();

        WebElement dropDownElement = driver.findElement(By.id("methodSel"));
        
        Select dropDown = new Select(dropDownElement);
        
        dropDown.selectByValue("POST(text/plain)[2]");
        
        driver.findElement(By.id("blobParam")).clear();

        driver.findElement(By.id("blobParam")).sendKeys("{\"car_model\":\"dfgdfg\",\"user_id\":22,\"car_reg\":\"dfgd\",\"car_id\":6,\"car_colour\":\"dfdf\",\"car_make\":\"dfgdfg\"}");
        
        driver.findElement(By.linkText("Test")).click();
        
        String expResult = "true";
        
        String result = driver.findElement(By.id("rawContent")).getText();
        
        assertEquals(expResult, result);

        driver.close();
        
    }
    
    @Test
    public void RegisterUsersCar_Fail() {
        
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\USER\\Downloads\\chromedriver_win32 (2)\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String baseUrl = "http://localhost:33986/parkinator/test-resbeans.html";
        driver.get(baseUrl);

        driver.findElement(By.linkText("car")).click();

        WebElement dropDownElement = driver.findElement(By.id("methodSel"));
        
        Select dropDown = new Select(dropDownElement);
        
        dropDown.selectByValue("POST(text/plain)[2]");
        
        driver.findElement(By.id("blobParam")).clear();

        driver.findElement(By.id("blobParam")).sendKeys("{\"car_model\":\"dfgdfg\",\"user_id\":-1,\"car_reg\":\"dfgd\",\"car_id\":6,\"car_colour\":\"dfdf\",\"car_make\":\"dfgdfg\"}");
        
        driver.findElement(By.linkText("Test")).click();
        
        String expResult = "false";
        
        String result = driver.findElement(By.id("rawContent")).getText();
        
        assertEquals(expResult, result);

        driver.close();
        
    }
}
