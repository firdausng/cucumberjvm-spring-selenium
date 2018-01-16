package com.example.utils;

import com.example.AppConfig;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import io.github.bonigarcia.wdm.InternetExplorerDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;

import java.net.URL;

//@ContextConfiguration(classes = AppConfig.class)
public class SharedDriver extends EventFiringWebDriver {
    private static WebDriver REAL_DRIVER;
    private static final Thread CLOSE_THREAD = new Thread() {
        @Override
        public void run() {
            quitGlobalInstance();
        }
    };

    @Value("${ui.defaultBrowser}")
    private static String defaultBrowser;

    @Value("${ui.seleniumHubURL}")
    private static String seleniumHubURL;

    private static void quitGlobalInstance() {
        WebDriver driver = REAL_DRIVER;
        REAL_DRIVER = null;
        if (driver != null) {
            driver.quit();
        }
    }

    private static WebDriver getRealDriver() throws Throwable {
        if (REAL_DRIVER == null) {
            REAL_DRIVER = getBrowser();
        }
        return REAL_DRIVER;
    }


    public SharedDriver() throws Throwable {
        super(getRealDriver());
        Runtime.getRuntime().addShutdownHook(CLOSE_THREAD);
    }



    @Override
    public void close() {
        if (Thread.currentThread() != CLOSE_THREAD) {
            throw new UnsupportedOperationException("You shouldn't close this WebDriver. It's shared and will close when the JVM exits.");
        }
        try {
            super.close();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    private static WebDriver getBrowser() throws Throwable {
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
                desiredBrowser = new RemoteWebDriver(new URL(seleniumHubURL), cap);
                break;
            default:
                //work out what to do when a browser isn't needed
                break;
        }
        return desiredBrowser;
    }
}
