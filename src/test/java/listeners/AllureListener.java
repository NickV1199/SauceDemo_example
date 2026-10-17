package listeners;

import io.qameta.allure.Allure;
import io.qameta.allure.AllureLifecycle;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;
import tests.TestBase;

import java.io.ByteArrayInputStream;
import java.util.Optional;

public class AllureListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {

        Object testInstance = result.getInstance();

        if (!(testInstance instanceof TestBase)) {
            return;
        }

        WebDriver driver = ((TestBase) testInstance).getDriver();

        if (driver == null) {
            return;
        }

        try {
            byte[] screenshot = ((TakesScreenshot) driver)
                    .getScreenshotAs(OutputType.BYTES);

            AllureLifecycle lifecycle = Allure.getLifecycle();

            Optional<String> testUuid = lifecycle.getCurrentTestCase();

            if (testUuid.isPresent()) {
                lifecycle.addAttachment(
                        "Screenshot on failure",
                        "image/png",
                        ".png",
                        new ByteArrayInputStream(screenshot)
                );

                System.out.println("Screenshot attached to test: " + testUuid.get());
            } else {
                System.out.println("Allure test UUID not found");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}