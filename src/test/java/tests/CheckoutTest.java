package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.CheckoutCompletePage;
import pages.CheckoutOverviewPage;
import pages.CheckoutPage;

import java.math.BigDecimal;

public class CheckoutTest extends TestBase {

    @Test(groups = {"smoke", "regression"})
    public void checkoutOverviewTest() {

        app.loginPage().login(
                "standard_user",
                "secret_sauce"
        );

        app.inventoryPage().addProductToCart("sauce-labs-backpack");
        app.inventoryPage().addProductToCart("sauce-labs-bike-light");

        CartPage cartPage = app.inventoryPage().openCart();

        CheckoutPage checkoutPage = cartPage.checkout();

        checkoutPage.fillCustomerInfo(
                "John",
                "Smith",
                "12345"
        );

        CheckoutOverviewPage overviewPage =
                checkoutPage.continueCheckout();

        Assert.assertEquals(
                overviewPage.getProductsCount(),
                2,
                "Cart should contain 2 products"
        );

        BigDecimal productsTotal = overviewPage.getProductsTotalPrice();
        BigDecimal subtotal = overviewPage.getSubtotalAmount();

        Assert.assertEquals(
                productsTotal.compareTo(subtotal),
                0,
                "Subtotal should equal sum of product prices"
        );

        BigDecimal tax = overviewPage.getTaxAmount();
        BigDecimal total = overviewPage.getTotalAmount();

        BigDecimal expectedTotal = subtotal.add(tax);

        Assert.assertEquals(
                total.compareTo(expectedTotal),
                0,
                "Total should equal subtotal plus tax"
        );

        CheckoutCompletePage completePage =
                overviewPage.finishCheckout();

        Assert.assertEquals(
                completePage.getConfirmationMessage(),
                "Thank you for your order!",
                "Order confirmation message is incorrect"
        );
    }


    @DataProvider(name = "invalidCheckoutData")
    public Object[][] invalidCheckoutData() {
        return new Object[][] {
                {
                        "", "Smith", "12345",
                        "Error: First Name is required"
                },
                {
                        "John", "", "12345",
                        "Error: Last Name is required"
                },
                {
                        "John", "Smith", "",
                        "Error: Postal Code is required"
                },
                {
                        "", "", "",
                        "Error: First Name is required"
                }
        };
    }

    @Test(
            dataProvider = "invalidCheckoutData",
            groups = {"regression"}
    )
    public void invalidCheckoutTest(
            String firstName,
            String lastName,
            String postalCode,
            String expectedError
    ) {

        app.loginPage().login(
                "standard_user",
                "secret_sauce"
        );

        app.inventoryPage()
                .addProductToCart("sauce-labs-backpack");

        CartPage cartPage =
                app.inventoryPage().openCart();

        CheckoutPage checkoutPage =
                cartPage.checkout();

        checkoutPage.fillCustomerInfo(
                firstName,
                lastName,
                postalCode
        );

        checkoutPage.clickContinue();

        Assert.assertEquals(
                checkoutPage.getErrorMessage(),
                expectedError
        );
    }



}
