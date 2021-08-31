package driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class WebDriverProvider {
    private static WebDriver driver;

    private WebDriverProvider() {
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            if ("firefox".equals(System.getProperty("browser"))) {
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            }
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
                    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            driver.manage().window().maximize();
        }
        return driver;
    }

//    public static void closeDriver() {
//        driver.quit();
//        driver = null;
//    }
}
