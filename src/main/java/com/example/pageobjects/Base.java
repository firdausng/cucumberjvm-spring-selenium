package com.example.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;

public class Base {

    WebDriver driver;
    @Autowired
    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }
    public WebDriver getDriver() {return driver; }

    protected static WebDriverWait shortWait;
    protected static WebDriverWait mediumWait;
    protected static WebDriverWait longWait;
    protected static WebDriverWait superLongWait;

    public Base(WebDriver driver){
        this.driver = driver;
        setupWait(10,30,60,60*5);
    }

    private void setupWait(int _shortWait, int _mediumWait, int _longWait, int _superLongWait){
        shortWait = new WebDriverWait(driver, _shortWait);
        mediumWait = new WebDriverWait(driver, _mediumWait);
        longWait = new WebDriverWait(driver, _longWait);
        superLongWait = new WebDriverWait(driver, _superLongWait);
    }
}
