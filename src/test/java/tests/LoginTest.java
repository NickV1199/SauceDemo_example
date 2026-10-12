package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTest extends TestBase {

    @Test
    public void loginWithValidCredentials() {

        app.user().login(
                "standard_user",
                "secret_sauce"
        );

        Assert.assertTrue(
                app.getDriver().getCurrentUrl()
                        .contains("inventory.html"),
                "User was not redirected to inventory page"
        );
    }

    @DataProvider(name = "invalidLoginData")
    public Object[][] invalidLoginData() {
        return new Object[][]{
                {
                        "standard_user",
                        "wrong_password",
                        "Username and password do not match"
                },
                {
                        "",
                        "secret_sauce",
                        "Username is required"
                },
                {
                        "locked_out_user",
                        "secret_sauce",
                        "Sorry, this user has been locked out"
                },
                {
                        "standard_user",
                        "",
                        "Password is required"
                }
        };
    }

    @Test(dataProvider = "invalidLoginData")
    public void loginWithInvalidCredentials(
            String username,
            String password,
            String expectedError) {

        app.user().login(username, password);

        String actualError = app.user().getErrorMessage();

        Assert.assertTrue(
                actualError.contains(expectedError),
                "Expected error: " + expectedError
                        + ", but got: " + actualError
        );
    }

}
