package tests;

import io.qameta.allure.Allure;
import listeners.AllureListener;
import manager.ApplicationManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

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
            if (result.getStatus() == ITestResult.FAILURE && getDriver() != null) {
                byte[] screenshot = ((TakesScreenshot) getDriver())
                        .getScreenshotAs(OutputType.BYTES);

                Allure.addAttachment(
                        "Screenshot on failure",
                        "image/png",
                        new ByteArrayInputStream(screenshot),
                        ".png"
                );
            }
        } finally {
            if (app != null) {
                app.stop();
            }
        }
    }
}