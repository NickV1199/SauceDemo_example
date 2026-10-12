package helper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HelperProduct extends HelperBase {

    public HelperProduct(WebDriver driver) {
        super(driver);
    }

    private final By addBackpackButton =
            By.id("add-to-cart-sauce-labs-backpack");

    private final By cartBadge =
            By.className("shopping_cart_badge");


    public void addBackpackToCart() {
        click(addBackpackButton);
    }

    public String getCartCount() {
        return getText(cartBadge);
    }
}
