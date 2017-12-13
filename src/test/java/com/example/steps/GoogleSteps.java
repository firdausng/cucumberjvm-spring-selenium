package com.example.steps;

import com.example.AppConfig;
import com.example.pageobjects.Google;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import cucumber.api.PendingException;
import cucumber.api.java8.En;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = AppConfig.class)
public class GoogleSteps implements En {

    @Autowired
    public GoogleSteps(Google google){

        Given("^I go to google$", google::goTo);
        When("^I query for \"([^\"]*)\"$", google::enterSearchQuery);
        And("^click search$", google::clickGoogleSearchBtn);
        Then("^google page title should become \"([^\"]*)\"$", (String pageTitle) -> assertThat(pageTitle,is(google.getSearchQuery() +" - Google Search")));
    }

}
