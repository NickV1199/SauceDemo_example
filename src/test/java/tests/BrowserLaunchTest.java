package tests;

import manager.ApplicationManager;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BrowserLaunchTest {

    @Test
    public void openDemotest() {
        ApplicationManager app = new ApplicationManager();

        try {
            app.init();
            Assert.assertEquals(
                    app.getDriver().getTitle(),
                    "Swag Labs"
            );
        } finally {
            app.stop();
        }
    }


}
