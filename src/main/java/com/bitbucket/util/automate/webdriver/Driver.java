package com.bitbucket.util.automate.webdriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 *
 * @author Ritika.Ghosh
 */
public class Driver {

    private static final List<WebDriverThread> webDriverThreadPool = Collections.synchronizedList(new ArrayList<WebDriverThread>());

    private static final List<ThreadLocal<WebDriverThread>> driverThreads = Collections.synchronizedList(new ArrayList<ThreadLocal<WebDriverThread>>());

    private static ThreadLocal<WebDriverThread> activeThread;

    public static WebDriver webDriver;

    public static WebDriver getDriver() {
        try {
            if (webDriver == null) {
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--no-sandbox");
                chromeOptions.addArguments("--headless");
                chromeOptions.addArguments("disable-gpu");
                chromeOptions.addArguments("--window-size=1280,800");
                WebDriverManager.chromedriver().setup();
                webDriver = new ChromeDriver(chromeOptions);
            }
            return webDriver;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void quitBrowser() {
        WebDriver driver = getDriver();
        if (driver != null) {
            driver.close();
            driver.quit();
        }
    }

    public static DriverType getBrowserType() {
        return activeThread.get().getSelectedDriverType();
    }

}
