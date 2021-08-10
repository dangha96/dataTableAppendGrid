package com.jquery.datatable;

import common.AbstractPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObject.jquery.HomePageObject;
import pageObject.jquery.PageGenerator;
import pageUI.jquery.HomePageUI;


public class Level_12_DataTable extends AbstractPage {
    WebDriver driver;
    HomePageObject homePage;
    String projectPath = System.getProperty("user.dir");


    //@Parameters("browser")
    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", projectPath +
                "\\browserDriver\\chromedriver.exe");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.jqueryscript.net/demo/jQuery-Dynamic-Data-Grid-Plugin-appendGrid/");
        driver.manage().window().maximize();
        homePage = PageGenerator.getHomePage(driver);
    }

    @Test

    public void TC_01_Table_Index() {

        //khac cha. input cot 2
        homePage.inputToTextBoxAtRowNumber("Company", "1", "Viet Nam");
        homePage.inputToTextBoxAtRowNumber("Contact Person", "2", "Automation");
        homePage.sleepInSecond(3);
        homePage.inputToTextBoxAtRowNumber("Order Placed", "1", "1");
        homePage.sleepInSecond(3);
        homePage.selectValueAtRowNumber("Country","1","Germany");
        homePage.sleepInSecond(5);
        homePage.selectItemInDropDown(driver, HomePageUI.DYNAMIC_SELECT_BY_COLUMN_AND_ROW_INDEX,"Germany");

    }


    @AfterClass
    public void afterClass() {
        driver.quit();

    }
}
