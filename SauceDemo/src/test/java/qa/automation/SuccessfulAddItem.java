package qa.automation;

import base.TestUtil;
import com.opencsv.exceptions.CsvException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductsPage;
import utils.CsvHelper;
import java.io.IOException;

public class SuccessfulAddItem extends TestUtil {

    @DataProvider(name = "addItemsToCart")
    public static Object [][] readUsersFromCsvFile() throws IOException, CsvException {
        return CsvHelper.readCsvFile("src/test/resources/addItemsToCart.csv");
    }

    @Test (dataProvider = "addItemsToCart")
    public void addItemToTheCart(String userName, String password, String productOne, String productTwo, String productThree, String productFour, String productFive){
        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = loginPage.login(userName, password);

        if (productOne.length() != 0) {
            productsPage.addItemToTheCart(productOne);
            Assert.assertEquals(productsPage.getItemsInTheCart(),1, "Because we have only one item so far");
        }
        if (productTwo.length() != 0) {
            productsPage.addItemToTheCart(productTwo);
            Assert.assertEquals(productsPage.getItemsInTheCart(),2, "Because we have two items so far");
        }
        if (productThree.length() != 0) {
            productsPage.addItemToTheCart(productThree);
            Assert.assertEquals(productsPage.getItemsInTheCart(),3, "Because we have three items so far");
        }
        if (productFour.length() != 0) {
            productsPage.addItemToTheCart(productFour);
            Assert.assertEquals(productsPage.getItemsInTheCart(),4, "Because we have four items so far");
        }
        if (productFive.length() != 0) {
            productsPage.addItemToTheCart(productFive);
            Assert.assertEquals(productsPage.getItemsInTheCart(),5, "Because we have five items so far");
        }

        if (productOne.length() != 0) {
            productsPage.removeItemFromTheCart(productOne);
        }
        if (productTwo.length() != 0) {
            productsPage.removeItemFromTheCart(productTwo);
        }
        if (productThree.length() != 0) {
            productsPage.removeItemFromTheCart(productThree);
        }
        if (productFour.length() != 0) {
            productsPage.removeItemFromTheCart(productFour);
        }
        if (productFive.length() != 0) {
            productsPage.removeItemFromTheCart(productFive);
        }

        Assert.assertEquals(productsPage.getItemsInTheCart(),0, "Shopping cart should be empty");
    }
}