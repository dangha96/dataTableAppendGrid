package pageFactory;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public abstract class AbtractPage {
    private WebDriverWait explicitWait;
    private WebElement element;
    private long longTimeOut = 30000;
    Alert alert;

    public String getCurrentUrl(WebDriver driver) {
        return driver.getCurrentUrl();
    }


    public void openUrl(WebDriver driver, String Url) {
        driver.get(Url);
        driver.manage().timeouts().implicitlyWait(longTimeOut, TimeUnit.SECONDS);
    }

    public void waitToElementVisible(WebDriver driver, WebElement element) {
        explicitWait = new WebDriverWait(driver, longTimeOut);
        explicitWait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitToElementInVisible(WebDriver driver, String locator) {
        explicitWait = new WebDriverWait(driver, longTimeOut);
        explicitWait.until(ExpectedConditions.invisibilityOf(element));
    }

    public void waitToElementClickable(WebDriver driver, String locator) {
        explicitWait = new WebDriverWait(driver, longTimeOut);
        explicitWait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void clickToElement(WebDriver driver, WebElement element) {
       element.click();
    }

    public void sendKeyToElement(WebDriver driver, WebElement element, String value) {
       element.clear();
       element.sendKeys(value);
    }

    public String getElementText(WebDriver driver, WebElement element) {
        return element.getText();
    }










    public void waitToAlertPresence(WebDriver driver) {
        explicitWait = new WebDriverWait(driver, longTimeOut);
        explicitWait.until(ExpectedConditions.alertIsPresent());
    }
//    public By byXpath(WebElement element) {
//        return By.xpath(locator);
//    }

    public String getElementText(WebDriver driver, String locator) {
        return element.getText();
    }
    public void waitElementVisible(WebDriver driver, WebElement element) {
        explicitWait = new WebDriverWait(driver, longTimeOut);
        explicitWait.until(ExpectedConditions.visibilityOf(element));
    }
    public void waitElementInVisible(WebDriver driver, WebElement element) {
        explicitWait = new WebDriverWait(driver, longTimeOut);
        explicitWait.until(ExpectedConditions.invisibilityOf(element));
    }

    public void waitElementClickable(WebDriver driver, WebElement element) {
        explicitWait = new WebDriverWait(driver, longTimeOut);
        explicitWait.until(ExpectedConditions.elementToBeClickable(element));
    }
    public boolean isControlDisplayed(WebDriver driver, WebElement element) {
        return element.isDisplayed();
    }

    public String verifyGetTextAlert(WebDriver driver) {
        alert = driver.switchTo().alert();
        return alert.getText().trim();
    }
    public void acceptAlert(WebDriver driver) {
        alert = driver.switchTo().alert();
        alert.accept();
    }

    public void sleepInSecond(long time) {
        try {
            Thread.sleep(time * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


}
