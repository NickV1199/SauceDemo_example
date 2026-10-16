package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InventoryPage extends BasePage {

    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    private final By addBackpackButton =
            By.id("add-to-cart-sauce-labs-backpack");

    private final By addBikeLightButton =
            By.id("add-to-cart-sauce-labs-bike-light");

    private final By cartBadge =
            By.className("shopping_cart_badge");

    public void addBackpackToCart() {
        click(addBackpackButton);
    }

    public void addBikeLightToCart() {
        click(addBikeLightButton);
    }

    public int getCartCount() {
        if (driver.findElements(cartBadge).isEmpty()) {
            return 0;
        }
        return Integer.parseInt(getText(cartBadge));
    }

    public void addProductToCart(String productId) {
        By addButton = By.id("add-to-cart-" + productId);
        click(addButton);
    }

    public void removeProductFromCart(String productId) {
        By removeButton = By.id("remove-" + productId);
        click(removeButton);
    }

    private final By cartButton =
            By.className("shopping_cart_link");

    public CartPage openCart() {
        click(cartButton);
        return new CartPage(driver);
    }

    private final By pageTitle = By.className("title");

    public String getPageTitle() {
        return getText(pageTitle);
    }

}

