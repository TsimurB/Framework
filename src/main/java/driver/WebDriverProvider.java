package driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverProvider {
    private static WebDriver driver;

    private WebDriverProvider() {
    }

    public static WebDriver getDriver() {
        if (null == driver) {
            switch (System.getProperty("browser")) {
                case "firefox": {
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                }
                default: {
                    System.out.println("rhfbnehwfjkm" + System.getProperty("browser"));
                    WebDriverManager.chromiumdriver().setup();
                    ChromeOptions options = new ChromeOptions();
//                    options.addArguments("--headless");
                    driver = new ChromeDriver(options);
                }
            }
            driver.manage().window().maximize();
        }
        return driver;
    }

    public static void closeDriver() {
        driver.quit();
        driver = null;
    }
}
