package manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import pages.CartPage;
import pages.LoginPage;
import pages.InventoryPage;

import java.util.Map;

public class ApplicationManager {

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

        options.addArguments(
                "--disable-features=PasswordLeakDetection"
        );

        if (System.getenv("CI") != null) {
            options.addArguments(
                    "--headless=new",
                    "--no-sandbox",
                    "--disable-dev-shm-usage",
                    "--window-size=1920,1080"
            );
        }

        driver = new ChromeDriver(options);

        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
        cartPage = new CartPage(driver);

        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
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
