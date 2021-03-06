package com.example.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("google")
public class Google extends Base {

    @Value("${googleUrl}")
    private String url;

    @FindBy(id = "lst-ib")
    private WebElement searchTextBox;
    @FindBy(name = "btnK")
    private WebElement googleSearchBtn;
    @FindBy(id = "resultStats")
    private WebElement resultCount;

    private String searchQuery="";
    public String getSearchQuery() {
        return searchQuery;
    }

    private void setSearchQuery(String searchQuery) {
        this.searchQuery = searchQuery;
    }

    public Google(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public boolean isAt() {
        return false;
    }

    public void goTo() {
        this.driver.get(url);
    }

    public void enterSearchQuery(String text) {
        setSearchQuery(text);
        mediumWait.until(ExpectedConditions.elementToBeClickable(searchTextBox)).clear();
        searchTextBox.sendKeys(getSearchQuery());
    }

    public void clickGoogleSearchBtn() {
        searchTextBox.submit();
    }

}
