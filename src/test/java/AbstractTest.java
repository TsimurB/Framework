import driver.WebDriverProvider;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import util.TestListeners;


@Listeners({TestListeners.class})
public class AbstractTest {
    protected WebDriver driver;

    @BeforeSuite()
    public void setUp(){
        System.out.println("fhghjtytu " + System.getProperty("browser"));
//        System.setProperty("browser", "chrome");
    }

    @BeforeMethod(alwaysRun = true)
    public void openDriver() {
        driver = WebDriverProvider.getDriver();
        driver.manage().window().maximize();
    }

    @AfterMethod(alwaysRun = true)
    public void closeDriver() {
        WebDriverProvider.getDriver().close();
        WebDriverProvider.closeDriver();
    }
}
