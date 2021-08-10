package com.java.basic;

public class Path12_Dynamic_Locator {
    static String NEW_CUSTOMER_LINK = "//a[text()='New Customer']";
    static String DEPOSIT_LINK = "//a[text()='Deposit']";
    //1 locator=n page ( khac nhau value)
    // 1 tham số truyền vào
    static String DYNAMIC_MENU_LINK = "//a[text()='%s']";
// 2 tham số truyền vào
    static String DYNAMIC_ROW_COUNTRY = "//td[@data-key='country' and text()='%s']"
        +"/following-sibling::td[@data-key='total' and text() ='%s']";
// 3 tham số truyển vào
static String DYNAMIC_ROW_COUNTRY_FEMALE = "//td[@data-key='country' and text()='%s']"
        +"/following-sibling::td[@data-key='males' and text() ='%s']"
        +"/following-sibling::td[@data-key='total' and text() ='%s']";
    public static void main(String args[]) {
        clickToMenu(DYNAMIC_MENU_LINK, "New Customer");
        clickToMenu(DYNAMIC_MENU_LINK, "Deposit");

    }

    public static void clickToMenu(String menuLink) {
        System.out.println("Click to" + menuLink);
    }

//    public static void clickToMenu(String locator, String value) {
//        locator = String.format(locator, value);
//        System.out.println("Click to" + locator);
//    }
//
//    public static void clickToMenu(String locator, String firstValue, String secondValue) {
//        locator = String.format(locator, firstValue,secondValue);
//        System.out.println("Click to" + locator);
//    }

    public static void clickToMenu(String locator, String... value) {
        locator = String.format(locator, (Object[]) value);
        System.out.println("Click to" + locator);
    }
}

