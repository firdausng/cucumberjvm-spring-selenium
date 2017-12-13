package com.example.runner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
//        plugin = {"util.TestRailReporter:target/report/TestRailReporter.json"},
//        tags = {"@test"},
        strict = true,
//        format = {"pretty","json:target/report/cucumber2.json"},
        features = {"src/test/resources/features"},
        glue = {"com/example/steps"}
)
public class UITest {
}
