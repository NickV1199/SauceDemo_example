package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage extends BasePage {

    private final By firstNameInput = By.id("first-name");
    private final By lastNameInput = By.id("last-name");
    private final By postalCodeInput = By.id("postal-code");
    private final By continueButton = By.id("continue");
    private final By errorMessage =
            By.cssSelector("[data-test='error']");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    @Step("Fill customer information")
    public void fillCustomerInfo(
            String firstName,
            String lastName,
            String postalCode) {

        type(firstNameInput, firstName);
        type(lastNameInput, lastName);
        type(postalCodeInput, postalCode);
    }

    @Step("Continue to checkout overview")
    public CheckoutOverviewPage continueCheckout() {
        click(continueButton);
        return new CheckoutOverviewPage(driver);
    }

    @Step("Click Continue button")
    public void clickContinue() {
        click(continueButton);
    }

    @Step("Get checkout error message")
    public String getErrorMessage() {
        return getText(errorMessage);
    }
}
