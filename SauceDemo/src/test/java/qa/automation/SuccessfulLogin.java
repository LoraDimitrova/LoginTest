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

public class SuccessfulLogin extends TestUtil {
    @DataProvider(name = "successfulLogins")
    public static Object [][] readUsersFromCsvFile() throws IOException, CsvException {
        return CsvHelper.readCsvFile("src/test/resources/successfulLogins.csv");
    }

    @Test (dataProvider = "successfulLogins")
    public void SuccessfulLogin(String userName, String password) {
        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = loginPage.login(userName, password);

        Assert.assertTrue(productsPage.isHamburgerMenuDisplayed(), "This shall be visible after successful login.");
    }
}