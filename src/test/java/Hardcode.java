import driver.WebDriverProvider;
import hardcode.CalculatorPage;
import hardcode.MainPage;
import hardcode.TenMinutesPage;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.ArrayList;

import static util.Util.switchToTab;

//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class Hardcode extends AbstractTest {
    private CalculatorPage calculatorPage;
    public WebDriver driver;


    @Test
    public void verifyThatSendEmailCorrespondsToTheDataOfTheCalculatorTest() throws IOException, UnsupportedFlavorException {
        String TEXTFORSEARCHCALKULATOR = "Google Cloud Platform Pricing Calculator";
        String VMCLASS = "Regular";
        String INSTANCETYPE = "n1-standard-8 (vCPUs: 8, RAM: 30GB)";
        String REGION = "Frankfurt (europe-west3)";
        String LOCALSSD = "2x375 GB";
        String COMMITMENTTERM = "1 Year";

        this.calculatorPage = new CalculatorPage(WebDriverProvider.getDriver());
        MainPage mainPage = new MainPage(WebDriverProvider.getDriver());
        CalculatorPage emailEstimatePage = mainPage
                .open()
                .searchPage(TEXTFORSEARCHCALKULATOR + "\n")
                .findCalk()
                .switchToCalculator()
                .initCompEngine()
                .setNumberOfInstances("4")
                .setOperatingSystemAndSoftware("Free: Debian, CentOS, CoreOS, Ubuntu or BYOL (Bring Your Own License)")
                .setVMClass(VMCLASS)
                .setInstanceSeries("N1")
                .setInstanceType(INSTANCETYPE)
                .setCheckboxAddGPUs(1, "NVIDIA Tesla V100")
                .setLocalSSD(LOCALSSD)
                .setDatacenterLocation(REGION)
                .setCommittedUsage(COMMITMENTTERM)
                .createEstimatePage();

        SoftAssertions assertions = new SoftAssertions();
        assertions.assertThat(calculatorPage.getVMClass()).containsIgnoringCase(VMCLASS);
        assertions.assertThat(calculatorPage.getInstanceType())
                .contains(INSTANCETYPE.replaceAll("\\(.*\\)", "").trim());
        assertions.assertThat(calculatorPage.getRegion())
                .contains(REGION.replaceAll("\\(.*\\)", "").trim());
        assertions.assertThat(calculatorPage.getLocalSSD().replaceAll("[GB | GiB]", "").trim())
                .contains(LOCALSSD.replaceAll("[GB | GiB]", "").trim());
        assertions.assertThat(calculatorPage.getCommitmentTerm())
                .contains(COMMITMENTTERM);
        assertions.assertAll();

        String totalCostFromCalculatorPage = emailEstimatePage.getTotalCost();
        String sumOnlyFromTotalCost = totalCostFromCalculatorPage.substring(totalCostFromCalculatorPage.indexOf(":") + 2, totalCostFromCalculatorPage.indexOf("per") - 1);

        TenMinutesPage emailPage = new TenMinutesPage(WebDriverProvider.getDriver());
        String email = emailPage.openPage()
                .copyEmail();
        switchToTab(0);
        emailEstimatePage.typeEmail(email);



//        (JavascriptExecutor) driver).executeScript("window.open()");
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));

//        TenMinutesPage tenMinuteMailPage = new TenMinutesPage(driver);
//        tenMinuteMailPage.openPage()
//                .saveEmailInBuffer();
//
//        String emailFromBuffer = tenMinuteMailPage.getRandomEmail();

//        driver.switchTo().window(tabs.get(0));

        emailEstimatePage.switchToCalculator()
                .sendEmail(emailFromBuffer);

        driver.switchTo().window(tabs.get(1));

        emailPage.openLetter()
                .getTotalSumFromLetter();

        String totalCostFromLetter = emailPage.getTotalSumFromLetter();

        assertions.assertThat(sumOnlyFromTotalCost.equals(totalCostFromLetter));


    }
}
