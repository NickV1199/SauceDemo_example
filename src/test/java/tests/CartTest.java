package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

import java.util.List;

public class CartTest extends TestBase {

    @Test
    public void addBackpackToCart() {

        app.loginPage().login("standard_user", "secret_sauce");

        app.inventoryPage().addBackpackToCart();

        Assert.assertEquals(
                app.inventoryPage().getCartCount(),
                1,
                "Cart should contain one product"
        );
    }


    @Test
    public void verifyProductInCart() {

        app.loginPage().login("standard_user",
                "secret_sauce");

        app.inventoryPage().addProductToCart("sauce-labs-backpack");

        CartPage cartPage = app.inventoryPage().openCart();

        Assert.assertTrue(
                cartPage.getProductNames().contains("Sauce Labs Backpack"),
                "Backpack was not found in cart"
        );
    }


    @Test
    public void removeBackpackFromCart() {

        app.loginPage().login("standard_user",
                "secret_sauce");

        app.inventoryPage().addProductToCart("sauce-labs-backpack");

        CartPage cartPage = app.inventoryPage().openCart();

        Assert.assertTrue(
                cartPage.getProductNames().contains("Sauce Labs Backpack"),
                "Backpack was not added to cart"
        );

        cartPage.removeProduct("sauce-labs-backpack");

        Assert.assertTrue(
                cartPage.isCartEmpty(),
                "Cart is not empty after removing the product"
        );
    }


    @Test
    public void addTwoProductsToCart() {

        app.loginPage().login("standard_user", "secret_sauce");

        app.inventoryPage().addProductToCart("sauce-labs-backpack");
        app.inventoryPage().addProductToCart("sauce-labs-bike-light");

        Assert.assertEquals(
                app.inventoryPage().getCartCount(),
                2,
                "Cart badge should show 2 products"
        );

        CartPage cartPage = app.inventoryPage().openCart();

        Assert.assertEquals(
                cartPage.getProductsCount(),
                2,
                "Cart should contain 2 products"
        );
    }


    @Test
    public void verifyTwoProductNamesInCart() {

        app.loginPage().login("standard_user", "secret_sauce");

        app.inventoryPage().addProductToCart("sauce-labs-backpack");
        app.inventoryPage().addProductToCart("sauce-labs-bike-light");

        CartPage cartPage = app.inventoryPage().openCart();

        List<String> actualNames = cartPage.getProductNames();

        Assert.assertEquals(
                actualNames.size(),
                2,
                "Cart should contain exactly 2 products"
        );

        Assert.assertTrue(
                actualNames.contains("Sauce Labs Backpack"),
                "Backpack was not found in cart"
        );

        Assert.assertTrue(
                actualNames.contains("Sauce Labs Bike Light"),
                "Bike Light was not found in cart"
        );
    }


    @Test
    public void removeOneOfTwoProducts() {

        app.loginPage().login("standard_user", "secret_sauce");

        app.inventoryPage().addProductToCart("sauce-labs-backpack");
        app.inventoryPage().addProductToCart("sauce-labs-bike-light");

        CartPage cartPage = app.inventoryPage().openCart();

        Assert.assertEquals(
                cartPage.getProductsCount(),
                2,
                "Cart should initially contain 2 products"
        );

        cartPage.removeProductByName("Sauce Labs Backpack");

        Assert.assertEquals(
                cartPage.getProductsCount(),
                1,
                "Cart should contain 1 product after removal"
        );

        Assert.assertTrue(
                cartPage.getProductNames().contains("Sauce Labs Bike Light"),
                "Bike Light should remain in cart"
        );

        Assert.assertFalse(
                cartPage.getProductNames().contains("Sauce Labs Backpack"),
                "Backpack should be removed from cart"
        );
    }


    @Test
    public void removeProductByNameTest() {

        app.loginPage().login("standard_user", "secret_sauce");

        app.inventoryPage().addProductToCart("sauce-labs-backpack");
        app.inventoryPage().addProductToCart("sauce-labs-bike-light");

        CartPage cartPage = app.inventoryPage().openCart();

        cartPage.removeProductByName("Sauce Labs Backpack");

        Assert.assertEquals(
                cartPage.getProductsCount(),
                1,
                "Cart should contain one product"
        );

        Assert.assertTrue(
                cartPage.getProductNames().contains("Sauce Labs Bike Light"),
                "Bike Light should remain in cart"
        );
    }

    @Test
    public void emptyCartCountTest() {
        app.loginPage().login("standard_user",
                "secret_sauce");

        Assert.assertEquals(
                app.inventoryPage().getCartCount(),
                0
        );
    }

    @Test
    public void addTwoProductsWithUniversalMethodTest(){
        app.loginPage().login("standard_user",
                "secret_sauce");

        app.inventoryPage().addProductToCart("sauce-labs-backpack");
        app.inventoryPage().addProductToCart("sauce-labs-bike-light");

        Assert.assertEquals(
                app.inventoryPage().getCartCount(),
                2,
                "Cart should contain two products"
        );
    }

    @Test
    public void addTwoAndRemoveOneProductWithUniversalMethodTest(){
        app.loginPage().login("standard_user",
                "secret_sauce");

        app.inventoryPage().addProductToCart("sauce-labs-backpack");
        app.inventoryPage().addProductToCart("sauce-labs-bike-light");

        Assert.assertEquals(
                app.inventoryPage().getCartCount(),
                2,
                "Cart should contain two products"
        );

        app.inventoryPage().removeProductFromCart("sauce-labs-backpack");

        Assert.assertEquals(
                app.inventoryPage().getCartCount(),
                1,
                "Cart should contain one product"
        );

    }

    @Test
    public void addTwoProductsCheckCart(){
        app.loginPage().login("standard_user",
                "secret_sauce");

        app.inventoryPage().addProductToCart("sauce-labs-backpack");
        app.inventoryPage().addProductToCart("sauce-labs-bike-light");

        app.inventoryPage().openCart();

        Assert.assertEquals(
                app.cartPage().getProductsCount(),
                2,
                "Cart should contain two products"
        );

    }



    @Test
    public void verifyProductNamesInCartTest() {
        app.loginPage().login("standard_user",
                "secret_sauce");

        app.inventoryPage().addProductToCart("sauce-labs-backpack");
        app.inventoryPage().addProductToCart("sauce-labs-bike-light");

        app.inventoryPage().openCart();

        Assert.assertEquals(
                app.cartPage().getProductsCount(),
                2,
                "Cart should contain two products"
        );

        Assert.assertTrue(
                app.cartPage().getProductNames().contains("Sauce Labs Backpack")
        );

        Assert.assertTrue(
                app.cartPage().getProductNames().contains("Sauce Labs Bike Light")
        );
    }

    @Test
    public void removeProductFromCartTest(){
        app.loginPage().login("standard_user",
                "secret_sauce");

        app.inventoryPage().addProductToCart("sauce-labs-backpack");
        app.inventoryPage().addProductToCart("sauce-labs-bike-light");

        app.inventoryPage().openCart();

        Assert.assertEquals(
                app.cartPage().getProductsCount(),
                2,
                "Cart should contain two products"
        );

        app.cartPage().removeProduct("sauce-labs-backpack");


        Assert.assertEquals(
                app.cartPage().getProductsCount(),
                1,
                "Cart should contain one product"
        );

        Assert.assertTrue(
                app.cartPage().getProductNames().contains("Sauce Labs Bike Light"),
                "Bike Light should remain in the cart"
        );

        Assert.assertFalse(
                app.cartPage().getProductNames().contains("Sauce Labs Backpack"),
                "Backpack should not be in the cart"
        );


    }

    @Test
    public void removeAllProductsFromCartTest(){
        app.loginPage().login("standard_user", "secret_sauce");

        app.inventoryPage().addProductToCart("sauce-labs-backpack");
        app.inventoryPage().addProductToCart("sauce-labs-bike-light");

        CartPage cartPage = app.inventoryPage().openCart();

        Assert.assertEquals(
                cartPage.getProductsCount(),
                2
        );

        cartPage.removeProduct("sauce-labs-backpack");
        cartPage.removeProduct("sauce-labs-bike-light");

        Assert.assertEquals(
                cartPage.getProductsCount(),
                0
        );
    }


    @Test
    public void continueShoppingTest() {

        app.loginPage().login("standard_user",
                "secret_sauce");

        app.inventoryPage().addProductToCart("sauce-labs-backpack");

        CartPage cartPage = app.inventoryPage().openCart();

        Assert.assertEquals(
                cartPage.getProductsCount(),
                1
        );

        InventoryPage inventoryPage = cartPage.continueShopping();

        Assert.assertEquals(
                inventoryPage.getPageTitle(),
                "Products",
                "Inventory page should be opened"
        );

        Assert.assertEquals(
                inventoryPage.getCartCount(),
                1
        );

        inventoryPage.addProductToCart("sauce-labs-bike-light");

        Assert.assertEquals(
                inventoryPage.getCartCount(),
                2
        );
    }




}

