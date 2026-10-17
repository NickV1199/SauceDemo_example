package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutCompletePage extends BasePage {

    public CheckoutCompletePage(WebDriver driver) {
        super(driver);
    }

    private final By completeHeader =
            By.className("complete-header");

    public String getConfirmationMessage() {
        return getText(completeHeader);
    }

}
