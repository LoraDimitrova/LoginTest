package qa.automation;

import base.TestUtil;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductsPage;

public class ProblemUserProductsFilter extends TestUtil {

    @Test
    public void selectDifferentOrderStandardUser() {
        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = loginPage.login("standard_user", "secret_sauce");

        Assert.assertFalse(productsPage.areProductsSortedByPriceAsc(), "Products should not be sorted by price ASC");

        productsPage.sortByPriceAsc();

        Assert.assertTrue(productsPage.areProductsSortedByPriceAsc(), "Products should be sorted by price ASC");
    }

    @Test
    public void selectDifferentOrderProblemUser() {
        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = loginPage.login("problem_user", "secret_sauce");

        Assert.assertFalse(productsPage.areProductsSortedByPriceAsc(), "Products should not be sorted by price ASC");

        productsPage.sortByPriceAsc();

        // This will fail
        Assert.assertTrue(productsPage.areProductsSortedByPriceAsc(), "Products should be sorted by price ASC");
    }
}