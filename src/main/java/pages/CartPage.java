package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;


import java.util.ArrayList;
import java.util.List;

public class CartPage extends BasePage {

    public CartPage(WebDriver driver) {
        super(driver);
    }

    private final By cartItems = By.className("cart_item");

    private final By productNames =
            By.className("inventory_item_name");

    public int getProductsCount() {
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.id("cart_contents_container")
                )
        );

        return driver.findElements(
                By.className("cart_item")
        ).size();
    }

    @Step("Get product names from cart")
    public List<String> getProductNames() {
        return wait.until(driver -> {
            List<WebElement> elements = driver.findElements(productNames);

            if (elements.isEmpty()) {
                return null;
            }

            List<String> names = new ArrayList<>();

            try {
                for (WebElement element : elements) {
                    if (!element.isDisplayed()) {
                        return null;
                    }

                    names.add(element.getText());
                }

                return names;

            } catch (StaleElementReferenceException e) {
                return null;
            }
        });
    }

    @Step("Remove product from cart: {productId}")
    public void removeProduct(String productId) {
        By removeButton = By.id("remove-" + productId);
        click(removeButton);
    }

    private final By continueShoppingButton =
            By.id("continue-shopping");

    public InventoryPage continueShopping() {
        click(continueShoppingButton);
        return new InventoryPage(driver);
    }

    private final By checkoutButton = By.id("checkout");

    @Step("Proceed to checkout")
    public CheckoutPage checkout() {
        click(checkoutButton);
        return new CheckoutPage(driver);
    }

    @Step("Check if cart is empty")
    public boolean isCartEmpty() {
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.id("cart_contents_container")
                )
        );

        return driver.findElements(cartItems).isEmpty();
    }

    @Step("Remove product by name: {productName}")
    public void removeProductByName(String productName) {

        By removeButton = By.xpath(
                "//div[contains(@class,'cart_item')]" +
                        "[.//div[contains(@class,'inventory_item_name') " +
                        "and normalize-space()='" + productName + "']]" +
                        "//button"
        );

        wait.until(
                ExpectedConditions.elementToBeClickable(removeButton)
        ).click();
    }

}
