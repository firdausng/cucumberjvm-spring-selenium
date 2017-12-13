package com.example.pageobjects;

import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Google extends Base {

    public Google(WebDriver driver){
        super(driver);
    }

    public void goTo(){
        this.driver.get("https://www.google.com");
    }

}
