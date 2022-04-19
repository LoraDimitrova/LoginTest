package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;

public class CartPage {

    protected WebDriver driver;
    private static final String CHECKOUT_LOCATOR = "//*[@id='checkout']";
    private static final String REMOVE_FROM_CART_LOCATOR = "//button[@id='remove-sauce-labs-%s']";

    @FindBy (className = "shopping_cart_link")
    private WebElement shoppingCartLink;

    @FindBy (className = "shopping_cart_badge")
    private WebElement shoppingCartCounter;

    public CartPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public CheckoutInfoPage openCheckoutPage(){
        WebElement checkoutLink = driver.findElement(By.xpath(CHECKOUT_LOCATOR));
        checkoutLink.click();

        return new CheckoutInfoPage(driver);
    }

    public boolean removeItemFromTheCart(String productName){
        String xpathOfElementToBeRemoved = String.format(REMOVE_FROM_CART_LOCATOR, productName);
        FluentWait fluentWait = new FluentWait(driver)
                .withTimeout(Duration.ofSeconds(3));

        WebElement removeButton = driver.findElement(By.xpath(xpathOfElementToBeRemoved));
        fluentWait.until(ExpectedConditions.elementToBeClickable(removeButton));

        if (removeButton.getText().equals("REMOVE")) {
            removeButton.click();
            return true;
        }

        return false;
    }

    public int getItemsInTheCart(){
        if (driver.findElements(By.className("shopping_cart_badge")).isEmpty()) {
            return 0;
        }

        return Integer.parseInt(shoppingCartCounter.getText());
    }
}