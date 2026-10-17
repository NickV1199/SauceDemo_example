
package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class InventoryPage extends BasePage {

    private final By addBackpackButton =
            By.id("add-to-cart-sauce-labs-backpack");

    private final By addBikeLightButton =
            By.id("add-to-cart-sauce-labs-bike-light");

    private final By cartBadge =
            By.className("shopping_cart_badge");

    private final By cartButton =
            By.className("shopping_cart_link");

    private final By pageTitle =
            By.className("title");

    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    @Step("Check that inventory page is opened")
    public boolean isInventoryPageOpened() {
        return wait.until(
                ExpectedConditions.urlContains("inventory.html")
        ) && wait.until(
                ExpectedConditions.visibilityOfElementLocated(pageTitle)
        ).getText().equals("Products");
    }

    @Step("Add backpack to cart")
    public void addBackpackToCart() {
        click(addBackpackButton);
    }

    @Step("Add bike light to cart")
    public void addBikeLightToCart() {
        click(addBikeLightButton);
    }

    @Step("Get shopping cart count")
    public int getCartCount() {
        if (driver.findElements(cartBadge).isEmpty()) {
            return 0;
        }

        return Integer.parseInt(getText(cartBadge));
    }

    @Step("Add product to cart: {productId}")
    public void addProductToCart(String productId) {
        By addButton = By.id("add-to-cart-" + productId);
        click(addButton);
    }

    @Step("Remove product from cart: {productId}")
    public void removeProductFromCart(String productId) {
        By removeButton = By.id("remove-" + productId);
        click(removeButton);
    }

    @Step("Open shopping cart")
    public CartPage openCart() {
        click(cartButton);
        return new CartPage(driver);
    }

    @Step("Get inventory page title")
    public String getPageTitle() {
        return getText(pageTitle);
    }
}
