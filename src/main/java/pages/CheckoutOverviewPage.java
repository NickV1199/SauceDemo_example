package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.math.BigDecimal;
import java.util.List;

public class CheckoutOverviewPage extends BasePage {

    public CheckoutOverviewPage(WebDriver driver) {
        super(driver);
    }

    private final By productNames =
            By.className("inventory_item_name");

    private final By subtotalLabel =
            By.className("summary_subtotal_label");

    private final By taxLabel =
            By.className("summary_tax_label");

    private final By totalLabel =
            By.className("summary_total_label");

    private final By products =
            By.className("cart_item");

    public int getProductsCount() {
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.className("cart_item")
                )
        );
        return driver.findElements(
                By.className("cart_item")
        ).size();
    }

    public String getSubtotal() {
        return getText(subtotalLabel);
    }

    public String getTax() {
        return getText(taxLabel);
    }

    public String getTotal() {
        return getText(totalLabel);
    }

    private final By finishButton = By.id("finish");

    public CheckoutCompletePage finishCheckout() {
        click(finishButton);
        return new CheckoutCompletePage(driver);
    }

    private BigDecimal parsePrice(String text) {
        String price = text.split("\\$")[1].trim();
        return new BigDecimal(price);
    }

    public BigDecimal getSubtotalAmount() {
        return parsePrice(getSubtotal());
    }

    public BigDecimal getTaxAmount() {
        return parsePrice(getTax());
    }

    public BigDecimal getTotalAmount() {
        return parsePrice(getTotal());
    }

    private final By productPrices =
            By.className("inventory_item_price");


    public BigDecimal getProductsTotalPrice() {
        List<WebElement> prices = driver.findElements(productPrices);

        BigDecimal sum = BigDecimal.ZERO;

        for (WebElement price : prices) {
            BigDecimal productPrice = parsePrice(price.getText());
            sum = sum.add(productPrice);
        }
        return sum;
    }




}
