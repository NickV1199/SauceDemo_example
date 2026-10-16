package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;

public class LoginTest extends TestBase {

    @Test
    public void loginWithValidCredentials() {

        app.loginPage().login(
                "standard_user",
                "secret_sauce");

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

        app.loginPage().login(username, password);

        String actualError = app.loginPage().getErrorMessage();

        Assert.assertTrue(
                actualError.contains(expectedError),
                "Expected error: " + expectedError
                        + ", but got: " + actualError
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

    @Test(dataProvider = "invalidCredentials")
    public void invalidLoginTest(
            String username,
            String password,
            String expectedError) {

        app.loginPage().login(username, password);

        String actualError = app.loginPage().getErrorMessage();

        Assert.assertEquals(
                actualError,
                expectedError,
                "Error message does not match"
        );
    }






    @Test
    public void loginWithInvalidPasswordTest(){
        app.loginPage().login(
                "standard_user",
                "wrong_password");

        String actualError = app.loginPage().getErrorMessage();
        String expectedError =
                "Epic sadface: Username and password do not match any user in this service";

        Assert.assertEquals(
                actualError,
                expectedError,
                "Error message should match"
        );
    }


    @Test
    public void loginWithInvalidUsernameTest(){
        app.loginPage().login(
                "wrong_user",
                "secret_sauce");

        String actualError = app.loginPage().getErrorMessage();
        String expectedError =
                "Epic sadface: Username and password do not match any user in this service";

        Assert.assertEquals(
                actualError,
                expectedError,
                "Error message should match"
        );


    }


}
