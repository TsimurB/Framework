package hardcode;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static util.Util.click;

public class GoogleCloudPricingCalculator extends AbstractPage {
    private final Logger logger = LogManager.getRootLogger();
    private final By choseCalk = By.xpath("//div[@class='gs-title']/a[@class='gs-title' and @href='https://cloud.google.com/products/calculator']");
    public GoogleCloudPricingCalculator(WebDriver driver) {
        super(driver);
    }

    public hardcode.CalculatorPage findCalk() {
        click(choseCalk);
        logger.info("Calculator page found");
        return new hardcode.CalculatorPage(driver);
    }
}
