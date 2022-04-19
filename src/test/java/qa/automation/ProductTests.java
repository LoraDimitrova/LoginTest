package qa.automation;

import base.TestUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductsPage;

import java.time.Duration;
import java.util.Collections;
import java.util.NoSuchElementException;

public class ProductTests extends TestUtil {


//        FluentWait fluentWait = new FluentWait(driver)
//                .withTimeout(Duration.ofSeconds(5))
//                .pollingEvery(Duration.ofSeconds(2))
//                .ignoreAll(Collections.singleton(NoSuchElementException.class));
//        WebElement lowToHighPriceOption = driver.findElement(By.cssSelector("[value=lohi]"));
//
//        fluentWait.until(ExpectedConditions.elementToBeClickable(lowToHighPriceOption));
//        lowToHighPriceOption.click();



    @Test
    public void addItemToTheCart(){
        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = loginPage.login("standard_user", "secret_sauce");
        String[] products = {"backpack","bike-light","bolt-t-shirt","fleece-jacket","onesie"};
        for (int i = 0; i < products.length; i++) {
            productsPage.addItemToTheCart(products[i]);

        }


        Assert.assertEquals(productsPage.getItemsInTheCart(),5,"Because we have only one item so far");
    }
}
