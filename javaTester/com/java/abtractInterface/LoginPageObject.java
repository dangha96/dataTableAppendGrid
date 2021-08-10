package com.java.abtractInterface;

public class LoginPageObject implements IPage{


    @Override
    public boolean isPageLoaded() {
        return false;
    }

    @Override
    public void clickToElement(String locator) {

    }

    @Override
    public void sendKeyToELement(String locator, String value) {

    }
}
