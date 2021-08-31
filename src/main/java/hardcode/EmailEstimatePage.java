package hardcode;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class EmailEstimatePage extends AbstractPage{
public static final String TAB_NAME="Google Cloud Platform";
private By emailField = By.xpath("//input[@type=\"email\"]");

    public EmailEstimatePage(WebDriver driver) {
        super(driver);
    }

    public EmailEstimatePage typeEmail(String email){

        driver.findElement(emailField).sendKeys(email);
        return this;
    }

}
