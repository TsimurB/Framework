package hardcode;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static util.Util.click;

public class MainPage extends AbstractPage {
    private static final String PAGE_ADDRESS = "https://cloud.google.com/";
    private final By buttonSearch = By.xpath("//div[@class='devsite-search-container']");
    private final By search = By.xpath("//input[@class='devsite-search-field devsite-search-query']");

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public hardcode.MainPage open() {
        driver.get(PAGE_ADDRESS);
        return this;
    }

    public hardcode.GoogleCloudPricingCalculator searchPage(String text) {
        click(buttonSearch);
        driver.findElement(search).sendKeys(text);
        return new hardcode.GoogleCloudPricingCalculator(driver);
    }
}
