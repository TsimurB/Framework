package hardcode;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static util.Util.click;

public class GoogleCloudPricingCalculator extends AbstractPage {

    private final By choseCalk = By.xpath("//div[@class='gs-title']/a[@class='gs-title' and @href='https://cloud.google.com/products/calculator']");
    public GoogleCloudPricingCalculator(WebDriver driver) {
        super(driver);
    }

//    public hardcode.CalculatorPage initCompEngine() {
//        click(compEngine);
//        return new hardcode.CalculatorPage(driver);
//    }

    public hardcode.CalculatorPage findCalk() {
        click(choseCalk);
        return new hardcode.CalculatorPage(driver);
    }

}
