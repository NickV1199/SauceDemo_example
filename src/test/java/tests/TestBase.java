
package tests;

import io.qameta.allure.Allure;
import manager.ApplicationManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.ByteArrayInputStream;

public class TestBase {

    protected ApplicationManager app;

    public WebDriver getDriver() {
        return app != null ? app.getDriver() : null;
    }

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        app = new ApplicationManager();
        app.init();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        try {
            WebDriver driver = getDriver();

            if (result.getStatus() == ITestResult.FAILURE
                    && driver instanceof TakesScreenshot) {

                try {
                    byte[] screenshot =
                            ((TakesScreenshot) driver)
                                    .getScreenshotAs(OutputType.BYTES);

                    Allure.addAttachment(
                            "Screenshot on failure",
                            "image/png",
                            new ByteArrayInputStream(screenshot),
                            ".png"
                    );

                } catch (Exception e) {
                    System.err.println(
                            "Failed to capture screenshot: "
                                    + e.getMessage()
                    );
                }
            }
        } finally {
            if (app != null) {
                app.stop();
            }
        }
    }
}
