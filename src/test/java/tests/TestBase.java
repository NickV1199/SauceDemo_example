package tests;

import manager.ApplicationManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class TestBase {

    protected ApplicationManager app;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        app = new ApplicationManager();
        app.init();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (app != null) {
            app.stop();
        }
    }
}