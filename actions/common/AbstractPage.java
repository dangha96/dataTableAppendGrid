package common;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;



import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;


public class AbstractPage {
    private WebDriverWait explicitWait;
    Alert alert;
    private Select select;
    JavascriptExecutor jsExecutor;
    private Actions action;
    private WebElement element;
    private long longTimeOut = GlobalConstant.LONG_TIMEOUT;


    public void openUrl(WebDriver driver, String Url) {
        driver.get(Url);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public String getPageTitle(WebDriver driver) {
        return driver.getTitle();

    }

    public String getCurrentUrl(WebDriver driver) {
        return driver.getCurrentUrl();
    }

    public String getPageSource(WebDriver driver) {
        return driver.getPageSource();
    }

    public void verifyBack(WebDriver driver) {
        driver.navigate().back();
    }

    public void verifyRefresh(WebDriver driver) {
        driver.navigate().refresh();
    }

    public void verifyForward(WebDriver driver) {
        driver.navigate().forward();
    }

    public void waitToAlertPresence(WebDriver driver) {
        explicitWait = new WebDriverWait(driver, longTimeOut);
        explicitWait.until(ExpectedConditions.alertIsPresent());
    }

    public void acceptAlert(WebDriver driver) {
        alert = driver.switchTo().alert();
        alert.accept();
    }

    public void verifyCancelAlert(WebDriver driver) {
        alert = driver.switchTo().alert();
        alert.dismiss();
    }

    public void verifySendKeyAlert(WebDriver driver, String value) {
        alert = driver.switchTo().alert();
        alert.sendKeys(value);
    }

    public String verifyGetTextAlert(WebDriver driver) {
        alert = driver.switchTo().alert();
        return alert.getText().trim();
    }

    public void switchToWindowByID(WebDriver driver, String parentID) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String runWindow : allWindows) {
            if (!runWindow.equals(parentID)) {
                driver.switchTo().window(runWindow);
                break;
            }
        }
    }

    //more than 1 window
    public void switchToWindowByTitle(WebDriver driver, String title) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String runWindows : allWindows) {
            driver.switchTo().window(runWindows);
            String currentWin = driver.getTitle();
            if (currentWin.equals(title)) {
                break;
            }
        }
    }

    public void closeAllWindowWithoutParent(WebDriver driver, String parentID) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String runWindows : allWindows) {
            if (!runWindows.equals(parentID)) {
                driver.switchTo().window(runWindows);
                driver.close();
            }
        }
        driver.switchTo().window(parentID);
    }

    public By byXpath(String locator) {
        return By.xpath(locator);
    }

    public WebElement find(WebDriver driver, String locator) {
        return driver.findElement(byXpath(locator));
    }

    public List<WebElement> finds(WebDriver driver, String locator) {
        return driver.findElements(byXpath(locator));

    }

    public String castToRestParameter(String locator, String... value) {
        locator = String.format(locator, (Object[]) value);
        return locator;

    }


    public void clickToElement(WebDriver driver, String locator) {
        find(driver, locator).click();
    }

    public void clickToElement(WebDriver driver, String locator, String... values) {
        find(driver, castToRestParameter(locator, values)).click();
    }


    public void sendKeyToElement(WebDriver driver, String locator, String value) {
        find(driver, locator).clear();
        find(driver, locator).sendKeys(value);
    }

    public void sendKeyToElement(WebDriver driver, String locator, String value, String... values) {
        find(driver, castToRestParameter(locator, values)).clear();
        find(driver, castToRestParameter(locator, values)).sendKeys(value);
    }

    public void selectItemInDropDown(WebDriver driver, String locator, String itemValue) {
        select = new Select(find(driver, locator));
        select.selectByVisibleText(itemValue);
    }

    public void selectItemInDropDown(WebDriver driver, String locator, String itemValue, String... values) {
        select = new Select(find(driver, castToRestParameter(locator, values)));
        select.selectByVisibleText(itemValue);
    }



    public String getFirstSelectedItemInDropDown(WebDriver driver, String locator) {
        return select.getFirstSelectedOption().getText();
    }

    public boolean isDropDownMulti(WebDriver driver, String locator) {
        select = new Select(find(driver, locator));
        return select.isMultiple();
    }

    public void selectItemInCustomDropdown(WebDriver driver, String parentLocator, String childItemLocator, String expectedItem) {
        // 1 - Click vào thẻ (cha) để xổ ra tất cả các item con
        find(driver, parentLocator).click();
        sleepInSecond(1);

        // 2 - Chờ cho tất cả các item con được load ra
        explicitWait = new WebDriverWait(driver, 20);
        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(byXpath(childItemLocator)));

        // Đưa tất cả các item trong dropdown vào 1 list để kiểm tra
        List<WebElement> allItems = finds(driver, childItemLocator);
        ;


        // 3 - Chạy qua tất cả các giá trị đang có trong list
        for (WebElement item : allItems) {
            // 4 - Kiểm tra xem text của các giá trị có item nào bằng vs text mong muốn ko
            if (item.getText().equals(expectedItem)) {
                // 5 - Scroll xuống đến đúng item này
                jsExecutor = (JavascriptExecutor) driver;
                jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
                sleepInSecond(1);

                // 6 - Click vào cái item này
                item.click();
                sleepInSecond(1);
                break;
            }
        }
    }


    public String getElementAttribute(WebDriver driver, String locator, String attributeName) {
        return find(driver, locator).getAttribute(attributeName);
    }

    public String getElementText(WebDriver driver, String locator) {
        return find(driver, locator).getText();
    }

    public int countElementSize(WebDriver driver, String locator) {
        return finds(driver, locator).size();
    }

    public int countElementSize(WebDriver driver, String locator, String... values) {
        return finds(driver,castToRestParameter(locator,values)).size();
    }
    public void checkToCheckbox(WebDriver driver, String locator) {
        if (!find(driver, locator).isSelected()) {
            find(driver, locator).click();
        }
    }

    public void unCheckToCheckbox(WebDriver driver, String locator) {
        if (find(driver, locator).isSelected()) {
            find(driver, locator).click();
        }
    }

    public boolean isControlDisplayed(WebDriver driver, String locator) {
        return find(driver, locator).isDisplayed();
    }

    public boolean isControlDisplayed(WebDriver driver, String locator, String... values) {
        find(driver, castToRestParameter(locator, values)).isDisplayed();
        return true;

    }


    public boolean isControlEnable(WebDriver driver, String locator) {
        return find(driver, locator).isEnabled();
    }

    public boolean isControlSelected(WebDriver driver, String locator) {
        return find(driver, locator).isSelected();
    }

    public void switchToFrame(WebDriver driver, String locator) {
        driver.switchTo().frame(find(driver, locator));
    }

    public void switchToDefaultPage(WebDriver driver) {
        driver.switchTo().defaultContent();
    }

    public void doubleClickToElement(WebDriver driver, String locator) {
        action = new Actions(driver);
        action.doubleClick(find(driver, locator)).perform();
    }

    public void rightClick(WebDriver driver, String locator) {
        action = new Actions(driver);
        action.contextClick(find(driver, locator)).perform();
    }

    public void hoverToElement(WebDriver driver, String locator) {
        action = new Actions(driver);
        action.moveToElement(find(driver, locator)).perform();
    }

    public void dragAndDropElement(WebDriver driver, String sourceLocator, String targetLocator) {
        action = new Actions(driver);
        action.dragAndDrop(find(driver, sourceLocator), find(driver, targetLocator)).perform();
    }

    public void sendKeyBoardToElement(WebDriver driver, String locator, Keys key) {
        action = new Actions(driver);
        action.sendKeys(find(driver, locator), key).perform();
    }


    public Object executeForBrowser(WebDriver driver, String javaScript) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        return jsExecutor.executeScript(javaScript);
    }

    public String getInnerText(WebDriver driver) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
    }

    public boolean areExpectedTextInInnerText(WebDriver driver, String textExpected) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0]");
        return textActual.equals(textExpected);
    }

    public void scrollToBottomPage(WebDriver driver) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public void navigateToUrlByJS(WebDriver driver, String url) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.location = '" + url + "'");
    }

    public void highlightElement(WebDriver driver, String locator) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        element = find(driver, locator);
        String originalStyle = element.getAttribute("style");
        jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
        sleepInSecond(1);
        jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
    }

    public void clickToElementByJS(WebDriver driver, String locator) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        element = find(driver, locator);
        jsExecutor.executeScript("arguments[0].click();", element);
    }

    public void scrollToElement(WebDriver driver, String locator) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        element = find(driver, locator);
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void sendkeyToElementByJS(WebDriver driver, String locator, String value) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        element = find(driver, locator);
        jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", element);
    }

    public void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        element = find(driver, locator);
        jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", element);
    }

    public boolean areJQueryAndJSLoadedSuccess(WebDriver driver) {
        WebDriverWait explicitWait = new WebDriverWait(driver, longTimeOut);
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    return ((Long) jsExecutor.executeScript("return jQuery.active") == 0);
                } catch (Exception e) {
                    return true;
                }
            }
        };

        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
            }
        };

        return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
    }

    public WebElement getElement(WebDriver driver, String xpathLocator) {
        return driver.findElement(By.xpath(xpathLocator));
    }

    public boolean isImageLoaded(WebDriver driver, String locator) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        element = find(driver, locator);
        boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", element);
        if (status) {
            return true;
        } else {
            return false;
        }

    }

    public void waitToElementPresence(WebDriver driver, String locator) {
        explicitWait = new WebDriverWait(driver, longTimeOut);
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(byXpath(locator)));
    }

    public void waitElementVisible(WebDriver driver, String locator) {
        explicitWait = new WebDriverWait(driver, longTimeOut);
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(byXpath(locator)));
    }

    public void waitElementVisible(WebDriver driver, String locator, String... values) {
        explicitWait = new WebDriverWait(driver, longTimeOut);
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(byXpath(castToRestParameter(locator, values))));
    }


    public void waitElementInVisible(WebDriver driver, String locator) {
        explicitWait = new WebDriverWait(driver, longTimeOut);
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(byXpath(locator)));
    }

    public void waitElementClickable(WebDriver driver, String locator) {
        explicitWait = new WebDriverWait(driver, longTimeOut);
        explicitWait.until(ExpectedConditions.elementToBeClickable(byXpath(locator)));
    }

    public void waitElementClickable(WebDriver driver, String locator, String... values) {
        explicitWait = new WebDriverWait(driver, longTimeOut);
        explicitWait.until(ExpectedConditions.elementToBeClickable(byXpath(castToRestParameter(locator, values))));
    }


    public void clearTextBox(WebDriver driver, String locator) {

    }

    public void sleepInSecond(long time) {
        try {
            Thread.sleep(time * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    /*Open Bank guru
    public NewCustomerPageObject openNewCustomerPage(WebDriver driver) {
        waitElementClickable(driver, AbstractPageUI.NEW_CUSTOMER_LINK);
        clickToElement(driver,AbstractPageUI.NEW_CUSTOMER_LINK);
        return PageGeneratorManager.getNewCustomerPage(driver);
    }
//    public EditCustomer openEditCustomerPage(WebDriver driver) {
//        waitElementClickable(driver, AbstractPageUI.EDIT_CUSTOMER_LINK);
//        clickToElement(driver,AbstractPageUI.EDIT_CUSTOMER_LINK);
//        return PageGeneratorManager.ge(driver);
//    }

    /*Open DYNAMIC page menu*/
    /*Số lượng page ít 10-15

    public AbstractPage openMenuPageByName(WebDriver driver, String pageName){
        waitElementClickable(driver, AbstractPageUI.DYNAMIC_MENU_PAGE_LINK, pageName);
        clickToElement(driver,AbstractPageUI.DYNAMIC_MENU_PAGE_LINK, pageName);
        if(pageName.equals("New Customer")){
             return PageGeneratorManager.getNewCustomerPage(driver);
        } else {
            throw new RuntimeException();
        }

    }
*/
    /*Number of page from 100..
    public void openManyPageByName(WebDriver driver, String pageName){
        waitElementClickable(driver, AbstractPageUI.DYNAMIC_MENU_PAGE_LINK, pageName);
        clickToElement(driver,AbstractPageUI.DYNAMIC_MENU_PAGE_LINK, pageName);


    }*/
}

