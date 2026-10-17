package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

    public List<String> getProductNames() {
        List<WebElement> elements = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(productNames)
        );

        List<String> names = new ArrayList<>();

        for (WebElement element : elements) {
            names.add(element.getText());
        }

        return names;
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



}
