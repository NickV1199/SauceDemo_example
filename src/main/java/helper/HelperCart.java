package helper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

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

    private final By removeBackpackButton =
            By.id("remove-sauce-labs-backpack");

    public void removeBackpack() {
        click(removeBackpackButton);
    }

    public boolean isCartEmpty() {
        return !isElementPresent(productName);
    }

    public int getProductsCount() {
        List<WebElement> products =
                driver.findElements(productName);
        return products.size();
    }

    public List<String> getProductNames() {
        List<WebElement> products =
                driver.findElements(productName);
        List<String> names = new ArrayList<>();
        for (WebElement product : products) {
            names.add(product.getText());
        }
        return names;
    }

    public void removeProduct(String productId) {
        By removeButton = By.id("remove-" + productId);
        click(removeButton);
    }

    public void removeProductByName(String productName) {
        By removeButton = By.xpath(
                "//div[@class='cart_item']" +
                        "[.//div[text()='" + productName + "']]" +
                        "//button"
        );
        click(removeButton);
    }

}
