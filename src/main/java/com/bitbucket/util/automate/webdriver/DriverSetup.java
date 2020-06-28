package com.bitbucket.util.automate.webdriver;

/**
 *
 * @author Ritika.Ghosh
 */
import org.openqa.selenium.WebDriver;

public interface DriverSetup {

    WebDriver getWebDriverObject(DesiredCapabilities desiredCapabilities);

    DesiredCapabilities getDesiredCapabilities();
}
