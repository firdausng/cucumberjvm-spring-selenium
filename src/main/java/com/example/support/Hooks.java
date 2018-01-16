package com.example.support;

import com.example.AppConfig;
import com.example.pageobjects.Google;
import cucumber.api.java8.En;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = AppConfig.class)
public class Hooks implements En{

    @Autowired
    public Hooks(Google page){
        After(scenario->{
            if (scenario.isFailed()) {scenario.embed(page.takeScreenshot(), "image/png"); }
        });
    }
}
