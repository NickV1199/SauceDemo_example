package tests;

import helper.HelperBase;
import manager.ApplicationManager;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HelperBaseTest {

    @Test
    public void checkLoginElements() {

        ApplicationManager app = new ApplicationManager();

        try {
            app.init();

            HelperBase helper = new HelperBase(app.getDriver());

            Assert.assertTrue(
                    helper.isElementPresent(By.id("user-name"))
            );

            Assert.assertTrue(
                    helper.isElementPresent(By.id("password"))
            );

            Assert.assertTrue(
                    helper.isElementPresent(By.id("login-button"))
            );

        } finally {
            app.stop();
        }
    }
}
