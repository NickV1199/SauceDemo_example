package manager;

import helper.HelperUser;
import helper.HelperProduct;
import helper.HelperCart;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.Map;

import pages.CartPage;
import pages.LoginPage;
import pages.InventoryPage;

public class ApplicationManager {

    private WebDriver driver;

    private HelperUser user;
    private HelperProduct product;
    private HelperCart cart;

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

        user = new HelperUser(driver);
        product = new HelperProduct(driver);
        cart = new HelperCart(driver);
    }

    public WebDriver getDriver(){
        return driver;
    }

    public HelperUser user(){
        return user;
    }

    public HelperProduct product() {
        return product;
    }

    public HelperCart cart() {
        return cart;
    }

    public void stop(){
        if (driver != null){
            driver.quit();
            driver = null;
        }
    }

    private LoginPage loginPage;

    public LoginPage loginPage() {
        return loginPage;
    }

    private InventoryPage inventoryPage;

    public InventoryPage inventoryPage() {
        return inventoryPage;
    }

    private CartPage cartPage;

    public CartPage cartPage() {
        return cartPage;
    }


}
