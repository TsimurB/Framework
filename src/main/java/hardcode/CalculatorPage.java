package hardcode;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static util.Util.click;
import static util.Util.sleep;

public class CalculatorPage extends AbstractPage {

    String selectionPattern = "//md-option/div[contains(text(),'%s')]";

    private final By firstFrame = By.xpath("//iframe[contains(@src,'https://cloud.google.com/products/calculator/index')]");
    private final By secondFrame = By.id("myFrame");
    private final By compEngine = By.xpath("//md-tab-item[@class='md-tab ng-scope ng-isolate-scope md-ink-ripple md-active']//descendant::div[@class='hexagon-in2']");
    //    private final By compEngine = By.xpath("//md-pagination-wrapper//div[@title='Compute Engine']/div[@class='presets-buttons layout-sm-column layout-align-center-center layout-column']//div[@title=\"Compute Engine\"]");
    private final By numberOfInstances = By.xpath("//label[contains(text(),\"Number of instances\")]/following-sibling::input");
    private final By operatingSystemAndSoftwareDDD = By.xpath("//label[contains(text(),\"Operating System / Software\")]/following-sibling::md-select");
    private final By virtualMClassDDD = By.xpath("//label[contains(text(),\"Machine Class\")]/following-sibling::md-select");
    private final By instanceSeriesDDD = By.xpath("//label[text()=\"Series\"]/following-sibling::md-select");
    private final By instanceTypeDDD = By.xpath("//label[text()=\"Machine type\"]/following-sibling::md-select");
    private final By CheckboxAddGPUs = By.xpath("//form[@name=\"ComputeEngineForm\"]//md-checkbox/div[contains(text(),\"Add GPUs\")]");
    private final By numberOfGPUsDDD = By.xpath("//label[text()=\"Number of GPUs\"]/following-sibling::md-select");
    private final By typeGPUsDDD = By.xpath("//label[text()=\"GPU type\"]/following-sibling::md-select");
    private final By localSSD = By.xpath("//label[text()=\"Local SSD\"]/following-sibling::md-select");
    private final By datacenterLocationDDD = By.xpath("//label[text()=\"Datacenter location\"]/following-sibling::md-select");
    private final By committedUsageDDD = By.xpath("//label[text()=\"Committed usage\"]/following-sibling::md-select");
    private final By submitAddToEstimateComputeEngineForm = By.xpath("//form[@name=\"ComputeEngineForm\"]//button[@class=\"md-raised md-primary cpc-button md-button md-ink-ripple\"]");
    private final By submitEmailEstimate = By.xpath("//button[contains(text(),\"Email Estimate\")]");
    private final By buttonEmailEstimate = By.xpath("//md-card-content[@id='resultBlock']//button[@id='email_quote']");
    private final By fieldForEmail = By.xpath("//div[@class='md-dialog-container ng-scope']//md-input-container//label[contains(text(), 'Email')]//following-sibling::input");
    private final By buttonSendEmail = By.xpath("//md-dialog-actions[@class='layout-row']//button[@class='md-raised md-primary cpc-button md-button md-ink-ripple']");
//    private final By estimatedCostResult = By.xpath("//md-list-item[@class='md-1-line md-no-proxy']/div[@class='md-list-item-text']/b[@class='ng-binding']");
    @FindBy(xpath = "//md-list-item[@class='md-1-line md-no-proxy']/div[@class='md-list-item-text']/b[@class='ng-binding']")
    private WebElement estimatedCostResult;

    private final By virtualMClass = By.xpath("//div[contains(text(),\"VM class: \")]");
    private final By instanceType = By.xpath("//div[contains(text(),\"Instance type: \")]");
    private final By region = By.xpath("//div[contains(text(),\"Region: \")]");
    private final By localSSDcheck = By.xpath("//div[contains(text(),\"local SSD \")]");
    private final By commitmentTerm = By.xpath("//div[contains(text(),\"Commitment term: \")]");

    public CalculatorPage(WebDriver driver) {
        super(driver);
    }

    public hardcode.CalculatorPage switchToCalculator() {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(firstFrame));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(secondFrame));
        //        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@src,'https://cloud.google.com/products/calculator/index')]")));
//        driver.switchTo().frame(driver.findElement(By.id("myFrame")));
        return this;
    }

    public hardcode.CalculatorPage initCompEngine() {
        click(compEngine);
        return this;
    }

    public hardcode.CalculatorPage setNumberOfInstances(String text) {
        driver.findElement(numberOfInstances).sendKeys(text);
        return this;
    }

    public hardcode.CalculatorPage setOperatingSystemAndSoftware(String text) {

        click(operatingSystemAndSoftwareDDD);
//        driver.findElements(By.xpath(String.format("//md-option/div[contains(text(),'%s')]", text))).click();
        click(By.xpath(String.format(selectionPattern, text)));
        return this;
    }

    public hardcode.CalculatorPage setVMClass(String text) {
        click(virtualMClassDDD);
        driver.findElements(By.xpath(String.format(selectionPattern, text))).get(1).click();
        return this;
    }

    public hardcode.CalculatorPage setInstanceSeries(String text) {
        click(instanceSeriesDDD);
        click(By.xpath(String.format(selectionPattern, text)));
        return this;
    }

    public hardcode.CalculatorPage setInstanceType(String type) {
        click(instanceTypeDDD);
        click(By.xpath(String.format(selectionPattern, type)));
        return this;
    }

    public hardcode.CalculatorPage setCheckboxAddGPUs(Integer number, String type) {
        click(CheckboxAddGPUs);
        click(numberOfGPUsDDD);
        click(By.xpath(String.format("//md-option[contains(@ng-repeat,\"supportedGpuNumbers\")]/div[contains(text(),'%s')]", number)));
        click(typeGPUsDDD);
        click(By.xpath(String.format("//md-option[contains(@ng-repeat,\"gpuList\")]/div[contains(text(),'%s')]", type)));
        return this;
    }

    public hardcode.CalculatorPage setLocalSSD(String type) {
        click(localSSD);
        click(By.xpath(String.format(selectionPattern, type)));
        return this;
    }

    public hardcode.CalculatorPage setDatacenterLocation(String type) {
        click(datacenterLocationDDD);
        click(By.xpath(String.format("//md-select-menu[@class]//md-option[contains(@ng-repeat,\"fullRegionList\")]/div[contains(text(),'%s')]", type)));
        return this;
    }

    public hardcode.CalculatorPage setCommittedUsage(String type) {
        driver.findElement(committedUsageDDD).sendKeys(type);
//        click(By.xpath(String.format("//md-option/div[contains(text(),'%s')]", type)));
        clickAway();
        return this;
    }

    public hardcode.CalculatorPage createEstimatePage() {
        click(submitAddToEstimateComputeEngineForm);
        return this;
    }

    public hardcode.EmailEstimatePage createEmailEstimate() {

        click(submitEmailEstimate);
//        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@src,'https://cloud.google.com/products/calculator/index')]")));
//        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@src,'https://cloudpricingcalculator.appspot.com?hl=ru')]")));
        return new hardcode.EmailEstimatePage(driver);
    }

    public String getVMClass() {
        return driver.findElement(virtualMClass).getText();
    }

    public String getInstanceType() {
        return driver.findElement(instanceType).getText();
    }

    public String getRegion() {
        return driver.findElement(region).getText();
    }

    public String getLocalSSD() {
        return driver.findElement(localSSDcheck).getText();
    }

    public String getCommitmentTerm() {
        return driver.findElement(commitmentTerm).getText();
    }

    public String getTotalCost() {
        return estimatedCostResult.getText();
    }

    public CalculatorPage sendEmail(String email) {
        wait.until(ExpectedConditions.elementToBeClickable(buttonEmailEstimate)).click();
        wait.until(ExpectedConditions.elementToBeClickable(fieldForEmail)).click();
        driver.findElement(fieldForEmail).sendKeys(email);
        wait.until(ExpectedConditions.elementToBeClickable(buttonSendEmail)).click();
        return this;
    }

    private void clickAway() {
        sleep(200);
        WebElement body = driver.findElement(By.tagName("body"));
        body.click();
        sleep(500);
    }
}
