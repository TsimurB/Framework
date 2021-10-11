package hardcode;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class EmailEstimatePage extends AbstractPage{

    private final Logger logger = LogManager.getRootLogger();
    public static final String TAB_NAME="Google Cloud Platform";
    private By emailField = By.xpath("//input[@type=\"email\"]");

    public EmailEstimatePage(WebDriver driver) { super(driver);}

    public hardcode.EmailEstimatePage typeEmail(String email){
        driver.findElement(emailField).sendKeys(email);
        logger.info("Switced to email field");
        return this;
    }
}
