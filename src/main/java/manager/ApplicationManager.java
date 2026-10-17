
package manager;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import pages.CartPage;
import pages.LoginPage;
import pages.InventoryPage;

import java.util.Map;

public class ApplicationManager {

    private static final String BASE_URL = "https://www.saucedemo.com/";

    private WebDriver driver;

    private LoginPage loginPage;
    private InventoryPage inventoryPage;
    private CartPage cartPage;

    public void init() {

        ChromeOptions options = new ChromeOptions();

        options.setExperimentalOption("prefs", Map.of(
                "credentials_enable_service", false,
                "profile.password_manager_enabled", false,
                "profile.password_manager_leak_detection", false
        ));

        options.addArguments("--disable-features=PasswordLeakDetection");

        boolean isCI = System.getenv("CI") != null;

        if (isCI) {
            options.addArguments(
                    "--headless=new",
                    "--no-sandbox",
                    "--disable-dev-shm-usage",
                    "--window-size=1920,1080"
            );
        }

        driver = new ChromeDriver(options);

        if (isCI) {
            driver.manage().window().setSize(new Dimension(1920, 1080));
        } else {
            driver.manage().window().maximize();
        }

        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
        cartPage = new CartPage(driver);

        driver.get(BASE_URL);
    }

    public WebDriver getDriver() {
        return driver;
    }

    public LoginPage loginPage() {
        return loginPage;
    }

    public InventoryPage inventoryPage() {
        return inventoryPage;
    }

    public CartPage cartPage() {
        return cartPage;
    }

    public void stop() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
