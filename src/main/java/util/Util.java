package util;

import driver.WebDriverProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class Util {
    private static final String EMAIL_PAGE_ADDRESS = "https://tempmail.plus/ru/#!";
    public static void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

//    public static String getPageTitle() {
//        return getDriver().getTitle();
//    }

    public static void click(By locator) {
        getWait(3).until(driver -> ExpectedConditions.elementToBeClickable(locator));
        WebElement element1 = WebDriverProvider.getDriver().findElement(locator);
        element1.click();
        sleep(800);
    }

    private static Wait getWait(int seconds) {
        return new FluentWait<>(WebDriverProvider.getDriver())
                .withTimeout(Duration.ofSeconds(seconds))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(Exception.class);
    }

    public static void openTab(String address) {
        ((JavascriptExecutor) WebDriverProvider.getDriver()).executeScript(String.format("window.open('%s');",address));
    }

    public static void switchToTab(int tabIndex){
        List<String> requireTab = WebDriverProvider.getDriver().getWindowHandles().stream()
                .collect(Collectors.toList());
        WebDriverProvider.getDriver().switchTo().window(requireTab.get(tabIndex));
    }
}
