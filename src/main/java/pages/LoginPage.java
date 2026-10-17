package pages;

import io.qameta.allure.Step;
import io.qameta.allure.Param;
import io.qameta.allure.model.Parameter;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {

    private final By usernameField = By.id("user-name");
    private final By passwordField = By.id("password");
    private final By loginButton = By.id("login-button");
    private final By errorMessage = By.cssSelector("[data-test='error']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Login as user: {username}")
    public void login(
            String username,
            @Param(mode = Parameter.Mode.MASKED) String password) {

        type(usernameField, username);
        type(passwordField, password);
        click(loginButton);
    }

    @Step("Get login error message")
    public String getErrorMessage() {
        return getText(errorMessage);
    }

    @Step("Check that login form is displayed")
    public boolean isLoginFormDisplayed() {

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(usernameField)
        ).isDisplayed()

                && wait.until(
                ExpectedConditions.visibilityOfElementLocated(passwordField)
        ).isDisplayed()

                && wait.until(
                ExpectedConditions.visibilityOfElementLocated(loginButton)
        ).isDisplayed();
    }
}
