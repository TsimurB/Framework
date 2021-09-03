package hardcode;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;

import static util.Util.*;

public class TenMinutesPage extends AbstractPage {
    //    private ChromeDriver driver;
    private static final String EMAIL_PAGE_ADDRESS = "https://tempmail.plus/ru/#!";
    public static final String TAB_NAME = "Временная электронная почта";
    private By copyEmailButton = By.id("pre_copy");
//    @FindBy(xpath = "//div[@id='copy_address']/span")
//    private WebElement buttonForSaveRandomEmail;
    @FindBy(xpath = "//div[@class='message_top']")
    private WebElement buttonOpenLetter;
    @FindBy(xpath = "//tr[@id='mobilepadding']//td[2]/h3")
    private WebElement totalEstimatedMonthlyCost;
    private final By quantityLettersBy = By.xpath("//span[@id='inbox_count_number']");

    public TenMinutesPage(WebDriver driver) {
        super(driver);
//        this.driver = driver;
    }

    public TenMinutesPage openPage() {
        openTab(EMAIL_PAGE_ADDRESS);
        switchToTab(1);
        return this;
    }

    public String copyEmail() {
        click(copyEmailButton);
        try {
            return (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

//    public TenMinutesPage saveEmailInBuffer() {
//        buttonForSaveRandomEmail.click();
//        return this;
//    }
//
//    public String getRandomEmail() throws IOException, UnsupportedFlavorException {
//        return (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
//    }

    public TenMinutesPage openLetter() {
        wait.until(ExpectedConditions.textToBe(quantityLettersBy, "1"));
        wait.until(ExpectedConditions.elementToBeClickable(buttonOpenLetter)).click();
        return this;
    }

    public String getTotalSumFromLetter() {
        return totalEstimatedMonthlyCost.getText();
    }

}
