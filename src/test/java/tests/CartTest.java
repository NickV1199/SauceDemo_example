package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CartTest extends TestBase {

    @Test
    public void addBackpackToCart() {

        app.user().login(
                "standard_user",
                "secret_sauce"
        );

        app.product().addBackpackToCart();

        Assert.assertEquals(
                app.product().getCartCount(),
                "1",
                "Cart should contain one product"
        );
    }


    @Test
    public void verifyProductInCart() {

        app.user().login(
                "standard_user",
                "secret_sauce"
        );

        app.product().addBackpackToCart();

        app.cart().openCart();

        String actualProductName =
                app.cart().getProductName();

        Assert.assertEquals(
                actualProductName,
                "Sauce Labs Backpack",
                "Incorrect product in cart"
        );
    }






}
