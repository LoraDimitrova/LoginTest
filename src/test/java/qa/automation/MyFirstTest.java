package qa.automation;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MyFirstTest {
    private WebDriver driver;

    @BeforeTest
    public void initializeDriver(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

 //   @AfterTest
   // public void tearDown(){
     //   driver.quit();
    //}

    @Test
    public void successfulLoginTest(){
        driver.get("https://www.saucedemo.com/");

        WebElement username = driver.findElement(By.xpath("//*[@id=\"user-name\"]"));
        username.click();
        username.sendKeys("standard_user");

        WebElement password = driver.findElement(By.xpath("//*[@id=\"password\"]"));
        password.click();
        password.sendKeys("secret_sauce");

        WebElement loginButton = driver.findElement(By.cssSelector("[name=login-button]"));
        loginButton.click();

        WebElement userAllPagesButton = driver.findElement(By.id("react-burger-menu-btn"));
        Assert.assertEquals(true,userAllPagesButton.isDisplayed());
    }
}

