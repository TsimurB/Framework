import driver.WebDriverProvider;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;
import util.TestListeners;

@Listeners({TestListeners.class})
public class AbstractTest {
    private static final Logger logger = LoggerFactory.getLogger(AbstractTest.class);
    protected WebDriver driver;

    @Parameters("browser")
    @BeforeSuite()
    public void setUp(String browser){
        logger.debug("Test set up");
//        System.out.println("fhghjtytu " + System.getProperty("browser"));
//        System.setProperty("browser", "chrome");
    }

    @BeforeMethod(alwaysRun = true)
    public void openDriver() {
        logger.debug("Test open driver");
        driver = WebDriverProvider.getDriver();
        driver.manage().window().maximize();
    }

    @AfterMethod(alwaysRun = true)
    public void closeDriver() {
        logger.info("Test close driver");
        WebDriverProvider.getDriver().close();
        WebDriverProvider.closeDriver();
    }
}
