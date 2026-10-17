package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import io.qameta.allure.Step;
import io.qameta.allure.Param;
import io.qameta.allure.model.Parameter;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    private final By usernameField = By.id("user-name");
    private final By passwordField = By.id("password");
    private final By loginButton = By.id("login-button");


    @Step("Login as user: {username}")
    public void login(
            String username,
            @Param(mode = Parameter.Mode.MASKED) String password){
        type(usernameField, username);
        type(passwordField, password);
        click(loginButton);
    }

    private final By errorMessage =
            By.cssSelector("[data-test='error']");


    public String getErrorMessage() {
        return getText(errorMessage);
    }


}
