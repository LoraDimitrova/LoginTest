package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class CheckoutOverviewPage {

    protected WebDriver driver;
    private static final String FINISH_LOCATOR = "//*[@id='finish']";

    public CheckoutOverviewPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public SuccessPage openSuccessPage(){
        WebElement continueShoppingLink = driver.findElement(By.xpath(FINISH_LOCATOR));
        continueShoppingLink.click();

        return new SuccessPage(driver);
    }
}