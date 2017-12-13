package com.example;

import com.example.pageobjects.Google;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import io.github.bonigarcia.wdm.InternetExplorerDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.net.URL;

@Configuration
@ComponentScan({"com.example"})
public class AppConfig {

    @Bean(name="webDriver")
    public WebDriver getWebDriver()  throws Throwable{
        String desiredBrowserName = System.getProperty("browser", "chrome");
        WebDriver desiredBrowser = null;

        switch(desiredBrowserName) {
            case "ie":
                InternetExplorerDriverManager.getInstance().setup();
                desiredBrowser = new InternetExplorerDriver();
                break;
            case "chrome":
                ChromeDriverManager.getInstance().setup();
                desiredBrowser = new ChromeDriver();
                break;
            case "firefox":
                FirefoxDriverManager.getInstance().setup();
                desiredBrowser = new FirefoxDriver();
                break;
            case "remote":
                DesiredCapabilities cap = DesiredCapabilities.chrome();
                cap.setBrowserName("chrome");
                desiredBrowser = new RemoteWebDriver(new URL("http://local:4444/wd/hub"), cap);
                break;
            default:
                //work out what to do when a browser isn't needed
                break;
        }
        return desiredBrowser;
    }
}
