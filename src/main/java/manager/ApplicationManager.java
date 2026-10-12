package manager;

import helper.HelperUser;
import helper.HelperProduct;
import helper.HelperCart;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ApplicationManager {

    private WebDriver driver;

    private HelperUser user;
    private HelperProduct product;
    private HelperCart cart;

    public void init(){
        driver = new ChromeDriver();
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

}
