package hardcode;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    protected WebDriver driver;
    private final Integer TIMEOUT = 5;
    public BasePage clickIn(WebElement element) {
        new WebDriverWait(driver, TIMEOUT)
                .until(ExpectedConditions.elementToBeClickable(element))
                .click();
        return this;

    public BasePage fillIn(WebElement element, String textToFillIn) {
        WebElement typeIn = new WebDriverWait(driver, TIMEOUT)
                .until(ExpectedConditions.visibilityOf(element));
        clickIn(element);
        typeIn.sendKeys(textToFillIn);
        return this;
    }
}
