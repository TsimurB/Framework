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
    private static final String EMAIL_PAGE_ADDRESS = "https://tempmail.plus/ru/#!";
    public static final String TAB_NAME = "Временная электронная почта";
    private By copyEmailButton = By.id("pre_copy");
    @FindBy(xpath = "//div[@class='message_top']")
    private WebElement buttonOpenLetter;
    @FindBy(xpath = "//tr[@id='mobilepadding']//td[2]/h3")
    private WebElement totalEstimatedMonthlyCost;
    private final By quantityLettersBy = By.xpath("//span[@id='inbox_count_number']");

    public TenMinutesPage(WebDriver driver) {
        super(driver);
    }

    public TenMinutesPage openPage() {
        openTab(EMAIL_PAGE_ADDRESS);
        switchToTab(1);
        return this;
    }

    public String copyEmail() throws Exception{
        click(copyEmailButton);
        return (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
    }

    public TenMinutesPage openLetter() {
        wait.until(ExpectedConditions.textToBe(quantityLettersBy, "1"));
        wait.until(ExpectedConditions.elementToBeClickable(buttonOpenLetter)).click();
        return this;
    }

    public String getTotalSumFromLetter() {
        return totalEstimatedMonthlyCost.getText();
    }
}
