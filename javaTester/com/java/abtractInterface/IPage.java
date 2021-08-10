package com.java.abtractInterface;

public interface IPage {
    public abstract boolean isPageLoaded();
    abstract void clickToElement(String locator);
    void sendKeyToELement(String locator,String value);

    public static void waitForElementVisible(){

    }
}
