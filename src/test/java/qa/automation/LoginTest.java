package qa.automation;

import base.TestUtil;
import com.opencsv.exceptions.CsvException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.CsvHelper;

import java.io.IOException;

public class LoginTest extends TestUtil {

    @DataProvider(name="wrongUserList")
    public Object [][] getWrongUsers(){
        return new Object[][]{
                {"standardUser7", "secret_sauce"},
                {"standard_user", "wrong paassword"},
                {"blah", "blah"}

        };
    }

    @DataProvider (name = "csvUserList")
    public static  Object [][] readUsersFromCsvFile() throws IOException, CsvException {
        return CsvHelper.readCsvFile("src/test/resources/users.csv");

    }

    @Test (dataProvider = "wrongUserList")
    public void unsuccessfulLogin(String userName,String password){
        //driver.get("https://www.saucedemo.com/");

        WebElement username = driver.findElement(By.xpath("//*[@id=\"user-name\"]"));
        username.click();
        username.sendKeys(userName);

        WebElement passwordInput = driver.findElement(By.xpath("//*[@id=\"password\"]"));
        passwordInput.click();
        passwordInput.sendKeys(password);

        WebElement loginButton = driver.findElement(By.cssSelector("[name=login-button]"));
        loginButton.click();

        WebElement errorLoginLabel = driver.findElement(By.xpath("//*[text()='Epic sadface: Username and password do not match any user in this service']"));

        Assert.assertTrue(errorLoginLabel.isDisplayed());
    }

    @Test(dataProvider = "csvUserList")
    public void SuccessfulLogin(String userName,String password){
       // driver.get("https://www.saucedemo.com/");

        WebElement username = driver.findElement(By.xpath("//*[@id=\"user-name\"]"));
        username.click();
        username.sendKeys(userName);

        WebElement passwordInput = driver.findElement(By.xpath("//*[@id=\"password\"]"));
        passwordInput.click();
        passwordInput.sendKeys(password);

        WebElement loginButton = driver.findElement(By.cssSelector("[name=login-button]"));
        loginButton.click();

        WebElement userAllPagesButton = driver.findElement(By.id("react-burger-menu-btn"));
        Assert.assertEquals(true,userAllPagesButton.isDisplayed());
    }
}
