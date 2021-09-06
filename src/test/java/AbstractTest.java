import driver.WebDriverProvider;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class AbstractTest {
    WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void openDriver() {
        driver = new ChromeDriver();
//        driver = WebDriverProvider.getDriver();
        driver.manage().window().maximize();
    }

    @AfterMethod(alwaysRun = true)
    public void closeDriver() {
        WebDriverProvider.getDriver().close();
        WebDriverProvider.closeDriver();
    }

//    @BeforeMethod(alwaysRun = true)
//    public void browserSetup() {
//        driver = new ChromeDriver();
//        driver.manage().window().maximize();
//    }

//    @AfterMethod(alwaysRun = true)
//    public void browserTearDown() {
//        driver.quit();
//        driver = null;
//    }
}
