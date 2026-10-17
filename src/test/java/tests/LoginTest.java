package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTest extends TestBase {

    @Test(groups = {"regression"})
    public void checkLoginElements() {

        Assert.assertTrue(
                app.loginPage().isLoginFormDisplayed(),
                "Login form elements were not found"
        );
    }

    @Test(groups = {"smoke", "regression"})
    public void loginWithValidCredentials() {

        app.loginPage().login(
                "standard_user",
                "secret_sauce"
        );

        Assert.assertTrue(
                app.getDriver().getCurrentUrl()
                        .contains("inventory.html"),
                "User was not redirected to inventory page"
        );
    }

    @DataProvider(name = "invalidCredentials")
    public Object[][] invalidCredentials() {
        return new Object[][]{
                {
                        "standard_user",
                        "wrong_password",
                        "Epic sadface: Username and password do not match any user in this service"
                },
                {
                        "wrong_user",
                        "secret_sauce",
                        "Epic sadface: Username and password do not match any user in this service"
                },
                {
                        "",
                        "secret_sauce",
                        "Epic sadface: Username is required"
                },
                {
                        "locked_out_user",
                        "secret_sauce",
                        "Epic sadface: Sorry, this user has been locked out."
                },
                {
                        "standard_user",
                        "",
                        "Epic sadface: Password is required"
                },
                {
                        "",
                        "",
                        "Epic sadface: Username is required"
                }
        };
    }

    @Test(
            dataProvider = "invalidCredentials",
            groups = {"regression"}
    )
    public void invalidLoginTest(
            String username,
            String password,
            String expectedError
    ) {

        app.loginPage().login(username, password);

        String actualError = app.loginPage().getErrorMessage();

        Assert.assertEquals(
                actualError,
                expectedError,
                "Error message does not match"
        );
    }
}
