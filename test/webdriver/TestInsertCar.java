/*
 * Made By
 * Name: Michael Lawson
 * Student Number: D00185184
 */
package webdriver;


import java.util.ResourceBundle;
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
public class TestInsertCar {
    ResourceBundle config = ResourceBundle.getBundle("webdriver.WDConfig");
        String driverUrl = config.getString("driverUrl");
        String testServicesUrl = config.getString("testServicesUrl");
    
    @Test
    public void InsertCar_CorrectDetails_Pass() {
        
        System.setProperty("webdriver.chrome.driver", driverUrl);
        WebDriver driver = new ChromeDriver();

        driver.get(testServicesUrl);

        driver.findElement(By.linkText("car")).click();

        WebElement dropDownElement = driver.findElement(By.id("methodSel"));
        
        Select dropDown = new Select(dropDownElement);
        
        dropDown.selectByVisibleText("POST(text/plain)");
        
        driver.findElement(By.id("blobParam")).clear();

        driver.findElement(By.id("blobParam")).sendKeys("{\"car_model\":\"dfgdfg\",\"user_id\":14,\"car_reg\":\"dfgd\",\"car_id\":6,\"car_colour\":\"dfdf\",\"car_make\":\"dfgdfg\"}");
        
        driver.findElement(By.linkText("Test")).click();
        
        String expResult = "true";
        
        String result = driver.findElement(By.id("rawContent")).getText();
        
        assertEquals(expResult, result);

        driver.close();
        
    }
    
    @Test
    public void InsertCar_userDoesNotExist_RegisterFails() {
        
        System.setProperty("webdriver.chrome.driver", driverUrl);
        WebDriver driver = new ChromeDriver();

        driver.get(testServicesUrl);

        driver.findElement(By.linkText("car")).click();

        WebElement dropDownElement = driver.findElement(By.id("methodSel"));
        
        Select dropDown = new Select(dropDownElement);
        
        dropDown.selectByVisibleText("POST(text/plain)");
        
        driver.findElement(By.id("blobParam")).clear();

        driver.findElement(By.id("blobParam")).sendKeys("{\"car_model\":\"dfgdfg\",\"user_id\":-1,\"car_reg\":\"dfgd\",\"car_id\":6,\"car_colour\":\"dfdf\",\"car_make\":\"dfgdfg\"}");
        
        driver.findElement(By.linkText("Test")).click();
        
        String expResult = "false";
        
        String result = driver.findElement(By.id("rawContent")).getText();
        
        assertEquals(expResult, result);

        driver.close();
        
    }
}
