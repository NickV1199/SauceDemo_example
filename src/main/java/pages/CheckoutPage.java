package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage extends BasePage {

    private final By firstNameInput = By.id("first-name");
    private final By lastNameInput = By.id("last-name");
    private final By postalCodeInput = By.id("postal-code");
    private final By continueButton = By.id("continue");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public void fillCustomerInfo(
            String firstName,
            String lastName,
            String postalCode) {

        type(firstNameInput, firstName);
        type(lastNameInput, lastName);
        type(postalCodeInput, postalCode);
    }

    public CheckoutOverviewPage continueCheckout() {
        click(continueButton);
        return new CheckoutOverviewPage(driver);
    }

    private final By errorMessage =
            By.cssSelector("[data-test='error']");

    public String getErrorMessage() {
        return getText(errorMessage);
    }

    public void clickContinue() {
        click(continueButton);
    }



}
