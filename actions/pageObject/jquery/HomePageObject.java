package pageObject.jquery;

import common.AbstractPage;
import org.openqa.selenium.WebDriver;
import pageUI.jquery.HomePageUI;

public class HomePageObject extends AbstractPage {
    WebDriver driver;

    public HomePageObject(WebDriver driver) {
        this.driver = driver;

    }

//    public void inputToTextBoxByColumnName(String columnName, String value) {
//        waitElementVisible(driver, HomePageUI.DYNAMIC_TEXTBOX_BY_COLUMN_NAME, columnName);
//        sendKeyToElement(driver, HomePageUI.DYNAMIC_TEXTBOX_BY_COLUMN_NAME, value, columnName);
//
//    }
//
//    public void navigateToPageByPageNumber(String pageNumber) {
//        waitElementVisible(driver, HomePageUI.DYNAMIC_PAGING_BY_PAGENUMBER, pageNumber);
//        clickToElement(driver, HomePageUI.DYNAMIC_PAGING_BY_PAGENUMBER, pageNumber);
//    }
//
//    public boolean isPageActiveByPageNumber(String pageNumber) {
//        waitElementVisible(driver, HomePageUI.DYNAMIC_PAGING_BY_PAGE_ACTIVE, pageNumber);
//        return isControlDisplayed(driver, HomePageUI.DYNAMIC_PAGING_BY_PAGE_ACTIVE, pageNumber);
//    }
//
//
//    public void clickToDeleteIconByCountryName(String countryName) {
//        waitElementVisible(driver, HomePageUI.DYNAMIC_DELETE_BY_COUNTRY_NAME, countryName);
//        clickToElement(driver, HomePageUI.DYNAMIC_DELETE_BY_COUNTRY_NAME, countryName);
//    }
//
//
//    public void clickToEditIconByCountryName(String countryName) {
//        waitElementVisible(driver, HomePageUI.EDIT_BY_COUNTRY_NAME, countryName);
//        clickToElement(driver, HomePageUI.EDIT_BY_COUNTRY_NAME, countryName);
//    }
//
//    public boolean isAllInforDisplayed(String females, String country, String males, String total) {
//        waitElementVisible(driver,HomePageUI.DYNAMIC_ALL_INFOR_AT_ROW,females,country,males,total);
//        return isControlDisplayed(driver,HomePageUI.DYNAMIC_ALL_INFOR_AT_ROW,females,country,males,total);
//
//    }

    public void inputToTextBoxAtRowNumber(String columnName, String rowNumber, String value) {
        waitElementVisible(driver,HomePageUI.DYNAMIC_COLUMN_INDEX_BY_COLUMN_NAME, columnName);
       String columnNameIndex= String.valueOf(countElementSize(driver,HomePageUI.DYNAMIC_COLUMN_INDEX_BY_COLUMN_NAME, columnName)+1);
       waitElementVisible(driver,HomePageUI.DYNAMIC_TEXTBOX_BY_COLUMN_AND_ROW_INDEX,rowNumber,columnNameIndex);
       sendKeyToElement(driver,HomePageUI.DYNAMIC_TEXTBOX_BY_COLUMN_AND_ROW_INDEX,value,rowNumber,columnNameIndex);

    }
    public void selectValueAtRowNumber(String columnName, String rowNumber, String itemValue) {
        waitElementVisible(driver,HomePageUI.DYNAMIC_COLUMN_INDEX_BY_COLUMN_NAME, columnName);
        String columnNameIndex= String.valueOf(countElementSize(driver,HomePageUI.DYNAMIC_COLUMN_INDEX_BY_COLUMN_NAME, columnName)+1);
        waitElementVisible(driver,HomePageUI.DYNAMIC_SELECT_BY_COLUMN_AND_ROW_INDEX,rowNumber,columnNameIndex);
        selectItemInDropDown(driver,HomePageUI.DYNAMIC_SELECT_BY_COLUMN_AND_ROW_INDEX,itemValue,rowNumber,columnNameIndex);

    }
}
