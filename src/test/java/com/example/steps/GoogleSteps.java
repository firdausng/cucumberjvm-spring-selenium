package com.example.steps;

import com.example.AppConfig;
import com.example.pageobjects.Google;
import cucumber.api.java.en.Given;
import cucumber.api.java8.En;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;


@ContextConfiguration(classes = AppConfig.class)
public class GoogleSteps implements En {

//    Google google;

    @Autowired
    public GoogleSteps(Google google){
        Given("^I go to google$", google::goTo);
    }

//    @Given("^I go to google$")
//    public void goGoogle(){
//        google.goTo();
//    }

}
