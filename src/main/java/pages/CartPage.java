package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class CartPage extends BasePage {

    public CartPage(WebDriver driver) {
        super(driver);
    }


    private final By productNames =
            By.className("inventory_item_name");

    public int getProductsCount() {
        return driver.findElements(productNames).size();
    }

    public List<String> getProductNames() {
        List<WebElement> elements = driver.findElements(productNames);
        List<String> names = new ArrayList<>();

        for (WebElement element : elements) {
            names.add(element.getText());
        }

        return names;
    }


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



}
