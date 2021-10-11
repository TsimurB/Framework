package driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
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
//                default: {
//                    System.out.println("rhfbnehwfjkm " + System.getProperty("browser"));
//                    WebDriverManager.chromiumdriver().setup();
//                    ChromeOptions options = new ChromeOptions();
//                    options.addArguments("--headless");
//                    options.addArguments("--no-sandbox");
//                    options.setExperimentalOption("useAutomationExtension", false);
//                    options.addArguments("--disable-dev-shm-usage");
//                    options.addArguments("--disable-notifications---");
//                    options.addArguments("--disable-setuid-sandbox");
//                    driver = new ChromeDriver(options);
//                }
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
