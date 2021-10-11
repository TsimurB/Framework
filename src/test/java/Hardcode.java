import driver.WebDriverProvider;
import hardcode.CalculatorPage;
import hardcode.MainPage;
import hardcode.TenMinutesPage;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import static util.Util.switchToTab;

//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class Hardcode extends AbstractTest {
    private static final Logger logger = LoggerFactory.getLogger(Hardcode.class);
    private CalculatorPage calculatorPage;
    public WebDriver driver;


    @Test
    public void verifyThatSendEmailCorrespondsToTheDataOfTheCalculatorTest() throws Exception {
        //String textForSearchCalkulator = "Google Cloud Platform Pricing Calculator";
        String VMClass = "Regular";
        String instanceType = "n1-standard-8";
        String region = "Frankfurt";
        String lokalSSD = "2x375 GB";
        String commitmentTerm = "1 Year";

        this.calculatorPage = new CalculatorPage(WebDriverProvider.getDriver());
        MainPage mainPage = new MainPage(WebDriverProvider.getDriver());
        CalculatorPage emailEstimatePage = mainPage
                .open()
                .searchPage(textForSearchCalkulator + "\n")
                .findCalk()
                .switchToCalculator()
                .initCompEngine()
                .setNumberOfInstances("4")
                .setOperatingSystemAndSoftware("Free: Debian, CentOS, CoreOS, Ubuntu or BYOL (Bring Your Own License)")
                .setVMClass(VMClass)
                .setInstanceSeries("N1")
                .setInstanceType(instanceType)
                .setCheckboxAddGPUs(1, "NVIDIA Tesla V100")
                .setLocalSSD(lokalSSD)
                .setDatacenterLocation(region)
                .setCommittedUsage(commitmentTerm)
                .createEstimatePage();
//                .createEmailEstimate();

        SoftAssertions assertions = new SoftAssertions();
        assertions.assertThat(emailEstimatePage.getVMClass()).containsIgnoringCase(VMClass);
        assertions.assertThat(emailEstimatePage.getInstanceType())
                .contains(instanceType.replaceAll("\\(.*\\)", "").trim());
        assertions.assertThat(emailEstimatePage.getRegion())
                .contains(region.replaceAll("\\(.*\\)", "").trim());
        assertions.assertThat(emailEstimatePage.getLocalSSD().replaceAll("[GB | GiB]", "").trim())
                .contains(lokalSSD.replaceAll("[GB | GiB]", "").trim());
        assertions.assertThat(emailEstimatePage.getCommitmentTerm())
                .contains(commitmentTerm);
        assertions.assertAll();

//        String totalCostFromCalculatorPage = emailEstimatePage.getTotalCost();
//        String sumOnlyFromTotalCost = totalCostFromCalculatorPage.substring(totalCostFromCalculatorPage.indexOf(":") + 2, totalCostFromCalculatorPage.indexOf("per") - 1);

        String sumOnlyFromTotalCost = emailEstimatePage.getTotalCost();

        TenMinutesPage emailPage = new TenMinutesPage(driver);
        String emailFromBufer = emailPage.openPage()
                .copyEmail();
        switchToTab(0);
//        emailEstimatePage.typeEmail(emailFromBufer);

//        (JavascriptExecutor) driver).executeScript("window.open()");
//        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
//        driver.switchTo().window(tabs.get(1));

//        TenMinutesPage tenMinuteMailPage = new TenMinutesPage(driver);
//        tenMinuteMailPage.openPage()
//                .saveEmailInBuffer();
////        String emailFromBuffer = tenMinuteMailPage.getRandomEmail();
//        driver.switchTo().window(tabs.get(0));

        emailEstimatePage.switchToCalculator()
                .sendEmail(emailFromBufer);

        switchToTab(1);
//        driver.switchTo().window(tabs.get(1));
        emailPage.openLetter();
//                .getTotalSumFromLetter();

        String totalCostFromLetter = emailPage.getTotalSumFromLetter();
        Assert.assertEquals(sumOnlyFromTotalCost, totalCostFromLetter);
    }
}
