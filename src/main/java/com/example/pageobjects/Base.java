package com.example.pageobjects;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class Base {

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

    public String getPageTitle(){
        return driver.getTitle();
    }

    public void quit(){
        driver.quit();
    }

    public byte[] takeScreenshot(){
        byte[] screenshot = null;
        try {
            screenshot = ((TakesScreenshot) this.driver).getScreenshotAs(OutputType.BYTES);
        } catch (WebDriverException wde) {
            System.err.println(wde.getMessage());
        } catch (ClassCastException cce) {
            cce.printStackTrace();
        }
        return screenshot;
    }

    public void navigate(String url){driver.get(url); }

    public abstract boolean isAt();
}
