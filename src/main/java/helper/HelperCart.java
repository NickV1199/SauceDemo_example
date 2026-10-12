package helper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HelperCart extends HelperBase {

    public HelperCart(WebDriver driver) {
        super(driver);
    }

    private final By cartIcon =
            By.className("shopping_cart_link");

    private final By productName =
            By.className("inventory_item_name");

    public void openCart() {
        click(cartIcon);
    }

    public String getProductName() {
        return getText(productName);
    }
}
