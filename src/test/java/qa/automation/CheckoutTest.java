package qa.automation;

import base.TestUtil;
import com.opencsv.exceptions.CsvException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.*;
import utils.CsvHelper;

import java.io.IOException;

public class CheckoutTest extends TestUtil {
    @DataProvider(name = "csvCheckout")
    public static Object [][] readUsersFromCsvFile() throws IOException, CsvException {
        return CsvHelper.readCsvFile("src/test/resources/checkoutInfo.csv");
    }

    @Test(dataProvider = "csvCheckout")
    public void successfulCheckout(String userName, String password, String productOne, String productTwo, String firstName, String lastName, String postalCode){
        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = loginPage.login(userName, password);

        productsPage.addItemToTheCart(productOne);
        productsPage.addItemToTheCart(productTwo);

        CartPage cartPage = productsPage.openCartPage();

        CheckoutInfoPage checkoutinfoPage = cartPage.openCheckoutPage();
        checkoutinfoPage.fillInfo(firstName, lastName, postalCode);
        CheckoutOverviewPage checkoutOverviewPage = checkoutinfoPage.openCheckoutOverviewPage();

        SuccessPage successPage = checkoutOverviewPage.openSuccessPage();
        Assert.assertTrue(successPage.isSuccessMessageDisplayed(), "The success message is visible");
    }
}