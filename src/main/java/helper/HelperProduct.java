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

    private final By addBikeLightButton =
            By.id("add-to-cart-sauce-labs-bike-light");

    public void addBackpackToCart() {
        click(addBackpackButton);
    }

    public void addBikeLightToCart() {
        click(addBikeLightButton);
    }

    public String getCartCount() {
        return getText(cartBadge);
    }



}
