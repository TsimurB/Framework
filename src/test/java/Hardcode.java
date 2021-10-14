import driver.WebDriverProvider;
import hardcode.CalculatorPage;
import hardcode.MainPage;
import hardcode.TenMinutesPage;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import service.TestDataReader;

import static util.Util.switchToTab;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class Hardcode extends AbstractTest {
    private static final Logger logger = LoggerFactory.getLogger(Hardcode.class);
    private CalculatorPage calculatorPage;
    public WebDriver driver;


    @Test
    public void verifyThatSendEmailCorrespondsToTheDataOfTheCalculatorTest() throws Exception {
        String textForSearchCalkulator = "text.for.search.calkulator";
        String VMClass = "VM.clas";
        String instanceType = "instance.type";
        String region = "region";
        String lokalSSD = "lokal.SSD";
        String commitmentTerm = "commitment.term";

        this.calculatorPage = new CalculatorPage(WebDriverProvider.getDriver());
        MainPage mainPage = new MainPage(WebDriverProvider.getDriver());
        CalculatorPage emailEstimatePage = mainPage
                .open()
                .searchPage(TestDataReader.getTestData(textForSearchCalkulator) + "\n")
                .findCalk()
                .switchToCalculator()
                .initCompEngine()
                .setNumberOfInstances("4")
                .setOperatingSystemAndSoftware("Free: Debian, CentOS, CoreOS, Ubuntu or BYOL (Bring Your Own License)")
                .setVMClass(TestDataReader.getTestData(TestDataReader.getTestData(VMClass)))
                .setInstanceSeries("N1")
                .setInstanceType(TestDataReader.getTestData(instanceType))
                .setCheckboxAddGPUs(1, "NVIDIA Tesla V100")
                .setLocalSSD(TestDataReader.getTestData(lokalSSD))
                .setDatacenterLocation(TestDataReader.getTestData(region))
                .setCommittedUsage(TestDataReader.getTestData(commitmentTerm))
                .createEstimatePage();

        SoftAssertions assertions = new SoftAssertions();
        assertions.assertThat(emailEstimatePage.getVMClass()).containsIgnoringCase(TestDataReader.getTestData(VMClass));
        assertions.assertThat(emailEstimatePage.getInstanceType())
                .contains(TestDataReader.getTestData(instanceType).replaceAll("\\(.*\\)", "").trim());
        assertions.assertThat(emailEstimatePage.getRegion())
                .contains(TestDataReader.getTestData(region).replaceAll("\\(.*\\)", "").trim());
        assertions.assertThat(emailEstimatePage.getLocalSSD().replaceAll("[GB | GiB]", "").trim())
                .contains(TestDataReader.getTestData(lokalSSD).replaceAll("[GB | GiB]", "").trim());
        assertions.assertThat(emailEstimatePage.getCommitmentTerm())
                .contains(TestDataReader.getTestData(commitmentTerm));
        assertions.assertAll();

        String sumOnlyFromTotalCost = emailEstimatePage.getTotalCost();
        TenMinutesPage emailPage = new TenMinutesPage(driver);
        String emailFromBufer = emailPage.openPage()
                .copyEmail();
        switchToTab(0);
//        emailEstimatePage.typeEmail(emailFromBufer);

        emailEstimatePage.switchToCalculator()
                .sendEmail(emailFromBufer);

        switchToTab(1);
        emailPage.openLetter();

        String totalCostFromLetter = emailPage.getTotalSumFromLetter();
        Assert.assertEquals(sumOnlyFromTotalCost, totalCostFromLetter);
    }
}
