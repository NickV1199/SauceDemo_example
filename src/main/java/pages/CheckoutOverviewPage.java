package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.math.BigDecimal;
import java.util.List;

public class CheckoutOverviewPage extends BasePage {

    private final By products =
            By.className("cart_item");

    private final By productPrices =
            By.className("inventory_item_price");

    private final By subtotalLabel =
            By.className("summary_subtotal_label");

    private final By taxLabel =
            By.className("summary_tax_label");

    private final By totalLabel =
            By.className("summary_total_label");

    private final By finishButton =
            By.id("finish");

    public CheckoutOverviewPage(WebDriver driver) {
        super(driver);
    }

    @Step("Get products count on checkout overview")
    public int getProductsCount() {
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(products)
        );

        return driver.findElements(products).size();
    }

    @Step("Get subtotal")
    public String getSubtotal() {
        return getText(subtotalLabel);
    }

    @Step("Get tax")
    public String getTax() {
        return getText(taxLabel);
    }

    @Step("Get total")
    public String getTotal() {
        return getText(totalLabel);
    }

    private BigDecimal parsePrice(String text) {
        String price = text.split("\\$")[1].trim();
        return new BigDecimal(price);
    }

    @Step("Get subtotal amount")
    public BigDecimal getSubtotalAmount() {
        return parsePrice(getSubtotal());
    }

    @Step("Get tax amount")
    public BigDecimal getTaxAmount() {
        return parsePrice(getTax());
    }

    @Step("Get total amount")
    public BigDecimal getTotalAmount() {
        return parsePrice(getTotal());
    }

    @Step("Calculate total price of products")
    public BigDecimal getProductsTotalPrice() {

        List<WebElement> prices = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(
                        productPrices
                )
        );

        BigDecimal sum = BigDecimal.ZERO;

        for (WebElement price : prices) {
            BigDecimal productPrice = parsePrice(price.getText());
            sum = sum.add(productPrice);
        }

        return sum;
    }

    @Step("Finish checkout")
    public CheckoutCompletePage finishCheckout() {
        click(finishButton);
        return new CheckoutCompletePage(driver);
    }
}
