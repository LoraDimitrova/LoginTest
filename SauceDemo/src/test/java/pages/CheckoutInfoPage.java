package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutInfoPage {

    protected WebDriver driver;
    private static final String CONTINUE_LOCATOR = "//*[@id='continue']";

    @FindBy(id = "first-name")
    private WebElement firstNameInput;

    @FindBy(id = "last-name")
    private WebElement lastNameInput;

    @FindBy(id = "postal-code")
    private WebElement postalCodeInput;

    public CheckoutInfoPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void fillInfo(String firstName, String lastName, String postalCode){
        firstNameInput.click();
        firstNameInput.sendKeys(firstName);

        lastNameInput.click();
        lastNameInput.sendKeys(lastName);

        postalCodeInput.click();
        postalCodeInput.sendKeys(postalCode);
    }

    public CheckoutOverviewPage openCheckoutOverviewPage(){
        WebElement continueShoppingLink = driver.findElement(By.xpath(CONTINUE_LOCATOR));
        continueShoppingLink.click();

        return new CheckoutOverviewPage(driver);
    }
}