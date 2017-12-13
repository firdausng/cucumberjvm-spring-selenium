package com.example.pageobjects;

import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;

public class Base {

    WebDriver driver;
    @Autowired
    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }
    public WebDriver getDriver() {return driver; }

    public Base(WebDriver driver){
        this.driver = driver;
    }
}
